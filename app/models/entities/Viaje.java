package models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import models.entities.Recorrido;
import java.util.List;


/**
 * Created by ger on 16/10/15.
 */
public class Viaje {

    @JsonProperty("_id")
    public ObjectId id;
    
    public Recorrido recorrido;

    public String fecha;

    public String tiempo;

    public String distancia;
}
