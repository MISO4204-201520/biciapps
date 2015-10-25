package notificaciones;

import java.util.List;

import models.form.NotificacionV;

import com.google.inject.ImplementedBy;

//@ImplementedBy(NotificationManagerMock.class)
//@ImplementedBy(NotificationManager.class)
public interface INotificationManager {
	
	public String test();
	
	public List<NotificacionV> getNotificacione();

}
