package com.taomini.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

/**
 * 类功能说明
 * 
 * @author sun
 * @version 1.0.0
 */

public class AESUtil {
	public static final String VIPARA = "0102030405060708";
	public static final String BM = "UTF-8";

	public static String encrypt(String content, String password) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
		SecretKeySpec key = getKey(password);
		// new SecretKeySpec(password.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(content.getBytes(BM));
		String encryptResultStr = parseByte2HexStr(encryptedData);
		return encryptResultStr; // 加密
		
//		 SecretKeySpec skeySpec = getKey(content);
//	     Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//	     IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
//	     cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//	     byte[] encrypted = cipher.doFinal(password.getBytes("UTF-8"));
//	
//	     return Base64.encode(encrypted);
	}

	public static String decrypt(String content, String password) throws Exception {
		content = new String(content.getBytes("UTF-8"));
		byte[] decryptFrom = parseHexStr2Byte(content);
		IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
		SecretKeySpec key = getKey(password);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte[] decryptedData = cipher.doFinal(decryptFrom);

		return new String(decryptedData, BM);
//		if (StringUtils.isEmpty(content) || StringUtils.isEmpty(password)) {
//            return null;
//        }
//        try {
//            SecretKeySpec skeySpec = getKey(content);
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//            byte[] encrypted1 = Base64.decode(password);
//
//            byte[] original = cipher.doFinal(encrypted1);
//            String originalString = new String(original, "UTF-8");
//            return originalString;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
	}

	private static SecretKeySpec getKey(String strKey) throws Exception {
		byte[] arrBTmp = strKey.getBytes();
		byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
		return skeySpec;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	/**
     * 构建健康通微信后台获取openId的请求参数
     * @param url 回调地址
     * @param accountId 公众号Id
     */
    public static String genGetWXOpenIdParam (String url, String accountId) throws Exception {
        String param = "{'accountid':'" + accountId +"','url':'"+ url +"'}";
        String key = "kmcnkle";
//        String p = encrypt(key, param);
        SecretKeySpec skeySpec = getKey(key);
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	    byte[] encrypted = cipher.doFinal(param.getBytes("UTF-8"));
	
	    String p =  Base64.encode(encrypted);
        p = URLEncoder.encode(p, "utf-8");
        return p;
    }
    /**
     * 获取健康通微信后台的返回的数据
     */
    public static JSONObject getWXBackResult (String aesData) throws Exception {
        String key = "kmcnkle";
//        String plainData = decrypt(key, aesData);
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(aesData)) {
        	return null;
        }
        try {
          SecretKeySpec skeySpec = getKey(key);
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
          IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
          cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
          byte[] encrypted1 = Base64.decode(aesData);

          byte[] original = cipher.doFinal(encrypted1);
          String originalString = new String(original, "UTF-8");
          return JSON.parseObject(originalString);
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
//        return JSON.parseObject(plainData);
    }
}
