package cl.rifeco.equipamiento.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipamiento {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private int cantidad;

    //Constructor
    public Equipamiento(){} 

    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public int getCantidad(){
        return cantidad;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
}