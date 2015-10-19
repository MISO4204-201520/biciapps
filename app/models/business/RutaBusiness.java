package models.business;

import utils.Mail;
import utils.Utilities;
import models.dao.MongoManager;
import org.bson.types.ObjectId;
import models.entities.Ruta;
import models.entities.Evento;
import org.jongo.MongoCollection;

/**
 * Created by Omar on 10/10/2015.
 */
public class RutaBusiness {

    public static MongoCollection rutas() {
        return MongoManager.jongo.getCollection("rutas");
    }

    public static MongoCollection eventos(){return MongoManager.jongo.getCollection("eventos");}

    public static void insertRuta(Ruta ruta) {
        
        rutas().save(ruta);
    }

    public static void insertEvento(Evento evento) {

        eventos().save(evento);
    }

    public static void removeRuta(ObjectId id) {
        rutas().remove(id);
    }

    public static void removeEvento(ObjectId id) {
        eventos().remove(id);
    }

    public static  Iterable<Ruta> findAllRutas() {
        Iterable<Ruta> rutas = rutas().find().as(Ruta.class);
        return rutas;
    }

    public static  Iterable<Evento> findAllEventos() {
        Iterable<Evento> eventos = eventos().find().as(Evento.class);
        return eventos;
    }

}
