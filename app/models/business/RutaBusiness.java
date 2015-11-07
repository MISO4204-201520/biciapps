package models.business;

import models.dao.MongoManager;
import org.bson.types.ObjectId;
import models.entities.Ruta;
import models.entities.User;
import org.jongo.MongoCollection;
import static org.jongo.Oid.withOid;
/**
 * Created by Fer Y german on 16/10/2015.
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

    public static Iterable<Ruta> findByUser(User user) {
        Iterable<Ruta> rutas = rutas().find("{usuarios.email: #}", user.getEmail()).as(Ruta.class);
        return rutas;
    }

    public static  Iterable<Ruta> findAll() {
        Iterable<Ruta> rutas = rutas().find().as(Ruta.class);
        return rutas;
    }
    public static Ruta findById(ObjectId id) {
        return rutas().findOne(id).as(Ruta.class);
    }

}