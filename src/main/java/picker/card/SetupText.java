package picker.card;

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
    BAKER_SETUP("Give everyone a coin token"),
    RESERVE_SETUP("Tavern mats"),
    PAGE_SETUP("Page Traveller pile"),
    PEASANT_SETUP("Peasant Traveller pile"),
    JOURNEY_TOKEN_SETUP("Journey tokens"),
    MINUS_COIN_SETUP("-$1 tokens"),
    MINUS_CARD_SETUP("-1 Card tokens"),
    PLUS_CARD_SETUP("+1 Card tokens"),
    PLUS_ACTION_SETUP("+1 Action tokens"),
    PLUS_BUY_SETUP("+1 Buy tokens"),
    PLUS_COIN_SETUP("+$1 tokens"),
    MINUS_COST_SETUP("-$2 Cost tokens"),
    TRASH_TOKEN_SETUP("Trashing tokens"),
    ESTATE_TOKEN_SETUP("Estate tokens"),
    DEBT_TOKEN_SETUP("Debt tokens"),
    TAX_SETUP("Add 1 Debt to each Supply pile"),
    AQUEDUCT_SETUP("Put 8 Victory tokens on the Silver and Gold piles"),
    ARENA_SETUP("Put 6 Victory tokens on the Arena per player"),
    BASILICA_SETUP("Put 6 Victory tokens on the Basilica per player"),
    BATHS_SETUP("Put 6 Victory tokens on the Baths per player"),
    BATTLEFIELD_SETUP("Put 6 Victory tokens on the Battlefield per player"),
    COLONNADE_SETUP("Put 6 Victory tokens on the Colonnade per player"),
    DEFILED_SHRINE_SETUP("Put 2 Victory tokens on each non-Gathering Action Supply pile"),
    LABYRINTH_SETUP("Put 6 Victory tokens on the Labyrinth per player"),
    OBELISK_SETUP("Choose a random Action Supply pile and put a Coin token on it"),
    ENCAMPMENT_SETUP("Place the Plunder pile sideways under the Encampment pile"),
    PATRICIAN_SETUP("Place the Emporium pile sideways under the Patrician pile"),
    SETTLERS_SETUP("Place the Bustling Village pile sideways under the Settlers pile"),
    CATAPULT_SETUP("Place the Rocks pile sideways under the Catapult pile"),
    GLADIATOR_SETUP("Place the Fortune pile sideways under the Gladiator pile"),
    ;

    private String text;

    SetupText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
