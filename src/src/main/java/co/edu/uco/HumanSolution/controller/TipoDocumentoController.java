package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.TipoDocumentoEntity;
import co.edu.uco.HumanSolution.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/tipos-documento")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody TipoDocumentoEntity tipoDocumento) {
        Map<String, Object> response = new HashMap<>();
        try {
            TipoDocumentoEntity creado = service.crear(tipoDocumento);
            response.put("success", true);
            response.put("mensaje", "Tipo de documento creado correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<TipoDocumentoEntity>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumentoEntity> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        try {
            service.eliminar(id);
            response.put("success", true);
            response.put("mensaje", "Tipo de documento eliminado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}