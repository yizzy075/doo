package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.ExperienciaLaboralEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Repository
public interface ExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboralEntity, UUID> {
    boolean existsByUsuarioAndEmpresaAndCargoAndFechaInicio(
            Usuario usuario, String empresa, String cargo, LocalDate fechaInicio
    );
    List<ExperienciaLaboralEntity> findByUsuario(Usuario usuario);
}