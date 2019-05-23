package de.tum.in.ase.eist.applicationlayer;

import de.tum.in.ase.eist.client.ChatClient;
import de.tum.in.ase.eist.networklayer.NetworkLayerInterface;

public class Application implements ApplicationLayerInterface {

	// TODO: Part 2: Replace with a reference to the presentation layer
	private NetworkLayerInterface networkLayer;
	private final ChatClient chatClient;

	public Application(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@Override
	public void start() {
		// TODO: Part 2: Replace with method invocation of the the presentation layer
		networkLayer.openConnection();
	}

	@Override
	public void stop() {
		// TODO: Part 2: Replace with method invocation of the the presentation layer
		networkLayer.closeConnection();
	}

	@Override
	public void sendMessage(String message) {
		// TODO: Part 2: Replace with method invocation of the the presentation layer
		networkLayer.sendMessage(message);
	}

	@Override
	public void receiveMessage(String message) {
		// TODO: Part 1: Notify the ChatClient to handle that a new message was received.
	}

	// TODO: Part 2: Replace the reference to network layer with setter and getter for presentation layer.
	@Override
	public void setNetworkLayer(NetworkLayerInterface networkLayer) {
		this.networkLayer = networkLayer;
	}

	@Override
	public NetworkLayerInterface getNetworkLayer() {
		return networkLayer;
	}

}
