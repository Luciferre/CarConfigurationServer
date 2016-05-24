package server;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import adapter.BuildAuto;
import exception.AutoException;
import model.Automobile;

//This class call the build auto, so the server class only interacts with BuildCarModelOptions instead of interacting with BuildAuto
public class BuildCarModelOptions implements IAutoServer {

	private static BuildAuto buildAuto;

	// constructor
	public BuildCarModelOptions() {
		buildAuto = new BuildAuto();
	}

	// build auto with .Properties file
	public boolean buildAutoFromFiles(Object object, boolean fileType) {
		return buildAuto.buildAutoFromFiles(object, fileType);

	}

	//get automobiles list
	public ArrayList<String> getAutoList() {
		return buildAuto.getAutoList();
	}

	//get selected auto
	public Automobile getSelectedAuto(String modelName) {
		return buildAuto.getSelectedAuto(modelName);
	}

}
