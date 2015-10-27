package notificaciones;

import java.util.ArrayList;
import java.util.List;

import models.form.NotificationV;

public class NotificationManagerDisabled implements INotificationManager{

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void sendNotification(String userId, NotificationV notification, String toUserId) {
		return;
	}
	
	@Override
	public List<NotificationV> getNotifications(String userId) {
		List<NotificationV> notificaciones = new ArrayList<NotificationV>();
		return notificaciones;
	}

	@Override
	public void markNotificationAsRead(String userId, String notificationId) {
		return;
	}
}
