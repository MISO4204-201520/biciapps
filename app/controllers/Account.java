package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.business.UserBusiness;
import models.entities.Amigo;
import models.entities.User;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
            if (existingUserFB != null) {
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
        return ok(views.html.login.amigosPage.render());
    }

    public Result getAmigos1() {
        String email = session(MySecureAuth.SESSION_ID);
        User usuario = UserBusiness.findByEmail(email);

        Iterable<User> usuarios = UserBusiness.findAll();

        List<User> usuariosList = new ArrayList<User>();
        usuarios.forEach(x-> usuariosList.add(x));

        ObjectNode result = Json.newObject();

        ArrayNode an = result.putArray("aaData");

        for(User c : usuariosList) {
            ObjectNode row = Json.newObject();
            row.put("0", c.nombres);
            row.put("1", c.apellidos);
            row.put("2", c.email);
            an.add(row);
        }
        return ok(result);
    }

    public Result addAmigo() {
        JsonNode json = request().body().asJson();
        String email = json.findPath("email").textValue();
        String emailUsuario = session(MySecureAuth.SESSION_ID);
        User usuarioSession = UserBusiness.findByEmail(emailUsuario);
        User usuarioAmigo = UserBusiness.findByEmail(email);

        if (usuarioSession.amigos != null && !usuarioSession.amigos.isEmpty()) {
            // Buscar Amigo si existe para no volver a adicionar
        } else {
            usuarioSession.amigos = new ArrayList<Amigo>();
            Amigo amigo = new Amigo();
            amigo.nickName = usuarioAmigo.nombres + " " + usuarioAmigo.apellidos;
            amigo.email = usuarioAmigo.email;

            usuarioSession.amigos.add(amigo);
        }

        UserBusiness.update(usuarioSession);

        ObjectNode result = Json.newObject();

        ArrayNode an = result.putArray("data");

        ObjectNode row = Json.newObject();
        row.put("res", true);
        row.put("mensaje", "Ok");
        an.add(row);

        return ok(result);
    }
}
