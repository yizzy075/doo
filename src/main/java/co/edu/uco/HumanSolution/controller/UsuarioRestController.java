package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.Usuario;
import co.edu.uco.HumanSolution.service.UsuarioService;
import co.edu.uco.HumanSolution.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioRestController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioRestController.class);

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Endpoint de prueba
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("✅ API funcionando correctamente");
    }

    /**
     * Obtiene la lista de roles disponibles
     */
    @GetMapping("/roles")
    public ResponseEntity<String[]> obtenerRoles() {
        logger.info("Obteniendo lista de roles");
        String[] roles = {"Postulante", "Empleado", "RRHH"};
        return ResponseEntity.ok(roles);
    }

    /**
     * Registra un nuevo usuario
     */
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrarUsuario(@RequestBody Usuario usuario) {
        Map<String, Object> response = new HashMap<>();

        try {
            logger.info("Recibida petición de registro: {}", usuario.getCorreo());

            Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);

            response.put("success", true);
            response.put("mensaje", "Usuario registrado correctamente");
            response.put("usuario", usuarioRegistrado);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (UsuarioException e) {
            logger.warn("Error de validación al registrar usuario: {}", e.getMessage());

            response.put("success", false);
            response.put("mensaje", e.getMessage());
            response.put("campoAfectado", e.getCampoAfectado());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (Exception e) {
            logger.error("Error inesperado al registrar usuario", e);

            response.put("success", false);
            response.put("mensaje", "Error inesperado: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
