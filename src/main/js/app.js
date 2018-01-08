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
		this.state = {cards: [], attributes: [], page: 1, pageSize: 2, links: {},
		              kingdoms: [], kingdomAttributes: [], kingdomPage: 1, kingdomPageSize: 2, kingdomLinks: {},
		              matchingKingdoms: [], wantedCard: "", wantedBox: ""};
		this.updatePageSize = this.updatePageSize.bind(this);
		this.onNavigate = this.onNavigate.bind(this);
		this.updateKingdomPageSize = this.updateKingdomPageSize.bind(this);
		this.onKingdomNavigate = this.onKingdomNavigate.bind(this);
		this.updateMatchingKingdomWantedComponents = this.updateMatchingKingdomWantedComponents.bind(this);
	}

	mergeUnionArrays(keyName, array1, array2) {
	    var index = {}, i, len, merge = [], arr, name;

        for (var j = 1; j < arguments.length; j++) {
            arr = arguments[j];
            for (i = 0, len = arr.length; i < len; i++) {
                name = arr[i][keyName];
                if ((typeof name != "undefined") && !(name in index)) {
                    index[name] = true;
                    merge.push(arr[i]);
                }
            }
        }
        return(merge);
	}

	mergeIntersectArrays(keyName, array1, array2) {
	    var index = {}, i, len, merge = [], arr, name;

        if (array2.length == 0) {
	        return(array1);
	    } else if (array1.length == 0) {
	        return(array2);
	    } else {
	        for (i = 0, len = array1.length; i < len; i++) {
	            name = array1[i][keyName];
	            if ((typeof name != "undefined")) {
	                index[name] = true;
	            }
	        }
	        for (i = 0, len = array2.length; i < len; i++) {
	            name = array2[i][keyName];
	            if ((typeof name != "undefined") && (name in index)) {
	                merge.push(array2[i]);
	            }
	        }
	        return(merge);
	    }
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

	loadKingdomsFromServer(kingdomPageSize) {
	    follow(client, root, [
	        {rel: 'kingdoms', params: {size: kingdomPageSize}}]
        ).then(kingdomCollection => {
            return client({
        	    method: 'GET',
        		path: kingdomCollection.entity._links.profile.href,
        		headers: {'Accept': 'application/schema+json'}
        	}).then(schema => {
        	    this.kingdomSchema = schema.entity;
        		this.kingdomLinks = kingdomCollection.entity._links;
        		return kingdomCollection;
        	});
        }).then(kingdomCollection => {
        	this.kingdomPage = kingdomCollection.entity.page;
        	return kingdomCollection.entity._embedded.kingdoms.map(kingdom =>
            	client({
        	    	method: 'GET',
        			path: kingdom._links.self.href
        		})
        	);
        }).then(kingdomPromises => {
        	return when.all(kingdomPromises);
        }).done(kingdoms => {
        	this.setState({
        		kingdomPage: this.kingdomPage,
        		kingdoms: kingdoms,
        		kingdomAttributes: Object.keys(this.kingdomSchema.properties),
        		kingdomPageSize: kingdomPageSize,
        		kingdomLinks: this.kingdomLinks
        	});
        });
    }

    loadMatchingKingdomsFromServer(wantedCard, wantedBox) {
        var cardKingdoms = [], boxKingdoms = [], mergedKingdoms = [];

        follow(client, root, [
            {rel: 'kingdoms'}, {rel: 'search'}, {rel: 'findByCards', params: {card: wantedCard}}]
        ).then(kingdomCollection => {
            return kingdomCollection.entity._embedded.kingdoms.map(kingdom =>
                client({
                    method: 'GET',
                    path: kingdom._links.self.href
                    })
            );
        }).then(kingdomPromises => {
            return when.all(kingdomPromises);
        }).done(kingdoms => {
            cardKingdoms = kingdoms;
            follow(client, root, [
                {rel: 'kingdoms'}, {rel: 'search'}, {rel: 'findByBoxes', params: {box: wantedBox}}]
            ).then(kingdomCollection => {
                return kingdomCollection.entity._embedded.kingdoms.map(kingdom =>
                    client({
                        method: 'GET',
                        path: kingdom._links.self.href
                        })
                );
            }).then(kingdomPromises => {
                return when.all(kingdomPromises);
            }).done(kingdoms => {
                boxKingdoms = kingdoms;
                mergedKingdoms = this.mergeIntersectArrays("url", cardKingdoms, boxKingdoms);

                this.setState({
                    matchingKingdoms: mergedKingdoms
                });
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
	
    onKingdomNavigate(navUri) {
        client({
            method: 'GET',
            path: navUri
        }).then(kingdomCollection => {
            this.kingdomLinks = kingdomCollection.entity._links;
            this.kingdomPage = kingdomCollection.entity.page;

            return kingdomCollection.entity._embedded.kingdoms.map(kingdom =>
                    client({
                        method: 'GET',
                        path: kingdom._links.self.href
                    })
            );
        }).then(kingdomPromises => {
            return when.all(kingdomPromises);
        }).done(kingdoms => {
            this.setState({
                kingdomPage: this.kingdomPage,
                kingdoms: kingdoms,
                kingdomAttributes: Object.keys(this.kingdomSchema.properties),
                kingdomPageSize: this.state.kingdomPageSize,
                kingdomLinks: this.kingdomLinks
            });
        });
    }

	updatePageSize(pageSize) {
		if (pageSize !== this.state.pageSize) {
			this.loadFromServer(pageSize);
		}
	}

	updateKingdomPageSize(kingdomPageSize) {
	    if (kingdomPageSize !== this.state.kingdomPageSize) {
	        this.loadKingdomsFromServer(kingdomPageSize);
	    }
	}

	updateMatchingKingdomWantedComponents(wantedCard, wantedBox) {
	    if (wantedCard !== this.state.wantedCard || wantedBox !== this.state.wantedBox) {
	        this.loadMatchingKingdomsFromServer(wantedCard, wantedBox);
	    }
	}

	componentDidMount() {
		this.loadFromServer(this.state.pageSize);
		this.loadKingdomsFromServer(this.state.kingdomPageSize);
		this.loadMatchingKingdomsFromServer(this.state.wantedCard, this.state.wantedBox);
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
							 updatePageSize={this.updatePageSize} />
				<KingdomList page={this.state.kingdomPage}
				             kingdoms={this.state.kingdoms}
				             links={this.state.kingdomLinks}
				             pageSize={this.state.kingdomPageSize}
				             attributes={this.state.kingdomAttributes}
				             onNavigate={this.onKingdomNavigate}
				             updatePageSize={this.updateKingdomPageSize} />
				<MatchingKingdomList kingdoms={this.state.matchingKingdoms}
				                     wantedCard={this.state.wantedCard}
				                     wantedBox={this.state.wantedBox}
				                     updateKingdom={this.updateMatchingKingdomWantedComponents} />
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
							<th>Other Setup</th>
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
        let otherSetup = null;
        if (this.props.card.entity.otherSetup !== null) {
            otherSetup = <td>{this.props.card.entity.otherSetup.map((setup, i) =>
                            <span key={i}>
                            {!!i && ", "}
                            {setup}
                         </span>)}</td>
        } else {
            otherSetup = <td></td>
        }

        return (
            <tr>
                <td>{this.props.card.entity.cost}</td>
                <td>{this.props.card.entity.name}</td>
                <td>{this.props.card.entity.box}</td>
                <td>{this.props.card.entity.types.map((type, i) =>
                    <span key={i}>
                    {!!i && ", "}
                    {type}
                </span>)}</td>
                {otherSetup}
            </tr>
        )
    }
}

class KingdomList extends React.Component {

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
			ReactDOM.findDOMNode(this.pageSize).value = pageSize.substring(0, pageSize.length - 1);
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
			<h3>Kingdoms - Page {this.props.page.number + 1} of {this.props.page.totalPages}</h3> : null;

		var kingdoms = this.props.kingdoms.map(kingdom =>
			<Kingdom key={kingdom.entity._links.self.href}
					 kingdom={kingdom}
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
							<th>Name</th>
							<th>Cards</th>
							<th>Events</th>
							<th>Landmarks</th>
							<th>Boxes</th>
							<th>Other Setup</th>
						</tr>
						{kingdoms}
					</tbody>
				</table>
				<div>
					{navLinks}
				</div>
			</div>
		)
	}
}

class MatchingKingdomList extends React.Component {
    constructor(props) {
        super(props);
		this.handleInput = this.handleInput.bind(this);
    }

    handleInput(e) {
        e.preventDefault();
        var wantedCard = ReactDOM.findDOMNode(this.refs.wantedCard).value;
        var wantedBox = ReactDOM.findDOMNode(this.refs.wantedBox).value;
        if (/^[a-zA-Z '-]+$/.test(wantedCard) || /^[a-zA-Z ]+$/.test(wantedBox)) {
            this.props.updateKingdom(wantedCard, wantedBox);
        } else {
            ReactDOM.findDOMNode(this.wantedCard).value = wantedCard.substring(0, wantedCard.length - 1);
            ReactDOM.findDOMNode(this.wantedBox).value = wantedBox.substring(0, wantedBox.length - 1);
        }
    }

    render() {
        var kingdoms = this.props.kingdoms.map(kingdom =>
            <Kingdom key={kingdom.entity._links.self.href}
                     kingdom={kingdom}
                     />
        );

        return (
            <div>
                <h3>Matching Kingdoms</h3>
                <input ref="wantedCard" placeholder="Enter Card Here" defaultValue={this.props.wantedCard} onInput={this.handleInput}/>
                <input ref="wantedBox" placeholder="Enter Box Here" defaultValue={this.props.wantedBox} onInput={this.handleInput}/>
                <table>
                    <tbody>
                        <tr>
                            <th>Name</th>
                            <th>Cards</th>
                            <th>Events</th>
                            <th>Landmarks</th>
                            <th>Boxes</th>
                            <th>Other Setup</th>
                        </tr>
                        {kingdoms}
                    </tbody>
                </table>
            </div>
        )
    }
}

class Kingdom extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        let otherSetup = null;
        if (this.props.kingdom.entity.otherSetup !== null) {
            otherSetup = <td>{this.props.kingdom.entity.otherSetup.map((setup, i) =>
                            <span key={i}>
                            {!!i && ", "}
                            {setup}
                         </span>)}</td>
        } else {
            otherSetup = <td></td>
        }

        let events = null;
        if (this.props.kingdom.entity.events !== null) {
            events = <td>{this.props.kingdom.entity.events.map((event, i) =>
                            <span key={i}>
                            {!!i && ", "}
                            {event}
                         </span>)}</td>
        } else {
            events = <td></td>
        }

        let landmarks = null;
        if (this.props.kingdom.entity.landmarks !== null) {
            landmarks = <td>{this.props.kingdom.entity.landmarks.map((landmark, i) =>
                            <span key={i}>
                            {!!i && ", "}
                            {landmark}
                         </span>)}</td>
        } else {
            landmarks = <td></td>
        }

        return (
            <tr>
                <td>{this.props.kingdom.entity.name}</td>
                <td>{this.props.kingdom.entity.cards.map((card, i) =>
                    <span key={i}>
                    {!!i && ", "}
                    {card}
                </span>)}</td>
                {events}
                {landmarks}
                <td>{this.props.kingdom.entity.boxes.map((box, i) =>
                    <span key={i}>
                    {!!i && ", "}
                    {box}
                </span>)}</td>
                {otherSetup}
            </tr>
        )
    }
}


ReactDOM.render(
	<App />,
	document.getElementById('react')
)
