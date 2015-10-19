package models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by User on 18/10/2015.
 */
public class Evento {

    @JsonProperty("_id")
    public ObjectId id;

    public String emailUser;

    public String nombre;

    public String fecha;

    public List<Amigo> amigos;

    public String latitudOrigen;

    public String longitudOrigen;

    public String nombreOrigen;

    public String latitudDestino;

    public String longitudDestino;

    public String nombreDestino;

    public String distancia;

    public String tiempo;

}
