package models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Omar on 18/10/2015.
 */
public class Amigo {

    @JsonProperty("_id")
    private ObjectId id;

    private String nickName;

    private String email;

    private ObjectId idUser;

    public Amigo(ObjectId id, String nickName, String email, ObjectId idUser) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.idUser = idUser;
    }

    public Amigo() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ObjectId getIdUser() {
        return idUser;
    }

    public void setIdUser(ObjectId idUser) {
        this.idUser = idUser;
    }
}
