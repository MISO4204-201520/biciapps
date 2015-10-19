package models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.List;


/**
 * Created by ger on 16/10/15.
 */
public class Recorrido {

    @JsonProperty("_id")
    public ObjectId id;

    public String nombre;

    public String fecha;

    public User creador;

    public String distancia;

    public String tiempo;

    public List<User> usuarios;

    public Ruta ruta;

}
