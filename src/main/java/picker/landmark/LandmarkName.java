package picker.landmark;

public enum LandmarkName {

    // Empires
    AQUEDUCT("Aqueduct"),
    ARENA("Arena"),
    BANDIT_FORT("Bandit Fort"),
    BASILICA("Basilica"),
    BATHS("Baths"),
    BATTLEFIELD("Battlefield"),
    COLONNADE("Colonnade"),
    DEFILED_SHRINE("Defiled Shrine"),
    FOUNTAIN("Fountain"),
    KEEP("Keep"),
    LABYRINTH("Labyrinth"),
    MOUNTAIN_PASS("Mountain Pass"),
    MUSEUM("Museum"),
    OBELISK("Obelisk"),
    ORCHARD("Orchard"),
    PALACE("Palace"),
    TOMB("Tomb"),
    TOWER("Tower"),
    TRIUMPHAL_ARCH("Triumphal Arch"),
    WALL("Wall"),
    WOLF_DEN("Wolf Den"),
    ;

    private String name;

    LandmarkName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
