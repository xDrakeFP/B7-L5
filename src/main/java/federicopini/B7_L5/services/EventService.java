package federicopini.B7_L5.services;

import federicopini.B7_L5.dto.EventDTO;
import federicopini.B7_L5.entities.Event;
import federicopini.B7_L5.entities.User;
import federicopini.B7_L5.exceptions.NotFoundException;
import federicopini.B7_L5.exceptions.UnauthorizedException;
import federicopini.B7_L5.repos.EventRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class EventService {
    @Autowired
    private EventRepo repo;

    @Autowired
    private UserService userService;

    public Event findById(UUID id) {
        return this.repo.findById(id).orElseThrow(()-> new NotFoundException("Nessun evento trovato con l'id indicato"));
    }

    public Page<Event> findAll(int pageNumber, int pageSize, String sortBy){
        if (pageSize >20) pageSize=20;
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).ascending());
        return this.repo.findAll(pageable);
    }

    public Event createEvent(EventDTO body, User organizer) {
        Event newEvent = new Event(body.title(), body.description(), body.data(), body.posti(), body.location(), organizer);
        return this.repo.save(newEvent);
    }

    public Event modifyEvent(UUID id,EventDTO body, User organizer) {
        Event found = this.findById(id);
        if(!found.getUser().getId().equals(organizer.getId())) throw new UnauthorizedException("Non sei autorizzato a modificare l'evento di qualcun'altro!");

        found.setData(body.data());
        found.setLocation(body.location());
        found.setDescription(body.description());
        found.setPosti(body.posti());
        found.setTitle(body.title());
        return this.repo.save(found);

    }

    public void deleteEvent(UUID id, User organizer){
        Event found = this.findById(id);
        if(!found.getUser().getId().equals(organizer.getId())) throw new UnauthorizedException("Non sei autorizzato a modificare l'evento di qualcun'altro!");
        this.repo.delete(found);
    }
}
