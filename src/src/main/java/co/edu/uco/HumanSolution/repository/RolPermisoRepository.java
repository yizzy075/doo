package co.edu.uco.HumanSolution.repository;

import co.edu.uco.HumanSolution.model.RolPermisoEntity;
import co.edu.uco.HumanSolution.model.Rol;  // ← Cambió de RolEntity a Rol
import co.edu.uco.HumanSolution.model.PermisoSistemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermisoEntity, UUID> {
    boolean existsByRolAndPermiso(Rol rol, PermisoSistemaEntity permiso);
    List<RolPermisoEntity> findByRol(Rol rol);
    void deleteByRolAndPermiso(Rol rol, PermisoSistemaEntity permiso);
}