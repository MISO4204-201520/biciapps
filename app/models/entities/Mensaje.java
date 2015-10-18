package models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;

public class Mensaje {
	
    @JsonProperty("_id")
    private ObjectId id;
	private String remitente;
	private Date fecha;
	private String asunto;
	private String contenidoTxt;
	private String contenidoHtml;
	
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getContenidoTxt() {
		return contenidoTxt;
	}
	public void setContenidoTxt(String contenidoTxt) {
		this.contenidoTxt = contenidoTxt;
	}
	public String getContenidoHtml() {
		return contenidoHtml;
	}
	public void setContenidoHtml(String contenidoHtml) {
		this.contenidoHtml = contenidoHtml;
	}
}
