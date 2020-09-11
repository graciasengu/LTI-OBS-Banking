package banking;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

 
public class Encryption {
 
	final static String code = "Supersecretpasswordorcode!!!";
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey) 
    {
        MessageDigest md = null;
        try {
            key = myKey.getBytes("UTF-8");
            md = MessageDigest.getInstance("SHA-1");
            key = md.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String pwtoencrypt) 
    {
        try
        {
            setKey(code);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                 
            return Base64.encodeBase64String(cipher.doFinal(pwtoencrypt.getBytes("UTF-8")));
            
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
 
    public static String decrypt(String pwtodecrypt) 
    {
        try
        {
            setKey(code);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
    
            
            return new String(cipher.doFinal(Base64.decodeBase64(pwtodecrypt)), "UTF-8");
          
            
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
        }
        return null;
    }
    
}