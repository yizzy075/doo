package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.Rol;
import co.edu.uco.HumanSolution.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de roles
 */
@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RolController {

    @Autowired
    private RolService rolService;

    /**
     * Obtiene todos los roles
     * GET http://localhost:8080/api/roles
     */
    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        List<Rol> roles = rolService.obtenerTodos();
        return ResponseEntity.ok(roles);
    }

    /**
     * Obtiene un rol por su ID
     * GET http://localhost:8080/api/roles/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable("id") String id) {
        Rol rol = rolService.obtenerPorId(java.util.UUID.fromString(id));
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }
}
