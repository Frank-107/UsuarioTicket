package mx.edu.utez.proyecto1D.service.ticket;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.proyecto1D.controller.ticket.TicketCreateDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto1D.exception.customExceptions.ResourceNotFoundException;
import mx.edu.utez.proyecto1D.model.ticket.Estado;
import mx.edu.utez.proyecto1D.model.ticket.Prioridad;
import mx.edu.utez.proyecto1D.model.ticket.Ticket;
import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import mx.edu.utez.proyecto1D.repository.ticket.TicketRepository;
import mx.edu.utez.proyecto1D.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Ticket crearTicket(TicketCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID " + dto.getUsuarioId() + " no existe"));

        if (!usuario.isActivo()) {
            throw new CustomBadRequestException("No se puede crear el ticket porque el usuario está inactivo");
        }

        Ticket ticket = new Ticket();
        ticket.setDescripcion(dto.getDescripcion().trim());
        ticket.setEstado(Estado.ABIERTO);
        ticket.setPrioridad(dto.getPrioridad());
        ticket.setUsuario(usuario);

        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket actualizarEstado(Long id) {
        Ticket ticket = buscarTicket(id);

        if (ticket.getEstado() == Estado.ABIERTO) {
            ticket.setEstado(Estado.EN_PROCESO);
        } else if (ticket.getEstado() == Estado.EN_PROCESO) {
            ticket.setEstado(Estado.CERRADO);
        } else {
            throw new CustomBadRequestException("El ticket ya se encuentra CERRADO y no se puede modificar");
        }

        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket reasignarPrioridad(Long id, Prioridad nuevaPrioridad) {
        Ticket ticket = buscarTicket(id);

        if (ticket.getEstado() == Estado.CERRADO) {
            throw new CustomBadRequestException("No se puede cambiar la prioridad a un ticket CERRADO");
        }

        ticket.setPrioridad(nuevaPrioridad);
        return ticketRepository.save(ticket);
    }

    private Ticket buscarTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ticket con ID " + id + " no existe"));
    }
}
