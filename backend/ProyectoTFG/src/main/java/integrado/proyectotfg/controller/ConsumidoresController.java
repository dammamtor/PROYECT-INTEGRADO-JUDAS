package integrado.proyectotfg.controller;

import integrado.proyectotfg.model.Actividades;
import integrado.proyectotfg.model.Consumidores;
import integrado.proyectotfg.model.SolicitudesActividades;
import integrado.proyectotfg.services.ActividadesServicesImpl;
import integrado.proyectotfg.services.ConsumidoresServicesImpl;
import integrado.proyectotfg.services.SolicitudesActividadesServicesImpl;
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
public class ConsumidoresController {
    @Autowired
    ConsumidoresServicesImpl consumidoresServices;

    @Autowired
    SolicitudesActividadesServicesImpl solicitudesActividadesServices;

    @Autowired
    ActividadesServicesImpl actividadesServices;

    @GetMapping("/consumidores")
    public List<Consumidores> listarConsumidores() {
        return consumidoresServices.listarConsumidores();
    }

    @PostMapping("/consumidores")
    public Consumidores guardarConsumidor(@RequestBody Consumidores consumidor) {
        return consumidoresServices.guardarConsumidor(consumidor);
    }

    @GetMapping("/consumidores/{id}")
    public ResponseEntity<Consumidores> obtenerConsumidorPorId(@PathVariable Long id){
        Consumidores consumidor = consumidoresServices.obtenerConsumidorPorId(id);
        return ResponseEntity.ok(consumidor);
    }

    @PutMapping("/consumidores/{id}")
    public ResponseEntity<Consumidores> actualizarConsumidorPorId(
            @PathVariable Long id,
            @RequestBody Consumidores detallesConsumidor
    ) {
        detallesConsumidor.setId(id);
        Consumidores consumidorActualizado = consumidoresServices.actualizarConsumidor(detallesConsumidor);
        return ResponseEntity.ok(consumidorActualizado);
    }

    @DeleteMapping("/consumidores/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarConsumidor(@PathVariable Long id) {
        consumidoresServices.eliminarConsumidor(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/consumidores/{idConsumidor}/solicitudes-actividades")
    public ResponseEntity<List<SolicitudesActividades>> obtenerSolicitudesActividadesPorConsumidor(
            @PathVariable Long idConsumidor) {

        // Obtener todas las solicitudes de actividades del consumidor por su ID
        List<SolicitudesActividades> solicitudesActividades = solicitudesActividadesServices.obtenerSolicitudesActividadesPorConsumidor(idConsumidor);

        if (solicitudesActividades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(solicitudesActividades);
    }

    @PostMapping("/consumidores/{idConsumidor}/solicitudes-actividades/{idActividad}")
    public ResponseEntity<SolicitudesActividades> enviarSolicitudActividad(
            @PathVariable Long idConsumidor,
            @PathVariable Long idActividad) {

        // Obtener el consumidor por su ID
        Consumidores consumidor = consumidoresServices.obtenerConsumidorPorId(idConsumidor);
        if (consumidor == null) {
            return ResponseEntity.notFound().build();
        }

        // Obtener la actividad por su ID
        Actividades actividad = actividadesServices.obtenerActividadPorId(idActividad);
        if (actividad == null) {
            return ResponseEntity.notFound().build();
        }

        // Crear una nueva solicitud de actividad
        SolicitudesActividades solicitudActividad = new SolicitudesActividades();
        solicitudActividad.setActividad(actividad);
        solicitudActividad.setConsumidor(consumidor);

        // Guardar la solicitud de actividad en la base de datos
        SolicitudesActividades nuevaSolicitud = solicitudesActividadesServices.guardarSolicitudActividad(solicitudActividad);

        return ResponseEntity.ok(nuevaSolicitud);
    }
    @DeleteMapping("/consumidores/{idConsumidor}/solicitudes-actividades/{idSolicitud}")
    public ResponseEntity<Map<String, Boolean>> cancelarSolicitudActividad(
            @PathVariable Long idConsumidor,
            @PathVariable Long idSolicitud) {

        // Obtener la solicitud de actividad por su ID
        SolicitudesActividades solicitud = solicitudesActividadesServices.obtenerSolicitudActividadPorId(idSolicitud);
        if (solicitud == null) {
            return ResponseEntity.notFound().build();
        }

        // Verificar que la solicitud pertenece al consumidor
        if (!solicitud.getConsumidor().getId().equals(idConsumidor)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Eliminar la solicitud de actividad
        solicitudesActividadesServices.eliminarSolicitudActividad(idSolicitud);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("cancelado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
