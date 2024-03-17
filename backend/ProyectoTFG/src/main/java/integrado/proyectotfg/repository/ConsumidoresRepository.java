package integrado.proyectotfg.repository;


import integrado.proyectotfg.model.Consumidores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumidoresRepository extends JpaRepository<Consumidores, Long> {
}
