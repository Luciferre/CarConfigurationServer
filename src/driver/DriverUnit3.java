package driver;

import adapter.BuildAuto;

public class DriverUnit3 {
	public static void main(String[] args) {
		DriverUnit3 driver = new DriverUnit3();
		driver.testEditOptions();
	}

	// test multithread editoption
	public void testEditOptions() {
		BuildAuto buildAuto = new BuildAuto();
		buildAuto.buildAuto("Car1.txt");
		System.out.println("The original automobile:");
		buildAuto.printAuto("Focus Wagon ZTW");

		String[] edit0 = { "Power Moonroof", "Selected", "100" };
		String[] edit1 = { "Power Moonroof", "Selected", "200" };
		// create two threads to edit option price
		buildAuto.editAuto(1, 2, edit0, "Focus Wagon ZTW");
		buildAuto.editAuto(2, 2, edit1, "Focus Wagon ZTW");

	}

}
