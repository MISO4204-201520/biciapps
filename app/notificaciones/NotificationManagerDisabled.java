package notificaciones;

import java.util.ArrayList;
import java.util.List;

import models.form.NotificacionV;

public class NotificationManagerDisabled implements INotificationManager{

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void sendNotification(String userId, NotificacionV notification, String toUserId) {
		return;
	}
	
	@Override
	public List<NotificacionV> getNotifications(String userId) {
		List<NotificacionV> notificaciones = new ArrayList<NotificacionV>();
		return notificaciones;
	}

	@Override
	public void markNotificationAsRead(String userId, String notificationId) {
		return;
	}
}
