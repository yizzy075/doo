package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RolRepository extends JpaRepository<Rol, UUID> {
}
