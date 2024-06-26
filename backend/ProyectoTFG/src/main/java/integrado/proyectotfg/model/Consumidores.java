package integrado.proyectotfg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "consumidores")
public class Consumidores {
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
    @OneToMany(mappedBy = "consumidor", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reseñas> reviews = new HashSet<>();

    @OneToMany(mappedBy = "consumidor", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SolicitudesActividades> solicitudesActividades = new HashSet<>();


    // Añade la relación con Usuario
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Consumidores() {
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


    public Set<Reseñas> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Reseñas> reviews) {
        this.reviews = reviews;
    }

    public Set<SolicitudesActividades> getSolicitudesActividades() {
        return solicitudesActividades;
    }

    public void setSolicitudesActividades(Set<SolicitudesActividades> solicitudesActividades) {
        this.solicitudesActividades = solicitudesActividades;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Consumidores{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nif='" + nif + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", reseñas=" + reviews +
                ", solicitudesActividades=" + solicitudesActividades +
                ", usuario=" + usuario +
                '}';
    }
}
