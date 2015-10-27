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
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Notifications extends Controller{

	private INotificationManager notificationManager;
	
	@Inject 
	public Notifications( INotificationManager notificationManager) {
		super();
		this.notificationManager = notificationManager;
	}

	public Result isEnabled() {
		ObjectNode json = Json.newObject();
		json.put("enabled", notificationManager.isEnabled());
		return ok(json);
	}
	
	public Result createNotification() {
		boolean success = true;
		if(success){
			return ok();
		}
		else{
			return badRequest();
		}
	}
	
	public Result getNotifications() {

		Logger.debug("");

		List<NotificacionV> notificacionesV = notificationManager.getNotifications("");
		return ok(Json.toJson(notificacionesV));
	}
	
	public Result deleteNotification() {
		boolean success = true;
		if(success){
			return ok();
		}
		else{
			return noContent();
		}
	}
}
