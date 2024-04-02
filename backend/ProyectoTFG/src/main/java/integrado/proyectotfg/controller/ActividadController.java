package integrado.proyectotfg.controller;

import integrado.proyectotfg.model.Actividades;
import integrado.proyectotfg.services.ActividadesServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class ActividadController {
    @Autowired
    ActividadesServicesImpl actividadesServices;

    @GetMapping("/actividades")
    public List<Actividades> listarActividades(){
        return actividadesServices.listarActividades();
    }
    @GetMapping("/actividades/{tipo}")
    public ResponseEntity<List<Actividades>> listarActividadesPorTipo(@PathVariable String tipo){
        List<Actividades> listaFiltrada = actividadesServices.listarActividadesPorTipo(tipo);

        return ResponseEntity.ok(listaFiltrada);
    }
    @GetMapping("/actividades/buscar/{busqueda}")
    public ResponseEntity<List<Actividades>> buscarActividadesPorNombre(@PathVariable String busqueda){
        List<Actividades> listaFiltrada = actividadesServices.buscarActividadesPorNombre(busqueda);
        return ResponseEntity.ok(listaFiltrada);
    }
}
