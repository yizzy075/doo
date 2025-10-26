package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.ExperienciaLaboralEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.service.ExperienciaLaboralService;
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
@RequestMapping("/api/experiencias-laborales")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaLaboralController {

    @Autowired
    private ExperienciaLaboralService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregar(@RequestBody ExperienciaLaboralEntity experiencia) {
        Map<String, Object> response = new HashMap<>();
        try {
            ExperienciaLaboralEntity creada = service.agregar(experiencia);
            response.put("success", true);
            response.put("mensaje", "Experiencia laboral agregada correctamente");
            response.put("data", creada);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/usuario/{numeroDocumento}")
    public ResponseEntity<List<ExperienciaLaboralEntity>> listarPorUsuario(@PathVariable String numeroDocumento) {
        Usuario usuario = usuarioService.buscarPorDocumento(numeroDocumento)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return ResponseEntity.ok(service.listarPorUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@PathVariable UUID id, @RequestBody ExperienciaLaboralEntity experiencia) {
        Map<String, Object> response = new HashMap<>();
        try {
            ExperienciaLaboralEntity actualizada = service.editar(id, experiencia);
            response.put("success", true);
            response.put("mensaje", "Experiencia laboral actualizada correctamente");
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
            response.put("mensaje", "Experiencia laboral eliminada correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}