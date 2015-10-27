package notificaciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import play.Logger;
import models.dao.DAONotification;
import models.entities.Notification;
import models.form.NotificationV;

public class NotificationManager implements INotificationManager{

	@Override
	public boolean isEnabled() {
		return true;
	}

	
	@Override
	public void sendNotification(NotificationV notification) {
		Notification entity = convertToEntity(notification);
		DAONotification.save(entity);
	}
	
	@Override
	public List<NotificationV> getNotifications(String userId) {
		List<Notification> eNotifications= DAONotification.getAllFromUser(userId);
		List<NotificationV> notifications = new ArrayList<NotificationV>();
		for(Notification e: eNotifications){
			NotificationV nv = convertToVisual(e);
			notifications.add(nv);
		}
		return notifications;
	}
	
	@Override
	public boolean markNotificationAsRead(String userId, String notificationId) {
		boolean res = DAONotification.markNotificationAsDeleted(userId, notificationId); 
		Logger.info("Delete: " + res);
		return res;
	}

	private static Notification convertToEntity(NotificationV notification){
		Notification n = new Notification();
		n.setTopic(notification.getTopic());
		n.setMessage(notification.getMessage());
		n.setUserId(notification.getUserId());
		n.setCreationDate(new Date());
		n.setDeleted(false);
		return n;
	}
	
	private static NotificationV convertToVisual(Notification notification){
		NotificationV n = new NotificationV();
		n.setId(notification.getId());
		n.setTopic(notification.getTopic());
		n.setMessage(notification.getMessage());
		n.setUserId(notification.getUserId());
		return n;
	}
	
}
