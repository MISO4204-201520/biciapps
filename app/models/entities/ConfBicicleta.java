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
	private User user;
	private String nombrePersonalizado;
	private String tipoBicicleta;
	private String aleacion;
	private String rines;
	private String bielas;
	private String tipoMarco;
	private String tenedor;
	private String tipoLlanta;
	private String accesorio1;
	private String accesorio2;
	private String accesorio3;	
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    public String getNombrePersonalizado() {
		return nombrePersonalizado;
	}
	public void setNombrePersonalizado(String nombrePersonalizado) {
		this.nombrePersonalizado = nombrePersonalizado;
	}
	public String getTipoBicicleta() {
		return tipoBicicleta;
	}
	public void setTipoBicicleta(String tipoBicicleta) {
		this.tipoBicicleta = tipoBicicleta;
	}
	public String getAleacion() {
		return aleacion;
	}
	public void setAleacion(String aleacion) {
		this.aleacion = aleacion;
	}
	public String getRines() {
		return rines;
	}
	public void setRines(String rines) {
		this.rines = rines;
	}
	public String getBielas() {
		return bielas;
	}
	public void setBielas(String bielas) {
		this.bielas = bielas;
	}
	public String getTipoMarco() {
		return tipoMarco;
	}
	public void setTipoMarco(String tipoMarco) {
		this.tipoMarco = tipoMarco;
	}
	public String getTenedor() {
		return tenedor;
	}
	public void setTenedor(String tenedor) {
		this.tenedor = tenedor;
	}
	public String getTipoLlanta() {
		return tipoLlanta;
	}
	public void setTipoLlanta(String tipoLlanta) {
		this.tipoLlanta = tipoLlanta;
	}
	public String getAccesorio1() {
		return accesorio1;
	}
	public void setAccesorio1(String accesorio1) {
		this.accesorio1 = accesorio1;
	}
	public String getAccesorio2() {
		return accesorio2;
	}
	public void setAccesorio2(String accesorio2) {
		this.accesorio2 = accesorio2;
	}
	public String getAccesorio3() {
		return accesorio3;
	}
	public void setAccesorio3(String accesorio3) {
		this.accesorio3 = accesorio3;
	}
	@Override
	public String toString() {
		return "ConfBicicleta [" 
				+ (tienda.getNombre() != null && !tienda.getNombre().isEmpty() ? 
						"tienda=" + tienda.getNombre():"")
				+ (nombrePersonalizado != null && !nombrePersonalizado.isEmpty() ?
						", nombrePersonalizado=" + nombrePersonalizado:"")
				+ (tipoBicicleta != null && !tipoBicicleta.isEmpty() ?
						", tipoBicicleta=" + tipoBicicleta:"")
				+ (aleacion != null && !aleacion.isEmpty() ?
						", aleacion=" + aleacion:"")
				+ (rines != null && !rines.isEmpty() ?
						", rines=" + rines:"")
				+ (bielas != null && !bielas.isEmpty() ?
						", bielas=" + bielas:"")
				+ (tipoMarco != null && !tipoMarco.isEmpty() ?
						", tipoMarco=" + tipoMarco:"")
				+ (tenedor != null && !tenedor.isEmpty() ?
						", tenedor=" + tenedor:"")
				+ (tipoLlanta != null && !tipoLlanta.isEmpty() ?
						", tipoLlanta=" + tipoLlanta:"")		
				+ (accesorio1 != null && !accesorio1.isEmpty() ?
						", accesorio1=" + accesorio1:"")			
				+ (accesorio2 != null && !accesorio2.isEmpty() ?
						", accesorio2=" + accesorio2:"")				
				+ (accesorio3 != null && !accesorio3.isEmpty() ?
						", accesorio3=" + accesorio3:"")
				+ "]";
	}
}
