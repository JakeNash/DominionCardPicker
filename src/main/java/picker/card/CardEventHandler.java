package picker.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static picker.WebSocketConfiguration.MESSAGE_PREFIX;

@Component
@RepositoryEventHandler(Card.class)
public class CardEventHandler {

	private final SimpMessagingTemplate websocket;

	private final EntityLinks entityLinks;

	@Autowired
	public CardEventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
		this.websocket = websocket;
		this.entityLinks = entityLinks;
	}

	@HandleAfterCreate
	public void newCard(Card card) {
		websocket.convertAndSend(
				MESSAGE_PREFIX + "/newCard", getPath(card));
	}

	@HandleAfterDelete
	public void deleteCard(Card card) {
		websocket.convertAndSend(
				MESSAGE_PREFIX + "/deleteCard", getPath(card));
	}

	@HandleAfterSave
	public void updateCard(Card card) {
		websocket.convertAndSend(
				MESSAGE_PREFIX + "/updateCard", getPath(card));
	}

	/**
	 * Take an {@link Card} and get the URI using Spring Data REST's {@link EntityLinks}.
	 *
	 * @param card The Card
	 */
	private String getPath(Card card) {
		return entityLinks.linkForSingleResource(card.getClass(),
				card.getId()).toUri().getPath();
	}

}
