package cl.rifeco.equipamiento.controllers;

import cl.rifeco.equipamiento.models.Equipamiento;
import cl.rifeco.equipamiento.services.EquipamientoService;
// import net.bytebuddy.implementation.bytecode.assign.Assigner.EqualTypesOnly;

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
// import org.springframework.web.bind.annotation.RequestParam;
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
        return new ResponseEntity<Equipamiento>(equipamiento,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEquipamiento(@PathVariable Long id, @RequestBody Equipamiento equipamiento){
        Equipamiento old_equipamiento = equipamientoService.findEquipamientoById(id);
        if (old_equipamiento != null){
            Equipamiento new_equipamiento = new Equipamiento();

            // Actualiza el nombre si es necesario
            if(equipamiento.getNombre() != null){
                new_equipamiento.setNombre(equipamiento.getNombre());
            } else {
                new_equipamiento.setNombre(old_equipamiento.getNombre());
            }

            new_equipamiento.setTipo(equipamiento.getTipo());
            new_equipamiento.setCantidad(equipamiento.getCantidad());
            new_equipamiento.setUbicacion(equipamiento.getUbicacion());
            new_equipamiento.setEstado(equipamiento.getEstado());
            equipamientoService.saveEquipamiento(equipamiento);

            equipamientoService.deleteEquipamiento(id);

            return new ResponseEntity<Equipamiento>(new_equipamiento, HttpStatus.OK);
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