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
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;

/**
 * Created by ger on 2/10/15.
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

        List<User> usuarios = new ArrayList<User>();

        String emailUsuarioLogueado = session(MySecureAuth.SESSION_ID);
        User usuarioLogueado = UserBusiness.findByEmail(emailUsuarioLogueado);

        usuarios.add(usuarioLogueado);
        formRuta.usuarios = usuarios;

        RutaBusiness.insert(formRuta);
        return ok("Ok");
    }

    public Result verRecorridos() {
        String emailUsuarioLogueado = session(MySecureAuth.SESSION_ID);
        User usuarioLogueado = UserBusiness.findByEmail(emailUsuarioLogueado);

        Iterable<Recorrido> recorridosIterable = RecorridoBusiness.findByUser(usuarioLogueado);
        List<Recorrido> recorridos = Lists.newArrayList(recorridosIterable);
        
        return ok(views.html.ListaRecorridos.render(recorridos));

    }

    public Result verRutas() {
        String emailUsuarioLogueado = session(MySecureAuth.SESSION_ID);
        User usuarioLogueado = UserBusiness.findByEmail(emailUsuarioLogueado);

        Iterable<Ruta> rutasIterable = RutaBusiness.findByUser(usuarioLogueado);
        List<Ruta> rutas = Lists.newArrayList(rutasIterable);
        
        return ok(views.html.ListaRutas.render(rutas));

    }

    public Result verDetalleRuta(String id) {
        
        if (id != null){
            ObjectId idObj = new ObjectId(id);
            Ruta ruta = RutaBusiness.findById(idObj);
            return ok(views.html.DetalleRuta.render(ruta));
        }
        return ok("Debes especificar el ID de la ruta");

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

        usuarios.add(usuarioLogueado);
        
        ruta.usuarios = usuarios;
        

        Recorrido recorrido = new Recorrido();

        recorrido.nombre = f.get("nombre");
        recorrido.fecha = f.get("salida");
        recorrido.distancia = f.get("distancia");
        recorrido.tiempo = f.get("tiempo");
        recorrido.creador = usuarioLogueado;

        String amigosIds = f.get("amigos");
        String[] amigosIdList = amigosIds.split(",");
        for (int i = 0; i < amigosIdList.length; i++) {
            ObjectId idObj = new ObjectId(amigosIdList[i]);
            User amigoEncontrado = UserBusiness.findById(idObj);
            usuarios.add(amigoEncontrado);
        }

        recorrido.usuarios = usuarios;

        recorrido.ruta = ruta;

        RecorridoBusiness.insert(recorrido);
        ruta.id = recorrido.id;
        RutaBusiness.insert(recorrido.ruta);
        return redirect(routes.RutaController.verRecorridos());
    }

}
