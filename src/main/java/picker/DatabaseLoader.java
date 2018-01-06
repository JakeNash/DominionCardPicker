package picker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static picker.BoxName.*;
import static picker.CardName.*;
import static picker.SetupText.*;
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

    private String box;
    
    // Type list for complex cards
    private List<String> typeList;

    // Setup lists
    private List<String> embargoSetup = Collections.singletonList(EMBARGO_SETUP.getText());
    private List<String> nativeVillageSetup = Collections.singletonList(NATIVE_VILLAGE_SETUP.getText());
    private List<String> pirateShipSetup = Arrays.asList(PIRATE_SHIP_SETUP.getText(), COIN_TOKEN_SETUP.getText());
    private List<String> islandSetup = Collections.singletonList(ISLAND_SETUP.getText());
    private List<String> potionSetup = Collections.singletonList(POTION_SETUP.getText());
    private List<String> prosperitySetup = Arrays.asList(PLATINUM_SETUP.getText(), COLONY_SETUP.getText());
    private List<String> tradeRouteSetup = Arrays.asList(TRADE_ROUTE_SETUP.getText(), COIN_TOKEN_SETUP.getText(), PLATINUM_SETUP.getText(), COLONY_SETUP.getText());
    private List<String> victoryTokenSetup = Collections.singletonList(VICTORY_TOKEN_SETUP.getText());
    private List<String> victoryAndProsperitySetup = Arrays.asList(VICTORY_TOKEN_SETUP.getText(), PLATINUM_SETUP.getText(), COLONY_SETUP.getText());

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

	private void saveCard(String cost, String cardName, List<String> types) {
	    cardRepository.save(new Card(cost, cardName, box, types));
    }

    private void saveCard(String cost, String cardName, List<String> types, List<String> otherSetup) {
	    cardRepository.save(new Card(cost, cardName, box, types, otherSetup));
    }

    private void saveBasicSupplyCards() {
	    box = BASIC.getName();
	    
        saveCard("0", COPPER.getName(), treasureCard);
        saveCard("0", CardName.CURSE.getName(), curseCard);
        saveCard("2", ESTATE.getName(), victoryCard);
        saveCard("3", SILVER.getName(), treasureCard);
        saveCard("5", DUCHY.getName(), victoryCard);
        saveCard("6", GOLD.getName(), treasureCard);
        saveCard("8", PROVINCE.getName(), victoryCard);
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
	    box = DOMINION.getName();
	    
        saveCard("2", CELLAR.getName(), actionCard);
        saveCard("2", CHAPEL.getName(), actionCard);
        saveCard("5", COUNCIL_ROOM.getName(), actionCard);
        saveCard("5", FESTIVAL.getName(), actionCard);
        saveCard("4", GARDENS.getName(), victoryCard);
        saveCard("5", LABORATORY.getName(), actionCard);
        saveCard("5", LIBRARY.getName(), actionCard);
        saveCard("5", MARKET.getName(), actionCard);
        saveCard("5", MINE.getName(), actionCard);
        saveCard("4", MONEYLENDER.getName(), actionCard);
        saveCard("4", REMODEL.getName(), actionCard);
        saveCard("4", SMITHY.getName(), actionCard);
        saveCard("4", THRONE_ROOM.getName(), actionCard);
        saveCard("3", VILLAGE.getName(), actionCard);
        saveCard("3", WORKSHOP.getName(), actionCard);
    }

    private void saveSimpleOldDominionCards() {
	    box = OLD_DOMINION.getName();
	    
        saveCard("3", CHANCELLOR.getName(), actionCard);
        saveCard("3", WOODCUTTER.getName(), actionCard);
        saveCard("4", FEAST.getName(), actionCard);
        saveCard("6", ADVENTURER.getName(), actionCard);
    }

    private void saveSimpleNewDominionCards() {
	    box = NEW_DOMINION.getName();
	    
        saveCard("3", HARBINGER.getName(), actionCard);
        saveCard("3", MERCHANT.getName(), actionCard);
        saveCard("3", VASSAL.getName(), actionCard);
        saveCard("4", POACHER.getName(), actionCard);
        saveCard("5", SENTRY.getName(), actionCard);
        saveCard("6", ARTISAN.getName(), actionCard);
    }

    private void saveSimpleIntrigueCards() {
	    box = INTRIGUE.getName();
	    
        saveCard("2", COURTYARD.getName(), actionCard);
        saveCard("2", PAWN.getName(), actionCard);
        saveCard("3", MASQUERADE.getName(), actionCard);
        saveCard("3", SHANTY_TOWN.getName(), actionCard);
        saveCard("3", STEWARD.getName(), actionCard);
        saveCard("3", WISHING_WELL.getName(), actionCard);
        saveCard("4", BARON.getName(), actionCard);
        saveCard("4", BRIDGE.getName(), actionCard);
        saveCard("4", CONSPIRATOR.getName(), actionCard);
        saveCard("4", IRONWORKS.getName(), actionCard);
        saveCard("4", MINING_VILLAGE.getName(), actionCard);
        saveCard("5", DUKE.getName(), victoryCard);
        saveCard("5", TRADING_POST.getName(), actionCard);
        saveCard("5", UPGRADE.getName(), actionCard);
    }

    private void saveSimpleOldIntrigueCards() {
	    box = OLD_INTRIGUE.getName();
	    
        saveCard("4", COPPERSMITH.getName(), actionCard);
        saveCard("4", SCOUT.getName(), actionCard);
        saveCard("5", TRIBUTE.getName(), actionCard);
    }

    private void saveSimpleNewIntrigueCards() {
	    box = NEW_INTRIGUE.getName();
	    
        saveCard("2", LURKER.getName(), actionCard);
        saveCard("4", SECRET_PASSAGE.getName(), actionCard);
        saveCard("5", COURTIER.getName(), actionCard);
        saveCard("5", PATROL.getName(), actionCard);
    }

    private void saveSimpleSeasideCards() {
	    box = SEASIDE.getName();

        saveCard("2", EMBARGO.getName(), actionCard, embargoSetup);
        saveCard("2", NATIVE_VILLAGE.getName(), actionCard, nativeVillageSetup);
        saveCard("2", PEARL_DIVER.getName(), actionCard);
        saveCard("3", LOOKOUT.getName(), actionCard);
        saveCard("3", SMUGGLERS.getName(), actionCard);
        saveCard("3", WAREHOUSE.getName(), actionCard);
        saveCard("4", NAVIGATOR.getName(), actionCard);
        saveCard("4", SALVAGER.getName(), actionCard);
        saveCard("4", TREASURE_MAP.getName(), actionCard);
        saveCard("5", BAZAAR.getName(), actionCard);
        saveCard("5", EXPLORER.getName(), actionCard);
        saveCard("5", TREASURY.getName(), actionCard);
    }

    private void saveSimpleAlchemyCards() {
	    box = ALCHEMY.getName();

	    saveCard("4", POTION.getName(), treasureCard);
	    saveCard("Potion", TRANSMUTE.getName(), actionCard, potionSetup);
	    saveCard("Potion", VINEYARD.getName(), victoryCard, potionSetup);
	    saveCard("2", HERBALIST.getName(), actionCard);
	    saveCard("2 & Potion", APOTHECARY.getName(), actionCard, potionSetup);
	    saveCard("2 & Potion", UNIVERSITY.getName(), actionCard, potionSetup);
	    saveCard("3 & Potion", ALCHEMIST.getName(), actionCard, potionSetup);
	    saveCard("3 & Potion", PHILOSOPHERS_STONE.getName(), treasureCard, potionSetup);
	    saveCard("4 & Potion", GOLEM.getName(), actionCard, potionSetup);
	    saveCard("5", APPRENTICE.getName(), actionCard);
	    saveCard("6 & Potion", POSSESSION.getName(), actionCard, potionSetup);
    }

    private void saveSimpleProsperityCards() {
	    box = PROSPERITY.getName();

        saveCard("9", PLATINUM.getName(), treasureCard);
        saveCard("11", COLONY.getName(), victoryCard);
        saveCard("3", LOAN.getName(), treasureCard, prosperitySetup);
        saveCard("3", TRADE_ROUTE.getName(), actionCard, tradeRouteSetup);
        saveCard("3", BISHOP.getName(), actionCard, victoryAndProsperitySetup);
        saveCard("4", MONUMENT.getName(), actionCard, victoryAndProsperitySetup);
        saveCard("4", QUARRY.getName(), treasureCard, prosperitySetup);
        saveCard("4", TALISMAN.getName(), treasureCard, prosperitySetup);
        saveCard("4", WORKERS_VILLAGE.getName(), actionCard, prosperitySetup);
        saveCard("5", CITY.getName(), actionCard, prosperitySetup);
        saveCard("5", CONTRABAND.getName(), treasureCard, prosperitySetup);
        saveCard("5", COUNTING_HOUSE.getName(), actionCard, prosperitySetup);
        saveCard("5", MINT.getName(), actionCard, prosperitySetup);
        saveCard("5", ROYAL_SEAL.getName(), treasureCard, prosperitySetup);
        saveCard("5", VAULT.getName(), actionCard, prosperitySetup);
        saveCard("5", VENTURE.getName(), treasureCard, prosperitySetup);
        saveCard("6*", GRAND_MARKET.getName(), actionCard, prosperitySetup);
        saveCard("6", HOARD.getName(), treasureCard, prosperitySetup);
        saveCard("7", BANK.getName(), treasureCard, prosperitySetup);
        saveCard("7", EXPAND.getName(), actionCard, prosperitySetup);
        saveCard("7", FORGE.getName(), actionCard, prosperitySetup);
        saveCard("7", KINGS_COURT.getName(), actionCard, prosperitySetup);
        saveCard("8*", PEDDLER.getName(), actionCard, prosperitySetup);
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

        saveActionReactionProsperityCards();
    }

    private void saveActionReactionDominionCards() {
	    box = DOMINION.getName();

        saveCard("2", MOAT.getName(), typeList);
    }

    private void saveActionReactionOldIntrigueCards() {
	    box = OLD_INTRIGUE.getName();

	    saveCard("2", SECRET_CHAMBER.getName(), typeList);
    }

    private void saveActionReactionNewIntrigueCards() {
	    box = NEW_INTRIGUE.getName();

	    saveCard("4", DIPLOMAT.getName(), typeList);
    }

    private void saveActionReactionProsperityCards() {
	    box = PROSPERITY.getName();

	    saveCard("3", WATCHTOWER.getName(), typeList, prosperitySetup);
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

        saveActionAttackAlchemyCards();

        saveActionAttackProsperityCards();
    }

    private void saveActionAttackDominionCards() {
	    box = DOMINION.getName();

        saveCard("4", BUREAUCRAT.getName(), typeList);
        saveCard("4", MILITIA.getName(), typeList);
        saveCard("5", WITCH.getName(), typeList);
    }

    private void saveActionAttackOldDominionCards() {
	    box = OLD_DOMINION.getName();

        saveCard("4", SPY.getName(), typeList);
        saveCard("4", THIEF.getName(), typeList);
    }

    private void saveActionAttackNewDominionCards() {
	    box = NEW_DOMINION.getName();

        saveCard("5", BANDIT.getName(), typeList);
    }

    private void saveActionAttackIntrigueCards() {
	    box = INTRIGUE.getName();

	    saveCard("3", SWINDLER.getName(), typeList);
	    saveCard("5", MINION.getName(), typeList);
	    saveCard("5", TORTURER.getName(), typeList);
    }

    private void saveActionAttackOldIntrigueCards() {
	    box = OLD_INTRIGUE.getName();

	    saveCard("5", SABOTEUR.getName(), typeList);
    }

    private void saveActionAttackNewIntrigueCards() {
	    box = NEW_INTRIGUE.getName();

	    saveCard("5", REPLACE.getName(), typeList);
    }

    private void saveActionAttackSeasideCards() {
	    box = SEASIDE.getName();

	    saveCard("3", AMBASSADOR.getName(), typeList);
	    saveCard("4", CUTPURSE.getName(), typeList);
	    saveCard("4", PIRATE_SHIP.getName(), typeList, pirateShipSetup);
	    saveCard("4", SEA_HAG.getName(), typeList);
	    saveCard("5", GHOST_SHIP.getName(), typeList);
    }

    private void saveActionAttackAlchemyCards() {
	    box = ALCHEMY.getName();

	    saveCard("2 & Potion", SCRYING_POOL.getName(), typeList, potionSetup);
	    saveCard("3 & Potion", FAMILIAR.getName(), typeList, potionSetup);
    }

    private void saveActionAttackProsperityCards() {
	    box = PROSPERITY.getName();

	    saveCard("5", MOUNTEBANK.getName(), typeList, prosperitySetup);
	    saveCard("5", RABBLE.getName(), typeList, prosperitySetup);
	    saveCard("6", GOONS.getName(), typeList, victoryAndProsperitySetup);
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
	    box = INTRIGUE.getName();

	    saveCard("6", NOBLES.getName(), typeList);
    }

    private void saveActionVictoryOldIntrigueCards() {
	    box = OLD_INTRIGUE.getName();

	    saveCard("3", GREAT_HALL.getName(), typeList);
    }

    private void saveActionVictoryNewIntrigueCards() {
	    box = NEW_INTRIGUE.getName();

	    saveCard("4", MILL.getName(), typeList);
    }

    private void saveActionVictorySeasideCards() {
	    box = SEASIDE.getName();

	    saveCard("4", ISLAND.getName(), typeList, islandSetup);
    }

    private void saveTreasureVictoryCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(victoryCard);

        saveTreasureVictoryIntrigueCards();
    }

    private void saveTreasureVictoryIntrigueCards() {
	    box = INTRIGUE.getName();

	    saveCard("6", HAREM.getName(), typeList);
    }

    private void saveActionDurationCards() {
	    typeList.clear();
	    typeList.addAll(actionCard);
	    typeList.addAll(durationCard);

	    saveActionDurationSeasideCards();
    }

    private void saveActionDurationSeasideCards() {
	    box = SEASIDE.getName();

        saveCard("2", HAVEN.getName(), typeList);
        saveCard("2", LIGHTHOUSE.getName(), typeList);
        saveCard("3", FISHING_VILLAGE.getName(), typeList);
        saveCard("4", CARAVAN.getName(), typeList);
        saveCard("5", MERCHANT_SHIP.getName(), typeList);
        saveCard("5", OUTPOST.getName(), typeList);
        saveCard("5", TACTICIAN.getName(), typeList);
        saveCard("5", WHARF.getName(), typeList);
    }
}