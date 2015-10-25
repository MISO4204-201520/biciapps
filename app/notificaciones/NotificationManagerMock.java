package notificaciones;

import java.util.ArrayList;
import java.util.List;


import models.form.NotificacionV;

public class NotificationManagerMock implements INotificationManager{

	@Override
	public String test() {
		return "NotificationManagerMock";
	}

	@Override
	public List<NotificacionV> getNotificacione() {
		List<NotificacionV> notificaciones = new ArrayList<NotificacionV>();
		return notificaciones;
	}
}
