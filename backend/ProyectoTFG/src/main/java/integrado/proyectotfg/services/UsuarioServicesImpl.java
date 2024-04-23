package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Usuario;
import integrado.proyectotfg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicesImpl implements UsuarioServices {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistenteOptional = usuarioRepository.findByUserName(usuario.getUserName());
        if (usuarioExistenteOptional.isPresent()) {
            throw new IllegalArgumentException("Ya existe ese usuario.");
        }
        return usuarioRepository.save(usuario);
    }


    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        // Verificar si el usuario existe en la base de datos
        Optional<Usuario> usuarioExistenteOptional = usuarioRepository.findByUserName(usuario.getUserName());
        if (usuarioExistenteOptional.isEmpty()) {
            throw new IllegalArgumentException("El usuario no existe.");
        }
        Usuario usuarioExistente = usuarioExistenteOptional.get();
        usuarioExistente.setRole(usuario.getRole());
        // Guardar los cambios en el usuario
        return usuarioRepository.save(usuarioExistente);
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

    @Override
    public Usuario obtenerUsuarioPorUsername(String userName) {
        return usuarioRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("No se encontró un usuario con el nombre de usuario: " + userName));
    }
}
