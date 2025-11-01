package co.edu.uco.HumanSolution.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "usuario_documento", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "tipo_documento_id", "fecha"})
})
public class UsuarioDocumentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_documento_id", nullable = false)
    private TipoDocumentoEntity tipoDocumento;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "estado_solicitud_id", nullable = false)
    private EstadoSolicitudEntity estadoSolicitud;

    @Column(length = 500)
    private String descripcion;

    // Constructores
    public UsuarioDocumentoEntity() {}

    public UsuarioDocumentoEntity(Usuario usuario, TipoDocumentoEntity tipoDocumento,
                                  LocalDate fecha, EstadoSolicitudEntity estadoSolicitud) {
        this.usuario = usuario;
        this.tipoDocumento = tipoDocumento;
        this.fecha = fecha;
        this.estadoSolicitud = estadoSolicitud;
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoDocumentoEntity getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoSolicitudEntity getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(EstadoSolicitudEntity estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}