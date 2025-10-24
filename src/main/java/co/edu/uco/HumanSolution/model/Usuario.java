package co.edu.uco.HumanSolution.model;

import jakarta.persistence.*;
import co.edu.uco.HumanSolution.exception.UsuarioException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity Usuario - Representa un usuario del sistema HumanSolution
 *
 * Políticas de Negocio implementadas:
 * - Usuario-PO-01: Nombre, apellido y correo son obligatorios
 * - Usuario-PO-02: Número de documento es obligatorio y debe ser válido (6-20 dígitos)
 * - Usuario-PO-03: No se permiten documentos duplicados
 * - Usuario-PO-04: No se permiten correos duplicados
 * - Usuario-PO-05: El correo debe tener formato válido (@dominio)
 * - Usuario-PO-06: La contraseña debe cumplir requisitos (obligatoria, 6+ caracteres, mayúscula, número)
 * - Usuario-PO-07: El rol debe ser Postulante, Empleado o RRHH
 *
 * @author HumanSolution Team
 * @version 1.0
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 15)
    private String numeroDocumento;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(unique = true, nullable = false, length = 100)
    private String correo;

    @Column(nullable = false, length = 100)
    private String contrasenia;

    @Column(nullable = false, length = 50)
    private String rol;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(nullable = false)
    private boolean activo = true;

    // Constructor vacío
    public Usuario() {}

    // Constructor completo
    public Usuario(String nombre, String apellido, String numeroDocumento,
                   String correo, String contrasenia, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDocumento = numeroDocumento;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    /**
     * Método de validación con políticas de negocio
     * Implementa las reglas Usuario-PO-01 a Usuario-PO-07
     *
     * @throws UsuarioException cuando alguna validación falla
     */
    public void validar() throws UsuarioException {
        // Usuario-PO-01: El nombre es obligatorio
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new UsuarioException("El nombre es obligatorio", "nombre");
        }

        // Usuario-PO-01: El apellido es obligatorio
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new UsuarioException("El apellido es obligatorio", "apellido");
        }

        // Usuario-PO-02: El número de documento es obligatorio
        if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
            throw new UsuarioException("El número de documento es obligatorio", "numeroDocumento");
        }

        // Usuario-PO-02: El número de documento debe corresponder a una persona real
        if (!numeroDocumento.matches("^\\d{6,20}$")) {
            throw new UsuarioException("El número de documento debe tener entre 6 y 20 dígitos", "numeroDocumento");
        }

        // Usuario-PO-01 y Usuario-PO-05: El correo es obligatorio y debe tener formato válido
        if (correo == null || correo.trim().isEmpty()) {
            throw new UsuarioException("El correo electrónico es obligatorio", "correo");
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new UsuarioException("El correo electrónico no es válido", "correo");
        }

        // Usuario-PO-06: La contraseña debe cumplir con los siguientes requisitos:
        // - Ser obligatoria
        // - Tener al menos 6 caracteres
        // - Contener al menos una letra mayúscula
        // - Contener al menos un número

        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            throw new UsuarioException("La contraseña es obligatoria", "contrasenia");
        }

        if (contrasenia.length() < 6) {
            throw new UsuarioException("La contraseña debe tener al menos 6 caracteres", "contrasenia");
        }

        if (!contrasenia.matches(".*[A-Z].*")) {
            throw new UsuarioException("La contraseña debe contener al menos una letra mayúscula", "contrasenia");
        }

        if (!contrasenia.matches(".*\\d.*")) {
            throw new UsuarioException("La contraseña debe contener al menos un número", "contrasenia");
        }

        // Usuario-PO-07: El rol asignado debe pertenecer al catálogo permitido
        if (rol == null || rol.trim().isEmpty()) {
            throw new UsuarioException("El rol es obligatorio", "rol");
        }

        // Validar que el rol sea uno de los permitidos
        if (!rol.equals("Postulante") && !rol.equals("Empleado") && !rol.equals("RRHH")) {
            throw new UsuarioException("El rol no es válido. Debe ser: Postulante, Empleado o RRHH", "rol");
        }
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}