package federicopini.B7_L5.dto;

import federicopini.B7_L5.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO (@NotBlank(message = "il nome è obbligatorio") String name,
                       @NotBlank(message = "il cognome è obbligatorio") String surname,
                       @NotBlank(message = "l'email è obbligatoria") @Email String email,
                       @NotBlank(message = "La password è obbligatorio")
                       @Size(min = 4, message = "La password deve avere minimo 4 caratteri")
                       String password,
                       @NotBlank(message = "Il ruolo è obbligatorio!")
                       Role role
                       ){

}
