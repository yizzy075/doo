package co.edu.uco.HumanSolution.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "usuario_permiso", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "tipo_permiso_id", "fecha_inicio", "fecha_fin"})
})
public class UsuarioPermisoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_permiso_id", nullable = false)
    private TipoPermisoEntity tipoPermiso;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "estado_solicitud_id", nullable = false)
    private EstadoSolicitudEntity estadoSolicitud;

    @Column(length = 500)
    private String observacion;

    // Constructores
    public UsuarioPermisoEntity() {}

    public UsuarioPermisoEntity(Usuario usuario, TipoPermisoEntity tipoPermiso,
                                LocalDate fechaInicio, LocalDate fechaFin,
                                EstadoSolicitudEntity estadoSolicitud) {
        this.usuario = usuario;
        this.tipoPermiso = tipoPermiso;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoSolicitud = estadoSolicitud;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public TipoPermisoEntity getTipoPermiso() { return tipoPermiso; }
    public void setTipoPermiso(TipoPermisoEntity tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public EstadoSolicitudEntity getEstadoSolicitud() { return estadoSolicitud; }
    public void setEstadoSolicitud(EstadoSolicitudEntity estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}