package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.EstadoSolicitudEntity;
import co.edu.uco.HumanSolution.service.EstadoSolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estados-solicitud")
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoSolicitudController {

    @Autowired
    private EstadoSolicitudService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody EstadoSolicitudEntity estado) {
        Map<String, Object> response = new HashMap<>();
        try {
            EstadoSolicitudEntity creado = service.crear(estado);
            response.put("success", true);
            response.put("mensaje", "Estado de solicitud creado correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<EstadoSolicitudEntity>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EstadoSolicitudEntity> buscarPorNombre(@PathVariable String nombre) {
        return service.buscarPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}