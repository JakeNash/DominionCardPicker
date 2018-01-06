package picker;

import java.util.ArrayList;
import java.util.List;

import static picker.CardName.*;
import static picker.KingdomName.*;

public class KingdomSorter {

    private final KingdomRepository kingdomRepository;
    private final CardRepository cardRepository;

    private List<String> cardList;
    private List<String> boxList;
    private List<String> otherSetupList;

    public KingdomSorter(KingdomRepository kingdomRepository, CardRepository cardRepository) {
        this.kingdomRepository = kingdomRepository;
        this.cardRepository = cardRepository;
        cardList = new ArrayList<>();
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

        addCornucopiaKingdoms();
        addOldCornucopiaAndDominionKingdoms();
        addNewCornucopiaAndDominionKingdoms();
        addOldCornucopiaAndIntrigueKingdoms();
        addNewCornucopiaAndIntrigueKingdoms();
        addCornucopiaAndSeasideKingdoms();
        addCornucopiaAndAlchemyKingdoms();
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
        addOldDarkAgesAndDominionKingdoms();
        addNewDarkAgesAndDominionKingdoms();
        addOldDarkAgesAndIntrigueKingdoms();
        addNewDarkAgesAndIntrigueKingdoms();
        addDarkAgesAndSeasideKingdoms();
        addDarkAgesAndAlchemyKingdoms();
        addDarkAgesAndProsperityKingdoms();
        addDarkAgesAndCornucopiaKingdoms();
        addDarkAgesAndHinterlandsKingdoms();

        addGuildsKingdoms();
        addOldGuildsAndDominionKingdoms();
        addNewGuildsAndDominionKingdoms();
        addOldGuildsAndIntrigueKingdoms();
        addNewGuildsAndIntrigueKingdoms();
        addGuildsAndSeasideKingdoms();
        addGuildsAndAlchemyKingdoms();
        addGuildsAndProsperityKingdoms();
        addGuildsAndCornucopiaKingdoms();
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

    private void saveKingdom(String name) {
        extractCardInfo();
        kingdomRepository.save(new Kingdom(name, cardList, boxList, otherSetupList));
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

    private void addCornucopiaKingdoms() {

    }

    private void addOldCornucopiaAndDominionKingdoms() {

    }

    private void addNewCornucopiaAndDominionKingdoms() {

    }

    private void addOldCornucopiaAndIntrigueKingdoms() {

    }

    private void addNewCornucopiaAndIntrigueKingdoms() {

    }

    private void addCornucopiaAndSeasideKingdoms() {

    }

    private void addCornucopiaAndAlchemyKingdoms() {

    }

    private void addCornucopiaAndProsperityKingdoms() {

    }

    private void addHinterlandsKingdoms() {

    }

    private void addOldHinterlandsAndDominionKingdoms() {

    }

    private void addNewHinterlandsAndDominionKingdoms() {

    }

    private void addOldHinterlandsAndIntrigueKingdoms() {

    }

    private void addNewHinterlandsAndIntrigueKingdoms() {

    }

    private void addHinterlandsAndSeasideKingdoms() {

    }

    private void addHinterlandsAndAlchemyKingdoms() {

    }

    private void addHinterlandsAndProsperityKingdoms() {

    }

    private void addHinterlandsAndCornucopiaKingdoms() {

    }

    private void addDarkAgesKingdoms() {

    }

    private void addOldDarkAgesAndDominionKingdoms() {

    }

    private void addNewDarkAgesAndDominionKingdoms() {

    }

    private void addOldDarkAgesAndIntrigueKingdoms() {

    }

    private void addNewDarkAgesAndIntrigueKingdoms() {

    }

    private void addDarkAgesAndSeasideKingdoms() {

    }

    private void addDarkAgesAndAlchemyKingdoms() {

    }

    private void addDarkAgesAndProsperityKingdoms() {

    }

    private void addDarkAgesAndCornucopiaKingdoms() {

    }

    private void addDarkAgesAndHinterlandsKingdoms() {

    }

    private void addGuildsKingdoms() {

    }

    private void addOldGuildsAndDominionKingdoms() {

    }

    private void addNewGuildsAndDominionKingdoms() {

    }

    private void addOldGuildsAndIntrigueKingdoms() {

    }

    private void addNewGuildsAndIntrigueKingdoms() {

    }

    private void addGuildsAndSeasideKingdoms() {

    }

    private void addGuildsAndAlchemyKingdoms() {

    }

    private void addGuildsAndProsperityKingdoms() {

    }

    private void addGuildsAndCornucopiaKingdoms() {

    }

    private void addGuildsAndHinterlandsKingdoms() {

    }

    private void addGuildsAndDarkAgesKingdoms() {

    }

    private void addAdventuresKingdoms() {

    }

    private void addOldAdventuresAndDominionKingdoms() {

    }

    private void addNewAdventuresAndDominionKingdoms() {

    }

    private void addOldAdventuresAndIntrigueKingdoms() {

    }

    private void addNewAdventuresAndIntrigueKingdoms() {

    }

    private void addAdventuresAndSeasideKingdoms() {

    }

    private void addAdventuresAndAlchemyKingdoms() {

    }

    private void addAdventuresAndProsperityKingdoms() {

    }

    private void addAdventuresAndCornucopiaKingdoms() {

    }

    private void addAdventuresAndHinterlandsKingdoms() {

    }

    private void addAdventuresAndDarkAgesKingdoms() {

    }

    private void addAdventuresAndGuildsKingdoms() {

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


