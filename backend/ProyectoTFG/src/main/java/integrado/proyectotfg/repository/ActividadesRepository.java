package integrado.proyectotfg.repository;


import integrado.proyectotfg.model.Actividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActividadesRepository extends JpaRepository<Actividades, Long> {

    List<Actividades> findByNombreContaining(String busqueda);

}
