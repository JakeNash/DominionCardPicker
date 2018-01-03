package picker;

public enum BoxName {

    BASIC("Basic"),
    DOMINION("Dominion"),
    OLD_DOMINION("Dominion 1st Edition"),
    NEW_DOMINION("Dominion 2nd Edition");

    private String name;

    BoxName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
