package picker;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.List;

public class Kingdom {

    private @Id
    String id;
    private String name;
    private List<String> cards;
    private List<String> otherSetup;

    private @Version @JsonIgnore Long version;

    private Kingdom() {
    }

    public Kingdom(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
        this.otherSetup = null;
    }

    public Kingdom(String name, List<String> cards, List<String> otherSetup) {
        this.name = name;
        this.cards = cards;
        this.otherSetup = otherSetup;
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

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }

    public List<String> getOtherSetup() {
        return otherSetup;
    }

    public void setOtherSetup(List<String> otherSetup) {
        this.otherSetup = otherSetup;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}