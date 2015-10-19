package models.business;

import utils.Mail;
import utils.Utilities;
import models.dao.MongoManager;
import org.bson.types.ObjectId;
import models.entities.Ruta;
import org.jongo.MongoCollection;

/**
 * Created by Omar on 10/10/2015.
 */
public class RutaBusiness {

    public static MongoCollection rutas() {
        return MongoManager.jongo.getCollection("rutas");
    }

    public static void insert(Ruta ruta) {
        
        rutas().save(ruta);
    }

    public static void remove(ObjectId id) {
        rutas().remove(id);
    }

    public static  Iterable<Ruta> findAll() {
        Iterable<Ruta> rutas = rutas().find().as(Ruta.class);
        return rutas;
    }

}
