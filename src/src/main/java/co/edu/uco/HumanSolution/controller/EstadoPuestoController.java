package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.EstadoPuestoEntity;
import co.edu.uco.HumanSolution.service.EstadoPuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estados-puesto")
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoPuestoController {

    @Autowired
    private EstadoPuestoService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody EstadoPuestoEntity estado) {
        Map<String, Object> response = new HashMap<>();
        try {
            EstadoPuestoEntity creado = service.crear(estado);
            response.put("success", true);
            response.put("mensaje", "Estado de puesto creado correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<EstadoPuestoEntity>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EstadoPuestoEntity> buscarPorNombre(@PathVariable String nombre) {
        return service.buscarPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}