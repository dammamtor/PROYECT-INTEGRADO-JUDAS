package integrado.proyectotfg.model;

import jakarta.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "id_actividades")
    private Actividades actividades;

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

    public Actividades getActividades() {
        return actividades;
    }

    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }
}
