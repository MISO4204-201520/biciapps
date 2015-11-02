package controllers;

import play.mvc.Controller;
import play.mvc.Http.Response;
import play.mvc.Result;

import com.feth.play.module.pa.PlayAuthenticate;

public class Authenticate extends Controller {


    private static final String PAYLOAD_KEY = "p";

    public static void noCache(final Response response) {
        // http://stackoverflow.com/questions/49547/making-sure-a-web-page-is-not-cached-across-all-browsers
        response.setHeader(Response.CACHE_CONTROL, "no-cache, no-store, must-revalidate");  // HTTP 1.1
        response.setHeader(Response.PRAGMA, "no-cache");  // HTTP 1.0.
        response.setHeader(Response.EXPIRES, "0");  // Proxies.
    }

    public Result authenticate(String provider) {
        noCache(response());
        final String payload = request().getQueryString(PAYLOAD_KEY);
        return PlayAuthenticate.handleAuthentication(provider, ctx(), payload);
    }

    public Result logout() {
        noCache(response());
        return PlayAuthenticate.logout(session());
    }
}