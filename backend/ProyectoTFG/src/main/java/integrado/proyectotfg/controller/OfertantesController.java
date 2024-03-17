package integrado.proyectotfg.controller;

import integrado.proyectotfg.model.Actividades;
import integrado.proyectotfg.model.Ofertantes;
import integrado.proyectotfg.services.ActividadesServicesImpl;
import integrado.proyectotfg.services.OfertantesServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Actividades> agregarActividadOfertante(
            @PathVariable Long ofertanteId,
            @RequestParam Long tipoActividadId,
            @RequestBody Actividades nuevaActividad
    ) {
        Ofertantes ofertante = ofertantesService.obtenerOfertantePorId(ofertanteId);

        return ResponseEntity.ok(actividadesService.crearActividadConTipo(ofertante, nuevaActividad, tipoActividadId));
    }
}
