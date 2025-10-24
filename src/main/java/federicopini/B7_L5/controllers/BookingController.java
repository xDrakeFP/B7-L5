package federicopini.B7_L5.controllers;

import federicopini.B7_L5.dto.BookingDTO;
import federicopini.B7_L5.entities.Booking;
import federicopini.B7_L5.entities.User;
import federicopini.B7_L5.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@AuthenticationPrincipal User user, @RequestBody BookingDTO body){
        return this.service.createBooking(user,body);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking (@AuthenticationPrincipal User user,@PathVariable UUID id){
        this.service.deleteBooking(user,id);
    }
}
