package picker.boon;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoonRepository extends MongoRepository<Boon, String> {

    Boon findByName(String name);
}
