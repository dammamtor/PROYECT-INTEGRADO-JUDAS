package integrado.proyectotfg.services;

import integrado.proyectotfg.model.SolicitudesActividades;
import integrado.proyectotfg.repository.SolicitudesActividadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SolicitudesActividadesServicesImpl implements SolicitudesActividadesServices{
    @Autowired
    SolicitudesActividadesRepository repository;
    @Override
    public SolicitudesActividades guardarSolicitudActividad(SolicitudesActividades solicitud) {
        // Establecer el estado
        solicitud.setEstado("Pendiente");

        // Establecer la fecha de la solicitud como la fecha actual
        solicitud.setFec_solictitud(new Date());

        // Guardar la solicitud de actividad en la base de datos
        SolicitudesActividades solicitudGuardada = repository.save(solicitud);

        return solicitudGuardada;
    }

    @Override
    public SolicitudesActividades obtenerSolicitudActividadPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe esa solicitud del consumidor con el ID: " + id));
    }

    @Override
    public void eliminarSolicitudActividad(Long id) {
        SolicitudesActividades solicitudActividad = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe esa solicitud del consumidor con el ID: " + id));

        repository.delete(solicitudActividad);
    }

    @Override
    public SolicitudesActividades validarSolicitudActividad(SolicitudesActividades solicitudesActividades) {
        SolicitudesActividades solicitudValidar = repository.findById(solicitudesActividades.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró una solicitud de actividad con el ID: " + solicitudesActividades.getId()));

        //ACTUALIZAR LOS DETALLES
        solicitudValidar.setEstado("Aceptado");

        return repository.save(solicitudValidar);
    }

    @Override
    public List<SolicitudesActividades> obtenerSolicitudesActividadesPorConsumidor(Long idConsumidor) {
        return repository.findByConsumidorId(idConsumidor);
    }

    @Override
    public List<SolicitudesActividades> obtenerSolicitudesActividadesPorOfertante(Long idOfertante) {
        return repository.findByOfertanteId(idOfertante);
    }
}
