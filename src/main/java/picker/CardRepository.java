package picker;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {

    Card findByName(String name);
    List<Card> findByKingdoms(String kingdom);
}
