package pl.lotto.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    Collection<Ticket> findAllTicketsByDrawDate(LocalDateTime drawDate);

    Ticket findByHash(String hash);
}
