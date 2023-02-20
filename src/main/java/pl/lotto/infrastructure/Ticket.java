package pl.lotto.infrastructure;


import org.springframework.data.mongodb.core.mapping.Document;

@Document("tickets")
public record Ticket(String id) {
}
