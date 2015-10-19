package models.business;

import utils.Mail;
import utils.Utilities;
import models.dao.MongoManager;
import org.bson.types.ObjectId;
import models.entities.Recorrido;
import org.jongo.MongoCollection;
import models.entities.User;

/**
 * Created by Ger on 16/10/2015.
 */
public class RecorridoBusiness {

    public static MongoCollection recorridos() {
        return MongoManager.jongo.getCollection("recorridos");
    }

    public static void insert(Recorrido recorrido) {
        
        recorridos().save(recorrido);
    }

    public static void remove(ObjectId id) {
        recorridos().remove(id);
    }

    public static Iterable<Recorrido> findByUser(User user) {
        Iterable<Recorrido> recorridos = recorridos().find("{creador.email: #}", user.email).as(Recorrido.class);
        return recorridos;
    }
    public static Iterable<Recorrido> findAll() {
        Iterable<Recorrido> recorridos = recorridos().find().as(Recorrido.class);
        return recorridos;
    }

}
