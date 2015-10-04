package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import uk.co.panaxiom.playjongo.PlayJongo;

/**
 * Created by l on 27/09/15.
 */
public class User {
    public static MongoCollection users() {
        return PlayJongo.getCollection("users");
    }

    @JsonProperty("_id")
    public ObjectId id;

    public String email;

    public String pwd;

    public void insert() {
        users().save(this);
    }

    public void remove() {
        users().remove(this.id);
    }

    public static User findByEmail(String email) {
        return users().findOne("{email: #}", email).as(User.class);
    }

    public static User findByEmailAndPwd(String email, String pwd) {
        return users().findOne("{email: #, pwd: #}", email, pwd).as(User.class);
    }

}
