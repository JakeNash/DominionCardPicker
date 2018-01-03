package picker;

public enum CardName {

    // Basic Supply
    COPPER("Copper"),
    CURSE("Curse"),
    DUCHY("Duchy"),
    ESTATE("Estate"),
    GOLD("Gold"),
    PROVINCE("Province"),
    SILVER("Silver"),

    // Dominion
    BUREAUCRAT("Bureaucrat"),
    CELLAR("Cellar"),
    CHAPEL("Chapel"),
    COUNCIL_ROOM("Council Room"),
    FESTIVAL("Festival"),
    GARDENS("Gardens"),
    LABORATORY("Laboratory"),
    LIBRARY("Library"),
    MARKET("Market"),
    MILITIA("Militia"),
    MINE("Mine"),
    MOAT("Moat"),
    MONEYLENDER("Moneylender"),
    REMODEL("Remodel"),
    SMITHY("Smithy"),
    THRONE_ROOM("Throne Room"),
    VILLAGE("Village"),
    WITCH("Witch"),
    WORKSHOP("Workshop"),

    // Dominion 1st Edition
    ADVENTURER("Adventurer"),
    CHANCELLOR("Chancellor"),
    FEAST("Feast"),
    SPY("Spy"),
    THIEF("Thief"),
    WOODCUTTER("Woodcutter"),

    // Dominion 2nd Edition
    ARTISAN("Artisan"),
    BANDIT("Bandit"),
    HARBINGER("Harbinger"),
    MERCHANT("Merchant"),
    POACHER("Poacher"),
    SENTRY("Sentry"),
    VASSAL("Vassal");

    private String name;

    CardName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
