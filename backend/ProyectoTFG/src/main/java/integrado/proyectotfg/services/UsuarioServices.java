package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Usuario;

import java.util.List;

public interface UsuarioServices {
    Usuario guardarUsuario (Usuario usario);

    Usuario obtenerUsuarioPorEmail(String email);

    void eliminarUsuario(Long id);

    Usuario obtenerUsuarioPorId(Long id);

    List<Usuario> obtenerTodosLosUsuarios();
}
