package naree.util;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordUtil {
	public static String encryptPassword(String data) throws Exception {

	      if (data == null) {
	          return "";
	      }
	      byte[] plainText = null; // 평문
	      byte[] hashValue = null; // 해쉬값
	      plainText = data.getBytes();

	      MessageDigest md = MessageDigest.getInstance("SHA-256");
	      hashValue = md.digest(plainText);
	      
	      return new String(Base64.getEncoder().encode(hashValue));
	   }
}
