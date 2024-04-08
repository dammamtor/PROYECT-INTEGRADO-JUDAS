package integrado.proyectotfg.services;


import integrado.proyectotfg.model.Actividades;
import integrado.proyectotfg.model.TipoActividad;
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
        List<TipoActividad> tiposActividad = tipoActividadRepository.findByNombre(tipo);
        if (!tiposActividad.isEmpty()) {
            TipoActividad tipoActividad = tiposActividad.get(0);
            return actividadesRepository.findByTipoActividad(tipoActividad);
        } else {
            throw new RuntimeException("El tipo de actividad '" + tipo + "' no existe.");
        }
    }

    @Override
    public List<Actividades> buscarActividadesPorNombre(String busqueda) {
        return actividadesRepository.findByNombreContaining(busqueda);
    }

    @Override
    public Actividades guardarActividad(Actividades actividad) {
        return actividadesRepository.save(actividad);
    }

    @Override
    public Actividades obtenerActividadPorId(Long idActividad) {
        return actividadesRepository.findById(idActividad)
                .orElseThrow(() -> new RuntimeException("No se encontró una actividad con el ID: " + idActividad));
    }

    @Override
    public void eliminarActividad(Long id) {
        Actividades actividad = actividadesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe ese tipo con el ID: " + id));
        actividadesRepository.delete(actividad);
    }

}
