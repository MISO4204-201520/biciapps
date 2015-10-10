package models.entities;

import Utils.Utilities;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.dao.MongoManager;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

/**
 * Created by l on 27/09/15.
 */
public class User {


    @JsonProperty("_id")
    public ObjectId id;

    public String token;
    
    public String name;

    public String fbid;

    public String email;

    public String pwd;

}
