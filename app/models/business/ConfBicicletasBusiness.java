package models.business;

import models.dao.MongoManager;
import org.bson.types.ObjectId;
import models.entities.ConfBicicleta;
import utils.Mail;

import org.jongo.MongoCollection;

/**
 * Created by Jhon Gutierrez
 * Modulo Configuración de Bicicletas
 */
public class ConfBicicletasBusiness {

    public static MongoCollection confBicicletas() {
        return MongoManager.jongo.getCollection("confBicicletas");
    }

    public static void insert(ConfBicicleta confBicicleta) {
    	confBicicletas().save(confBicicleta);
    }

    public static void remove(ObjectId id) {
    	confBicicletas().remove(id);
    }
    
    public static ConfBicicleta buscarPorNombrePersonalizado(String nombrePersonalizado) {
        return confBicicletas().findOne("{nombrePersonalizado: #}", nombrePersonalizado).as(ConfBicicleta.class);
    }
}
