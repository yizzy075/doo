package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.UsuarioHoraExtraEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.service.UsuarioHoraExtraService;
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
@RequestMapping("/api/usuario-horas-extras")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioHoraExtraController {

    @Autowired
    private UsuarioHoraExtraService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody UsuarioHoraExtraEntity horaExtra) {
        Map<String, Object> response = new HashMap<>();
        try {
            UsuarioHoraExtraEntity creada = service.registrar(horaExtra);
            response.put("success", true);
            response.put("mensaje", "Hora extra registrada correctamente");
            response.put("data", creada);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/usuario/{numeroDocumento}")
    public ResponseEntity<List<UsuarioHoraExtraEntity>> listarPorUsuario(@PathVariable String numeroDocumento) {
        Usuario usuario = usuarioService.buscarPorDocumento(numeroDocumento)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return ResponseEntity.ok(service.listarPorUsuario(usuario));
    }

    @GetMapping("/usuario/{numeroDocumento}/aprobadas")
    public ResponseEntity<List<UsuarioHoraExtraEntity>> listarAprobadas(@PathVariable String numeroDocumento) {
        Usuario usuario = usuarioService.buscarPorDocumento(numeroDocumento)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return ResponseEntity.ok(service.listarAprobadas(usuario));
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Map<String, Object>> aprobar(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        try {
            UsuarioHoraExtraEntity aprobada = service.aprobar(id);
            response.put("success", true);
            response.put("mensaje", "Hora extra aprobada correctamente");
            response.put("data", aprobada);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<Map<String, Object>> pagar(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        try {
            service.pagar(id);
            response.put("success", true);
            response.put("mensaje", "Hora extra pagada correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}