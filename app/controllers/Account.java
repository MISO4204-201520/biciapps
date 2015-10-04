package controllers;

import models.User;
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
        String pwd = f.get("pwd");

        User formUser = new User();
        formUser.email = email;
        formUser.pwd = pwd;
        User existingUser = User.findByEmail(email);
        if(existingUser != null){
            formUser.insert();
            return ok("Already exists");
        }

        System.out.println("Se creo el usuario: " + email);
        formUser.insert();
        boolean loggedIn = loginTask(email, pwd);
        if(loggedIn){
            return redirect(controllers.routes.Application.deletePage());
        }
        else{
            return ok("No se pudo logear");
        }


    }

    public Result loginPage() {

        return ok(views.html.login.loginPage.render(""));
    }

    public Result login() {
        DynamicForm f = Form.form().bindFromRequest();
        String email = f.get("email");
        String pwd = f.get("pwd");
        boolean loggedIn = loginTask(email, pwd);
        if(loggedIn){
            return redirect(controllers.routes.Application.deletePage());
        }
        else{
            return ok("No se pudo logear");
        }
    }

    public Result logout() {
        session().clear();
        return redirect(controllers.routes.Application.index());
    }



    private boolean loginTask(String email, String pwd) {
        boolean loggedIn = false;
        User user = User.findByEmailAndPwd(email, pwd);
        if(user != null){
            //Login consiste en agregar cookie:
            session(MySecureAuth.SESSION_ID, email);
            System.out.println("Logged in:" + email);
            loggedIn = true;
        }
        return loggedIn;
    }




}
