package adapter;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import database.CreateOperation;
import database.DeleteOperation;
import database.UpdateOperation;
import exception.AutoException;
import model.Automobile;
import scale.EditOptions;
import util.FileIO;

//Abstract class to implement interface methods
public abstract class ProxyAutomobile {
	private static LinkedHashMap<String, Automobile> autoMobileList = new LinkedHashMap<>();

	// Build auto from object that uploaded from client
	public boolean buildAutoFromFiles(Object object, boolean fileType) {
		Automobile automobile = null;
		if (fileType) {// will not reach because text file will not be uploaded
			try {
				automobile = new FileIO().buildAutoObjectFromText(object);
			} catch (AutoException e) {
				fix(e, e.getErrorNo());
			}
		} else {
			automobile = new FileIO().buildAutoFromProperties(object);
		}

		if (automobile != null) {
			autoMobileList.put(automobile.getModel(), automobile);
			return true;
		}
		return false;
	}

	public void buildAuto(String fileName) {
		try {
			Automobile automobile = new FileIO().buildAutoObject(fileName);
			if (automobile != null) {
				autoMobileList.put(automobile.getModel(), automobile);
				new CreateOperation().createOperation(automobile);
			}
		} catch (AutoException e) {
			fix(e, e.getErrorNo());
		}
	}

	// This function searches and prints the properties of a given Automobile
	public void printAuto(String modelName) {
		Automobile automobile = autoMobileList.get(modelName);
		automobile.print();
	}

	// This function searches the Model for a given OptionSet and sets the name
	// of OptionSet to newName.
	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		Automobile automobile = autoMobileList.get(modelName);
		automobile.updateOptionSetName(optionSetName, newName);
		new UpdateOperation().updateOptionSetName(modelName, optionSetName,newName);

	}

	// This function searches the Model for a given OptionSet and Option name,
	// and sets the price to newPrice.
	public void updateOptionPrice(String modelname, String optionSetName, String optionName, float newprice) {
		Automobile automobile = autoMobileList.get(modelname);
		int setIndex = automobile.findOptionSet(optionSetName);
		automobile.updateOptionPrice(setIndex, optionName, newprice);
		new UpdateOperation().updateOptionPrice(modelname, optionSetName, optionName, newprice);
	};

	// Fix autoexceptions
	public void fix(AutoException e, int errNo) {
		e.fix(errNo);
	}

	// This function edits automobile's optionset and option
	public void editAuto(int threadID, int editOptionID, String[] newOptions, String autoName) {
		Automobile autoMobile = null;
		autoMobile = autoMobileList.get(autoName);
		EditOptions editOption = new EditOptions(threadID, editOptionID, newOptions, autoMobile);
		editOption.start();

	}

	// Provide a list of available models to the client.
	public ArrayList<String> getAutoList() {
		ArrayList<String> list = new ArrayList<>();
		Set<String> keyset = autoMobileList.keySet();
		for (String str : keyset) {
			list.add(str);
		}
		return list;
	}

	// Send the object to the client, upon selection of an Automobile.
	public Automobile getSelectedAuto(String modelName) {
		return autoMobileList.get(modelName);
	}

	public void deleteAuto(String autoName){
		autoMobileList.remove(autoName);
		new DeleteOperation().deleteOption(autoName);
	}
	
}
