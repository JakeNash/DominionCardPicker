package picker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import picker.boon.Boon;
import picker.boon.BoonRepository;
import picker.card.Card;
import picker.card.CardName;
import picker.card.CardRepository;
import picker.card.TypeName;
import picker.event.Event;
import picker.event.EventRepository;
import picker.hex.Hex;
import picker.hex.HexRepository;
import picker.kingdom.KingdomRepository;
import picker.kingdom.KingdomSorter;
import picker.landmark.Landmark;
import picker.landmark.LandmarkRepository;
import picker.state.State;
import picker.state.StateRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static picker.BoxName.*;
import static picker.boon.BoonName.*;
import static picker.card.CardName.*;
import static picker.card.SetupText.*;
import static picker.card.TypeName.*;
import static picker.event.EventName.*;
import static picker.hex.HexName.*;
import static picker.landmark.LandmarkName.*;
import static picker.state.StateName.*;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final CardRepository cardRepository;
	private final EventRepository eventRepository;
	private final LandmarkRepository landmarkRepository;
	private final BoonRepository boonRepository;
	private final HexRepository hexRepository;
	private final StateRepository stateRepository;
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
    private final List<String> reserveCard = Collections.singletonList(RESERVE.getName());
    private final List<String> travellerCard = Collections.singletonList(TRAVELLER.getName());
    private final List<String> castleCard = Collections.singletonList(CASTLE.getName());
    private final List<String> gatheringCard = Collections.singletonList(GATHERING.getName());
    private final List<String> fateCard = Collections.singletonList(FATE.getName());
    private final List<String> nightCard = Collections.singletonList(NIGHT.getName());
    private final List<String> doomCard = Collections.singletonList(DOOM.getName());
    private final List<String> heirloomCard = Collections.singletonList(HEIRLOOM.getName());
    private final List<String> spiritCard = Collections.singletonList(SPIRIT.getName());
    private final List<String> zombieCard = Collections.singletonList(ZOMBIE.getName());

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
    private List<String> reserveSetup = Collections.singletonList(RESERVE_SETUP.getText());
    private List<String> pageSetup = Collections.singletonList(PAGE_SETUP.getText());
    private List<String> peasantSetup = Arrays.asList(PEASANT_SETUP.getText(), RESERVE_SETUP.getText(), PLUS_CARD_SETUP.getText(), PLUS_ACTION_SETUP.getText(), PLUS_BUY_SETUP.getText(), PLUS_COIN_SETUP.getText());
    private List<String> journeySetup = Collections.singletonList(JOURNEY_TOKEN_SETUP.getText());
    private List<String> minusCoinSetup = Collections.singletonList(MINUS_COIN_SETUP.getText());
    private List<String> minusCardSetup = Collections.singletonList(MINUS_CARD_SETUP.getText());
    private List<String> minusCostSetup = Collections.singletonList(MINUS_COST_SETUP.getText());
    private List<String> trashTokenSetup = Collections.singletonList(TRASH_TOKEN_SETUP.getText());
    private List<String> plusBuySetup = Collections.singletonList(PLUS_BUY_SETUP.getText());
    private List<String> plusActionSetup = Collections.singletonList(PLUS_ACTION_SETUP.getText());
    private List<String> plusCoinSetup = Collections.singletonList(PLUS_COIN_SETUP.getText());
    private List<String> estateTokenSetup = Collections.singletonList(ESTATE_TOKEN_SETUP.getText());
    private List<String> plusCardSetup = Collections.singletonList(PLUS_CARD_SETUP.getText());
    private List<String> debtTokenSetup = Collections.singletonList(DEBT_TOKEN_SETUP.getText());
    private List<String> encampmentSetup = Arrays.asList(ENCAMPMENT_SETUP.getText(), VICTORY_TOKEN_SETUP.getText());
    private List<String> patricianSetup = Arrays.asList(PATRICIAN_SETUP.getText(), VICTORY_TOKEN_SETUP.getText());
    private List<String> settlersSetup = Collections.singletonList(SETTLERS_SETUP.getText());
    private List<String> catapultSetup = Collections.singletonList(CATAPULT_SETUP.getText());
    private List<String> gladiatorSetup = Arrays.asList(GLADIATOR_SETUP.getText(), DEBT_TOKEN_SETUP.getText());
    private List<String> taxSetup = Collections.singletonList(TAX_SETUP.getText());
    private List<String> aqueductSetup = Collections.singletonList(AQUEDUCT_SETUP.getText());
    private List<String> arenaSetup = Collections.singletonList(ARENA_SETUP.getText());
    private List<String> basilicaSetup = Collections.singletonList(BASILICA_SETUP.getText());
    private List<String> bathsSetup = Collections.singletonList(BATHS_SETUP.getText());
    private List<String> battlefieldSetup = Collections.singletonList(BATTLEFIELD_SETUP.getText());
    private List<String> colonnadeSetup = Collections.singletonList(COLONNADE_SETUP.getText());
    private List<String> defiledShrineSetup = Collections.singletonList(DEFILED_SHRINE_SETUP.getText());
    private List<String> labyrinthSetup = Collections.singletonList(LABYRINTH_SETUP.getText());
    private List<String> obeliskSetup = Collections.singletonList(OBELISK_SETUP.getText());
    private List<String> debtAndVictorySetup = Arrays.asList(VICTORY_TOKEN_SETUP.getText(), DEBT_TOKEN_SETUP.getText());
    private List<String> druidSetup = Arrays.asList(BOON_SETUP.getText(), WILL_O_WISP_SETUP.getText(), DRUID_SETUP.getText());
    private List<String> pixieSetup = Arrays.asList(BOON_SETUP.getText(), WILL_O_WISP_SETUP.getText(), PIXIE_SETUP.getText());
    private List<String> trackerSetup = Arrays.asList(BOON_SETUP.getText(), WILL_O_WISP_SETUP.getText(), TRACKER_SETUP.getText());
    private List<String> foolSetup = Arrays.asList(BOON_SETUP.getText(), WILL_O_WISP_SETUP.getText(), FOOL_CARD_SETUP.getText(), FOOL_STATE_SETUP.getText());
    private List<String> leprechaunSetup = Arrays.asList(WISH_SETUP.getText(), HEX_SETUP.getText(), DELUSION_ENVY_SETUP.getText(), MISERY_SETUP.getText());
    private List<String> secretCaveSetup = Arrays.asList(WISH_SETUP.getText(), SECRET_CAVE_SETUP.getText());
    private List<String> boonSetup = Arrays.asList(BOON_SETUP.getText(), WILL_O_WISP_SETUP.getText());
    private List<String> cemeterySetup = Arrays.asList(CEMETERY_SETUP.getText(), GHOST_SETUP.getText());
    private List<String> impSetup = Collections.singletonList(IMP_SETUP.getText());
    private List<String> exorcistSetup = Arrays.asList(WILL_O_WISP_SETUP.getText(), IMP_SETUP.getText(), GHOST_SETUP.getText());
    private List<String> necromancerSetup = Collections.singletonList(ZOMBIE_SETUP.getText());
    private List<String> shepherdSetup = Collections.singletonList(SHEPHERD_SETUP.getText());
    private List<String> hexSetup = Arrays.asList(HEX_SETUP.getText(), DELUSION_ENVY_SETUP.getText(), MISERY_SETUP.getText());
    private List<String> pookaSetup = Collections.singletonList(POOKA_SETUP.getText());
    private List<String> tormentorSetup = Arrays.asList(IMP_SETUP.getText(), HEX_SETUP.getText(), DELUSION_ENVY_SETUP.getText(), MISERY_SETUP.getText());
    private List<String> vampireSetup = Arrays.asList(HEX_SETUP.getText(), DELUSION_ENVY_SETUP.getText(), MISERY_SETUP.getText(), VAMPIRE_SETUP.getText());
    private List<String> willowispSetup = Collections.singletonList(WILL_O_WISP_SETUP.getText());
    private List<String> delusionEnvySetup = Collections.singletonList(DELUSION_ENVY_SETUP.getText());
    private List<String> miserySetup = Collections.singletonList(MISERY_SETUP.getText());

    @Autowired
	public DatabaseLoader(CardRepository cardRepository, EventRepository eventRepository, LandmarkRepository landmarkRepository,
                          BoonRepository boonRepository, HexRepository hexRepository, StateRepository stateRepository, KingdomRepository kingdomRepository) {
		this.cardRepository = cardRepository;
		this.eventRepository = eventRepository;
		this.landmarkRepository = landmarkRepository;
		this.boonRepository = boonRepository;
		this.hexRepository = hexRepository;
		this.stateRepository = stateRepository;
		this.kingdomRepository = kingdomRepository;
		typeList = new ArrayList<>();
	}

	@Override
	public void run(String... strings) throws Exception {

		cardRepository.deleteAll();
		eventRepository.deleteAll();
		landmarkRepository.deleteAll();
		boonRepository.deleteAll();
		hexRepository.deleteAll();
		stateRepository.deleteAll();
		kingdomRepository.deleteAll();

        saveBasicSupplyCards();
        saveSimpleCards();
        saveComplexCards();

        saveEvents();

        saveLandmarks();

        saveBoons();
        saveHexes();
        saveStates();

        KingdomSorter kingdomSorter = new KingdomSorter(kingdomRepository, cardRepository, eventRepository, landmarkRepository);
        kingdomSorter.createKingdoms();
	}

	private void saveCard(String cost, String cardName, List<String> types, Boolean supply) {
	    cardRepository.save(new Card(cost, cardName, box, types, supply));
    }

    private void saveCard(String cost, String cardName, List<String> types, List<String> otherSetup, Boolean supply) {
	    cardRepository.save(new Card(cost, cardName, box, types, otherSetup, supply));
    }

    private void saveEvent(String cost, String name) {
        eventRepository.save(new Event(cost, name, box));
    }

    private void saveEvent(String cost, String name, List<String> setup) {
        eventRepository.save(new Event(cost, name, box, setup));
    }

    private void saveLandmark(String name) {
        landmarkRepository.save(new Landmark(name, box));
    }

    private void saveLandmark(String name, List<String> setup) {
        landmarkRepository.save(new Landmark(name, box, setup));
    }

    private void saveBoon(String name) {
        boonRepository.save(new Boon(name, box));
    }

    private void saveBoon(String name, List<String> setup) {
        boonRepository.save(new Boon(name, box, setup));
    }

    private void saveHex(String name) {
        hexRepository.save(new Hex(name, box));
    }

    private void saveHex(String name, List<String> setup) {
        hexRepository.save(new Hex(name, box, setup));
    }

    private void saveState(String name) {
        stateRepository.save(new State(name, box));
    }

    private void saveBasicSupplyCards() {
	    box = BASIC.getName();
	    
        saveCard("0", COPPER.getName(), treasureCard, false);
        saveCard("0", CardName.CURSE.getName(), curseCard, false);
        saveCard("2", ESTATE.getName(), victoryCard, false);
        saveCard("3", SILVER.getName(), treasureCard, false);
        saveCard("5", DUCHY.getName(), victoryCard, false);
        saveCard("6", GOLD.getName(), treasureCard, false);
        saveCard("8", PROVINCE.getName(), victoryCard, false);
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
	    
        saveCard("2", CELLAR.getName(), actionCard,true);
        saveCard("2", CHAPEL.getName(), actionCard, true);
        saveCard("5", COUNCIL_ROOM.getName(), actionCard, true);
        saveCard("5", FESTIVAL.getName(), actionCard, true);
        saveCard("4", GARDENS.getName(), victoryCard, true);
        saveCard("5", LABORATORY.getName(), actionCard, true);
        saveCard("5", LIBRARY.getName(), actionCard, true);
        saveCard("5", MARKET.getName(), actionCard, true);
        saveCard("5", MINE.getName(), actionCard, true);
        saveCard("4", MONEYLENDER.getName(), actionCard, true);
        saveCard("4", REMODEL.getName(), actionCard, true);
        saveCard("4", SMITHY.getName(), actionCard, true);
        saveCard("4", THRONE_ROOM.getName(), actionCard, true);
        saveCard("3", VILLAGE.getName(), actionCard, true);
        saveCard("3", WORKSHOP.getName(), actionCard, true);
    }

    private void saveSimpleOldDominionCards() {
	    box = OLD_DOMINION.getName();
	    
        saveCard("3", CHANCELLOR.getName(), actionCard, true);
        saveCard("3", WOODCUTTER.getName(), actionCard, true);
        saveCard("4", FEAST.getName(), actionCard, true);
        saveCard("6", ADVENTURER.getName(), actionCard, true);
    }

    private void saveSimpleNewDominionCards() {
	    box = NEW_DOMINION.getName();
	    
        saveCard("3", HARBINGER.getName(), actionCard, true);
        saveCard("3", MERCHANT.getName(), actionCard, true);
        saveCard("3", VASSAL.getName(), actionCard, true);
        saveCard("4", POACHER.getName(), actionCard, true);
        saveCard("5", SENTRY.getName(), actionCard, true);
        saveCard("6", ARTISAN.getName(), actionCard, true);
    }

    private void saveSimpleIntrigueCards() {
	    box = INTRIGUE.getName();
	    
        saveCard("2", COURTYARD.getName(), actionCard, true);
        saveCard("2", PAWN.getName(), actionCard, true);
        saveCard("3", MASQUERADE.getName(), actionCard, true);
        saveCard("3", SHANTY_TOWN.getName(), actionCard, true);
        saveCard("3", STEWARD.getName(), actionCard, true);
        saveCard("3", WISHING_WELL.getName(), actionCard, true);
        saveCard("4", BARON.getName(), actionCard, true);
        saveCard("4", BRIDGE.getName(), actionCard, true);
        saveCard("4", CONSPIRATOR.getName(), actionCard, true);
        saveCard("4", IRONWORKS.getName(), actionCard, true);
        saveCard("4", MINING_VILLAGE.getName(), actionCard, true);
        saveCard("5", DUKE.getName(), victoryCard, true);
        saveCard("5", TRADING_POST.getName(), actionCard, true);
        saveCard("5", UPGRADE.getName(), actionCard, true);
    }

    private void saveSimpleOldIntrigueCards() {
	    box = OLD_INTRIGUE.getName();
	    
        saveCard("4", COPPERSMITH.getName(), actionCard, true);
        saveCard("4", SCOUT.getName(), actionCard, true);
        saveCard("5", TRIBUTE.getName(), actionCard, true);
    }

    private void saveSimpleNewIntrigueCards() {
	    box = NEW_INTRIGUE.getName();
	    
        saveCard("2", LURKER.getName(), actionCard, true);
        saveCard("4", SECRET_PASSAGE.getName(), actionCard, true);
        saveCard("5", COURTIER.getName(), actionCard, true);
        saveCard("5", PATROL.getName(), actionCard, true);
    }

    private void saveSimpleSeasideCards() {
	    box = SEASIDE.getName();

        saveCard("2", EMBARGO.getName(), actionCard, embargoSetup, true);
        saveCard("2", NATIVE_VILLAGE.getName(), actionCard, nativeVillageSetup, true);
        saveCard("2", PEARL_DIVER.getName(), actionCard, true);
        saveCard("3", LOOKOUT.getName(), actionCard, true);
        saveCard("3", SMUGGLERS.getName(), actionCard, true);
        saveCard("3", WAREHOUSE.getName(), actionCard, true);
        saveCard("4", NAVIGATOR.getName(), actionCard, true);
        saveCard("4", SALVAGER.getName(), actionCard, true);
        saveCard("4", TREASURE_MAP.getName(), actionCard, true);
        saveCard("5", BAZAAR.getName(), actionCard, true);
        saveCard("5", EXPLORER.getName(), actionCard, true);
        saveCard("5", TREASURY.getName(), actionCard, true);
    }

    private void saveSimpleAlchemyCards() {
	    box = ALCHEMY.getName();

	    saveCard("4", POTION.getName(), treasureCard, false);
	    saveCard("Potion", TRANSMUTE.getName(), actionCard, potionSetup, true);
	    saveCard("Potion", VINEYARD.getName(), victoryCard, potionSetup, true);
	    saveCard("2", HERBALIST.getName(), actionCard, true);
	    saveCard("2 & Potion", APOTHECARY.getName(), actionCard, potionSetup, true);
	    saveCard("2 & Potion", UNIVERSITY.getName(), actionCard, potionSetup, true);
	    saveCard("3 & Potion", ALCHEMIST.getName(), actionCard, potionSetup, true);
	    saveCard("3 & Potion", PHILOSOPHERS_STONE.getName(), treasureCard, potionSetup, true);
	    saveCard("4 & Potion", GOLEM.getName(), actionCard, potionSetup, true);
	    saveCard("5", APPRENTICE.getName(), actionCard, true);
	    saveCard("6 & Potion", POSSESSION.getName(), actionCard, potionSetup, true);
    }

    private void saveSimpleProsperityCards() {
	    box = PROSPERITY.getName();

        saveCard("9", PLATINUM.getName(), treasureCard, false);
        saveCard("11", COLONY.getName(), victoryCard, false);
        saveCard("3", LOAN.getName(), treasureCard, prosperitySetup, true);
        saveCard("3", TRADE_ROUTE.getName(), actionCard, tradeRouteSetup, true);
        saveCard("3", BISHOP.getName(), actionCard, victoryAndProsperitySetup, true);
        saveCard("4", MONUMENT.getName(), actionCard, victoryAndProsperitySetup, true);
        saveCard("4", QUARRY.getName(), treasureCard, prosperitySetup, true);
        saveCard("4", TALISMAN.getName(), treasureCard, prosperitySetup, true);
        saveCard("4", WORKERS_VILLAGE.getName(), actionCard, prosperitySetup, true);
        saveCard("5", CITY.getName(), actionCard, prosperitySetup, true);
        saveCard("5", CONTRABAND.getName(), treasureCard, prosperitySetup, true);
        saveCard("5", COUNTING_HOUSE.getName(), actionCard, prosperitySetup, true);
        saveCard("5", MINT.getName(), actionCard, prosperitySetup, true);
        saveCard("5", ROYAL_SEAL.getName(), treasureCard, prosperitySetup, true);
        saveCard("5", VAULT.getName(), actionCard, prosperitySetup, true);
        saveCard("5", VENTURE.getName(), treasureCard, prosperitySetup, true);
        saveCard("6*", GRAND_MARKET.getName(), actionCard, prosperitySetup, true);
        saveCard("6", HOARD.getName(), treasureCard, prosperitySetup, true);
        saveCard("7", BANK.getName(), treasureCard, prosperitySetup, true);
        saveCard("7", EXPAND.getName(), actionCard, prosperitySetup, true);
        saveCard("7", FORGE.getName(), actionCard, prosperitySetup, true);
        saveCard("7", KINGS_COURT.getName(), actionCard, prosperitySetup, true);
        saveCard("8*", PEDDLER.getName(), actionCard, prosperitySetup, true);
    }

    private void saveSimpleCornucopiaCards() {
        box = CORNUCOPIA.getName();

        saveCard("2", HAMLET.getName(), actionCard, true);
        saveCard("3", MENAGERIE.getName(), actionCard, true);
        saveCard("4", FARMING_VILLAGE.getName(), actionCard, true);
        saveCard("4", REMAKE.getName(), actionCard, true);
        saveCard("4", TOURNAMENT.getName(), actionCard, tournamentSetup, true);
        saveCard("5", HARVEST.getName(), actionCard, true);
        saveCard("5", HORN_OF_PLENTY.getName(), treasureCard, true);
        saveCard("5", HUNTING_PARTY.getName(), actionCard, true);
        saveCard("6", FAIRGROUNDS.getName(), victoryCard, true);
    }

    private void saveSimpleHinterlandsCards() {
        box = HINTERLANDS.getName();

        saveCard("2", CROSSROADS.getName(), actionCard, true);
        saveCard("2", DUCHESS.getName(), actionCard, true);
        saveCard("3", DEVELOP.getName(), actionCard, true);
        saveCard("3", OASIS.getName(), actionCard, true);
        saveCard("3", SCHEME.getName(), actionCard, true);
        saveCard("4", JACK_OF_ALL_TRADES.getName(), actionCard, true);
        saveCard("4", NOMAD_CAMP.getName(), actionCard, true);
        saveCard("4", SILK_ROAD.getName(), victoryCard, true);
        saveCard("4", SPICE_MERCHANT.getName(), actionCard, true);
        saveCard("5", CACHE.getName(), treasureCard, true);
        saveCard("5", CARTOGRAPHER.getName(), actionCard, true);
        saveCard("5", EMBASSY.getName(), actionCard, true);
        saveCard("5", HAGGLER.getName(), actionCard, true);
        saveCard("5", HIGHWAY.getName(), actionCard, true);
        saveCard("5", ILL_GOTTEN_GAINS.getName(), treasureCard, true);
        saveCard("5", INN.getName(), actionCard, true);
        saveCard("5", MANDARIN.getName(), actionCard, true);
        saveCard("5", STABLES.getName(), actionCard, true);
        saveCard("6", BORDER_VILLAGE.getName(), actionCard, true);
        saveCard("6", FARMLAND.getName(), victoryCard, true);
    }

    private void saveSimpleDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("1", POOR_HOUSE.getName(), actionCard, darkAgesSetup, true);
        saveCard("2", SQUIRE.getName(), actionCard, darkAgesSetup, true);
        saveCard("2", VAGRANT.getName(), actionCard, darkAgesSetup, true);
        saveCard("3", FORAGER.getName(), actionCard, darkAgesSetup, true);
        saveCard("3", HERMIT.getName(), actionCard, hermitSetup, true);
        saveCard("3", SAGE.getName(), actionCard, darkAgesSetup, true);
        saveCard("3", STOREROOM.getName(), actionCard, darkAgesSetup, true);
        saveCard("4", ARMORY.getName(), actionCard, darkAgesSetup, true);
        saveCard("4", FEODUM.getName(), victoryCard, darkAgesSetup, true);
        saveCard("4", FORTRESS.getName(), actionCard, darkAgesSetup, true);
        saveCard("4", IRONMONGER.getName(), actionCard, darkAgesSetup, true);
        saveCard("4", PROCESSION.getName(), actionCard, darkAgesSetup, true);
        saveCard("4", RATS.getName(), actionCard, darkAgesSetup, true);
        saveCard("4", SCAVENGER.getName(), actionCard, darkAgesSetup, true);
        saveCard("4", WANDERING_MINSTREL.getName(), actionCard, darkAgesSetup, true);
        saveCard("5", BAND_OF_MISFITS.getName(), actionCard, darkAgesSetup, true);
        saveCard("5", BANDIT_CAMP.getName(), actionCard, spoilsAndDarkAgesSetup, true);
        saveCard("5", CATACOMBS.getName(), actionCard, darkAgesSetup, true);
        saveCard("5", COUNT.getName(), actionCard, darkAgesSetup, true);
        saveCard("5", COUNTERFEIT.getName(), treasureCard, darkAgesSetup, true);
        saveCard("5", GRAVEROBBER.getName(), actionCard, darkAgesSetup, true);
        saveCard("5", JUNK_DEALER.getName(), actionCard, darkAgesSetup, true);
        saveCard("5", MYSTIC.getName(), actionCard, darkAgesSetup, true);
        saveCard("5", REBUILD.getName(), actionCard, darkAgesSetup, true);
        saveCard("6", ALTAR.getName(), actionCard, darkAgesSetup, true);
        saveCard("6", HUNTING_GROUNDS.getName(), actionCard, darkAgesSetup, true);
        saveCard("0*", MADMAN.getName(), actionCard, darkAgesSetup, false);
        saveCard("0*", SPOILS.getName(), treasureCard, darkAgesSetup, false);
    }

    private void saveSimpleGuildsCards() {
        box = GUILDS.getName();

        saveCard("2", CANDLESTICK_MAKER.getName(), actionCard, coinTokenSetup, true);
        saveCard("2+", STONEMASON.getName(), actionCard, true);
        saveCard("3+", DOCTOR.getName(), actionCard, true);
        saveCard("3+", MASTERPIECE.getName(), treasureCard, true);
        saveCard("4", ADVISOR.getName(), actionCard, true);
        saveCard("4", PLAZA.getName(), actionCard, coinTokenSetup, true);
        saveCard("4+", HERALD.getName(), actionCard, true);
        saveCard("5", BAKER.getName(), actionCard, bakerSetup, true);
        saveCard("5", BUTCHER.getName(), actionCard, coinTokenSetup, true);
        saveCard("5", JOURNEYMAN.getName(), actionCard, true);
        saveCard("5", MERCHANT_GUILD.getName(), actionCard, coinTokenSetup, true);
    }

    private void saveSimpleAdventuresCards() {
        box = ADVENTURES.getName();

        saveCard("2", RAZE.getName(), actionCard, true);
        saveCard("4", MAGPIE.getName(), actionCard, true);
        saveCard("4", MESSENGER.getName(), actionCard, true);
        saveCard("4", MISER.getName(), actionCard, reserveSetup, true);
        saveCard("4", PORT.getName(), actionCard, true);
        saveCard("4", RANGER.getName(), actionCard, journeySetup, true);
        saveCard("5", ARTIFICER.getName(), actionCard, true);
        saveCard("5", LOST_CITY.getName(), actionCard, true);
        saveCard("5", STORYTELLER.getName(), actionCard, true);
        saveCard("5", TREASURE_TROVE.getName(), treasureCard, true);
    }

    private void saveSimpleEmpiresCards() {
        box = EMPIRES.getName();

        saveCard("4 Debt", ENGINEER.getName(), actionCard, debtTokenSetup, true);
        saveCard("8 Debt", CITY_QUARTER.getName(), actionCard, debtTokenSetup, true);
        saveCard("8 Debt", OVERLORD.getName(), actionCard, debtTokenSetup, true);
        saveCard("8 Debt", ROYAL_BLACKSMITH.getName(), actionCard, debtTokenSetup, true);
        saveCard("2", ENCAMPMENT.getName(), actionCard, encampmentSetup, true);
        saveCard("5", PLUNDER.getName(), treasureCard, encampmentSetup, false);
        saveCard("2", PATRICIAN.getName(), actionCard, patricianSetup, true);
        saveCard("5", EMPORIUM.getName(), actionCard, patricianSetup, false);
        saveCard("2", SETTLERS.getName(), actionCard, settlersSetup, true);
        saveCard("5", BUSTLING_VILLAGE.getName(), actionCard, settlersSetup, false);
        saveCard("4", ROCKS.getName(), treasureCard, catapultSetup, false);
        saveCard("3", CHARIOT_RACE.getName(), actionCard, victoryTokenSetup, true);
        saveCard("3", GLADIATOR.getName(), actionCard, gladiatorSetup, true);
        saveCard("8 & 8 Debt", FORTUNE.getName(), treasureCard, gladiatorSetup, false);
        saveCard("4", SACRIFICE.getName(), actionCard, victoryTokenSetup, true);
        saveCard("4", VILLA.getName(), actionCard, true);
        saveCard("5", CAPITAL.getName(), treasureCard, debtTokenSetup, true);
        saveCard("5", CHARM.getName(), treasureCard, true);
        saveCard("5", FORUM.getName(), actionCard, true);
        saveCard("5", GROUNDSKEEPER.getName(), actionCard, victoryTokenSetup, true);
    }

    private void saveSimpleNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("2", MONASTERY.getName(), nightCard, true);
        saveCard("3", CHANGELING.getName(), nightCard, true);
        saveCard("3", NIGHT_WATCHMAN.getName(), nightCard, true);
        saveCard("4", CEMETERY.getName(), victoryCard, cemeterySetup, true);
        saveCard("4", CONCLAVE.getName(), actionCard, true);
        saveCard("4", DEVILS_WORKSHOP.getName(), nightCard, impSetup, true);
        saveCard("4", EXORCIST.getName(), nightCard, exorcistSetup, true);
        saveCard("4", NECROMANCER.getName(), actionCard, necromancerSetup, true);
        saveCard("4", SHEPHERD.getName(), actionCard, shepherdSetup, true);
        saveCard("5", POOKA.getName(), actionCard, pookaSetup, true);
        saveCard("5", TRAGIC_HERO.getName(), actionCard, true);
        saveCard("0*", WISH.getName(), actionCard, false);
        saveCard("2*", BAT.getName(), nightCard, false);
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
        saveTreasureReserveCards();
        saveActionTravellerCards();
        saveActionReserveCards();
        saveActionDurationReactionCards();
        saveActionAttackDurationCards();
        saveActionReserveVictoryCards();
        saveTreasureAttackCards();
        saveActionAttackTravellerCards();
        saveVictoryCastleCards();
        saveActionGatheringCards();
        saveActionTreasureCards();
        saveTreasureVictoryCastleCards();
        saveActionVictoryCastleCards();
        saveActionFateCards();
        saveNightDurationCards();
        saveActionDoomCards();
        saveActionAttackDoomCards();
        saveTreasureAttackFateCards();
        saveNightAttackDoomCards();
        saveActionNightAttackDoomCards();
        saveNightDurationAttackCards();
        saveTreasureHeirloomCards();
        saveTreasureVictoryHeirloomCards();
        saveActionSpiritCards();
        saveActionZombieCards();
        saveNightDurationSpiritCards();
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

        saveActionReactionNocturneCards();
    }

    private void saveActionReactionDominionCards() {
	    box = DOMINION.getName();

        saveCard("2", MOAT.getName(), typeList, true);
    }

    private void saveActionReactionOldIntrigueCards() {
	    box = OLD_INTRIGUE.getName();

	    saveCard("2", SECRET_CHAMBER.getName(), typeList, true);
    }

    private void saveActionReactionNewIntrigueCards() {
	    box = NEW_INTRIGUE.getName();

	    saveCard("4", DIPLOMAT.getName(), typeList, true);
    }

    private void saveActionReactionProsperityCards() {
	    box = PROSPERITY.getName();

	    saveCard("3", WATCHTOWER.getName(), typeList, prosperitySetup, true);
    }

    private void saveActionReactionCornucopiaCards() {
        box = CORNUCOPIA.getName();

        saveCard("4", HORSE_TRADERS.getName(), typeList, true);
    }

    private void saveActionReactionHinterlandsCards() {
        box = HINTERLANDS.getName();

        saveCard("4", TRADER.getName(), typeList, true);
    }

    private void saveActionReactionDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("2", BEGGAR.getName(), typeList, darkAgesSetup, true);
        saveCard("3", MARKET_SQUARE.getName(), typeList, darkAgesSetup, true);
    }

    private void saveActionReactionNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("2", FAITHFUL_HOUND.getName(), typeList, true);
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

        saveActionAttackAdventuresCards();

        saveActionAttackEmpiresCards();
    }

    private void saveActionAttackDominionCards() {
	    box = DOMINION.getName();

        saveCard("4", BUREAUCRAT.getName(), typeList, true);
        saveCard("4", MILITIA.getName(), typeList, true);
        saveCard("5", WITCH.getName(), typeList, true);
    }

    private void saveActionAttackOldDominionCards() {
	    box = OLD_DOMINION.getName();

        saveCard("4", SPY.getName(), typeList, true);
        saveCard("4", THIEF.getName(), typeList, true);
    }

    private void saveActionAttackNewDominionCards() {
	    box = NEW_DOMINION.getName();

        saveCard("5", BANDIT.getName(), typeList, true);
    }

    private void saveActionAttackIntrigueCards() {
	    box = INTRIGUE.getName();

	    saveCard("3", SWINDLER.getName(), typeList, true);
	    saveCard("5", MINION.getName(), typeList, true);
	    saveCard("5", TORTURER.getName(), typeList, true);
    }

    private void saveActionAttackOldIntrigueCards() {
	    box = OLD_INTRIGUE.getName();

	    saveCard("5", SABOTEUR.getName(), typeList, true);
    }

    private void saveActionAttackNewIntrigueCards() {
	    box = NEW_INTRIGUE.getName();

	    saveCard("5", REPLACE.getName(), typeList, true);
    }

    private void saveActionAttackSeasideCards() {
	    box = SEASIDE.getName();

	    saveCard("3", AMBASSADOR.getName(), typeList, true);
	    saveCard("4", CUTPURSE.getName(), typeList, true);
	    saveCard("4", PIRATE_SHIP.getName(), typeList, pirateShipSetup, true);
	    saveCard("4", SEA_HAG.getName(), typeList, true);
	    saveCard("5", GHOST_SHIP.getName(), typeList, true);
    }

    private void saveActionAttackAlchemyCards() {
	    box = ALCHEMY.getName();

	    saveCard("2 & Potion", SCRYING_POOL.getName(), typeList, potionSetup, true);
	    saveCard("3 & Potion", FAMILIAR.getName(), typeList, potionSetup, true);
    }

    private void saveActionAttackProsperityCards() {
	    box = PROSPERITY.getName();

	    saveCard("5", MOUNTEBANK.getName(), typeList, prosperitySetup, true);
	    saveCard("5", RABBLE.getName(), typeList, prosperitySetup, true);
	    saveCard("6", GOONS.getName(), typeList, victoryAndProsperitySetup, true);
    }

    private void saveActionAttackCornucopiaCards() {
        box = CORNUCOPIA.getName();

        saveCard("3", FORTUNE_TELLER.getName(), typeList, true);
        saveCard("4", YOUNG_WITCH.getName(), typeList, youngWitchSetup, true);
        saveCard("5", JESTER.getName(), typeList, true);
    }

    private void saveActionAttackHinterlandsCards() {
        box = HINTERLANDS.getName();

        saveCard("3", ORACLE.getName(), typeList, true);
        saveCard("4", NOBLE_BRIGAND.getName(), typeList, true);
        saveCard("5", MARGRAVE.getName(), typeList, true);
    }

    private void saveActionAttackDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("3", URCHIN.getName(), typeList, urchinSetup, true);
        saveCard("5", PILLAGE.getName(), typeList, spoilsAndDarkAgesSetup, true);
        saveCard("5", ROGUE.getName(), typeList, darkAgesSetup, true);
        saveCard("0*", MERCENARY.getName(), typeList, darkAgesSetup, false);
    }

    private void saveActionAttackGuildsCards() {
        box = GUILDS.getName();

        saveCard("4", TAXMAN.getName(), typeList, true);
        saveCard("5", SOOTHSAYER.getName(), typeList, true);
    }

    private void saveActionAttackAdventuresCards() {
        box = ADVENTURES.getName();

        saveCard("5", GIANT.getName(), typeList, journeySetup, true);
    }

    private void saveActionAttackEmpiresCards() {
        box = EMPIRES.getName();

        saveCard("3", CATAPULT.getName(), typeList, catapultSetup, true);
        saveCard("5", LEGIONARY.getName(), typeList, true);
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

	    saveCard("6", NOBLES.getName(), typeList, true);
    }

    private void saveActionVictoryOldIntrigueCards() {
	    box = OLD_INTRIGUE.getName();

	    saveCard("3", GREAT_HALL.getName(), typeList, true);
    }

    private void saveActionVictoryNewIntrigueCards() {
	    box = NEW_INTRIGUE.getName();

	    saveCard("4", MILL.getName(), typeList, true);
    }

    private void saveActionVictorySeasideCards() {
	    box = SEASIDE.getName();

	    saveCard("4", ISLAND.getName(), typeList, islandSetup, true);
    }

    private void saveTreasureVictoryCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(victoryCard);

        saveTreasureVictoryIntrigueCards();
    }

    private void saveTreasureVictoryIntrigueCards() {
	    box = INTRIGUE.getName();

	    saveCard("6", HAREM.getName(), typeList, true);
    }

    private void saveActionDurationCards() {
	    typeList.clear();
	    typeList.addAll(actionCard);
	    typeList.addAll(durationCard);

	    saveActionDurationSeasideCards();

	    saveActionDurationAdventuresCards();

	    saveActionDurationEmpiresCards();

	    saveActionDurationNocturneCards();
    }

    private void saveActionDurationSeasideCards() {
	    box = SEASIDE.getName();

        saveCard("2", HAVEN.getName(), typeList, true);
        saveCard("2", LIGHTHOUSE.getName(), typeList, true);
        saveCard("3", FISHING_VILLAGE.getName(), typeList, true);
        saveCard("4", CARAVAN.getName(), typeList, true);
        saveCard("5", MERCHANT_SHIP.getName(), typeList, true);
        saveCard("5", OUTPOST.getName(), typeList, true);
        saveCard("5", TACTICIAN.getName(), typeList, true);
        saveCard("5", WHARF.getName(), typeList, true);
    }

    private void saveActionDurationAdventuresCards() {
        box = ADVENTURES.getName();

        saveCard("3", AMULET.getName(), typeList, true);
        saveCard("3", DUNGEON.getName(), typeList, true);
        saveCard("3", GEAR.getName(), typeList, true);
        saveCard("6", HIRELING.getName(), typeList, true);
        saveCard("6*", CHAMPION.getName(), typeList, false);
    }

    private void saveActionDurationEmpiresCards() {
        box = EMPIRES.getName();

        saveCard("5", ARCHIVE.getName(), typeList, true);
    }

    private void saveActionDurationNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("3", SECRET_CAVE.getName(), typeList, secretCaveSetup, true);
    }

    private void saveActionPrizeCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(prizeCard);

        saveActionPrizeCornucopiaCards();
    }

    private void saveActionPrizeCornucopiaCards() {
        box = CORNUCOPIA.getName();

        saveCard("0*", BAG_OF_GOLD.getName(), typeList, false);
        saveCard("0*", PRINCESS.getName(), typeList, false);
        saveCard("0*", TRUSTY_STEED.getName(), typeList, false);
    }

    private void saveTreasurePrizeCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(prizeCard);

        saveTreasurePrizeCornucopiaCards();
    }

    private void saveTreasurePrizeCornucopiaCards() {
        box = CORNUCOPIA.getName();

        saveCard("0*", DIADEM.getName(), typeList, false);
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

        saveCard("0*", FOLLOWERS.getName(), typeList, false);
    }

    private void saveTreasureReactionCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(reactionCard);

        saveTreasureReactionHinterlandsCards();
    }

    private void saveTreasureReactionHinterlandsCards() {
        box = HINTERLANDS.getName();

        saveCard("2", FOOLS_GOLD.getName(), typeList, true);
    }

    private void saveVictoryReactionCards() {
        typeList.clear();
        typeList.addAll(victoryCard);
        typeList.addAll(reactionCard);

        saveVictoryReactionHinterlandsCards();
    }

    private void saveVictoryReactionHinterlandsCards() {
        box = HINTERLANDS.getName();

        saveCard("3", TUNNEL.getName(), typeList, true);
    }

    private void saveActionRuinsCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(ruinsCard);

        saveActionRuinsDarkAgesCards();
    }

    private void saveActionRuinsDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("0", ABANDONED_MINE.getName(), typeList, darkAgesSetup, false);
        saveCard("0", RUINED_LIBRARY.getName(), typeList, darkAgesSetup, false);
        saveCard("0", RUINED_MARKET.getName(), typeList, darkAgesSetup, false);
        saveCard("0", RUINED_VILLAGE.getName(), typeList, darkAgesSetup, false);
        saveCard("0", SURVIVORS.getName(), typeList, darkAgesSetup, false);
    }

    private void saveActionLooterCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(looterCard);

        saveActionLooterDarkAgesCards();
    }

    private void saveActionLooterDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("4", DEATH_CART.getName(), typeList, ruinsAndDarkAgesSetup, true);
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

        saveCard("4", MARAUDER.getName(), typeList, marauderSetup, true);
        saveCard("5", CULTIST.getName(), typeList, ruinsAndDarkAgesSetup, true);
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

        saveCard("5", KNIGHTS.getName(), typeList, knightsSetup, true);
        saveCard("5", DAME_ANNA.getName(), typeList, knightsSetup, false);
        saveCard("5", DAME_MOLLY.getName(), typeList, knightsSetup, false);
        saveCard("5", DAME_NATALIE.getName(), typeList, knightsSetup, false);
        saveCard("5", DAME_SYLVIA.getName(), typeList, knightsSetup, false);
        saveCard("5", SIR_BAILEY.getName(), typeList, knightsSetup, false);
        saveCard("5", SIR_DESTRY.getName(), typeList, knightsSetup, false);
        saveCard("4", SIR_MARTIN.getName(), typeList, knightsSetup, false);
        saveCard("5", SIR_MICHAEL.getName(), typeList, knightsSetup, false);
        saveCard("5", SIR_VANDER.getName(), typeList, knightsSetup, false);
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

        saveCard("5", DAME_JOSEPHINE.getName(), typeList, knightsSetup, false);
    }

    private void saveReactionShelterCards() {
        typeList.clear();
        typeList.addAll(reactionCard);
        typeList.addAll(shelterCard);

        saveReactionShelterDarkAgesCards();
    }

    private void saveReactionShelterDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("1", HOVEL.getName(), typeList, false);
    }

    private void saveActionShelterCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(shelterCard);

        saveActionShelterDarkAgesCards();
    }

    private void saveActionShelterDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("1", NECROPOLIS.getName(), typeList, false);
    }

    private void saveVictoryShelterCards() {
        typeList.clear();
        typeList.addAll(victoryCard);
        typeList.addAll(shelterCard);

        saveVictoryShelterDarkAgesCards();
    }

    private void saveVictoryShelterDarkAgesCards() {
        box = DARK_AGES.getName();

        saveCard("1", OVERGROWN_ESTATE.getName(), typeList, false);
    }

    private void saveTreasureReserveCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(reserveCard);

        saveTreasureReserveAdventureCards();
    }

    private void saveTreasureReserveAdventureCards() {
        box = ADVENTURES.getName();

        saveCard("2", COIN_OF_THE_REALM.getName(), typeList, reserveSetup, true);
    }

    private void saveActionTravellerCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(travellerCard);

        saveActionTravellerAdventuresCards();
    }

    private void saveActionTravellerAdventuresCards() {
        box = ADVENTURES.getName();

        saveCard("2", PAGE.getName(), typeList, pageSetup, true);
        saveCard("2", PEASANT.getName(), typeList, peasantSetup, true);
        saveCard("3*", TREASURE_HUNTER.getName(), typeList, false);
        saveCard("5*", HERO.getName(), typeList, false);
        saveCard("4*", FUGITIVE.getName(), typeList, false);
        saveCard("5*", DISCIPLE.getName(), typeList, false);
    }

    private void saveActionReserveCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(reserveCard);

        saveActionReserveAdventuresCards();
    }

    private void saveActionReserveAdventuresCards() {
        box = ADVENTURES.getName();

        saveCard("2", RATCATCHER.getName(), typeList, reserveSetup, true);
        saveCard("3", GUIDE.getName(), typeList, reserveSetup, true);
        saveCard("4", DUPLICATE.getName(), typeList, reserveSetup, true);
        saveCard("4", TRANSMOGRIFY.getName(), typeList, reserveSetup, true);
        saveCard("5", ROYAL_CARRIAGE.getName(), typeList, reserveSetup, true);
        saveCard("5", WINE_MERCHANT.getName(), typeList, reserveSetup, true);
        saveCard("6*", TEACHER.getName(), typeList, peasantSetup, false);
    }

    private void saveActionDurationReactionCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(durationCard);
        typeList.addAll(reactionCard);

        saveActionDurationReactionAdventuresCards();
    }

    private void saveActionDurationReactionAdventuresCards() {
        box = ADVENTURES.getName();

        saveCard("3", CARAVAN_GUARD.getName(), typeList, true);
    }

    private void saveActionAttackDurationCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(attackCard);
        typeList.addAll(durationCard);

        saveActionAttackDurationAdventuresCards();

        saveActionAttackDurationEmpiresCards();
    }

    private void saveActionAttackDurationAdventuresCards() {
        box = ADVENTURES.getName();

        saveCard("5", BRIDGE_TROLL.getName(), typeList, minusCoinSetup, true);
        saveCard("5", HAUNTED_WOODS.getName(), typeList, true);
        saveCard("5", SWAMP_HAG.getName(), typeList, true);
    }

    private void saveActionAttackDurationEmpiresCards() {
        box = EMPIRES.getName();

        saveCard("3", ENCHANTRESS.getName(), typeList, true);
    }

    private void saveActionReserveVictoryCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(reserveCard);
        typeList.addAll(victoryCard);

        saveActionReserveVictoryAdventuresCards();
    }

    private void saveActionReserveVictoryAdventuresCards() {
        box = ADVENTURES.getName();

        saveCard("5", DISTANT_LANDS.getName(), typeList, reserveSetup, true);
    }

    private void saveTreasureAttackCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(attackCard);

        saveTreasureAttackAdventuresCards();
    }

    private void saveTreasureAttackAdventuresCards() {
        box = ADVENTURES.getName();

        saveCard("5", RELIC.getName(), typeList, minusCardSetup, true);
    }

    private void saveActionAttackTravellerCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(attackCard);
        typeList.addAll(travellerCard);

        saveActionAttackTravellerAdventuresCards();
    }

    private void saveActionAttackTravellerAdventuresCards() {
        box = ADVENTURES.getName();

        saveCard("4*", WARRIOR.getName(), typeList, false);
        saveCard("3*", SOLDIER.getName(), typeList, false);
    }

    private void saveVictoryCastleCards() {
        typeList.clear();
        typeList.addAll(victoryCard);
        typeList.addAll(castleCard);

        saveVictoryCastleEmpiresCards();
    }

    private void saveVictoryCastleEmpiresCards() {
        box = EMPIRES.getName();

        saveCard("3", CASTLES.getName(), typeList, victoryTokenSetup, true);
        saveCard("4", CRUMBLING_CASTLE.getName(), typeList, victoryTokenSetup, false);
        saveCard("6", HAUNTED_CASTLE.getName(), typeList, false);
        saveCard("8", SPRAWLING_CASTLE.getName(), typeList, false);
        saveCard("9", GRAND_CASTLE.getName(), typeList, false);
        saveCard("10", KINGS_CASTLE.getName(), typeList, false);
    }

    private void saveActionGatheringCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(gatheringCard);

        saveActionGatheringEmpiresCards();
    }

    private void saveActionGatheringEmpiresCards() {
        box = EMPIRES.getName();

        saveCard("3", FARMERS_MARKET.getName(), typeList, victoryTokenSetup, true);
        saveCard("4", TEMPLE.getName(), typeList, victoryTokenSetup, true);
        saveCard("5", WILD_HUNT.getName(), typeList, victoryTokenSetup, true);
    }

    private void saveActionTreasureCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(treasureCard);

        saveActionTreasureEmpiresCards();
    }

    private void saveActionTreasureEmpiresCards() {
        box = EMPIRES.getName();

        saveCard("5", CROWN.getName(), typeList, true);
    }

    private void saveTreasureVictoryCastleCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(victoryCard);
        typeList.addAll(castleCard);

        saveTreasureVictoryCastleEmpiresCards();
    }

    private void saveTreasureVictoryCastleEmpiresCards() {
        box = EMPIRES.getName();

        saveCard("3", HUMBLE_CASTLE.getName(), typeList, false);
    }

    private void saveActionVictoryCastleCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(victoryCard);
        typeList.addAll(castleCard);

        saveActionVictoryCastleEmpiresCards();
    }

    private void saveActionVictoryCastleEmpiresCards() {
        box = EMPIRES.getName();

        saveCard("5", SMALL_CASTLE.getName(), typeList, false);
        saveCard("7", OPULENT_CASTLE.getName(), typeList, false);
    }

    private void saveActionFateCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(fateCard);

        saveActionFateNocturneCards();
    }

    private void saveActionFateNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("2", DRUID.getName(), typeList, druidSetup, true);
        saveCard("2", PIXIE.getName(), typeList, pixieSetup, true);
        saveCard("2", TRACKER.getName(), typeList, trackerSetup, true);
        saveCard("3", FOOL.getName(), typeList, foolSetup, true);
        saveCard("4", BARD.getName(), typeList, boonSetup, true);
        saveCard("4", BLESSED_VILLAGE.getName(), typeList, boonSetup, true);
        saveCard("5", SACRED_GROVE.getName(), typeList, boonSetup, true);
    }

    private void saveNightDurationCards() {
        typeList.clear();
        typeList.addAll(nightCard);
        typeList.addAll(durationCard);

        saveNightDurationNocturneCards();
    }

    private void saveNightDurationNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("2", GUARDIAN.getName(), typeList, true);
        saveCard("3", GHOST_TOWN.getName(), typeList, true);
        saveCard("5", COBBLER.getName(), typeList, true);
        saveCard("5", CRYPT.getName(), typeList, true);
        saveCard("5", DEN_OF_SIN.getName(), typeList, true);
    }

    private void saveActionDoomCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(doomCard);

        saveActionDoomNocturneCards();
    }

    private void saveActionDoomNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("3", LEPRECHAUN.getName(), typeList, leprechaunSetup, true);
        saveCard("5", CURSED_VILLAGE.getName(), typeList, hexSetup, true);
    }

    private void saveActionAttackDoomCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(attackCard);
        typeList.addAll(doomCard);

        saveActionAttackDoomNocturneCards();
    }

    private void saveActionAttackDoomNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("4", SKULK.getName(), typeList, hexSetup, true);
        saveCard("5", TORMENTOR.getName(), typeList, tormentorSetup, true);
    }

    private void saveTreasureAttackFateCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(attackCard);
        typeList.addAll(fateCard);

        saveTreasureAttackFateNocturneCards();
    }

    private void saveTreasureAttackFateNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("5", IDOL.getName(), typeList, boonSetup, true);
    }

    private void saveNightAttackDoomCards() {
        typeList.clear();
        typeList.addAll(nightCard);
        typeList.addAll(attackCard);
        typeList.addAll(doomCard);

        saveNightAttackDoomNocturneCards();
    }

    private void saveNightAttackDoomNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("5", VAMPIRE.getName(), typeList, vampireSetup, true);
    }

    private void saveActionNightAttackDoomCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(nightCard);
        typeList.addAll(attackCard);
        typeList.addAll(doomCard);

        saveActionNightAttackDoomNocturneCards();
    }

    private void saveActionNightAttackDoomNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("5", WEREWOLF.getName(), typeList, hexSetup, true);
    }

    private void saveNightDurationAttackCards() {
        typeList.clear();
        typeList.addAll(nightCard);
        typeList.addAll(durationCard);
        typeList.addAll(attackCard);

        saveNightDurationAttackNocturneCards();
    }

    private void saveNightDurationAttackNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("6", RAIDER.getName(), typeList, true);
    }

    private void saveTreasureHeirloomCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(heirloomCard);

        saveTreasureHeirloomNocturneCards();
    }

    private void saveTreasureHeirloomNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("0", HAUNTED_MIRROR.getName(), typeList, false);
        saveCard("0", MAGIC_LAMP.getName(), typeList, false);
        saveCard("2", GOAT.getName(), typeList, false);
        saveCard("2", POUCH.getName(), typeList, false);
        saveCard("4", CURSED_GOLD.getName(), typeList, false);
        saveCard("4", LUCKY_COIN.getName(), typeList, false);
    }

    private void saveTreasureVictoryHeirloomCards() {
        typeList.clear();
        typeList.addAll(treasureCard);
        typeList.addAll(victoryCard);
        typeList.addAll(heirloomCard);

        saveTreasureVictoryHeirloomNocturneCards();
    }

    private void saveTreasureVictoryHeirloomNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("2", PASTURE.getName(), typeList, false);
    }

    private void saveActionSpiritCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(spiritCard);

        saveActionSpiritNocturneCards();
    }

    private void saveActionSpiritNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("0*", WILL_O_WISP.getName(), typeList, false);
        saveCard("2*", IMP.getName(), typeList, false);
    }

    private void saveActionZombieCards() {
        typeList.clear();
        typeList.addAll(actionCard);
        typeList.addAll(zombieCard);

        saveActionZombieNocturneCards();
    }

    private void saveActionZombieNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("3", ZOMBIE_APPRENTICE.getName(), typeList, false);
        saveCard("3", ZOMBIE_MASON.getName(), typeList, false);
        saveCard("3", ZOMBIE_SPY.getName(), typeList, false);
    }

    private void saveNightDurationSpiritCards() {
        typeList.clear();
        typeList.addAll(nightCard);
        typeList.addAll(durationCard);
        typeList.addAll(spiritCard);

        saveNightDurationSpiritNocturneCards();
    }

    private void saveNightDurationSpiritNocturneCards() {
        box = NOCTURNE.getName();

        saveCard("4*", GHOST.getName(), typeList, false);
    }

    private void saveEvents() {
        saveAdventuresEvents();

        saveEmpiresEvents();
    }

    private void saveAdventuresEvents() {
        box = ADVENTURES.getName();

        saveEvent("0", ALMS.getName());
        saveEvent("0", BORROW.getName(), minusCardSetup);
        saveEvent("0", QUEST.getName());
        saveEvent("1", SAVE.getName());
        saveEvent("2", SCOUTING_PARTY.getName());
        saveEvent("2", TRAVELLING_FAIR.getName());
        saveEvent("3", BONFIRE.getName());
        saveEvent("3", EXPEDITION.getName());
        saveEvent("3", FERRY.getName(), minusCostSetup);
        saveEvent("3", PLAN.getName(), trashTokenSetup);
        saveEvent("4", MISSION.getName());
        saveEvent("4", PILGRIMAGE.getName(), journeySetup);
        saveEvent("5", BALL.getName(), minusCoinSetup);
        saveEvent("5", RAID.getName(), minusCardSetup);
        saveEvent("5", SEAWAY.getName(), plusBuySetup);
        saveEvent("5", TRADE.getName());
        saveEvent("6", LOST_ARTS.getName(), plusActionSetup);
        saveEvent("6", TRAINING.getName(), plusCoinSetup);
        saveEvent("7", INHERITANCE.getName(), estateTokenSetup);
        saveEvent("8", PATHFINDING.getName(), plusCardSetup);
    }

    private void saveEmpiresEvents() {
        box = EMPIRES.getName();

        saveEvent("5 Debt", TRIUMPH.getName(), debtAndVictorySetup);
        saveEvent("8 Debt", ANNEX.getName(), debtTokenSetup);
        saveEvent("8 Debt", DONATE.getName(), debtTokenSetup);
        saveEvent("0", ADVANCE.getName());
        saveEvent("2", DELVE.getName());
        saveEvent("2", TAX.getName(), taxSetup);
        saveEvent("3", BANQUET.getName());
        saveEvent("4", RITUAL.getName(), victoryTokenSetup);
        saveEvent("4", SALT_THE_EARTH.getName(), victoryTokenSetup);
        saveEvent("4 & 3 Debt", WEDDING.getName(), debtAndVictorySetup);
        saveEvent("5", WINDFALL.getName());
        saveEvent("6", CONQUEST.getName(), victoryTokenSetup);
        saveEvent("14", DOMINATE.getName(), victoryTokenSetup);
    }

    private void saveLandmarks() {
        saveEmpiresLandmarks();
    }

    private void saveEmpiresLandmarks() {
        box = EMPIRES.getName();

        saveLandmark(AQUEDUCT.getName(), aqueductSetup);
        saveLandmark(ARENA.getName(), arenaSetup);
        saveLandmark(BANDIT_FORT.getName());
        saveLandmark(BASILICA.getName(), basilicaSetup);
        saveLandmark(BATHS.getName(), bathsSetup);
        saveLandmark(BATTLEFIELD.getName(), battlefieldSetup);
        saveLandmark(COLONNADE.getName(), colonnadeSetup);
        saveLandmark(DEFILED_SHRINE.getName(), defiledShrineSetup);
        saveLandmark(FOUNTAIN.getName());
        saveLandmark(KEEP.getName());
        saveLandmark(LABYRINTH.getName(), labyrinthSetup);
        saveLandmark(MOUNTAIN_PASS.getName(), debtAndVictorySetup);
        saveLandmark(MUSEUM.getName());
        saveLandmark(OBELISK.getName(), obeliskSetup);
        saveLandmark(ORCHARD.getName());
        saveLandmark(PALACE.getName());
        saveLandmark(TOMB.getName(), victoryTokenSetup);
        saveLandmark(TOWER.getName());
        saveLandmark(TRIUMPHAL_ARCH.getName());
        saveLandmark(WALL.getName());
        saveLandmark(WOLF_DEN.getName());
    }

    private void saveBoons() {
        saveNocturneBoons();
    }

    private void saveNocturneBoons() {
        box = NOCTURNE.getName();

        saveBoon(THE_EARTHS_GIFT.getName());
        saveBoon(THE_FIELDS_GIFT.getName());
        saveBoon(THE_FLAMES_GIFT.getName());
        saveBoon(THE_FORESTS_GIFT.getName());
        saveBoon(THE_MOONS_GIFT.getName());
        saveBoon(THE_MOUNTAINS_GIFT.getName());
        saveBoon(THE_RIVERS_GIFT.getName());
        saveBoon(THE_SEAS_GIFT.getName());
        saveBoon(THE_SKYS_GIFT.getName());
        saveBoon(THE_SUNS_GIFT.getName());
        saveBoon(THE_SWAMPS_GIFT.getName(), willowispSetup);
        saveBoon(THE_WINDS_GIFT.getName());
    }

    private void saveHexes() {
        saveNocturneHexes();
    }

    private void saveNocturneHexes() {
        box = NOCTURNE.getName();

        saveHex(BAD_OMENS.getName());
        saveHex(DELUSION.getName(), delusionEnvySetup);
        saveHex(ENVY.getName(), delusionEnvySetup);
        saveHex(FAMINE.getName());
        saveHex(FEAR.getName());
        saveHex(GREED.getName());
        saveHex(HAUNTING.getName());
        saveHex(LOCUSTS.getName());
        saveHex(MISERY.getName(), miserySetup);
        saveHex(PLAGUE.getName());
        saveHex(POVERTY.getName());
        saveHex(WAR.getName());
    }

    private void saveStates() {
        saveNocturneStates();
    }

    private void saveNocturneStates() {
        box = NOCTURNE.getName();

        saveState(DELUDED.getName());
        saveState(ENVIOUS.getName());
        saveState(MISERABLE.getName());
        saveState(TWICE_MISERABLE.getName());
        saveState(LOST_IN_THE_WOODS.getName());
    }
}