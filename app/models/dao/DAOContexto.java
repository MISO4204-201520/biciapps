package models.dao;


import java.util.ArrayList;
import java.util.List;

import org.jongo.MongoCollection;

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
	
//	public static PromocionContexto guardarUsuario(PromocionContexto usuario){
//		usuarios.save(usuario);
//		return usuario;
//	}
	
	public static List<PromocionContexto> getPromocionesUsuario(InfoUsuario infoUsuario) {
		
		List<PromocionContexto> promociones = new ArrayList<>();
		
		PromocionContexto p1 = new PromocionContexto();
		p1.setTitulo("titulo1");
    	p1.setDescripcion("descripcion1");
    	p1.setId("id");
    	p1.setTienda("Tienda1");
    	p1.setTiendaId("idtienda1");
    	p1.setLatitud(11);
    	p1.setLongitud(21);
    	promociones.add(p1);
    	
    	PromocionContexto p2 = new PromocionContexto();
		p2.setTitulo("titulo2");
    	p2.setDescripcion("descripcion2");
    	p2.setId("id2");
    	p2.setTienda("Tienda2");
    	p2.setTiendaId("idtienda2");
    	p2.setLatitud(12);
    	p2.setLongitud(22);
    	promociones.add(p2);
        
        return promociones;
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
