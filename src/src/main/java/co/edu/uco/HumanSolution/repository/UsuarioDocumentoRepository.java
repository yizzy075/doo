package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.UsuarioDocumentoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.model.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Repository
public interface UsuarioDocumentoRepository extends JpaRepository<UsuarioDocumentoEntity, UUID> {
    boolean existsByUsuarioAndTipoDocumentoAndFecha(
            Usuario usuario, TipoDocumentoEntity tipoDocumento, LocalDate fecha
    );
    List<UsuarioDocumentoEntity> findByUsuario(Usuario usuario);
}