package federicopini.B7_L5.controllers;

import federicopini.B7_L5.dto.LoginDTO;
import federicopini.B7_L5.dto.LoginResponseDTO;
import federicopini.B7_L5.dto.UserDTO;
import federicopini.B7_L5.entities.User;
import federicopini.B7_L5.exceptions.ValidationException;
import federicopini.B7_L5.services.AuthService;
import federicopini.B7_L5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body){
        return new LoginResponseDTO(authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return this.userService.registerUser(body);
    }


}
