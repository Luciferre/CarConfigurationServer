package driver;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

public class DriverUnit1 {
	public static void main(String[] args) throws AutoException {
		FileIO fileIO = new FileIO();
		// Build Automobile Object from a file.
		Automobile FordZTW = fileIO.buildAutoObject("Car1.txt");
		// Print attributes before serialization
		System.out.println("The original automobile:");
		FordZTW.print();
		// Serialize the object
		fileIO.serializeAuto(FordZTW);
		// Deserialize the object and read it into memory.
		Automobile newFordZTW = fileIO.deserializeAuto("auto.ser");
		newFordZTW.updateOptionSetName("Transmission", "Trans");
		int index = newFordZTW.findOptionSet("Color");
		newFordZTW.deleteOptionSet(index);// Print new attributes.
		System.out.println("The updated automobile:");
		newFordZTW.print();

	}
}
