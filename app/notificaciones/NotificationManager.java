package notificaciones;

import java.util.ArrayList;
import java.util.List;

import models.form.NotificationV;

public class NotificationManager implements INotificationManager{

	@Override
	public boolean isEnabled() {
		return true;
	}

	
	@Override
	public void sendNotification(String userId, NotificationV notification, String toUserId) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<NotificationV> getNotifications(String userId) {
		List<NotificationV> notificaciones = new ArrayList<NotificationV>();
		return notificaciones;
	}
	
	@Override
	public void markNotificationAsRead(String userId, String notificationId) {
		// TODO Auto-generated method stub
		
	}

	
}
