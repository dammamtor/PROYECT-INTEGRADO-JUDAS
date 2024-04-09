package integrado.proyectotfg.services;

import integrado.proyectotfg.model.SolicitudesActividades;

import java.util.List;

public interface SolicitudesActividadesServices {
    SolicitudesActividades guardarSolicitudActividad(SolicitudesActividades solicitudesActividades);
    SolicitudesActividades obtenerSolicitudActividadPorId(Long id);
    void eliminarSolicitudActividad(Long id);
    SolicitudesActividades validarSolicitudActividad (SolicitudesActividades solicitudesActividades);
    List<SolicitudesActividades> obtenerSolicitudesActividadesPorConsumidor(Long idConsumidor);

    List<SolicitudesActividades> obtenerSolicitudesActividadesPorOfertante(Long idOfertante);
}
