package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Consumidores;

import java.util.List;

public interface ConsumidoresServices {
    List<Consumidores> listarConsumidores();
    Consumidores guardarConsumidor(Consumidores consumidor);
    Consumidores obtenerConsumidorPorId(Long id);
    Consumidores actualizarConsumidor(Consumidores detallesOf);
    void eliminarConsumidor(Long id);
}
