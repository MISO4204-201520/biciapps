package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constantes.ConstantesEstadoMensaje;
import models.business.ConfBicicletasBusiness;
import models.business.MensajesBusiness;
import models.business.UserBusiness;
import models.entities.ConfBicicleta;
import models.entities.Tienda;
import models.entities.User;
import models.entities.Mensaje;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Mail;

/**
 * Created by Jhon Gutierrez
 * Modulo Notificaciones
 */
public class MensajesController extends Controller {
	private Mail mail;
	private User usuarioLogueado;
	private List<Mensaje> listaMensajes;
	
	public Result inicializar(){		
   	 	usuarioLogueado = UserBusiness.findByEmail(session(MySecureAuth.SESSION_ID));
   	 	
   	 	if (usuarioLogueado != null){
   	 		listaMensajes = new ArrayList<Mensaje>();
   	 		Iterable<Mensaje> iMensajes = MensajesBusiness.findByDestinatarioEstado(
   	   	 			usuarioLogueado.email, ConstantesEstadoMensaje.SIN_LEER);

	   	    if(iMensajes != null) {
	   	      for(Mensaje mensaje: iMensajes) {
	   	    	listaMensajes.add(mensaje);
	   	      }
	   	    }	   	    
   	 	}
		return ok(views.html.mensajesPage.render(""));
	}
	
    public Result enviarMail() {
    	
        DynamicForm df = Form.form().bindFromRequest();
        String salida ="";
    
        if (validarMensaje()){
        	 Mensaje mensaje = new Mensaje();
    
        	 mensaje.setUserFrom(usuarioLogueado);
        	 mensaje.setDestinatario(df.get("destinatario"));
        	 mensaje.setFecha(new Date());
        	 mensaje.setAsunto(df.get("asunto"));
        	 mensaje.setContenido(df.get("mensaje"));
        	 mensaje.setEstado(ConstantesEstadoMensaje.SIN_LEER);      	
        	 
        	 MensajesBusiness.insert(mensaje);
        	 
             String[] to = {mensaje.getDestinatario()};
             String subject = mensaje.getAsunto();
             String body = mensaje.getContenido();

             mail.sendMailUser(to, usuarioLogueado, subject, body);  
             salida = "Mensaje Enviado exitosamente";
        }else{
        	salida = "Error en envío del mensaje";
        }  
        return ok(salida);
    } 
    
    /**
     * TODO
     * @return
     */
    private boolean validarMensaje(){
    	return true;
    }
}
