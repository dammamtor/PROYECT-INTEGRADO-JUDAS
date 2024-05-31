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
import java.util.List;
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

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioServices.obtenerTodosLosUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/usuarios/{username}")
    public ResponseEntity<Usuario> obtenerUsuarioPorUsername(@PathVariable String username) {
        Usuario usuario = usuarioServices.obtenerUsuarioPorUsername(username);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }


    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> registrarUsuarioYConsumidorYOfertante(
            @RequestBody Usuario usuarioSave
    ) {
        // Asignar el rol de "CONSUMER" al usuario
        usuarioSave.setRole("CONSUMER");

        // Guardar el usuario en la base de datos
        Usuario usuarioGuardado = usuarioServices.guardarUsuario(usuarioSave);

        // Crear el consumidor asociado al usuario
        Consumidores consumidor = new Consumidores();
        consumidor.setNombre(usuarioSave.getNombre());
        consumidor.setApellidos(usuarioSave.getApellidos());
        consumidor.setNif(usuarioSave.getNif());
        consumidor.setTelefono(usuarioSave.getTelefono());
        consumidor.setCorreo(usuarioSave.getEmail());
        consumidor.setDireccion(usuarioSave.getDireccion());

        // Establecer la relación bidireccional entre consumidor y usuario
        consumidor.setUsuario(usuarioGuardado);
        usuarioGuardado.setConsumidores(consumidor);

        // Guardar el consumidor en la base de datos
        consumidoresServices.guardarConsumidor(consumidor);

        // Crear el ofertante asociado al usuario
        Ofertantes ofertante = new Ofertantes();
        ofertante.setNombre(usuarioSave.getNombre());
        ofertante.setApellidos(usuarioSave.getApellidos());
        ofertante.setNif(usuarioSave.getNif());
        ofertante.setTelefono(usuarioSave.getTelefono());
        ofertante.setCorreo(usuarioSave.getEmail());
        ofertante.setDireccion(usuarioSave.getDireccion());

        // Establecer la relación bidireccional entre ofertante y usuario
        ofertante.setUsuario(usuarioGuardado);
        usuarioGuardado.setOfertante(ofertante);

        // Guardar el ofertante en la base de datos
        ofertantesServices.guardarOfertante(ofertante);

        usuarioServices.actualizarUsuario(usuarioGuardado);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }

    @PostMapping("/usuarios/iniciar-sesion")
    public ResponseEntity<String> iniciarSesion(
            @RequestParam String user,
            @RequestParam String password,
            @RequestParam String tipoSesion // Nuevo parámetro para determinar el tipo de sesión
    ) {
        Usuario usuario = usuarioServices.obtenerUsuarioPorUsername(user);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Usuario no encontrado\"}");
        }

        if (!usuario.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Contraseña incorrecta\"}");
        }

        if (tipoSesion.equals("1")) {
            usuario.setRole("CONSUMER");
            usuarioServices.actualizarUsuario(usuario);
            return ResponseEntity.ok().body("{\"mensaje\": \"Iniciaste sesión como Consumidor\"}");
        } else if (tipoSesion.equals("2")) {
            usuario.setRole("PROVIDER");
            usuarioServices.actualizarUsuario(usuario);
            return ResponseEntity.ok().body("{\"mensaje\": \"Iniciaste sesión como Ofertante\"}");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Tipo de sesión inválido\"}");
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioServices.obtenerUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        Consumidores consumidor = null;
        Ofertantes ofertante = null;
        try {
            // Manejar consumidor
            consumidor = consumidoresServices.obtenerConsumidorPorUsuario(usuario);
            if (consumidor != null) {
                consumidoresServices.eliminarConsumidor(consumidor.getId());
            }
        } catch (Exception e) {
        }

        try {
            // Manejar ofertante
            ofertante = ofertantesServices.obtenerOfertantePorUsuario(usuario);
            if (ofertante != null) {
                ofertantesServices.eliminarOfertante(ofertante.getId());
            }
        } catch (Exception e) {
        }

        // Eliminar al usuario
        usuarioServices.eliminarUsuario(id);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }


    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuarioUpdate
    ) {
        Usuario usuarioExistente = usuarioServices.obtenerUsuarioPorId(id);
        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // Buscar el consumidor asociado
            Consumidores consumidor = consumidoresServices.obtenerConsumidorPorUsuario(usuarioExistente);
            // Actualizar el consumidor si existe
            if (consumidor != null) {
                consumidor.setNombre(usuarioUpdate.getNombre());
                consumidor.setApellidos(usuarioUpdate.getApellidos());
                consumidor.setNif(usuarioUpdate.getNif());
                consumidor.setTelefono(usuarioUpdate.getTelefono());
                consumidor.setCorreo(usuarioUpdate.getEmail());
                consumidor.setDireccion(usuarioUpdate.getDireccion());

                consumidoresServices.actualizarConsumidor(consumidor);
            }
        } catch (RuntimeException e) {

        }

        try {
            // Buscar el ofertante asociado
            Ofertantes ofertante = ofertantesServices.obtenerOfertantePorUsuario(usuarioExistente);
            // Actualizar el ofertante si existe
            if (ofertante != null) {
                ofertante.setNombre(usuarioUpdate.getNombre());
                ofertante.setApellidos(usuarioUpdate.getApellidos());
                ofertante.setNif(usuarioUpdate.getNif());
                ofertante.setTelefono(usuarioUpdate.getTelefono());
                ofertante.setCorreo(usuarioUpdate.getEmail());
                ofertante.setDireccion(usuarioUpdate.getDireccion());

                ofertantesServices.actualizarOfertante(ofertante);
            }
        } catch (RuntimeException e) {

        }

        // Actualizar los campos del usuario existente con los nuevos valores
        usuarioExistente.setNombre(usuarioUpdate.getNombre());
        usuarioExistente.setApellidos(usuarioUpdate.getApellidos());
        usuarioExistente.setNif(usuarioUpdate.getNif());
        usuarioExistente.setTelefono(usuarioUpdate.getTelefono());
        usuarioExistente.setEmail(usuarioUpdate.getEmail());
        usuarioExistente.setDireccion(usuarioUpdate.getDireccion());

        // Guardar el usuario actualizado en la base de datos
        Usuario usuarioActualizado = usuarioServices.actualizarUsuario(usuarioExistente);

        return ResponseEntity.ok(usuarioActualizado);
    }

}
