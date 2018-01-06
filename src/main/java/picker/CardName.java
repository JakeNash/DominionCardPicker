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
    VASSAL("Vassal"),

    // Intrigue
    COURTYARD("Courtyard"),
    PAWN("Pawn"),
    MASQUERADE("Masquerade"),
    SHANTY_TOWN("Shanty Town"),
    STEWARD("Steward"),
    SWINDLER("Swindler"),
    WISHING_WELL("Wishing Well"),
    BARON("Baron"),
    BRIDGE("Bridge"),
    CONSPIRATOR("Conspirator"),
    IRONWORKS("Ironworks"),
    MINING_VILLAGE("Mining Village"),
    DUKE("Duke"),
    MINION("Minion"),
    TORTURER("Torturer"),
    TRADING_POST("Trading Post"),
    UPGRADE("Upgrade"),
    HAREM("Harem"),
    NOBLES("Nobles"),

    // Intrigue 1st Edition
    SECRET_CHAMBER("Secret Chamber"),
    GREAT_HALL("Great Hall"),
    COPPERSMITH("Coppersmith"),
    SCOUT("Scout"),
    SABOTEUR("Saboteur"),
    TRIBUTE("Tribute"),

    // Intrigue 2nd Edition
    LURKER("Lurker"),
    DIPLOMAT("Diplomat"),
    MILL("Mill"),
    SECRET_PASSAGE("Secret Passage"),
    COURTIER("Courtier"),
    PATROL("Patrol"),
    REPLACE("Replace"),

    // Seaside
    EMBARGO("Embargo"),
    HAVEN("Haven"),
    LIGHTHOUSE("Lighthouse"),
    NATIVE_VILLAGE("Native Village"),
    PEARL_DIVER("Pearl Diver"),
    AMBASSADOR("Ambassador"),
    FISHING_VILLAGE("Fishing Village"),
    LOOKOUT("Lookout"),
    SMUGGLERS("Smugglers"),
    WAREHOUSE("Warehouse"),
    CARAVAN("Caravan"),
    CUTPURSE("Cutpurse"),
    ISLAND("Island"),
    NAVIGATOR("Navigator"),
    PIRATE_SHIP("Pirate Ship"),
    SALVAGER("Salvager"),
    SEA_HAG("Sea Hag"),
    TREASURE_MAP("Treasure Map"),
    BAZAAR("Bazaar"),
    EXPLORER("Explorer"),
    GHOST_SHIP("Ghost Ship"),
    MERCHANT_SHIP("Merchant Ship"),
    OUTPOST("Outpost"),
    TACTICIAN("Tactician"),
    TREASURY("Treasury"),
    WHARF("Wharf"),

    // Alchemy
    POTION("Potion"),
    HERBALIST("Herbalist"),
    APPRENTICE("Apprentice"),
    TRANSMUTE("Transmute"),
    VINEYARD("Vineyard"),
    APOTHECARY("Apothecary"),
    SCRYING_POOL("Scrying Pool"),
    UNIVERSITY("University"),
    ALCHEMIST("Alchemist"),
    FAMILIAR("Familiar"),
    PHILOSOPHERS_STONE("Philosopher's Stone"),
    GOLEM("Golem"),
    POSSESSION("Possession"),

    // Prosperity

    // Cornucopia

    // Hinterlands

    // Dark Ages

    // Guilds

    // Adventures

    // Empires

    // Nocturne
    ;

    private String name;

    CardName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
