package integrado.proyectotfg.controller;

import integrado.proyectotfg.model.Consumidores;
import integrado.proyectotfg.services.ConsumidoresServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
}
