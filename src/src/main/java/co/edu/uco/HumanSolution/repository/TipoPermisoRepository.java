package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.TipoPermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoPermisoRepository extends JpaRepository<TipoPermisoEntity, UUID> {
    boolean existsByNombre(String nombre);
}