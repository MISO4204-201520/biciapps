package models.business;

import utils.Mail;
import utils.Utilities;
import models.dao.MongoManager;
import org.bson.types.ObjectId;
import models.entities.Recorrido;
import models.entities.Viaje;
import org.jongo.MongoCollection;
import models.entities.User;

/**
 * Created by Ger on 16/10/2015.
 */
public class ViajeBusiness {

    public static MongoCollection viajes() {
        return MongoManager.jongo.getCollection("viajes");
    }

    public static void insert(Viaje viaje) {        
        viajes().save(viaje);
    }

    public static void remove(ObjectId id) {
        viajes().remove(id);
    }

    public static Iterable<Viaje> findByUser(User user) {
        Iterable<Viaje> viajes = viajes().find("{recorrido.creador.email: #}", user.getEmail()).as(Viaje.class);
        return viajes;
    }
    public static Iterable<Viaje> findAll() {
        Iterable<Viaje> viajes = viajes().find().as(Viaje.class);
        return viajes;
    }

}
