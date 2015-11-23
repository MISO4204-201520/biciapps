package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constantes.ConstantesEstadoMensaje;
import models.business.ConfBicicletasBusiness;
import models.business.MensajesBusiness;
import models.business.UserBusiness;
import models.entities.Amigo;
import models.entities.ConfBicicleta;
import models.entities.Tienda;
import models.entities.User;
import models.entities.Mensaje;
import play.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Mail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

/**
 * Created by Jhon Gutierrez
 * Modulo Notificaciones
 */
public class MensajesController extends Controller {
	private Mail mail;
	private User usuarioLogueado;
	private List<Mensaje> listaMensajes;
	private Integer cantMenNoLeidos = 0;
	
	public Result inicializar(){		
   	 	usuarioLogueado = UserBusiness.findByEmail(session(MySecureAuth.SESSION_ID));
   	 	cantMenNoLeidos = 0;
   	 	
   	 	if (usuarioLogueado != null){
   	 		listaMensajes = new ArrayList<Mensaje>();
   	 		Iterable<Mensaje> iMensajes = MensajesBusiness.findByDestinatarioEstado(
					usuarioLogueado.getEmail(), ConstantesEstadoMensaje.SIN_LEER);

	   	    if(iMensajes != null) {
	   	    	iMensajes.forEach(x-> listaMensajes.add(x));
	   	    	cantMenNoLeidos = listaMensajes.size();
	   	    }	   	    
   	 	} 	
		return ok(views.html.mensajesPage.render(cantMenNoLeidos));
	}
	
	public Result getListaMensajes(){	
		ObjectNode result = Json.newObject();
   	 	if (listaMensajes != null){
		   ArrayNode an = result.putArray("aaData");
		   for(Mensaje message : listaMensajes) {
		      ObjectNode row = Json.newObject();
		      row.put("0", message.getUserFrom().getEmail());
		      row.put("1", message.getAsunto());
		      row.put("2", message.getFecha());
		      row.put("3", message.getContenido());
		      an.add(row);
		   }   	    
   	 	}
   	 	return ok(result);
	}
	
    public Result enviarMail() {
    	
        DynamicForm df = Form.form().bindFromRequest();
        String salida ="";
    
        if (validarMensaje()){
        	 Mensaje mensaje = new Mensaje();
    
        	 mensaje.setUserFrom(usuarioLogueado);
        	 mensaje.setDestinatario(df.get("destinatario"));
        	 mensaje.setFecha(new Date().toString());
        	 mensaje.setAsunto(df.get("asunto"));
        	 mensaje.setContenido(df.get("mensaje"));
        	 mensaje.setEstado(ConstantesEstadoMensaje.SIN_LEER);      	
        	 
        	 MensajesBusiness.insert(mensaje);
        	 
         	 String conf = Play.application().configuration().getString("comunicacion.notificaciones");
        	 boolean enabled = (conf != null)? conf.equals("TRUE"): false;
        	 if(enabled){
        		 String[] to = {mensaje.getDestinatario()};
                 String subject = mensaje.getAsunto();
                 String body = mensaje.getContenido();
                
                //notificaciones=>
//                mail.sendMailUser(to, usuarioLogueado, subject, body); 
                return ok("Mensaje Enviado exitosamente y Notificado en Correo");
        	 }
             salida = "Mensaje Enviado exitosamente";
        }else{
        	salida = "Error en envío del mensaje";
        }  
        return ok(salida);
    } 
    
    public Result marcarLeido() {
        JsonNode json = request().body().asJson();
        String contacto = json.findPath("contacto").textValue();
        String fecha = json.findPath("fecha").textValue();
    	
	 	listaMensajes = new ArrayList<Mensaje>();
	 	Mensaje mensajeBD = MensajesBusiness.findByDestinatarioFecha(
	 			contacto, fecha);
	 	
	 	if (mensajeBD != null){
	 		mensajeBD.setEstado(ConstantesEstadoMensaje.LEIDO);
	 		MensajesBusiness.update(mensajeBD);
	 	}
	 	
	 	cantMenNoLeidos = 0;
 		Iterable<Mensaje> mensajesNoLeidos = MensajesBusiness.findByDestinatarioEstado(
				usuarioLogueado.getEmail(), ConstantesEstadoMensaje.SIN_LEER);
	 	
   	    if(mensajesNoLeidos != null) {
   	    	mensajesNoLeidos.forEach(x-> listaMensajes.add(x));
   	    	cantMenNoLeidos = listaMensajes.size();
   	    }

        return ok(views.html.mensajesPage.render(cantMenNoLeidos));
    }
    
    /**
     * TODO
     * @return
     */
    private boolean validarMensaje(){
    	return true;
    }
}
