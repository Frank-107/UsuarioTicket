package mx.edu.utez.proyecto1D.controller.usuario;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.proyecto1D.model.ticket.Ticket;
import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import mx.edu.utez.proyecto1D.service.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody RequestUsuarioDTO dto) {
        return new ResponseEntity<>(usuarioService.crearUsuario(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Usuario> desactivarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.desactivarUsuario(id));
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<Ticket>> obtenerTicketsUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerTicketsUsuario(id));
    }
}
