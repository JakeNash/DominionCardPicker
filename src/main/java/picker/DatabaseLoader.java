package picker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static picker.BoxName.*;
import static picker.CardName.*;
import static picker.TypeName.*;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final CardRepository cardRepository;
	private final KingdomRepository kingdomRepository;

	// Written card types
    private final List<String> treasureCard = Collections.singletonList(TREASURE.getName());
    private final List<String> curseCard = Collections.singletonList(TypeName.CURSE.getName());
    private final List<String> victoryCard = Collections.singletonList(VICTORY.getName());
    private final List<String> actionCard = Collections.singletonList(ACTION.getName());
    private final List<String> reactionCard = Collections.singletonList(REACTION.getName());
    private final List<String> attackCard = Collections.singletonList(ATTACK.getName());
    private final List<String> durationCard = Collections.singletonList(DURATION.getName());

    // Type list for complex cards
    private List<String> typeList;

	@Autowired
	public DatabaseLoader(CardRepository cardRepository, KingdomRepository kingdomRepository) {
		this.cardRepository = cardRepository;
		this.kingdomRepository = kingdomRepository;
		typeList = new ArrayList<>();
	}

	@Override
	public void run(String... strings) throws Exception {

		cardRepository.deleteAll();
		kingdomRepository.deleteAll();

        saveBasicSupplyCards();
        saveSimpleCards();
        saveComplexCards();

        KingdomSorter kingdomSorter = new KingdomSorter(kingdomRepository, cardRepository);
        kingdomSorter.createKingdoms();
	}

	private void saveCard(String cost, String cardName, String boxName, List<String> types) {
	    cardRepository.save(new Card(cost, cardName, boxName, types));
    }

    private void saveCard(String cost, String cardName, String boxName, List<String> types, List<String> otherSetup) {
	    cardRepository.save(new Card(cost, cardName, boxName, types, otherSetup));
    }

    private void saveBasicSupplyCards() {
        saveCard("0", COPPER.getName(), BASIC.getName(), treasureCard);
        saveCard("0", CardName.CURSE.getName(), BASIC.getName(), curseCard);
        saveCard("2", ESTATE.getName(), BASIC.getName(), victoryCard);
        saveCard("3", SILVER.getName(), BASIC.getName(), treasureCard);
        saveCard("5", DUCHY.getName(), BASIC.getName(), victoryCard);
        saveCard("6", GOLD.getName(), BASIC.getName(), treasureCard);
        saveCard("8", PROVINCE.getName(), BASIC.getName(), victoryCard);
    }

    private void saveSimpleCards() {
        saveSimpleDominionCards();
        saveSimpleOldDominionCards();
        saveSimpleNewDominionCards();

        saveSimpleIntrigueCards();
        saveSimpleOldIntrigueCards();
        saveSimpleNewIntrigueCards();

        saveSimpleSeasideCards();

        saveSimpleAlchemyCards();

        saveSimpleProsperityCards();

        saveSimpleCornucopiaCards();

        saveSimpleHinterlandsCards();

        saveSimpleDarkAgesCards();

        saveSimpleGuildsCards();

        saveSimpleAdventuresCards();

        saveSimpleEmpiresCards();

        saveSimpleNocturneCards();
    }

    private void saveSimpleDominionCards() {
        saveCard("2", CELLAR.getName(), DOMINION.getName(), actionCard);
        saveCard("2", CHAPEL.getName(), DOMINION.getName(), actionCard);
        saveCard("5", COUNCIL_ROOM.getName(), DOMINION.getName(), actionCard);
        saveCard("5", FESTIVAL.getName(), DOMINION.getName(), actionCard);
        saveCard("4", GARDENS.getName(), DOMINION.getName(), victoryCard);
        saveCard("5", LABORATORY.getName(), DOMINION.getName(), actionCard);
        saveCard("5", LIBRARY.getName(), DOMINION.getName(), actionCard);
        saveCard("5", MARKET.getName(), DOMINION.getName(), actionCard);
        saveCard("5", MINE.getName(), DOMINION.getName(), actionCard);
        saveCard("4", MONEYLENDER.getName(), DOMINION.getName(), actionCard);
        saveCard("4", REMODEL.getName(), DOMINION.getName(), actionCard);
        saveCard("4", SMITHY.getName(), DOMINION.getName(), actionCard);
        saveCard("4", THRONE_ROOM.getName(), DOMINION.getName(), actionCard);
        saveCard("3", VILLAGE.getName(), DOMINION.getName(), actionCard);
        saveCard("3", WORKSHOP.getName(), DOMINION.getName(), actionCard);
    }

    private void saveSimpleOldDominionCards() {
        saveCard("3", CHANCELLOR.getName(), OLD_DOMINION.getName(), actionCard);
        saveCard("3", WOODCUTTER.getName(), OLD_DOMINION.getName(), actionCard);
        saveCard("4", FEAST.getName(), OLD_DOMINION.getName(), actionCard);
        saveCard("6", ADVENTURER.getName(), OLD_DOMINION.getName(), actionCard);
    }

    private void saveSimpleNewDominionCards() {
        saveCard("3", HARBINGER.getName(), NEW_DOMINION.getName(), actionCard);
        saveCard("3", MERCHANT.getName(), NEW_DOMINION.getName(), actionCard);
        saveCard("3", VASSAL.getName(), NEW_DOMINION.getName(), actionCard);
        saveCard("4", POACHER.getName(), NEW_DOMINION.getName(), actionCard);
        saveCard("5", SENTRY.getName(), NEW_DOMINION.getName(), actionCard);
        saveCard("6", ARTISAN.getName(), NEW_DOMINION.getName(), actionCard);
    }

    private void saveSimpleIntrigueCards() {
        saveCard("2", COURTYARD.getName(), INTRIGUE.getName(), actionCard);
        saveCard("2", PAWN.getName(), INTRIGUE.getName(), actionCard);
        saveCard("3", MASQUERADE.getName(), INTRIGUE.getName(), actionCard);
        saveCard("3", SHANTY_TOWN.getName(), INTRIGUE.getName(), actionCard);
        saveCard("3", STEWARD.getName(), INTRIGUE.getName(), actionCard);
        saveCard("3", WISHING_WELL.getName(), INTRIGUE.getName(), actionCard);
        saveCard("4", BARON.getName(), INTRIGUE.getName(), actionCard);
        saveCard("4", BRIDGE.getName(), INTRIGUE.getName(), actionCard);
        saveCard("4", CONSPIRATOR.getName(), INTRIGUE.getName(), actionCard);
        saveCard("4", IRONWORKS.getName(), INTRIGUE.getName(), actionCard);
        saveCard("4", MINING_VILLAGE.getName(), INTRIGUE.getName(), actionCard);
        saveCard("5", DUKE.getName(), INTRIGUE.getName(), victoryCard);
        saveCard("5", TRADING_POST.getName(), INTRIGUE.getName(), actionCard);
        saveCard("5", UPGRADE.getName(), INTRIGUE.getName(), actionCard);
    }

    private void saveSimpleOldIntrigueCards() {
        saveCard("4", COPPERSMITH.getName(), OLD_INTRIGUE.getName(), actionCard);
        saveCard("4", SCOUT.getName(), OLD_INTRIGUE.getName(), actionCard);
        saveCard("5", TRIBUTE.getName(), OLD_INTRIGUE.getName(), actionCard);
    }

    private void saveSimpleNewIntrigueCards() {
        saveCard("2", LURKER.getName(), NEW_INTRIGUE.getName(), actionCard);
        saveCard("4", SECRET_PASSAGE.getName(), NEW_INTRIGUE.getName(), actionCard);
        saveCard("5", COURTIER.getName(), NEW_INTRIGUE.getName(), actionCard);
        saveCard("5", PATROL.getName(), NEW_INTRIGUE.getName(), actionCard);
    }

    private void saveSimpleSeasideCards() {
	    List<String> embargoSetup = Collections.singletonList("Embargo tokens");
	    List<String> nativeVillageSetup = Collections.singletonList("Native Village mats");

        saveCard("2", EMBARGO.getName(), SEASIDE.getName(), actionCard, embargoSetup);
        saveCard("2", NATIVE_VILLAGE.getName(), SEASIDE.getName(), actionCard, nativeVillageSetup);
        saveCard("2", PEARL_DIVER.getName(), SEASIDE.getName(), actionCard);
        saveCard("3", LOOKOUT.getName(), SEASIDE.getName(), actionCard);
        saveCard("3", SMUGGLERS.getName(), SEASIDE.getName(), actionCard);
        saveCard("3", WAREHOUSE.getName(), SEASIDE.getName(), actionCard);
        saveCard("4", NAVIGATOR.getName(), SEASIDE.getName(), actionCard);
        saveCard("4", SALVAGER.getName(), SEASIDE.getName(), actionCard);
        saveCard("4", TREASURE_MAP.getName(), SEASIDE.getName(), actionCard);
        saveCard("5", BAZAAR.getName(), SEASIDE.getName(), actionCard);
        saveCard("5", EXPLORER.getName(), SEASIDE.getName(), actionCard);
        saveCard("5", TREASURY.getName(), SEASIDE.getName(), actionCard);
    }

    private void saveSimpleAlchemyCards() {

    }

    private void saveSimpleProsperityCards() {

    }

    private void saveSimpleCornucopiaCards() {

    }

    private void saveSimpleHinterlandsCards() {

    }

    private void saveSimpleDarkAgesCards() {

    }

    private void saveSimpleGuildsCards() {

    }

    private void saveSimpleAdventuresCards() {

    }

    private void saveSimpleEmpiresCards() {

    }

    private void saveSimpleNocturneCards() {

    }

    private void saveComplexCards() {
        saveActionReactionCards();
        saveActionAttackCards();
        saveActionVictoryCards();
        saveTreasureVictoryCards();
        saveActionDurationCards();
    }

    private void saveActionReactionCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(reactionCard);

        saveActionReactionDominionCards();

        saveActionReactionOldIntrigueCards();
        saveActionReactionNewIntrigueCards();
    }

    private void saveActionReactionDominionCards() {
        saveCard("2", MOAT.getName(), DOMINION.getName(), typeList);
    }

    private void saveActionReactionOldIntrigueCards() {
	    saveCard("2", SECRET_CHAMBER.getName(), OLD_INTRIGUE.getName(), typeList);
    }

    private void saveActionReactionNewIntrigueCards() {
	    saveCard("4", DIPLOMAT.getName(), NEW_INTRIGUE.getName(), typeList);
    }

    private void saveActionAttackCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(attackCard);

        saveActionAttackDominionCards();
        saveActionAttackOldDominionCards();
        saveActionAttackNewDominionCards();

        saveActionAttackIntrigueCards();
        saveActionAttackOldIntrigueCards();
        saveActionAttackNewIntrigueCards();

        saveActionAttackSeasideCards();
    }

    private void saveActionAttackDominionCards() {
        saveCard("4", BUREAUCRAT.getName(), DOMINION.getName(), typeList);
        saveCard("4", MILITIA.getName(), DOMINION.getName(), typeList);
        saveCard("5", WITCH.getName(), DOMINION.getName(), typeList);
    }

    private void saveActionAttackOldDominionCards() {
        saveCard("4", SPY.getName(), OLD_DOMINION.getName(), typeList);
        saveCard("4", THIEF.getName(), OLD_DOMINION.getName(), typeList);
    }

    private void saveActionAttackNewDominionCards() {
        saveCard("5", BANDIT.getName(), NEW_DOMINION.getName(), typeList);
    }

    private void saveActionAttackIntrigueCards() {
	    saveCard("3", SWINDLER.getName(), INTRIGUE.getName(), typeList);
	    saveCard("5", MINION.getName(), INTRIGUE.getName(), typeList);
	    saveCard("5", TORTURER.getName(), INTRIGUE.getName(), typeList);
    }

    private void saveActionAttackOldIntrigueCards() {
	    saveCard("5", SABOTEUR.getName(), OLD_INTRIGUE.getName(), typeList);
    }

    private void saveActionAttackNewIntrigueCards() {
	    saveCard("5", REPLACE.getName(), NEW_INTRIGUE.getName(), typeList);
    }

    private void saveActionAttackSeasideCards() {
	    List<String> pirateShipSetup = new ArrayList<>();
	    pirateShipSetup.add("Pirate Ship mats");
	    pirateShipSetup.add("Coin tokens");

	    saveCard("3", AMBASSADOR.getName(), SEASIDE.getName(), typeList);
	    saveCard("4", CUTPURSE.getName(), SEASIDE.getName(), typeList);
	    saveCard("4", PIRATE_SHIP.getName(), SEASIDE.getName(), typeList, pirateShipSetup);
	    saveCard("4", SEA_HAG.getName(), SEASIDE.getName(), typeList);
	    saveCard("5", GHOST_SHIP.getName(), SEASIDE.getName(), typeList);
    }

    private void saveActionVictoryCards() {
	    typeList.clear();
	    typeList.addAll(actionCard);
	    typeList.addAll(victoryCard);

	    saveActionVictoryIntrigueCards();
	    saveActionVictoryOldIntrigueCards();
	    saveActionVictoryNewIntrigueCards();

	    saveActionVictorySeasideCards();
    }

    private void saveActionVictoryIntrigueCards() {
	    saveCard("6", NOBLES.getName(), INTRIGUE.getName(), typeList);
    }

    private void saveActionVictoryOldIntrigueCards() {
	    saveCard("3", GREAT_HALL.getName(), OLD_INTRIGUE.getName(), typeList);
    }

    private void saveActionVictoryNewIntrigueCards() {
	    saveCard("4", MILL.getName(), NEW_INTRIGUE.getName(), typeList);
    }

    private void saveActionVictorySeasideCards() {
	    List<String> islandSetup = Collections.singletonList("Island mats");

	    saveCard("4", ISLAND.getName(), SEASIDE.getName(), typeList, islandSetup);
    }

    private void saveTreasureVictoryCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(victoryCard);

        saveTreasureVictoryIntrigueCards();
    }

    private void saveTreasureVictoryIntrigueCards() {
	    saveCard("6", HAREM.getName(), INTRIGUE.getName(), typeList);
    }

    private void saveActionDurationCards() {
	    typeList.clear();
	    typeList.addAll(actionCard);
	    typeList.addAll(durationCard);

	    saveActionDurationSeasideCards();
    }

    private void saveActionDurationSeasideCards() {
        saveCard("2", HAVEN.getName(), SEASIDE.getName(), typeList);
        saveCard("2", LIGHTHOUSE.getName(), SEASIDE.getName(), typeList);
        saveCard("3", FISHING_VILLAGE.getName(), SEASIDE.getName(), typeList);
        saveCard("4", CARAVAN.getName(), SEASIDE.getName(), typeList);
        saveCard("5", MERCHANT_SHIP.getName(), SEASIDE.getName(), typeList);
        saveCard("5", OUTPOST.getName(), SEASIDE.getName(), typeList);
        saveCard("5", TACTICIAN.getName(), SEASIDE.getName(), typeList);
        saveCard("5", WHARF.getName(), SEASIDE.getName(), typeList);
    }
}