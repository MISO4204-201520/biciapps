package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Created by Omar on 10/10/2015.
 */
public class Utilities {

    public static String encryptPass(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHexa(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("ERROR - Utilities - encryptPass");
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            System.out.println("ERROR - Utilities - encryptPass");
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHexa(byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }

        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
