package models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.List;


/**
 * Created by l on 27/09/15.
 */
public class User {

    @JsonProperty("_id")
    private ObjectId id;

    private String token;

    private String fbid;

    private String nombres;

    private String apellidos;

    private String sexo;

    private String email;

    private String pwd;

    private List<Amigo> amigos;

    public User(ObjectId id, String token, String fbid, String nombres, String apellidos, String sexo, String email, String pwd) {
        this.id = id;
        this.token = token;
        this.fbid = fbid;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.email = email;
        this.pwd = pwd;
    }

    public User() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Amigo> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Amigo> amigos) {
        this.amigos = amigos;
    }
}
