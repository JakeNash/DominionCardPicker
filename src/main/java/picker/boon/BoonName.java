package picker.boon;

public enum BoonName {

    THE_EARTHS_GIFT("The Earth's Gift"),
    THE_FIELDS_GIFT("The Field's Gift"),
    THE_FLAMES_GIFT("The Flame's Gift"),
    THE_FORESTS_GIFT("The Forest's Gift"),
    THE_MOONS_GIFT("The Moon's Gift"),
    THE_MOUNTAINS_GIFT("The Mountain's Gift"),
    THE_RIVERS_GIFT("The River's Gift"),
    THE_SEAS_GIFT("The Sea's Gift"),
    THE_SKYS_GIFT("The Sky's Gift"),
    THE_SUNS_GIFT("The Sun's Gift"),
    THE_SWAMPS_GIFT("The Swamp's Gift"),
    THE_WINDS_GIFT("The Wind's Gift"),
    ;

    private String name;

    BoonName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
