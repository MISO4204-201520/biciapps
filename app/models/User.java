package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.dao.MongoManager;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

/**
 * Created by l on 27/09/15.
 */
public class User {
    public static MongoCollection users() {
        //return PlayJongo.getCollection("users");
        return MongoManager.jongo.getCollection("users");
    }

    @JsonProperty("_id")
    public ObjectId id;

    public String token;
    
    public String name;

    public String fbid;

    public String email;

    public String pwd;

    private String name;
    
    public String getName()
    {
         return name;
    }
    
    public void setName(String name)
    {
         this.name = name;
    }
    
    public void insert() {
        users().save(this);
    }

    public void update() {
        User user = users().findOne("{email: #}", email).as(User.class);
        if (this.token != null){
            user.token = this.token
        }
        if (this.name != null){
            user.name = this.name
        }
        if (this.fbid != null){
            user.fbid = this.fbid
        }
        users().update("{email: '#'}", this.email).with(user);
    }

    public void remove() {
        users().remove(this.id);
    }

    
    public static User findByFBId(String fbid) {
        return users().findOne("{fbid: #}", fbid).as(User.class);
    }
    public static User findByEmail(String email) {
        return users().findOne("{email: #}", email).as(User.class);
    }

    public static User findByEmailAndPwd(String email, String pwd) {
        return users().findOne("{email: #, pwd: #}", email, pwd).as(User.class);
    }

    public static  Iterable<User> findAll() {
    	Iterable<User> usuarios = users().find().as(User.class);
    	return usuarios; 		
    }
}
