package models.business;

import models.dao.MongoManager;

import org.bson.types.ObjectId;

import models.entities.Recorrido;
import models.entities.Ruta;
import models.entities.User;
import models.entities.Viaje;
import models.form.reports.ReporteHistorialViajeV;
import models.form.reports.ReporteRutaV;

import org.jongo.MongoCollection;

import play.Play;
import static org.jongo.Oid.withOid;
/**
 * Created by Fer Y german on 16/10/2015.
 */
public class RutaBusiness {

    public static MongoCollection rutas() {
        return MongoManager.jongo.getCollection("rutas");
    }

    public static void insert(Ruta ruta) {

        rutas().save(ruta);
    }

    public static void remove(ObjectId id) {
        rutas().remove(id);
    }

    public static Iterable<Ruta> findByUser(User user) {
        Iterable<Ruta> rutas = rutas().find("{usuarios.email: #}", user.getEmail()).as(Ruta.class);
        return rutas;
    }

    public static  Iterable<Ruta> findAll() {
        Iterable<Ruta> rutas = rutas().find().as(Ruta.class);
        return rutas;
    }
    public static Ruta findById(ObjectId id) {
        return rutas().findOne(id).as(Ruta.class);
    }
    
    
    //Reporte
    private static final int MAX_RUTAS = 5;
    
    public static ReporteRutaV getReporteRuta(String userEmail){
    	boolean enabled = Play.application().configuration()
						.getString("reportes.rutas") != null &&
					 Play.application().configuration()
					 	.getString("reportes.rutas").equals("TRUE");
		if(!enabled){
			return null;
		}
    	
    	Iterable<Ruta> iter = rutas().find("{usuarios.email: #}", userEmail)
    			.as(Ruta.class);
    	ReporteRutaV reporte = new ReporteRutaV();
    	
    	int numeroDeRutasGuardadas = 0;
    	
    	for(Ruta v: iter){
    		numeroDeRutasGuardadas++;
    		String nombreR = v.nombreOrigen + " -> " + v.nombreDestino;
    		if(reporte.getRutas().size() < MAX_RUTAS){
    			reporte.getRutas().add(nombreR);
    		}
    		
    		System.out.println(nombreR);
    	}
    	reporte.setNumeroDeRutasGuardadas(numeroDeRutasGuardadas);
    	
    	return reporte;
    }

}