package controllers;

import java.util.ArrayList;
import java.util.List;

import models.dao.DAOContexto;
import models.dao.DAOContexto.InfoUsuario;
import models.entities.PromocionContexto;
import models.form.PromocionV;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.Play;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import scala.util.parsing.json.JSONObject$;


/**
 * Created by l on 9/10/15.
 */
public class Store extends Controller {

    public Result homePage() {
//        String url = routes.Store.homePage().toString();
        return ok(views.html.store.homePage.render());
    }

    public Result deleteStorePage() {
        return ok(views.html.store.deleteStorePage.render());
    }

    public Result contextTestPage() {
        return ok(views.html.store.contextTestPage.render());
    }

    public Result test() {
    	
    	DAOContexto.removeAll();
    	
    	PromocionContexto p1 = new PromocionContexto();
		p1.setTitulo("titulo1");
    	p1.setDescripcion("descripcion1");
    	p1.setTienda("Tienda1");
    	p1.setTiendaId("idtienda1");
    	p1.setLatitud(11);
    	p1.setLongitud(21);
    	DAOContexto.savePromocion(p1);
    	
    	PromocionContexto p2 = new PromocionContexto();
		p2.setTitulo("titulo2");
    	p2.setDescripcion("descripcion2");
    	p2.setTienda("Tienda2");
    	p2.setTiendaId("idtienda2");
    	p2.setLatitud(12);
    	p2.setLongitud(22);
    	DAOContexto.savePromocion(p2);
        return ok("Test");
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public Result darPromociones() {
    	JsonNode json = request().body().asJson();
    	System.out.println("Called: " + json);
    	InfoUsuario infoUsuario = darInfoUsuarioDadoJson(json);
        if(infoUsuario == null){
        	return badRequest("Invalid");        	
        }
    	
        List<PromocionContexto> promociones = DAOContexto.getPromocionesUsuario(infoUsuario);
        List<PromocionV> promocionesV = toPromocionesV(promociones);
        
        return ok(Json.toJson(promocionesV));
    }

    //-------------------------------
    //Helpers
    //------------------------------
    public static InfoUsuario darInfoUsuarioDadoJson(JsonNode json){

    	double latitud = json.findPath("latitud").asDouble();
    	double longitud = json.findPath("longitud").asDouble();
    	System.out.println(latitud + "," + longitud);
    	//      if(name == null) {
    	//          return badRequest("Missing parameter [name]");
    	//      } else {
    	//          return ok("Hello " + name);
    	//      }
    	InfoUsuario usuario = new InfoUsuario();
    	usuario.setLongitud(longitud);
    	usuario.setLatitud(latitud);
    	return usuario;
    }
    
    public List<PromocionV> toPromocionesV(List<PromocionContexto> promociones){
    	List<PromocionV> promocionesV = new ArrayList();
		
    	for(PromocionContexto promocion: promociones){
    		PromocionV p = new PromocionV();
        	p.setTitulo(promocion.getTitulo());
        	p.setDescripcion(promocion.getDescripcion());
        	p.setId(promocion.getId());
        	p.setTienda(promocion.getTienda());
        	p.setLatitud(promocion.getLatitud());
        	p.setLongitud(promocion.getLongitud());
        	promocionesV.add(p);
    	}
        return promocionesV;
    
    }

}
