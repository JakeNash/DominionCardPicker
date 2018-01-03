package picker;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KingdomRepository extends MongoRepository<Kingdom, String> {

    List<Kingdom> findByCards(String card);
}
