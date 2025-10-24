package federicopini.B7_L5.repos;

import federicopini.B7_L5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
}

