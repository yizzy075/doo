package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, UUID> {
    boolean existsByNombre(String nombre);
}