package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Usuario;
import integrado.proyectotfg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicesImpl implements UsuarioServices {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardarUsuario(Usuario usario) {
        Usuario usuarioLocal = usuarioRepository.findByUserName(usario.getUserName());
        if (usuarioLocal != null) {
            throw new IllegalArgumentException("Ya existe ese usuario.");
        }
        return usuarioRepository.save(usario);
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No se encontró un usuario con dicho email: " + email));
    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuarioDelete = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un usuario con el ID: " + id));
        usuarioRepository.delete(usuarioDelete);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró un usuario con el ID: " + id));
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
}
