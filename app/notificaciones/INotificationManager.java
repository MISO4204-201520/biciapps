package notificaciones;

import java.util.List;

import models.form.NotificacionV;

import com.google.inject.ImplementedBy;

//@ImplementedBy(NotificationManagerMock.class)
//@ImplementedBy(NotificationManager.class)
public interface INotificationManager {
	
	public boolean isEnabled();
	
	public void sendNotification(String userId, NotificacionV notification, String toUserId);
	
	public List<NotificacionV> getNotifications(String userId);

	public void markNotificationAsRead(String userId, String notificationId);
	
}
