package picker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KingdomSorter {

    private final KingdomRepository kingdomRepository;

    public KingdomSorter(KingdomRepository kingdomRepository) {
        this.kingdomRepository = kingdomRepository;
    }

    public void createKingdoms() {
        addBasicCards();

        addOldDominionKingdoms();
        addNewDominionKingdoms();
    }

    private void addBasicCards() {
        List<String> cardList = new ArrayList<>();

        cardList.add(CardName.COPPER.getName());
        cardList.add(CardName.CURSE.getName());
        cardList.add(CardName.DUCHY.getName());
        cardList.add(CardName.ESTATE.getName());
        cardList.add(CardName.GOLD.getName());
        cardList.add(CardName.PROVINCE.getName());
        cardList.add(CardName.SILVER.getName());

        kingdomRepository.save(new Kingdom(KingdomName.BASIC.getName(), cardList));
    }

    private void addOldDominionKingdoms() {
        List<String> cardList = new ArrayList<>();

        // First Game
        cardList.add(CardName.CELLAR.getName());
        cardList.add(CardName.MARKET.getName());
        cardList.add(CardName.MILITIA.getName());
        cardList.add(CardName.MINE.getName());
        cardList.add(CardName.MOAT.getName());
        cardList.add(CardName.REMODEL.getName());
        cardList.add(CardName.SMITHY.getName());
        cardList.add(CardName.VILLAGE.getName());
        cardList.add(CardName.WOODCUTTER.getName());
        cardList.add(CardName.WORKSHOP.getName());

        kingdomRepository.save(new Kingdom(KingdomName.FIRST_GAME_1.getName(), cardList));

        // Big Money
        cardList.clear();
        cardList.add(CardName.ADVENTURER.getName());
        cardList.add(CardName.BUREAUCRAT.getName());
        cardList.add(CardName.CHANCELLOR.getName());
        cardList.add(CardName.CHAPEL.getName());
        cardList.add(CardName.FEAST.getName());
        cardList.add(CardName.LABORATORY.getName());
        cardList.add(CardName.MARKET.getName());
        cardList.add(CardName.MINE.getName());
        cardList.add(CardName.MONEYLENDER.getName());
        cardList.add(CardName.THRONE_ROOM.getName());

        kingdomRepository.save(new Kingdom(KingdomName.BIG_MONEY.getName(), cardList));

        // Interaction
        cardList.clear();
        cardList.add(CardName.BUREAUCRAT.getName());
        cardList.add(CardName.CHANCELLOR.getName());
        cardList.add(CardName.COUNCIL_ROOM.getName());
        cardList.add(CardName.FESTIVAL.getName());
        cardList.add(CardName.LIBRARY.getName());
        cardList.add(CardName.MILITIA.getName());
        cardList.add(CardName.MOAT.getName());
        cardList.add(CardName.SPY.getName());
        cardList.add(CardName.THIEF.getName());
        cardList.add(CardName.VILLAGE.getName());

        kingdomRepository.save(new Kingdom(KingdomName.INTERACTION.getName(), cardList));

        // Size Distortion
        cardList.clear();
        cardList.add(CardName.CELLAR.getName());
        cardList.add(CardName.CHAPEL.getName());
        cardList.add(CardName.FEAST.getName());
        cardList.add(CardName.GARDENS.getName());
        cardList.add(CardName.LABORATORY.getName());
        cardList.add(CardName.THIEF.getName());
        cardList.add(CardName.VILLAGE.getName());
        cardList.add(CardName.WITCH.getName());
        cardList.add(CardName.WOODCUTTER.getName());
        cardList.add(CardName.WORKSHOP.getName());

        kingdomRepository.save(new Kingdom(KingdomName.SIZE_DISTORTION_1.getName(), cardList));

        // Village Square
        cardList.clear();
        cardList.add(CardName.BUREAUCRAT.getName());
        cardList.add(CardName.CELLAR.getName());
        cardList.add(CardName.FESTIVAL.getName());
        cardList.add(CardName.LIBRARY.getName());
        cardList.add(CardName.MARKET.getName());
        cardList.add(CardName.REMODEL.getName());
        cardList.add(CardName.SMITHY.getName());
        cardList.add(CardName.THRONE_ROOM.getName());
        cardList.add(CardName.VILLAGE.getName());
        cardList.add(CardName.WOODCUTTER.getName());

        kingdomRepository.save(new Kingdom(KingdomName.VILLAGE_SQUARE.getName(), cardList));
    }

    private void addNewDominionKingdoms() {
        List<String> cardList = new ArrayList<>();

        // First Game
        cardList.add(CardName.CELLAR.getName());
        cardList.add(CardName.MARKET.getName());
        cardList.add(CardName.MERCHANT.getName());
        cardList.add(CardName.MILITIA.getName());
        cardList.add(CardName.MINE.getName());
        cardList.add(CardName.MOAT.getName());
        cardList.add(CardName.REMODEL.getName());
        cardList.add(CardName.SMITHY.getName());
        cardList.add(CardName.VILLAGE.getName());
        cardList.add(CardName.WORKSHOP.getName());

        kingdomRepository.save(new Kingdom(KingdomName.FIRST_GAME_2.getName(), cardList));

        // Size Distortion
        cardList.clear();
        cardList.add(CardName.ARTISAN.getName());
        cardList.add(CardName.BANDIT.getName());
        cardList.add(CardName.BUREAUCRAT.getName());
        cardList.add(CardName.CHAPEL.getName());
        cardList.add(CardName.FESTIVAL.getName());
        cardList.add(CardName.GARDENS.getName());
        cardList.add(CardName.SENTRY.getName());
        cardList.add(CardName.THRONE_ROOM.getName());
        cardList.add(CardName.WITCH.getName());
        cardList.add(CardName.WORKSHOP.getName());

        kingdomRepository.save(new Kingdom(KingdomName.SIZE_DISTORTION_2.getName(), cardList));

        // Deck Top
        cardList.clear();
        cardList.add(CardName.ARTISAN.getName());
        cardList.add(CardName.BUREAUCRAT.getName());
        cardList.add(CardName.COUNCIL_ROOM.getName());
        cardList.add(CardName.FESTIVAL.getName());
        cardList.add(CardName.HARBINGER.getName());
        cardList.add(CardName.LABORATORY.getName());
        cardList.add(CardName.MONEYLENDER.getName());
        cardList.add(CardName.SENTRY.getName());
        cardList.add(CardName.VASSAL.getName());
        cardList.add(CardName.VILLAGE.getName());

        kingdomRepository.save(new Kingdom(KingdomName.DECK_TOP.getName(), cardList));

        // Sleight of Hand
        cardList.clear();
        cardList.add(CardName.CELLAR.getName());
        cardList.add(CardName.COUNCIL_ROOM.getName());
        cardList.add(CardName.FESTIVAL.getName());
        cardList.add(CardName.GARDENS.getName());
        cardList.add(CardName.LIBRARY.getName());
        cardList.add(CardName.HARBINGER.getName());
        cardList.add(CardName.MILITIA.getName());
        cardList.add(CardName.POACHER.getName());
        cardList.add(CardName.SMITHY.getName());
        cardList.add(CardName.THRONE_ROOM.getName());

        kingdomRepository.save(new Kingdom(KingdomName.SLEIGHT_OF_HAND.getName(), cardList));

        // Improvements
        cardList.clear();
        cardList.add(CardName.ARTISAN.getName());
        cardList.add(CardName.CELLAR.getName());
        cardList.add(CardName.MARKET.getName());
        cardList.add(CardName.MERCHANT.getName());
        cardList.add(CardName.MINE.getName());
        cardList.add(CardName.MOAT.getName());
        cardList.add(CardName.MONEYLENDER.getName());
        cardList.add(CardName.POACHER.getName());
        cardList.add(CardName.REMODEL.getName());
        cardList.add(CardName.WITCH.getName());

        kingdomRepository.save(new Kingdom(KingdomName.IMPROVEMENTS.getName(), cardList));

        // Silver & Gold
        cardList.clear();
        cardList.add(CardName.BANDIT.getName());
        cardList.add(CardName.BUREAUCRAT.getName());
        cardList.add(CardName.CHAPEL.getName());
        cardList.add(CardName.HARBINGER.getName());
        cardList.add(CardName.LABORATORY.getName());
        cardList.add(CardName.MERCHANT.getName());
        cardList.add(CardName.MINE.getName());
        cardList.add(CardName.MONEYLENDER.getName());
        cardList.add(CardName.THRONE_ROOM.getName());
        cardList.add(CardName.VASSAL.getName());

        kingdomRepository.save(new Kingdom(KingdomName.SILVER_AND_GOLD.getName(), cardList));
    }
}
