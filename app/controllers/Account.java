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
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;


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
        formUser.setNombres(nombres);
        formUser.setApellidos(apellidos);
        formUser.setSexo(sexo);
        formUser.setEmail(email);
        formUser.setPwd(pwd);

        User existingUser = UserBusiness.findByEmail(email);
        if (existingUser != null) {
            flash("error", "Ya existe ese usuario");
            return redirect(controllers.routes.Account.registerPage());
        }

        System.out.println("Se creo el usuario: " + email);
        UserBusiness.insert(formUser);
        boolean loggedIn = loginTask(email, pwd);
        if (loggedIn) {
            return redirect(controllers.routes.Application.userPage());
        } else {
            return ok("No se pudo logear");
        }
    }

    public Result loginPage() {
        if (isLoggedIn()) {
            return redirect(controllers.routes.Application.userPage());
        }
        return ok(views.html.login.loginPage.render());
    }

    public Result login() {

        DynamicForm f = Form.form().bindFromRequest();
        String email = f.get("email");
        String pwd = f.get("pwd");
        boolean loggedIn = loginTask(email, pwd);
        if (loggedIn) {
            return redirect(controllers.routes.Application.userPage());
        } else {
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
        formUser.setEmail(email);
        formUser.setPwd(pwd);
        User existingUser = UserBusiness.findByEmail(email);
        if (existingUser != null) {
            flash("error", "Ya existe ese usuario");
            return redirect(controllers.routes.Account.storeRegisterPage());
        }

        System.out.println("Se creo el usuario: " + email);
        UserBusiness.insert(formUser);
        boolean loggedIn = loginTask(email, pwd);
        if (loggedIn) {
            return redirect(controllers.routes.Store.listPromotionPage());
        } else {
            return ok("No se pudo logear");
        }
    }

    public Result storeLoginPage() {
        if (isLoggedIn()) {
            return redirect(controllers.routes.Store.listPromotionPage());
        }
        return ok(views.html.login.storeLoginPage.render());
    }

    public Result storeLogin() {
        DynamicForm f = Form.form().bindFromRequest();
        String email = f.get("email");
        String pwd = f.get("pwd");
        boolean loggedIn = loginTask(email, pwd);
        if (loggedIn) {
            return redirect(controllers.routes.Store.listPromotionPage());
        } else {
            flash("error", "Credenciales no validas");
            return redirect(controllers.routes.Account.storeLoginPage());
        }
    }

    public Result logout() {
        session().clear();
        return redirect(controllers.routes.Application.index());
    }

    public Result storeLogout() {
        session().clear();
        return redirect(controllers.routes.Store.homePage());
    }


    public boolean loginTask(String email, String pwd) {
        boolean loggedIn = false;
        User user = UserBusiness.findByEmailAndPwd(email, pwd);
        if (user != null) {
            //Login consiste en agregar cookie:
            session(MySecureAuth.SESSION_ID, email);
            System.out.println("Logged in:" + email);
            loggedIn = true;
        }
        return loggedIn;
    }

    private boolean isLoggedIn() {
        String email = session(MySecureAuth.SESSION_ID);
        return (email != null) ? true : false;
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result editarPerfilPage() {
        String email = session(MySecureAuth.SESSION_ID);
        User usuario = UserBusiness.findByEmail(email);
        Form<User> formData = Form.form(User.class).fill(usuario);
        return ok(views.html.login.editarPerfilPage.render(formData));
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result editarUsuario() {

        DynamicForm f = Form.form().bindFromRequest();
        String nombres = f.get("nombres");
        String apellidos = f.get("apellidos");
        String sexo = f.get("sexo");
        String email = f.get("email");
        String pwd = f.get("pwd");


        User formUser = new User();
        formUser.setNombres(nombres);
        formUser.setApellidos(apellidos);
        formUser.setSexo(sexo);
        formUser.setEmail(email);
        formUser.setPwd(pwd);

        UserBusiness.update(formUser);

        return ok("actualizado");
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result getAmigos() {
        return ok(views.html.login.amigosPage.render());
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result getAmigos1() {
        String email = session(MySecureAuth.SESSION_ID);
        User usuario = UserBusiness.findByEmail(email);

        List<Amigo> amigos = usuario.getAmigos();

        if (amigos == null) {
            amigos = new ArrayList<Amigo>();
        }

        ObjectNode result = Json.newObject();

        ArrayNode an = result.putArray("aaData");

        for (Amigo c : amigos) {
            ObjectNode row = Json.newObject();
            row.put("0", c.getNickName());
            row.put("1", c.getEmail());
            an.add(row);
        }
        return ok(result);
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result addAmigo() {
        Boolean respuesta = false;
        String mensaje = "Ya existe en su lista de amigos";
        JsonNode json = request().body().asJson();
        String email = json.findPath("email").textValue();
        String emailUsuario = session(MySecureAuth.SESSION_ID);
        User usuarioSession = UserBusiness.findByEmail(emailUsuario);
        User usuarioAmigo = UserBusiness.findByEmail(email);

        if (usuarioSession.getAmigos() != null && !usuarioSession.getAmigos().isEmpty()) {
            // Buscar Amigo si existe para no volver a adicionar
            Optional<Amigo> amigoOptEnco = usuarioSession.getAmigos().stream().filter(x -> x.getEmail().equals(usuarioAmigo.getEmail())).findFirst();
            if (!amigoOptEnco.isPresent()) {
                Amigo amigo = new Amigo();
                amigo.setNickName(usuarioAmigo.getNombres() + " " + usuarioAmigo.getApellidos());
                amigo.setEmail(usuarioAmigo.getEmail());
                amigo.setIdUser(usuarioAmigo.getId());
                usuarioSession.getAmigos().add(amigo);
                respuesta = true;
                mensaje = "Amigo adicionado correctamente";
            }
        } else {
            usuarioSession.setAmigos(new ArrayList<Amigo>());
            Amigo amigo = new Amigo();
            amigo.setNickName(usuarioAmigo.getNombres() + " " + usuarioAmigo.getApellidos());
            amigo.setEmail(usuarioAmigo.getEmail());
            amigo.setIdUser(usuarioAmigo.getId());
            usuarioSession.getAmigos().add(amigo);
            respuesta = true;
            mensaje = "Amigo adicionado correctamente";
        }

        UserBusiness.update(usuarioSession);

        ObjectNode result = Json.newObject();

        ArrayNode an = result.putArray("data");

        ObjectNode row = Json.newObject();
        row.put("res", respuesta);
        row.put("mensaje", mensaje);
        an.add(row);

        return ok(result);
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result getAddAmigos() {
        String email = session(MySecureAuth.SESSION_ID);
        User usuarioSession = UserBusiness.findByEmail(email);

        Iterable<User> usuarios = UserBusiness.findAll();

        List<User> usuariosList = new ArrayList<User>();


        usuarios.forEach(x -> {
            if (usuarioSession.getAmigos() != null) {
                Optional<Amigo> amigoOptEnco = usuarioSession.getAmigos().stream().filter(y -> y.getEmail().equals(x.getEmail())).findFirst();
                if (!x.getEmail().equalsIgnoreCase(usuarioSession.getEmail()) && !amigoOptEnco.isPresent()) {
                    usuariosList.add(x);
                }
            } else {
                if (!x.getEmail().equalsIgnoreCase(usuarioSession.getEmail())) {
                    usuariosList.add(x);
                }
            }
        });

        ObjectNode result = Json.newObject();

        ArrayNode an = result.putArray("aaData");

        for (User c : usuariosList) {
            ObjectNode row = Json.newObject();
            row.put("0", c.getNombres());
            row.put("1", c.getApellidos());
            row.put("2", c.getEmail());
            an.add(row);
        }
        return ok(result);
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result removeAmigo() {
        Boolean respuesta = false;
        String mensaje = "No se puedo ejecutar la funcionlidad";

        JsonNode json = request().body().asJson();
        String email = json.findPath("email").textValue();
        String emailUsuario = session(MySecureAuth.SESSION_ID);

        User usuarioSession = UserBusiness.findByEmail(emailUsuario);

        if (usuarioSession.getAmigos() != null && !usuarioSession.getAmigos().isEmpty()) {
            // Buscar Amigo para removerlo
            Optional<Amigo> amigoOptEnco = usuarioSession.getAmigos().stream().filter(x -> x.getEmail().equals(email)).findFirst();
            if (amigoOptEnco.isPresent()) {

                usuarioSession.getAmigos().remove(amigoOptEnco.get());
                respuesta = true;
                mensaje = "Amigo removido correctamente";
            }
        } else {
            mensaje = "El amigo no existe en su lista";
        }

        UserBusiness.update(usuarioSession);

        ObjectNode result = Json.newObject();

        ArrayNode an = result.putArray("data");

        ObjectNode row = Json.newObject();
        row.put("res", respuesta);
        row.put("mensaje", mensaje);
        an.add(row);

        return ok(result);
    }
}