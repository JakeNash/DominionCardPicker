package picker.state;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StateRepository extends MongoRepository<State, String> {

    State findByName(String name);
}
