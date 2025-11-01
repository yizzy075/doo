package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.UsuarioPermisoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.service.UsuarioPermisoService;
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
@RequestMapping("/api/usuario-permisos")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioPermisoController {

    @Autowired
    private UsuarioPermisoService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> solicitar(@RequestBody UsuarioPermisoEntity permiso) {
        Map<String, Object> response = new HashMap<>();
        try {
            UsuarioPermisoEntity creado = service.solicitar(permiso);
            response.put("success", true);
            response.put("mensaje", "Permiso solicitado correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/usuario/{numeroDocumento}")
    public ResponseEntity<List<UsuarioPermisoEntity>> listarPorUsuario(@PathVariable String numeroDocumento) {
        Usuario usuario = usuarioService.buscarPorDocumento(numeroDocumento)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return ResponseEntity.ok(service.listarPorUsuario(usuario));
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Map<String, Object>> aprobar(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        try {
            UsuarioPermisoEntity aprobado = service.aprobar(id);
            response.put("success", true);
            response.put("mensaje", "Permiso aprobado correctamente");
            response.put("data", aprobado);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}/rechazar")
    public ResponseEntity<Map<String, Object>> rechazar(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        Map<String, Object> response = new HashMap<>();
        try {
            String razon = body.get("razon");
            UsuarioPermisoEntity rechazado = service.rechazar(id, razon);
            response.put("success", true);
            response.put("mensaje", "Permiso rechazado correctamente");
            response.put("data", rechazado);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> cancelar(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        try {
            service.cancelar(id);
            response.put("success", true);
            response.put("mensaje", "Permiso cancelado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}