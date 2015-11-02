package service;

import com.feth.play.module.pa.providers.oauth2.facebook.FacebookAuthUser;
import controllers.Account;
import controllers.MySecureAuth;
import models.business.UserBusiness;
import models.entities.User;
import org.bson.types.ObjectId;
import play.Application;

import com.feth.play.module.pa.user.AuthUser;
import com.feth.play.module.pa.user.AuthUserIdentity;
import com.feth.play.module.pa.service.UserServicePlugin;
import com.google.inject.Inject;
import play.mvc.Controller;

public class MyUserServicePlugin extends UserServicePlugin {

    @Inject
    public MyUserServicePlugin(final Application app) {
        super(app);
    }

    @Override
    public Object save(AuthUser authUser) {
        boolean isLinked = UserBusiness.existsByAuthUserIdentity(authUser);
        if (!isLinked) {
            User usuarioCreado = UserBusiness.createUserAuth(authUser);
            boolean loggedIn = new Account().loginTask(usuarioCreado.getEmail(), usuarioCreado.getPwd());
            if(loggedIn) {
                Controller.session(MySecureAuth.SESSION_ID, usuarioCreado.getEmail());
            }
            return usuarioCreado.getIdSocial();
        } else {
            // we have this user already, so return null
            return null;
        }
    }

    @Override
    public Object getLocalIdentity(AuthUserIdentity identity) {
        final User user = UserBusiness.findByIdSocialAndProvider(identity.getId(), identity.getProvider());
        if(user != null) {
            Controller.session(MySecureAuth.SESSION_ID, user.getEmail());
            return user.getIdSocial();
        } else {
            return null;
        }
    }

    @Override
    public AuthUser merge(AuthUser newUser, AuthUser oldUser) {

        return  null;
    }

    @Override
    public AuthUser link(AuthUser oldUser, AuthUser newUser) {

        return  null;
    }

}
