package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constantes.ConstantesConfBicicleta;
import models.business.ConfBicicletasBusiness;
import models.entities.ConfBicicleta;
import models.entities.Tienda;
import play.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Mail;

/**
 * Created by Jhon Gutierrez
 * Modulo Configuracion de Bicicletas
 */
public class ConfBicicletasController extends Controller {
	public List<Tienda> listaTiendas;	
	
	public Result inicializar(){
		
    	String conf = Play.application().configuration().getString("configuracionBicicleta");
    	boolean enabled = (conf != null)? conf.equals("TRUE"): false;
    	if(!enabled){
    		return notFound("Module not enabled");
    	}
		
		listaTiendas  = new ArrayList<Tienda>();		
		return ok(views.html.confBicicletasPage.render(""));
	}
	
    public Result registrar() {

        DynamicForm df = Form.form().bindFromRequest();
        String resultado ="";
    	
        ConfBicicleta confBicicleta = leerConfiguracion(df);
        
        String[] to = {"biciapps@gmail.com"};
        String subject = confBicicleta.getNombrePersonalizado();
        String body = confBicicleta.toString();

        Mail.sendMailAdmin(to, subject, body);     
        resultado = "Configuración enviada correctamente " + confBicicleta.toString();
        
        return ok(resultado);
    } 
    
    /**
     * 
     * @param df
     * @return
     */
    private ConfBicicleta leerConfiguracion(DynamicForm df){ 	
        ConfBicicleta conf = new ConfBicicleta();

        Tienda tiendaSeleccionada = new Tienda();
        tiendaSeleccionada.setNombre(df.get("tienda").toString());
        
        conf.setTienda(tiendaSeleccionada);   
        conf.setNombrePersonalizado(df.get("nombrePersonalizado").toString());      
        conf.setTipoBicicleta(df.get("tipoBicicleta").toString());     
        conf.setAleacion(df.get("aleacion").toString());     
        conf.setRines(df.get("rines").toString());
        
        if (conf.getTipoBicicleta().equals(ConstantesConfBicicleta.MONTANA)){
            conf.setTipoMarco(df.get("tipoMarco").toString());
            conf.setTenedor(df.get("tenedor").toString());
            conf.setTipoLlanta(df.get("tipoLlanta").toString());
            conf.setBielas(df.get("bielas").toString());
        }
        else if (conf.getTipoBicicleta().equals(ConstantesConfBicicleta.RUTA)){
            conf.setTipoMarco(ConstantesConfBicicleta.RIGIDO);
            conf.setTenedor(ConstantesConfBicicleta.TENEDOR_RIGIDO);
            conf.setTipoLlanta(ConstantesConfBicicleta.PISTERA);
            
            if (df.get("bielas").equals(ConstantesConfBicicleta.DISCO3)){
            	conf.setBielas(ConstantesConfBicicleta.DISCO1);
            }
            else{
            	conf.setBielas(df.get("bielas").toString());
            }    
        }
        else if (conf.getTipoBicicleta().equals(ConstantesConfBicicleta.CROSS)){
            conf.setTipoMarco(ConstantesConfBicicleta.RIGIDO);
            conf.setTenedor(df.get("tenedor").toString());
            conf.setTipoLlanta(df.get("tipoLlanta").toString());
            conf.setBielas(ConstantesConfBicicleta.DISCO1);
        }

        conf.setAccesorio1(df.get("accesorio1"));
        conf.setAccesorio2(df.get("accesorio2"));
        conf.setAccesorio3(df.get("accesorio3"));
    	
    	return conf;  	
    }
}
