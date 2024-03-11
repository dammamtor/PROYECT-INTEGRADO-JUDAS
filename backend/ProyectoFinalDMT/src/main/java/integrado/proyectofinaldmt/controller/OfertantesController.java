package integrado.proyectofinaldmt.controller;

import integrado.proyectofinaldmt.modelo.Ofertantes;
import integrado.proyectofinaldmt.repository.OfertantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class OfertantesController {
    @Autowired
    private OfertantesRepository repository;

    @GetMapping("/ofertantes")
    public List<Ofertantes> listarOfertantes() {
        return repository.findAll();
    }

    @PostMapping("/ofertantes")
    public Ofertantes guardarOfertante(@RequestBody Ofertantes ofertante) {
        return repository.save(ofertante);
    }

    @GetMapping("/ofertantes/{id}")
    public ResponseEntity<Ofertantes> obtenerOfertantePorId(@PathVariable Long id) {
        Ofertantes ofertanteOptional = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró un ofertante con el ID: " + id));
        return ResponseEntity.ok(ofertanteOptional);
    }

    @PutMapping("/ofertantes/{id}")
    public ResponseEntity<Ofertantes> actualizarOfertantePorId(@PathVariable Long id, @RequestBody Ofertantes detallesOf) {
        Ofertantes ofertanteOptional = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró un ofertante con el ID: " + id));

        ofertanteOptional.setNombre(detallesOf.getNombre());
        ofertanteOptional.setApellidos(detallesOf.getApellidos());
        ofertanteOptional.setNif(detallesOf.getNif());
        ofertanteOptional.setCorreo(detallesOf.getCorreo());
        ofertanteOptional.setTelefono(detallesOf.getTelefono());
        ofertanteOptional.setDireccion(detallesOf.getDireccion());

        Ofertantes ofertanteActualizado = repository.save(ofertanteOptional);

        return ResponseEntity.ok(ofertanteActualizado);
    }

    @DeleteMapping("/ofertantes/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarOfertante(@PathVariable Long id) {
        Ofertantes ofertante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un ofertante con el ID: " + id));

        repository.delete(ofertante);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
