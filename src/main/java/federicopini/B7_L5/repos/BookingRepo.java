package federicopini.B7_L5.repos;

import federicopini.B7_L5.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepo extends JpaRepository <Booking, UUID> {
    boolean existsByEvent_IdAndUser_Id(UUID eventoId, UUID UtenteId);
}
