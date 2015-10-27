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
import models.form.NotificationV;
import models.form.PromocionV;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
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
	
	@BodyParser.Of(BodyParser.Json.class)
	public Result createNotification() {
		
		JsonNode json = request().body().asJson();
		NotificationV notification = jsonToNotificationV(json);
		Logger.debug(json.toString());
		
		boolean success = true;
		if(success){
			return ok();
		}
		else{
			return badRequest();
		}
	}
	
	public Result getNotifications() {
		
		List<NotificationV> notificacionesV = notificationManager.getNotifications("");
		return ok(Json.toJson(notificacionesV));
	}
	
	public Result deleteNotification(String id) {
		Logger.debug(id);
		boolean success = true;
		if(success){
			return ok();
		}
		else{
			return noContent();
		}
	}
	
	public static NotificationV jsonToNotificationV(JsonNode json){
		String message = json.findPath("message").textValue();
		String topic = json.findPath("topic").textValue();
		String toUserId = json.findPath("toUserId").textValue();
		
		NotificationV notification = new NotificationV();
		notification.setMessage(message);
		notification.setTopic(topic);
		
		return notification;
	}
}
