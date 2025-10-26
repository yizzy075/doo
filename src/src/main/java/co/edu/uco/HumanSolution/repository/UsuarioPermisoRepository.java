package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.UsuarioPermisoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Repository
public interface UsuarioPermisoRepository extends JpaRepository<UsuarioPermisoEntity, UUID> {
    List<UsuarioPermisoEntity> findByUsuario(Usuario usuario);

    @Query("SELECT COUNT(p) > 0 FROM UsuarioPermisoEntity p WHERE p.usuario = :usuario " +
            "AND p.estadoSolicitud.nombre = 'Aprobado' " +
            "AND ((p.fechaInicio <= :fechaFin AND p.fechaFin >= :fechaInicio))")
    boolean existsSolapamientoPermisos(
            @Param("usuario") Usuario usuario,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );
}