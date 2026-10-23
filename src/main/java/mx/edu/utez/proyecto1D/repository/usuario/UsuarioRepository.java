package mx.edu.utez.proyecto1D.repository.usuario;

import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {




}
