package integrado.proyectotfg.controller;

import integrado.proyectotfg.model.Consumidores;
import integrado.proyectotfg.model.Ofertantes;
import integrado.proyectotfg.model.Usuario;
import integrado.proyectotfg.services.ConsumidoresServicesImpl;
import integrado.proyectotfg.services.OfertantesServicesImpl;
import integrado.proyectotfg.services.UsuarioServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    @Autowired
    UsuarioServicesImpl usuarioServices;

    @Autowired
    ConsumidoresServicesImpl consumidoresServices;

    @Autowired
    OfertantesServicesImpl ofertantesServices;

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> registrarUsuarioYConsumidor(
            @RequestBody Usuario usuarioSave
    ){
        usuarioSave.setRole("CONSUMER");

        // Guardar el usuario en la base de datos
        Usuario usuarioGuardado = usuarioServices.guardarUsuario(usuarioSave);

        // Crear el consumidor asociado al usuario
        Consumidores consumidor = new Consumidores();
        consumidor.setNombre(usuarioSave.getNombre());
        consumidor.setApellidos(usuarioSave.getApellidos());
        consumidor.setNif(usuarioSave.getNif()); // Suponiendo que el Consumidor también tiene estos campos
        consumidor.setTelefono(usuarioSave.getTelefono());
        consumidor.setCorreo(usuarioSave.getEmail()); // O utilizar el correo del usuario
        consumidor.setDireccion(usuarioSave.getDireccion());
        consumidor.setUsuario(usuarioGuardado);
        consumidoresServices.guardarConsumidor(consumidor);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }

    @PostMapping("/usuarios/convertir-ofertante")
    public ResponseEntity<String> convertirAOfertante(
            @RequestParam String email,
            @RequestParam String password
    ) {
        Usuario usuario = usuarioServices.obtenerUsuarioPorEmail(email);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }

        if (!usuario.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        // Verificar si ya existe un ofertante asociado a este usuario
        Ofertantes ofertante = ofertantesServices.obtenerOfertantePorUsuario(usuario);
        if (ofertante == null) {
            ofertante = new Ofertantes();
            ofertante.setUsuario(usuario);
            ofertante.setNombre(usuario.getNombre());
            ofertante.setApellidos(usuario.getApellidos());
            ofertante.setNif(usuario.getNif()); // Suponiendo que el Consumidor también tiene estos campos
            ofertante.setTelefono(usuario.getTelefono());
            ofertante.setCorreo(usuario.getEmail()); // O utilizar el correo del usuario
            ofertante.setDireccion(usuario.getDireccion());

            ofertantesServices.guardarOfertante(ofertante);

            return ResponseEntity.ok("Rol cambiado exitosamente a ofertante y se ha creado un nuevo ofertante asociado");
        }

        // Cambiar el rol del usuario a ofertante
        usuario.setRole("PROVIDER");
        usuarioServices.guardarUsuario(usuario);

        // Retornar una respuesta exitosa
        return ResponseEntity.ok("Rol cambiado exitosamente a ofertante");
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioServices.obtenerUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        Consumidores consumidor = consumidoresServices.obtenerConsumidorPorUsuario(usuario);
        if (consumidor != null) {
            consumidoresServices.eliminarConsumidor(consumidor.getId());
        }

        Ofertantes ofertante = ofertantesServices.obtenerOfertantePorUsuario(usuario);
        if (ofertante != null) {
            ofertantesServices.eliminarOfertante(ofertante.getId());
        }

        // Eliminar al usuario
        usuarioServices.eliminarUsuario(id);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
