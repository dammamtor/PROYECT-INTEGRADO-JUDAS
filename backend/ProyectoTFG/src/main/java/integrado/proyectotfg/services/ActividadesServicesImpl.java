package integrado.proyectotfg.services;


import integrado.proyectotfg.model.Actividades;
import integrado.proyectotfg.repository.ActividadesRepository;
import integrado.proyectotfg.repository.TipoActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadesServicesImpl implements ActividadesServices {
    @Autowired
    private ActividadesRepository actividadesRepository;

    @Autowired
    private TipoActividadRepository tipoActividadRepository;

    @Override
    public List<Actividades> listarActividades() {
        return actividadesRepository.findAll();
    }

    @Override
    public List<Actividades> listarActividadesPorTipo(String tipo) {
        return null;
    }

    @Override
    public List<Actividades> buscarActividadesPorNombre(String busqueda) {
        return actividadesRepository.findByNombreContaining(busqueda);
    }
    @Override
    public Actividades guardarActividad(Actividades actividad) {
        return actividadesRepository.save(actividad);
    }

}
