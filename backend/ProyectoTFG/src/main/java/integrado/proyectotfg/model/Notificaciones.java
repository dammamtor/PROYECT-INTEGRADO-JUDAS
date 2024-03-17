package integrado.proyectotfg.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "notificaciones")
public class Notificaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String contenido;
    @Column(nullable = false)
    private Date fec_notif;
    @Column(nullable = false)
    private Boolean leido;

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_consumidores")
    private Consumidores consumidor;

    public Notificaciones() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFec_notif() {
        return fec_notif;
    }

    public void setFec_notif(Date fec_notif) {
        this.fec_notif = fec_notif;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public Consumidores getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidores consumidor) {
        this.consumidor = consumidor;
    }
}
