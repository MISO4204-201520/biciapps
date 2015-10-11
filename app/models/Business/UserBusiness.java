package models.Business;

import Utils.Utilities;
import models.dao.MongoManager;
import org.bson.types.ObjectId;
import models.entities.User;
import org.jongo.MongoCollection;

/**
 * Created by Omar on 10/10/2015.
 */
public class UserBusiness {

    public static MongoCollection users() {
        //return PlayJongo.getCollection("users");
        return MongoManager.jongo.getCollection("users");
    }

    public static void insert(User user) {
        user.pwd = Utilities.encryptPass(user.pwd);
        users().save(user);
    }

    public static void update(User user) {
        User userDb = users().findOne("{email: #}", user.email).as(User.class);
        if (user.token != null){
            userDb.token = user.token;
        }
        if (user.name != null){
            userDb.name = user.name;
        }
        if (user.fbid != null){
            userDb.fbid = user.fbid;
        }
        users().update("{email: '#'}", user.email).with(userDb);
    }

    public static void remove(ObjectId id) {
        users().remove(id);
    }

    public static User findByFBId(String fbid) {
        return users().findOne("{fbid: #}", fbid).as(User.class);
    }

    public static User findByEmail(String email) {
        return users().findOne("{email: #}", email).as(User.class);
    }

    public static User findByEmailAndPwd(String email, String pwd) {
        pwd = Utilities.encryptPass(pwd);
        return users().findOne("{email: #, pwd: #}", email, pwd).as(User.class);
    }

    public static  Iterable<User> findAll() {
        Iterable<User> usuarios = users().find().as(User.class);
        return usuarios;
    }

}
