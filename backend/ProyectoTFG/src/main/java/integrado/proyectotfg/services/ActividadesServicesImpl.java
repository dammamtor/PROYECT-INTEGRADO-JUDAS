package integrado.proyectotfg.services;


import integrado.proyectotfg.model.Actividades;
import integrado.proyectotfg.model.Ofertantes;
import integrado.proyectotfg.model.TipoActividad;
import integrado.proyectotfg.repository.ActividadesRepository;
import integrado.proyectotfg.repository.TipoActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<Actividades> listaActividades = actividadesRepository.findAll();
        List<Actividades> actividadesFiltradas = new ArrayList<>();

        for (Actividades actividad : listaActividades) {
            for (TipoActividad tipoActividad : actividad.getTipoActividads()) {
                if (tipoActividad.getNombre().equals(tipo)) {
                    actividadesFiltradas.add(actividad);
                    break; // Termina la iteración cuando se encuentra un tipo coincidente
                }
            }
        }

        return actividadesFiltradas;
    }

    @Override
    public List<Actividades> buscarActividadesPorNombre(String busqueda) {
        return actividadesRepository.findByNombre(busqueda);
    }

    @Override
    public Actividades crearActividadConTipo(Ofertantes ofertante, Actividades nuevaActividad, Long tipoActividadId) {
        // Asignar el ofertante a la nueva actividad
        nuevaActividad.setOfertante(ofertante);

        // Guardar la nueva actividad en la base de datos
        Actividades actividadGuardada = actividadesRepository.save(nuevaActividad);

        // Obtener el tipo de actividad por su ID
        Optional<TipoActividad> tipoActividadOptional = tipoActividadRepository.findById(tipoActividadId);
        if (!tipoActividadOptional.isPresent()) {
            throw new RuntimeException("No se encontró un tipo de actividad con el ID: " + tipoActividadId);
        }

        TipoActividad tipoActividad = tipoActividadOptional.get();

        // Asignar el tipo de actividad a la actividad guardada
        actividadGuardada.getTipoActividads().add(tipoActividad);
        actividadGuardada = actividadesRepository.save(actividadGuardada);

        return actividadGuardada;
    }


}
