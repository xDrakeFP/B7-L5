package federicopini.B7_L5.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventDTO (
        @NotBlank (message = "Il titolo non può essere vuoto") String title,
        @NotBlank (message = "La descrizione non può essere vuota") String description,
        @NotNull (message = "La data non può essere vuota") LocalDateTime data,
        @Min(value = 10,message = "Un evento deve avere almeno 10 posti disponibili") int posti,
        @NotBlank (message = "L'evento deve avere una location") String location
        ) {
}
