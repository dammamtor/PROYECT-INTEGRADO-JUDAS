package integrado.proyectofinaldmt.repository;

import integrado.proyectofinaldmt.modelo.Ofertantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertantesRepository extends JpaRepository<Ofertantes, Long> {
}
