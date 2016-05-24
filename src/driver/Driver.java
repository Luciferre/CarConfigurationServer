package driver;

import adapter.BuildAuto;
import adapter.ICreateAuto;
import adapter.IUpdateAuto;
import util.DatabaseIO;

public class Driver {
	
	public static void main(String[] args) {
		
		//create database
		DatabaseIO databaseIO = new DatabaseIO();
		databaseIO.createDB();

		//test create operation
		System.out.println("Add Models:");
		ICreateAuto createAuto = new BuildAuto();
		createAuto.buildAuto("Car1.txt");
		createAuto.buildAuto("Car2.txt");
		databaseIO.printAutomobile();

		//test delete operation
		System.out.println("\nDelete Model Accord:");
		BuildAuto buildAuto = new BuildAuto();
		buildAuto.deleteAuto("Accord");
		databaseIO.printAutomobile();

		//test update operation
		IUpdateAuto updateAuto = new BuildAuto();
		System.out.println("\nUpdate OptionSet Name: (Transmission -> Trans)");
		System.out.println("Before update:");
		databaseIO.printOptionSet();
		System.out.println("After update:");
		updateAuto.updateOptionSetName("Focus Wagon ZTW", "Transmission", "Trans");
		databaseIO.printOptionSet();

		System.out.println("\nUpdate Option Price: (Fort Knox Gold Clearcoat Metallic: 0 -> 1000)");
		System.out.println("Before update:");
		databaseIO.printOptions();
		System.out.println("After update:");
		updateAuto.updateOptionPrice("Focus Wagon ZTW", "Color", "Fort Knox Gold Clearcoat Metallic", 1000);
		databaseIO.printOptions();
		databaseIO.closeConnection();

	}
}
