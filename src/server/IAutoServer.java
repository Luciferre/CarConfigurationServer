package server;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import model.Automobile;

//This class is a interface of server
public interface IAutoServer {

	// Accept properties object from client socket over an ObjectStream and
	// create an Automobile. Add that created Automobile to the LinkedHashMap.
	public boolean buildAutoFromFiles(Object object, boolean fileType);

	// Provide a list of available models to the client.
	public ArrayList<String> getAutoList();

	// Send the object to the client, upon selection of an Automobile.
	public Automobile getSelectedAuto(String modelName);
}
