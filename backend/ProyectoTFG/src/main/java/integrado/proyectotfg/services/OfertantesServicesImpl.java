package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Actividades;
import integrado.proyectotfg.model.Ofertantes;
import integrado.proyectotfg.model.Usuario;
import integrado.proyectotfg.repository.ActividadesRepository;
import integrado.proyectotfg.repository.OfertantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfertantesServicesImpl implements OfertantesServices {
    @Autowired
    private OfertantesRepository repository;

    @Autowired
    private ActividadesRepository actividadesRepository;

    @Override
    public List<Ofertantes> listarOfertantes() {
        return repository.findAll();
    }

    @Override
    public Ofertantes guardarOfertante(Ofertantes ofertante) {
        // Verificar si ya existe un ofertante con el mismo nombre y apellidos
        Ofertantes ofertanteExistente = repository.findByNombreAndApellidos(ofertante.getNombre(), ofertante.getApellidos());

        if (ofertanteExistente != null) {
            // Si ya existe un ofertante con el mismo nombre y apellidos, puedes lanzar una excepción o manejarlo de otra forma
            throw new IllegalArgumentException("Ya existe un ofertante con el mismo nombre y apellidos.");
        }

        // Si no existe un ofertante con el mismo nombre y apellidos, guardar el nuevo ofertante
        return repository.save(ofertante);
    }

    @Override
    public Ofertantes obtenerOfertantePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró un ofertante con el ID: " + id));
    }

    @Override
    public Ofertantes actualizarOfertante(Ofertantes detallesOf) {
        Ofertantes ofertanteOptional = repository.findById(detallesOf.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró un ofertante con el ID: " + detallesOf.getId()));

        // Actualizar los detalles del ofertante
        ofertanteOptional.setNombre(detallesOf.getNombre());
        ofertanteOptional.setApellidos(detallesOf.getApellidos());
        ofertanteOptional.setNif(detallesOf.getNif());
        ofertanteOptional.setCorreo(detallesOf.getCorreo());
        ofertanteOptional.setTelefono(detallesOf.getTelefono());
        ofertanteOptional.setDireccion(detallesOf.getDireccion());

        return repository.save(ofertanteOptional);
    }

    @Override
    public void eliminarOfertante(Long id) {
        Ofertantes ofertante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un ofertante con el ID: " + id));

        repository.delete(ofertante);
    }


    @Override
    public void eliminarActividadOfertante(Long ofertanteId, Long actividadId) {
        Ofertantes ofertante = repository.findById(ofertanteId)
                .orElseThrow(() -> new RuntimeException("No existe un ofertante con el ID: " + ofertanteId));

        // Desempaquetar el Optional para obtener la instancia de Actividades
        Actividades actividad = actividadesRepository.findById(actividadId)
                .orElseThrow(() -> new RuntimeException("No existe una actividad con el ID: " + actividadId));

        if (actividad == null) {
            throw new RuntimeException("No existe una actividad con el ID: " + actividadId);
        }

        // Verificar si la actividad pertenece al ofertante
        if (!actividad.getOfertante().equals(ofertante)) {
            throw new RuntimeException("La actividad no pertenece al ofertante con el ID: " + ofertanteId);
        }

        // Eliminar la actividad
        actividadesRepository.delete(actividad);
    }

    @Override
    public Ofertantes obtenerOfertantePorUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }
}
