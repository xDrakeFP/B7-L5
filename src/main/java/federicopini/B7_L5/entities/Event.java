package federicopini.B7_L5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "eventi")
public class Event {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "titolo")
    private String title;

    @Column(name = "descrizione")
    private String description;

    @Column(name = "data_evento")
    private LocalDateTime data;

    @Column(name ="posti_disponibili")
    private int posti;

    @Column(name = "posti_occupati")
    private int postiOccupati;

    @Column(name = "luogo")
    private String location;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    @Setter(AccessLevel.NONE)
    private User user;

    public Event(String title, String description, LocalDateTime data, int posti, String location, User user) {
        this.title = title;
        this.description = description;
        this.data = data;
        this.posti = posti;
        this.location = location;
        this.user = user;
    }
}
