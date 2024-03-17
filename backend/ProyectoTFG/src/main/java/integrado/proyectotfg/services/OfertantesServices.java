package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Ofertantes;

import java.util.List;

public interface OfertantesServices {
    List<Ofertantes> listarOfertantes();
    Ofertantes guardarOfertante(Ofertantes ofertante);
    Ofertantes obtenerOfertantePorId(Long id);
    Ofertantes actualizarOfertante(Ofertantes detallesOf);
    void eliminarOfertante(Long id);
}
