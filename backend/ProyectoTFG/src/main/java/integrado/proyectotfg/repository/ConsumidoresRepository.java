package integrado.proyectotfg.repository;


import integrado.proyectotfg.model.Consumidores;
import integrado.proyectotfg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumidoresRepository extends JpaRepository<Consumidores, Long> {
    Consumidores findByNombreAndApellidos(String nombre, String apellidos);

    Consumidores findByUsuario(Usuario usuario);
}
