package cl.rifeco.equipamiento.controllers;

import cl.rifeco.equipamiento.models.Equipamiento;
import cl.rifeco.equipamiento.services.EquipamientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/equipamientos")
public class EquipamientoController {
    
    @Autowired
    @Qualifier("EquipamientoService")
    private EquipamientoService equipamientoService;

    @GetMapping("")
    public Iterable<Equipamiento> getEquipamientos(){
        return equipamientoService.listAllEquipamientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEquipamiento(@PathVariable Long id){
        Equipamiento equipamiento = equipamientoService.findEquipamientoById(id);
        if (equipamiento != null){
            return new ResponseEntity<Equipamiento>(equipamiento, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<Equipamiento> createEquipamiento(@RequestBody Equipamiento equipamiento){
        equipamientoService.saveEquipamiento(equipamiento);
        return new ResponseEntity<Equipamiento>(equipamiento, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEquipamiento(@PathVariable Long id, @RequestBody Equipamiento equipamiento){
        Equipamiento equipamiento_actual = equipamientoService.findEquipamientoById(id);
        if (equipamiento_actual != null){

            equipamiento_actual.setNombre(equipamiento.getNombre() != null ? equipamiento.getNombre() : equipamiento_actual.getNombre());
            equipamiento_actual.setTipo(equipamiento.getTipo() != null ? equipamiento.getTipo() : equipamiento_actual.getTipo());
            equipamiento_actual.setUbicacion(equipamiento.getUbicacion() != null ? equipamiento.getUbicacion() : equipamiento_actual.getUbicacion());
            equipamiento_actual.setEstado(equipamiento.getEstado() != null ? equipamiento.getEstado() : equipamiento_actual.getEstado());
            
            equipamientoService.saveEquipamiento(equipamiento_actual);
            
            return new ResponseEntity<Equipamiento>(equipamiento_actual, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquipamiento(@PathVariable Long id){
        Equipamiento equipamiento = equipamientoService.findEquipamientoById(id);
        if (equipamiento != null){
            equipamientoService.deleteEquipamiento(id);
            return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
        } else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}