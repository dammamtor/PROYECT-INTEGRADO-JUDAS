package integrado.proyectotfg.services;

import integrado.proyectotfg.model.TipoActividad;

import java.util.List;

public interface TipoActividadServices {
    List<TipoActividad> listarTipoActividades();

    TipoActividad guardarTipoActividad(TipoActividad tipoActividad);

    TipoActividad obtenerTipoPorId(Long id);

    TipoActividad actualizarTipoActividad(TipoActividad tipoActividad);

    void eliminarTipo(Long id);
}
