package utils;

import play.Play;

public class EnvHelper {

	public static boolean reportesEnabled(){
		return reporteRutasEnabled() ||
				reporteViajesEnabled() ||
				reporteMetricasEnabled();
	}
	
	public static boolean reporteRutasEnabled() {
		return Play.application().configuration()
						.getString("reportes.rutas") != null &&
					 Play.application().configuration()
					 	.getString("reportes.rutas").equals("TRUE");
	}

	public static boolean reporteViajesEnabled() {
		return Play.application().configuration()
					.getString("reportes.historialviaje") != null &&
				 Play.application().configuration()
				.getString("reportes.historialviaje").equals("TRUE");
	}

	public static boolean reporteMetricasEnabled() {
		return Play.application().configuration()
				.getString("reportes.metricas") != null &&
			 Play.application().configuration()
			.getString("reportes.metricas").equals("TRUE");
	}

	public static boolean grupalEnabled() {
		return Play.application().configuration().getString("mapa.desplazamientoGrupal") != null &&
				Play.application().configuration().getString("mapa.desplazamientoGrupal").equals("TRUE");
	}
	public static boolean metricasEnabled() {
		return Play.application().configuration().getString("mapa.metricas") != null &&
				Play.application().configuration().getString("mapa.metricas").equals("TRUE");
	}
	public static boolean historialViajesEnabled() {
		return Play.application().configuration().getString("historialViajes") != null &&
				Play.application().configuration().getString("historialViajes").equals("TRUE");
	} 
}
