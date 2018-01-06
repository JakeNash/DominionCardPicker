package picker;

public enum TypeName {

    TREASURE("Treasure"),
    CURSE("Curse"),
    VICTORY("Victory"),
    ACTION("Action"),
    REACTION("Reaction"),
    ATTACK("Attack"),
    DURATION("Duration"),
    PRIZE("Prize"),
    RUINS("Ruins"),
    LOOTER("Looter"),
    KNIGHT("Knight"),
    SHELTER("Shelter"),
    ;

    private String name;

    TypeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
