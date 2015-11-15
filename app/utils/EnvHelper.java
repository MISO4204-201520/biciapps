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

}
