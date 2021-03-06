package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.business.UserBusiness;
import models.entities.User;
import play.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.EnvHelper;
import models.entities.Ruta;
import models.business.RutaBusiness;
import models.entities.Recorrido;
import models.business.RecorridoBusiness;
import models.entities.Viaje;
import models.business.ViajeBusiness;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import play.mvc.Security;
import utils.compartirredessociales.CompartirFactory;
import utils.compartirredessociales.Facebook;
import utils.compartirredessociales.ICompartirRedSocial;
import utils.compartirredessociales.Twitter;

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

        return ok(views.html.ListaRecorridos.render(recorridos, EnvHelper.grupalEnabled(), EnvHelper.metricasEnabled()));

    }

    public Result verRutas() {
        String emailUsuarioLogueado = session(MySecureAuth.SESSION_ID);
        User usuarioLogueado = UserBusiness.findByEmail(emailUsuarioLogueado);

        Iterable<Ruta> rutasIterable = RutaBusiness.findByUser(usuarioLogueado);
        List<Ruta> rutas = Lists.newArrayList(rutasIterable);

        return ok(views.html.ListaRutas.render(rutas));

    }

    public Result verDetalleRuta(String id) {

        if (id != null) {
            ObjectId idObj = new ObjectId(id);
            Ruta ruta = RutaBusiness.findById(idObj);
            return ok(views.html.DetalleRuta.render(ruta));
        }
        return ok("Debes especificar el ID de la ruta");

    }

    public Result verViajes() {
        String emailUsuarioLogueado = session(MySecureAuth.SESSION_ID);
        User usuarioLogueado = UserBusiness.findByEmail(emailUsuarioLogueado);

        Iterable<Viaje> viajesIterable = ViajeBusiness.findByUser(usuarioLogueado);
        List<Viaje> viajes = Lists.newArrayList(viajesIterable);
        return ok(views.html.listaViajes.render(viajes, EnvHelper.grupalEnabled(), EnvHelper.metricasEnabled()));
    }

    public Result crearViaje() {
        DynamicForm f = Form.form().bindFromRequest();
        Viaje viaje = new Viaje();
        ObjectId idObj = new ObjectId(f.get("recorridoid"));
        viaje.recorrido = RecorridoBusiness.findById(idObj);
        viaje.fecha = f.get("fecha");
        viaje.tiempo = viaje.recorrido.tiempo;
        viaje.distancia = viaje.recorrido.distancia;
        ViajeBusiness.insert(viaje);
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

        usuarios.add(usuarioLogueado);

        ruta.usuarios = usuarios;


        Recorrido recorrido = new Recorrido();

        recorrido.nombre = f.get("nombre");
        recorrido.fecha = f.get("salida");
        recorrido.distancia = f.get("distancia");
        recorrido.tiempo = f.get("tiempo");
        recorrido.creador = usuarioLogueado;

        String amigosIds = f.get("amigos");
        if (amigosIds != "") {
            String[] amigosIdList = amigosIds.split(",");
            for (int i = 0; i < amigosIdList.length; i++) {
                ObjectId idObj = new ObjectId(amigosIdList[i]);
                User amigoEncontrado = UserBusiness.findById(idObj);
                usuarios.add(amigoEncontrado);
            }
        }


        recorrido.usuarios = usuarios;

        recorrido.ruta = ruta;

        RecorridoBusiness.insert(recorrido);
        ruta.id = recorrido.id;
        RutaBusiness.insert(recorrido.ruta);
        return redirect(routes.RutaController.verRecorridos());
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result tweet() {
        String emailUsuarioLogueado = session(MySecureAuth.SESSION_ID);
        Boolean respuesta = false;
        String mensaje = "No se puedo compartir el contenido";
        JsonNode json = request().body().asJson();
        String mensajeInfo = json.findPath("info").textValue();
        mensajeInfo = "@" + emailUsuarioLogueado + "," + mensajeInfo;
        int maxlength = mensajeInfo.length() - 1;
        if (maxlength > 139) {
            mensajeInfo = mensajeInfo.substring(0, 139);
        }
        if (Boolean.parseBoolean(Play.application().configuration().getString("comunicacion.compartirredessociales")) &&
                Boolean.parseBoolean(Play.application().configuration().getString("redessociales.twitter"))) {
            ICompartirRedSocial twitter = CompartirFactory.getInstance().getCompartirRed("twitter");

            String resultado = twitter.publicarMensaje(mensajeInfo, "");

            if (resultado != null) {
                respuesta = true;
                mensaje = "Se compartio exitosamente el contenido";
            }
        }
        ObjectNode result = Json.newObject();

        ArrayNode an = result.putArray("data");

        ObjectNode row = Json.newObject();
        row.put("res", respuesta);
        row.put("mensaje", mensaje);
        an.add(row);

        return ok(result);
    }


    @Security.Authenticated(MySecureAuth.class)
    public Result post() {
        String emailUsuarioLogueado = session(MySecureAuth.SESSION_ID);
        Boolean respuesta = false;
        String mensaje = "No se puedo compartir el contenido";
        JsonNode json = request().body().asJson();
        String mensajeInfo = json.findPath("info").textValue();

        if (Boolean.parseBoolean(Play.application().configuration().getString("comunicacion.compartirredessociales")) &&
                Boolean.parseBoolean(Play.application().configuration().getString("redessociales.facebook"))) {
            ICompartirRedSocial facebook = CompartirFactory.getInstance().getCompartirRed("facebook");

            String usuarioToken = session("oauth2accessToken");

            String resultado = facebook.publicarMensaje(mensajeInfo, usuarioToken);

            if (resultado != null) {
                respuesta = true;
                mensaje = "Se compartio exitosamente el contenido";
            }
        }

        ObjectNode result = Json.newObject();

        ArrayNode an = result.putArray("data");

        ObjectNode row = Json.newObject();
        row.put("res", respuesta);
        row.put("mensaje", mensaje);
        an.add(row);

        return ok(result);
    }
}
