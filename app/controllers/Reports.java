package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Reports extends Controller{

	public Result reportsPage() {
        return ok(views.html.ReportsPage.render());
    }
}
