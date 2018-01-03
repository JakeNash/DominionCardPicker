'use strict';

const React = require('react');
const ReactDOM = require('react-dom')
const when = require('when');
const client = require('./client');

const follow = require('./follow'); // function to hop multiple links by "rel"

const stompClient = require('./websocket-listener');

const root = '/api';

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {cards: [], attributes: [], page: 1, pageSize: 2, links: {}};
		this.updatePageSize = this.updatePageSize.bind(this);
		this.onCreate = this.onCreate.bind(this);
		this.onUpdate = this.onUpdate.bind(this);
		this.onDelete = this.onDelete.bind(this);
		this.onNavigate = this.onNavigate.bind(this);
		this.refreshCurrentPage = this.refreshCurrentPage.bind(this);
		this.refreshAndGoToLastPage = this.refreshAndGoToLastPage.bind(this);
	}

	loadFromServer(pageSize) {
		follow(client, root, [
				{rel: 'cards', params: {size: pageSize}}]
		).then(cardCollection => {
				return client({
					method: 'GET',
					path: cardCollection.entity._links.profile.href,
					headers: {'Accept': 'application/schema+json'}
				}).then(schema => {
					this.schema = schema.entity;
					this.links = cardCollection.entity._links;
					return cardCollection;
				});
		}).then(cardCollection => {
			this.page = cardCollection.entity.page;
			return cardCollection.entity._embedded.cards.map(card =>
					client({
						method: 'GET',
						path: card._links.self.href
					})
			);
		}).then(cardPromises => {
			return when.all(cardPromises);
		}).done(cards => {
			this.setState({
				page: this.page,
				cards: cards,
				attributes: Object.keys(this.schema.properties),
				pageSize: pageSize,
				links: this.links
			});
		});
	}

	onCreate(newCard) {
		follow(client, root, ['cards']).done(response => {
			client({
				method: 'POST',
				path: response.entity._links.self.href,
				entity: newCard,
				headers: {'Content-Type': 'application/json'}
			})
		})
	}

	onUpdate(card, updatedCard) {
		client({
			method: 'PUT',
			path: card.entity._links.self.href,
			entity: updatedCard,
			headers: {
				'Content-Type': 'application/json',
				'If-Match': card.headers.Etag
			}
		}).done(response => {
			/* Let the websocket handler update the state */
		}, response => {
			if (response.status.code === 412) {
				alert('DENIED: Unable to update ' + card.entity._links.self.href + '. Your copy is stale.');
			}
		});
	}

	onDelete(card) {
		client({method: 'DELETE', path: card.entity._links.self.href});
	}

	onNavigate(navUri) {
		client({
			method: 'GET',
			path: navUri
		}).then(cardCollection => {
			this.links = cardCollection.entity._links;
			this.page = cardCollection.entity.page;

			return cardCollection.entity._embedded.cards.map(card =>
					client({
						method: 'GET',
						path: card._links.self.href
					})
			);
		}).then(cardPromises => {
			return when.all(cardPromises);
		}).done(cards => {
			this.setState({
				page: this.page,
				cards: cards,
				attributes: Object.keys(this.schema.properties),
				pageSize: this.state.pageSize,
				links: this.links
			});
		});
	}

	updatePageSize(pageSize) {
		if (pageSize !== this.state.pageSize) {
			this.loadFromServer(pageSize);
		}
	}

	refreshAndGoToLastPage(message) {
		follow(client, root, [{
			rel: 'cards',
			params: {size: this.state.pageSize}
		}]).done(response => {
			if (response.entity._links.last !== undefined) {
				this.onNavigate(response.entity._links.last.href);
			} else {
				this.onNavigate(response.entity._links.self.href);
			}
		})
	}

	refreshCurrentPage(message) {
		follow(client, root, [{
			rel: 'cards',
			params: {
				size: this.state.pageSize,
				page: this.state.page.number
			}
		}]).then(cardCollection => {
			this.links = cardCollection.entity._links;
			this.page = cardCollection.entity.page;

			return cardCollection.entity._embedded.cards.map(card => {
				return client({
					method: 'GET',
					path: card._links.self.href
				})
			});
		}).then(cardPromises => {
			return when.all(cardPromises);
		}).then(cards => {
			this.setState({
				page: this.page,
				cards: cards,
				attributes: Object.keys(this.schema.properties),
				pageSize: this.state.pageSize,
				links: this.links
			});
		});
	}

	componentDidMount() {
		this.loadFromServer(this.state.pageSize);
		stompClient.register([
			{route: '/topic/newCard', callback: this.refreshAndGoToLastPage},
			{route: '/topic/updateCard', callback: this.refreshCurrentPage},
			{route: '/topic/deleteCard', callback: this.refreshCurrentPage}
		]);
	}

	render() {
		return (
			<div>
				<CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
				<CardList page={this.state.page}
							 cards={this.state.cards}
							 links={this.state.links}
							 pageSize={this.state.pageSize}
							 attributes={this.state.attributes}
							 onNavigate={this.onNavigate}
							 onUpdate={this.onUpdate}
							 onDelete={this.onDelete}
							 updatePageSize={this.updatePageSize}/>
			</div>
		)
	}
}

class CreateDialog extends React.Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		var newCard = {};
		this.props.attributes.forEach(attribute => {
			newCard[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.props.onCreate(newCard);
		this.props.attributes.forEach(attribute => {
			ReactDOM.findDOMNode(this.refs[attribute]).value = ''; // clear out the dialog's inputs
		});
		window.location = "#";
	}

	render() {
		var inputs = this.props.attributes.map(attribute =>
				<p key={attribute}>
					<input type="text" placeholder={attribute} ref={attribute} className="field" />
				</p>
		);
		return (
			<div>
				<a href="#createCard">Create</a>

				<div id="createCard" className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Create new card</h2>

						<form>
							{inputs}
							<button onClick={this.handleSubmit}>Create</button>
						</form>
					</div>
				</div>
			</div>
		)
	}
}

class UpdateDialog extends React.Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		var updatedCard = {};
		this.props.attributes.forEach(attribute => {
			updatedCard[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.props.onUpdate(this.props.card, updatedCard);
		window.location = "#";
	}

	render() {
		var inputs = this.props.attributes.map(attribute =>
				<p key={this.props.card.entity[attribute]}>
					<input type="text" placeholder={attribute}
						   defaultValue={this.props.card.entity[attribute]}
						   ref={attribute} className="field" />
				</p>
		);

		var dialogId = "updateCard-" + this.props.card.entity._links.self.href;

		return (
			<div>
				<a href={"#" + dialogId}>Update</a>

				<div id={dialogId} className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Update a card</h2>

						<form>
							{inputs}
							<button onClick={this.handleSubmit}>Update</button>
						</form>
					</div>
				</div>
			</div>
		)
	}

}

class CardList extends React.Component {

	constructor(props) {
		super(props);
		this.handleNavFirst = this.handleNavFirst.bind(this);
		this.handleNavPrev = this.handleNavPrev.bind(this);
		this.handleNavNext = this.handleNavNext.bind(this);
		this.handleNavLast = this.handleNavLast.bind(this);
		this.handleInput = this.handleInput.bind(this);
	}

	handleInput(e) {
		e.preventDefault();
		var pageSize = ReactDOM.findDOMNode(this.refs.pageSize).value;
		if (/^[0-9]+$/.test(pageSize)) {
			this.props.updatePageSize(pageSize);
		} else {
			ReactDOM.findDOMNode(this.refs.pageSize).value = pageSize.substring(0, pageSize.length - 1);
		}
	}

	handleNavFirst(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.first.href);
	}

	handleNavPrev(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.prev.href);
	}

	handleNavNext(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.next.href);
	}

	handleNavLast(e) {
		e.preventDefault();
		this.props.onNavigate(this.props.links.last.href);
	}

	render() {
		var pageInfo = this.props.page.hasOwnProperty("number") ?
			<h3>Cards - Page {this.props.page.number + 1} of {this.props.page.totalPages}</h3> : null;

		var cards = this.props.cards.map(card =>
			<Card key={card.entity._links.self.href}
					 card={card}
					 attributes={this.props.attributes}
					 onUpdate={this.props.onUpdate}
					 onDelete={this.props.onDelete}/>
		);

		var navLinks = [];
		if ("first" in this.props.links) {
			navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
		}
		if ("prev" in this.props.links) {
			navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
		}
		if ("next" in this.props.links) {
			navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
		}
		if ("last" in this.props.links) {
			navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
		}

		return (
			<div>
				{pageInfo}
				<input ref="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
				<table>
					<tbody>
						<tr>
							<th>Cost</th>
							<th>Name</th>
							<th>Box</th>
							<th>Types</th>
							<th>Kingdoms</th>
							<th></th>
							<th></th>
						</tr>
						{cards}
					</tbody>
				</table>
				<div>
					{navLinks}
				</div>
			</div>
		)
	}
}

class Card extends React.Component {

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete() {
        this.props.onDelete(this.props.card);
    }

    render() {
        return (
            <tr>
                <td>{this.props.card.entity.cost}</td>
                <td>{this.props.card.entity.name}</td>
                <td>{this.props.card.entity.box}</td>
                <td>{this.props.card.entity.types}</td>
                <td>{this.props.card.entity.kingdoms}</td>
                <td>
                    <UpdateDialog card={this.props.card}
                                  attributes={this.props.attributes}
                                  onUpdate={this.props.onUpdate}/>
                </td>
                <td>
                    <button onClick={this.handleDelete}>Delete</button>
                </td>
            </tr>
        )
    }
}


ReactDOM.render(
	<App />,
	document.getElementById('react')
)
