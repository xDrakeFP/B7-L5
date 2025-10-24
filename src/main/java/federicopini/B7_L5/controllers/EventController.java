package federicopini.B7_L5.controllers;

import federicopini.B7_L5.dto.EventDTO;
import federicopini.B7_L5.entities.Event;
import federicopini.B7_L5.entities.User;
import federicopini.B7_L5.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping
    public Page<Event> findAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy){
        return this.service.findAll(page, size, sortBy);
    }

    @PreAuthorize("hasRole('ORGANIZER')")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent (@RequestBody @Validated EventDTO body, @AuthenticationPrincipal User currentUser){
        return this.service.createEvent(body, currentUser);
    }

    @PreAuthorize("hasRole('ORGANIZER')")
    @PutMapping("/edit/{id}")
    public Event editEvent (@PathVariable UUID id, @RequestBody @Validated EventDTO body, @AuthenticationPrincipal User currentUser){
        return this.service.modifyEvent(id,body,currentUser);
    }

    @PreAuthorize("hasRole('ORGANIZER')")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable UUID id, @AuthenticationPrincipal User currentUser){
        this.service.deleteEvent(id,currentUser);
    }
}
