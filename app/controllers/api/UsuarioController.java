package controllers.api;

import models.Business.UserBusiness;
import models.entities.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class UsuarioController extends Controller {

	 public Result users()
     {
		 Iterable<User> foundUsers = UserBusiness.findAll();
         return ok(Json.toJson(foundUsers));
     }
	
	 public Result userByEmail(String email)
     {
		 User foundUser = UserBusiness.findByEmail(email);
         return ok(Json.toJson(foundUser));
     }
}
