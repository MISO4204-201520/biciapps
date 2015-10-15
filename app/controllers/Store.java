package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.Play;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import scala.util.parsing.json.JSONObject$;


/**
 * Created by l on 9/10/15.
 */
public class Store extends Controller {

    public Result homePage() {
//        String url = routes.Store.homePage().toString();
        return ok(views.html.store.homePage.render());
    }

    public Result deleteStorePage() {
        return ok(views.html.store.deleteStorePage.render());
    }

    public Result contextTestPage() {
        return ok(views.html.store.contextTestPage.render());
    }

    //@BodyParser.Of(BodyParser.Json.class)
    public Result getPromotion(String id) {
        System.out.println("Called: " + id);
        ArrayNode result = Json.newArray();
        ObjectNode promotion = Json.newObject();
        promotion.put("title", "promotion");
        result.insert(0, promotion);

        ObjectNode promotion2 = Json.newObject();
        promotion2.put("title", "title 2");
        result.insert(1, promotion2);

        return ok(result);
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result sendData(String id) {

        JsonNode json = request().body().asJson();
        System.out.println("Called: " + json +", "+ id);
//        String name = json.findPath("lat").textValue();
//        if(name == null) {
//            return badRequest("Missing parameter [name]");
//        } else {
//            return ok("Hello " + name);
//        }

//        result.put("a", "bbbbbbbb");
        return ok();
    }
}
