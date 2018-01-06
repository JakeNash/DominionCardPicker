package picker;

public enum KingdomName {

    // Basic cards
    BASIC("Basic"),

    // Old Dominion
    FIRST_GAME_1("First Game 1st Edition"),
    BIG_MONEY("Big Money"),
    INTERACTION("Interaction"),
    SIZE_DISTORTION_1("Size Distortion 1st Edition"),
    VILLAGE_SQUARE("Village Square"),

    // New Dominion
    FIRST_GAME_2("First Game 2nd Edition"),
    SIZE_DISTORTION_2("Size Distortion 2nd Edition"),
    DECK_TOP("Deck Top"),
    SLEIGHT_OF_HAND("Sleight of Hand"),
    IMPROVEMENTS("Improvements"),
    SILVER_AND_GOLD("Silver & Gold"),

    // Old Intrigue
    VICTORY_DANCE_1("Victory Dance 1st Edition"),
    SECRET_SCHEMES("Secret Schemes"),
    BEST_WISHES_1("Best Wishes 1st Edition"),

    // New Intrigue
    VICTORY_DANCE_2("Victory Dance 2nd Edition"),
    THE_PLOT_THICKENS("The Plot Thickens"),
    BEST_WISHES_2("Best Wishes 2nd Edition"),

    // Old Intrigue + Dominion
    DECONSTRUCTION_1("Deconstruction 1st Edition"),
    HAND_MADNESS("Hand Madness"),
    UNDERLINGS_1("Underlings 1st Edition"),

    // New Intrigue + Dominion
    UNDERLINGS_2("Underlings 2nd Edition"),
    GRAND_SCHEME("Grand Scheme"),
    DECONSTRUCTION_2("Deconstruction 2nd Edition"),

    // Seaside
    HIGH_SEAS("High Seas"),
    BURIED_TREASURE("Buried Treasure"),
    SHIPWRECKS("Shipswrecks"),

    // Old Seaside + Dominion
    REACH_FOR_TOMORROW_1("Reach for Tomorrow 1st Edition"),
    REPETITION_1("Repetition 1st Edition"),
    GIVE_AND_TAKE("Give and Take"),

    // New Seaside + Dominion
    REACH_FOR_TOMORROW_2("Reach for Tomorrow 2nd Edition"),
    REPETITION_2("Repetition 2nd Edition"),

    // Seaside + Intrigue
    A_STAR_TO_STEER_BY("A Star to Steer By"),
    SHORE_PATROL("Shore Patrol"),
    BRIDGE_CROSSING("Bridge Crossing"),

    // Old Alchemy + Dominion
    FORBIDDEN_ARTS_1("Forbidden Arts 1st Edition"),
    POTION_MIXERS_1("Potion Mixers 1st Edition"),
    CHEMISTRY_LESSON_1("Chemistry Lesson 1st Edition"),

    // New Alchemy + Dominion
    FORBIDDEN_ARTS_2("Forbidden Arts 2nd Edition"),
    POTION_MIXERS_2("Potion Mixers 2nd Edition"),
    CHEMISTRY_LESSON_2("Chemistry Lesson 2nd Edition"),

    // Old Alchemy + Intrigue
    SERVANTS_1("Servants 1st Edition"),
    SECRET_RESEARCH("Secret Research"),
    POOLS_TOOLS_AND_FOOLS_1("Pools, Tools, and Fools 1st Edition"),

    // New Alchemy + Intrigue
    SERVANTS_2("Servants 2nd Edition"),
    POOLS_TOOLS_AND_FOOLS_2("Pools, Tools, and Fools 2nd Edition"),

    // Alchemy + Seaside
    FOREWARNED("Forewarned"),
    GUMMED_UP("Gummed Up"),

    // Prosperity

    // Old Prosperity + Dominion

    // New Prosperity + Dominion

    // Old Prosperity + Intrigue

    // New Prosperity + Intrigue

    // Prosperity + Seaside

    // Prosperity + Alchemy

    // Cornucopia

    // Old Cornucopia + Dominion

    // New Cornucopia + Dominion

    // Old Cornucopia + Intrigue

    // New Cornucopia + Intrigue

    // Cornucopia + Seaside

    // Cornucopia + Alchemy

    // Cornucopia + Prosperity

    // Hinterlands

    // Old Hinterlands + Dominion

    // New Hinterlands + Dominion

    // Old Hinterlands + Intrigue

    // New Hinterlands + Intrigue

    // Hinterlands + Seaside

    // Hinterlands + Alchemy

    // Hinterlands + Prosperity

    // Hinterlands + Cornucopia

    // Dark Ages

    // Old Dark Ages + Dominion

    // New Dark Ages + Dominion

    // Old Dark Ages + Intrigue

    // New Dark Ages + Intrigue

    // Dark Ages + Seaside

    // Dark Ages + Alchemy

    // Dark Ages + Prosperity

    // Dark Ages + Cornucopia

    // Dark Ages + Hinterlands

    // Guilds

    // Old Guilds + Dominion

    // New Guilds + Dominion

    // Old Guilds + Intrigue

    // New Guilds + Intrigue

    // Guilds + Seaside

    // Guilds + Alchemy

    // Guilds + Prosperity

    // Guilds + Cornucopia

    // Guilds + Hinterlands

    // Guilds + Dark Ages

    // Adventures

    // Old Adventures + Dominion

    // New Adventures + Dominion

    // Old Adventures + Intrigue

    // New Adventures + Intrigue

    // Adventures + Seaside

    // Adventures + Alchemy

    // Adventures + Prosperity

    // Adventures + Cornucopia

    // Adventures + Hinterlands

    // Adventures + Dark Ages

    // Adventures + Guilds

    // Empires

    // Old Empires + Dominion

    // New Empires + Dominion

    // Old Empires + Intrigue

    // New Empires + Intrigue

    // Empires + Seaside

    // Empires + Alchemy

    // Empires + Prosperity

    // Empires + Cornucopia

    // Empires + Hinterlands

    // Empires + Dark Ages

    // Empires + Guilds

    // Empires + Adventures

    // Nocturne

    // Old Nocturne + Dominion

    // New Nocturne + Dominion

    // Old Nocturne + Intrigue

    // New Nocturne + Intrigue

    // Nocturne + Seaside

    // Nocturne + Alchemy

    // Nocturne + Prosperity

    // Nocturne + Cornucopia

    // Nocturne + Hinterlands

    // Nocturne + Dark Ages

    // Nocturne + Guilds

    // Nocturne + Adventures

    // Nocturne + Empires

    ;

    private String name;

    KingdomName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
