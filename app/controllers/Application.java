package controllers;

import models.User;
import play.*;
import play.mvc.*;

import views.html.*;


public class Application extends Controller {

    public Result index() {

        return ok(index.render());
    }


    @Security.Authenticated(MySecureAuth.class)
    public Result deletePage() {

        String listUsers = "";
        Iterable<User> allUsers = User.users().find().as(User.class);

        for(User u : allUsers){
            listUsers = listUsers + ", " + u.email;
        }
        //return ok(listUsers);
        return ok(views.html.deletePage.render(listUsers));


    }

}
