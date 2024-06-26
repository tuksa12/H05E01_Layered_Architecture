package de.tum.in.ase.eist.presentationlayer;

import de.tum.in.ase.eist.applicationlayer.ApplicationLayerInterface;
import de.tum.in.ase.eist.networklayer.NetworkLayerInterface;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Code from Stackoverflow.
 * http://stackoverflow.com/questions/15554296/simple-java-aes-encrypt-decrypt-example
 */
public class AesEncryption extends ChatEncryption implements PresentationLayerInterface {

	private static final byte[] INIT_VECTOR = "RandomInitVector".getBytes(StandardCharsets.UTF_8);

	// TODO: Part 3: Conform to the interface PresentationLayerInterface and pass the method calls to the application layer or network layer
	// TODO: Part 3: Add references to ApplicationLayerInterface and NetworkLayerInterface

	private ApplicationLayerInterface applicationLayer;
	private NetworkLayerInterface networkLayer;

	private final byte[] key;

	public AesEncryption(String key) {
		this.key = key.getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public String encrypt(String message) {
		try {
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR);
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (RuntimeException | GeneralSecurityException ex) {
			ex.printStackTrace();
		}

		return "Errors in encryption.";
	}

	@Override
	public String decrypt(String message) {
		// handle special cases that are sent unencrypted
		if (this.isServerMessage(message)) {
			return message;
		}
		String serverPrefix = findServerPrefix(message);
		String encryptedMessage = findEncryptedMessage(message);
		try {
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR);
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
			return serverPrefix + new String(original, StandardCharsets.UTF_8);
		} catch (RuntimeException | GeneralSecurityException ex) {
			ex.printStackTrace();
		}
		return "Errors in decryption.";
	}

	@Override
	public void start() {
		this.getNetworkLayer().openConnection();
	}

	@Override
	public void stop() {
		this.getNetworkLayer().closeConnection();
	}

	@Override
	public void sendMessage(String message) {
		this.getNetworkLayer().sendMessage(encrypt(message));
	}

	@Override
	public void receiveMessage(String message) {
		this.getApplicationLayer().receiveMessage(decrypt(message));
	}

	@Override
	public ApplicationLayerInterface getApplicationLayer() {
		return applicationLayer;
	}

	@Override
	public void setApplicationLayer(ApplicationLayerInterface applicationLayer) {
		this.applicationLayer = applicationLayer;
	}

	@Override
	public NetworkLayerInterface getNetworkLayer() {
		return networkLayer;
	}

	@Override
	public void setNetworkLayer(NetworkLayerInterface networkLayer) {
		this.networkLayer = networkLayer;
	}

	// TODO: Part 3: The send message method must encrypt the message before sending
	// TODO: Part 3: The receive message method must decrypt the message before giving it to upper layer

}
