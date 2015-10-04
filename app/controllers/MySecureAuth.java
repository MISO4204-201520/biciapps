package controllers;


import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security.Authenticator;


/**
 * Created by l on 4/10/15.
 */
public class MySecureAuth extends Authenticator{

    public final static String SESSION_ID = "id";

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get(SESSION_ID);
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        //return unauthorized(views.html.defaultpages.unauthorized.render());
        return redirect(routes.Account.loginPage());
    }

}
