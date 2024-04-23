package integrado.proyectotfg.repository;

import integrado.proyectotfg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByUserName(String userName);

  Optional<Usuario> findByEmail(String email);

}
