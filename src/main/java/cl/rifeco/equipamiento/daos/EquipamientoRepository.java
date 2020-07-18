package cl.rifeco.equipamiento.daos;
import cl.rifeco.equipamiento.models.Equipamiento;
import org.springframework.data.repository.CrudRepository;


public interface EquipamientoRepository extends CrudRepository<Equipamiento,Long> {

}