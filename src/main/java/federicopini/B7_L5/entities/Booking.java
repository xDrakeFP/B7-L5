package federicopini.B7_L5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventi")
public class Booking {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Event event;

    public Booking(User user, Event event) {
        this.user = user;
        this.event = event;
    }
}
