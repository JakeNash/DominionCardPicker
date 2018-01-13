package picker.kingdom;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KingdomRepository extends MongoRepository<Kingdom, String> {

    List<Kingdom> findByCards(@Param("card") String card);
    List<Kingdom> findByBoxes(@Param("box") String box);
    List<Kingdom> findByEvents(@Param("event") String event);
    List<Kingdom> findByLandmarks(@Param("landmark") String landmark);
}
