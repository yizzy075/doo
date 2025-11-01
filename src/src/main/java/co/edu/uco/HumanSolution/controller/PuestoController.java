package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.PuestoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.service.PuestoService;
import co.edu.uco.HumanSolution.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/puestos")
@CrossOrigin(origins = "http://localhost:4200")
public class PuestoController {

    @Autowired
    private PuestoService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody PuestoEntity puesto) {
        Map<String, Object> response = new HashMap<>();
        try {
            PuestoEntity creado = service.crear(puesto);
            response.put("success", true);
            response.put("mensaje", "Puesto creado correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<PuestoEntity>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuestoEntity> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/asignar-usuario")
    public ResponseEntity<Map<String, Object>> asignarUsuario(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        Map<String, Object> response = new HashMap<>();
        try {
            String numeroDocumento = body.get("numeroDocumento");
            Usuario usuario = usuarioService.buscarPorDocumento(numeroDocumento)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

            PuestoEntity actualizado = service.asignarUsuario(id, usuario);
            response.put("success", true);
            response.put("mensaje", "Usuario asignado al puesto correctamente");
            response.put("data", actualizado);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}