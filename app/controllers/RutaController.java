package controllers;

import models.business.UserBusiness;
import models.entities.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.entities.Ruta;
import models.business.RutaBusiness;

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

        RutaBusiness.insert(formRuta);

        return ok("Hola Mafe" + latitudOrigen + longitudDestino);
    }

}
