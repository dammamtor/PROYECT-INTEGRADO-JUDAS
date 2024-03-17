package integrado.proyectotfg.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ofertantes")
public class Ofertantes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 60)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String apellidos;
    @Column(unique = true, length = 9, nullable = false)
    private String nif;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false, unique = true)
    private String correo;
    @Column(nullable = false)
    private String direccion;

    //RELACIONES
    @OneToMany(mappedBy = "ofertante", cascade = CascadeType.ALL)
    private Set<Actividades> actividades;

    @OneToMany(mappedBy = "ofertante", cascade = CascadeType.ALL)
    private Set<SolicitudesOfertantes> solicitudesOfertantes;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "ofertantes_consumidores",
            joinColumns = {@JoinColumn(name = "idOfertantes")},
            inverseJoinColumns = {@JoinColumn(name = "idConsumidores")})
    private Set<Consumidores> consumidor = new HashSet<>();

    public Ofertantes() {
    }

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<Actividades> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividades> actividades) {
        this.actividades = actividades;
    }

    public Set<SolicitudesOfertantes> getSolicitudesOfertantes() {
        return solicitudesOfertantes;
    }

    public void setSolicitudesOfertantes(Set<SolicitudesOfertantes> solicitudesOfertantes) {
        this.solicitudesOfertantes = solicitudesOfertantes;
    }

    public Set<Consumidores> getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Set<Consumidores> consumidor) {
        this.consumidor = consumidor;
    }
}
