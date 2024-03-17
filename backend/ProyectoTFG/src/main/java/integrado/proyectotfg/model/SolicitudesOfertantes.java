package integrado.proyectotfg.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "solicitudes_ofertantes")
public class SolicitudesOfertantes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private Date fec_solictitud;

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_ofertantes")
    private Ofertantes ofertante;

    public SolicitudesOfertantes() {
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

    public Ofertantes getOfertante() {
        return ofertante;
    }

    public void setOfertante(Ofertantes ofertante) {
        this.ofertante = ofertante;
    }
}
