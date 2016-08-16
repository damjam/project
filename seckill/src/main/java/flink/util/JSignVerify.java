package flink.util;

//���б���������Ҫ����JCE��Bouncy Castle
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;

public class JSignVerify {

	PrivateKey priKey;
	PublicKey pubKey;

	// ����128λ���������ɹ�Կ
	public void ReadRawPublicKeyFromBigInt(String base16pubkey)
			throws Exception {
		byte e[] = { 0, 1, 0, 1 };

		byte[] bigint = Base16.hexStrToBytes("00" + base16pubkey);
		try {
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec pubkf = new RSAPublicKeySpec(
					new BigInteger(bigint), new BigInteger(e));
			pubKey = keyf.generatePublic(pubkf);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// ��X509֤���ļ���ȡ��Կ
	public void ReadX509Certificate(String file) throws Exception {
		FileInputStream is = new FileInputStream(file);

		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		java.security.cert.Certificate cert = cf.generateCertificate(is);

		pubKey = cert.getPublicKey();

	}

	// ��ȡX509��ʽ��Կ
	public void ReadX509PublicKey(String certfile) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(certfile));
		String s = br.readLine();
		String str = "";
		s = br.readLine();
		while (s.charAt(0) != '-') {
			str += s + "\r";
			s = br.readLine();
		}

		// ����ת��������BASE64����
		BASE64Decoder base64decoder = new BASE64Decoder();
		byte[] b = base64decoder.decodeBuffer(str);

		// ���ɹ�Կ
		KeyFactory kf = KeyFactory.getInstance("RSA");
		// KeyFactory kf = KeyFactory.getInstance("RSA", new
		// org.bouncycastle.jce.provider.BouncyCastleProvider());
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b);

		pubKey = kf.generatePublic(keySpec);

	}

	// ����˽Կ
	public byte[] RSADecrypt(byte[] buf) throws Exception {
		Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

		rsaCipher.init(Cipher.DECRYPT_MODE, priKey);
		rsaCipher.update(buf);
		return rsaCipher.doFinal();

	}

	// ��Կ����
	public byte[] RSAEncrypt(byte[] buf) throws Exception {
		Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		// Cipher rsaCipher=Cipher.getInstance("RSA");
		rsaCipher.init(Cipher.ENCRYPT_MODE, pubKey);
		rsaCipher.update(buf);
		return rsaCipher.doFinal();

	}

	public byte[] SignFile(String inFile) throws Exception {
		byte[] buf = new byte[1024];
		int num;
		FileInputStream fin = new FileInputStream(inFile);
		// String myinfo =
		// "orderId=10dkfadsfksdkssdkd&amount=80&orderTime=20060509"; // Ҫǩ������Ϣ
		// signet.update(myinfo.getBytes("ISO-8859-1"));

		// ��˽Կ����Ϣ��������ǩ��
		java.security.Signature signet = java.security.Signature
				.getInstance("MD5withRSA");
		signet.initSign(priKey);

		while ((num = fin.read(buf, 0, buf.length)) != -1) {
			signet.update(buf, 0, num);
		}

		byte[] signed = signet.sign(); // ����Ϣ������ǩ��
		System.out.println("ǩ���������ļ��ɹ�");
		return signed;
	}

	public byte[] SignFileKMPS(String inFile) throws Exception {
		byte[] buf = new byte[1024];
		int num;
		FileInputStream fin = new FileInputStream(inFile);
		// String myinfo =
		// "orderId=10dkfadsfksdkssdkd&amount=80&orderTime=20060509"; // Ҫǩ������Ϣ
		// signet.update(myinfo.getBytes("ISO-8859-1"));

		// ��˽Կ����Ϣ��������ǩ��
		java.security.Signature signet = java.security.Signature
				.getInstance("MD5withRSA");
		signet.initSign(priKey);

		fin.read(buf, 0, 100);

		while ((num = fin.read(buf, 0, buf.length)) != -1) {
			signet.update(buf, 0, num);
		}

		byte[] signed = signet.sign(); // ����Ϣ������ǩ��
		System.out.println("ǩ���������ļ��ɹ�");
		return signed;
	}

	// ǩ��
	public byte[] SignMemory(byte[] info) throws Exception {
		// String myinfo =
		// "orderId=10dkfadsfksdkssdkd&amount=80&orderTime=20060509"; // Ҫǩ������Ϣ
		// myinfo.getBytes("ISO-8859-1");

		// ��˽Կ����Ϣ��������ǩ��
		java.security.Signature signet = java.security.Signature
				.getInstance("MD5withRSA");
		signet.initSign(priKey);
		signet.update(info);
		byte[] signed = signet.sign(); // ����Ϣ������ǩ��
		System.out.println("ǩ���������ļ��ɹ�");
		return signed;
	}

	// ǩ��
	public byte[] SignMemoryKMPS(byte[] info) throws Exception {
		// String myinfo =
		// "orderId=10dkfadsfksdkssdkd&amount=80&orderTime=20060509"; // Ҫǩ������Ϣ
		// myinfo.getBytes("ISO-8859-1");

		// ��˽Կ����Ϣ��������ǩ��
		java.security.Signature signet = java.security.Signature
				.getInstance("MD5withRSA");
		signet.initSign(priKey);

		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		bs.write(info, 100, info.length - 100);

		signet.update(bs.toByteArray());
		byte[] signed = signet.sign(); // ����Ϣ������ǩ��
		System.out.println("ǩ���������ļ��ɹ�");
		return signed;
	}

	public boolean VerifyFile(String inFile, byte[] pSigBuf, int SigLen)
			throws Exception {
		byte[] buf = new byte[1024];
		int num;
		FileInputStream fin = new FileInputStream(inFile);

		java.security.Signature signetcheck = java.security.Signature
				.getInstance("MD5withRSA");
		signetcheck.initVerify(pubKey);
		while ((num = fin.read(buf, 0, buf.length)) != -1) {
			signetcheck.update(buf, 0, num);
		}

		if (signetcheck.verify(pSigBuf, 0, SigLen)) {
			System.out.println("ǩ������");
			return true;
		} else {
			System.out.println("��ǩ������");
			return false;
		}

	}

	public boolean VerifyFileKMPS(String inFile, byte[] pSigBuf, int SigLen)
			throws Exception {
		byte[] buf = new byte[1024];
		int num;
		FileInputStream fin = new FileInputStream(inFile);

		java.security.Signature signetcheck = java.security.Signature
				.getInstance("MD5withRSA");
		signetcheck.initVerify(pubKey);
		fin.read(buf, 0, 100);
		while ((num = fin.read(buf, 0, buf.length)) != -1) {
			signetcheck.update(buf, 0, num);
		}

		if (signetcheck.verify(pSigBuf, 0, SigLen)) {
			System.out.println("ǩ������");
			return true;
		} else {
			System.out.println("��ǩ������");
			return false;
		}

	}

	// ��֤ǩ��
	public boolean VerifyMemoryMD5(byte[] info, byte[] signed) throws Exception {
		java.security.Signature signetcheck = java.security.Signature
				.getInstance("MD5withRSA");
		signetcheck.initVerify(pubKey);
		signetcheck.update(info);
		if (signetcheck.verify(signed)) {
			System.out.println("ǩ������");
			return true;
		} else {
			System.out.println("��ǩ������");
			return true;
		}
	}

	// ��֤ǩ��
	public boolean VerifyMemoryMD5KMPS(byte[] info, byte[] signed)
			throws Exception {
		java.security.Signature signetcheck = java.security.Signature
				.getInstance("MD5withRSA");
		signetcheck.initVerify(pubKey);
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		bs.write(info, 100, info.length - 100);

		signetcheck.update(bs.toByteArray());
		if (signetcheck.verify(signed)) {
			System.out.println("ǩ������");
			return true;
		} else {
			System.out.println("��ǩ������");
			return true;
		}
	}

	public boolean VerifyMemorySHA1(byte[] info, byte[] signed)
			throws Exception {
		java.security.Signature signetcheck = java.security.Signature
				.getInstance("SHA1withRSA");
		signetcheck.initVerify(pubKey);
		signetcheck.update(info);
		if (signetcheck.verify(signed)) {
			// System.out.println("ǩ������");
			return true;
		} else {
			// System.out.println("��ǩ������");
			return false;
		}
	}

}
