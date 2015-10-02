package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by l on 2/10/15.
 */
public class Account extends Controller {

    public Result registerPage() {

        return ok(views.html.login.registerPage.render(""));
    }

    public Result loginPage() {

        return ok(views.html.login.loginPage.render(""));
    }



}
