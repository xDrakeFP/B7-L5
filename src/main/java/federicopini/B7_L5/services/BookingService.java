package federicopini.B7_L5.services;

import federicopini.B7_L5.dto.BookingDTO;
import federicopini.B7_L5.entities.Booking;
import federicopini.B7_L5.entities.Event;
import federicopini.B7_L5.entities.User;
import federicopini.B7_L5.exceptions.BadRequestException;
import federicopini.B7_L5.exceptions.FullBookingsException;
import federicopini.B7_L5.exceptions.NotFoundException;
import federicopini.B7_L5.repos.BookingRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BookingService {
    @Autowired
    private BookingRepo repo;

    @Autowired
    private EventService eventService;

    public Booking findById(UUID id) {
        return this.repo.findById(id).orElseThrow(()-> new NotFoundException("Nessuna prenotazione trovata con l'id indicato"));
    }

    public Booking createBooking(User user, BookingDTO body){
        Event event = this.eventService.findById(body.id());
        if(this.repo.existsByEvent_IdAndUser_Id(event.getId(),user.getId())) throw new BadRequestException("Esiste gia una prenotazione per questo utente a questo evento");
        if(event.getPostiOccupati() >= event.getPosti()) throw new FullBookingsException("L'evento ha tutti i posti prenotati");
        Booking newBooking = new Booking(user, event);
        return this.repo.save(newBooking);
    }
    public void deleteBooking(User user, UUID bookingID){
        Booking found = this.findById(bookingID);
        this.repo.delete(found);
    }

}
