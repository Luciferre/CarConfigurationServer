package scale;

import java.util.ArrayList;

import model.Automobile;

//This class aims to create multithread to edit optionset and options
public class EditOptions extends Thread {

	private int threadId;// The id of thread

	private int editOptionID;// The ID of action of editOption

	private Automobile automobile;// The automobile needed to be edited

	private String[] newOptions;// The information needed to be updated

	public EditOptions(int threadId, int editOptionID, String[] newOptions, Automobile automobile) {
		this.threadId = threadId;
		this.automobile = automobile;
		this.editOptionID = editOptionID;
		this.newOptions = newOptions;
	}

	@Override
	public void run() {
		switch (editOptionID) {
		case 1:
			editOptionSetName();
			break;
		case 2:
			editOptionPrice();
			break;
		case 3:
			editOptionName();
			break;
		default:
			break;
		}
	}

	// Let current thread sleep at random time
	public void randomWait() {
		try {
			Thread.sleep((long) (3000 * Math.random()));
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
		}
	}

	// Edit optionset name after wait for random time
	public void editOptionSetName() {
		synchronized (automobile) {

			randomWait();
			automobile.updateOptionSetName(newOptions[0], newOptions[1]);

			System.out.println("Edit Option Set Name:");
			automobile.print();
		}
	}

	// Edit option price after wait for random time
	public void editOptionPrice() {
		//synchronized (automobile) {
			System.out.println("Thread " + this.threadId + " start editing option price.....");
			randomWait();
			int setIndex = automobile.findOptionSet(newOptions[0]);
			automobile.updateOptionPrice(setIndex, newOptions[1], Float.parseFloat(newOptions[2]));
			randomWait();// This is very important to check whether synchronized
							// works

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Thread ").append(this.threadId).append(" edit \"").append(newOptions[0]).append(":")
					.append(newOptions[1]).append("\" price:");
			System.out.println(stringBuilder.toString());
			automobile.printOptionSet(setIndex);
			System.out.println("Thread " + this.threadId + " end editing option price.....");
		//}
	}

	// Edit option name after wait for random time
	public void editOptionName() {
		synchronized (automobile) {
			randomWait();
			int setIndex = automobile.findOptionSet(newOptions[0]);
			automobile.updateOptionName(setIndex, newOptions[1], newOptions[2]);

			System.out.println("Edit Option Name:");
			automobile.printOptionSet(setIndex);
		}
	}

}
