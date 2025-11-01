package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.EvaluacionDesempenoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.service.EvaluacionDesempenoService;
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
@RequestMapping("/api/evaluaciones-desempeno")
@CrossOrigin(origins = "http://localhost:4200")
public class EvaluacionDesempenoController {

    @Autowired
    private EvaluacionDesempenoService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody EvaluacionDesempenoEntity evaluacion) {
        Map<String, Object> response = new HashMap<>();
        try {
            EvaluacionDesempenoEntity creada = service.registrar(evaluacion);
            response.put("success", true);
            response.put("mensaje", "Evaluación de desempeño registrada correctamente");
            response.put("data", creada);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/usuario/{numeroDocumento}")
    public ResponseEntity<List<EvaluacionDesempenoEntity>> listarPorUsuario(@PathVariable String numeroDocumento) {
        Usuario usuario = usuarioService.buscarPorDocumento(numeroDocumento)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return ResponseEntity.ok(service.listarPorUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> modificar(@PathVariable UUID id, @RequestBody Map<String, Object> body) {
        Map<String, Object> response = new HashMap<>();
        try {
            EvaluacionDesempenoEntity evaluacion = new EvaluacionDesempenoEntity();
            evaluacion.setCalificacion((Integer) body.get("calificacion"));
            evaluacion.setObservacion((String) body.get("observacion"));
            String justificacion = (String) body.get("justificacion");

            EvaluacionDesempenoEntity actualizada = service.modificar(id, evaluacion, justificacion);
            response.put("success", true);
            response.put("mensaje", "Evaluación modificada correctamente");
            response.put("data", actualizada);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        Map<String, Object> response = new HashMap<>();
        try {
            String justificacion = body.get("justificacion");
            service.eliminar(id, justificacion);
            response.put("success", true);
            response.put("mensaje", "Evaluación eliminada correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}