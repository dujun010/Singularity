package qid.tool;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密解密
 * 
 * 首先，对用户输入的password进行MD5转换，
 * 然后，对用户输入的Data进行des加密，
 * 最后进行base64转换为密文； 
 * 
 * 解密，对用户输入的password进行MD5转换，
 * 然后，对用户输入的Data进行base64解密，
 * 最后进行des解密为明文； 
 * @author djun
 *
 */
public class ToolCrypto {

	private static byte[] keys = { 1, -1, 1, -1, 1, -1, 1, -1 };

	private static String key = "SINGULARITY";

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		ToolCrypto.key = key;
	}

	/**
	 * 
	 * <p>
	 * 对password进行MD5加密
	 * @param source
	 * @return 
	 * @return byte[]    
	 * author: Heweipo
	 */
	private static byte[] getMD5(byte[] source) {
		byte tmp[] = null;
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			tmp = md.digest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}

	/**
	 * 
	 * <p>
	 * 采用JDK内置类进行真正的加密操作
	 * @param byteS
	 * @param password
	 * @return 
	 * @return byte[]    
	 * author: Heweipo
	 */
	private static byte[] encryptByte(byte[] byteS, byte password[]) {
		byte[] byteFina = null;
		try {// 初始化加密/解密工具
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec desKeySpec = new DESKeySpec(password);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(keys);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return byteFina;
	}

	/**
	 * 
	 * <p>
	 * 采用JDK对应的内置类进行解密操作
	 * @param byteS
	 * @param password
	 * @return 
	 * @return byte[]    
	 * author: Heweipo
	 */
	private static byte[] decryptByte(byte[] byteS, byte password[]) {
		byte[] byteFina = null;
		try {// 初始化加密/解密工具
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec desKeySpec = new DESKeySpec(password);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(keys);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return byteFina;
	}

	/**
	 * 
	 * <p>
	 * Des加密strMing，然后base64转换
	 * @param strMing
	 * @param md5key
	 * @return 
	 * @return String    
	 * author: Heweipo
	 */
	private static String encryptStr(String strMing, byte md5key[]) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		try {
			byteMing = strMing.getBytes("utf-8");
			byteMi = encryptByte(byteMing, md5key);
			BASE64Encoder base64Encoder = new BASE64Encoder();
			strMi = base64Encoder.encode(byteMi);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 
	 * <p>
	 * Base64转换strMi,然后进行des解密
	 * @param strMi
	 * @param md5key
	 * @return 
	 * @return String    
	 * author: Heweipo
	 */
	private static String decryptStr(String strMi, byte md5key[]) {
		byte[] byteMing = null;
		String strMing = "";
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byteMing = decoder.decodeBuffer(strMi);
			byteMing = decryptByte(byteMing, md5key);
			strMing = new String(byteMing);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			byteMing = null;
		}
		return strMing;
	}
	
	
	/**
	 * 加密
	 * @author djun
	 * @param data	需加密内容
	 * @return
	 */
	public static String Encryption(String data){
		return Encryption(data, getKey());
	}
	/**
	 * 加密
	 * @author djun
	 * @param data	需加密内容
	 * @param KEY	加密的KEY
	 * @return
	 */
	public static String Encryption(String data,String KEY){
		try {
			return ToolCrypto.encryptStr(data, ToolCrypto.getMD5(KEY.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 解密
	 * @author djun		需解密内容
	 * @param data
	 * @return
	 */
	public static String Decrypt(String data){
		return Decrypt(data, getKey());
	}
	/**
	 * 解密
	 * @author djun
	 * @param data	需解密内容
	 * @param KEY	解密的KEY
	 * @return
	 */
	public static String Decrypt(String data,String KEY){
		try {
			return ToolCrypto.decryptStr(data, ToolCrypto.getMD5(KEY.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		
		System.out.println(ToolCrypto.Encryption("222222"));
		
		System.out.println(ToolCrypto.Decrypt("WA8iAD5Oe2s="));
		
		
		/*String[] keys = {"leagsoft","leagsoftpo","leagsoftiu","leagsoftyy","leagsoftew","leagsoftmm"};
		for(String key : keys){
			String data = "222222";
			try{
				System.out.println(DesCoderUtil.encryptStr(data, DesCoderUtil.getMD5(key.getBytes("utf-8"))));
				System.out.println("--------------");
				System.out.println(DesCoderUtil.decryptStr(DesCoderUtil.encryptStr(data, DesCoderUtil.getMD5(key.getBytes("utf-8"))), 
						DesCoderUtil.getMD5(key.getBytes("utf-8"))));
			}catch(Exception e){
				e.printStackTrace();
			}
		}*/
	}

}