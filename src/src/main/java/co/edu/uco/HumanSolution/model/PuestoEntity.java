package co.edu.uco.HumanSolution.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "puesto", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombre", "unidad_id"})
})
public class PuestoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "unidad_id", nullable = false)
    private UnidadOrganizativaEntity unidad;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoPuestoEntity estado;

    @ManyToOne
    @JoinColumn(name = "jefe_id")
    private PuestoEntity jefe;

    // Constructores
    public PuestoEntity() {}

    public PuestoEntity(String nombre, UnidadOrganizativaEntity unidad,
                        EstadoPuestoEntity estado) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.estado = estado;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public UnidadOrganizativaEntity getUnidad() { return unidad; }
    public void setUnidad(UnidadOrganizativaEntity unidad) { this.unidad = unidad; }

    public EstadoPuestoEntity getEstado() { return estado; }
    public void setEstado(EstadoPuestoEntity estado) { this.estado = estado; }

    public PuestoEntity getJefe() { return jefe; }
    public void setJefe(PuestoEntity jefe) { this.jefe = jefe; }
}