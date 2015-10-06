package controllers.api;
import java.util.List;

import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class UsuarioController extends Controller {

	 public Result Usuarios()
     {
		 Iterable<User> usuarios = User.findAll();
         return ok(Json.toJson(usuarios));
     }
	
	 public Result UsuarioPorCorreo(String correo)
     {
		 User usuarios = User.findByEmail(correo);
         return ok(Json.toJson(usuarios));
     }
}
