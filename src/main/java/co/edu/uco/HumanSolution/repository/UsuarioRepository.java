package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByNumeroDocumento(String numeroDocumento);
}
