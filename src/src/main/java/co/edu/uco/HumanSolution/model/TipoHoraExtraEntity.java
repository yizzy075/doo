package co.edu.uco.HumanSolution.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tipo_hora_extra")
public class TipoHoraExtraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Integer recargo;

    // Constructores
    public TipoHoraExtraEntity() {}

    public TipoHoraExtraEntity(String nombre, Integer recargo) {
        this.nombre = nombre;
        this.recargo = recargo;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getRecargo() { return recargo; }
    public void setRecargo(Integer recargo) { this.recargo = recargo; }
}