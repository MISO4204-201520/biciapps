package models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.List;


/**
 * Created by ger y mafe on 16/10/15.
 */
public class Ruta {

    @JsonProperty("_id")
    public ObjectId id;

    public String latitudOrigen;

    public String longitudOrigen;

    public String nombreOrigen;

    public String latitudDestino;

    public String longitudDestino;

    public String nombreDestino;

    public List<User> usuarios;

}
