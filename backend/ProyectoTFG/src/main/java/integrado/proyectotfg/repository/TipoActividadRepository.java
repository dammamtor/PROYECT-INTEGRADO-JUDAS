package integrado.proyectotfg.repository;

import integrado.proyectotfg.model.TipoActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoActividadRepository extends JpaRepository<TipoActividad, Long> {
    List<TipoActividad> findByNombre(String nombreTipoActividad);
}
