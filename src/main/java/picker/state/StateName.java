package picker.state;

public enum StateName {

    DELUDED("Deluded"),
    ENVIOUS("Envious"),
    MISERABLE("Miserable"),
    TWICE_MISERABLE("Twice Miserable"),
    LOST_IN_THE_WOODS("Lost in the Woods"),
    ;

    private String name;

    StateName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
