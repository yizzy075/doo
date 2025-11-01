package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.EstadoPuestoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface EstadoPuestoRepository extends JpaRepository<EstadoPuestoEntity, UUID> {
    boolean existsByNombre(String nombre);
    Optional<EstadoPuestoEntity> findByNombre(String nombre);
}