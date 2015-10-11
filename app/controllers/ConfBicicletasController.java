package controllers;

import models.business.ConfBicicletasBusiness;
import models.entities.ConfBicicleta;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Jhon Gutierrez
 * Modulo Configuración de Bicicletas
 */
public class ConfBicicletasController extends Controller {
	
	public Result inicializar(){
		return ok(views.html.confBicicletasPage.render(""));
	}
	
    public Result registrar() {

        DynamicForm df = Form.form().bindFromRequest();
        String resultado ="";
        
        ConfBicicleta confBicicleta = new ConfBicicleta();
        confBicicleta.setTienda(df.get("tienda"));
        confBicicleta.setDescripcionConf(df.get("descripcion"));
        
        if(ConfBicicletasBusiness.buscarPorNombrePersonalizado(
        		confBicicleta.getNombrePersonalizado()) == null){
        	ConfBicicletasBusiness.insert(confBicicleta);
        	resultado = "Registro insertado";
        }else{
        	resultado = "Existe un registro con el mismo nombre";
        }
        
        return ok(resultado);
    }
}
