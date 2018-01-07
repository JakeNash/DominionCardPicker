package picker.kingdom;

import picker.card.Card;
import picker.card.CardName;
import picker.card.CardRepository;
import picker.event.Event;
import picker.event.EventRepository;
import picker.landmark.Landmark;
import picker.landmark.LandmarkRepository;

import java.util.ArrayList;
import java.util.List;

import static picker.boon.BoonName.*;
import static picker.card.CardName.*;
import static picker.event.EventName.*;
import static picker.kingdom.KingdomName.*;
import static picker.landmark.LandmarkName.*;

public class KingdomSorter {

    private final KingdomRepository kingdomRepository;
    private final CardRepository cardRepository;
    private final EventRepository eventRepository;
    private final LandmarkRepository landmarkRepository;

    private List<String> cardList;
    private List<String> eventList;
    private List<String> landmarkList;
    private List<String> boxList;
    private List<String> otherSetupList;

    public KingdomSorter(KingdomRepository kingdomRepository, CardRepository cardRepository, EventRepository eventRepository, LandmarkRepository landmarkRepository) {
        this.kingdomRepository = kingdomRepository;
        this.cardRepository = cardRepository;
        this.eventRepository = eventRepository;
        this.landmarkRepository = landmarkRepository;
        cardList = new ArrayList<>();
        eventList = new ArrayList<>();
        landmarkList = new ArrayList<>();
        boxList = new ArrayList<>();
        otherSetupList = new ArrayList<>();
    }

    public void createKingdoms() {
        addBasicCards();

        addOldDominionKingdoms();
        addNewDominionKingdoms();

        addOldIntrigueKingdoms();
        addNewIntrigueKingdoms();
        addOldIntrigueAndDominionKingdoms();
        addNewIntrigueAndDominionKingdoms();

        addSeasideKingdoms();
        addOldSeasideAndDominionKingdoms();
        addNewSeasideAndDominionKingdoms();
        addSeasideAndIntrigueKingdoms();

        addOldAlchemyAndDominionKingdoms();
        addNewAlchemyAndDominionKingdoms();
        addOldAlchemyAndIntrigueKingdoms();
        addNewAlchemyAndIntrigueKingdoms();
        addAlchemyAndSeasideKingdoms();

        addProsperityKingdoms();
        addOldProsperityAndDominionKingdoms();
        addNewProsperityAndDominionKingdoms();
        addOldProsperityAndIntrigueKingdoms();
        addNewProsperityAndIntrigueKingdoms();
        addProsperityAndSeasideKingdoms();
        addProsperityAndAlchemyKingdoms();

        addOldCornucopiaAndDominionKingdoms();
        addNewCornucopiaAndDominionKingdoms();
        addOldCornucopiaAndIntrigueKingdoms();
        addNewCornucopiaAndIntrigueKingdoms();
        addCornucopiaAndSeasideKingdoms();
        addCornucopiaAndProsperityKingdoms();

        addHinterlandsKingdoms();
        addOldHinterlandsAndDominionKingdoms();
        addNewHinterlandsAndDominionKingdoms();
        addOldHinterlandsAndIntrigueKingdoms();
        addNewHinterlandsAndIntrigueKingdoms();
        addHinterlandsAndSeasideKingdoms();
        addHinterlandsAndAlchemyKingdoms();
        addHinterlandsAndProsperityKingdoms();
        addHinterlandsAndCornucopiaKingdoms();

        addDarkAgesKingdoms();
        addDarkAgesAndDominionKingdoms();
        addOldDarkAgesAndIntrigueKingdoms();
        addNewDarkAgesAndIntrigueKingdoms();
        addDarkAgesAndSeasideKingdoms();
        addDarkAgesAndAlchemyKingdoms();
        addDarkAgesAndProsperityKingdoms();
        addDarkAgesAndCornucopiaKingdoms();
        addDarkAgesAndHinterlandsKingdoms();

        addOldGuildsAndDominionKingdoms();
        addNewGuildsAndDominionKingdoms();
        addOldGuildsAndIntrigueKingdoms();
        addNewGuildsAndIntrigueKingdoms();
        addGuildsAndSeasideKingdoms();
        addGuildsAndProsperityKingdoms();
        addGuildsAndHinterlandsKingdoms();
        addGuildsAndDarkAgesKingdoms();

        addAdventuresKingdoms();
        addOldAdventuresAndDominionKingdoms();
        addNewAdventuresAndDominionKingdoms();
        addOldAdventuresAndIntrigueKingdoms();
        addNewAdventuresAndIntrigueKingdoms();
        addAdventuresAndSeasideKingdoms();
        addAdventuresAndAlchemyKingdoms();
        addAdventuresAndProsperityKingdoms();
        addAdventuresAndCornucopiaKingdoms();
        addAdventuresAndHinterlandsKingdoms();
        addAdventuresAndDarkAgesKingdoms();
        addAdventuresAndGuildsKingdoms();

        addEmpiresKingdoms();
        addEmpiresAndDominionKingdoms();
        addEmpiresAndIntrigueKingdoms();
        addEmpiresAndSeasideKingdoms();
        addEmpiresAndAlchemyKingdoms();
        addEmpiresAndProsperityKingdoms();
        addEmpiresAndCornucopiaKingdoms();
        addEmpiresAndHinterlandsKingdoms();
        addEmpiresAndDarkAgesKingdoms();
        addEmpiresAndGuildsKingdoms();
        addEmpiresAndAdventuresKingdoms();

        addNocturneKingdoms();
        addNocturneAndDominionKingdoms();
        addNocturneAndIntrigueKingdoms();
        addNocturneAndSeasideKingdoms();
        addNocturneAndAlchemyKingdoms();
        addNocturneAndProsperityKingdoms();
        addNocturneAndCornucopiaKingdoms();
        addNocturneAndHinterlandsKingdoms();
        addNocturneAndDarkAgesKingdoms();
        addNocturneAndGuildsKingdoms();
        addNocturneAndAdventuresKingdoms();
        addNocturneAndEmpiresKingdoms();
    }

    private void extractCardInfo() {
        for (String card : cardList) {
            if (!card.startsWith("Boon: ")) {
                if (card.startsWith("Bane: ")) {
                    card = card.substring(6);
                }
                Card foundCard = cardRepository.findByName(card);
                String box = foundCard.getBox();
                if (!boxList.contains(box)) {
                    boxList.add(box);
                }
                List<String> otherSetup = foundCard.getOtherSetup();
                if (otherSetup != null) {
                    for (String other : otherSetup) {
                        if (!otherSetupList.contains(other)) {
                            otherSetupList.add(other);
                        }
                    }
                }
            }
        }
    }

    private void extractEventInfo() {
        for (String event : eventList) {
            Event foundEvent = eventRepository.findByName(event);
            String box = foundEvent.getBox();
            if (!boxList.contains(box)) {
                boxList.add(box);
            }
            List<String> setup = foundEvent.getSetup();
            if (setup != null) {
                for (String set : setup) {
                    if (!otherSetupList.contains(set)) {
                        otherSetupList.add(set);
                    }
                }
            }
        }
    }

    private void extractLandmarkInfo() {
        for (String landmark : landmarkList) {
            Landmark foundLandmark = landmarkRepository.findByName(landmark);
            String box = foundLandmark.getBox();
            if (!boxList.contains(box)) {
                boxList.add(box);
            }
            List<String> setup = foundLandmark.getSetup();
            if (setup != null) {
                for (String set : setup) {
                    if (!otherSetupList.contains(set)) {
                        otherSetupList.add(set);
                    }
                }
            }
        }
    }

    private void saveKingdom(String name) {
        extractCardInfo();
        extractEventInfo();
        extractLandmarkInfo();
        kingdomRepository.save(new Kingdom(name, cardList, eventList, landmarkList, boxList, otherSetupList));
        cardList.clear();
        eventList.clear();
        landmarkList.clear();
        boxList.clear();
        otherSetupList.clear();
    }

    private void addBasicCards() {
        cardList.add(COPPER.getName());
        cardList.add(CURSE.getName());
        cardList.add(DUCHY.getName());
        cardList.add(ESTATE.getName());
        cardList.add(GOLD.getName());
        cardList.add(PROVINCE.getName());
        cardList.add(SILVER.getName());

        saveKingdom(BASIC.getName());
    }

    private void addOldDominionKingdoms() {
        // First Game
        cardList.add(CELLAR.getName());
        cardList.add(MARKET.getName());
        cardList.add(MILITIA.getName());
        cardList.add(MINE.getName());
        cardList.add(MOAT.getName());
        cardList.add(REMODEL.getName());
        cardList.add(SMITHY.getName());
        cardList.add(VILLAGE.getName());
        cardList.add(WOODCUTTER.getName());
        cardList.add(WORKSHOP.getName());

        saveKingdom(FIRST_GAME_1.getName());

        // Big Money
        cardList.add(ADVENTURER.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(CHANCELLOR.getName());
        cardList.add(CHAPEL.getName());
        cardList.add(FEAST.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(MARKET.getName());
        cardList.add(MINE.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(THRONE_ROOM.getName());

        saveKingdom(BIG_MONEY.getName());

        // Interaction
        cardList.add(BUREAUCRAT.getName());
        cardList.add(CHANCELLOR.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(LIBRARY.getName());
        cardList.add(MILITIA.getName());
        cardList.add(MOAT.getName());
        cardList.add(SPY.getName());
        cardList.add(THIEF.getName());
        cardList.add(VILLAGE.getName());

        saveKingdom(INTERACTION.getName());

        // Size Distortion
        cardList.add(CELLAR.getName());
        cardList.add(CHAPEL.getName());
        cardList.add(FEAST.getName());
        cardList.add(GARDENS.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(THIEF.getName());
        cardList.add(VILLAGE.getName());
        cardList.add(WITCH.getName());
        cardList.add(WOODCUTTER.getName());
        cardList.add(WORKSHOP.getName());

        saveKingdom(SIZE_DISTORTION_1.getName());

        // Village Square
        cardList.add(BUREAUCRAT.getName());
        cardList.add(CELLAR.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(LIBRARY.getName());
        cardList.add(MARKET.getName());
        cardList.add(REMODEL.getName());
        cardList.add(SMITHY.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(VILLAGE.getName());
        cardList.add(WOODCUTTER.getName());

        saveKingdom(VILLAGE_SQUARE.getName());
    }

    private void addNewDominionKingdoms() {
        // First Game
        cardList.add(CELLAR.getName());
        cardList.add(MARKET.getName());
        cardList.add(MERCHANT.getName());
        cardList.add(MILITIA.getName());
        cardList.add(MINE.getName());
        cardList.add(MOAT.getName());
        cardList.add(REMODEL.getName());
        cardList.add(SMITHY.getName());
        cardList.add(VILLAGE.getName());
        cardList.add(WORKSHOP.getName());

        saveKingdom(FIRST_GAME_2.getName());

        // Size Distortion
        cardList.add(ARTISAN.getName());
        cardList.add(BANDIT.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(CHAPEL.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(GARDENS.getName());
        cardList.add(SENTRY.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(WITCH.getName());
        cardList.add(WORKSHOP.getName());

        saveKingdom(SIZE_DISTORTION_2.getName());

        // Deck Top
        cardList.add(ARTISAN.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(HARBINGER.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(SENTRY.getName());
        cardList.add(VASSAL.getName());
        cardList.add(VILLAGE.getName());

        saveKingdom(DECK_TOP.getName());

        // Sleight of Hand
        cardList.add(CELLAR.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(GARDENS.getName());
        cardList.add(LIBRARY.getName());
        cardList.add(HARBINGER.getName());
        cardList.add(MILITIA.getName());
        cardList.add(POACHER.getName());
        cardList.add(SMITHY.getName());
        cardList.add(THRONE_ROOM.getName());

        saveKingdom(SLEIGHT_OF_HAND.getName());

        // Improvements
        cardList.add(ARTISAN.getName());
        cardList.add(CELLAR.getName());
        cardList.add(MARKET.getName());
        cardList.add(MERCHANT.getName());
        cardList.add(MINE.getName());
        cardList.add(MOAT.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(POACHER.getName());
        cardList.add(REMODEL.getName());
        cardList.add(WITCH.getName());

        saveKingdom(IMPROVEMENTS.getName());

        // Silver & Gold
        cardList.add(BANDIT.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(CHAPEL.getName());
        cardList.add(HARBINGER.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(MERCHANT.getName());
        cardList.add(MINE.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(VASSAL.getName());

        saveKingdom(SILVER_AND_GOLD.getName());
    }

    private void addOldIntrigueKingdoms() {
        // Victory Dance
        cardList.add(BRIDGE.getName());
        cardList.add(DUKE.getName());
        cardList.add(GREAT_HALL.getName());
        cardList.add(HAREM.getName());
        cardList.add(IRONWORKS.getName());
        cardList.add(MASQUERADE.getName());
        cardList.add(NOBLES.getName());
        cardList.add(PAWN.getName());
        cardList.add(SCOUT.getName());
        cardList.add(UPGRADE.getName());

        saveKingdom(VICTORY_DANCE_1.getName());

        // Secret Schemes
        cardList.add(CONSPIRATOR.getName());
        cardList.add(HAREM.getName());
        cardList.add(IRONWORKS.getName());
        cardList.add(PAWN.getName());
        cardList.add(SABOTEUR.getName());
        cardList.add(SHANTY_TOWN.getName());
        cardList.add(STEWARD.getName());
        cardList.add(SWINDLER.getName());
        cardList.add(TRADING_POST.getName());
        cardList.add(TRIBUTE.getName());

        saveKingdom(SECRET_SCHEMES.getName());

        // Best Wishes
        cardList.add(COPPERSMITH.getName());
        cardList.add(COURTYARD.getName());
        cardList.add(MASQUERADE.getName());
        cardList.add(SCOUT.getName());
        cardList.add(SHANTY_TOWN.getName());
        cardList.add(STEWARD.getName());
        cardList.add(TORTURER.getName());
        cardList.add(TRADING_POST.getName());
        cardList.add(UPGRADE.getName());
        cardList.add(WISHING_WELL.getName());

        saveKingdom(BEST_WISHES_1.getName());
    }

    private void addNewIntrigueKingdoms() {
        // Victory Dance
        cardList.add(BARON.getName());
        cardList.add(COURTIER.getName());
        cardList.add(DUKE.getName());
        cardList.add(HAREM.getName());
        cardList.add(IRONWORKS.getName());
        cardList.add(MASQUERADE.getName());
        cardList.add(MILL.getName());
        cardList.add(NOBLES.getName());
        cardList.add(PATROL.getName());
        cardList.add(REPLACE.getName());

        saveKingdom(VICTORY_DANCE_2.getName());

        // The Plot Thickens
        cardList.add(CONSPIRATOR.getName());
        cardList.add(IRONWORKS.getName());
        cardList.add(LURKER.getName());
        cardList.add(PAWN.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(SECRET_PASSAGE.getName());
        cardList.add(STEWARD.getName());
        cardList.add(SWINDLER.getName());
        cardList.add(TORTURER.getName());
        cardList.add(TRADING_POST.getName());

        saveKingdom(THE_PLOT_THICKENS.getName());

        // Best Wishes
        cardList.add(BARON.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(COURTYARD.getName());
        cardList.add(DIPLOMAT.getName());
        cardList.add(DUKE.getName());
        cardList.add(SECRET_PASSAGE.getName());
        cardList.add(SHANTY_TOWN.getName());
        cardList.add(TORTURER.getName());
        cardList.add(UPGRADE.getName());
        cardList.add(WISHING_WELL.getName());

        saveKingdom(BEST_WISHES_2.getName());
    }

    private void addOldIntrigueAndDominionKingdoms() {
        // Underlings
        cardList.add(BARON.getName());
        cardList.add(CELLAR.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(LIBRARY.getName());
        cardList.add(MASQUERADE.getName());
        cardList.add(MINION.getName());
        cardList.add(NOBLES.getName());
        cardList.add(PAWN.getName());
        cardList.add(STEWARD.getName());
        cardList.add(WITCH.getName());

        saveKingdom(UNDERLINGS_1.getName());

        // Hand Madness
        cardList.add(BUREAUCRAT.getName());
        cardList.add(CHANCELLOR.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(COURTYARD.getName());
        cardList.add(MINE.getName());
        cardList.add(MILITIA.getName());
        cardList.add(MINION.getName());
        cardList.add(NOBLES.getName());
        cardList.add(STEWARD.getName());
        cardList.add(TORTURER.getName());

        saveKingdom(HAND_MADNESS.getName());

        // Deconstruction
        cardList.add(BRIDGE.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(REMODEL.getName());
        cardList.add(SABOTEUR.getName());
        cardList.add(SECRET_CHAMBER.getName());
        cardList.add(SPY.getName());
        cardList.add(SWINDLER.getName());
        cardList.add(THIEF.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(TORTURER.getName());

        saveKingdom(DECONSTRUCTION_1.getName());
    }

    private void addNewIntrigueAndDominionKingdoms() {
        // Underlings
        cardList.add(CELLAR.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(LIBRARY.getName());
        cardList.add(SENTRY.getName());
        cardList.add(VASSAL.getName());
        cardList.add(COURTIER.getName());
        cardList.add(DIPLOMAT.getName());
        cardList.add(MINION.getName());
        cardList.add(NOBLES.getName());
        cardList.add(PAWN.getName());

        saveKingdom(UNDERLINGS_2.getName());

        // Grand Scheme
        cardList.add(ARTISAN.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(MARKET.getName());
        cardList.add(MILITIA.getName());
        cardList.add(WORKSHOP.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(MILL.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(PATROL.getName());
        cardList.add(SHANTY_TOWN.getName());

        saveKingdom(GRAND_SCHEME.getName());

        // Deconstruction
        cardList.add(BANDIT.getName());
        cardList.add(MINE.getName());
        cardList.add(REMODEL.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(VILLAGE.getName());
        cardList.add(DIPLOMAT.getName());
        cardList.add(HAREM.getName());
        cardList.add(LURKER.getName());
        cardList.add(REPLACE.getName());
        cardList.add(SWINDLER.getName());

        saveKingdom(DECONSTRUCTION_2.getName());
    }

    private void addSeasideKingdoms() {
        // High Seas
        cardList.add(BAZAAR.getName());
        cardList.add(CARAVAN.getName());
        cardList.add(EMBARGO.getName());
        cardList.add(EXPLORER.getName());
        cardList.add(HAVEN.getName());
        cardList.add(ISLAND.getName());
        cardList.add(LOOKOUT.getName());
        cardList.add(PIRATE_SHIP.getName());
        cardList.add(SMUGGLERS.getName());
        cardList.add(WHARF.getName());

        saveKingdom(HIGH_SEAS.getName());

        // Buried Treasure
        cardList.add(AMBASSADOR.getName());
        cardList.add(CUTPURSE.getName());
        cardList.add(FISHING_VILLAGE.getName());
        cardList.add(LIGHTHOUSE.getName());
        cardList.add(OUTPOST.getName());
        cardList.add(PEARL_DIVER.getName());
        cardList.add(TACTICIAN.getName());
        cardList.add(TREASURE_MAP.getName());
        cardList.add(WAREHOUSE.getName());
        cardList.add(WHARF.getName());

        saveKingdom(BURIED_TREASURE.getName());

        // Shipwrecks
        cardList.add(GHOST_SHIP.getName());
        cardList.add(MERCHANT_SHIP.getName());
        cardList.add(NATIVE_VILLAGE.getName());
        cardList.add(NAVIGATOR.getName());
        cardList.add(PEARL_DIVER.getName());
        cardList.add(SALVAGER.getName());
        cardList.add(SEA_HAG.getName());
        cardList.add(SMUGGLERS.getName());
        cardList.add(TREASURY.getName());
        cardList.add(WAREHOUSE.getName());

        saveKingdom(SHIPWRECKS.getName());
    }

    private void addOldSeasideAndDominionKingdoms() {
        // Give and Take
        cardList.add(AMBASSADOR.getName());
        cardList.add(FISHING_VILLAGE.getName());
        cardList.add(HAVEN.getName());
        cardList.add(ISLAND.getName());
        cardList.add(LIBRARY.getName());
        cardList.add(MARKET.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(SALVAGER.getName());
        cardList.add(SMUGGLERS.getName());
        cardList.add(WITCH.getName());

        saveKingdom(GIVE_AND_TAKE.getName());

        // Reach for Tomorrow
        cardList.add(ADVENTURER.getName());
        cardList.add(CELLAR.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(CUTPURSE.getName());
        cardList.add(GHOST_SHIP.getName());
        cardList.add(LOOKOUT.getName());
        cardList.add(SEA_HAG.getName());
        cardList.add(SPY.getName());
        cardList.add(TREASURE_MAP.getName());
        cardList.add(VILLAGE.getName());

        saveKingdom(REACH_FOR_TOMORROW_1.getName());

        // Repetition
        cardList.add(CARAVAN.getName());
        cardList.add(CHANCELLOR.getName());
        cardList.add(EXPLORER.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(MILITIA.getName());
        cardList.add(OUTPOST.getName());
        cardList.add(PEARL_DIVER.getName());
        cardList.add(PIRATE_SHIP.getName());
        cardList.add(TREASURY.getName());
        cardList.add(WORKSHOP.getName());

        saveKingdom(REPETITION_1.getName());
    }

    private void addNewSeasideAndDominionKingdoms() {
        // Reach for Tomorrow
        cardList.add(ARTISAN.getName());
        cardList.add(CELLAR.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(VASSAL.getName());
        cardList.add(VILLAGE.getName());
        cardList.add(CUTPURSE.getName());
        cardList.add(GHOST_SHIP.getName());
        cardList.add(LOOKOUT.getName());
        cardList.add(SEA_HAG.getName());
        cardList.add(TREASURE_MAP.getName());

        saveKingdom(REACH_FOR_TOMORROW_2.getName());

        // Repetition
        cardList.add(FESTIVAL.getName());
        cardList.add(HARBINGER.getName());
        cardList.add(MILITIA.getName());
        cardList.add(WORKSHOP.getName());
        cardList.add(CARAVAN.getName());
        cardList.add(EXPLORER.getName());
        cardList.add(OUTPOST.getName());
        cardList.add(PEARL_DIVER.getName());
        cardList.add(PIRATE_SHIP.getName());
        cardList.add(TREASURY.getName());

        saveKingdom(REPETITION_2.getName());
    }

    private void addSeasideAndIntrigueKingdoms() {
        // A Star to Steer By
        cardList.add(SECRET_PASSAGE.getName());
        cardList.add(DIPLOMAT.getName());
        cardList.add(SWINDLER.getName());
        cardList.add(WISHING_WELL.getName());
        cardList.add(COURTIER.getName());
        cardList.add(LOOKOUT.getName());
        cardList.add(TREASURE_MAP.getName());
        cardList.add(GHOST_SHIP.getName());
        cardList.add(HAVEN.getName());
        cardList.add(OUTPOST.getName());

        saveKingdom(A_STAR_TO_STEER_BY.getName());

        // Shore Patrol
        cardList.add(PATROL.getName());
        cardList.add(REPLACE.getName());
        cardList.add(SHANTY_TOWN.getName());
        cardList.add(TRADING_POST.getName());
        cardList.add(PAWN.getName());
        cardList.add(ISLAND.getName());
        cardList.add(WHARF.getName());
        cardList.add(CUTPURSE.getName());
        cardList.add(LIGHTHOUSE.getName());
        cardList.add(WAREHOUSE.getName());

        saveKingdom(SHORE_PATROL.getName());

        // Bridge Crossing
        cardList.add(LURKER.getName());
        cardList.add(NOBLES.getName());
        cardList.add(DUKE.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(SALVAGER.getName());
        cardList.add(EMBARGO.getName());
        cardList.add(SMUGGLERS.getName());
        cardList.add(NATIVE_VILLAGE.getName());
        cardList.add(TREASURY.getName());

        saveKingdom(BRIDGE_CROSSING.getName());
    }

    private void addOldAlchemyAndDominionKingdoms() {
        // Forbidden Arts
        cardList.add(APPRENTICE.getName());
        cardList.add(FAMILIAR.getName());
        cardList.add(POSSESSION.getName());
        cardList.add(UNIVERSITY.getName());
        cardList.add(CELLAR.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(GARDENS.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(THIEF.getName());
        cardList.add(THRONE_ROOM.getName());

        saveKingdom(FORBIDDEN_ARTS_1.getName());

        // Potion Mixers
        cardList.add(ALCHEMIST.getName());
        cardList.add(APOTHECARY.getName());
        cardList.add(GOLEM.getName());
        cardList.add(HERBALIST.getName());
        cardList.add(TRANSMUTE.getName());
        cardList.add(CELLAR.getName());
        cardList.add(CHANCELLOR.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(MILITIA.getName());
        cardList.add(SMITHY.getName());

        saveKingdom(POTION_MIXERS_1.getName());

        // Chemistry Lesson
        cardList.add(ALCHEMIST.getName());
        cardList.add(GOLEM.getName());
        cardList.add(PHILOSOPHERS_STONE.getName());
        cardList.add(UNIVERSITY.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(MARKET.getName());
        cardList.add(MOAT.getName());
        cardList.add(REMODEL.getName());
        cardList.add(WITCH.getName());
        cardList.add(WOODCUTTER.getName());

        saveKingdom(CHEMISTRY_LESSON_1.getName());
    }

    private void addNewAlchemyAndDominionKingdoms() {
        // Forbidden Arts
        cardList.add(BANDIT.getName());
        cardList.add(CELLAR.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(GARDENS.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(APPRENTICE.getName());
        cardList.add(FAMILIAR.getName());
        cardList.add(POSSESSION.getName());
        cardList.add(UNIVERSITY.getName());

        saveKingdom(FORBIDDEN_ARTS_2.getName());

        // Potion Mixers
        cardList.add(CELLAR.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(MILITIA.getName());
        cardList.add(POACHER.getName());
        cardList.add(SMITHY.getName());
        cardList.add(ALCHEMIST.getName());
        cardList.add(APOTHECARY.getName());
        cardList.add(GOLEM.getName());
        cardList.add(HERBALIST.getName());
        cardList.add(TRANSMUTE.getName());

        saveKingdom(POTION_MIXERS_2.getName());

        // Chemistry Lesson
        cardList.add(BUREAUCRAT.getName());
        cardList.add(MARKET.getName());
        cardList.add(MOAT.getName());
        cardList.add(REMODEL.getName());
        cardList.add(VASSAL.getName());
        cardList.add(WITCH.getName());
        cardList.add(ALCHEMIST.getName());
        cardList.add(GOLEM.getName());
        cardList.add(PHILOSOPHERS_STONE.getName());
        cardList.add(UNIVERSITY.getName());

        saveKingdom(CHEMISTRY_LESSON_2.getName());
    }

    private void addOldAlchemyAndIntrigueKingdoms() {
        // Servants
        cardList.add(GOLEM.getName());
        cardList.add(POSSESSION.getName());
        cardList.add(SCRYING_POOL.getName());
        cardList.add(TRANSMUTE.getName());
        cardList.add(VINEYARD.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(GREAT_HALL.getName());
        cardList.add(MINION.getName());
        cardList.add(PAWN.getName());
        cardList.add(STEWARD.getName());

        saveKingdom(SERVANTS_1.getName());

        // Secret Research
        cardList.add(FAMILIAR.getName());
        cardList.add(HERBALIST.getName());
        cardList.add(PHILOSOPHERS_STONE.getName());
        cardList.add(UNIVERSITY.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(MASQUERADE.getName());
        cardList.add(MINION.getName());
        cardList.add(NOBLES.getName());
        cardList.add(SHANTY_TOWN.getName());
        cardList.add(TORTURER.getName());

        saveKingdom(SECRET_RESEARCH.getName());

        // Pools, Tools, and Fools
        cardList.add(APOTHECARY.getName());
        cardList.add(APPRENTICE.getName());
        cardList.add(GOLEM.getName());
        cardList.add(SCRYING_POOL.getName());
        cardList.add(BARON.getName());
        cardList.add(COPPERSMITH.getName());
        cardList.add(IRONWORKS.getName());
        cardList.add(NOBLES.getName());
        cardList.add(TRADING_POST.getName());
        cardList.add(WISHING_WELL.getName());

        saveKingdom(POOLS_TOOLS_AND_FOOLS_1.getName());
    }

    private void addNewAlchemyAndIntrigueKingdoms() {
        // Servants
        cardList.add(CONSPIRATOR.getName());
        cardList.add(MILL.getName());
        cardList.add(MINION.getName());
        cardList.add(PAWN.getName());
        cardList.add(STEWARD.getName());
        cardList.add(GOLEM.getName());
        cardList.add(POSSESSION.getName());
        cardList.add(SCRYING_POOL.getName());
        cardList.add(TRANSMUTE.getName());
        cardList.add(VINEYARD.getName());

        saveKingdom(SERVANTS_2.getName());

        // Pools, Tools, and Fools
        cardList.add(BARON.getName());
        cardList.add(IRONWORKS.getName());
        cardList.add(LURKER.getName());
        cardList.add(NOBLES.getName());
        cardList.add(TRADING_POST.getName());
        cardList.add(WISHING_WELL.getName());
        cardList.add(APOTHECARY.getName());
        cardList.add(APPRENTICE.getName());
        cardList.add(GOLEM.getName());
        cardList.add(SCRYING_POOL.getName());

        saveKingdom(POOLS_TOOLS_AND_FOOLS_2.getName());
    }

    private void addAlchemyAndSeasideKingdoms() {
        // Forewarned
        cardList.add(CUTPURSE.getName());
        cardList.add(EMBARGO.getName());
        cardList.add(GHOST_SHIP.getName());
        cardList.add(NATIVE_VILLAGE.getName());
        cardList.add(TREASURE_MAP.getName());
        cardList.add(APOTHECARY.getName());
        cardList.add(GOLEM.getName());
        cardList.add(POSSESSION.getName());
        cardList.add(SCRYING_POOL.getName());
        cardList.add(TRANSMUTE.getName());

        saveKingdom(FOREWARNED.getName());

        // Gummed Up
        cardList.add(AMBASSADOR.getName());
        cardList.add(HAVEN.getName());
        cardList.add(SEA_HAG.getName());
        cardList.add(SMUGGLERS.getName());
        cardList.add(WAREHOUSE.getName());
        cardList.add(APPRENTICE.getName());
        cardList.add(FAMILIAR.getName());
        cardList.add(HERBALIST.getName());
        cardList.add(PHILOSOPHERS_STONE.getName());
        cardList.add(VINEYARD.getName());

        saveKingdom(GUMMED_UP.getName());
    }

    private void addProsperityKingdoms() {
        // Beginners
        cardList.add(BANK.getName());
        cardList.add(COUNTING_HOUSE.getName());
        cardList.add(EXPAND.getName());
        cardList.add(GOONS.getName());
        cardList.add(MONUMENT.getName());
        cardList.add(RABBLE.getName());
        cardList.add(ROYAL_SEAL.getName());
        cardList.add(VENTURE.getName());
        cardList.add(WATCHTOWER.getName());
        cardList.add(WORKERS_VILLAGE.getName());

        saveKingdom(BEGINNERS.getName());

        // Friendly Interactive
        cardList.add(BISHOP.getName());
        cardList.add(CITY.getName());
        cardList.add(CONTRABAND.getName());
        cardList.add(FORGE.getName());
        cardList.add(HOARD.getName());
        cardList.add(PEDDLER.getName());
        cardList.add(ROYAL_SEAL.getName());
        cardList.add(TRADE_ROUTE.getName());
        cardList.add(VAULT.getName());
        cardList.add(WORKERS_VILLAGE.getName());

        saveKingdom(FRIENDLY_INTERACTIVE.getName());

        // Big Actions
        cardList.add(CITY.getName());
        cardList.add(EXPAND.getName());
        cardList.add(GRAND_MARKET.getName());
        cardList.add(KINGS_COURT.getName());
        cardList.add(LOAN.getName());
        cardList.add(MINT.getName());
        cardList.add(QUARRY.getName());
        cardList.add(RABBLE.getName());
        cardList.add(TALISMAN.getName());
        cardList.add(VAULT.getName());

        saveKingdom(BIG_ACTIONS.getName());
    }

    private void addOldProsperityAndDominionKingdoms() {
        // Biggest Money
        cardList.add(BANK.getName());
        cardList.add(GRAND_MARKET.getName());
        cardList.add(MINT.getName());
        cardList.add(ROYAL_SEAL.getName());
        cardList.add(VENTURE.getName());
        cardList.add(ADVENTURER.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(MINE.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(SPY.getName());

        saveKingdom(BIGGEST_MONEY_1.getName());

        // The King's Army
        cardList.add(EXPAND.getName());
        cardList.add(GOONS.getName());
        cardList.add(KINGS_COURT.getName());
        cardList.add(RABBLE.getName());
        cardList.add(VAULT.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(MOAT.getName());
        cardList.add(SPY.getName());
        cardList.add(VILLAGE.getName());

        saveKingdom(THE_KINGS_ARMY_1.getName());

        // The Good Life
        cardList.add(CONTRABAND.getName());
        cardList.add(COUNTING_HOUSE.getName());
        cardList.add(HOARD.getName());
        cardList.add(MONUMENT.getName());
        cardList.add(MOUNTEBANK.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(CELLAR.getName());
        cardList.add(CHANCELLOR.getName());
        cardList.add(GARDENS.getName());
        cardList.add(VILLAGE.getName());

        saveKingdom(THE_GOOD_LIFE_1.getName());
    }

    private void addNewProsperityAndDominionKingdoms() {
        // Biggest Money
        cardList.add(ARTISAN.getName());
        cardList.add(HARBINGER.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(MINE.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(BANK.getName());
        cardList.add(GRAND_MARKET.getName());
        cardList.add(MINT.getName());
        cardList.add(ROYAL_SEAL.getName());
        cardList.add(VENTURE.getName());

        saveKingdom(BIGGEST_MONEY_2.getName());

        // The King's Army
        cardList.add(BUREAUCRAT.getName());
        cardList.add(COUNCIL_ROOM.getName());
        cardList.add(MERCHANT.getName());
        cardList.add(MOAT.getName());
        cardList.add(VILLAGE.getName());
        cardList.add(EXPAND.getName());
        cardList.add(GOONS.getName());
        cardList.add(KINGS_COURT.getName());
        cardList.add(RABBLE.getName());
        cardList.add(VAULT.getName());

        saveKingdom(THE_KINGS_ARMY_2.getName());

        // The Good Life
        cardList.add(ARTISAN.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(CELLAR.getName());
        cardList.add(GARDENS.getName());
        cardList.add(VILLAGE.getName());
        cardList.add(CONTRABAND.getName());
        cardList.add(COUNTING_HOUSE.getName());
        cardList.add(HOARD.getName());
        cardList.add(MONUMENT.getName());
        cardList.add(MOUNTEBANK.getName());

        saveKingdom(THE_GOOD_LIFE_2.getName());
    }

    private void addOldProsperityAndIntrigueKingdoms() {
        // Paths to Victory
        cardList.add(BISHOP.getName());
        cardList.add(COUNTING_HOUSE.getName());
        cardList.add(GOONS.getName());
        cardList.add(MONUMENT.getName());
        cardList.add(PEDDLER.getName());
        cardList.add(BARON.getName());
        cardList.add(HAREM.getName());
        cardList.add(PAWN.getName());
        cardList.add(SHANTY_TOWN.getName());
        cardList.add(UPGRADE.getName());

        saveKingdom(PATHS_TO_VICTORY.getName());

        // All Along the Watchtower
        cardList.add(HOARD.getName());
        cardList.add(TALISMAN.getName());
        cardList.add(TRADE_ROUTE.getName());
        cardList.add(VAULT.getName());
        cardList.add(WATCHTOWER.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(GREAT_HALL.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(PAWN.getName());
        cardList.add(TORTURER.getName());

        saveKingdom(ALL_ALONG_THE_WATCHTOWER_1.getName());

        // Lucky Seven
        cardList.add(BANK.getName());
        cardList.add(EXPAND.getName());
        cardList.add(FORGE.getName());
        cardList.add(KINGS_COURT.getName());
        cardList.add(VAULT.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(COPPERSMITH.getName());
        cardList.add(SWINDLER.getName());
        cardList.add(TRIBUTE.getName());
        cardList.add(WISHING_WELL.getName());

        saveKingdom(LUCKY_SEVEN_1.getName());
    }

    private void addNewProsperityAndIntrigueKingdoms() {
        // All Along the Watchtower
        cardList.add(HOARD.getName());
        cardList.add(TALISMAN.getName());
        cardList.add(TRADE_ROUTE.getName());
        cardList.add(VAULT.getName());
        cardList.add(WATCHTOWER.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(MILL.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(PAWN.getName());
        cardList.add(TORTURER.getName());

        saveKingdom(ALL_ALONG_THE_WATCHTOWER_2.getName());

        // Lucky Seven
        cardList.add(BANK.getName());
        cardList.add(EXPAND.getName());
        cardList.add(FORGE.getName());
        cardList.add(KINGS_COURT.getName());
        cardList.add(VAULT.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(LURKER.getName());
        cardList.add(PATROL.getName());
        cardList.add(SWINDLER.getName());
        cardList.add(WISHING_WELL.getName());

        saveKingdom(LUCKY_SEVEN_2.getName());
    }

    private void addProsperityAndSeasideKingdoms() {
        // Exploding Kingdom
        cardList.add(BISHOP.getName());
        cardList.add(CITY.getName());
        cardList.add(GRAND_MARKET.getName());
        cardList.add(KINGS_COURT.getName());
        cardList.add(QUARRY.getName());
        cardList.add(FISHING_VILLAGE.getName());
        cardList.add(LOOKOUT.getName());
        cardList.add(OUTPOST.getName());
        cardList.add(TACTICIAN.getName());
        cardList.add(WHARF.getName());

        saveKingdom(EXPLODING_KINGDOM.getName());

        // Pirate Bay
        cardList.add(EXPAND.getName());
        cardList.add(HOARD.getName());
        cardList.add(MINT.getName());
        cardList.add(TRADE_ROUTE.getName());
        cardList.add(WATCHTOWER.getName());
        cardList.add(BAZAAR.getName());
        cardList.add(LIGHTHOUSE.getName());
        cardList.add(PIRATE_SHIP.getName());
        cardList.add(SMUGGLERS.getName());
        cardList.add(WAREHOUSE.getName());

        saveKingdom(PIRATE_BAY.getName());
    }

    private void addProsperityAndAlchemyKingdoms() {
        // Counting Contest
        cardList.add(BANK.getName());
        cardList.add(COUNTING_HOUSE.getName());
        cardList.add(HOARD.getName());
        cardList.add(GOONS.getName());
        cardList.add(RABBLE.getName());
        cardList.add(QUARRY.getName());
        cardList.add(PHILOSOPHERS_STONE.getName());
        cardList.add(GOLEM.getName());
        cardList.add(HERBALIST.getName());
        cardList.add(APOTHECARY.getName());

        saveKingdom(COUNTING_CONTEST.getName());

        // Lower Learning
        cardList.add(TALISMAN.getName());
        cardList.add(MINT.getName());
        cardList.add(BISHOP.getName());
        cardList.add(WORKERS_VILLAGE.getName());
        cardList.add(PEDDLER.getName());
        cardList.add(VAULT.getName());
        cardList.add(FAMILIAR.getName());
        cardList.add(APPRENTICE.getName());
        cardList.add(UNIVERSITY.getName());
        cardList.add(VINEYARD.getName());

        saveKingdom(LOWER_LEARNING.getName());
    }

    private void addOldCornucopiaAndDominionKingdoms() {
        // Bounty of the Hunt
        cardList.add(HARVEST.getName());
        cardList.add(HORN_OF_PLENTY.getName());
        cardList.add(HUNTING_PARTY.getName());
        cardList.add(MENAGERIE.getName());
        cardList.add(TOURNAMENT.getName());
        cardList.add(CELLAR.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(MILITIA.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(SMITHY.getName());

        saveKingdom(BOUNTY_OF_THE_HUNT.getName());

        // Bad Omens
        cardList.add(FORTUNE_TELLER.getName());
        cardList.add(HAMLET.getName());
        cardList.add(HORN_OF_PLENTY.getName());
        cardList.add(JESTER.getName());
        cardList.add(REMAKE.getName());
        cardList.add(ADVENTURER.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(SPY.getName());
        cardList.add(THRONE_ROOM.getName());

        saveKingdom(BAD_OMENS_1.getName());

        // The Jester's Workshop
        cardList.add(FAIRGROUNDS.getName());
        cardList.add(FARMING_VILLAGE.getName());
        cardList.add(HORSE_TRADERS.getName());
        cardList.add(JESTER.getName());
        cardList.add(YOUNG_WITCH.getName());
        cardList.add(FEAST.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(MARKET.getName());
        cardList.add(REMODEL.getName());
        cardList.add(WORKSHOP.getName());
        cardList.add("Bane: " + CHANCELLOR.getName());

        saveKingdom(THE_JESTERS_WORKSHOP_1.getName());
    }

    private void addNewCornucopiaAndDominionKingdoms() {
        // Bad Omens
        cardList.add(BUREAUCRAT.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(MERCHANT.getName());
        cardList.add(POACHER.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(FORTUNE_TELLER.getName());
        cardList.add(HAMLET.getName());
        cardList.add(HORN_OF_PLENTY.getName());
        cardList.add(JESTER.getName());
        cardList.add(REMAKE.getName());

        saveKingdom(BAD_OMENS_2.getName());

        // The Jester's Workshop
        cardList.add(ARTISAN.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(MARKET.getName());
        cardList.add(REMODEL.getName());
        cardList.add(WORKSHOP.getName());
        cardList.add(FAIRGROUNDS.getName());
        cardList.add(FARMING_VILLAGE.getName());
        cardList.add(HORSE_TRADERS.getName());
        cardList.add(JESTER.getName());
        cardList.add(YOUNG_WITCH.getName());
        cardList.add("Bane: " + MERCHANT.getName());

        saveKingdom(THE_JESTERS_WORKSHOP_2.getName());
    }

    private void addOldCornucopiaAndIntrigueKingdoms() {
        // Last Laughs
        cardList.add(FARMING_VILLAGE.getName());
        cardList.add(HARVEST.getName());
        cardList.add(HORSE_TRADERS.getName());
        cardList.add(HUNTING_PARTY.getName());
        cardList.add(JESTER.getName());
        cardList.add(MINION.getName());
        cardList.add(NOBLES.getName());
        cardList.add(PAWN.getName());
        cardList.add(STEWARD.getName());
        cardList.add(SWINDLER.getName());

        saveKingdom(LAST_LAUGHS.getName());

        // The Spice of Life
        cardList.add(FAIRGROUNDS.getName());
        cardList.add(HORSE_TRADERS.getName());
        cardList.add(REMAKE.getName());
        cardList.add(TOURNAMENT.getName());
        cardList.add(YOUNG_WITCH.getName());
        cardList.add(COPPERSMITH.getName());
        cardList.add(COURTYARD.getName());
        cardList.add(GREAT_HALL.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(TRIBUTE.getName());
        cardList.add("Bane: " + WISHING_WELL.getName());

        saveKingdom(THE_SPICE_OF_LIFE_1.getName());

        // Small Victories
        cardList.add(FORTUNE_TELLER.getName());
        cardList.add(HAMLET.getName());
        cardList.add(HUNTING_PARTY.getName());
        cardList.add(REMAKE.getName());
        cardList.add(TOURNAMENT.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(DUKE.getName());
        cardList.add(GREAT_HALL.getName());
        cardList.add(HAREM.getName());
        cardList.add(PAWN.getName());

        saveKingdom(SMALL_VICTORIES_1.getName());
    }

    private void addNewCornucopiaAndIntrigueKingdoms() {
        // The Spice of Life
        cardList.add(FAIRGROUNDS.getName());
        cardList.add(HORN_OF_PLENTY.getName());
        cardList.add(REMAKE.getName());
        cardList.add(TOURNAMENT.getName());
        cardList.add(YOUNG_WITCH.getName());
        cardList.add(COURTIER.getName());
        cardList.add(COURTYARD.getName());
        cardList.add(DIPLOMAT.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(REPLACE.getName());
        cardList.add("Bane: " + WISHING_WELL.getName());

        saveKingdom(THE_SPICE_OF_LIFE_2.getName());

        // Small Victories
        cardList.add(FORTUNE_TELLER.getName());
        cardList.add(HAMLET.getName());
        cardList.add(HUNTING_PARTY.getName());
        cardList.add(REMAKE.getName());
        cardList.add(TOURNAMENT.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(DUKE.getName());
        cardList.add(HAREM.getName());
        cardList.add(PAWN.getName());
        cardList.add(SECRET_PASSAGE.getName());

        saveKingdom(SMALL_VICTORIES_2.getName());
    }

    private void addCornucopiaAndSeasideKingdoms() {
        // Collector
        cardList.add(EMBARGO.getName());
        cardList.add(FISHING_VILLAGE.getName());
        cardList.add(MERCHANT_SHIP.getName());
        cardList.add(NAVIGATOR.getName());
        cardList.add(SMUGGLERS.getName());
        cardList.add(FAIRGROUNDS.getName());
        cardList.add(FARMING_VILLAGE.getName());
        cardList.add(FORTUNE_TELLER.getName());
        cardList.add(HARVEST.getName());
        cardList.add(HUNTING_PARTY.getName());

        saveKingdom(COLLECTOR.getName());

        // Collider
        cardList.add(LIGHTHOUSE.getName());
        cardList.add(SALVAGER.getName());
        cardList.add(TREASURE_MAP.getName());
        cardList.add(TREASURY.getName());
        cardList.add(WAREHOUSE.getName());
        cardList.add(MENAGERIE.getName());
        cardList.add(HORN_OF_PLENTY.getName());
        cardList.add(HORSE_TRADERS.getName());
        cardList.add(JESTER.getName());
        cardList.add(TOURNAMENT.getName());

        saveKingdom(COLLIDER.getName());
    }

    private void addCornucopiaAndProsperityKingdoms() {
        // Detours
        cardList.add(RABBLE.getName());
        cardList.add(PEDDLER.getName());
        cardList.add(HOARD.getName());
        cardList.add(TRADE_ROUTE.getName());
        cardList.add(VENTURE.getName());
        cardList.add(FARMING_VILLAGE.getName());
        cardList.add(HORN_OF_PLENTY.getName());
        cardList.add(JESTER.getName());
        cardList.add(REMAKE.getName());
        cardList.add(TOURNAMENT.getName());

        saveKingdom(DETOURS.getName());
    }

    private void addHinterlandsKingdoms() {
        // Introduction
        cardList.add(CACHE.getName());
        cardList.add(CROSSROADS.getName());
        cardList.add(DEVELOP.getName());
        cardList.add(HAGGLER.getName());
        cardList.add(JACK_OF_ALL_TRADES.getName());
        cardList.add(MARGRAVE.getName());
        cardList.add(NOMAD_CAMP.getName());
        cardList.add(OASIS.getName());
        cardList.add(SPICE_MERCHANT.getName());
        cardList.add(STABLES.getName());

        saveKingdom(INTRODUCTION.getName());

        // Fair Trades
        cardList.add(BORDER_VILLAGE.getName());
        cardList.add(CARTOGRAPHER.getName());
        cardList.add(DEVELOP.getName());
        cardList.add(DUCHESS.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(ILL_GOTTEN_GAINS.getName());
        cardList.add(NOBLE_BRIGAND.getName());
        cardList.add(SILK_ROAD.getName());
        cardList.add(STABLES.getName());
        cardList.add(TRADER.getName());

        saveKingdom(FAIR_TRADES.getName());

        // Bargains
        cardList.add(BORDER_VILLAGE.getName());
        cardList.add(CACHE.getName());
        cardList.add(DUCHESS.getName());
        cardList.add(FOOLS_GOLD.getName());
        cardList.add(HAGGLER.getName());
        cardList.add(HIGHWAY.getName());
        cardList.add(NOMAD_CAMP.getName());
        cardList.add(SCHEME.getName());
        cardList.add(SPICE_MERCHANT.getName());
        cardList.add(TRADER.getName());

        saveKingdom(BARGAINS.getName());

        // Gambits
        cardList.add(CARTOGRAPHER.getName());
        cardList.add(CROSSROADS.getName());
        cardList.add(EMBASSY.getName());
        cardList.add(INN.getName());
        cardList.add(JACK_OF_ALL_TRADES.getName());
        cardList.add(MANDARIN.getName());
        cardList.add(NOMAD_CAMP.getName());
        cardList.add(OASIS.getName());
        cardList.add(ORACLE.getName());
        cardList.add(TUNNEL.getName());

        saveKingdom(GAMBITS.getName());
    }

    private void addOldHinterlandsAndDominionKingdoms() {
        // Highway Robbery
        cardList.add(CELLAR.getName());
        cardList.add(LIBRARY.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(WORKSHOP.getName());
        cardList.add(HIGHWAY.getName());
        cardList.add(INN.getName());
        cardList.add(MARGRAVE.getName());
        cardList.add(NOBLE_BRIGAND.getName());
        cardList.add(OASIS.getName());

        saveKingdom(HIGHWAY_ROBBERY.getName());

        // Adventures Abroad
        cardList.add(ADVENTURER.getName());
        cardList.add(CHANCELLOR.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(REMODEL.getName());
        cardList.add(CROSSROADS.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(FOOLS_GOLD.getName());
        cardList.add(ORACLE.getName());
        cardList.add(SPICE_MERCHANT.getName());

        saveKingdom(ADVENTURES_ABROAD_1.getName());
    }

    private void addNewHinterlandsAndDominionKingdoms() {
        // Adventures Abroad
        cardList.add(FESTIVAL.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(REMODEL.getName());
        cardList.add(SENTRY.getName());
        cardList.add(VASSAL.getName());
        cardList.add(CROSSROADS.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(FOOLS_GOLD.getName());
        cardList.add(ORACLE.getName());
        cardList.add(SPICE_MERCHANT.getName());

        saveKingdom(ADVENTURES_ABROAD_2.getName());
    }

    private void addOldHinterlandsAndIntrigueKingdoms() {
        // Money for Nothing
        cardList.add(COPPERSMITH.getName());
        cardList.add(GREAT_HALL.getName());
        cardList.add(PAWN.getName());
        cardList.add(SHANTY_TOWN.getName());
        cardList.add(TORTURER.getName());
        cardList.add(CACHE.getName());
        cardList.add(CARTOGRAPHER.getName());
        cardList.add(JACK_OF_ALL_TRADES.getName());
        cardList.add(SILK_ROAD.getName());
        cardList.add(TUNNEL.getName());

        saveKingdom(MONEY_FOR_NOTHING_1.getName());

        // The Duke's Ball
        cardList.add(CONSPIRATOR.getName());
        cardList.add(DUKE.getName());
        cardList.add(HAREM.getName());
        cardList.add(MASQUERADE.getName());
        cardList.add(UPGRADE.getName());
        cardList.add(DUCHESS.getName());
        cardList.add(HAGGLER.getName());
        cardList.add(INN.getName());
        cardList.add(NOBLE_BRIGAND.getName());
        cardList.add(SCHEME.getName());

        saveKingdom(THE_DUKES_BALL.getName());
    }

    private void addNewHinterlandsAndIntrigueKingdoms() {
        // Money for Nothing
        cardList.add(REPLACE.getName());
        cardList.add(PATROL.getName());
        cardList.add(PAWN.getName());
        cardList.add(SHANTY_TOWN.getName());
        cardList.add(TORTURER.getName());
        cardList.add(CACHE.getName());
        cardList.add(CARTOGRAPHER.getName());
        cardList.add(JACK_OF_ALL_TRADES.getName());
        cardList.add(SILK_ROAD.getName());
        cardList.add(TUNNEL.getName());

        saveKingdom(MONEY_FOR_NOTHING_2.getName());
    }

    private void addHinterlandsAndSeasideKingdoms() {
        // Travelers
        cardList.add(CUTPURSE.getName());
        cardList.add(ISLAND.getName());
        cardList.add(LOOKOUT.getName());
        cardList.add(MERCHANT_SHIP.getName());
        cardList.add(WAREHOUSE.getName());
        cardList.add(CARTOGRAPHER.getName());
        cardList.add(CROSSROADS.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(SILK_ROAD.getName());
        cardList.add(STABLES.getName());

        saveKingdom(TRAVELERS.getName());

        // Diplomacy
        cardList.add(AMBASSADOR.getName());
        cardList.add(BAZAAR.getName());
        cardList.add(CARAVAN.getName());
        cardList.add(EMBARGO.getName());
        cardList.add(SMUGGLERS.getName());
        cardList.add(EMBASSY.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(ILL_GOTTEN_GAINS.getName());
        cardList.add(NOBLE_BRIGAND.getName());
        cardList.add(TRADER.getName());

        saveKingdom(DIPLOMACY.getName());
    }

    private void addHinterlandsAndAlchemyKingdoms() {
        // Schemes and Dreams
        cardList.add(APOTHECARY.getName());
        cardList.add(APPRENTICE.getName());
        cardList.add(HERBALIST.getName());
        cardList.add(PHILOSOPHERS_STONE.getName());
        cardList.add(TRANSMUTE.getName());
        cardList.add(DUCHESS.getName());
        cardList.add(FOOLS_GOLD.getName());
        cardList.add(ILL_GOTTEN_GAINS.getName());
        cardList.add(JACK_OF_ALL_TRADES.getName());
        cardList.add(SCHEME.getName());

        saveKingdom(SCHEMES_AND_DREAMS.getName());

        // Wine Country
        cardList.add(APPRENTICE.getName());
        cardList.add(FAMILIAR.getName());
        cardList.add(GOLEM.getName());
        cardList.add(UNIVERSITY.getName());
        cardList.add(VINEYARD.getName());
        cardList.add(CROSSROADS.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(HAGGLER.getName());
        cardList.add(HIGHWAY.getName());
        cardList.add(NOMAD_CAMP.getName());

        saveKingdom(WINE_COUNTRY.getName());
    }

    private void addHinterlandsAndProsperityKingdoms() {
        // Instant Gratification
        cardList.add(BISHOP.getName());
        cardList.add(EXPAND.getName());
        cardList.add(HOARD.getName());
        cardList.add(MINT.getName());
        cardList.add(WATCHTOWER.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(HAGGLER.getName());
        cardList.add(ILL_GOTTEN_GAINS.getName());
        cardList.add(NOBLE_BRIGAND.getName());
        cardList.add(TRADER.getName());

        saveKingdom(INSTANT_GRATIFICATION.getName());

        // Treasure Trove
        cardList.add(BANK.getName());
        cardList.add(MONUMENT.getName());
        cardList.add(ROYAL_SEAL.getName());
        cardList.add(TRADE_ROUTE.getName());
        cardList.add(VENTURE.getName());
        cardList.add(CACHE.getName());
        cardList.add(DEVELOP.getName());
        cardList.add(FOOLS_GOLD.getName());
        cardList.add(ILL_GOTTEN_GAINS.getName());
        cardList.add(MANDARIN.getName());

        saveKingdom(KingdomName.TREASURE_TROVE.getName());
    }

    private void addHinterlandsAndCornucopiaKingdoms() {
        // Blue Harvest
        cardList.add(HAMLET.getName());
        cardList.add(HORN_OF_PLENTY.getName());
        cardList.add(HORSE_TRADERS.getName());
        cardList.add(JESTER.getName());
        cardList.add(TOURNAMENT.getName());
        cardList.add(FOOLS_GOLD.getName());
        cardList.add(MANDARIN.getName());
        cardList.add(NOBLE_BRIGAND.getName());
        cardList.add(TRADER.getName());
        cardList.add(TUNNEL.getName());

        saveKingdom(BLUE_HARVEST.getName());

        // Traveling Circus
        cardList.add(FAIRGROUNDS.getName());
        cardList.add(FARMING_VILLAGE.getName());
        cardList.add(HUNTING_PARTY.getName());
        cardList.add(JESTER.getName());
        cardList.add(MENAGERIE.getName());
        cardList.add(BORDER_VILLAGE.getName());
        cardList.add(EMBASSY.getName());
        cardList.add(FOOLS_GOLD.getName());
        cardList.add(NOMAD_CAMP.getName());
        cardList.add(OASIS.getName());

        saveKingdom(TRAVELING_CIRCUS.getName());
    }

    private void addDarkAgesKingdoms() {
        // Grim Parade
        cardList.add(ARMORY.getName());
        cardList.add(BAND_OF_MISFITS.getName());
        cardList.add(CATACOMBS.getName());
        cardList.add(CULTIST.getName());
        cardList.add(FORAGER.getName());
        cardList.add(FORTRESS.getName());
        cardList.add(HUNTING_GROUNDS.getName());
        cardList.add(KNIGHTS.getName());
        cardList.add(MARKET_SQUARE.getName());
        cardList.add(PROCESSION.getName());

        saveKingdom(GRIM_PARADE.getName());

        // Chess With Death
        cardList.add(BANDIT_CAMP.getName());
        cardList.add(GRAVEROBBER.getName());
        cardList.add(JUNK_DEALER.getName());
        cardList.add(MYSTIC.getName());
        cardList.add(PILLAGE.getName());
        cardList.add(RATS.getName());
        cardList.add(SAGE.getName());
        cardList.add(SCAVENGER.getName());
        cardList.add(STOREROOM.getName());
        cardList.add(VAGRANT.getName());

        saveKingdom(CHESS_WITH_DEATH.getName());
    }

    private void addDarkAgesAndDominionKingdoms() {
        // High and Low
        cardList.add(CELLAR.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(WITCH.getName());
        cardList.add(WORKSHOP.getName());
        cardList.add(HERMIT.getName());
        cardList.add(HUNTING_GROUNDS.getName());
        cardList.add(MYSTIC.getName());
        cardList.add(POOR_HOUSE.getName());
        cardList.add(WANDERING_MINSTREL.getName());

        saveKingdom(HIGH_AND_LOW.getName());

        // Chivalry and Revelry
        cardList.add(FESTIVAL.getName());
        cardList.add(GARDENS.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(LIBRARY.getName());
        cardList.add(REMODEL.getName());
        cardList.add(ALTAR.getName());
        cardList.add(KNIGHTS.getName());
        cardList.add(RATS.getName());
        cardList.add(SCAVENGER.getName());
        cardList.add(SQUIRE.getName());

        saveKingdom(CHIVALRY_AND_REVELRY.getName());
    }

    private void addOldDarkAgesAndIntrigueKingdoms() {
        // Prophecy
        cardList.add(ARMORY.getName());
        cardList.add(IRONMONGER.getName());
        cardList.add(MYSTIC.getName());
        cardList.add(REBUILD.getName());
        cardList.add(VAGRANT.getName());
        cardList.add(BARON.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(GREAT_HALL.getName());
        cardList.add(NOBLES.getName());
        cardList.add(WISHING_WELL.getName());

        saveKingdom(PROPHECY_1.getName());

        // Invasion
        cardList.add(BEGGAR.getName());
        cardList.add(MARAUDER.getName());
        cardList.add(ROGUE.getName());
        cardList.add(SQUIRE.getName());
        cardList.add(URCHIN.getName());
        cardList.add(HAREM.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(SWINDLER.getName());
        cardList.add(TORTURER.getName());
        cardList.add(UPGRADE.getName());

        saveKingdom(INVASION_1.getName());
    }

    private void addNewDarkAgesAndIntrigueKingdoms() {
        // Prophecy
        cardList.add(ARMORY.getName());
        cardList.add(IRONMONGER.getName());
        cardList.add(MYSTIC.getName());
        cardList.add(REBUILD.getName());
        cardList.add(VAGRANT.getName());
        cardList.add(BARON.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(NOBLES.getName());
        cardList.add(SECRET_PASSAGE.getName());
        cardList.add(WISHING_WELL.getName());

        saveKingdom(PROPHECY_2.getName());

        // Invasion
        cardList.add(BEGGAR.getName());
        cardList.add(MARAUDER.getName());
        cardList.add(ROGUE.getName());
        cardList.add(SQUIRE.getName());
        cardList.add(URCHIN.getName());
        cardList.add(DIPLOMAT.getName());
        cardList.add(HAREM.getName());
        cardList.add(SWINDLER.getName());
        cardList.add(TORTURER.getName());
        cardList.add(UPGRADE.getName());

        saveKingdom(INVASION_2.getName());
    }

    private void addDarkAgesAndSeasideKingdoms() {
        // Watery Graves
        cardList.add(COUNT.getName());
        cardList.add(GRAVEROBBER.getName());
        cardList.add(HERMIT.getName());
        cardList.add(SCAVENGER.getName());
        cardList.add(URCHIN.getName());
        cardList.add(NATIVE_VILLAGE.getName());
        cardList.add(PIRATE_SHIP.getName());
        cardList.add(SALVAGER.getName());
        cardList.add(TREASURE_MAP.getName());
        cardList.add(TREASURY.getName());

        saveKingdom(WATERY_GRAVES.getName());

        // Peasants
        cardList.add(DEATH_CART.getName());
        cardList.add(FEODUM.getName());
        cardList.add(POOR_HOUSE.getName());
        cardList.add(URCHIN.getName());
        cardList.add(VAGRANT.getName());
        cardList.add(FISHING_VILLAGE.getName());
        cardList.add(HAVEN.getName());
        cardList.add(ISLAND.getName());
        cardList.add(LOOKOUT.getName());
        cardList.add(WAREHOUSE.getName());

        saveKingdom(PEASANTS.getName());
    }

    private void addDarkAgesAndAlchemyKingdoms() {
        // Infestations
        cardList.add(ARMORY.getName());
        cardList.add(CULTIST.getName());
        cardList.add(FEODUM.getName());
        cardList.add(MARKET_SQUARE.getName());
        cardList.add(RATS.getName());
        cardList.add(WANDERING_MINSTREL.getName());
        cardList.add(APPRENTICE.getName());
        cardList.add(SCRYING_POOL.getName());
        cardList.add(TRANSMUTE.getName());
        cardList.add(VINEYARD.getName());

        saveKingdom(INFESTATIONS.getName());

        // Lamentations
        cardList.add(BEGGAR.getName());
        cardList.add(CATACOMBS.getName());
        cardList.add(COUNTERFEIT.getName());
        cardList.add(FORAGER.getName());
        cardList.add(IRONMONGER.getName());
        cardList.add(PILLAGE.getName());
        cardList.add(APOTHECARY.getName());
        cardList.add(GOLEM.getName());
        cardList.add(HERBALIST.getName());
        cardList.add(UNIVERSITY.getName());

        saveKingdom(LAMENTATIONS.getName());
    }

    private void addDarkAgesAndProsperityKingdoms() {
        // One Man's Trash
        cardList.add(COUNTERFEIT.getName());
        cardList.add(FORAGER.getName());
        cardList.add(GRAVEROBBER.getName());
        cardList.add(MARKET_SQUARE.getName());
        cardList.add(ROGUE.getName());
        cardList.add(CITY.getName());
        cardList.add(GRAND_MARKET.getName());
        cardList.add(MONUMENT.getName());
        cardList.add(TALISMAN.getName());
        cardList.add(VENTURE.getName());

        saveKingdom(ONE_MANS_TRASH.getName());

        // Honor Among Thieves
        cardList.add(BANDIT_CAMP.getName());
        cardList.add(PROCESSION.getName());
        cardList.add(REBUILD.getName());
        cardList.add(ROGUE.getName());
        cardList.add(SQUIRE.getName());
        cardList.add(FORGE.getName());
        cardList.add(HOARD.getName());
        cardList.add(PEDDLER.getName());
        cardList.add(QUARRY.getName());
        cardList.add(WATCHTOWER.getName());

        saveKingdom(HONOR_AMONG_THIEVES.getName());
    }

    private void addDarkAgesAndCornucopiaKingdoms() {
        // Dark Carnival
        cardList.add(BAND_OF_MISFITS.getName());
        cardList.add(CULTIST.getName());
        cardList.add(FORTRESS.getName());
        cardList.add(HERMIT.getName());
        cardList.add(JUNK_DEALER.getName());
        cardList.add(KNIGHTS.getName());
        cardList.add(FAIRGROUNDS.getName());
        cardList.add(HAMLET.getName());
        cardList.add(HORN_OF_PLENTY.getName());
        cardList.add(MENAGERIE.getName());

        saveKingdom(DARK_CARNIVAL.getName());

        // To the Victor
        cardList.add(BANDIT_CAMP.getName());
        cardList.add(COUNTERFEIT.getName());
        cardList.add(DEATH_CART.getName());
        cardList.add(MARAUDER.getName());
        cardList.add(PILLAGE.getName());
        cardList.add(SAGE.getName());
        cardList.add(HARVEST.getName());
        cardList.add(HUNTING_PARTY.getName());
        cardList.add(REMAKE.getName());
        cardList.add(TOURNAMENT.getName());

        saveKingdom(TO_THE_VICTOR.getName());
    }

    private void addDarkAgesAndHinterlandsKingdoms() {
        // Far From Home
        cardList.add(BEGGAR.getName());
        cardList.add(COUNT.getName());
        cardList.add(FEODUM.getName());
        cardList.add(MARAUDER.getName());
        cardList.add(WANDERING_MINSTREL.getName());
        cardList.add(CARTOGRAPHER.getName());
        cardList.add(DEVELOP.getName());
        cardList.add(EMBASSY.getName());
        cardList.add(FOOLS_GOLD.getName());
        cardList.add(HAGGLER.getName());

        saveKingdom(FAR_FROM_HOME.getName());

        // Expeditions
        cardList.add(ALTAR.getName());
        cardList.add(CATACOMBS.getName());
        cardList.add(IRONMONGER.getName());
        cardList.add(POOR_HOUSE.getName());
        cardList.add(STOREROOM.getName());
        cardList.add(CROSSROADS.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(HIGHWAY.getName());
        cardList.add(SPICE_MERCHANT.getName());
        cardList.add(TUNNEL.getName());

        saveKingdom(EXPEDITIONS.getName());
    }

    private void addOldGuildsAndDominionKingdoms() {
        // Arts and Crafts
        cardList.add(STONEMASON.getName());
        cardList.add(ADVISOR.getName());
        cardList.add(BAKER.getName());
        cardList.add(JOURNEYMAN.getName());
        cardList.add(MERCHANT_GUILD.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(CELLAR.getName());
        cardList.add(WORKSHOP.getName());
        cardList.add(FESTIVAL.getName());
        cardList.add(MONEYLENDER.getName());

        saveKingdom(ARTS_AND_CRAFTS.getName());

        // Clean Living
        cardList.add(BUTCHER.getName());
        cardList.add(BAKER.getName());
        cardList.add(CANDLESTICK_MAKER.getName());
        cardList.add(DOCTOR.getName());
        cardList.add(SOOTHSAYER.getName());
        cardList.add(MILITIA.getName());
        cardList.add(THIEF.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(GARDENS.getName());
        cardList.add(VILLAGE.getName());

        saveKingdom(CLEAN_LIVING_1.getName());

        // Gilding the Lily
        cardList.add(PLAZA.getName());
        cardList.add(MASTERPIECE.getName());
        cardList.add(CANDLESTICK_MAKER.getName());
        cardList.add(TAXMAN.getName());
        cardList.add(HERALD.getName());
        cardList.add(LIBRARY.getName());
        cardList.add(REMODEL.getName());
        cardList.add(ADVENTURER.getName());
        cardList.add(MARKET.getName());
        cardList.add(CHANCELLOR.getName());

        saveKingdom(GILDING_THE_LILY_1.getName());
    }

    private void addNewGuildsAndDominionKingdoms() {
        // Clean Living
        cardList.add(BANDIT.getName());
        cardList.add(MILITIA.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(GARDENS.getName());
        cardList.add(VILLAGE.getName());
        cardList.add(BUTCHER.getName());
        cardList.add(BAKER.getName());
        cardList.add(CANDLESTICK_MAKER.getName());
        cardList.add(DOCTOR.getName());
        cardList.add(SOOTHSAYER.getName());

        saveKingdom(CLEAN_LIVING_2.getName());

        // Gilding the Lily
        cardList.add(LIBRARY.getName());
        cardList.add(MERCHANT.getName());
        cardList.add(REMODEL.getName());
        cardList.add(MARKET.getName());
        cardList.add(SENTRY.getName());
        cardList.add(PLAZA.getName());
        cardList.add(MASTERPIECE.getName());
        cardList.add(CANDLESTICK_MAKER.getName());
        cardList.add(TAXMAN.getName());
        cardList.add(HERALD.getName());

        saveKingdom(GILDING_THE_LILY_2.getName());
    }

    private void addOldGuildsAndIntrigueKingdoms() {
        // Name That Card
        cardList.add(BAKER.getName());
        cardList.add(DOCTOR.getName());
        cardList.add(PLAZA.getName());
        cardList.add(ADVISOR.getName());
        cardList.add(MASTERPIECE.getName());
        cardList.add(COURTYARD.getName());
        cardList.add(WISHING_WELL.getName());
        cardList.add(HAREM.getName());
        cardList.add(TRIBUTE.getName());
        cardList.add(NOBLES.getName());

        saveKingdom(NAME_THAT_CARD_1.getName());

        // Tricks of the Trade
        cardList.add(STONEMASON.getName());
        cardList.add(HERALD.getName());
        cardList.add(SOOTHSAYER.getName());
        cardList.add(JOURNEYMAN.getName());
        cardList.add(BUTCHER.getName());
        cardList.add(GREAT_HALL.getName());
        cardList.add(NOBLES.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(MASQUERADE.getName());
        cardList.add(COPPERSMITH.getName());

        saveKingdom(TRICKS_OF_THE_TRADE_1.getName());

        // Decisions, Decisions
        cardList.add(MERCHANT_GUILD.getName());
        cardList.add(CANDLESTICK_MAKER.getName());
        cardList.add(MASTERPIECE.getName());
        cardList.add(TAXMAN.getName());
        cardList.add(BUTCHER.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(PAWN.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(UPGRADE.getName());
        cardList.add(DUKE.getName());

        saveKingdom(DECISIONS_DECISIONS.getName());
    }

    private void addNewGuildsAndIntrigueKingdoms() {
        // Name That Card
        cardList.add(BAKER.getName());
        cardList.add(DOCTOR.getName());
        cardList.add(PLAZA.getName());
        cardList.add(ADVISOR.getName());
        cardList.add(MASTERPIECE.getName());
        cardList.add(COURTYARD.getName());
        cardList.add(HAREM.getName());
        cardList.add(NOBLES.getName());
        cardList.add(REPLACE.getName());
        cardList.add(WISHING_WELL.getName());

        saveKingdom(NAME_THAT_CARD_2.getName());

        // Tricks of the Trade
        cardList.add(STONEMASON.getName());
        cardList.add(HERALD.getName());
        cardList.add(SOOTHSAYER.getName());
        cardList.add(JOURNEYMAN.getName());
        cardList.add(BUTCHER.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(MASQUERADE.getName());
        cardList.add(MILL.getName());
        cardList.add(NOBLES.getName());
        cardList.add(SECRET_PASSAGE.getName());

        saveKingdom(TRICKS_OF_THE_TRADE_2.getName());
    }

    private void addGuildsAndSeasideKingdoms() {
        // Ghosts & Taxes
        cardList.add(CUTPURSE.getName());
        cardList.add(GHOST_SHIP.getName());
        cardList.add(HAVEN.getName());
        cardList.add(OUTPOST.getName());
        cardList.add(SMUGGLERS.getName());
        cardList.add(BUTCHER.getName());
        cardList.add(CANDLESTICK_MAKER.getName());
        cardList.add(HERALD.getName());
        cardList.add(SOOTHSAYER.getName());
        cardList.add(TAXMAN.getName());

        saveKingdom(GHOSTS_AND_TAXES.getName());

        // Island Builder
        cardList.add(ISLAND.getName());
        cardList.add(NATIVE_VILLAGE.getName());
        cardList.add(SALVAGER.getName());
        cardList.add(TACTICIAN.getName());
        cardList.add(TREASURY.getName());
        cardList.add(BAKER.getName());
        cardList.add(DOCTOR.getName());
        cardList.add(MERCHANT_GUILD.getName());
        cardList.add(PLAZA.getName());
        cardList.add(STONEMASON.getName());

        saveKingdom(ISLAND_BUILDER.getName());
    }

    private void addGuildsAndProsperityKingdoms() {
        // Quarrymen
        cardList.add(MOUNTEBANK.getName());
        cardList.add(CITY.getName());
        cardList.add(EXPAND.getName());
        cardList.add(GRAND_MARKET.getName());
        cardList.add(QUARRY.getName());
        cardList.add(BAKER.getName());
        cardList.add(MERCHANT_GUILD.getName());
        cardList.add(SOOTHSAYER.getName());
        cardList.add(STONEMASON.getName());
        cardList.add(TAXMAN.getName());

        saveKingdom(QUARRYMEN.getName());
    }

    private void addGuildsAndHinterlandsKingdoms() {
        // Exchanges
        cardList.add(BUTCHER.getName());
        cardList.add(HERALD.getName());
        cardList.add(MASTERPIECE.getName());
        cardList.add(SOOTHSAYER.getName());
        cardList.add(STONEMASON.getName());
        cardList.add(BORDER_VILLAGE.getName());
        cardList.add(DEVELOP.getName());
        cardList.add(ILL_GOTTEN_GAINS.getName());
        cardList.add(STABLES.getName());
        cardList.add(TRADER.getName());

        saveKingdom(EXCHANGES.getName());

        // Road to Riches
        cardList.add(ADVISOR.getName());
        cardList.add(BAKER.getName());
        cardList.add(CANDLESTICK_MAKER.getName());
        cardList.add(JOURNEYMAN.getName());
        cardList.add(MERCHANT_GUILD.getName());
        cardList.add(CROSSROADS.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(HIGHWAY.getName());
        cardList.add(SPICE_MERCHANT.getName());
        cardList.add(TUNNEL.getName());

        saveKingdom(ROAD_TO_RICHES.getName());
    }

    private void addGuildsAndDarkAgesKingdoms() {
        // Stoneground
        cardList.add(HUNTING_GROUNDS.getName());
        cardList.add(IRONMONGER.getName());
        cardList.add(PROCESSION.getName());
        cardList.add(MARAUDER.getName());
        cardList.add(ROGUE.getName());
        cardList.add(ADVISOR.getName());
        cardList.add(BAKER.getName());
        cardList.add(CANDLESTICK_MAKER.getName());
        cardList.add(PLAZA.getName());
        cardList.add(STONEMASON.getName());

        saveKingdom(STONEGROUND.getName());

        // Class Struggle
        cardList.add(FEODUM.getName());
        cardList.add(FORTRESS.getName());
        cardList.add(KNIGHTS.getName());
        cardList.add(MARKET_SQUARE.getName());
        cardList.add(POOR_HOUSE.getName());
        cardList.add(BUTCHER.getName());
        cardList.add(DOCTOR.getName());
        cardList.add(JOURNEYMAN.getName());
        cardList.add(MERCHANT_GUILD.getName());
        cardList.add(TAXMAN.getName());

        saveKingdom(CLASS_STRUGGLE.getName());
    }

    private void addAdventuresKingdoms() {
        // Gentle Intro
        cardList.add(AMULET.getName());
        cardList.add(DISTANT_LANDS.getName());
        cardList.add(DUNGEON.getName());
        cardList.add(DUPLICATE.getName());
        cardList.add(GIANT.getName());
        cardList.add(HIRELING.getName());
        cardList.add(PORT.getName());
        cardList.add(RANGER.getName());
        cardList.add(RATCATCHER.getName());
        cardList.add(CardName.TREASURE_TROVE.getName());
        
        eventList.add(SCOUTING_PARTY.getName());

        saveKingdom(GENTLE_INTRO.getName());

        // Expert Intro
        cardList.add(CARAVAN_GUARD.getName());
        cardList.add(COIN_OF_THE_REALM.getName());
        cardList.add(HAUNTED_WOODS.getName());
        cardList.add(LOST_CITY.getName());
        cardList.add(MAGPIE.getName());
        cardList.add(PEASANT.getName());
        cardList.add(RAZE.getName());
        cardList.add(SWAMP_HAG.getName());
        cardList.add(TRANSMOGRIFY.getName());
        cardList.add(WINE_MERCHANT.getName());
        
        eventList.add(MISSION.getName());
        eventList.add(PLAN.getName());

        saveKingdom(EXPERT_INTRO.getName());
    }

    private void addOldAdventuresAndDominionKingdoms() {
        // Level Up
        cardList.add(DUNGEON.getName());
        cardList.add(GEAR.getName());
        cardList.add(GUIDE.getName());
        cardList.add(LOST_CITY.getName());
        cardList.add(MISER.getName());
        cardList.add(MARKET.getName());
        cardList.add(MILITIA.getName());
        cardList.add(SPY.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(WORKSHOP.getName());
        
        eventList.add(TRAINING.getName());

        saveKingdom(LEVEL_UP_1.getName());

        // Son of Size Distortion
        cardList.add(AMULET.getName());
        cardList.add(DUPLICATE.getName());
        cardList.add(GIANT.getName());
        cardList.add(MESSENGER.getName());
        cardList.add(CardName.TREASURE_TROVE.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(GARDENS.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(THIEF.getName());
        cardList.add(WITCH.getName());
        
        eventList.add(BONFIRE.getName());
        eventList.add(RAID.getName());

        saveKingdom(SON_OF_SIZE_DISTORTION_1.getName());
    }

    private void addNewAdventuresAndDominionKingdoms() {
        // Level Up
        cardList.add(MARKET.getName());
        cardList.add(MERCHANT.getName());
        cardList.add(MILITIA.getName());
        cardList.add(THRONE_ROOM.getName());
        cardList.add(WORKSHOP.getName());
        cardList.add(DUNGEON.getName());
        cardList.add(GEAR.getName());
        cardList.add(GUIDE.getName());
        cardList.add(LOST_CITY.getName());
        cardList.add(MISER.getName());
        
        eventList.add(TRAINING.getName());

        saveKingdom(LEVEL_UP_2.getName());

        // Son of Size Distortion
        cardList.add(BANDIT.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(GARDENS.getName());
        cardList.add(MONEYLENDER.getName());
        cardList.add(WITCH.getName());
        cardList.add(AMULET.getName());
        cardList.add(DUPLICATE.getName());
        cardList.add(GIANT.getName());
        cardList.add(MESSENGER.getName());
        cardList.add(CardName.TREASURE_TROVE.getName());
        
        eventList.add(BONFIRE.getName());
        eventList.add(RAID.getName());

        saveKingdom(SON_OF_SIZE_DISTORTION_2.getName());
    }

    private void addOldAdventuresAndIntrigueKingdoms() {
        // Royalty Factory
        cardList.add(BRIDGE_TROLL.getName());
        cardList.add(DUPLICATE.getName());
        cardList.add(PAGE.getName());
        cardList.add(RAZE.getName());
        cardList.add(ROYAL_CARRIAGE.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(HAREM.getName());
        cardList.add(NOBLES.getName());
        cardList.add(SECRET_CHAMBER.getName());
        cardList.add(SWINDLER.getName());
        
        eventList.add(PILGRIMAGE.getName());

        saveKingdom(ROYALTY_FACTORY_1.getName());

        // Masters of Finance
        cardList.add(ARTIFICER.getName());
        cardList.add(DISTANT_LANDS.getName());
        cardList.add(GEAR.getName());
        cardList.add(TRANSMOGRIFY.getName());
        cardList.add(WINE_MERCHANT.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(PAWN.getName());
        cardList.add(SHANTY_TOWN.getName());
        cardList.add(STEWARD.getName());
        cardList.add(UPGRADE.getName());
        
        eventList.add(BALL.getName());
        eventList.add(BORROW.getName());

        saveKingdom(MASTERS_OF_FINANCE.getName());
    }

    private void addNewAdventuresAndIntrigueKingdoms() {
        // Royalty Factory
        cardList.add(BRIDGE_TROLL.getName());
        cardList.add(DUPLICATE.getName());
        cardList.add(PAGE.getName());
        cardList.add(RAZE.getName());
        cardList.add(ROYAL_CARRIAGE.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(COURTIER.getName());
        cardList.add(HAREM.getName());
        cardList.add(NOBLES.getName());
        cardList.add(SWINDLER.getName());
        
        eventList.add(PILGRIMAGE.getName());

        saveKingdom(ROYALTY_FACTORY_2.getName());
    }

    private void addAdventuresAndSeasideKingdoms() {
        // Prince of Orange
        cardList.add(AMULET.getName());
        cardList.add(DUNGEON.getName());
        cardList.add(HAUNTED_WOODS.getName());
        cardList.add(PAGE.getName());
        cardList.add(SWAMP_HAG.getName());
        cardList.add(CARAVAN.getName());
        cardList.add(FISHING_VILLAGE.getName());
        cardList.add(MERCHANT_SHIP.getName());
        cardList.add(TACTICIAN.getName());
        cardList.add(TREASURE_MAP.getName());
        
        eventList.add(MISSION.getName());

        saveKingdom(PRINCE_OF_ORANGE.getName());

        // Gifts and Mathoms
        cardList.add(BRIDGE_TROLL.getName());
        cardList.add(CARAVAN_GUARD.getName());
        cardList.add(HIRELING.getName());
        cardList.add(LOST_CITY.getName());
        cardList.add(MESSENGER.getName());
        cardList.add(AMBASSADOR.getName());
        cardList.add(EMBARGO.getName());
        cardList.add(HAVEN.getName());
        cardList.add(SALVAGER.getName());
        cardList.add(SMUGGLERS.getName());
        
        eventList.add(EXPEDITION.getName());
        eventList.add(QUEST.getName());

        saveKingdom(GIFTS_AND_MATHOMS.getName());
    }

    private void addAdventuresAndAlchemyKingdoms() {
        // Haste Potion
        cardList.add(MAGPIE.getName());
        cardList.add(MESSENGER.getName());
        cardList.add(PORT.getName());
        cardList.add(ROYAL_CARRIAGE.getName());
        cardList.add(CardName.TREASURE_TROVE.getName());
        cardList.add(APPRENTICE.getName());
        cardList.add(SCRYING_POOL.getName());
        cardList.add(TRANSMUTE.getName());
        cardList.add(UNIVERSITY.getName());
        cardList.add(VINEYARD.getName());
        
        eventList.add(PLAN.getName());

        saveKingdom(HASTE_POTION.getName());

        // Cursecatchers
        cardList.add(AMULET.getName());
        cardList.add(BRIDGE_TROLL.getName());
        cardList.add(CARAVAN_GUARD.getName());
        cardList.add(PEASANT.getName());
        cardList.add(RATCATCHER.getName());
        cardList.add(APOTHECARY.getName());
        cardList.add(FAMILIAR.getName());
        cardList.add(GOLEM.getName());
        cardList.add(HERBALIST.getName());
        cardList.add(PHILOSOPHERS_STONE.getName());
        
        eventList.add(SAVE.getName());
        eventList.add(TRADE.getName());

        saveKingdom(CURSECATCHERS.getName());
    }

    private void addAdventuresAndProsperityKingdoms() {
        // Last Will and Monument
        cardList.add(COIN_OF_THE_REALM.getName());
        cardList.add(DUNGEON.getName());
        cardList.add(MESSENGER.getName());
        cardList.add(RELIC.getName());
        cardList.add(CardName.TREASURE_TROVE.getName());
        cardList.add(BISHOP.getName());
        cardList.add(COUNTING_HOUSE.getName());
        cardList.add(MONUMENT.getName());
        cardList.add(RABBLE.getName());
        cardList.add(VAULT.getName());
        
        eventList.add(INHERITANCE.getName());

        saveKingdom(LAST_WILL_AND_MONUMENT.getName());

        // Think Big
        cardList.add(DISTANT_LANDS.getName());
        cardList.add(GIANT.getName());
        cardList.add(HIRELING.getName());
        cardList.add(MISER.getName());
        cardList.add(STORYTELLER.getName());
        cardList.add(CONTRABAND.getName());
        cardList.add(EXPAND.getName());
        cardList.add(HOARD.getName());
        cardList.add(KINGS_COURT.getName());
        cardList.add(PEDDLER.getName());
        
        eventList.add(BALL.getName());
        eventList.add(FERRY.getName());

        saveKingdom(THINK_BIG.getName());
    }

    private void addAdventuresAndCornucopiaKingdoms() {
        // The Hero's Return
        cardList.add(ARTIFICER.getName());
        cardList.add(MISER.getName());
        cardList.add(PAGE.getName());
        cardList.add(RANGER.getName());
        cardList.add(RELIC.getName());
        cardList.add(FAIRGROUNDS.getName());
        cardList.add(FARMING_VILLAGE.getName());
        cardList.add(HORSE_TRADERS.getName());
        cardList.add(JESTER.getName());
        cardList.add(MENAGERIE.getName());
        
        eventList.add(TRAVELLING_FAIR.getName());

        saveKingdom(THE_HEROS_RETURN.getName());

        // Seacraft and Witchcraft
        cardList.add(PEASANT.getName());
        cardList.add(STORYTELLER.getName());
        cardList.add(SWAMP_HAG.getName());
        cardList.add(TRANSMOGRIFY.getName());
        cardList.add(WINE_MERCHANT.getName());
        cardList.add(FORTUNE_TELLER.getName());
        cardList.add(HAMLET.getName());
        cardList.add(HORN_OF_PLENTY.getName());
        cardList.add(TOURNAMENT.getName());
        cardList.add(YOUNG_WITCH.getName());
        cardList.add("Bane: " + GUIDE.getName());
        
        eventList.add(FERRY.getName());
        eventList.add(SEAWAY.getName());

        saveKingdom(SEACRAFT_AND_WITCHCRAFT.getName());
    }

    private void addAdventuresAndHinterlandsKingdoms() {
        // Traders and Raiders
        cardList.add(HAUNTED_WOODS.getName());
        cardList.add(LOST_CITY.getName());
        cardList.add(PAGE.getName());
        cardList.add(PORT.getName());
        cardList.add(WINE_MERCHANT.getName());
        cardList.add(DEVELOP.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(HAGGLER.getName());
        cardList.add(SPICE_MERCHANT.getName());
        cardList.add(TRADER.getName());
        
        eventList.add(RAID.getName());

        saveKingdom(TRADERS_AND_RAIDERS.getName());

        // Journeys
        cardList.add(BRIDGE_TROLL.getName());
        cardList.add(DISTANT_LANDS.getName());
        cardList.add(GIANT.getName());
        cardList.add(GUIDE.getName());
        cardList.add(RANGER.getName());
        cardList.add(CARTOGRAPHER.getName());
        cardList.add(CROSSROADS.getName());
        cardList.add(HIGHWAY.getName());
        cardList.add(INN.getName());
        cardList.add(SILK_ROAD.getName());
        
        eventList.add(EXPEDITION.getName());
        eventList.add(INHERITANCE.getName());

        saveKingdom(JOURNEYS.getName());
    }

    private void addAdventuresAndDarkAgesKingdoms() {
        // Cemetery Polka
        cardList.add(AMULET.getName());
        cardList.add(CARAVAN_GUARD.getName());
        cardList.add(HIRELING.getName());
        cardList.add(PEASANT.getName());
        cardList.add(RELIC.getName());
        cardList.add(GRAVEROBBER.getName());
        cardList.add(MARAUDER.getName());
        cardList.add(PROCESSION.getName());
        cardList.add(ROGUE.getName());
        cardList.add(WANDERING_MINSTREL.getName());
        
        eventList.add(ALMS.getName());

        saveKingdom(CEMETERY_POLKA.getName());

        // Groovy Decay
        cardList.add(DUNGEON.getName());
        cardList.add(HAUNTED_WOODS.getName());
        cardList.add(RATCATCHER.getName());
        cardList.add(RAZE.getName());
        cardList.add(TRANSMOGRIFY.getName());
        cardList.add(CULTIST.getName());
        cardList.add(DEATH_CART.getName());
        cardList.add(FORTRESS.getName());
        cardList.add(KNIGHTS.getName());
        cardList.add(RATS.getName());
        
        eventList.add(LOST_ARTS.getName());
        eventList.add(PATHFINDING.getName());

        saveKingdom(GROOVY_DECAY.getName());
    }

    private void addAdventuresAndGuildsKingdoms() {
        // Spendthrift
        cardList.add(ARTIFICER.getName());
        cardList.add(GEAR.getName());
        cardList.add(MAGPIE.getName());
        cardList.add(MISER.getName());
        cardList.add(STORYTELLER.getName());
        cardList.add(DOCTOR.getName());
        cardList.add(MASTERPIECE.getName());
        cardList.add(MERCHANT_GUILD.getName());
        cardList.add(SOOTHSAYER.getName());
        cardList.add(STONEMASON.getName());
        
        eventList.add(LOST_ARTS.getName());

        saveKingdom(SPENDTHRIFT.getName());

        // Queen of Tan
        cardList.add(COIN_OF_THE_REALM.getName());
        cardList.add(DUPLICATE.getName());
        cardList.add(GUIDE.getName());
        cardList.add(RATCATCHER.getName());
        cardList.add(ROYAL_CARRIAGE.getName());
        cardList.add(ADVISOR.getName());
        cardList.add(BUTCHER.getName());
        cardList.add(CANDLESTICK_MAKER.getName());
        cardList.add(HERALD.getName());
        cardList.add(JOURNEYMAN.getName());
        
        eventList.add(PATHFINDING.getName());
        eventList.add(SAVE.getName());

        saveKingdom(QUEEN_OF_TAN.getName());
    }

    private void addEmpiresKingdoms() {
        // Basic Intro
        cardList.add(CASTLES.getName());
        cardList.add(CHARIOT_RACE.getName());
        cardList.add(CITY_QUARTER.getName());
        cardList.add(ENGINEER.getName());
        cardList.add(FARMERS_MARKET.getName());
        cardList.add(FORUM.getName());
        cardList.add(LEGIONARY.getName());
        cardList.add(PATRICIAN.getName());
        cardList.add(SACRIFICE.getName());
        cardList.add(VILLA.getName());
        
        eventList.add(WEDDING.getName());

        landmarkList.add(TOWER.getName());

        saveKingdom(BASIC_INTRO.getName());

        // Advanced Intro
        cardList.add(ARCHIVE.getName());
        cardList.add(CAPITAL.getName());
        cardList.add(CATAPULT.getName());
        cardList.add(CROWN.getName());
        cardList.add(ENCHANTRESS.getName());
        cardList.add(GLADIATOR.getName());
        cardList.add(GROUNDSKEEPER.getName());
        cardList.add(ROYAL_BLACKSMITH.getName());
        cardList.add(SETTLERS.getName());
        cardList.add(TEMPLE.getName());
        
        landmarkList.add(ARENA.getName());
        landmarkList.add(TRIUMPHAL_ARCH.getName());

        saveKingdom(ADVANCED_INTRO.getName());
    }

    private void addEmpiresAndDominionKingdoms() {
        // Everything in Moderation
        cardList.add(ENCHANTRESS.getName());
        cardList.add(FORUM.getName());
        cardList.add(LEGIONARY.getName());
        cardList.add(OVERLORD.getName());
        cardList.add(TEMPLE.getName());
        cardList.add(CELLAR.getName());
        cardList.add(LIBRARY.getName());
        cardList.add(REMODEL.getName());
        cardList.add(VILLAGE.getName());
        cardList.add(WORKSHOP.getName());
        
        eventList.add(WINDFALL.getName());

        landmarkList.add(ORCHARD.getName());

        saveKingdom(EVERYTHING_IN_MODERATION.getName());

        // Silver Bullets
        cardList.add(CATAPULT.getName());
        cardList.add(CHARM.getName());
        cardList.add(FARMERS_MARKET.getName());
        cardList.add(GROUNDSKEEPER.getName());
        cardList.add(PATRICIAN.getName());
        cardList.add(BUREAUCRAT.getName());
        cardList.add(GARDENS.getName());
        cardList.add(LABORATORY.getName());
        cardList.add(MARKET.getName());
        cardList.add(MONEYLENDER.getName());
        
        eventList.add(CONQUEST.getName());

        landmarkList.add(AQUEDUCT.getName());

        saveKingdom(SILVER_BULLETS.getName());
    }

    private void addEmpiresAndIntrigueKingdoms() {
        // Delicious Torture
        cardList.add(CASTLES.getName());
        cardList.add(CROWN.getName());
        cardList.add(ENCHANTRESS.getName());
        cardList.add(SACRIFICE.getName());
        cardList.add(SETTLERS.getName());
        cardList.add(BARON.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(HAREM.getName());
        cardList.add(IRONWORKS.getName());
        cardList.add(TORTURER.getName());
        
        eventList.add(BANQUET.getName());
        
        landmarkList.add(ARENA.getName());

        saveKingdom(DELICIOUS_TORTURE.getName());

        // Buddy System
        cardList.add(ARCHIVE.getName());
        cardList.add(CAPITAL.getName());
        cardList.add(CATAPULT.getName());
        cardList.add(ENGINEER.getName());
        cardList.add(FORUM.getName());
        cardList.add(MASQUERADE.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(NOBLES.getName());
        cardList.add(PAWN.getName());
        cardList.add(TRADING_POST.getName());
        
        eventList.add(SALT_THE_EARTH.getName());

        landmarkList.add(WOLF_DEN.getName());

        saveKingdom(BUDDY_SYSTEM.getName());
    }

    private void addEmpiresAndSeasideKingdoms() {
        // Boxed In
        cardList.add(CASTLES.getName());
        cardList.add(CHARIOT_RACE.getName());
        cardList.add(ENCAMPMENT.getName());
        cardList.add(ENCHANTRESS.getName());
        cardList.add(GLADIATOR.getName());
        cardList.add(SALVAGER.getName());
        cardList.add(SMUGGLERS.getName());
        cardList.add(TACTICIAN.getName());
        cardList.add(WAREHOUSE.getName());
        cardList.add(WHARF.getName());
        
        eventList.add(TAX.getName());

        landmarkList.add(WALL.getName());

        saveKingdom(BOXED_IN.getName());

        // King of the Sea
        cardList.add(ARCHIVE.getName());
        cardList.add(FARMERS_MARKET.getName());
        cardList.add(OVERLORD.getName());
        cardList.add(TEMPLE.getName());
        cardList.add(WILD_HUNT.getName());
        cardList.add(EXPLORER.getName());
        cardList.add(HAVEN.getName());
        cardList.add(NATIVE_VILLAGE.getName());
        cardList.add(PIRATE_SHIP.getName());
        cardList.add(SEA_HAG.getName());
        
        eventList.add(DELVE.getName());

        landmarkList.add(FOUNTAIN.getName());

        saveKingdom(KING_OF_THE_SEA.getName());
    }

    private void addEmpiresAndAlchemyKingdoms() {
        // Collectors
        cardList.add(CITY_QUARTER.getName());
        cardList.add(CROWN.getName());
        cardList.add(ENCAMPMENT.getName());
        cardList.add(ENCHANTRESS.getName());
        cardList.add(FARMERS_MARKET.getName());
        cardList.add(APOTHECARY.getName());
        cardList.add(APPRENTICE.getName());
        cardList.add(HERBALIST.getName());
        cardList.add(TRANSMUTE.getName());
        cardList.add(UNIVERSITY.getName());
        
        landmarkList.add(COLONNADE.getName());
        landmarkList.add(MUSEUM.getName());

        saveKingdom(COLLECTORS.getName());
    }

    private void addEmpiresAndProsperityKingdoms() {
        // Big Time
        cardList.add(CAPITAL.getName());
        cardList.add(GLADIATOR.getName());
        cardList.add(PATRICIAN.getName());
        cardList.add(ROYAL_BLACKSMITH.getName());
        cardList.add(VILLA.getName());
        cardList.add(BANK.getName());
        cardList.add(FORGE.getName());
        cardList.add(GRAND_MARKET.getName());
        cardList.add(LOAN.getName());
        cardList.add(ROYAL_SEAL.getName());
        
        eventList.add(DOMINATE.getName());

        landmarkList.add(OBELISK.getName());

        saveKingdom(BIG_TIME.getName());

        // Gilded Gates
        cardList.add(CHARIOT_RACE.getName());
        cardList.add(CITY_QUARTER.getName());
        cardList.add(ENCAMPMENT.getName());
        cardList.add(GROUNDSKEEPER.getName());
        cardList.add(WILD_HUNT.getName());
        cardList.add(BISHOP.getName());
        cardList.add(MONUMENT.getName());
        cardList.add(MINT.getName());
        cardList.add(PEDDLER.getName());
        cardList.add(TALISMAN.getName());

        landmarkList.add(BASILICA.getName());
        landmarkList.add(PALACE.getName());

        saveKingdom(GILDED_GATES.getName());
    }

    private void addEmpiresAndCornucopiaKingdoms() {
        // Zookeepers
        cardList.add(OVERLORD.getName());
        cardList.add(SACRIFICE.getName());
        cardList.add(SETTLERS.getName());
        cardList.add(VILLA.getName());
        cardList.add(WILD_HUNT.getName());
        cardList.add(FAIRGROUNDS.getName());
        cardList.add(HORSE_TRADERS.getName());
        cardList.add(MENAGERIE.getName());
        cardList.add(JESTER.getName());
        cardList.add(TOURNAMENT.getName());
        
        eventList.add(ANNEX.getName());

        landmarkList.add(COLONNADE.getName());

        saveKingdom(ZOOKEEPERS.getName());
    }

    private void addEmpiresAndHinterlandsKingdoms() {
        // Simple Plans
        cardList.add(CATAPULT.getName());
        cardList.add(FORUM.getName());
        cardList.add(PATRICIAN.getName());
        cardList.add(TEMPLE.getName());
        cardList.add(VILLA.getName());
        cardList.add(BORDER_VILLAGE.getName());
        cardList.add(DEVELOP.getName());
        cardList.add(HAGGLER.getName());
        cardList.add(ILL_GOTTEN_GAINS.getName());
        cardList.add(STABLES.getName());
        
        eventList.add(DONATE.getName());

        landmarkList.add(LABYRINTH.getName());

        saveKingdom(SIMPLE_PLANS.getName());

        // Expansion
        cardList.add(CASTLES.getName());
        cardList.add(CHARM.getName());
        cardList.add(ENCAMPMENT.getName());
        cardList.add(ENGINEER.getName());
        cardList.add(LEGIONARY.getName());
        cardList.add(CACHE.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(HIGHWAY.getName());
        cardList.add(SPICE_MERCHANT.getName());
        cardList.add(TUNNEL.getName());

        landmarkList.add(BATTLEFIELD.getName());
        landmarkList.add(FOUNTAIN.getName());

        saveKingdom(EXPANSION.getName());
    }

    private void addEmpiresAndDarkAgesKingdoms() {
        // Tomb of the Rat King
        cardList.add(CASTLES.getName());
        cardList.add(CHARIOT_RACE.getName());
        cardList.add(CITY_QUARTER.getName());
        cardList.add(LEGIONARY.getName());
        cardList.add(SACRIFICE.getName());
        cardList.add(DEATH_CART.getName());
        cardList.add(FORTRESS.getName());
        cardList.add(PILLAGE.getName());
        cardList.add(RATS.getName());
        cardList.add(STOREROOM.getName());
        
        eventList.add(ADVANCE.getName());

        landmarkList.add(TOMB.getName());

        saveKingdom(TOMB_OF_THE_RAT_KING.getName());

        // Triumph of the Bandit King
        cardList.add(CAPITAL.getName());
        cardList.add(CHARM.getName());
        cardList.add(ENGINEER.getName());
        cardList.add(GROUNDSKEEPER.getName());
        cardList.add(LEGIONARY.getName());
        cardList.add(BANDIT_CAMP.getName());
        cardList.add(CATACOMBS.getName());
        cardList.add(HUNTING_GROUNDS.getName());
        cardList.add(MARKET_SQUARE.getName());
        cardList.add(PROCESSION.getName());
        
        eventList.add(TRIUMPH.getName());

        landmarkList.add(DEFILED_SHRINE.getName());

        saveKingdom(TRIUMPH_OF_THE_BANDIT_KING.getName());

        // The Squire's Ritual
        cardList.add(ARCHIVE.getName());
        cardList.add(CATAPULT.getName());
        cardList.add(CROWN.getName());
        cardList.add(PATRICIAN.getName());
        cardList.add(SETTLERS.getName());
        cardList.add(FEODUM.getName());
        cardList.add(HERMIT.getName());
        cardList.add(IRONMONGER.getName());
        cardList.add(ROGUE.getName());
        cardList.add(SQUIRE.getName());
        
        eventList.add(RITUAL.getName());

        landmarkList.add(MUSEUM.getName());

        saveKingdom(THE_SQUIRES_RITUAL.getName());
    }

    private void addEmpiresAndGuildsKingdoms() {
        // Cash Flow
        cardList.add(CASTLES.getName());
        cardList.add(CITY_QUARTER.getName());
        cardList.add(ENGINEER.getName());
        cardList.add(GLADIATOR.getName());
        cardList.add(ROYAL_BLACKSMITH.getName());
        cardList.add(BAKER.getName());
        cardList.add(BUTCHER.getName());
        cardList.add(DOCTOR.getName());
        cardList.add(HERALD.getName());
        cardList.add(SOOTHSAYER.getName());

        landmarkList.add(BATHS.getName());
        landmarkList.add(MOUNTAIN_PASS.getName());

        saveKingdom(CASH_FLOW.getName());
    }

    private void addEmpiresAndAdventuresKingdoms() {
        // Area Control
        cardList.add(CAPITAL.getName());
        cardList.add(CATAPULT.getName());
        cardList.add(CHARM.getName());
        cardList.add(CROWN.getName());
        cardList.add(FARMERS_MARKET.getName());
        cardList.add(COIN_OF_THE_REALM.getName());
        cardList.add(PAGE.getName());
        cardList.add(RELIC.getName());
        cardList.add(CardName.TREASURE_TROVE.getName());
        cardList.add(WINE_MERCHANT.getName());
        
        eventList.add(BANQUET.getName());

        landmarkList.add(KEEP.getName());

        saveKingdom(AREA_CONTROL.getName());

        // No Money No Problems
        cardList.add(ARCHIVE.getName());
        cardList.add(ENCAMPMENT.getName());
        cardList.add(ROYAL_BLACKSMITH.getName());
        cardList.add(TEMPLE.getName());
        cardList.add(VILLA.getName());
        cardList.add(DUNGEON.getName());
        cardList.add(DUPLICATE.getName());
        cardList.add(HIRELING.getName());
        cardList.add(PEASANT.getName());
        cardList.add(TRANSMOGRIFY.getName());
        
        eventList.add(MISSION.getName());

        landmarkList.add(BANDIT_FORT.getName());

        saveKingdom(NO_MONEY_NO_PROBLEMS.getName());
    }

    private void addNocturneKingdoms() {
        // Dusk
        cardList.add(BLESSED_VILLAGE.getName());
        cardList.add(COBBLER.getName());
        cardList.add(DEN_OF_SIN.getName());
        cardList.add(FAITHFUL_HOUND.getName());
        cardList.add(FOOL.getName());
        cardList.add(MONASTERY.getName());
        cardList.add(NIGHT_WATCHMAN.getName());
        cardList.add(SHEPHERD.getName());
        cardList.add(TORMENTOR.getName());
        cardList.add(TRAGIC_HERO.getName());

        saveKingdom(DUSK.getName());

        // Midnight
        cardList.add(CONCLAVE.getName());
        cardList.add(CRYPT.getName());
        cardList.add(CURSED_VILLAGE.getName());
        cardList.add(DEVILS_WORKSHOP.getName());
        cardList.add(DRUID.getName());
        cardList.add(EXORCIST.getName());
        cardList.add(LEPRECHAUN.getName());
        cardList.add(POOKA.getName());
        cardList.add(RAIDER.getName());
        cardList.add(SECRET_CAVE.getName());
        cardList.add("Boon: " + THE_SWAMPS_GIFT.getName());
        cardList.add("Boon: " + THE_FLAMES_GIFT.getName());
        cardList.add("Boon: " + THE_WINDS_GIFT.getName());

        saveKingdom(MIDNIGHT.getName());
    }

    private void addNocturneAndDominionKingdoms() {
        // Night Shift
        cardList.add(DRUID.getName());
        cardList.add(EXORCIST.getName());
        cardList.add(GHOST_TOWN.getName());
        cardList.add(IDOL.getName());
        cardList.add(NIGHT_WATCHMAN.getName());
        cardList.add(BANDIT.getName());
        cardList.add(GARDENS.getName());
        cardList.add(MINE.getName());
        cardList.add(POACHER.getName());
        cardList.add(SMITHY.getName());
        cardList.add("Boon: " + THE_EARTHS_GIFT.getName());
        cardList.add("Boon: " + THE_FLAMES_GIFT.getName());
        cardList.add("Boon: " + THE_FORESTS_GIFT.getName());

        saveKingdom(NIGHT_SHIFT.getName());

        // Idle Hands
        cardList.add(BARD.getName());
        cardList.add(CONCLAVE.getName());
        cardList.add(CURSED_VILLAGE.getName());
        cardList.add(DEVILS_WORKSHOP.getName());
        cardList.add(TRAGIC_HERO.getName());
        cardList.add(CELLAR.getName());
        cardList.add(HARBINGER.getName());
        cardList.add(MARKET.getName());
        cardList.add(MERCHANT.getName());
        cardList.add(MONEYLENDER.getName());

        saveKingdom(IDLE_HANDS.getName());
    }

    private void addNocturneAndIntrigueKingdoms() {
        // Shadowy Figures
        cardList.add(COBBLER.getName());
        cardList.add(CONCLAVE.getName());
        cardList.add(FAITHFUL_HOUND.getName());
        cardList.add(SHEPHERD.getName());
        cardList.add(TRAGIC_HERO.getName());
        cardList.add(BRIDGE.getName());
        cardList.add(CONSPIRATOR.getName());
        cardList.add(MILL.getName());
        cardList.add(NOBLES.getName());
        cardList.add(SECRET_PASSAGE.getName());

        saveKingdom(SHADOWY_FIGURES.getName());

        // Impending Doom
        cardList.add(LEPRECHAUN.getName());
        cardList.add(MONASTERY.getName());
        cardList.add(NECROMANCER.getName());
        cardList.add(TORMENTOR.getName());
        cardList.add(WEREWOLF.getName());
        cardList.add(COURTIER.getName());
        cardList.add(LURKER.getName());
        cardList.add(MINING_VILLAGE.getName());
        cardList.add(SWINDLER.getName());
        cardList.add(UPGRADE.getName());

        saveKingdom(IMPENDING_DOOM.getName());
    }

    private void addNocturneAndSeasideKingdoms() {
        // The New Black
        cardList.add(COBBLER.getName());
        cardList.add(DEN_OF_SIN.getName());
        cardList.add(GHOST_TOWN.getName());
        cardList.add(RAIDER.getName());
        cardList.add(SECRET_CAVE.getName());
        cardList.add(CARAVAN.getName());
        cardList.add(HAVEN.getName());
        cardList.add(MERCHANT_SHIP.getName());
        cardList.add(OUTPOST.getName());
        cardList.add(TACTICIAN.getName());

        saveKingdom(THE_NEW_BLACK.getName());

        // Forbidden Isle
        cardList.add(BLESSED_VILLAGE.getName());
        cardList.add(CEMETERY.getName());
        cardList.add(IDOL.getName());
        cardList.add(TRACKER.getName());
        cardList.add(TRAGIC_HERO.getName());
        cardList.add(FISHING_VILLAGE.getName());
        cardList.add(GHOST_SHIP.getName());
        cardList.add(LOOKOUT.getName());
        cardList.add(SALVAGER.getName());
        cardList.add(TREASURE_MAP.getName());

        saveKingdom(FORBIDDEN_ISLE.getName());
    }

    private void addNocturneAndAlchemyKingdoms() {
        // Nightmare Fuel
        cardList.add(BARD.getName());
        cardList.add(BLESSED_VILLAGE.getName());
        cardList.add(CEMETERY.getName());
        cardList.add(SACRED_GROVE.getName());
        cardList.add(SKULK.getName());
        cardList.add(TRACKER.getName());
        cardList.add(ALCHEMIST.getName());
        cardList.add(APPRENTICE.getName());
        cardList.add(TRANSMUTE.getName());
        cardList.add(VINEYARD.getName());

        saveKingdom(NIGHTMARE_FUEL.getName());
    }

    private void addNocturneAndProsperityKingdoms() {
        // Treasures of the Night
        cardList.add(CRYPT.getName());
        cardList.add(GUARDIAN.getName());
        cardList.add(NIGHT_WATCHMAN.getName());
        cardList.add(RAIDER.getName());
        cardList.add(VAMPIRE.getName());
        cardList.add(BANK.getName());
        cardList.add(CONTRABAND.getName());
        cardList.add(LOAN.getName());
        cardList.add(ROYAL_SEAL.getName());
        cardList.add(VENTURE.getName());

        saveKingdom(TREASURES_OF_THE_NIGHT.getName());

        // Day at the Races
        cardList.add(BLESSED_VILLAGE.getName());
        cardList.add(CEMETERY.getName());
        cardList.add(DRUID.getName());
        cardList.add(TORMENTOR.getName());
        cardList.add(TRAGIC_HERO.getName());
        cardList.add(BISHOP.getName());
        cardList.add(PEDDLER.getName());
        cardList.add(TALISMAN.getName());
        cardList.add(TRADE_ROUTE.getName());
        cardList.add(WATCHTOWER.getName());
        cardList.add("Boon: " + THE_SWAMPS_GIFT.getName());
        cardList.add("Boon: " + THE_RIVERS_GIFT.getName());
        cardList.add("Boon: " + THE_FORESTS_GIFT.getName());

        saveKingdom(DAY_AT_THE_RACES.getName());
    }

    private void addNocturneAndCornucopiaKingdoms() {
        // The Endless Fair
        cardList.add(DEVILS_WORKSHOP.getName());
        cardList.add(EXORCIST.getName());
        cardList.add(MONASTERY.getName());
        cardList.add(PIXIE.getName());
        cardList.add(SHEPHERD.getName());
        cardList.add(BAKER.getName());
        cardList.add(FAIRGROUNDS.getName());
        cardList.add(FARMING_VILLAGE.getName());
        cardList.add(FORTUNE_TELLER.getName());
        cardList.add(MERCHANT_GUILD.getName());

        saveKingdom(THE_ENDLESS_FAIR.getName());
    }

    private void addNocturneAndHinterlandsKingdoms() {
        // Search Party
        cardList.add(COBBLER.getName());
        cardList.add(CONCLAVE.getName());
        cardList.add(DRUID.getName());
        cardList.add(FAITHFUL_HOUND.getName());
        cardList.add(WEREWOLF.getName());
        cardList.add(CARTOGRAPHER.getName());
        cardList.add(HIGHWAY.getName());
        cardList.add(INN.getName());
        cardList.add(OASIS.getName());
        cardList.add(SCHEME.getName());
        cardList.add("Boon: " + THE_MOUNTAINS_GIFT.getName());
        cardList.add("Boon: " + THE_SKYS_GIFT.getName());
        cardList.add("Boon: " + THE_SUNS_GIFT.getName());

        saveKingdom(SEARCH_PARTY.getName());

        // Counting Sheep
        cardList.add(BARD.getName());
        cardList.add(CRYPT.getName());
        cardList.add(LEPRECHAUN.getName());
        cardList.add(POOKA.getName());
        cardList.add(SHEPHERD.getName());
        cardList.add(CROSSROADS.getName());
        cardList.add(FARMLAND.getName());
        cardList.add(HAGGLER.getName());
        cardList.add(NOBLE_BRIGAND.getName());
        cardList.add(TUNNEL.getName());

        saveKingdom(COUNTING_SHEEP.getName());
    }

    private void addNocturneAndDarkAgesKingdoms() {
        // Grave Matters
        cardList.add(CEMETERY.getName());
        cardList.add(CURSED_VILLAGE.getName());
        cardList.add(NECROMANCER.getName());
        cardList.add(SKULK.getName());
        cardList.add(TORMENTOR.getName());
        cardList.add(ARMORY.getName());
        cardList.add(FORAGER.getName());
        cardList.add(GRAVEROBBER.getName());
        cardList.add(MARKET_SQUARE.getName());
        cardList.add(SQUIRE.getName());

        saveKingdom(GRAVE_MATTERS.getName());

        // Rats and Bats
        cardList.add(CHANGELING.getName());
        cardList.add(DEVILS_WORKSHOP.getName());
        cardList.add(SACRED_GROVE.getName());
        cardList.add(TRACKER.getName());
        cardList.add(VAMPIRE.getName());
        cardList.add(CATACOMBS.getName());
        cardList.add(COUNT.getName());
        cardList.add(FORTRESS.getName());
        cardList.add(HERMIT.getName());
        cardList.add(RATS.getName());

        saveKingdom(RATS_AND_BATS.getName());
    }

    private void addNocturneAndGuildsKingdoms() {
        // Happy Chaos
        cardList.add(BLESSED_VILLAGE.getName());
        cardList.add(CHANGELING.getName());
        cardList.add(FOOL.getName());
        cardList.add(FAITHFUL_HOUND.getName());
        cardList.add(SACRED_GROVE.getName());
        cardList.add(DOCTOR.getName());
        cardList.add(HARVEST.getName());
        cardList.add(HERALD.getName());
        cardList.add(JESTER.getName());
        cardList.add(MASTERPIECE.getName());

        saveKingdom(HAPPY_CHAOS.getName());
    }

    private void addNocturneAndAdventuresKingdoms() {
        // Monster Mash
        cardList.add(CONCLAVE.getName());
        cardList.add(GUARDIAN.getName());
        cardList.add(PIXIE.getName());
        cardList.add(VAMPIRE.getName());
        cardList.add(WEREWOLF.getName());
        cardList.add(BRIDGE_TROLL.getName());
        cardList.add(GIANT.getName());
        cardList.add(MESSENGER.getName());
        cardList.add(RATCATCHER.getName());
        cardList.add(STORYTELLER.getName());
        
        eventList.add(QUEST.getName());

        saveKingdom(MONSTER_MASH.getName());

        // Lost in the Woods
        cardList.add(BLESSED_VILLAGE.getName());
        cardList.add(DRUID.getName());
        cardList.add(FOOL.getName());
        cardList.add(SACRED_GROVE.getName());
        cardList.add(TRACKER.getName());
        cardList.add(CARAVAN_GUARD.getName());
        cardList.add(GUIDE.getName());
        cardList.add(HAUNTED_WOODS.getName());
        cardList.add(HIRELING.getName());
        cardList.add(RANGER.getName());
        cardList.add("Boon: " + THE_SKYS_GIFT.getName());
        cardList.add("Boon: " + THE_FIELDS_GIFT.getName());
        cardList.add("Boon: " + THE_SEAS_GIFT.getName());

        eventList.add(PILGRIMAGE.getName());

        saveKingdom(LOST_IN_THE_WOODS.getName());
    }

    private void addNocturneAndEmpiresKingdoms() {
        // Luftschloss
        cardList.add(CEMETERY.getName());
        cardList.add(CHANGELING.getName());
        cardList.add(EXORCIST.getName());
        cardList.add(FOOL.getName());
        cardList.add(SHEPHERD.getName());
        cardList.add(ARCHIVE.getName());
        cardList.add(CASTLES.getName());
        cardList.add(CATAPULT.getName());
        cardList.add(ENGINEER.getName());
        cardList.add(TEMPLE.getName());

        landmarkList.add(TOMB.getName());

        saveKingdom(LUFTSCHLOSS.getName());

        // Pooka Pranks
        cardList.add(FAITHFUL_HOUND.getName());
        cardList.add(GHOST_TOWN.getName());
        cardList.add(PIXIE.getName());
        cardList.add(POOKA.getName());
        cardList.add(SKULK.getName());
        cardList.add(CHARIOT_RACE.getName());
        cardList.add(FORUM.getName());
        cardList.add(GROUNDSKEEPER.getName());
        cardList.add(SACRIFICE.getName());
        cardList.add(SETTLERS.getName());

        eventList.add(BANQUET.getName());

        saveKingdom(POOKA_PRANKS.getName());
    }
}