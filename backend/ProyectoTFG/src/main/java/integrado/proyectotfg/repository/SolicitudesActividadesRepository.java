package integrado.proyectotfg.repository;

import integrado.proyectotfg.model.SolicitudesActividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudesActividadesRepository extends JpaRepository<SolicitudesActividades, Long> {
    List<SolicitudesActividades> findByConsumidorId(Long idConsumidor);
}
