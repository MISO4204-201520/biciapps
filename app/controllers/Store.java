package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.dao.DAOContexto;
import models.dao.DAOContexto.QueryParametersPromocion;
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

    public Result listPromotionPage() {

		List<PromocionContexto> promociones = DAOContexto.getPromociones();
		List<PromocionV> promocionesV = toPromocionesV(promociones);

		System.out.println(promocionesV);
        return ok(views.html.store.listPromotionPage.render(promocionesV));
    }

    public Result contextTestPage() {
        return ok(views.html.store.contextTestPage.render());
    }

    public Result test() {
    	
    	DAOContexto.removeAll();
    	
    	PromocionContexto p1 = new PromocionContexto();
		p1.setTitulo("Promocion 1");
    	p1.setDescripcion("50% descuento en bicicletas");
    	p1.setTienda("Mario Laserna");
    	p1.setTiendaId("idtienda1");
    								//long, lat
    	p1.setPosicion(new double[] {4.603226,-74.065188});
    	DAOContexto.savePromocion(p1);
    	
    	PromocionContexto p2 = new PromocionContexto();
		p2.setTitulo("Promocion 2");
    	p2.setDescripcion("Compra una casco lleva el otro gratis");
    	p2.setTienda("Bloque B");
    	p2.setTiendaId("idtienda2");
    							//long, lat
    	p2.setPosicion(new double[] {4.601452,-74.065772});
    	DAOContexto.savePromocion(p2);
        return ok("Test");
    }

//POST    
//    @BodyParser.Of(BodyParser.Json.class)
    public Result darPromociones() {
    	Map<String, String[]> queryString = request().queryString();
    	String jString = queryString.keySet().iterator().next();
    	
    	JsonNode json = play.libs.Json.parse(jString);
    	System.out.println("JSON" +json);
//    	JsonNode json = request().body().asJson(); //POST
    	System.out.println("Called: " + json);
    	QueryParametersPromocion infoUsuario = darInfoUsuarioDadoJson(json);
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
    public static QueryParametersPromocion darInfoUsuarioDadoJson(JsonNode json){

    	double longitud = json.findPath("longitud").asDouble();
    	double latitud = json.findPath("latitud").asDouble();
    	double maxDistancia;
    	if(json.has("maxDistancia")){
    		maxDistancia = json.findPath("maxDistancia").asDouble();
    	}
    	else{
    		maxDistancia = 1000;
    	}
    	QueryParametersPromocion usuario = new QueryParametersPromocion();
    	usuario.setLongitud(longitud);
    	usuario.setLatitud(latitud);
    	usuario.setMaxDistancia(maxDistancia);
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
        	p.setLatitud(promocion.getPosicion()[0]);
        	p.setLongitud(promocion.getPosicion()[1]);
        	promocionesV.add(p);
    	}
        return promocionesV;
    
    }

}
