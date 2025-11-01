package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.UsuarioDocumentoEntity;
import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.service.UsuarioDocumentoService;
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
@RequestMapping("/api/usuario-documentos")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioDocumentoController {

    @Autowired
    private UsuarioDocumentoService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> subir(@RequestBody UsuarioDocumentoEntity documento) {
        Map<String, Object> response = new HashMap<>();
        try {
            UsuarioDocumentoEntity creado = service.subir(documento);
            response.put("success", true);
            response.put("mensaje", "Documento subido correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/usuario/{numeroDocumento}")
    public ResponseEntity<List<UsuarioDocumentoEntity>> listarPorUsuario(@PathVariable String numeroDocumento) {
        Usuario usuario = usuarioService.buscarPorDocumento(numeroDocumento)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return ResponseEntity.ok(service.listarPorUsuario(usuario));
    }

    @PutMapping("/{id}/validar")
    public ResponseEntity<Map<String, Object>> validar(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        try {
            UsuarioDocumentoEntity validado = service.validar(id);
            response.put("success", true);
            response.put("mensaje", "Documento validado correctamente");
            response.put("data", validado);
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
            response.put("mensaje", "Documento eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}