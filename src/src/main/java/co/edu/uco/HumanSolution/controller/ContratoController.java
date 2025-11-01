package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.ContratoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.service.ContratoService;
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
@RequestMapping("/api/contratos")
@CrossOrigin(origins = "http://localhost:4200")
public class ContratoController {

    @Autowired
    private ContratoService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody ContratoEntity contrato) {
        Map<String, Object> response = new HashMap<>();
        try {
            ContratoEntity creado = service.registrar(contrato);
            response.put("success", true);
            response.put("mensaje", "Contrato registrado correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<ContratoEntity>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoEntity> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{numeroDocumento}")
    public ResponseEntity<List<ContratoEntity>> buscarPorUsuario(@PathVariable String numeroDocumento) {
        Usuario usuario = usuarioService.buscarPorDocumento(numeroDocumento)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return ResponseEntity.ok(service.buscarPorUsuario(usuario));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Map<String, Object>> finalizar(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        Map<String, Object> response = new HashMap<>();
        try {
            String razon = body.get("razon");
            ContratoEntity finalizado = service.finalizar(id, razon);
            response.put("success", true);
            response.put("mensaje", "Contrato finalizado correctamente");
            response.put("data", finalizado);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}