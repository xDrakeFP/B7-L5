package federicopini.B7_L5.services;

import federicopini.B7_L5.repos.BookingRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingService {
    @Autowired
    private BookingRepo repo;
}
