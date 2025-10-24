package federicopini.B7_L5.entities;


import federicopini.B7_L5.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import javax.management.ConstructorParameters;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "utenti")
public class User {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "nome")
    private String name;

    @Column(name = "cognome")
    private String surname;

    private String email;

    @Column(name = "ruolo")
    private Role role;

    private String password;

    public User(String name, String surname, String email, Role role, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}
