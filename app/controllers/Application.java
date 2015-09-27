package controllers;

import models.User;
import play.*;
import play.mvc.*;

import views.html.*;


public class Application extends Controller {

    public Result index() {

        User user = new User();
        user.name = "Mike1";
        user.insert();

        //Inserta y luego quita
        User user2 = new User();
        user2.name = "Mike1";
        user2.insert();
        user2.remove();


        String listUsers = "";
        Iterable<User> allUsers = User.users().find().as(User.class);

        for(User u : allUsers){
            listUsers = listUsers + ", " + u.name;
        }
        return ok(listUsers);
        //return ok(index.render("Your new application is ready."));


    }

}
