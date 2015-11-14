package utils.compartirredessociales;

import facebook4j.FacebookFactory;
import facebook4j.conf.ConfigurationBuilder;
import play.Configuration;
import play.Play;


/**
 * Created by Omar on 07/11/2015.
 */
public class Facebook implements ICompartirRedSocial {
    public static final String ACCESS_TOKEN = "facebook.access-token";
    public static final String CONSUMER_KEY = "facebook.consumer-key";
    public static final String CONSUMER_SECRET = "facebook.consumer-secret";


    private static String accessToken;
    private static String consumerKey;
    private static String consumerSecret;

    public String publicarMensaje(String mensaje, String usuarioToken) {

        String resultado = null;

        try {
            Configuration configuration = Play.application().configuration();
            if(usuarioToken != null && !usuarioToken.isEmpty()) {
                accessToken = usuarioToken;
            }
            else {
                accessToken = configuration.getString(ACCESS_TOKEN);
            }
            consumerKey = configuration.getString(CONSUMER_KEY);
            consumerSecret = configuration.getString(CONSUMER_SECRET);

            facebook4j.Facebook facebook;
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthAppId(consumerKey)
                    .setOAuthAppSecret(consumerSecret)
                    .setOAuthAccessToken(accessToken)
                    .setOAuthPermissions("email,user_posts");

            facebook = new FacebookFactory(cb.build()).getInstance();

            resultado = facebook.postStatusMessage(mensaje);

        } catch (Exception exp) {
            exp.printStackTrace();
            System.out.println("ERRRORR");
        }

        return resultado;
    }
}
