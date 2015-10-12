package controllers;

import java.util.ArrayList;
import java.util.List;

import models.business.ConfBicicletasBusiness;
import models.entities.ConfBicicleta;
import models.entities.Tienda;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Jhon Gutierrez
 * Modulo Configuracin de Bicicletas
 */
public class ConfBicicletasController extends Controller {
	public Tienda tiendaSeleccionada;
	public List<Tienda> listaTiendas;
	
	
	public Result inicializar(){
		listaTiendas  = new ArrayList<Tienda>();
		
		//TODO
		Tienda tienda1 = new Tienda();
		tienda1.setNombre("Cannondale");
		
		Tienda tienda2 = new Tienda();
		tienda2.setNombre("Bike House Bogota");
		
		listaTiendas.add(tienda1);
		listaTiendas.add(tienda2);
		
		tiendaSeleccionada = tienda2;
		
		return ok(views.html.confBicicletasPage.render(""));
	}
	
    public Result registrar() {

        DynamicForm df = Form.form().bindFromRequest();
        String resultado ="";
    
        if (validarConfBicicleta()){
        	 ConfBicicleta confBicicleta = new ConfBicicleta();
             confBicicleta.setTienda(tiendaSeleccionada);
             confBicicleta.setNombrePersonalizado(df.get("nombrePersonalizado"));
             
             confBicicleta.setDescripcionConf(df.get("opciones"));
             
             if(ConfBicicletasBusiness.buscarPorNombrePersonalizado(
             		confBicicleta.getNombrePersonalizado()) == null){
             	ConfBicicletasBusiness.insert(confBicicleta);
             	resultado = "Registro insertado";
             }else{
             	resultado = "Existe un registro con el mismo nombre";
             }
        }else{
        	resultado = "Error validando datos obligatorios";
        }  
        return ok(resultado);
    } 
    
    /**
     * 
     * @return
     */
    private boolean validarConfBicicleta(){
    	//TODO
    	return true;
    } 
}
