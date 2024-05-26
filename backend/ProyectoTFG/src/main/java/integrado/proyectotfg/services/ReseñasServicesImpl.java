package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Reseñas;
import integrado.proyectotfg.repository.ConsumidoresRepository;
import integrado.proyectotfg.repository.ReseñasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReseñasServicesImpl implements ReseñasServices {
    @Autowired
    ReseñasRepository repository;
    @Autowired
    ConsumidoresRepository consumidoresRepository;

    @Override
    public Reseñas crearReseña(Reseñas reseña) {
        return repository.save(reseña);
    }

    @Override
    public List<Reseñas> obtenerReseñaPorIDconsumidor(Long idConsumidor) {
        return repository.findByConsumidorId(idConsumidor);
    }


    @Override
    public List<Reseñas> listaReseñas(Long idOfertante) {
        return repository.findByActividad_Ofertante_Id(idOfertante);
    }

    @Override
    public boolean eliminarReseñaPorId(Long idReseña) {
        if (repository.existsById(idReseña)) {
            repository.deleteById(idReseña);
            return true;
        } else {
            return false;
        }
    }
}
