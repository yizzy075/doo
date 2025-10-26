package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.Rol;
import co.edu.uco.HumanSolution.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RolController {

    @Autowired
    private RolService rolService;

    /**
     * Crea un nuevo rol
     * POST /api/roles
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody Rol rol) {
        Map<String, Object> response = new HashMap<>();
        try {
            Rol creado = rolService.crear(rol);
            response.put("success", true);
            response.put("mensaje", "Rol creado correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Obtiene todos los roles
     * GET /api/roles
     */
    @GetMapping
    public ResponseEntity<List<Rol>> listarTodos() {
        return ResponseEntity.ok(rolService.listarTodos());
    }

    /**
     * Busca un rol por su ID
     * GET /api/roles/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return rolService.buscarPorNombre(id) // Si buscas por nombre
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Busca un rol por su nombre
     * GET /api/roles/nombre/{nombre}
     */
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Rol> buscarPorNombre(@PathVariable String nombre) {
        return rolService.buscarPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un rol existente
     * PUT /api/roles/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable UUID id, @RequestBody Rol rol) {
        Map<String, Object> response = new HashMap<>();
        try {
            Rol actualizado = rolService.actualizar(id, rol);
            response.put("success", true);
            response.put("mensaje", "Rol actualizado correctamente");
            response.put("data", actualizado);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Elimina un rol
     * DELETE /api/roles/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        try {
            rolService.eliminar(id);
            response.put("success", true);
            response.put("mensaje", "Rol eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}