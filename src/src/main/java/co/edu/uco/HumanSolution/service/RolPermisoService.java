package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.RolPermisoEntity;
import co.edu.uco.HumanSolution.model.Rol;
import co.edu.uco.HumanSolution.model.PermisoSistemaEntity;
import co.edu.uco.HumanSolution.repository.RolPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class RolPermisoService {

    private static final Logger logger = LoggerFactory.getLogger(RolPermisoService.class);

    @Autowired
    private RolPermisoRepository repository;

    public RolPermisoEntity asignarPermisoARol(Rol rol, PermisoSistemaEntity permiso) {
        logger.debug("Asignando permiso {} al rol {}", permiso.getNombre(), rol.getNombre());

        if (rol == null || permiso == null) {
            throw new IllegalArgumentException("El rol y el permiso deben existir");
        }

        if (repository.existsByRolAndPermiso(rol, permiso)) {
            throw new IllegalArgumentException("El permiso ya está asignado a ese rol");
        }

        RolPermisoEntity rolPermiso = new RolPermisoEntity(rol, permiso);
        return repository.save(rolPermiso);
    }

    @Transactional
    public void quitarPermisoDeRol(Rol rol, PermisoSistemaEntity permiso) {
        logger.debug("Quitando permiso {} del rol {}", permiso.getNombre(), rol.getNombre());
        repository.deleteByRolAndPermiso(rol, permiso);
    }

    public List<RolPermisoEntity> obtenerPermisosDeRol(Rol rol) {
        return repository.findByRol(rol);
    }
}