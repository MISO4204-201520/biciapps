package notificaciones;

import java.util.ArrayList;
import java.util.List;

import models.form.NotificacionV;

public class NotificationManager implements INotificationManager{

	@Override
	public boolean isEnabled() {
		return true;
	}

	
	@Override
	public void sendNotification(String userId, NotificacionV notification, String toUserId) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<NotificacionV> getNotifications(String userId) {
		List<NotificacionV> notificaciones = new ArrayList<NotificacionV>();
		return notificaciones;
	}
	
	@Override
	public void markNotificationAsRead(String userId, String notificationId) {
		// TODO Auto-generated method stub
		
	}

	
}
