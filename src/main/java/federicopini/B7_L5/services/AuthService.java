package federicopini.B7_L5.services;

import federicopini.B7_L5.dto.LoginDTO;
import federicopini.B7_L5.entities.User;
import federicopini.B7_L5.exceptions.UnauthorizedException;
import federicopini.B7_L5.security.JWTTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private UserService service;
    @Autowired
    private JWTTools tools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(LoginDTO body){
        User found = this.service.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            return tools.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
