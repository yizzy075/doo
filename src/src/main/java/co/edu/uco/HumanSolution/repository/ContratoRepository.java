package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.ContratoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<ContratoEntity, UUID> {
    List<ContratoEntity> findByUsuario(Usuario usuario);

    @Query("SELECT c FROM ContratoEntity c WHERE c.usuario = :usuario AND c.activo = true " +
            "AND c.fechaInicio <= :fecha AND (c.fechaFin IS NULL OR c.fechaFin >= :fecha)")
    Optional<ContratoEntity> findContratoVigenteByUsuarioAndFecha(
            @Param("usuario") Usuario usuario,
            @Param("fecha") LocalDate fecha
    );

    @Query("SELECT COUNT(c) > 0 FROM ContratoEntity c WHERE c.usuario = :usuario AND c.activo = true")
    boolean existsContratoActivoByUsuario(@Param("usuario") Usuario usuario);
}