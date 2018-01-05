package picker;

import java.util.ArrayList;
import java.util.List;

import static picker.CardName.*;
import static picker.KingdomName.*;

public class KingdomSorter {

    private final KingdomRepository kingdomRepository;

    public KingdomSorter(KingdomRepository kingdomRepository) {
        this.kingdomRepository = kingdomRepository;
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
        addOldSeasideAndIntrigueKingdoms();
        addNewSeasideAndIntrigueKingdoms();

        addAlchemyKingdoms();
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

    private void addBasicCards() {
        List<String> cardList = new ArrayList<>();

        cardList.add(COPPER.getName());
        cardList.add(CURSE.getName());
        cardList.add(DUCHY.getName());
        cardList.add(ESTATE.getName());
        cardList.add(GOLD.getName());
        cardList.add(PROVINCE.getName());
        cardList.add(SILVER.getName());

        kingdomRepository.save(new Kingdom(BASIC.getName(), cardList));
    }

    private void addOldDominionKingdoms() {
        List<String> cardList = new ArrayList<>();

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

        kingdomRepository.save(new Kingdom(FIRST_GAME_1.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(BIG_MONEY.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(INTERACTION.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(SIZE_DISTORTION_1.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(VILLAGE_SQUARE.getName(), cardList));
    }

    private void addNewDominionKingdoms() {
        List<String> cardList = new ArrayList<>();

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

        kingdomRepository.save(new Kingdom(FIRST_GAME_2.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(SIZE_DISTORTION_2.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(DECK_TOP.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(SLEIGHT_OF_HAND.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(IMPROVEMENTS.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(SILVER_AND_GOLD.getName(), cardList));
    }

    private void addOldIntrigueKingdoms() {
        List<String> cardList = new ArrayList<>();

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

        kingdomRepository.save(new Kingdom(VICTORY_DANCE_1.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(SECRET_SCHEMES.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(BEST_WISHES_1.getName(), cardList));
    }

    private void addNewIntrigueKingdoms() {
        List<String> cardList = new ArrayList<>();

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

        kingdomRepository.save(new Kingdom(VICTORY_DANCE_2.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(THE_PLOT_THICKENS.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(BEST_WISHES_2.getName(), cardList));
    }

    private void addOldIntrigueAndDominionKingdoms() {
        List<String> cardList = new ArrayList<>();

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

        kingdomRepository.save(new Kingdom(DECONSTRUCTION_1.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(HAND_MADNESS.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(UNDERLINGS_1.getName(), cardList));
    }

    private void addNewIntrigueAndDominionKingdoms() {
        List<String> cardList = new ArrayList<>();

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

        kingdomRepository.save(new Kingdom(UNDERLINGS_2.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(GRAND_SCHEME.getName(), cardList));

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

        kingdomRepository.save(new Kingdom(DECONSTRUCTION_2.getName(), cardList));
    }

    private void addSeasideKingdoms() {

    }

    private void addOldSeasideAndDominionKingdoms() {

    }

    private void addNewSeasideAndDominionKingdoms() {

    }

    private void addOldSeasideAndIntrigueKingdoms() {

    }

    private void addNewSeasideAndIntrigueKingdoms() {

    }

    private void addAlchemyKingdoms() {

    }

    private void addOldAlchemyAndDominionKingdoms() {

    }

    private void addNewAlchemyAndDominionKingdoms() {

    }

    private void addOldAlchemyAndIntrigueKingdoms() {

    }

    private void addNewAlchemyAndIntrigueKingdoms() {

    }

    private void addAlchemyAndSeasideKingdoms() {

    }

    private void addProsperityKingdoms() {

    }

    private void addOldProsperityAndDominionKingdoms() {

    }

    private void addNewProsperityAndDominionKingdoms() {

    }

    private void addOldProsperityAndIntrigueKingdoms() {

    }

    private void addNewProsperityAndIntrigueKingdoms() {

    }

    private void addProsperityAndSeasideKingdoms() {

    }

    private void addProsperityAndAlchemyKingdoms() {

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


