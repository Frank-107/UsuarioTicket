package mx.edu.utez.proyecto1D.controller.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mx.edu.utez.proyecto1D.model.ticket.Prioridad;

@Getter
@Setter
public class TicketCreateDTO {

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotNull(message = "La prioridad es requerida (BAJA, MEDIA, ALTA)")
    private Prioridad prioridad;

    @NotNull(message = "El ID del usuario es requerido")
    private Long usuarioId;
}
