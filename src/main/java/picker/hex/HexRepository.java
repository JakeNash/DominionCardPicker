package picker.hex;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HexRepository extends MongoRepository<Hex, String> {

    Hex findByName(String name);
}
