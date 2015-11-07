package models.business;

import com.feth.play.module.pa.providers.oauth1.twitter.TwitterAuthUser;
import com.feth.play.module.pa.providers.oauth2.facebook.FacebookAuthUser;
import com.feth.play.module.pa.user.AuthUser;
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
        if(user.getEmail() != null && user.getEmail().contains("@")) {
            String[] to = {user.getEmail()};
            String subject = "Usuario Registrado Correctamente";
            String body = "Bienvenido " + user.getEmail().substring(0, user.getEmail().indexOf("@")) + " al sistema biciapps.";

            Mail.sendMailAdmin(to, subject, body);
        }
    }

    public static void update(User user) {
        User userDb = users().findOne("{email: #}", user.getEmail()).as(User.class);
        if (user.getToken() != null) {
            userDb.setToken(user.getToken());
        }

        if (user.getNombres() != null) {
            userDb.setNombres(user.getNombres());
        }

        if (user.getFbid() != null) {
            userDb.setFbid(user.getFbid());
        }

        if (user.getApellidos() != null) {
            userDb.setApellidos(user.getApellidos());
        }

        if (user.getSexo() != null) {
            userDb.setSexo(user.getSexo());
        }

        if (user.getPwd() != null) {
            //userDb.pwd = Utilities.encryptPass(user.pwd);
            userDb.setPwd((user.getPwd()));
        }

        if (user.getAmigos() != null) {
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

    public static Iterable<User> findAll() {
        Iterable<User> usuarios = users().find().as(User.class);
        return usuarios;
    }

    public static boolean existsByAuthUserIdentity(AuthUser authUser) {
        User usuarioBd = null;
        String provider = authUser.getProvider();
        String emailUsuario = "";
        if (authUser instanceof FacebookAuthUser) {
            emailUsuario = ((FacebookAuthUser) authUser).getEmail();
        } else if (authUser instanceof TwitterAuthUser) {
            emailUsuario = ((TwitterAuthUser) authUser).getScreenName();
        }

        usuarioBd = findByEmailAndProvider(emailUsuario, provider);
        return usuarioBd != null;
    }

    public static User createUserAuth(AuthUser authUser) {

        String nombres = "";
        String apellidos = "";
        String sexo = "";
        String emailUsuario = "";

        if (authUser instanceof FacebookAuthUser) {
            nombres = ((FacebookAuthUser) authUser).getFirstName();
            apellidos = ((FacebookAuthUser) authUser).getLastName();
            sexo = ((FacebookAuthUser) authUser).getGender();
            emailUsuario = ((FacebookAuthUser) authUser).getEmail();
        } else if (authUser instanceof TwitterAuthUser) {
            nombres = ((TwitterAuthUser) authUser).getName();
            apellidos = ((TwitterAuthUser) authUser).getScreenName();
            emailUsuario = ((TwitterAuthUser) authUser).getScreenName();
        }

        String proveedor = authUser.getProvider();
        String idSocial = authUser.getId();

        User usuario = new User();
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setSexo(sexo);
        usuario.setEmail(emailUsuario);
        usuario.setPwd(idSocial);
        usuario.setProveedor(proveedor);
        usuario.setIdSocial(idSocial);

        UserBusiness.insert(usuario);
        System.out.println("Se creo el usuario: " + emailUsuario);
        return usuario;
    }
    public static User findById(ObjectId id) {
        return users().findOne(id).as(User.class);
    }
    public static User findByIdSocialAndProvider(String idSocial, String provider) {
        //pwd = Utilities.encryptPass(pwd);
        return users().findOne("{idSocial: #, proveedor: #}", idSocial, provider).as(User.class);
    }

    public static User findByEmailAndProvider(String email, String provider) {
        return users().findOne("{email: #, proveedor: #}", email, provider).as(User.class);
    }
}
