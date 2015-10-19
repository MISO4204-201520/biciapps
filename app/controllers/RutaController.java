package controllers;

import models.business.UserBusiness;
import models.entities.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.entities.Ruta;
import models.business.RutaBusiness;
import models.entities.Recorrido;
import models.business.RecorridoBusiness;

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
        return ok("Ok");
    }

    public Result crearRecorrido() {

        DynamicForm f = Form.form().bindFromRequest();

        Ruta ruta = new Ruta();

        ruta.nombreOrigen = f.get("pinicio");
        ruta.latitudOrigen = f.get("latOrigen");
        ruta.longitudOrigen = f.get("lngOrigen");

        ruta.nombreDestino = f.get("pfinal");
        ruta.latitudDestino = f.get("latDestino");
        ruta.longitudDestino = f.get("lngDestino");

        
        List<User> usuarios = new ArrayList<User>();
        
        String emailUsuarioLogueado = session(MySecureAuth.SESSION_ID);
        User usuarioLogueado = UserBusiness.findByEmail(emailUsuarioLogueado);

        String msj = "No favorita"; 
        
        //¿Es una ruta favorita?

        if (f.get("rutaFavorita").charAt(0) == '1'){

            usuarios.add(usuarioLogueado);
            msj = "Favorita";
        }

        ruta.usuarios = usuarios;
        RutaBusiness.insert(ruta);

        Recorrido recorrido = new Recorrido();

        recorrido.nombre = f.get("nombre");
        recorrido.fecha = f.get("salida");
        recorrido.distancia = f.get("distancia");
        recorrido.tiempo = f.get("tiempo");
        recorrido.creador = usuarioLogueado;
        recorrido.usuarios = usuarios;

        recorrido.ruta = ruta;

        RecorridoBusiness.insert(recorrido);

        return ok(msj+f.get("rutaFavorita"));
    }

}
