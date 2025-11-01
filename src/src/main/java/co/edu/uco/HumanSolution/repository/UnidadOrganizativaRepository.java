package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.UnidadOrganizativaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnidadOrganizativaRepository extends JpaRepository<UnidadOrganizativaEntity, UUID> {
    boolean existsByNombre(String nombre);
}