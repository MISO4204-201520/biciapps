package models.dao;


import org.jongo.MongoCollection;

import com.fasterxml.jackson.databind.JsonNode;

import models.entities.UsuarioContexto;

public class DAOContexto {
	private static final String USUARIOS_CONTEXTO = "UsuariosContexto";
	private static MongoCollection usuarios = 
			MongoManager.jongo.getCollection(USUARIOS_CONTEXTO);
	
	public static UsuarioContexto guardarUsuario(UsuarioContexto usuario){
		usuarios.save(usuario);
		return usuario;
	}
	
	
	public static UsuarioContexto darUsuarioDadoJson(JsonNode json){
//      String name = json.findPath("lat").textValue();
//      if(name == null) {
//          return badRequest("Missing parameter [name]");
//      } else {
//          return ok("Hello " + name);
//      }
		UsuarioContexto usuario = new UsuarioContexto();
		return usuario;
	}

}
