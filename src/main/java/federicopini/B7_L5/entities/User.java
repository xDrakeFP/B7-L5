package federicopini.B7_L5.entities;


import federicopini.B7_L5.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.ConstructorParameters;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "utenti")
public class User implements UserDetails {
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
    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    public User(String name, String surname, String email, Role role, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
