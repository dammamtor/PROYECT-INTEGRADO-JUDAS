package integrado.proyectotfg.services;

import integrado.proyectotfg.model.SolicitudesActividades;
import integrado.proyectotfg.repository.SolicitudesActividadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

        // Guardar la solicitud en la base de datos
        return repository.save(solicitud);
    }
}
