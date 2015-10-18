package controllers;

import java.util.ArrayList;
import java.util.List;

import models.business.ConfBicicletasBusiness;
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
 * Modulo Configuracin de Bicicletas
 */
public class MensajesController extends Controller {
	
	public Result inicializar(){		
		return ok(views.html.mensajesPage.render(""));
	}
	
    public Result enviarMail() {

        DynamicForm df = Form.form().bindFromRequest();
        String resultado ="";
    
        if (validarMensaje()){
        	 Mensaje mensaje = new Mensaje();
        	 
        	 String emailUsuarioLogueado = session(MySecureAuth.SESSION_ID);
        	 User userdb = UserBusiness.findByEmail(emailUsuarioLogueado);
        	 
        	 mensaje.setRemitente(df.get("destinatario"));
        	 mensaje.setAsunto(df.get("asunto"));        	 
        	 mensaje.setContenidoTxt(df.get("mensaje"));
        	 
             String[] to = {mensaje.getRemitente()};
             String subject = mensaje.getAsunto();
             String body = mensaje.getContenidoTxt();

             Mail.sendMailUser(to, userdb, subject, body);      
        }else{
        	resultado = "Error validando datos obligatorios";
        }  
        return ok(resultado);
    } 
    
    /**
     * 
     * @return
     */
    private boolean validarMensaje(){
    	return true;
    }
}
