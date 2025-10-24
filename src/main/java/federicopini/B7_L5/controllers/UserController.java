package federicopini.B7_L5.controllers;

import federicopini.B7_L5.entities.User;
import federicopini.B7_L5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/me")
    public User getMyProfile(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }
}
