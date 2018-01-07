package picker.event;

public enum EventName {

    // Adventures
    ALMS("Alms"),
    BORROW("Borrow"),
    QUEST("Quest"),
    SAVE("Save"),
    SCOUTING_PARTY("Scouting Party"),
    TRAVELLING_FAIR("Travelling Fair"),
    BONFIRE("Bonfire"),
    EXPEDITION("Expedition"),
    FERRY("Ferry"),
    PLAN("Plan"),
    MISSION("Mission"),
    PILGRIMAGE("Pilgrimage"),
    BALL("Ball"),
    RAID("Raid"),
    SEAWAY("Seaway"),
    TRADE("Trade"),
    LOST_ARTS("Lost Arts"),
    TRAINING("Training"),
    INHERITANCE("Inheritance"),
    PATHFINDING("Pathfinding"),

    // Empires
    TRIUMPH("Triumph"),
    ANNEX("Annex"),
    DONATE("Donate"),
    ADVANCE("Advance"),
    DELVE("Delve"),
    TAX("Tax"),
    BANQUET("Banquet"),
    RITUAL("Ritual"),
    SALT_THE_EARTH("Salt the Earth"),
    WEDDING("Wedding"),
    WINDFALL("Windfall"),
    CONQUEST("Conquest"),
    DOMINATE("Dominate"),
    ;

    private String name;

    EventName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
