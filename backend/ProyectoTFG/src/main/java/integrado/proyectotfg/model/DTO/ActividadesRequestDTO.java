package integrado.proyectotfg.model.DTO;

import java.io.Serializable;
import java.util.Date;

public class ActividadesRequestDTO implements Serializable {
    private String nombre;
    private String descripcion;
    private Date fec_inicio;
    private Date fec_final;
    private String precio;
    private String materiales;
    private Long tipoActividadId;

    public ActividadesRequestDTO() {
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

    public Long getTipoActividadId() {
        return tipoActividadId;
    }

    public void setTipoActividadId(Long tipoActividadId) {
        this.tipoActividadId = tipoActividadId;
    }
}
