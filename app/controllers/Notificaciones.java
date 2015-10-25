package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import notificaciones.INotificationManager;
import models.dao.DAOContexto;
import models.dao.DAOContexto.QueryParametersPromocion;
import models.entities.PromocionContexto;
import models.form.NotificacionV;
import models.form.PromocionV;

import com.fasterxml.jackson.databind.JsonNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Notificaciones extends Controller{

	private INotificationManager notificationManager;
	
	@Inject 
	public Notificaciones( INotificationManager notificationManager) {
		super();
		this.notificationManager = notificationManager;
	}

	public Result darNotificaciones() {
		
		Logger.debug(notificationManager.test());
		
        List<NotificacionV> notificacionesV = notificationManager.getNotificacione();
        return ok(Json.toJson(notificacionesV));
        
  }
}
