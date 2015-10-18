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

        String listUsers = "";
        Iterable<User> allUsers = UserBusiness.users().find().as(User.class);

        for(User u : allUsers){
            listUsers = listUsers + ", " + u.email;
        }
        //return ok(listUsers);
        return ok(views.html.userPage.render(listUsers));
    }

    @Security.Authenticated(MySecureAuth.class)
    public Result mapPage() {

        String listUsers = "";
        Iterable<User> allUsers = UserBusiness.users().find().as(User.class);

        for(User u : allUsers){
            listUsers = listUsers + ", " + u.email;
        }
        //return ok(listUsers);
        return ok(views.html.MapPage.render(listUsers));


    }

}
