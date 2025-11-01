package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.UsuarioHoraExtraEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.model.TipoHoraExtraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Repository
public interface UsuarioHoraExtraRepository extends JpaRepository<UsuarioHoraExtraEntity, UUID> {
    boolean existsByUsuarioAndFechaAndTipoHoraExtra(
            Usuario usuario, LocalDate fecha, TipoHoraExtraEntity tipoHoraExtra
    );
    List<UsuarioHoraExtraEntity> findByUsuario(Usuario usuario);
    List<UsuarioHoraExtraEntity> findByUsuarioAndEstadoSolicitud_Nombre(Usuario usuario, String estado);
}