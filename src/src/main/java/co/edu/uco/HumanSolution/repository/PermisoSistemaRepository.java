package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.PermisoSistemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface PermisoSistemaRepository extends JpaRepository<PermisoSistemaEntity, UUID> {
    boolean existsByNombre(String nombre);
    Optional<PermisoSistemaEntity> findByNombre(String nombre);
}