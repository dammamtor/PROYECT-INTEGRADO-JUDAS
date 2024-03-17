package integrado.proyectotfg.services;

import integrado.proyectotfg.model.TipoActividad;
import integrado.proyectotfg.repository.TipoActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TipoActividadServicesImpl implements TipoActividadServices {
    @Autowired
    TipoActividadRepository tipoActividadRepository;

    @Override
    public List<TipoActividad> listarTipoActividades() {
        return tipoActividadRepository.findAll();
    }


    @Override
    public TipoActividad guardarTipoActividad(TipoActividad tipoActividad) {
        return tipoActividadRepository.save(tipoActividad);
    }

    @Override
    public TipoActividad obtenerTipoPorId(Long id) {
        return tipoActividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró este tipo de actividad con el ID: " + id));
    }

    @Override
    public TipoActividad actualizarTipoActividad(TipoActividad tipoActividad) {
        // Buscar el tipo de actividad en la base de datos por su ID
        TipoActividad tipoActividadOptional = tipoActividadRepository.findById(tipoActividad.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró un tipo de actividad con el ID: " + tipoActividad.getId()));

        // Actualizar los detalles del tipo de actividad
        tipoActividadOptional.setNombre(tipoActividad.getNombre());
        tipoActividadOptional.setDescripcion(tipoActividad.getDescripcion());

        // Guardar los cambios en la base de datos y devolver el tipo de actividad actualizado
        return tipoActividadRepository.save(tipoActividadOptional);
    }

    @Override
    public void eliminarTipo(Long id) {
        TipoActividad tipoActividad = tipoActividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe ese tipo con el ID: " + id));
        tipoActividadRepository.delete(tipoActividad);
    }
}
