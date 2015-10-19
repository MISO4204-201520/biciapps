package controllers;

import models.business.UserBusiness;
import models.entities.Amigo;
import models.entities.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.entities.Ruta;
import models.entities.Evento;
import models.entities.Amigo;
import models.business.RutaBusiness;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Fernanda on 2/10/15.
 */
public class RutaController extends Controller {

    
    public Result crearRuta() {

        DynamicForm f = Form.form().bindFromRequest();
        String emailUser= f.get("emailuser");
        String nombreOrigen = f.get("pinicio");
        String latitudOrigen = f.get("latOrigen");
        String longitudOrigen = f.get("lngOrigen");

        String nombreDestino = f.get("pfinal");
        String latitudDestino = f.get("latDestino");
        String longitudDestino = f.get("lngDestino");

        Ruta formRuta = new Ruta();

        formRuta.emailUser=emailUser;
        formRuta.nombreOrigen = nombreOrigen;
        formRuta.latitudOrigen = latitudOrigen;
        formRuta.longitudOrigen = longitudOrigen;

        formRuta.nombreDestino = nombreDestino;
        formRuta.latitudDestino = latitudDestino;
        formRuta.longitudDestino = longitudDestino;

        RutaBusiness.insertRuta(formRuta);

        return ok( latitudOrigen + longitudDestino);
    }

    public Result crearEvento() {

        DynamicForm f = Form.form().bindFromRequest();
        String emailUser= f.get("emailuser");
        String nombreruta=f.get("nombre");
        //List<Amigo> amigos=f.get("grupo");
        String fecha=f.get("salida");
        String nombreOrigen = f.get("pinicio");
        String latitudOrigen = f.get("latOrigen");
        String longitudOrigen = f.get("lngOrigen");

        String nombreDestino = f.get("pfinal");
        String latitudDestino = f.get("latDestino");
        String longitudDestino = f.get("lngDestino");
        String distancia=f.get("distancia");
        String tiempo=f.get("tiempo");

        Evento formEvento= new Evento();

        formEvento.emailUser=emailUser;
        formEvento.nombre=nombreruta;
        formEvento.fecha=fecha;
        //formEvento.amigos=amigos;
        formEvento.nombreOrigen = nombreOrigen;
        formEvento.latitudOrigen = latitudOrigen;
        formEvento.longitudOrigen = longitudOrigen;
        formEvento.nombreDestino = nombreDestino;
        formEvento.latitudDestino = latitudDestino;
        formEvento.longitudDestino = longitudDestino;
        formEvento.distancia=distancia;
        formEvento.tiempo=tiempo;

        RutaBusiness.insertEvento(formEvento);

        return ok( latitudOrigen + longitudDestino);
    }

}
