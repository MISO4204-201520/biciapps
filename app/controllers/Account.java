package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by l on 2/10/15.
 */
public class Account extends Controller {

    public Result registerPage() {


        return ok(views.html.login.registerPage.render(""));
    }

    public Result register() {

        DynamicForm f = Form.form().bindFromRequest();
        String email = f.get("email");
        String pass = f.get("pwd");
        return ok( email + ", " + pass);
    }

    public Result loginPage() {

        return ok(views.html.login.loginPage.render( ""));
    }

    public Result login() {
        DynamicForm f = Form.form().bindFromRequest();
        String email = f.get("email");
        String pass = f.get("pwd");
        return ok( email + ", " + pass);
    }



}
