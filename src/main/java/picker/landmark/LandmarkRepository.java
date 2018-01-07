package picker.landmark;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LandmarkRepository extends MongoRepository<Landmark, String> {

    Landmark findByName(String name);
}
