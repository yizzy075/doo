package co.edu.uco.HumanSolution.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "evaluacion_desempeno", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "fecha"})
})
public class EvaluacionDesempenoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Integer calificacion;

    @Column(length = 500)
    private String observacion;

    // Constructores
    public EvaluacionDesempenoEntity() {}

    public EvaluacionDesempenoEntity(Usuario usuario, LocalDate fecha,
                                     Integer calificacion, String observacion) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.observacion = observacion;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Integer getCalificacion() { return calificacion; }
    public void setCalificacion(Integer calificacion) { this.calificacion = calificacion; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}