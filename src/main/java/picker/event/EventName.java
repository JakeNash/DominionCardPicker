package picker.event;

public enum EventName {

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
    ;

    private String name;

    EventName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
