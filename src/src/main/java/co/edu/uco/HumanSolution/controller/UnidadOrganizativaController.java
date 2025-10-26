package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.UnidadOrganizativaEntity;
import co.edu.uco.HumanSolution.service.UnidadOrganizativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/unidades-organizativas")
@CrossOrigin(origins = "http://localhost:4200")
public class UnidadOrganizativaController {

    @Autowired
    private UnidadOrganizativaService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody UnidadOrganizativaEntity unidad) {
        Map<String, Object> response = new HashMap<>();
        try {
            UnidadOrganizativaEntity creada = service.crear(unidad);
            response.put("success", true);
            response.put("mensaje", "Unidad organizativa creada correctamente");
            response.put("data", creada);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<UnidadOrganizativaEntity>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadOrganizativaEntity> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable UUID id, @RequestBody UnidadOrganizativaEntity unidad) {
        Map<String, Object> response = new HashMap<>();
        try {
            UnidadOrganizativaEntity actualizada = service.actualizar(id, unidad);
            response.put("success", true);
            response.put("mensaje", "Unidad organizativa actualizada correctamente");
            response.put("data", actualizada);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        try {
            service.eliminar(id);
            response.put("success", true);
            response.put("mensaje", "Unidad organizativa eliminada correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}