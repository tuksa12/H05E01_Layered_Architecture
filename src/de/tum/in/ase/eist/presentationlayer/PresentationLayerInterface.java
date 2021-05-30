package de.tum.in.ase.eist.presentationlayer;

import de.tum.in.ase.eist.applicationlayer.ApplicationLayerInterface;
import de.tum.in.ase.eist.networklayer.NetworkLayerInterface;

public interface PresentationLayerInterface {

    void start();

    void stop();

    void sendMessage(String message);

    void receiveMessage(String message);

    ApplicationLayerInterface getApplicationLayer();

    void setApplicationLayer(ApplicationLayerInterface applicationLayer);

    NetworkLayerInterface getNetworkLayer();

    void setNetworkLayer(NetworkLayerInterface networkLayer);


	// TODO: Part 2: Add setters and getters for neighboring layers. Add other methods according to UML model.

}
