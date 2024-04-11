package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Ofertantes;
import integrado.proyectotfg.model.Usuario;

import java.util.List;

public interface OfertantesServices {
    List<Ofertantes> listarOfertantes();
    Ofertantes guardarOfertante(Ofertantes ofertante);
    Ofertantes obtenerOfertantePorId(Long id);
    Ofertantes actualizarOfertante(Ofertantes detallesOf);
    void eliminarOfertante(Long id);

    void eliminarActividadOfertante(Long ofertanteId, Long actividadId);

    Ofertantes obtenerOfertantePorUsuario(Usuario usuario);
}
