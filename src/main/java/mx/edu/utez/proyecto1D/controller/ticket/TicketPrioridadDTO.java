package mx.edu.utez.proyecto1D.controller.ticket;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mx.edu.utez.proyecto1D.model.ticket.Prioridad;

@Getter
@Setter
public class TicketPrioridadDTO {

    @NotNull(message = "La prioridad es requerida (BAJA, MEDIA, ALTA)")
    private Prioridad prioridad;
}
