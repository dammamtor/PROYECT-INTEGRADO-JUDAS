package integrado.proyectotfg.repository;

import integrado.proyectotfg.model.Ofertantes;
import integrado.proyectotfg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertantesRepository extends JpaRepository<Ofertantes, Long> {
    Ofertantes findByNombreAndApellidos(String nombre, String apellidos);

    Ofertantes findByUsuario(Usuario usuario);
}
