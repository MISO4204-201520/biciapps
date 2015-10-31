package models.business;

import utils.Mail;
import utils.Utilities;
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
        //user.pwd = Utilities.encryptPass(user.pwd);
        user.setPwd(user.getPwd());
        users().save(user);

        // TODO: Revisar si se puede hacer con AspectJ
        String[] to = {user.getEmail()};
        String subject = "Usuario Registrado Correctamente";
        String body = "Bienvenido " + user.getEmail().substring(0, user.getEmail().indexOf("@")) + " al sistema biciapps.";

        Mail.sendMailAdmin(to, subject, body);
    }

    public static void update(User user) {
        User userDb = users().findOne("{email: #}", user.getEmail()).as(User.class);
        if (user.getToken() != null){
            userDb.setToken(user.getToken());
        }

        if (user.getNombres() != null){
            userDb.setNombres(user.getNombres());
        }

        if (user.getFbid() != null){
            userDb.setFbid(user.getFbid());
        }

        if (user.getApellidos() != null){
            userDb.setApellidos(user.getApellidos());
        }

        if (user.getSexo() != null){
            userDb.setSexo(user.getSexo());
        }

        if (user.getPwd() != null){
            //userDb.pwd = Utilities.encryptPass(user.pwd);
            userDb.setPwd((user.getPwd()));
        }

        if (user.getAmigos() != null){
            //userDb.pwd = Utilities.encryptPass(user.pwd);
            userDb.setAmigos(user.getAmigos());
        }

        users().update("{email: #}", user.getEmail()).with(userDb);
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
        //pwd = Utilities.encryptPass(pwd);
        return users().findOne("{email: #, pwd: #}", email, pwd).as(User.class);
    }

    public static  Iterable<User> findAll() {
        Iterable<User> usuarios = users().find().as(User.class);
        return usuarios;
    }

}
