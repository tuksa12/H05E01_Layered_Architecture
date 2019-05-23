package de.tum.in.ase.eist.networklayer;

import de.tum.in.ase.eist.applicationlayer.ApplicationLayerInterface;

public interface NetworkLayerInterface {

	void sendMessage(String message);

	void receiveMessage(String message);

	void openConnection();

	void closeConnection();

	// TODO: Part 2: Replace with setter and getter for presentation layer
	void setApplicationLayer(ApplicationLayerInterface applicationLayer);

	ApplicationLayerInterface getApplicationLayer();
}
