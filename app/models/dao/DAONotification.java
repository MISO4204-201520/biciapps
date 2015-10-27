package models.dao;

import java.util.ArrayList;
import java.util.List;

import models.entities.Notification;
import models.entities.PromocionContexto;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.jongo.Update;

import play.Logger;

import com.mongodb.WriteResult;

public class DAONotification {

	private static final String NOTIFICATION = "Notifications";
	private static MongoCollection notifications = 
			MongoManager.jongo.getCollection(NOTIFICATION);
	
	public static void removeAll(){
		notifications.remove("{}");
	}
	
	public static void save(Notification notification){
		notifications.save(notification);
	}

	public static List<Notification> getAll(){
		List<Notification> res = new ArrayList<>();
		MongoCursor<Notification> cursor = notifications.find("{}").as(Notification.class);

		for(Notification pc: cursor){
			res.add(pc);
		}
		return res;
	}
	
	public static List<Notification> getAllFromUser(String userId){
		List<Notification> res = new ArrayList<>();
		MongoCursor<Notification> cursor = notifications.find("{userId: #, deleted: #}", userId, false).as(Notification.class);
		for(Notification pc: cursor){
			res.add(pc);
		}
		return res;
	}	
	
	public static boolean markNotificationAsDeleted(String userId, String notificationId){
		Logger.info(userId + "," + notificationId);

		WriteResult update = notifications.update("{_id: #, userId: #}", new ObjectId(notificationId), userId)
				.with("{$set: {deleted: #}}", true);
		
		return (update.getN()!=0)? true:false;
	}
	
}
