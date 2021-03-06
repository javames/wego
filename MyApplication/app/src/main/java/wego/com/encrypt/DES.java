package wego.com.encrypt;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.SecureRandom;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//import static com.alipay.sdk.cons.MiniDefine.s;

/**
 * 加解密 工具类
 *
 * @author sy
 */
public class DES {

    /**
     * 向量
     */
    public static final byte[] iv = {54, 51, 54, 100, 56, 50, 98, 54};

    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

    public final static String ALGORITHM_3DES = "DESede/CBC/PKCS7Padding";
    public final static String ECB_3DES = "DESede/ECB/PKCS7Padding";
    public final static String SECRET_KEY = "636d82b614235f1bcfd08969";
    //随机生成的密钥
    private static String md5SecrtKey;

    /**
     * @param encryptString
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static String encryptDES(String encryptString) throws Exception {
        Key deskey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM_3DES);
        Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
        IvParameterSpec ips = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(encryptString.getBytes());

        return Base64.encode(bOut);
    }

    /**
     * 没有向量的3DES加密
     *
     * @param encryptString
     * @return
     * @throws Exception
     */
    public static String encryptDesNoIps(String encryptString) throws Exception {
        SecureRandom sr = new SecureRandom();
        Key deskey = new SecretKeySpec(SECRET_KEY.getBytes(), ECB_3DES);
        Cipher cipher = Cipher.getInstance(ECB_3DES);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, sr);
        byte[] bOut = cipher.doFinal(encryptString.getBytes());

        return Base64.encode(bOut);
    }

    //随机生成密钥的DES加密
    public static String encryptDesRandom(String encryptString) throws Exception {
        //生成随机数
        String secrtKey = UUID.randomUUID().toString();
        //MD5
        md5SecrtKey = MD5Encrypt.get32Md5(secrtKey);
        //截取24位当做密钥
        md5SecrtKey = md5SecrtKey.substring(0, 24);
        SecureRandom sr = new SecureRandom();
        Key deskey = new SecretKeySpec(md5SecrtKey.getBytes(), ECB_3DES);
        Cipher cipher = Cipher.getInstance(ECB_3DES);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, sr);
        byte[] bOut = cipher.doFinal(encryptString.getBytes());

        return Base64.encode(bOut);

    }

    public static String getDESecrtKey() {
        return md5SecrtKey;
    }

    /**
     * @param decryptString
     * @param decryptKey
     * @return
     * @throws Exception
     */
    public static String decryptDES(String decryptString, String decryptKey)
            throws Exception {
        try {
            byte[] data = Base64.decode(decryptString);
            Key deskey = new SecretKeySpec(decryptKey.getBytes(),
                    ALGORITHM_3DES);
            Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
            IvParameterSpec ips = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
            byte decryptedData[] = cipher.doFinal(data);
            return new String(decryptedData);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "";
        } finally {

        }
    }

    /**
     * 不需要转换向量的3DES解密
     *
     * @param decryptString 解密字符串
     * @param decryptKey    解密秘药
     * @return
     */
    public static String decrypt3DesNOIps(String decryptString,
                                          String decryptKey) {
        try {
            if (TextUtils.isEmpty(decryptKey)) {
                decryptKey = SECRET_KEY;
            }
            SecureRandom sr = new SecureRandom();
            byte[] data = Base64.decode(decryptString);
            Key deskey = new SecretKeySpec(decryptKey.getBytes(), ECB_3DES);
            Cipher cipher = Cipher.getInstance(ECB_3DES);
            cipher.init(Cipher.DECRYPT_MODE, deskey, sr);
            byte decryptedData[] = cipher.doFinal(data);
            return new String(decryptedData);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "";
        } finally {
        }
    }

    /**
     * @param decryptString
     * @param decryptKey
     * @return
     * @throws Exception
     */
    public static InputStream decryptDESToInputStream(String decryptString,
                                                      String decryptKey) throws Exception {
        byte[] data = Base64.decode(decryptString);
        Key deskey = new SecretKeySpec(decryptKey.getBytes(), ALGORITHM_3DES);
        Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
        IvParameterSpec ips = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte decryptedData[] = cipher.doFinal(data);
        ByteArrayInputStream is = new ByteArrayInputStream(decryptedData);
        return is;
    }
}
