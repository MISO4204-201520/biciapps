package controllers;

import models.business.UserBusiness;
import models.entities.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by l on 2/10/15.
 */
public class Account extends Controller {

    public Result registerPage() {
        return ok(views.html.login.registerPage.render());
    }

    public Result register() {

        DynamicForm f = Form.form().bindFromRequest();
        String nombres = f.get("nombres");
        String apellidos = f.get("apellidos");
        String sexo = f.get("sexo");
        String email = f.get("email");
        String pwd = f.get("pwd");


        User formUser = new User();
        formUser.nombres = nombres;
        formUser.apellidos = apellidos;
        formUser.sexo = sexo;
        formUser.email = email;
        formUser.pwd = pwd;

        User existingUser = UserBusiness.findByEmail(email);
        if (existingUser != null) {
            flash("error", "Ya existe ese usuario");
            return redirect(controllers.routes.Account.registerPage());
        }

        System.out.println("Se creo el usuario: " + email);
        UserBusiness.insert(formUser);
        boolean loggedIn = loginTask(email, pwd);
        if (loggedIn) {
            return redirect(controllers.routes.Application.deletePage());
        } else {
            return ok("No se pudo logear");
        }
    }

    public Result loginPage() {

        return ok(views.html.login.loginPage.render());
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
            flash("error", "Credenciales no validas");
            return redirect(controllers.routes.Account.loginPage());
        }
    }

    public Result storeRegisterPage() {
        return ok(views.html.login.storeRegisterPage.render());
    }

    public Result storeRegister() {

        DynamicForm f = Form.form().bindFromRequest();
        String email = f.get("email");
        String pwd = f.get("pwd");

        User formUser = new User();
        formUser.email = email;
        formUser.pwd = pwd;
        User existingUser = UserBusiness.findByEmail(email);
        if(existingUser != null){
            flash("error", "Ya existe ese usuario");
            return redirect(controllers.routes.Account.storeRegisterPage());
        }

        System.out.println("Se creo el usuario: " + email);
        UserBusiness.insert(formUser);
        boolean loggedIn = loginTask(email, pwd);
        if(loggedIn){
            return redirect(controllers.routes.Store.deleteStorePage());
        }else{
            return ok("No se pudo logear");
        }
    }

    public Result storeLoginPage() {

        return ok(views.html.login.storeLoginPage.render());
    }

    public Result storeLogin() {
        DynamicForm f = Form.form().bindFromRequest();
        String email = f.get("email");
        String pwd = f.get("pwd");
        boolean loggedIn = loginTask(email, pwd);
        if(loggedIn){
            return redirect(controllers.routes.Store.deleteStorePage());
        }
        else{
            flash("error", "Credenciales no validas");
            return redirect(controllers.routes.Account.storeLoginPage());
        }
    }



    public Result loginFacebook() {
        DynamicForm f = Form.form().bindFromRequest();
        String token = f.get("token");
        String name = f.get("name");
        String fbid = f.get("id");
        String email = f.get("email");

        User userInfo = new User();
        userInfo.token = token;
        userInfo.nombres = name;
        userInfo.fbid = fbid;
        
        
        User existingUserFB = UserBusiness.findByFBId(fbid);
        User existingUser = UserBusiness.findByEmail(email);
        
        if (existingUser != null){
            UserBusiness.insert(userInfo);
        }else{
            if (existingUserFB != null){
                UserBusiness.update(userInfo);
            }
        }
        

        return redirect(controllers.routes.Application.deletePage());
    }

    public Result logout() {
        session().clear();
        return redirect(controllers.routes.Application.index());
    }



    private boolean loginTask(String email, String pwd) {
        boolean loggedIn = false;
        User user = UserBusiness.findByEmailAndPwd(email, pwd);
        if(user != null){
            //Login consiste en agregar cookie:
            session(MySecureAuth.SESSION_ID, email);
            System.out.println("Logged in:" + email);
            loggedIn = true;
        }
        return loggedIn;
    }

    public Result editarPerfilPage() {
        String email = session(MySecureAuth.SESSION_ID);
        User usuario = UserBusiness.findByEmail(email);
        Form<User> formData = Form.form(User.class).fill(usuario);
        return ok(views.html.login.editarPerfilPage.render(formData));
    }

    public Result editarUsuario() {

        DynamicForm f = Form.form().bindFromRequest();
        String nombres = f.get("nombres");
        String apellidos = f.get("apellidos");
        String sexo = f.get("sexo");
        String email = f.get("email");
        String pwd = f.get("pwd");


        User formUser = new User();
        formUser.nombres = nombres;
        formUser.apellidos = apellidos;
        formUser.sexo = sexo;
        formUser.email = email;
        formUser.pwd = pwd;

        UserBusiness.update(formUser);

        return ok("actualizado");
    }

    public Result getAmigos() {
        String email = session(MySecureAuth.SESSION_ID);
        User usuario = UserBusiness.findByEmail(email);

        Iterable<User> usuarios = UserBusiness.findAll();

        List<User> usuariosList = new ArrayList<User>();
        usuarios.forEach(x-> usuariosList.add(x));

        return ok(views.html.login.amigosPage.render(usuariosList));
    }

}
