package picker.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.List;

public class Card {

    private @Id String id;
    private String cost;
    private String name;
    private String box;
    private List<String> types;
    private List<String> otherSetup;
    private Boolean supply;

    private @Version @JsonIgnore Long version;

    private Card() {}

    public Card(String cost, String name, String box, List<String> types, Boolean supply) {
        this.cost = cost;
        this.name = name;
        this.box = box;
        this.types = types;
        this.otherSetup = null;
        this.supply = supply;
    }

    public Card(String cost, String name, String box, List<String> types, List<String> otherSetup, Boolean supply) {
        this.cost = cost;
        this.name = name;
        this.box = box;
        this.types = types;
        this.otherSetup = otherSetup;
        this.supply = supply;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<String> getOtherSetup() {
        return otherSetup;
    }

    public void setOtherSetup(List<String> otherSetup) {
        this.otherSetup = otherSetup;
    }

    public Boolean getSupply() {
        return supply;
    }

    public void setSupply(Boolean supply) {
        this.supply = supply;
    }
}
