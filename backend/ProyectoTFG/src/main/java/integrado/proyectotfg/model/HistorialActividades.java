package integrado.proyectotfg.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "historial_actividades")
public class HistorialActividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date fec_inicio;
    @Column(nullable = false)
    private Date fec_compl;

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_consumidores")
    private Consumidores consumidor;

    public HistorialActividades() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFec_inicio() {
        return fec_inicio;
    }

    public void setFec_inicio(Date fec_inicio) {
        this.fec_inicio = fec_inicio;
    }

    public Date getFec_compl() {
        return fec_compl;
    }

    public void setFec_compl(Date fec_compl) {
        this.fec_compl = fec_compl;
    }

    public Consumidores getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidores consumidor) {
        this.consumidor = consumidor;
    }
}
