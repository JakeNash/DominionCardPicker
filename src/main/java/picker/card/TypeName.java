package picker.card;

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
    RESERVE("Reserve"),
    TRAVELLER("Traveller"),
    CASTLE("Castle"),
    GATHERING("Gathering"),
    FATE("Fate"),
    NIGHT("Night"),
    DOOM("Doom"),
    HEIRLOOM("Heirloom"),
    SPIRIT("Spirit"),
    ZOMBIE("Zombie"),
    ;

    private String name;

    TypeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
