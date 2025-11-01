package co.edu.uco.HumanSolution.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "usuario_hora_extra", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "fecha", "tipo_hora_extra_id"})
})
public class UsuarioHoraExtraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "estado_solicitud_id", nullable = false)
    private EstadoSolicitudEntity estadoSolicitud;

    @Column(nullable = false)
    private Integer horas;

    @ManyToOne
    @JoinColumn(name = "tipo_hora_extra_id", nullable = false)
    private TipoHoraExtraEntity tipoHoraExtra;

    // Constructores
    public UsuarioHoraExtraEntity() {}

    public UsuarioHoraExtraEntity(Usuario usuario, LocalDate fecha, Integer horas,
                                  TipoHoraExtraEntity tipoHoraExtra,
                                  EstadoSolicitudEntity estadoSolicitud) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.horas = horas;
        this.tipoHoraExtra = tipoHoraExtra;
        this.estadoSolicitud = estadoSolicitud;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public EstadoSolicitudEntity getEstadoSolicitud() { return estadoSolicitud; }
    public void setEstadoSolicitud(EstadoSolicitudEntity estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public Integer getHoras() { return horas; }
    public void setHoras(Integer horas) { this.horas = horas; }

    public TipoHoraExtraEntity getTipoHoraExtra() { return tipoHoraExtra; }
    public void setTipoHoraExtra(TipoHoraExtraEntity tipoHoraExtra) {
        this.tipoHoraExtra = tipoHoraExtra;
    }
}