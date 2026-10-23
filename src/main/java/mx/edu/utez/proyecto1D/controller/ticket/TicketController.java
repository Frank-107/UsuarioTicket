package mx.edu.utez.proyecto1D.controller.ticket;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.proyecto1D.model.ticket.Ticket;
import mx.edu.utez.proyecto1D.service.ticket.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody TicketCreateDTO dto) {
        return new ResponseEntity<>(ticketService.crearTicket(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Ticket> actualizarEstado(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.actualizarEstado(id));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<Ticket> reasignarPrioridad(@PathVariable Long id,
                                                     @Valid @RequestBody TicketPrioridadDTO dto) {
        return ResponseEntity.ok(ticketService.reasignarPrioridad(id, dto.getPrioridad()));
    }
}
