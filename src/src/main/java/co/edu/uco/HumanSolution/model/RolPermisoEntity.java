package co.edu.uco.HumanSolution.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rol_permiso", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rol_id", "permiso_id"})
})
public class RolPermisoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;  // ← Cambió de RolEntity a Rol

    @ManyToOne
    @JoinColumn(name = "permiso_id", nullable = false)
    private PermisoSistemaEntity permiso;

    // Constructores
    public RolPermisoEntity() {}

    public RolPermisoEntity(Rol rol, PermisoSistemaEntity permiso) {
        this.rol = rol;
        this.permiso = permiso;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public PermisoSistemaEntity getPermiso() { return permiso; }
    public void setPermiso(PermisoSistemaEntity permiso) { this.permiso = permiso; }
}