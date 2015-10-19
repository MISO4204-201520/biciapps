package models.business;

import models.dao.MongoManager;

import org.bson.types.ObjectId;
import models.entities.Mensaje;
import models.entities.User;

import org.jongo.MongoCollection;

/**
 * Created by Jhon Gutierrez on 18/10/2015.
 */
public class MensajesBusiness {

    public static MongoCollection mensajes() {
        return MongoManager.jongo.getCollection("mensajes");
    }

    public static void insert(Mensaje mensaje) {
    	mensajes().save(mensaje);
    }

    public static void update(Mensaje mensaje) {
        Mensaje mensajeDb = findByDestinatarioFecha(mensaje.getUserFrom().email, mensaje.getFecha());
        
        mensajeDb.setEstado(mensaje.getEstado());
        mensajes().update("{userFrom.email: #, fecha: #}", mensaje.getUserFrom().email, mensaje.getFecha()).with(mensajeDb);
    }

    public static void remove(ObjectId id) {
    	mensajes().remove(id);
    }

    public static Mensaje findById(ObjectId id) {
        return mensajes().findOne("{id: #}", id).as(Mensaje.class);
    }
    
    public static Iterable<Mensaje> findByDestinatarioEstado(String destinatario,  Long idEstado) {
        Iterable<Mensaje> mensajes = mensajes().find("{destinatario: #, estado: #}", destinatario, idEstado).as(Mensaje.class);
        return mensajes;
    }
    
    public static Mensaje findByDestinatarioFecha(String remitente,  String fecha) {
    	return mensajes().findOne("{userFrom.email: #, fecha: #}", remitente, fecha).as(Mensaje.class);
    }
    
    public static Iterable<Mensaje> findAll() {
        Iterable<Mensaje> mensajes = mensajes().find().as(Mensaje.class);
        return mensajes;
    }
}
