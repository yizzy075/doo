package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.EvaluacionDesempenoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Repository
public interface EvaluacionDesempenoRepository extends JpaRepository<EvaluacionDesempenoEntity, UUID> {
    boolean existsByUsuarioAndFecha(Usuario usuario, LocalDate fecha);
    List<EvaluacionDesempenoEntity> findByUsuario(Usuario usuario);
}