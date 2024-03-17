package integrado.proyectotfg.repository;

import integrado.proyectotfg.model.TipoActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoActividadRepository extends JpaRepository<TipoActividad, Long> {
    TipoActividad findByNombre(String nombreTipoActividad);
}
