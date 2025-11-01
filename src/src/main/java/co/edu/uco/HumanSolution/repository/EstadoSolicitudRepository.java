package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.EstadoSolicitudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitudEntity, UUID> {
    boolean existsByNombre(String nombre);
    Optional<EstadoSolicitudEntity> findByNombre(String nombre);
}