package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by l on 9/10/15.
 */
public class Store extends Controller {

    public Result homePage() {
//        String url = routes.Store.homePage().toString();
        return ok(views.html.store.homePage.render());
    }

    public Result deleteStorePage() {
        return ok(views.html.store.deleteStorePage.render());
    }
}
