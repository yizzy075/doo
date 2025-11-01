package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.TipoHoraExtraEntity;
import co.edu.uco.HumanSolution.service.TipoHoraExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/tipos-hora-extra")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoHoraExtraController {

    @Autowired
    private TipoHoraExtraService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody TipoHoraExtraEntity tipoHoraExtra) {
        Map<String, Object> response = new HashMap<>();
        try {
            TipoHoraExtraEntity creado = service.crear(tipoHoraExtra);
            response.put("success", true);
            response.put("mensaje", "Tipo de hora extra creado correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<TipoHoraExtraEntity>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoHoraExtraEntity> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}