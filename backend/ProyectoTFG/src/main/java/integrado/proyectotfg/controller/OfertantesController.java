package integrado.proyectotfg.controller;

import integrado.proyectotfg.model.Actividades;
import integrado.proyectotfg.model.DTO.ActividadesRequestDTO;
import integrado.proyectotfg.model.Ofertantes;
import integrado.proyectotfg.model.TipoActividad;
import integrado.proyectotfg.repository.TipoActividadRepository;
import integrado.proyectotfg.services.ActividadesServicesImpl;
import integrado.proyectotfg.services.OfertantesServicesImpl;
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
            @RequestBody ActividadesRequestDTO actividadDTO)
    {
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

}
