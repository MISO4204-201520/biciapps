package models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Omar on 18/10/2015.
 */
public class Amigo {

    @JsonProperty("_id")
    public ObjectId id;

    public String nickName;

    public String email;
}
