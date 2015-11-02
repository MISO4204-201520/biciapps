package controllers;

import models.form.reports.ReporteHistorialViajeV;
import models.form.reports.ReporteMetricasV;
import models.form.reports.ReporteRutaV;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class Reports extends Controller{

	@Security.Authenticated(MySecureAuth.class)
	public Result reportsPage() {
		
		ReporteMetricasV reporteMetricas = new ReporteMetricasV();
		reporteMetricas.setDistanciaTotal(120);
		reporteMetricas.setTiempoTotal(300);
		
		ReporteRutaV reporteRuta = new ReporteRutaV();
		reporteRuta.setNumeroDeRutasGuardadas(20);
		
		ReporteHistorialViajeV reporteHistorial = new ReporteHistorialViajeV();
		reporteHistorial.setNumeroDeViajes(10);
		reporteHistorial.setNumeroDeViajesGrupales(8);
		reporteHistorial.setNumeroDeViajesIndividuales(2);
		
        //return ok(views.html.ReportsPage.render(null, reporteRuta, reporteHistorial));
        return ok(views.html.ReportsPage.render(reporteMetricas,reporteRuta ,reporteHistorial));
    

	}
}
