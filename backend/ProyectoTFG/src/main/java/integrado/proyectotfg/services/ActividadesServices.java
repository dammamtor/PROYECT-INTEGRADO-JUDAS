package integrado.proyectotfg.services;


import integrado.proyectotfg.model.Actividades;
import integrado.proyectotfg.model.Ofertantes;

import java.util.List;

public interface ActividadesServices {
    List<Actividades> listarActividades();
    List<Actividades> listarActividadesPorTipo(String tipo);
    List<Actividades> buscarActividadesPorNombre(String busqueda);

    Actividades guardarActividad(Actividades actividad);
}
