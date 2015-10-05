package controllers;

import java.net.UnknownHostException;

import org.jongo.Jongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import models.User;
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
        Iterable<User> allUsers = User.users().find().as(User.class);

        for(User u : allUsers){
            listUsers = listUsers + ", " + u.email;
        }
        //return ok(listUsers);
        return ok(views.html.deletePage.render(listUsers));


    }

}
