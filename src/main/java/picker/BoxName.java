package picker;

public enum BoxName {

    BASIC("Basic"),
    DOMINION("Dominion"),
    OLD_DOMINION("Dominion 1st Edition"),
    NEW_DOMINION("Dominion 2nd Edition"),
    INTRIGUE("Intrigue"),
    OLD_INTRIGUE("Intrigue 1st Edition"),
    NEW_INTRIGUE("Intrigue 2nd Edition"),
    SEASIDE("Seaside"),
    ALCHEMY("Alchemy"),
    PROSPERITY("Prosperity"),
    CORNUCOPIA("Cornucopia"),
    HINTERLANDS("Hinterlands"),
    DARK_AGES("Dark Ages"),
    GUILDS("Guilds"),
    ;

    private String name;

    BoxName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
