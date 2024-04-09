package integrado.proyectotfg.repository;

import integrado.proyectotfg.model.Reseñas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReseñasRepository extends JpaRepository<Reseñas, Long> {
    List<Reseñas> findByConsumidorId(Long idConsumidor);


    List<Reseñas> findByActividad_Ofertante_Id(Long idOfertante);
}
