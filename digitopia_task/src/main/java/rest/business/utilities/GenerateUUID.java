package rest.business.utilities;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class GenerateUUID {
	
	public static long generateId() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//UUID uuid = UUID.randomUUID();
		
		MessageDigest salt = MessageDigest.getInstance("SHA-256");
		salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
		String digest = new BigInteger(1, salt.digest()).toString(16);
		
		return Long.parseLong(digest);
	}

}



