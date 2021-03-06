package fortifytest;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
	private static final String MD5_ALGORITHM = "MD5";
	private static final String SHA256_ALGORITHM = "SHA-256";
	private static final String UTF8_CHARSET = "UTF-8";
	private static final String SALT = "4TK9IUD2P0JSX6WTPVMU";
	private static final int HEX = 16;
	public static final String md5(String base) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		return hash( MessageDigest.getInstance(MD5_ALGORITHM), base );
	}
	
	public static final String sha256(String base) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		return hash( MessageDigest.getInstance(SHA256_ALGORITHM), base );
	}
	
	private static final String hash(MessageDigest md, String base) throws UnsupportedEncodingException{
		byte[] toBeHashed = (base + SALT).getBytes(UTF8_CHARSET);
		byte[] bytes =  md.digest(toBeHashed);
		BigInteger bigInt = new BigInteger(1,bytes);
		return bigInt.toString(HEX);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(md5("My String"));
			System.out.println(sha256("My String"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
