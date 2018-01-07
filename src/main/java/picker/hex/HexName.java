package picker.hex;

public enum HexName {

    BAD_OMENS("Bad Omens"),
    DELUSION("Delusion"),
    ENVY("Envy"),
    FAMINE("Famine"),
    FEAR("Fear"),
    GREED("Greed"),
    HAUNTING("Haunting"),
    LOCUSTS("Locusts"),
    MISERY("Misery"),
    PLAGUE("Plague"),
    POVERTY("Poverty"),
    WAR("War"),
    ;

    private String name;

    HexName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
