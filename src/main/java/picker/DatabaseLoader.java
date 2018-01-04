package picker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final CardRepository cardRepository;
	private final KingdomRepository kingdomRepository;

	// Written card types
    private final List<String> treasureCard = Collections.singletonList(TypeName.TREASURE.getName());
    private final List<String> curseCard = Collections.singletonList(TypeName.CURSE.getName());
    private final List<String> victoryCard = Collections.singletonList(TypeName.VICTORY.getName());
    private final List<String> actionCard = Collections.singletonList(TypeName.ACTION.getName());
    private final List<String> reactionCard = Collections.singletonList(TypeName.REACTION.getName());
    private final List<String> attackCard = Collections.singletonList(TypeName.ATTACK.getName());

	@Autowired
	public DatabaseLoader(CardRepository cardRepository, KingdomRepository kingdomRepository) {
		this.cardRepository = cardRepository;
		this.kingdomRepository = kingdomRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		cardRepository.deleteAll();
		kingdomRepository.deleteAll();

        KingdomSorter kingdomSorter = new KingdomSorter(kingdomRepository);
        kingdomSorter.createKingdoms();

		saveBasicSupplyCards();
        saveSimpleCards();
		saveComplexCards();
	}

    private void saveBasicSupplyCards() {
        String cardName;

        cardName = CardName.COPPER.getName();
        cardRepository.save(new Card("0", cardName, BoxName.BASIC.getName(), treasureCard, extractKingdomNames(cardName)));

        cardName = CardName.CURSE.getName();
        cardRepository.save(new Card("0", cardName, BoxName.BASIC.getName(), curseCard, extractKingdomNames(cardName)));

        cardName = CardName.ESTATE.getName();
        cardRepository.save(new Card("2", cardName, BoxName.BASIC.getName(), victoryCard, extractKingdomNames(cardName)));

        cardName = CardName.SILVER.getName();
        cardRepository.save(new Card("3", cardName, BoxName.BASIC.getName(), treasureCard, extractKingdomNames(cardName)));

        cardName = CardName.DUCHY.getName();
        cardRepository.save(new Card("5", cardName, BoxName.BASIC.getName(), victoryCard, extractKingdomNames(cardName)));

        cardName = CardName.GOLD.getName();
        cardRepository.save(new Card("6", cardName, BoxName.BASIC.getName(), treasureCard, extractKingdomNames(cardName)));

        cardName = CardName.PROVINCE.getName();
        cardRepository.save(new Card("8", cardName, BoxName.BASIC.getName(), victoryCard, extractKingdomNames(cardName)));
    }

    private void saveSimpleCards() {
        saveSimpleDominionCards();
        saveSimpleOldDominionCards();
        saveSimpleNewDominionCards();
    }

    private List<String> extractKingdomNames(String card) {
	    List<Kingdom> kingdomList = kingdomRepository.findByCards(card);
	    List<String> kingdomNameList = new ArrayList<>();

        for(Kingdom kingdom : kingdomList) {
            kingdomNameList.add(kingdom.getName());
        }

        return kingdomNameList;
    }

    private void saveSimpleDominionCards() {
        String cardName;

        cardName = CardName.CELLAR.getName();
        cardRepository.save(new Card("2", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.CHAPEL.getName();
        cardRepository.save(new Card("2", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.COUNCIL_ROOM.getName();
        cardRepository.save(new Card("5", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.FESTIVAL.getName();
        cardRepository.save(new Card("5", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.GARDENS.getName();
        cardRepository.save(new Card("4", cardName, BoxName.DOMINION.getName(), victoryCard, extractKingdomNames(cardName)));

        cardName = CardName.LABORATORY.getName();
        cardRepository.save(new Card("5", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.LIBRARY.getName();
        cardRepository.save(new Card("5", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.MARKET.getName();
        cardRepository.save(new Card("5", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.MINE.getName();
        cardRepository.save(new Card("5", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.MONEYLENDER.getName();
        cardRepository.save(new Card("4", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.REMODEL.getName();
        cardRepository.save(new Card("4", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.SMITHY.getName();
        cardRepository.save(new Card("4", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.THRONE_ROOM.getName();
        cardRepository.save(new Card("4", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.VILLAGE.getName();
        cardRepository.save(new Card("3", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.WORKSHOP.getName();
        cardRepository.save(new Card("3", cardName, BoxName.DOMINION.getName(), actionCard, extractKingdomNames(cardName)));
    }

    private void saveSimpleOldDominionCards() {
        String cardName;

        cardName = CardName.CHANCELLOR.getName();
        cardRepository.save(new Card("3", cardName, BoxName.OLD_DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.WOODCUTTER.getName();
        cardRepository.save(new Card("3", cardName, BoxName.OLD_DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.FEAST.getName();
        cardRepository.save(new Card("4", cardName, BoxName.OLD_DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.ADVENTURER.getName();
        cardRepository.save(new Card("6", cardName, BoxName.OLD_DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

    }

    private void saveSimpleNewDominionCards() {
        String cardName;

        cardName = CardName.HARBINGER.getName();
        cardRepository.save(new Card("3", cardName, BoxName.NEW_DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.MERCHANT.getName();
        cardRepository.save(new Card("3", cardName, BoxName.NEW_DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.VASSAL.getName();
        cardRepository.save(new Card("3", cardName, BoxName.NEW_DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.POACHER.getName();
        cardRepository.save(new Card("4", cardName, BoxName.NEW_DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.SENTRY.getName();
        cardRepository.save(new Card("5", cardName, BoxName.NEW_DOMINION.getName(), actionCard, extractKingdomNames(cardName)));

        cardName = CardName.ARTISAN.getName();
        cardRepository.save(new Card("6", cardName, BoxName.NEW_DOMINION.getName(), actionCard, extractKingdomNames(cardName)));
    }

    private void saveComplexCards() {
        List<String> typeList = new ArrayList<>();

        saveActionReactionCards(typeList);
        saveActionAttackCards(typeList);
    }

    private void saveActionReactionCards(List<String> typeList) {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(reactionCard);

        saveActionReactionDominionCards(typeList);
    }

    private void saveActionReactionDominionCards(List<String> typeList) {
        String cardName;

        cardName = CardName.MOAT.getName();
        cardRepository.save(new Card("2", cardName, BoxName.DOMINION.getName(), typeList, extractKingdomNames(cardName)));
    }

    private void saveActionAttackCards(List<String> typeList) {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(attackCard);

        saveActionAttackDominionCards(typeList);
        saveActionAttackOldDominionCards(typeList);
        saveActionAttackNewDominionCards(typeList);
    }

    private void saveActionAttackDominionCards(List<String> typeList) {
        String cardName;

        cardName = CardName.BUREAUCRAT.getName();
        cardRepository.save(new Card("4", cardName, BoxName.DOMINION.getName(), typeList, extractKingdomNames(cardName)));

        cardName = CardName.MILITIA.getName();
        cardRepository.save(new Card("4", cardName, BoxName.DOMINION.getName(), typeList, extractKingdomNames(cardName)));

        cardName = CardName.WITCH.getName();
        cardRepository.save(new Card("5", cardName, BoxName.DOMINION.getName(), typeList, extractKingdomNames(cardName)));
    }

    private void saveActionAttackOldDominionCards(List<String> typeList) {
        String cardName;

        cardName = CardName.SPY.getName();
        cardRepository.save(new Card("4", cardName, BoxName.OLD_DOMINION.getName(), typeList, extractKingdomNames(cardName)));

        cardName = CardName.THIEF.getName();
        cardRepository.save(new Card("4", cardName, BoxName.OLD_DOMINION.getName(), typeList, extractKingdomNames(cardName)));
    }

    private void saveActionAttackNewDominionCards(List<String> typeList) {
        String cardName;

        cardName = CardName.BANDIT.getName();
        cardRepository.save(new Card("5", cardName, BoxName.NEW_DOMINION.getName(), typeList, extractKingdomNames(cardName)));
    }
}