package com.nitro.security.hash;

import java.security.GeneralSecurityException;
import java.util.Properties;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.crypto.random.CryptoRandom;
import org.apache.commons.crypto.random.CryptoRandomFactory;
import org.apache.commons.crypto.random.CryptoRandomFactory.RandomProvider;

public class HashGenerator {

	public static void main(String[] args) throws GeneralSecurityException {
		String passwd = random();
		System.out.println("Password is " + passwd);
		//String passwd = "8c6bf06d78ed7f7d7d80508596d9be32";
		String hash = hash(passwd);
		System.out.println("Output hash is " + hash);
	}

	private static String hash(String passwd) {
		String hash = DigestUtils.sha256Hex(passwd.getBytes());
		return hash;
	}
	
	private static String random() throws GeneralSecurityException {
		Properties props = new Properties();
		props.setProperty(CryptoRandomFactory.CLASSES_KEY, RandomProvider.OPENSSL.getClassName());
		CryptoRandom random = CryptoRandomFactory.getCryptoRandom(props);
		byte[] passwd = new byte[16];
		random.nextBytes(passwd);
		return Hex.encodeHexString(passwd);
	}
}
