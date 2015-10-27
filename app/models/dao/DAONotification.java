package models.dao;

import java.util.ArrayList;
import java.util.List;

import models.entities.Notification;
import models.entities.PromocionContexto;

import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

public class DAONotification {

	private static final String NOTIFICACIONES = "Notificaciones";
	private static MongoCollection notificaciones = 
			MongoManager.jongo.getCollection(NOTIFICACIONES);
	
	public static void removeAll(){
		notificaciones.remove("{}");
	}
	
	public static void savePromocion(Notification notification){
		notificaciones.save(notification);
	}

	public static List<Notification> getPromociones(){
		List<Notification> res = new ArrayList<>();
		MongoCursor<Notification> cursor = notificaciones.find("{}").as(Notification.class);

		for(Notification pc: cursor){
			res.add(pc);
		}
		return res;
	}
}
