package integrado.proyectotfg.services;

import integrado.proyectotfg.controller.ConsumidoresController;
import integrado.proyectotfg.model.Consumidores;
import integrado.proyectotfg.model.Usuario;
import integrado.proyectotfg.repository.ConsumidoresRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumidoresServicesImpl implements ConsumidoresServices{
    static final Logger logger = LoggerFactory.getLogger(ConsumidoresServicesImpl.class);

    @Autowired
    ConsumidoresRepository consumidoresRepository;

    @Override
    public List<Consumidores> listarConsumidores() {
        return consumidoresRepository.findAll();
    }

    @Override
    public Consumidores guardarConsumidor(Consumidores consumidor) {
        Consumidores consumidorExistente = consumidoresRepository.findByNombreAndApellidos(consumidor.getNombre(), consumidor.getApellidos());

        if( consumidorExistente != null){
            throw new IllegalArgumentException("Ya existe un consumidor con el mismo nombre y apellidos.");
        }

        return consumidoresRepository.save(consumidor);
    }

    @Override
    public Consumidores obtenerConsumidorPorId(Long id) {
        return consumidoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró un consumidor con el ID: " + id));
    }

    @Override
    public Consumidores actualizarConsumidor(Consumidores detallesOf) {
        Consumidores consumidorOptional = consumidoresRepository.findById(detallesOf.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró un consumidor con el ID: " + detallesOf.getId()));

        // Actualizar los detalles del ofertante
        consumidorOptional.setNombre(detallesOf.getNombre());
        consumidorOptional.setApellidos(detallesOf.getApellidos());
        consumidorOptional.setNif(detallesOf.getNif());
        consumidorOptional.setCorreo(detallesOf.getCorreo());
        consumidorOptional.setTelefono(detallesOf.getTelefono());
        consumidorOptional.setDireccion(detallesOf.getDireccion());

        return consumidoresRepository.save(consumidorOptional);
    }

    @Override
    public void eliminarConsumidor(Long id) {
        Consumidores consumidores = consumidoresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un consumidor con el ID: " + id));

        consumidoresRepository.delete(consumidores);
    }

    @Override
    public Consumidores obtenerConsumidorPorUsuario(Usuario usuario) {
        logger.info("Usuario en consumidores services impl: " + usuario);
        Consumidores consumidor = consumidoresRepository.findByUsuario(usuario);
        logger.info("Consumidor devuelto: " + consumidor);

        if (consumidor == null) {
            throw new RuntimeException("No se encontró un consumidor asociado al usuario proporcionado");
        }

        return consumidor;
    }


}
