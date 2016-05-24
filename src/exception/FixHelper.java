package exception;

import adapter.BuildAuto;
import model.Automobile;
import util.FileIO;

//This class aims to fix different exceptions
public class FixHelper {

	public void fixMissingPrice() {
		System.out.println("Base Price Missing! Please check the input file.\n");
		// System.exit(-1);
	}

	public void fixMissingOptionSet() {
		System.out.println("Option Set Missing! Please check the input file.\n");
		// System.exit(-1);
	}

	public void fixMissingOptionPrice() {
		System.out.println("Option Price Missing! Please check the input file.\n");
		// System.exit(-1);
	}

	public void fixWrongFileName() {
		System.out.println("Cannot find the file! Read in the default file.\n");
		new BuildAuto().buildAuto("Car1.txt");
	}

	public void fixMissingOption() {
		System.out.println("Option Missing! Please check the input file.\n");
		// System.exit(-1);
	}

}
