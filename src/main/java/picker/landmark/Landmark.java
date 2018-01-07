package picker.landmark;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.List;

public class Landmark {

    private @Id
    String id;
    private String name;
    private String box;
    private List<String> setup;

    private @Version
    @JsonIgnore
    Long version;

    private Landmark() {}

    public Landmark(String name, String box, List<String> setup) {
        this.name = name;
        this.box = box;
        this.setup = setup;
    }

    public Landmark(String name, String box) {
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
