package federicopini.B7_L5.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDTO (@Email @NotNull(message = "L'email non può essere vuoto") String email, @NotNull(message = "la password non può essere vuota") String password){
}
