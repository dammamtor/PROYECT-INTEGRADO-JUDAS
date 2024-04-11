package integrado.proyectotfg.services;

import integrado.proyectotfg.model.Usuario;

public interface UsuarioServices {
    Usuario guardarUsuario (Usuario usario);

    Usuario obtenerUsuarioPorEmail(String email);

    void eliminarUsuario(Long id);

    Usuario obtenerUsuarioPorId(Long id);
}
