package models.business;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.EnvHelper;
import utils.Mail;
import utils.Utilities;
import models.dao.MongoManager;

import org.bson.types.ObjectId;

import models.entities.Recorrido;
import models.entities.Viaje;

import org.jongo.MongoCollection;

import models.entities.User;
import models.form.reports.ReporteHistorialViajeV;
import models.form.reports.ReporteMetricasV;

/**
 * Created by Ger on 16/10/2015.
 */
public class ViajeBusiness {

    public static MongoCollection viajes() {
        return MongoManager.jongo.getCollection("viajes");
    }

    public static void insert(Viaje viaje) {        
        viajes().save(viaje);
    }

    public static void remove(ObjectId id) {
        viajes().remove(id);
    }

    public static Iterable<Viaje> findByUser(User user) {
        Iterable<Viaje> viajes = viajes().find("{recorrido.creador.email: #}", user.getEmail()).as(Viaje.class);
        return viajes;
    }
    public static Iterable<Viaje> findAll() {
        Iterable<Viaje> viajes = viajes().find().as(Viaje.class);
        return viajes;
    }
    
    //Reportes
    public static ReporteMetricasV getReporteMetricas(String userEmail){
    	
    	boolean enabled = EnvHelper.reporteMetricasEnabled();
    	if(!enabled){
    		return null;
    	}
    	
    	Iterable<Viaje> iter = viajes().find("{recorrido.usuarios.email: #}", userEmail)
    			.as(Viaje.class);
    	ReporteMetricasV reporte = new ReporteMetricasV();
    	if(!iter.iterator().hasNext()){
    		reporte.setDistanciaTotal(0);
    		reporte.setTiempoTotal(0);
    		return reporte;
    	}
    	
    	Pattern patternDistancia = Pattern.compile("^\\d*\\.\\d*");
    		
    	Pattern patternTiempo = Pattern.compile("((\\d*)\\sdays?)?\\s?((\\d*)\\shours?)?\\s?((\\d*)\\smins?)?");
    	
    	
    	double distanciaTotal = 0;
    	double tiempoTotal = 0;
    	
    	for(Viaje v: iter){
    		String distancia = v.distancia;
    		String tiempo = v.tiempo;
    		
    		Matcher matcherDistancia = patternDistancia.matcher(distancia);
    		if(matcherDistancia.find()){
    			double dist = Double.valueOf(matcherDistancia.group());
    			distanciaTotal += dist;
    		}
    		Matcher matcherTiempo = patternTiempo.matcher(tiempo);
    		if(matcherTiempo.find()){
    			String days = matcherTiempo.group(2);
    			String hour = matcherTiempo.group(4);
    			String min =  matcherTiempo.group(6);
        		System.out.println(days + ", "+ hour + ", " + min);
        		
        		tiempoTotal += (days != null)? Double.valueOf(days)*24:0;
        		tiempoTotal += (hour != null)? Double.valueOf(hour):0;
        		tiempoTotal += (min != null)? Double.valueOf(min)/60:0;
    		}
    		
    		System.out.println(distancia + "(" +distanciaTotal + ")" +", " 
    				+ tiempo  + "(" + tiempoTotal + ")");	
    	}
    	reporte.setDistanciaTotal(distanciaTotal);
    	reporte.setTiempoTotal(tiempoTotal);
        return reporte;
    }

	public static ReporteHistorialViajeV getReporteViajes(String userEmail){
    	
    	boolean enabled = EnvHelper.reporteViajesEnabled();
    	if(!enabled){
    		return null;
    	}
    	
    	Iterable<Viaje> iter = viajes().find("{recorrido.usuarios.email: #}", userEmail)
    			.as(Viaje.class);
    	ReporteHistorialViajeV reporte = new ReporteHistorialViajeV();
    	
    	int numeroDeViajes = 0;
    	int numeroDeViajesGrupales = 0;
    	int numeroDeViajesIndividuales = 0;
    	
    	for(Viaje v: iter){
    		numeroDeViajes++;
    		if(v.recorrido.usuarios.size() == 1){
    			numeroDeViajesIndividuales++;
    		}
    		else{
    			numeroDeViajesGrupales++;
    		}
    	}
    	reporte.setNumeroDeViajes(numeroDeViajes);
    	reporte.setNumeroDeViajesGrupales(numeroDeViajesGrupales);
    	reporte.setNumeroDeViajesIndividuales(numeroDeViajesIndividuales);
    	
    	return reporte;
    }

    
}
