package de.tum.in.ase.eist.applicationlayer;

import de.tum.in.ase.eist.presentationlayer.PresentationLayerInterface;

public interface ApplicationLayerInterface {

	void start();

	void stop();

	void sendMessage(String message);

	void receiveMessage(String message);

	// TODO: Part 2: Replace with setters and getters for presentation layer
	void setPresentationLayer(PresentationLayerInterface presentationLayer);

	PresentationLayerInterface getPresentationLayer();
}
