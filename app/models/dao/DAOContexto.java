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
	
	static{
		promociones.ensureIndex("{posicion: '2dsphere'}");
	}
	
	public static void removeAll(){
		promociones.remove("{}");
	}
	
	public static void savePromocion(PromocionContexto promocion){
		promociones.save(promocion);
	}

	public static List<PromocionContexto> getPromociones(){
		List<PromocionContexto> res = new ArrayList<>();
		MongoCursor<PromocionContexto> cursor = promociones.find("{}").as(PromocionContexto.class);

		for(PromocionContexto pc: cursor){
			res.add(pc);
		}
		return res;
	}
	
	public static List<PromocionContexto> getPromocionesUsuario(QueryParametersPromocion infoUsuario) {
		
		List<PromocionContexto> res = new ArrayList<>();	
//		double longitud = -122.252696;
//		double latitud = 37.900933;
		
		double longitud = infoUsuario.getLongitud();
		double latitud = infoUsuario.getLatitud();
		double distanciaMaxima = infoUsuario.getMaxDistancia();
		System.out.println("["+longitud + "," + latitud + "]" + distanciaMaxima);
		
		MongoCursor<PromocionContexto> cursor = promociones.find(
				"{posicion: "+
						"{$near : {$geometry : {type: 'Point', " + "coordinates: [#, #] },"+
										" $maxDistance: # "+
								"}"+
						"}"+
				"}",longitud, latitud, distanciaMaxima
				).as(PromocionContexto.class);
		
		for(PromocionContexto pc: cursor){
			res.add(pc);
		}
		System.out.println("NUM promociones: " + res.size());
		return res;
    }

	
	
	public static class QueryParametersPromocion {
		private double latitud;
		private double longitud;
		private double maxDistancia;	//metros
		
		public QueryParametersPromocion(){
			
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

		public double getMaxDistancia() {
			return maxDistancia;
		}

		public void setMaxDistancia(double maxDistancia) {
			this.maxDistancia = maxDistancia;
		}
		
		
	}

}
