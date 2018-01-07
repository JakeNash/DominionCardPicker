package picker.kingdom;

import picker.card.Card;
import picker.card.CardName;
import picker.card.CardRepository;
import picker.event.Event;
import picker.event.EventRepository;

import java.util.ArrayList;
import java.util.List;

import static picker.card.CardName.*;
import static picker.event.EventName.*;
import static picker.kingdom.KingdomName.*;

public class KingdomSorter {

    private final KingdomRepository kingdomRepository;
    private final CardRepository cardRepository;
    private final EventRepository eventRepository;

    private List<String> cardList;
    private List<String> eventList;
    private List<String> boxList;
    private List<String> otherSetupList;

    public KingdomSorter(KingdomRepository kingdomRepository, CardRepository cardRepository, EventRepository eventRepository) {
        this.kingdomRepository = kingdomRepository;
        this.cardRepository = cardRepository;
        this.eventRepository = eventRepository;
        cardList = new ArrayList<>();
        eventList = new ArrayList<>();
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
        addOldEmpiresAndDominionKingdoms();
        addNewEmpiresAndDominionKingdoms();
        addOldEmpiresAndIntrigueKingdoms();
        addNewEmpiresAndIntrigueKingdoms();
        addEmpiresAndSeasideKingdoms();
        addEmpiresAndAlchemyKingdoms();
        addEmpiresAndProsperityKingdoms();
        addEmpiresAndCornucopiaKingdoms();
        addEmpiresAndHinterlandsKingdoms();
        addEmpiresAndDarkAgesKingdoms();
        addEmpiresAndGuildsKingdoms();
        addEmpiresAndAdventuresKingdoms();

        addNocturneKingdoms();
        addOldNocturneAndDominionKingdoms();
        addNewNocturneAndDominionKingdoms();
        addOldNocturneAndIntrigueKingdoms();
        addNewNocturneAndIntrigueKingdoms();
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
        boxList.clear();
        otherSetupList.clear();

        for (String card : cardList) {
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

    private void saveKingdom(String name) {
        extractCardInfo();
        extractEventInfo();
        kingdomRepository.save(new Kingdom(name, cardList, eventList, boxList, otherSetupList));
    }

    private void addBasicCards() {
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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
        cardList.clear();
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

        eventList.clear();
        eventList.add(SCOUTING_PARTY.getName());

        saveKingdom(GENTLE_INTRO.getName());

        // Expert Intro
        cardList.clear();
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

        eventList.clear();
        eventList.add(MISSION.getName());
        eventList.add(PLAN.getName());

        saveKingdom(EXPERT_INTRO.getName());
    }

    private void addOldAdventuresAndDominionKingdoms() {
        // Level Up
        cardList.clear();
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

        eventList.clear();
        eventList.add(TRAINING.getName());

        saveKingdom(LEVEL_UP_1.getName());

        // Son of Size Distortion
        cardList.clear();
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

        eventList.clear();
        eventList.add(BONFIRE.getName());
        eventList.add(RAID.getName());

        saveKingdom(SON_OF_SIZE_DISTORTION_1.getName());
    }

    private void addNewAdventuresAndDominionKingdoms() {
        // Level Up
        cardList.clear();
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

        eventList.clear();
        eventList.add(TRAINING.getName());

        saveKingdom(LEVEL_UP_2.getName());

        // Son of Size Distortion
        cardList.clear();
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

        eventList.clear();
        eventList.add(BONFIRE.getName());
        eventList.add(RAID.getName());

        saveKingdom(SON_OF_SIZE_DISTORTION_2.getName());
    }

    private void addOldAdventuresAndIntrigueKingdoms() {
        // Royalty Factory
        cardList.clear();
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

        eventList.clear();
        eventList.add(PILGRIMAGE.getName());

        saveKingdom(ROYALTY_FACTORY_1.getName());

        // Masters of Finance
        cardList.clear();
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

        eventList.clear();
        eventList.add(BALL.getName());
        eventList.add(BORROW.getName());

        saveKingdom(MASTERS_OF_FINANCE.getName());
    }

    private void addNewAdventuresAndIntrigueKingdoms() {
        // Royalty Factory
        cardList.clear();
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

        eventList.clear();
        eventList.add(PILGRIMAGE.getName());

        saveKingdom(ROYALTY_FACTORY_2.getName());
    }

    private void addAdventuresAndSeasideKingdoms() {
        // Prince of Orange
        cardList.clear();
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

        eventList.clear();
        eventList.add(MISSION.getName());

        saveKingdom(PRINCE_OF_ORANGE.getName());

        // Gifts and Mathoms
        cardList.clear();
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

        eventList.clear();
        eventList.add(EXPEDITION.getName());
        eventList.add(QUEST.getName());

        saveKingdom(GIFTS_AND_MATHOMS.getName());
    }

    private void addAdventuresAndAlchemyKingdoms() {
        // Haste Potion
        cardList.clear();
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

        eventList.clear();
        eventList.add(PLAN.getName());

        saveKingdom(HASTE_POTION.getName());

        // Cursecatchers
        cardList.clear();
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

        eventList.clear();
        eventList.add(SAVE.getName());
        eventList.add(TRADE.getName());

        saveKingdom(CURSECATCHERS.getName());
    }

    private void addAdventuresAndProsperityKingdoms() {
        // Last Will and Monument
        cardList.clear();
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

        eventList.clear();
        eventList.add(INHERITANCE.getName());

        saveKingdom(LAST_WILL_AND_MONUMENT.getName());

        // Think Big
        cardList.clear();
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

        eventList.clear();
        eventList.add(BALL.getName());
        eventList.add(FERRY.getName());

        saveKingdom(THINK_BIG.getName());
    }

    private void addAdventuresAndCornucopiaKingdoms() {
        // The Hero's Return
        cardList.clear();
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

        eventList.clear();
        eventList.add(TRAVELLING_FAIR.getName());

        saveKingdom(THE_HEROS_RETURN.getName());

        // Seacraft and Witchcraft
        cardList.clear();
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

        eventList.clear();
        eventList.add(FERRY.getName());
        eventList.add(SEAWAY.getName());

        saveKingdom(SEACRAFT_AND_WITCHCRAFT.getName());
    }

    private void addAdventuresAndHinterlandsKingdoms() {
        // Traders and Raiders
        cardList.clear();
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

        eventList.clear();
        eventList.add(RAID.getName());

        saveKingdom(TRADERS_AND_RAIDERS.getName());

        // Journeys
        cardList.clear();
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

        eventList.clear();
        eventList.add(EXPEDITION.getName());
        eventList.add(INHERITANCE.getName());

        saveKingdom(JOURNEYS.getName());
    }

    private void addAdventuresAndDarkAgesKingdoms() {
        // Cemetery Polka
        cardList.clear();
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

        eventList.clear();
        eventList.add(ALMS.getName());

        saveKingdom(CEMETERY_POLKA.getName());

        // Groovy Decay
        cardList.clear();
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

        eventList.clear();
        eventList.add(LOST_ARTS.getName());
        eventList.add(PATHFINDING.getName());

        saveKingdom(GROOVY_DECAY.getName());
    }

    private void addAdventuresAndGuildsKingdoms() {
        // Spendthrift
        cardList.clear();
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

        eventList.clear();
        eventList.add(LOST_ARTS.getName());

        saveKingdom(SPENDTHRIFT.getName());

        // Queen of Tan
        cardList.clear();
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

        eventList.clear();
        eventList.add(PATHFINDING.getName());
        eventList.add(SAVE.getName());

        saveKingdom(QUEEN_OF_TAN.getName());
    }

    private void addEmpiresKingdoms() {

    }

    private void addOldEmpiresAndDominionKingdoms() {

    }

    private void addNewEmpiresAndDominionKingdoms() {

    }

    private void addOldEmpiresAndIntrigueKingdoms() {

    }

    private void addNewEmpiresAndIntrigueKingdoms() {

    }

    private void addEmpiresAndSeasideKingdoms() {

    }

    private void addEmpiresAndAlchemyKingdoms() {

    }

    private void addEmpiresAndProsperityKingdoms() {

    }

    private void addEmpiresAndCornucopiaKingdoms() {

    }

    private void addEmpiresAndHinterlandsKingdoms() {

    }

    private void addEmpiresAndDarkAgesKingdoms() {

    }

    private void addEmpiresAndGuildsKingdoms() {

    }

    private void addEmpiresAndAdventuresKingdoms() {

    }

    private void addNocturneKingdoms() {

    }

    private void addOldNocturneAndDominionKingdoms() {

    }

    private void addNewNocturneAndDominionKingdoms() {

    }

    private void addOldNocturneAndIntrigueKingdoms() {

    }

    private void addNewNocturneAndIntrigueKingdoms() {

    }

    private void addNocturneAndSeasideKingdoms() {

    }

    private void addNocturneAndAlchemyKingdoms() {

    }

    private void addNocturneAndProsperityKingdoms() {

    }

    private void addNocturneAndCornucopiaKingdoms() {

    }

    private void addNocturneAndHinterlandsKingdoms() {

    }

    private void addNocturneAndDarkAgesKingdoms() {

    }

    private void addNocturneAndGuildsKingdoms() {

    }

    private void addNocturneAndAdventuresKingdoms() {

    }

    private void addNocturneAndEmpiresKingdoms() {

    }
}


