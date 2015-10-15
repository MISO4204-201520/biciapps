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
