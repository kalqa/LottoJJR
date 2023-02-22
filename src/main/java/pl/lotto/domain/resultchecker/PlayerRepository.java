package pl.lotto.domain.resultchecker;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

    Optional<Player> findById(String hash);
}
