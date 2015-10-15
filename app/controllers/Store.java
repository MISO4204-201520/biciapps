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


    @BodyParser.Of(BodyParser.Json.class)
    public Result crearUsuario() {

        JsonNode json = request().body().asJson();
        System.out.println("Called: " + json);
//        String name = json.findPath("lat").textValue();
//        if(name == null) {
//            return badRequest("Missing parameter [name]");
//        } else {
//            return ok("Hello " + name);
//        }

        String idUsuario = "id1";
        ObjectNode newUserId = Json.newObject();
        newUserId.put("idUsuario", idUsuario);
        return ok(newUserId);
//        return badRequest("Invalid");
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result actualizarUsuario(String idUsuario) {

        JsonNode json = request().body().asJson();
        System.out.println("Called: " + json +", "+ idUsuario);
//        String name = json.findPath("lat").textValue();
//        if(name == null) {
//            return badRequest("Missing parameter [name]");
//        } else {
//            return ok("Hello " + name);
//        }

//        result.put("a", "bbbbbbbb");
        return ok();
    }

    public Result darPromociones(String idUsuario) {
        System.out.println("Called: " + idUsuario);
        ArrayNode result = getPromocionesUsuario(idUsuario);
        return ok(result);
    }

    private ArrayNode getPromocionesUsuario(String idUsuario) {
        ArrayNode result = Json.newArray();
        ObjectNode promotion = Json.newObject();

        promotion.put("titulo", "promotion");
        promotion.put("descripcion", "desc");
        promotion.put("tienda", "tienda");
        promotion.put("longitud", 10);
        promotion.put("latitud", 10);
        promotion.put("idPromocion", 101);
        result.insert(0, promotion);

        ObjectNode promotion2 = Json.newObject();
        promotion2.put("titulo", "promotion2");
        promotion2.put("descripcion", "desc2");
        promotion2.put("tienda", "tienda2");
        promotion2.put("longitud", 20);
        promotion2.put("latitud", 20);
        promotion2.put("idPromocion", 102);
        result.insert(1, promotion2);
        return result;
    }


}
