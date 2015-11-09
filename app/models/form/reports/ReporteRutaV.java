package models.form.reports;

import java.util.ArrayList;
import java.util.List;

public class ReporteRutaV {
	
	private int numeroDeRutasGuardadas;
	private List<String> rutas;
	
	public ReporteRutaV(){
		rutas = new ArrayList<String>();
	}

	public int getNumeroDeRutasGuardadas() {
		return numeroDeRutasGuardadas;
	}

	public void setNumeroDeRutasGuardadas(int numeroDeRutasGuardadas) {
		this.numeroDeRutasGuardadas = numeroDeRutasGuardadas;
	}

	public List<String> getRutas() {
		return rutas;
	}

	public void setRutas(List<String> rutas) {
		this.rutas = rutas;
	}
	
	
	

}
