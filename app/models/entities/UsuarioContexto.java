package models.entities;

import org.bson.types.ObjectId;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;
/**
 * Created by l on 15/10/15.
 */
public class UsuarioContexto {

    @MongoId // auto
    @MongoObjectId
    private String idUsuario;

    private double longitud;
    
    private double latitud;
    
    public UsuarioContexto(){
    	
    }
    
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}



	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

    
    
}
