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
		this.onNavigate = this.onNavigate.bind(this);
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

	componentDidMount() {
		this.loadFromServer(this.state.pageSize);
	}

	render() {
		return (
			<div>
				<CardList page={this.state.page}
							 cards={this.state.cards}
							 links={this.state.links}
							 pageSize={this.state.pageSize}
							 attributes={this.state.attributes}
							 onNavigate={this.onNavigate}
							 updatePageSize={this.updatePageSize}/>
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
					 />
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
    }

    render() {
        return (
            <tr>
                <td>{this.props.card.entity.cost}</td>
                <td>{this.props.card.entity.name}</td>
                <td>{this.props.card.entity.box}</td>
                <td>{this.props.card.entity.types}</td>
                <td>{this.props.card.entity.kingdoms}</td>
            </tr>
        )
    }
}


ReactDOM.render(
	<App />,
	document.getElementById('react')
)
