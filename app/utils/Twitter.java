package utils;

import play.Configuration;
import play.Play;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Query;

/**
 * Created by Omar on 07/11/2015.
 */
public class Twitter {
    public static final String ACCESS_TOKEN = "twitter.access-token";
    public static final String ACCESS_SECRET = "twitter.access-secret";
    public static final String CONSUMER_KEY = "twitter.consumer-key";
    public static final String CONSUMER_SECRET = "twitter.consumer-secret";
    public static final String REFRESH_INTERVAL = "twitter.refresh-interval";

    private static String accessToken;
    private static String accessSecret;
    private static String consumerKey;
    private static String consumerSecret;
    private static long refreshInterval;

    public static Status tweet(String mensaje) {

        Status resultado = null;

        try {
            Configuration configuration = Play.application().configuration();
            accessToken = configuration.getString(ACCESS_TOKEN);
            accessSecret = configuration.getString(ACCESS_SECRET);
            consumerKey = configuration.getString(CONSUMER_KEY);
            consumerSecret = configuration.getString(CONSUMER_SECRET);
            refreshInterval = configuration.getMilliseconds(REFRESH_INTERVAL, 30000L);

            twitter4j.Twitter twitter;
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(consumerKey)
                    .setOAuthConsumerSecret(consumerSecret)
                    .setOAuthAccessToken(accessToken)
                    .setOAuthAccessTokenSecret(accessSecret);
            twitter = new TwitterFactory(cb.build()).getInstance();

            Query q = new Query(mensaje); // Search for tweets that contains this term
            q.setCount(1); // How many tweets, max, to retrieve
            q.resultType(Query.ResultType.recent); // Get all tweets
            QueryResult res = twitter.search(q);

            if(res.getTweets().size() == 0) {
                resultado = twitter.updateStatus(mensaje);
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            System.out.println("ERRRORR");
        }

        return resultado;
    }
}
