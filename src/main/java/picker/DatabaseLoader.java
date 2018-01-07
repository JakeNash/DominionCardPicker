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
    private final List<String> prizeCard = Collections.singletonList(PRIZE.getName());
    private final List<String> ruinsCard = Collections.singletonList(RUINS.getName());
    private final List<String> looterCard = Collections.singletonList(LOOTER.getName());
    private final List<String> knightCard = Collections.singletonList(KNIGHTS.getName());
    private final List<String> shelterCard = Collections.singletonList(SHELTER.getName());

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
    private List<String> tournamentSetup = Collections.singletonList(TOURNAMENT_SETUP.getText());
    private List<String> youngWitchSetup = Collections.singletonList(YOUNG_WITCH_SETUP.getText());
    private List<String> darkAgesSetup = Collections.singletonList(SHELTERS_SETUP.getText());
    private List<String> hermitSetup = Arrays.asList(HERMIT_SETUP.getText(), SHELTERS_SETUP.getText());
    private List<String> urchinSetup = Arrays.asList(URCHIN_SETUP.getText(), SHELTERS_SETUP.getText());
    private List<String> ruinsAndDarkAgesSetup = Arrays.asList(RUINS_SETUP.getText(), SHELTERS_SETUP.getText());
    private List<String> spoilsAndDarkAgesSetup = Arrays.asList(SPOILS_SETUP.getText(), SHELTERS_SETUP.getText());
    private List<String> knightsSetup = Arrays.asList(KNIGHTS_SETUP.getText(), SHELTERS_SETUP.getText());
    private List<String> marauderSetup = Arrays.asList(SPOILS_SETUP.getText(), RUINS_SETUP.getText(), SHELTERS_SETUP.getText());
    private List<String> coinTokenSetup = Collections.singletonList(COIN_TOKEN_SETUP.getText());
    private List<String> bakerSetup = Arrays.asList(BAKER_SETUP.getText(), COIN_TOKEN_SETUP.getText());

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
        box = CORNUCOPIA.getName();

        saveCard("2", HAMLET.getName(), actionCard);
        saveCard("3", MENAGERIE.getName(), actionCard);
        saveCard("4", FARMING_VILLAGE.getName(), actionCard);
        saveCard("4", REMAKE.getName(), actionCard);
        saveCard("4", TOURNAMENT.getName(), actionCard, tournamentSetup);
        saveCard("5", HARVEST.getName(), actionCard);
        saveCard("5", HORN_OF_PLENTY.getName(), treasureCard);
        saveCard("5", HUNTING_PARTY.getName(), actionCard);
        saveCard("6", FAIRGROUNDS.getName(), victoryCard);
    }

    private void saveSimpleHinterlandsCards() {
        box = HINTERLANDS.getName();

        saveCard("2", CROSSROADS.getName(), actionCard);
        saveCard("2", DUCHESS.getName(), actionCard);
        saveCard("3", DEVELOP.getName(), actionCard);
        saveCard("3", OASIS.getName(), actionCard);
        saveCard("3", SCHEME.getName(), actionCard);
        saveCard("4", JACK_OF_ALL_TRADES.getName(), actionCard);
        saveCard("4", NOMAD_CAMP.getName(), actionCard);
        saveCard("4", SILK_ROAD.getName(), victoryCard);
        saveCard("4", SPICE_MERCHANT.getName(), actionCard);
        saveCard("5", CACHE.getName(), treasureCard);
        saveCard("5", CARTOGRAPHER.getName(), actionCard);
        saveCard("5", EMBASSY.getName(), actionCard);
        saveCard("5", HAGGLER.getName(), actionCard);
        saveCard("5", HIGHWAY.getName(), actionCard);
        saveCard("5", ILL_GOTTEN_GAINS.getName(), treasureCard);
        saveCard("5", INN.getName(), actionCard);
        saveCard("5", MANDARIN.getName(), actionCard);
        saveCard("5", STABLES.getName(), actionCard);
        saveCard("6", BORDER_VILLAGE.getName(), actionCard);
        saveCard("6", FARMLAND.getName(), victoryCard);
    }

    private void saveSimpleDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("1", POOR_HOUSE.getName(), actionCard, darkAgesSetup);
        saveCard("2", SQUIRE.getName(), actionCard, darkAgesSetup);
        saveCard("2", VAGRANT.getName(), actionCard, darkAgesSetup);
        saveCard("3", FORAGER.getName(), actionCard, darkAgesSetup);
        saveCard("3", HERMIT.getName(), actionCard, hermitSetup);
        saveCard("3", SAGE.getName(), actionCard, darkAgesSetup);
        saveCard("3", STOREROOM.getName(), actionCard, darkAgesSetup);
        saveCard("4", ARMORY.getName(), actionCard, darkAgesSetup);
        saveCard("4", FEODUM.getName(), victoryCard, darkAgesSetup);
        saveCard("4", FORTRESS.getName(), actionCard, darkAgesSetup);
        saveCard("4", IRONMONGER.getName(), actionCard, darkAgesSetup);
        saveCard("4", PROCESSION.getName(), actionCard, darkAgesSetup);
        saveCard("4", RATS.getName(), actionCard, darkAgesSetup);
        saveCard("4", SCAVENGER.getName(), actionCard, darkAgesSetup);
        saveCard("4", WANDERING_MINSTREL.getName(), actionCard, darkAgesSetup);
        saveCard("5", BAND_OF_MISFITS.getName(), actionCard, darkAgesSetup);
        saveCard("5", BANDIT_CAMP.getName(), actionCard, spoilsAndDarkAgesSetup);
        saveCard("5", CATACOMBS.getName(), actionCard, darkAgesSetup);
        saveCard("5", COUNT.getName(), actionCard, darkAgesSetup);
        saveCard("5", COUNTERFEIT.getName(), treasureCard, darkAgesSetup);
        saveCard("5", GRAVEROBBER.getName(), actionCard, darkAgesSetup);
        saveCard("5", JUNK_DEALER.getName(), actionCard, darkAgesSetup);
        saveCard("5", MYSTIC.getName(), actionCard, darkAgesSetup);
        saveCard("5", REBUILD.getName(), actionCard, darkAgesSetup);
        saveCard("6", ALTAR.getName(), actionCard, darkAgesSetup);
        saveCard("6", HUNTING_GROUNDS.getName(), actionCard, darkAgesSetup);
        saveCard("0*", MADMAN.getName(), actionCard, darkAgesSetup);
        saveCard("0*", SPOILS.getName(), treasureCard, darkAgesSetup);
    }

    private void saveSimpleGuildsCards() {
        box = GUILDS.getName();

        saveCard("2", CANDLESTICK_MAKER.getName(), actionCard, coinTokenSetup);
        saveCard("2+", STONEMASON.getName(), actionCard);
        saveCard("3+", DOCTOR.getName(), actionCard);
        saveCard("3+", MASTERPIECE.getName(), treasureCard);
        saveCard("4", ADVISOR.getName(), actionCard);
        saveCard("4", PLAZA.getName(), actionCard, coinTokenSetup);
        saveCard("4+", HERALD.getName(), actionCard);
        saveCard("5", BAKER.getName(), actionCard, bakerSetup);
        saveCard("5", BUTCHER.getName(), actionCard, coinTokenSetup);
        saveCard("5", JOURNEYMAN.getName(), actionCard);
        saveCard("5", MERCHANT_GUILD.getName(), actionCard, coinTokenSetup);
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
        saveActionPrizeCards();
        saveTreasurePrizeCards();
        saveActionAttackPrizeCards();
        saveTreasureReactionCards();
        saveVictoryReactionCards();
        saveActionRuinsCards();
        saveActionLooterCards();
        saveActionAttackLooterCards();
        saveActionAttackKnightCards();
        saveActionAttackKnightVictoryCards();
        saveReactionShelterCards();
        saveActionShelterCards();
        saveVictoryShelterCards();
    }

    private void saveActionReactionCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(reactionCard);

        saveActionReactionDominionCards();

        saveActionReactionOldIntrigueCards();
        saveActionReactionNewIntrigueCards();

        saveActionReactionProsperityCards();

        saveActionReactionCornucopiaCards();

        saveActionReactionHinterlandsCards();

        saveActionReactionDarkAgesCards();
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

    private void saveActionReactionCornucopiaCards() {
        box = CORNUCOPIA.getName();

        saveCard("4", HORSE_TRADERS.getName(), typeList);
    }

    private void saveActionReactionHinterlandsCards() {
        box = HINTERLANDS.getName();

        saveCard("4", TRADER.getName(), typeList);
    }

    private void saveActionReactionDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("2", BEGGAR.getName(), typeList, darkAgesSetup);
        saveCard("3", MARKET_SQUARE.getName(), typeList, darkAgesSetup);
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

        saveActionAttackCornucopiaCards();

        saveActionAttackHinterlandsCards();

        saveActionAttackDarkAgesCards();

        saveActionAttackGuildsCards();
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

    private void saveActionAttackCornucopiaCards() {
        box = CORNUCOPIA.getName();

        saveCard("3", FORTUNE_TELLER.getName(), typeList);
        saveCard("4", YOUNG_WITCH.getName(), typeList, youngWitchSetup);
        saveCard("5", JESTER.getName(), typeList);
    }

    private void saveActionAttackHinterlandsCards() {
        box = HINTERLANDS.getName();

        saveCard("3", ORACLE.getName(), typeList);
        saveCard("4", NOBLE_BRIGAND.getName(), typeList);
        saveCard("5", MARGRAVE.getName(), typeList);
    }

    private void saveActionAttackDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("3", URCHIN.getName(), typeList, urchinSetup);
        saveCard("5", PILLAGE.getName(), typeList, spoilsAndDarkAgesSetup);
        saveCard("5", ROGUE.getName(), typeList, darkAgesSetup);
        saveCard("0*", MERCENARY.getName(), typeList, darkAgesSetup);
    }

    private void saveActionAttackGuildsCards() {
        box = GUILDS.getName();

        saveCard("4", TAXMAN.getName(), typeList);
        saveCard("5", SOOTHSAYER.getName(), typeList);
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

    private void saveActionPrizeCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(prizeCard);

        saveActionPrizeCornucopiaCards();
    }

    private void saveActionPrizeCornucopiaCards() {
        box = CORNUCOPIA.getName();

        saveCard("0*", BAG_OF_GOLD.getName(), typeList);
        saveCard("0*", PRINCESS.getName(), typeList);
        saveCard("0*", TRUSTY_STEED.getName(), typeList);
    }

    private void saveTreasurePrizeCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(prizeCard);

        saveTreasurePrizeCornucopiaCards();
    }

    private void saveTreasurePrizeCornucopiaCards() {
        box = CORNUCOPIA.getName();

        saveCard("0*", DIADEM.getName(), typeList);
    }

    private void saveActionAttackPrizeCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(attackCard);
        typeList.addAll(prizeCard);

        saveActionAttackPrizeCornucopiaCards();
    }

    private void saveActionAttackPrizeCornucopiaCards() {
        box = CORNUCOPIA.getName();

        saveCard("0*", FOLLOWERS.getName(), typeList);
    }

    private void saveTreasureReactionCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(reactionCard);

        saveTreasureReactionHinterlandsCards();
    }

    private void saveTreasureReactionHinterlandsCards() {
        box = HINTERLANDS.getName();

        saveCard("2", FOOLS_GOLD.getName(), typeList);
    }

    private void saveVictoryReactionCards() {
        typeList.clear();
        typeList.addAll(victoryCard);
        typeList.addAll(reactionCard);

        saveVictoryReactionHinterlandsCards();
    }

    private void saveVictoryReactionHinterlandsCards() {
        box = HINTERLANDS.getName();

        saveCard("3", TUNNEL.getName(), typeList);
    }

    private void saveActionRuinsCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(ruinsCard);

        saveActionRuinsDarkAgesCards();
    }

    private void saveActionRuinsDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("0", ABANDONED_MINE.getName(), typeList, darkAgesSetup);
        saveCard("0", RUINED_LIBRARY.getName(), typeList, darkAgesSetup);
        saveCard("0", RUINED_MARKET.getName(), typeList, darkAgesSetup);
        saveCard("0", RUINED_VILLAGE.getName(), typeList, darkAgesSetup);
        saveCard("0", SURVIVORS.getName(), typeList, darkAgesSetup);
    }

    private void saveActionLooterCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(looterCard);

        saveActionLooterDarkAgesCards();
    }

    private void saveActionLooterDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("4", DEATH_CART.getName(), typeList, ruinsAndDarkAgesSetup);
    }

    private void saveActionAttackLooterCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(attackCard);
        typeList.addAll(looterCard);

        saveActionAttackLooterDarAgesCards();
    }

    private void saveActionAttackLooterDarAgesCards() {
        box = DARK_AGES.getName();

        saveCard("4", MARAUDER.getName(), typeList, marauderSetup);
        saveCard("5", CULTIST.getName(), typeList, ruinsAndDarkAgesSetup);
    }

    private void saveActionAttackKnightCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(attackCard);
        typeList.addAll(knightCard);

        saveActionAttackKnightDarkAgesCards();
    }

    private void saveActionAttackKnightDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("5", KNIGHTS.getName(), typeList, knightsSetup);
        saveCard("5", DAME_ANNA.getName(), typeList, knightsSetup);
        saveCard("5", DAME_MOLLY.getName(), typeList, knightsSetup);
        saveCard("5", DAME_NATALIE.getName(), typeList, knightsSetup);
        saveCard("5", DAME_SYLVIA.getName(), typeList, knightsSetup);
        saveCard("5", SIR_BAILEY.getName(), typeList, knightsSetup);
        saveCard("5", SIR_DESTRY.getName(), typeList, knightsSetup);
        saveCard("4", SIR_MARTIN.getName(), typeList, knightsSetup);
        saveCard("5", SIR_MICHAEL.getName(), typeList, knightsSetup);
        saveCard("5", SIR_VANDER.getName(), typeList, knightsSetup);
    }

    private void saveActionAttackKnightVictoryCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(attackCard);
        typeList.addAll(knightCard);
        typeList.addAll(victoryCard);

        saveActionAttackKnightVictoryDarkAgesCards();
    }

    private void saveActionAttackKnightVictoryDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("5", DAME_JOSEPHINE.getName(), typeList, knightsSetup);
    }

    private void saveReactionShelterCards() {
        typeList.clear();
        typeList.addAll(reactionCard);
        typeList.addAll(shelterCard);

        saveReactionSHelterDarkAgesCards();
    }

    private void saveReactionSHelterDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("1", HOVEL.getName(), typeList);
    }

    private void saveActionShelterCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(shelterCard);

        saveActionShelterDarkAgesCards();
    }

    private void saveActionShelterDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("1", NECROPOLIS.getName(), typeList);
    }

    private void saveVictoryShelterCards() {
        typeList.clear();
        typeList.addAll(victoryCard);
        typeList.addAll(shelterCard);

        saveVictoryShelterDarkAgesCards();
    }

    private void saveVictoryShelterDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("1", OVERGROWN_ESTATE.getName(), typeList);
    }
}