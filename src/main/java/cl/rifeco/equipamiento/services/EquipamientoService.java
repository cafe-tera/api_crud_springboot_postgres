package cl.rifeco.equipamiento.services;

import org.springframework.stereotype.Service;
import cl.rifeco.equipamiento.daos.EquipamientoRepository;
import cl.rifeco.equipamiento.models.Equipamiento;
import org.springframework.beans.factory.annotation.Autowired;

@Service("EquipamientoService")
public class EquipamientoService {
    @Autowired
    private EquipamientoRepository equipamientoRepository;

    public Equipamiento saveEquipamiento (Equipamiento equipamiento){
        return equipamientoRepository.save(equipamiento);
    }

    public Equipamiento findEquipamientoById(Long id){
        return equipamientoRepository.findById(id).orElse(null);
    }

    public Iterable<Equipamiento> listAllEquipamientos(){
        return equipamientoRepository.findAll();
    }
    
    public  void deleteEquipamiento(Long id){
        equipamientoRepository.deleteById(id);
        return;
    }    
}
