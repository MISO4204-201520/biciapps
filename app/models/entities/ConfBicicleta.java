package models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

/**
 * Created by Jhon Gutierrez
 * Modulo Configuración de Bicicletas
 */
public class ConfBicicleta {

    @JsonProperty("_id")
    private ObjectId id;
	private Tienda tienda;    
    private String descripcionConf;
	private String nombrePersonalizado;

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
    public Tienda getTienda() {
		return tienda;
	}
	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	public String getDescripcionConf() {
		return descripcionConf;
	}
	public void setDescripcionConf(String descripcionConf) {
		this.descripcionConf = descripcionConf;
	}
    public String getNombrePersonalizado() {
		return nombrePersonalizado;
	}
	public void setNombrePersonalizado(String nombrePersonalizado) {
		this.nombrePersonalizado = nombrePersonalizado;
	}
}
