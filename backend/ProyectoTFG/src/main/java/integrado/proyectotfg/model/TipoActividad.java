package integrado.proyectotfg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_actividad")
public class TipoActividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String descripcion;

    public TipoActividad() {
    }

    //RELACION
    @OneToMany(mappedBy = "tipoActividad", cascade = CascadeType.ALL)
    @JsonIgnore // Ignorar la serialización de actividades para evitar recursión infinita
    private Set<Actividades> actividades = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Actividades> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividades> actividades) {
        this.actividades = actividades;
    }
}
