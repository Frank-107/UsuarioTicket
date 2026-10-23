package mx.edu.utez.proyecto1D.service.usuario;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.proyecto1D.controller.usuario.RequestUsuarioDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.ResourceNotFoundException;
import mx.edu.utez.proyecto1D.model.ticket.Ticket;
import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import mx.edu.utez.proyecto1D.repository.ticket.TicketRepository;
import mx.edu.utez.proyecto1D.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TicketRepository ticketRepository;

    @Transactional
    public Usuario crearUsuario(RequestUsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setActivo(dto.getActivo() == null || dto.getActivo());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario desactivarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<Ticket> obtenerTicketsUsuario(Long id) {
        buscarPorId(id);
        return ticketRepository.findByUsuarioId(id);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID " + id + " no existe"));
    }
}
