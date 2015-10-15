package models.dao;


import java.util.ArrayList;
import java.util.List;

import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.entities.PromocionContexto;
import models.form.PromocionV;

public class DAOContexto {
	private static final String PROMOCIONES_CONTEXTO = "PromocionesContexto";
	private static MongoCollection promociones = 
			MongoManager.jongo.getCollection(PROMOCIONES_CONTEXTO);
	
	public static void removeAll(){
		promociones.remove("{}");
	}
	
	public static void savePromocion(PromocionContexto promocion){
		promociones.save(promocion);
	}
	
	public static List<PromocionContexto> getPromocionesUsuario(InfoUsuario infoUsuario) {
		
		List<PromocionContexto> res = new ArrayList<>();
		
		MongoCursor<PromocionContexto> cursor = promociones.find("{}").as(PromocionContexto.class);
		
		for(PromocionContexto pc: cursor){
			res.add(pc);
		}
		return res;
    }

	
	
	public static class InfoUsuario {
		private double latitud;
		private double longitud;
		
		public InfoUsuario(){
			
		}

		public double getLatitud() {
			return latitud;
		}

		public void setLatitud(double latitud) {
			this.latitud = latitud;
		}

		public double getLongitud() {
			return longitud;
		}

		public void setLongitud(double longitud) {
			this.longitud = longitud;
		}
		
	}

}
