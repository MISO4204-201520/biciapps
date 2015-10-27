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
		NotificationV notification = jsonToNotificationV(request().body().asJson());
		if(notification == null){
			return badRequest();
		}
		notificationManager.sendNotification(notification);
		return ok();
	}
	
	public Result getNotifications() {
		List<NotificationV> notificacionesV = notificationManager.getNotifications("userId");
		return ok(Json.toJson(notificacionesV));
	}
	
	public Result deleteNotification(String id) {
		Logger.debug(id);
		boolean success = notificationManager.markNotificationAsRead("userId", id);
		if(success){
			return ok();
		}
		else{
			return noContent();
		}
	}
	
	public static NotificationV jsonToNotificationV(JsonNode json){
		String topic = json.findPath("topic").textValue();
		String message = json.findPath("message").textValue();
		String userId = json.findPath("userId").textValue();
		if(	topic == null || topic.equals("") ||
			message == null || message.equals("")||
			userId == null || userId.equals("")){
			return null;
		}
		
		NotificationV notification = new NotificationV();
		notification.setMessage(message);
		notification.setTopic(topic);
		notification.setUserId(userId);
		
		return notification;
	}
}
