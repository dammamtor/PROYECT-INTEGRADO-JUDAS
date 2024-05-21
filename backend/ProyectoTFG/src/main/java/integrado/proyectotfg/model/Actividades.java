package integrado.proyectotfg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actividades")
public class Actividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String nombre;
    @Column(nullable = false, length = 1000)
    private String descripcion;
    @Column(nullable = false)
    private Date fec_inicio;
    @Column(nullable = false)
    private Date fec_final;
    @Column(nullable = false)
    private String precio;
    @Column(length = 1000)
    private String materiales;


    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_ofertantes")

    private Ofertantes ofertante;

    @ManyToOne
    @JoinColumn(name = "tipo_actividad_id")
    private TipoActividad tipoActividad;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
    @JsonIgnore // Ignorar la serialización de actividades para evitar recursión infinita
    private Set<SolicitudesActividades> solicitudesActividades = new HashSet<>();

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
    @JsonIgnore // Ignorar la serialización de actividades para evitar recursión infinita
    private Set<Reseñas> reseñas = new HashSet<>();


    public Actividades() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getFec_inicio() {
        return fec_inicio;
    }

    public void setFec_inicio(Date fec_inicio) {
        this.fec_inicio = fec_inicio;
    }

    public Date getFec_final() {
        return fec_final;
    }

    public void setFec_final(Date fec_final) {
        this.fec_final = fec_final;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public Ofertantes getOfertante() {
        return ofertante;
    }

    public void setOfertante(Ofertantes ofertante) {
        this.ofertante = ofertante;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public Set<SolicitudesActividades> getSolicitudesActividades() {
        return solicitudesActividades;
    }

    public void setSolicitudesActividades(Set<SolicitudesActividades> solicitudesActividades) {
        this.solicitudesActividades = solicitudesActividades;
    }

    public Set<Reseñas> getReseñas() {
        return reseñas;
    }

    public void setReseñas(Set<Reseñas> reseñas) {
        this.reseñas = reseñas;
    }
}
