package notificaciones;

import java.util.List;

import models.form.NotificationV;

import com.google.inject.ImplementedBy;

//@ImplementedBy(NotificationManagerMock.class)
//@ImplementedBy(NotificationManager.class)
public interface INotificationManager {
	
	public boolean isEnabled();
	
	public void sendNotification(NotificationV notification);
	
	public List<NotificationV> getNotifications(String userId);

	public void markNotificationAsRead(String userId, String notificationId);
	
}
