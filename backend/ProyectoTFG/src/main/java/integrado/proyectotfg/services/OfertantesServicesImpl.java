package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Ofertantes;
import integrado.proyectotfg.repository.OfertantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertantesServicesImpl implements OfertantesServices {
    @Autowired
    private OfertantesRepository repository;

    @Override
    public List<Ofertantes> listarOfertantes() {
        return repository.findAll();
    }

    @Override
    public Ofertantes guardarOfertante(Ofertantes ofertante) {
        return repository.save(ofertante);    }

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
}
