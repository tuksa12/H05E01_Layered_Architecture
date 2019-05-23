package de.tum.in.ase.eist.presentationlayer;

public abstract class ChatEncryption {

	public abstract String decrypt(String message);

	public abstract String encrypt(String message);

	protected boolean isServerMessage(String message) {
		// Test if its the welcome message
		if (message.startsWith("Welcome to chat! Your client ID is")) {
			return true;
		}
		// Test if the client logged out
		return message.matches("Client [0-9]+ logged out.");
	}

	protected String findEncryptedMessage(String message) {
		String encryptedMessage = message;
		if (message.contains(":")) {
			encryptedMessage = message.replaceAll("Client [0-9]+: ", "");
		}
		return encryptedMessage;
	}

	protected String findServerPrefix(String message) {
		if (message.contains(":")) {
			return message.substring(0, message.indexOf(":") + 2);
		}
		return "";
	}
}
