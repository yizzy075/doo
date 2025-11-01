package co.edu.uco.HumanSolution.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "unidad_organizativa")
public class UnidadOrganizativaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 200)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "unidad_superior_id")
    private UnidadOrganizativaEntity unidadSuperior;

    // Constructores
    public UnidadOrganizativaEntity() {}

    public UnidadOrganizativaEntity(String nombre, UnidadOrganizativaEntity unidadSuperior) {
        this.nombre = nombre;
        this.unidadSuperior = unidadSuperior;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public UnidadOrganizativaEntity getUnidadSuperior() { return unidadSuperior; }
    public void setUnidadSuperior(UnidadOrganizativaEntity unidadSuperior) {
        this.unidadSuperior = unidadSuperior;
    }
}