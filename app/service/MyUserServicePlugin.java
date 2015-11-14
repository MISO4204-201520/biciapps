package service;

import com.feth.play.module.pa.providers.oauth2.OAuth2AuthUser;
import controllers.Account;
import controllers.MySecureAuth;
import models.business.UserBusiness;
import models.entities.User;
import play.Application;

import com.feth.play.module.pa.user.AuthUser;
import com.feth.play.module.pa.user.AuthUserIdentity;
import com.feth.play.module.pa.service.UserServicePlugin;
import com.google.inject.Inject;
import play.Play;
import play.mvc.Controller;
import utils.compartirredessociales.CompartirFactory;
import utils.compartirredessociales.ICompartirRedSocial;
import utils.compartirredessociales.Twitter;

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

            if(authUser.getProvider().equalsIgnoreCase("twitter")
                    && Boolean.parseBoolean(Play.application().configuration().getString("redessociales.twitter"))) {
                ICompartirRedSocial twitter = CompartirFactory.getInstance().getCompartirRed("twitter");
                twitter.publicarMensaje("@" + usuarioCreado.getEmail() + " esta usando biciapps", "");
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
            if(identity.getProvider().equalsIgnoreCase("twitter")
                    && Boolean.parseBoolean(Play.application().configuration().getString("redessociales.twitter"))) {

                ICompartirRedSocial twitter = CompartirFactory.getInstance().getCompartirRed("twitter");
                twitter.publicarMensaje("@" + user.getEmail() + " esta usando biciapps", "");
            }
            else if(identity.getProvider().equalsIgnoreCase("facebook") &&
                    Boolean.parseBoolean(Play.application().configuration().getString("redessociales.facebook"))){

                if (identity instanceof OAuth2AuthUser) {
                    OAuth2AuthUser oAuth2AuthUser = (OAuth2AuthUser) identity;
                    String oauth2accessToken = oAuth2AuthUser.getOAuth2AuthInfo().getAccessToken();
                    Controller.session().put("oauth2accessToken", oauth2accessToken);
                }
            }

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
