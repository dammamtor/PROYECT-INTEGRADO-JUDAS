package integrado.proyectotfg.controller;

import integrado.proyectotfg.model.*;
import integrado.proyectotfg.model.DTO.ActividadesRequestDTO;
import integrado.proyectotfg.repository.TipoActividadRepository;
import integrado.proyectotfg.services.*;
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
public class OfertantesController {
    @Autowired
    private OfertantesServicesImpl ofertantesService;

    @Autowired
    private ActividadesServicesImpl actividadesService;

    @Autowired
    private TipoActividadRepository tipoActividadRepository;

    @Autowired
    SolicitudesActividadesServicesImpl solicitudesActividadesServices;
    @Autowired
    ReseñasServicesImpl reseñasServices;
    @Autowired
    UsuarioServicesImpl usuarioServices;


    @GetMapping("/ofertantes")
    public List<Ofertantes> listarOfertantes() {
        return ofertantesService.listarOfertantes();
    }

    @PostMapping("/ofertantes")
    public Ofertantes guardarOfertante(@RequestBody Ofertantes ofertante) {
        return ofertantesService.guardarOfertante(ofertante);
    }

    @GetMapping("/ofertantes/{id}")
    public ResponseEntity<Ofertantes> obtenerOfertantePorId(@PathVariable Long id) {
        Ofertantes ofertante = ofertantesService.obtenerOfertantePorId(id);
        return ResponseEntity.ok(ofertante);
    }

    @PutMapping("/ofertantes/{id}")
    public ResponseEntity<Ofertantes> actualizarOfertantePorId(@PathVariable Long id, @RequestBody Ofertantes detallesOf) {
        detallesOf.setId(id);
        Ofertantes ofertanteActualizado = ofertantesService.actualizarOfertante(detallesOf);
        return ResponseEntity.ok(ofertanteActualizado);
    }

    @DeleteMapping("/ofertantes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarOfertante(@PathVariable Long id) {
        ofertantesService.eliminarOfertante(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    //metodo para asignarle una actividad a un ofertante
    @PostMapping("/ofertantes/{ofertanteId}/actividades")
    public ResponseEntity<Actividades> crearActividadParaOfertante(
            @PathVariable Long ofertanteId,
            @RequestBody ActividadesRequestDTO actividadDTO) {
        Ofertantes ofertante = ofertantesService.obtenerOfertantePorId(ofertanteId);
        if (ofertante == null) {
            return ResponseEntity.notFound().build();
        }

        // Obtener el tipo de actividad seleccionado del DTO
        Long tipoActividadId = actividadDTO.getTipoActividadId();
        TipoActividad tipoActividad = tipoActividadRepository.findById(tipoActividadId).orElse(null);
        if (tipoActividad == null) {
            return ResponseEntity.notFound().build();
        }

        // Crear la actividad
        Actividades actividad = new Actividades();
        actividad.setNombre(actividadDTO.getNombre());
        actividad.setDescripcion(actividadDTO.getDescripcion());
        actividad.setFec_inicio(actividadDTO.getFec_inicio());
        actividad.setFec_final(actividadDTO.getFec_final());
        actividad.setPrecio(actividadDTO.getPrecio());
        actividad.setMateriales(actividadDTO.getMateriales());
        actividad.setOfertante(ofertante);
        actividad.setTipoActividad(tipoActividad);

        // Guardar la actividad
        Actividades actividadGuardada = actividadesService.guardarActividad(actividad);
        return ResponseEntity.ok(actividadGuardada);
    }

    @DeleteMapping("/ofertantes/{ofertanteId}/actividades/{actividadId}")
    public ResponseEntity<Map<String, Object>> eliminarActividadOfertante(@PathVariable Long ofertanteId, @PathVariable Long actividadId) {
        try {
            ofertantesService.eliminarActividadOfertante(ofertanteId, actividadId);
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("eliminado", Boolean.FALSE);
            respuesta.put("mensaje", "Error al eliminar la actividad: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    //METODOS SOBRE SOLICITUDES DEL LADO DEL OFERTANTE
    @GetMapping("/ofertantes/{ofertanteId}/solicitudes-actividades")
    public ResponseEntity<List<SolicitudesActividades>> obtenerSolicitudesActividadesPorOfertante(
            @PathVariable Long ofertanteId) {
        // Obtener todas las solicitudes de actividades para el ofertante por su ID
        List<SolicitudesActividades> solicitudesActividades = solicitudesActividadesServices.obtenerSolicitudesActividadesPorOfertante(ofertanteId);

        if (solicitudesActividades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(solicitudesActividades);
    }

    @PutMapping("/ofertantes/{ofertanteId}/solicitudes-actividades/{idSolicitud}")
    public ResponseEntity<Map<String, Boolean>> aceptarSolicitudPorId(
            @PathVariable Long ofertanteId,
            @PathVariable Long idSolicitud
    ) {
        //Obtener el ofertante (no vaya a ser que metas uno que no existe)
        Ofertantes ofertante1 = ofertantesService.obtenerOfertantePorId(ofertanteId);
        if (ofertante1 == null) {
            return ResponseEntity.notFound().build();
        }
        //Obtener la solicitud de actividad
        SolicitudesActividades solicitudPendiente = solicitudesActividadesServices.obtenerSolicitudActividadPorId(idSolicitud);
        if (solicitudPendiente == null) {
            return ResponseEntity.notFound().build();
        }

        //Validamos la solicitud
        solicitudesActividadesServices.validarSolicitudActividad(solicitudPendiente);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("aceptado", Boolean.TRUE);

        return ResponseEntity.ok(respuesta);

    }

    //METODOS RESEÑAS
    @GetMapping("/ofertantes/{ofertanteId}/opinion-actividades")
    public ResponseEntity<List<Reseñas>> obtenerReseñaPorIDofertantes(
            @PathVariable Long ofertanteId)
    {
        List<Reseñas> listaOpiniones = reseñasServices.listaReseñas(ofertanteId);
        if (listaOpiniones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listaOpiniones);
    }

    @GetMapping("/ofertantes/usuario/{user}")
    public ResponseEntity<Ofertantes> obtenerOfertantePorUsername(@PathVariable String user) {
        Usuario usuario = usuarioServices.obtenerUsuarioPorUsername(user);
        Ofertantes ofertante = ofertantesService.obtenerOfertantePorUsuario(usuario);
        return ResponseEntity.ok(ofertante);
    }
}
