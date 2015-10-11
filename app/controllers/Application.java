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
    public Result deletePage() {

        String listUsers = "";
        Iterable<User> allUsers = UserBusiness.users().find().as(User.class);

        for(User u : allUsers){
            listUsers = listUsers + ", " + u.email;
        }
        //return ok(listUsers);
        return ok(views.html.deletePage.render(listUsers));


    }

}
