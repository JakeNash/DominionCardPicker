package picker;

public enum KingdomName {

    // Basic cards
    BASIC("Basic"),

    // Recommended Old Dominion
    FIRST_GAME_1("First Game 1st Edition"),
    BIG_MONEY("Big Money"),
    INTERACTION("Interaction"),
    SIZE_DISTORTION_1("Size Distortion 1st Edition"),
    VILLAGE_SQUARE("Village Square"),

    // Recommended New Dominion
    FIRST_GAME_2("First Game 2nd Edition"),
    SIZE_DISTORTION_2("Size Distortion 2nd Edition"),
    DECK_TOP("Deck Top"),
    SLEIGHT_OF_HAND("Sleight of Hand"),
    IMPROVEMENTS("Improvements"),
    SILVER_AND_GOLD("Silver & Gold");

    private String name;

    KingdomName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
