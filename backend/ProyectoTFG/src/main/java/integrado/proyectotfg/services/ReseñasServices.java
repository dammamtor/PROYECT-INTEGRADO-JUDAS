package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Reseñas;

import java.util.List;

public interface ReseñasServices {
    Reseñas crearReseña(Reseñas reseña);

    List<Reseñas> obtenerReseñaPorIDconsumidor(Long idConsumidor);
    List<Reseñas> listaReseñas(Long idOfertante);

    boolean eliminarReseñaPorId(Long idReseña);
}
