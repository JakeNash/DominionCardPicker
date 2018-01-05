package picker;

public enum TypeName {

    TREASURE("Treasure"),
    CURSE("Curse"),
    VICTORY("Victory"),
    ACTION("Action"),
    REACTION("Reaction"),
    ATTACK("Attack"),
    DURATION("Duration"),
    ;

    private String name;

    TypeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
