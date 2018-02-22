import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Base64Utils;

public class A {
	private static final String KEY_ALGORITHM = "RSA";
	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";
	//private static final String PUBLIC_KEY = "publicKey";
	//private static final String PRIVATE_KEY = "privateKey";
	
	private static final String PUBLIC_KEY_STR = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq9z1VumzqN0ExM/2cyeWwCDTWtn51mw7bffgyJGot8wQMKAg9F5riOWuz6LKl0Q0SY6sI0C847WxzwGpznGQ4abDptu6dk+nqYhj2VOOjC86OkfMTLC6DNSQFzZ2Hu2DPQ04+WmZIki0OKE2WacTjFkY9yVbo20d6klBxKr/KP8CQLs+651oxEYLzEq2a4ftRxWFNusSvd8ZjS9QCbh6obUNMNbNqtB2VRTEj2jHt64QBBtwkrnLL4TwtfI7YdNoLi0XyNVmYPKMKOnNuXsY/h7BXWKFS6g5iNAl00iZewk9gGNVzovUsdwcM4Ao/WMZA2r3X3boVkKN9P1SmeA89QIDAQAB";
	private static final String PRIVATE_KEY_STR = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCr3PVW6bOo3QTEz/ZzJ5bAINNa2fnWbDtt9+DIkai3zBAwoCD0XmuI5a7PosqXRDRJjqwjQLzjtbHPAanOcZDhpsOm27p2T6epiGPZU46MLzo6R8xMsLoM1JAXNnYe7YM9DTj5aZkiSLQ4oTZZpxOMWRj3JVujbR3qSUHEqv8o/wJAuz7rnWjERgvMSrZrh+1HFYU26xK93xmNL1AJuHqhtQ0w1s2q0HZVFMSPaMe3rhAEG3CSucsvhPC18jth02guLRfI1WZg8owo6c25exj+HsFdYoVLqDmI0CXTSJl7CT2AY1XOi9Sx3BwzgCj9YxkDavdfduhWQo30/VKZ4Dz1AgMBAAECggEAO0dqhED1zB/3/h6xJgUYtUpdPHAFAdWxJfwi12uOY18KkosY8tECjGSUyF+hO022L0z7yOkSfgR0wS8Ur/VyALMxtwMBLL/K1+oIbrSobWf40JNlP2qRsc07qhYJ5h8Mbml6JmUrAa1MJGBOy9uaHYW5271R/2uqrt33xmsoua2wgd38Rf7D7dcQAkWIA0h8vicJQFNm2dEpqjxqaNHNJTpQpQe7bwg1jOAkSyHMwmwL9D3E1pgxWTDbe9JFa5gI2r/V4KZG7XLkzNBX6rVRqI+ip6GMFc8gLdqCRj764V5l5MArQOmALRZilRJTF0tGmtlpGmtXb76/1obwG0OrAQKBgQD9YrHqHdlCGY60m8TD6mi0JWePxh1iDaKsgA/rA5v7PLyEtKtFVrvpcI7m8ItijJyYcoP+jnTXFwEs6LCLMZQsXnNorwmVXVqz8LPIZN2G5vj3XLcqZqAU8uHG5Yy9zlkrqhl+pb8mgAcgvVA+KVnwPhlD2bS+Eko//KjMtDxswQKBgQCtou0S8C6myk0usvr0BiZ1oibqbb/qxYEMsbrD7JI7Y00Ev+OLyQOiueY9KndWDuqp4dINDQMIIA4F9E7G8RgUCm0KhXuvoZm+j4NlUe3H7jRlUiwt6HMVl3mQZhHS/udSLwHZDLpSXY/+mtXwW/EsmOFEld5pPhFKxfWc6xb5NQKBgBseQvhRdX0TiyhdO3PUVJxjHHBQvbhOyUg49HssibcbyvlQ9LuZCsdzGngw6loDvoa6StIloO+cMK4DwVFEXx1YhnV/tkDcGucDLdqWoPbdJWwj6h6JmTxkujE1RBjUSsrXi574UjrmnNyuJjZg6pVBsCaI2EU3Y+bxPYebCpSBAoGBAKJD2ONS23X5JknW7xiuyrAxucZoi+pdai0vxoakBUBEm0wt+vq9/ozKx3QvxFgDFp9trnuNxmCIfDJqTlgyJLtNxvsHJUBLd78SpMtB1kF5xTNOqIS6bxoldXBRQqT4m7o8vbBV6aVhVFit3/KAKZDwycvrdm7JlyVWfCKJu+1lAoGASQnIIsrUvg4bkdJJVFCVOzqb+NRSDE2Un2GgY9XLmFWqLupLitdbbRMqOuiSeH8856MdRD9PBYVG6PtvEPTKLElt8cVms5VW13lnOj3iteXObtm0ozWvPi5G9v6SFdq59daNe0Q0XA/7TyoRS3hejgt7IsYRuOGr5EYoF1ogrbE=";

	public static void main(String[] args) throws Exception {
		// genKey();
		SHA1withRSA();
	}

	public static void SHA1withRSA() throws Exception {
		
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(PRIVATE_KEY_STR));
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyf.generatePrivate(priPKCS8);
		
		Signature sign = Signature.getInstance("SHA1withRSA");
		sign.initSign(privateKey);
		byte[] bs = "sssssss".getBytes("UTF-8");
		sign.update(bs);
		byte[] signature = sign.sign();
		String zzzz = Base64Utils.encodeToString(signature);
		System.out.println(zzzz);
		
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64Utils.decodeFromString(PUBLIC_KEY_STR);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
		Signature signature2 = Signature.getInstance(SIGN_ALGORITHMS);
		
		signature2.initVerify(pubKey);
		signature2.update(bs);
		
		boolean bverify = signature2.verify(Base64Utils.decodeFromString(zzzz));
		
		System.out.println(bverify);
	}

	public static Map<String, String> genKey() throws NoSuchAlgorithmException {
		Map<String, String> keyMap = new HashMap<String, String>();
		KeyPairGenerator keygen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		SecureRandom random = new SecureRandom();
		// random.setSeed(keyInfo.getBytes());
		// 初始加密，512位已被破解，用1024位,最好用2048位
		keygen.initialize(1024, random);
		// 取得密钥对
		KeyPair kp = keygen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
		// String privateKeyString = Base64.encode(privateKey.getEncoded());
		RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
		// String publicKeyString = Base64.encode(publicKey.getEncoded());
		// keyMap.put(PUBLIC_KEY, publicKeyString);
		// keyMap.put(PRIVATE_KEY, privateKeyString);

		System.out.println(Base64Utils.encodeToString(privateKey.getEncoded()));
		System.out.println(Base64Utils.encodeToString(publicKey.getEncoded()));
		return keyMap;
	}

	// public static RSAPublicKey getPublicKey(String publicKey) throws
	// Exception {
	// byte[] keyBytes = LBase64.decode(publicKey);
	// X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
	// KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	// return (RSAPublicKey) keyFactory.generatePublic(spec);
	// }
	//
	// public static RSAPrivateKey getPrivateKey(String privateKey) throws
	// Exception {
	// byte[] keyBytes = LBase64.decode(privateKey);
	// PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
	// KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
	// return (RSAPrivateKey) keyFactory.generatePrivate(spec);
	// }
}
