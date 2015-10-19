package controllers;

import models.business.UserBusiness;
import models.entities.User;
import play.*;
import play.mvc.*;
import views.html.*;


public class Application extends Controller {

    public Result index() {
    	System.out.println(Play.application().configuration().getString("playjongo.uri"));
        return ok(index.render());
    }


    @Security.Authenticated(MySecureAuth.class)
    public Result userPage() {

            String email = session(MySecureAuth.SESSION_ID);
            User usuario = UserBusiness.findByEmail(email);
            return ok(views.html.userPage.render(usuario));
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result mapPage() {

        String email = session(MySecureAuth.SESSION_ID);
        User usuario = UserBusiness.findByEmail(email);
        return ok(views.html.MapPage.render(usuario));


    }

}
