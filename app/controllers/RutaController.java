package controllers;

import models.business.UserBusiness;
import models.entities.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.entities.Ruta;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by l on 2/10/15.
 */
public class RutaController extends Controller {

    
    public Result crearRuta() {

        DynamicForm f = Form.form().bindFromRequest();
        String nombreOrigen = f.get("pinicio");
        String latitudOrigen = f.get("latOrigen");
        String longitudOrigen = f.get("lngOrigen");

        String nombreDestino = f.get("pfinal");
        String latitudDestino = f.get("latDestino");
        String longitudDestino = f.get("lngDestino");

        Ruta formRuta = new Ruta();

        formRuta.nombreOrigen = nombreOrigen;
        formRuta.latitudOrigen = latitudOrigen;
        formRuta.longitudOrigen = longitudOrigen;

        formRuta.nombreDestino = nombreDestino;
        formRuta.latitudDestino = latitudDestino;
        formRuta.longitudDestino = longitudDestino;


        return ok("Hola Mafe" + latitudOrigen + longitudDestino);/*
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
            return redirect(controllers.routes.Application.userPage());
        } else {
            return ok("No se pudo logear");
        }*/ 
    }

}
