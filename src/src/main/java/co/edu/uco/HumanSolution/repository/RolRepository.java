package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, UUID> {
    boolean existsByNombre(String nombre);
    Optional<Rol> findByNombre(String nombre);
}