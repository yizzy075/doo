package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.PuestoEntity;
import co.edu.uco.HumanSolution.model.UnidadOrganizativaEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

@Repository
public interface PuestoRepository extends JpaRepository<PuestoEntity, UUID> {
    boolean existsByNombreAndUnidad(String nombre, UnidadOrganizativaEntity unidad);
    Optional<PuestoEntity> findByUsuario(Usuario usuario);
    List<PuestoEntity> findByUnidad(UnidadOrganizativaEntity unidad);
}
