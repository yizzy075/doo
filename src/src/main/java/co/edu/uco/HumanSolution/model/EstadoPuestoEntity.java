package co.edu.uco.HumanSolution.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "estado_puesto")
public class EstadoPuestoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    // Constructores
    public EstadoPuestoEntity() {}

    public EstadoPuestoEntity(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}