package picker.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.List;

public class Event {

    private @Id String id;
    private String cost;
    private String name;
    private String box;
    private List<String> setup;

    private @Version @JsonIgnore Long version;

    private Event() {}

    public Event(String cost, String name, String box, List<String> setup) {
        this.cost = cost;
        this.name = name;
        this.box = box;
        this.setup = setup;
    }

    public Event(String cost, String name, String box) {
        this.cost = cost;
        this.name = name;
        this.box = box;
        this.setup = null;
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

    public List<String> getSetup() {
        return setup;
    }

    public void setSetup(List<String> setup) {
        this.setup = setup;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}