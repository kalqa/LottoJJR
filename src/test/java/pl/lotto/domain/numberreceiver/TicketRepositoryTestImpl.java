package pl.lotto.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TicketRepositoryTestImpl implements TicketRepository {

    private final Map<String, Ticket> tickets = new ConcurrentHashMap<>();

    @Override
    public Collection<Ticket> findAllTicketsByDrawDate(LocalDateTime drawDate) {
        return tickets.values()
                .stream()
                .filter(ticket -> ticket.drawDate().isEqual(drawDate))
                .collect(Collectors.toList());
    }

    @Override
    public Ticket findByHash(String hash) {
        return tickets.get(hash);
    }

    @Override
    public Ticket save(Ticket savedTicket) {
        tickets.put(savedTicket.hash(), savedTicket);
        return savedTicket;
    }
}
