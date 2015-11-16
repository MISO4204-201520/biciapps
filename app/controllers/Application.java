package controllers;

import models.business.UserBusiness;
import models.entities.User;
import play.*;
import play.mvc.*;
import procesador.Procesador;
import utils.EnvHelper;
import views.html.*;
import models.entities.Amigo;

import java.util.List;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Application extends Controller {

    public static final String FLASH_MESSAGE_KEY = "message";
    public static final String FLASH_ERROR_KEY = "error";

    public Result index() {
    	System.out.println(Play.application().configuration().getString("playjongo.uri"));
        return ok(index.render());
    }


    @Security.Authenticated(MySecureAuth.class)
    public Result userPage() {
    		Procesador.cargarConfiguracion();
            String email = session(MySecureAuth.SESSION_ID);
            User usuario = UserBusiness.findByEmail(email);
            
            return ok(views.html.userPage.render(usuario, EnvHelper.reportesEnabled()));
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result mapPage() {

        String email = session(MySecureAuth.SESSION_ID);
        User usuario = UserBusiness.findByEmail(email);

        List<Amigo> amigos = usuario.getAmigos();
        List<User> amigosData = new ArrayList<User>();
        if (amigos != null){
            for(Amigo amigo : amigos){
                ObjectId amigoId = amigo.getIdUser();
                User amigoData = UserBusiness.findById(amigoId);
                amigosData.add(amigoData);
            }
        }


        return ok(views.html.MapPage.render(usuario, amigosData, EnvHelper.grupalEnabled(), EnvHelper.metricasEnabled()));


    }

    public Result oAuthDenied(String providerKey) {
        Authenticate.noCache(response());
        flash(FLASH_ERROR_KEY,
                "You need to accept the OAuth connection in order to use this website!");
        return redirect(controllers.routes.Application.index());
    }

}
