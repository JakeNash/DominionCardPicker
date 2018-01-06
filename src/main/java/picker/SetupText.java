package picker;

public enum SetupText {

    EMBARGO_SETUP("Embargo tokens"),
    NATIVE_VILLAGE_SETUP("Native Village mats"),
    PIRATE_SHIP_SETUP("Pirate Ship mats"),
    COIN_TOKEN_SETUP("Coin tokens"),
    ISLAND_SETUP("Island mats"),
    POTION_SETUP("Potions"),
    PLATINUM_SETUP("Platinums"),
    COLONY_SETUP("Colonies"),
    TRADE_ROUTE_SETUP("Trade Route mat"),
    VICTORY_TOKEN_SETUP("Victory tokens"),
    TOURNAMENT_SETUP("Prizes"),
    YOUNG_WITCH_SETUP("Choose a Bane"),
    SHELTERS_SETUP("Deal Shelters to everyone"),
    HERMIT_SETUP("Madmen"),
    URCHIN_SETUP("Mercenaries"),
    RUINS_SETUP("Shuffle Ruins & place face down except top card"),
    SPOILS_SETUP("Spoils"),
    KNIGHTS_SETUP("Shuffle Knights & place face down except top card"),
    ;

    private String text;

    SetupText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
