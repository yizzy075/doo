package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.TipoHoraExtraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoHoraExtraRepository extends JpaRepository<TipoHoraExtraEntity, UUID> {
    boolean existsByNombre(String nombre);
}