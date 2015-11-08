package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        	
          	 Map<String, Object> map = leerAtributos(df);
        	
        	 ConfBicicleta confBicicleta = new ConfBicicleta();
             confBicicleta.setTienda(tiendaSeleccionada);   
             confBicicleta.setNombrePersonalizado(map.get("nombrePersonalizado").toString());
             confBicicleta.setTipoBicicleta(map.get("tipoBicicleta").toString());
             confBicicleta.setAleacion(map.get("aleacion").toString());
    
             confBicicleta.setRines(map.get("rines").toString());
             confBicicleta.setBielas(map.get("bielas").toString());
             confBicicleta.setTipoMarco(map.get("tipoMarco").toString());
             confBicicleta.setTenedor(map.get("tenedor").toString());
             
             confBicicleta.setTipoLlanta(map.get("tipoLlanta").toString());
             confBicicleta.setAccesorio1(map.get("luzDelantera").toString());
             confBicicleta.setAccesorio2(map.get("luzStop").toString());
             confBicicleta.setAccesorio3(map.get("guardaBarros").toString());
             
             if(ConfBicicletasBusiness.buscarPorNombrePersonalizado(
             		confBicicleta.getNombrePersonalizado()) == null){
             	ConfBicicletasBusiness.insert(confBicicleta);
             	resultado = "Registro insertado" + confBicicleta.toString();
             }else{
             	resultado = "Existe un registro con el mismo nombre" + confBicicleta.toString();
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
    
    /**
     * 
     * @param df
     * @return
     */
    private Map<String, Object> leerAtributos(DynamicForm df){ 	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	map.put("nombrePersonalizado",df.get("nombrePersonalizado"));
    	map.put("tipoBicicleta",df.get("tipoBicicleta"));
    	map.put("aleacion",df.get("aleacion"));
    	map.put("rines",df.get("rines"));
    	map.put("bielas",df.get("bielas"));
    	map.put("tipoMarco",df.get("tipoMarco"));
    	map.put("tenedor",df.get("tenedor"));
    	map.put("tipoLlanta",df.get("tipoLlanta"));
    	map.put("luzDelantera",df.get("accesorio1"));
    	map.put("luzStop",df.get("accesorio2"));
    	map.put("guardaBarros",df.get("accesorio3"));
    	
    	return map;  	
    }
}
