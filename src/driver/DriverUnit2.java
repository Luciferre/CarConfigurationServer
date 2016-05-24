package driver;

import adapter.BuildAuto;
import exception.AutoException;
import util.FileIO;

public class DriverUnit2 {
	public static void main(String[] args) throws AutoException {
		BuildAuto buildAuto = new BuildAuto();
		buildAuto.buildAuto("Car.txt");
		System.out.println("Default automobile:");
		buildAuto.printAuto("Focus Wagon ZTW");
	}
}
