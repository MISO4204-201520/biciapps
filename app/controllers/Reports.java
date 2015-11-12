package controllers;

import models.business.RutaBusiness;
import models.business.ViajeBusiness;
import models.form.reports.ReporteHistorialViajeV;
import models.form.reports.ReporteMetricasV;
import models.form.reports.ReporteRutaV;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class Reports extends Controller{

	@Security.Authenticated(MySecureAuth.class)
	public Result reportsPage() {
		
		String email = Account.getLoggedInEmail();
		
		ReporteMetricasV reporteMetricas = ViajeBusiness.getReporteMetricas(email);		
		ReporteRutaV reporteRuta = RutaBusiness.getReporteRuta(email);
		ReporteHistorialViajeV reporteHistorial = ViajeBusiness.getReporteViajes(email);

        //return ok(views.html.ReportsPage.render(null, reporteRuta, reporteHistorial));
        return ok(views.html.ReportsPage.render(reporteMetricas,reporteRuta ,reporteHistorial));
    

	}
}
