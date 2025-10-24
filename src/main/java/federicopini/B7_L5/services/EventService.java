package federicopini.B7_L5.services;

import federicopini.B7_L5.dto.EventDTO;
import federicopini.B7_L5.entities.Event;
import federicopini.B7_L5.entities.User;
import federicopini.B7_L5.repos.EventRepo;
import federicopini.B7_L5.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventService {
    @Autowired
    private EventRepo repo;

    @Autowired
    private UserService userService;

    public Page<Event> findAll(int pageNumber, int pageSize, String sortBy){
        if (pageSize >20) pageSize=20;
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).ascending());
        return this.repo.findAll(pageable);
    }

    public Event createEvent(EventDTO body, User organizer) {
        Event newEvent = new Event(body.title(), body.description(), body.data(), body.posti(), body.location(), organizer);
        return this.repo.save(newEvent);
    }
}
