package test.java.security;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class SecretKeyTest {
	public static void main(String[] args) throws Exception {
		String s = "要加密的字符串";
		String keyFileName = "secretKey.key";
		Key key = generateKey();
		writeFile(key, keyFileName);
		String encode = encode(s, key);
		System.out.println("加密后:" + encode);
		Key key2 = readKey(keyFileName);
		String decode = decode(encode, key2);
		System.out.println("解密后:" + decode);
	}

	private static String decode(String s, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] ss = cipher.doFinal(s.getBytes());
		return new String(ss);
	}

	private static String encode(String s, Key key)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] update = cipher.update(s.getBytes());
		byte[] doFinal = cipher.doFinal();
		return new String(doFinal);
	}

	// 从文件读取key
	private static Key readKey(String fileName) throws IOException,
			ClassNotFoundException {
		InputStream ins = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(ins);
		Key key = (Key) ois.readObject();
		return key;
	}

	// 生成密钥密钥
	private static Key generateKey() throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IOException {
		// SecretKeyFactory.getInstance("AES").generateSecret(keySpec);
		Key key = KeyGenerator.getInstance("AES").generateKey();
		return key;
	}

	// 生成密钥文件
	private static void writeFile(Key key, String fileName) throws IOException {
		FileOutputStream os = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(key);
		oos.close();
		os.close();
	}
}
