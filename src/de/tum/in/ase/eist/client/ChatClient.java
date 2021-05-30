package de.tum.in.ase.eist.client;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import de.tum.in.ase.eist.applicationlayer.Application;
import de.tum.in.ase.eist.applicationlayer.ApplicationLayerInterface;
import de.tum.in.ase.eist.networklayer.NetworkLayerInterface;
import de.tum.in.ase.eist.networklayer.TcpNetworkLayer;

public class ChatClient {

	private static final String SERVER_HOST = "131.159.38.124";
	private static final int SERVER_PORT = 1337;
	private static final String LOGOUT_MESSAGE = ".logout";

	private final AtomicBoolean running = new AtomicBoolean(false);
	private Thread waitForUserInputThread;

	private String lastMessgeReceived;

	private final ApplicationLayerInterface applicationLayer;

	public static void main(String[] args) {
		ChatClient chatClient = new ChatClient(SERVER_HOST, SERVER_PORT);
		chatClient.start();
	}

	public ChatClient(String serverHost, int serverPort) {
		// TODO: Part 2: Instantiate and configure the layers
		// TODO: Part 2: Add and use a CaesarEncryption implementation for the presentation layer.
		// TODO: Part 3: Replace the CaesarEncryption with an AesEncryption implementation in the presentation layer.
		applicationLayer = new Application(this);

		NetworkLayerInterface networkLayer = new TcpNetworkLayer(serverHost, serverPort);

		//applicationLayer.setNetworkLayer(networkLayer);
		//networkLayer.setApplicationLayer(applicationLayer);
	}

	public void start() {
		applicationLayer.start();
		waitForUserInputThread = new Thread(() -> {
			running.set(true);
			waitForUserInput();
		});
		waitForUserInputThread.start();
		System.out.println("ChatClient started.");
	}

	private void waitForUserInput() {
		try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
			while (!Thread.interrupted()) {
				String outgoingMessage = scanner.nextLine();
				if (LOGOUT_MESSAGE.equals(outgoingMessage)) {
					shutDown();
					return;
				}
				sendMessage(outgoingMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void receiveMessage(String message) {
		lastMessgeReceived = message;
		printMessage(lastMessgeReceived);
		// Done: Part 1: Print the received message by invoking the printMessage() method
	}

	public void sendMessage(String outgoingMessage) {
		applicationLayer.sendMessage(outgoingMessage);
		// : Part 1: Use the application layer to send the message.
	}

	private void shutDown() {
		applicationLayer.stop();
		running.set(false);
		waitForUserInputThread.interrupt();
	}

	private static void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * Added for tests
	 */
	public ApplicationLayerInterface getApplicationLayer() {
		return applicationLayer;
	}

	/**
	 * Added for tests
	 */
	public String getLastMessgeReceived() {
		return lastMessgeReceived;
	}
}
