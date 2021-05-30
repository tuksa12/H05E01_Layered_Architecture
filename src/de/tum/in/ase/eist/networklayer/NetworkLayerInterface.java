package de.tum.in.ase.eist.networklayer;

import de.tum.in.ase.eist.presentationlayer.PresentationLayerInterface;

public interface NetworkLayerInterface {

	void sendMessage(String message);

	void receiveMessage(String message);

	void openConnection();

	void closeConnection();

	// TODO: Part 2: Replace with setter and getter for presentation layer
	void setPresentationLayer(PresentationLayerInterface presentationLayer);

	PresentationLayerInterface getPresentationLayer();
}
