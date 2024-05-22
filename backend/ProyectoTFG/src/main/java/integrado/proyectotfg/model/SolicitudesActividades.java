package integrado.proyectotfg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "solicitudes_actividades")
public class SolicitudesActividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private Date fec_solictitud;

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_consumidores")
    private Consumidores consumidor;

    @ManyToOne
    @JoinColumn(name = "id_actividad")
    private Actividades actividad;

    @ManyToOne
    @JoinColumn(name = "id_ofertantes")
    @JsonIgnore // Ignorar la serialización de actividades para evitar recursión infinita
    private Ofertantes ofertante;

    public SolicitudesActividades() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFec_solictitud() {
        return fec_solictitud;
    }

    public void setFec_solictitud(Date fec_solictitud) {
        this.fec_solictitud = fec_solictitud;
    }

    public Consumidores getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidores consumidor) {
        this.consumidor = consumidor;
    }

    public Actividades getActividad() {
        return actividad;
    }

    public void setActividad(Actividades actividad) {
        this.actividad = actividad;
    }

    public Ofertantes getOfertante() {
        return ofertante;
    }

    public void setOfertante(Ofertantes ofertante) {
        this.ofertante = ofertante;
    }
}
