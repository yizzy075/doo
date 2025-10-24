package co.edu.uco.HumanSolution.service;

import co.edu.uco.HumanSolution.model.Rol;
import co.edu.uco.HumanSolution.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    /**
     * Obtiene todos los roles de la base de datos
     */
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    /**
     * Busca un rol por su ID
     */
    public Rol obtenerPorId(UUID id) {
        return rolRepository.findById(id).orElse(null);
    }
}
