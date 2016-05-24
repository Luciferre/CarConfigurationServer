package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Automobile implements Serializable {

	/**
	 * This class is the model of automobile
	 */
	private static final long serialVersionUID = -6616796673467582433L;

	private String model;

	private String make;

	private ArrayList<OptionSet> optionSet;

	private float basePrice;

	public Automobile() {
		optionSet = new ArrayList<>();
	}

	public Automobile(int size, String model, String make, float basePrice) {
		optionSet = new ArrayList<>();
		// initialize opitonset
		// for (int i = 0; i < size; i++)
		// optionSet.add(new OptionSet());
		this.model = model;
		this.make = make;
		this.basePrice = basePrice;
	}

	// get optionset by index
	public OptionSet getOptionSet(int index) {
		return optionSet.get(index);
	}

	public float getBasePrice() {
		return basePrice;
	}

	// initialize optionset new name, size
	public void setOptionSet(String name, int size) {
		optionSet.add(new OptionSet(name, size));

	}

	// set optionset by index and new name, size
	public void setOptionSet(int index, String name, int size) {
		optionSet.set(index, new OptionSet(name, size));

	}

	// set base price
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	// find optionset by name
	public int findOptionSet(String name) {
		for (int i = 0; i < optionSet.size(); i++) {
			if (optionSet.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

	// delete optionset by index
	public void deleteOptionSet(int index) {
		optionSet.remove(index);
	}

	// update optionset name by find optionset first then set name
	public void updateOptionSetName(String oldName, String newName) {
		int setIndex = findOptionSet(oldName);
		optionSet.get(setIndex).setName(newName);
	}

	// print Automobile
	public void print() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Model: ").append(model).append("\nMake: ").append(make).append("\nBase Price: ")
				.append(basePrice);
		System.out.println(stringBuilder.toString());
		for (int i = 0; i < optionSet.size(); i++) {
			System.out.print(optionSet.get(i).getName());
			System.out.print(": ");
			optionSet.get(i).print();
		}
		System.out.println("");
	}

	public ArrayList<String> getOptionSetList() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < optionSet.size(); i++) {
			list.add(optionSet.get(i).getName());
		}
		return list;
	}

	// =======Access methods for OptionSet and Option========
	public String getOptionSetName(int setIndex) {
		return optionSet.get(setIndex).getName();
	}

	public void setOptionSetName(int setIndex, String name) {
		optionSet.get(setIndex).setName(name);
	}

	public void setOption(int optionSetIndex, String name, float price) {
		optionSet.get(optionSetIndex).setOption(name, price);
	}

	public void deleteOption(int setIndex, int optionIndex) {
		optionSet.get(setIndex).deletOption(optionIndex);
	}

	public int findOption(int setIndex, String name) {
		return optionSet.get(setIndex).findOption(name);
	}

	public void updateOptionName(int setIndex, String oldName, String newName) {
		optionSet.get(setIndex).updateOptionName(oldName, newName);
	}

	public void updateOptionPrice(int setIndex, String name, float price) {
		optionSet.get(setIndex).updateOptionPrice(name, price);
	}

	public void printOptionSet(int setIndex) {
		optionSet.get(setIndex).print();
	}

	public float getOptionPrice(String setName, String optionName) {
		int setIndex = findOptionSet(setName);
		int optionIndex = optionSet.get(setIndex).findOption(optionName);
		return optionSet.get(setIndex).getOptionPrice(optionIndex);
	}

	public String getOptionChoiceName(String setName) {
		int setIndex = findOptionSet(setName);
		return optionSet.get(setIndex).getChoiceName();
	}

	public float getOptionChoicePrice(String setName) {
		int setIndex = findOptionSet(setName);
		return optionSet.get(setIndex).getChoicePrice();
	}

	public void setOptionChoice(String setName, String optionName) {
		int setIndex = findOptionSet(setName);
		optionSet.get(setIndex).setChoice(optionName);
	}

	public ArrayList<String> getOptionList(String setName) {
		int setIndex = findOptionSet(setName);
		return optionSet.get(setIndex).getOptionList();
	}

	// Calculate the total price
	public float getTotalPrice() {
		float total = 0;
		for (int i = 0; i < optionSet.size(); i++) {
			total += optionSet.get(i).getChoicePrice();
		}
		return total;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

}
