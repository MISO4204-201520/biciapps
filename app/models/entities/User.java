package models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

/**
 * Created by l on 27/09/15.
 */
public class User {

    @JsonProperty("_id")
    public ObjectId id;

    public String token;

    public String fbid;

    public String nombres;

    public String apellidos;

    public String sexo;

    public String email;

    public String pwd;

}
