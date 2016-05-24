package adapter;

import java.io.File;

//Interface of creating and print automobile
public interface ICreateAuto {
	
	// Given a text file name can be written to build an instance of Automobile.
	public boolean buildAutoFromFiles(Object object, boolean fileType);//true for text, false for properties

	// This function searches and prints the properties of a given Automobile
	public void printAuto(String Modelname);
	
	public void buildAuto(String fileName);
}
