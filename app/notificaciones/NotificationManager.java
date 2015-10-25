package notificaciones;

import java.util.ArrayList;
import java.util.List;

import models.form.NotificacionV;

public class NotificationManager implements INotificationManager{

	@Override
	public String test() {
		return "NotificationManager";
	}

	@Override
	public List<NotificacionV> getNotificacione() {
		List<NotificacionV> notificaciones = new ArrayList<NotificacionV>();
		return notificaciones;
	}

	
}
