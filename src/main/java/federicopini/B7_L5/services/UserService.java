package federicopini.B7_L5.services;

import federicopini.B7_L5.dto.UserDTO;
import federicopini.B7_L5.entities.User;
import federicopini.B7_L5.enums.Role;
import federicopini.B7_L5.exceptions.BadRequestException;
import federicopini.B7_L5.exceptions.NotFoundException;
import federicopini.B7_L5.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder bcrypt;

    public User findById (UUID id) {
        return this.repo.findById(id).orElseThrow(()->new NotFoundException("Nessun utente trovato con l'ID indicato"));
    }

    public User findByEmail(String email) {
        return this.repo.findByEmail(email).orElseThrow(()->new NotFoundException("Nessun utente trovato con l'email indicata"));
    }

    public User registerUser(UserDTO body) {
        this.repo.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email "+body.email()+" è gia in uso");
        });

        User newUser = new User(body.name(), body.surname(), body.email(), body.role(),bcrypt.encode(body.password()));
        User savedUser = this.repo.save(newUser);
        return this.repo.save(savedUser);
    }
}
