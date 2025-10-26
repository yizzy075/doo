package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.PermisoSistemaEntity;
import co.edu.uco.HumanSolution.service.PermisoSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/permisos-sistema")
@CrossOrigin(origins = "http://localhost:4200")
public class PermisoSistemaController {

    @Autowired
    private PermisoSistemaService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody PermisoSistemaEntity permiso) {
        Map<String, Object> response = new HashMap<>();
        try {
            PermisoSistemaEntity creado = service.crear(permiso);
            response.put("success", true);
            response.put("mensaje", "Permiso de sistema creado correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<PermisoSistemaEntity>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<PermisoSistemaEntity> buscarPorNombre(@PathVariable String nombre) {
        return service.buscarPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        try {
            service.eliminar(id);
            response.put("success", true);
            response.put("mensaje", "Permiso eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}