package integrado.proyectotfg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "reseñas")
public class Reseñas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int Puntos;
    @Column(nullable = false)
    private String comentario;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate fecha;

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_consumidores")
    private Consumidores consumidor;

    @ManyToOne
    @JoinColumn(name = "id_actividades")
    private Actividades actividad;

    public Reseñas() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPuntos() {
        return Puntos;
    }

    public void setPuntos(int puntos) {
        Puntos = puntos;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
}
