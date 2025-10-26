package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.model.PermisoSistemaEntity;
import co.edu.uco.HumanSolution.model.Rol;
import co.edu.uco.HumanSolution.model.RolPermisoEntity;
import co.edu.uco.HumanSolution.service.PermisoSistemaService;
import co.edu.uco.HumanSolution.service.RolPermisoService;
import co.edu.uco.HumanSolution.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rol-permisos")
@CrossOrigin(origins = "http://localhost:4200")
public class RolPermisoController {

    @Autowired
    private RolPermisoService service;

    @Autowired
    private RolService rolService;

    @Autowired
    private PermisoSistemaService permisoService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> asignarPermiso(@RequestBody Map<String, String> body) {
        Map<String, Object> response = new HashMap<>();
        try {
            String rolId = body.get("rolId");
            String permisoId = body.get("permisoId");

            Rol rol = rolService.buscarPorNombre(rolId)
                    .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

            PermisoSistemaEntity permiso = permisoService.buscarPorNombre(permisoId)
                    .orElseThrow(() -> new IllegalArgumentException("Permiso no encontrado"));

            RolPermisoEntity creado = service.asignarPermisoARol(rol, permiso);
            response.put("success", true);
            response.put("mensaje", "Permiso asignado al rol correctamente");
            response.put("data", creado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/rol/{nombre}")
    public ResponseEntity<List<RolPermisoEntity>> obtenerPermisosDeRol(@PathVariable String nombre) {
        Rol rol = rolService.buscarPorNombre(nombre)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

        return ResponseEntity.ok(service.obtenerPermisosDeRol(rol));
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> quitarPermiso(@RequestBody Map<String, String> body) {
        Map<String, Object> response = new HashMap<>();
        try {
            String rolId = body.get("rolId");
            String permisoId = body.get("permisoId");

            Rol rol = rolService.buscarPorNombre(rolId)
                    .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

            PermisoSistemaEntity permiso = permisoService.buscarPorNombre(permisoId)
                    .orElseThrow(() -> new IllegalArgumentException("Permiso no encontrado"));

            service.quitarPermisoDeRol(rol, permiso);
            response.put("success", true);
            response.put("mensaje", "Permiso quitado del rol correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}