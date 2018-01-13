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
		              matchingKingdoms: [], wantedCard: "", wantedBox: "", wantedEvent: "", wantedLandmark: ""};
		this.updatePageSize = this.updatePageSize.bind(this);
		this.onNavigate = this.onNavigate.bind(this);
		this.updateMatchingKingdomWantedComponents = this.updateMatchingKingdomWantedComponents.bind(this);
	}

	mergeUnionArrays(keyName) {
	    var index = {}, i, len, merge = [], arr, elem;

        for (var j = 1; j < arguments.length; j++) {
            arr = arguments[j];
            for (i = 0, len = arr.length; i < len; i++) {
                elem = arr[i][keyName];
                if ((typeof elem != "undefined") && !(elem in index)) {
                    index[elem] = true;
                    merge.push(arr[i]);
                }
            }
        }
        return(merge);
	}

	mergeIntersectArrays(keyName, array1, array2) {
	    var index = {}, i, len, merge = [], arr, elem;

        if (array2.length == 0) {
	        return(array1);
	    } else if (array1.length == 0) {
	        return(array2);
	    } else {
	        for (i = 0, len = array1.length; i < len; i++) {
	            elem = array1[i][keyName];
	            if ((typeof elem != "undefined")) {
	                index[elem] = true;
	            }
	        }
	        for (i = 0, len = array2.length; i < len; i++) {
	            elem = array2[i][keyName];
	            if ((typeof elem != "undefined") && (elem in index)) {
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

    loadMatchingKingdomsFromServer(wantedCard, wantedBox, wantedEvent, wantedLandmark) {
        var cardKingdoms = [], boxKingdoms = [], mergedKingdoms1 = [], cardKingdomPromises, boxKingdomPromises,
            eventKingdoms = [], landmarkKingdoms = [], eventKingdomPromises, landmarkKingdomPromises,
            mergedKingdoms2 = [], mergedKingdoms3 = [], promises = [];

        cardKingdomPromises = follow(client, root, [
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
            });

        boxKingdomPromises = follow(client, root, [
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
             });

        eventKingdomPromises = follow(client, root, [
                 {rel: 'kingdoms'}, {rel: 'search'}, {rel: 'findByEvents', params: {event: wantedEvent}}]
             ).then(kingdomCollection => {
                 return kingdomCollection.entity._embedded.kingdoms.map(kingdom =>
                     client({
                         method: 'GET',
                         path: kingdom._links.self.href
                         })
                 );
             }).then(kingdomPromises => {
                 return when.all(kingdomPromises);
             });

        landmarkKingdomPromises = follow(client, root, [
                 {rel: 'kingdoms'}, {rel: 'search'}, {rel: 'findByLandmarks', params: {landmark: wantedLandmark}}]
             ).then(kingdomCollection => {
                 return kingdomCollection.entity._embedded.kingdoms.map(kingdom =>
                     client({
                         method: 'GET',
                         path: kingdom._links.self.href
                         })
                 );
             }).then(kingdomPromises => {
                 return when.all(kingdomPromises);
             });

        cardKingdomPromises.then(kingdoms1 => {
            cardKingdoms = kingdoms1;
            boxKingdomPromises.then(kingdoms2 => {
                boxKingdoms = kingdoms2;
                eventKingdomPromises.then(kingdoms3 => {
                    eventKingdoms = kingdoms3;
                    landmarkKingdomPromises.then(kingdoms4 => {
                        landmarkKingdoms = kingdoms4;
                        mergedKingdoms1 = this.mergeIntersectArrays("url", cardKingdoms, boxKingdoms);
                        mergedKingdoms2 = this.mergeIntersectArrays("url", mergedKingdoms1, eventKingdoms);
                        mergedKingdoms3 = this.mergeIntersectArrays("url", mergedKingdoms2, landmarkKingdoms);

                        this.setState({
                            matchingKingdoms: mergedKingdoms3
                        });
                    }).catch(err => {
                        console.error(err);
                    })
                })
            })
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

	updateMatchingKingdomWantedComponents(wantedCard, wantedBox, wantedEvent, wantedLandmark) {
	    if (wantedCard !== this.state.wantedCard || wantedBox !== this.state.wantedBox
	        || wantedEvent !== this.state.wantedEvent || wantedLandmark !== this.state.wantedLandmark) {
	        this.loadMatchingKingdomsFromServer(wantedCard, wantedBox, wantedEvent, wantedLandmark);
	    }
	}

	componentDidMount() {
		this.loadFromServer(this.state.pageSize);
		this.loadMatchingKingdomsFromServer(this.state.wantedCard, this.state.wantedBox, this.state.wantedEvent, this.state.wantedLandmark);
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
				<MatchingKingdomList kingdoms={this.state.matchingKingdoms}
				                     wantedCard={this.state.wantedCard}
				                     wantedBox={this.state.wantedBox}
				                     wantedEvent={this.state.wantedEvent}
				                     wantedLandmark={this.state.wantedLandmark}
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

class MatchingKingdomList extends React.Component {
    constructor(props) {
        super(props);
		this.handleInput = this.handleInput.bind(this);
    }

    handleInput(e) {
        e.preventDefault();
        var wantedCard = ReactDOM.findDOMNode(this.refs.wantedCard).value;
        var wantedBox = ReactDOM.findDOMNode(this.refs.wantedBox).value;
        var wantedEvent = ReactDOM.findDOMNode(this.refs.wantedEvent).value;
        var wantedLandmark = ReactDOM.findDOMNode(this.refs.wantedLandmark).value;
        if (/^[a-zA-Z '-]+$/.test(wantedCard) || /^[a-zA-Z ]+$/.test(wantedBox) || /^[a-zA-Z ]+$/.test(wantedEvent) || /^[a-zA-Z ]+$/.test(wantedLandmark)) {
            this.props.updateKingdom(wantedCard, wantedBox, wantedEvent, wantedLandmark);
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
                <input ref="wantedEvent" placeholder="Enter Event Here" defaultValue={this.props.wantedEvent} onInput={this.handleInput}/>
                <input ref="wantedLandmark" placeholder="Enter Landmark Here" defaultValue={this.props.wantedLandmark} onInput={this.handleInput}/>
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
