package utils;

import java.io.*;
import java.util.*;

/**
 * Created by Omar on 11/10/2015.
 */
public class PropertyReader {
    private static Properties prop = null;

    public static void initializePropertyReader(){

        InputStream is = null;
        try {
            prop = new Properties();

            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties");
            if(is == null) {
                System.out.println("No se puedo cargar el archivo mail.properties");
            }
            else {
                prop.load(is);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Object> getAllKeys(){
        Set<Object> keys = prop.keySet();
        return keys;
    }

    public static String getPropertyValue(String key){
        return prop.getProperty(key);
    }

    public static Properties getProperties() {
        return prop;
    }

}
