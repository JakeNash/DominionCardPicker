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
    PLATINUM("Platinum"),
    COLONY("Colony"),
    LOAN("Loan"),
    TRADE_ROUTE("Trade Route"),
    WATCHTOWER("Watchtower"),
    BISHOP("Bishop"),
    MONUMENT("Monument"),
    QUARRY("Quarry"),
    TALISMAN("Talisman"),
    WORKERS_VILLAGE("Worker's Village"),
    CITY("City"),
    CONTRABAND("Contraband"),
    COUNTING_HOUSE("Counting House"),
    MINT("Mint"),
    MOUNTEBANK("Mountebank"),
    RABBLE("Rabble"),
    ROYAL_SEAL("Royal Seal"),
    VAULT("Vault"),
    VENTURE("Venture"),
    GOONS("Goons"),
    GRAND_MARKET("Grand Market"),
    HOARD("Hoard"),
    BANK("Bank"),
    EXPAND("Expand"),
    FORGE("Forge"),
    KINGS_COURT("King's Court"),
    PEDDLER("Peddler"),

    // Cornucopia
    HAMLET("Hamlet"),
    FORTUNE_TELLER("Fortune Teller"),
    MENAGERIE("Menagerie"),
    FARMING_VILLAGE("Farming Village"),
    HORSE_TRADERS("Horse Traders"),
    REMAKE("Remake"),
    TOURNAMENT("Tournament"),
    YOUNG_WITCH("Young Witch"),
    HARVEST("Harvest"),
    HORN_OF_PLENTY("Horn of Plenty"),
    HUNTING_PARTY("Hunting Party"),
    JESTER("Jester"),
    FAIRGROUNDS("Fairgrounds"),
    BAG_OF_GOLD("Bag of Gold"),
    DIADEM("Diadem"),
    FOLLOWERS("Followers"),
    PRINCESS("Princess"),
    TRUSTY_STEED("Trusty Steed"),

    // Hinterlands
    CROSSROADS("Crossroads"),
    DUCHESS("Duchess"),
    FOOLS_GOLD("Fool's Gold"),
    DEVELOP("Develop"),
    OASIS("Oasis"),
    ORACLE("Oracle"),
    SCHEME("Scheme"),
    TUNNEL("Tunnel"),
    JACK_OF_ALL_TRADES("Jack of All Trades"),
    NOBLE_BRIGAND("Noble Brigand"),
    NOMAD_CAMP("Nomad Camp"),
    SILK_ROAD("Silk Road"),
    SPICE_MERCHANT("Spice Merchant"),
    TRADER("Trader"),
    CACHE("Cache"),
    CARTOGRAPHER("Cartographer"),
    EMBASSY("Embassy"),
    HAGGLER("Haggler"),
    HIGHWAY("Highway"),
    ILL_GOTTEN_GAINS("Ill-Gotten Gains"),
    INN("Inn"),
    MANDARIN("Mandarin"),
    MARGRAVE("Margrave"),
    STABLES("Stables"),
    BORDER_VILLAGE("Border Village"),
    FARMLAND("Farmland"),

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
