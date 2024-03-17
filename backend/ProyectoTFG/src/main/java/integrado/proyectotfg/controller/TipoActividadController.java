package integrado.proyectotfg.controller;

import integrado.proyectotfg.model.TipoActividad;
import integrado.proyectotfg.services.TipoActividadServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoActividadController {
    @Autowired
    TipoActividadServicesImpl tipoActividadServices;

    @GetMapping("/tipos")
    public List<TipoActividad> listarTipoActividades() {
        return tipoActividadServices.listarTipoActividades();
    }

    @PostMapping("/tipos")
    public TipoActividad guardarTipoActividad(@RequestBody TipoActividad tipoActividad) {
        return tipoActividadServices.guardarTipoActividad(tipoActividad);
    }

    @GetMapping("/tipos/{id}")
    public ResponseEntity<TipoActividad> obtenerTipoPorId(@PathVariable Long id) {
        TipoActividad tipoActividad = tipoActividadServices.obtenerTipoPorId(id);
        return ResponseEntity.ok(tipoActividad);
    }
    @PutMapping("/tipos/{id}")
    public ResponseEntity<TipoActividad> actualizarTipoActividad(@PathVariable Long id, @RequestBody TipoActividad tipoActividad){
        tipoActividad.setId(id);
        TipoActividad tipoActividadActualizado = tipoActividadServices.actualizarTipoActividad(tipoActividad);
        return ResponseEntity.ok(tipoActividadActualizado);
    }
    @DeleteMapping("/tipos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarTipoActividad(@PathVariable Long id) {
        tipoActividadServices.eliminarTipo(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
