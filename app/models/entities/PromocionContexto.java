package models.entities;

import org.bson.types.ObjectId;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;
/**
 * Created by l on 15/10/15.
 */
public class PromocionContexto {

    @MongoId // auto
    @MongoObjectId
    private String id;
    private String titulo;
	private String descripcion;
	private String tienda;
    private String tiendaId;
    
    private double [] posicion;
    
    public PromocionContexto(){
    	
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public String getTiendaId() {
		return tiendaId;
	}

	public void setTiendaId(String tiendaId) {
		this.tiendaId = tiendaId;
	}
	
	public double[] getPosicion() {
		return posicion;
	}

	public void setPosicion(double[] posicion) {
		this.posicion = posicion;
	}
	
    
    
}
