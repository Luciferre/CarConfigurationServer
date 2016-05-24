package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable {

	/**
	 * This class is the model of optionset
	 */
	private static final long serialVersionUID = -4561892916150939686L;

	private ArrayList<Option> option;

	private Option choice;

	private String name;

	protected OptionSet() {

	}

	protected OptionSet(String name, int size) {
		option = new ArrayList<>();
		// initialize option
		// for (int i = 0; i < size; i++)
		// option.add(new Option());
		this.name = name;
	}

	private class Option implements Serializable {
		/**
		 * This class is the model of Option
		 */
		private static final long serialVersionUID = -6409717731298046707L;
		private String name;

		private float price;

		protected Option() {

		}

		protected Option(String name, float price) {
			this.name = name;
			this.price = price;
		}

		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}

		protected float getPrice() {
			return price;
		}

		protected void setPrice(float price) {
			this.price = price;
		}
	}

	protected String getName() {
		return name;
	}

	// get option by index

	protected float getOptionPrice(int index) {
		return option.get(index).getPrice();
	}

	protected void setName(String name) {
		this.name = name;
	}

	// initialize option by new name and price
	protected void setOption(String name, float price) {
		option.add(new Option(name, price));
	}

	// set option by option index, new name and price
	protected void setOption(int optionIndex, String name, float price) {
		option.set(optionIndex, new Option(name, price));
	}

	// find option by name
	protected int findOption(String name) {
		for (int i = 0; i < option.size(); i++) {
			if (option.get(i).getName().equals(name))
				return i;
		}
		return -1;
	}

	// delete option by index
	protected void deletOption(int index) {
		option.remove(index);
	}

	// update option name by finding option first then set name
	protected void updateOptionName(String oldName, String newName) {
		int optionIndex = findOption(oldName);
		option.get(optionIndex).setName(newName);
	}

	// update option price by finding opiton first then set price
	protected void updateOptionPrice(String name, float price) {
		int optionIndex = findOption(name);
		option.get(optionIndex).setPrice(price);
	}

	// Print option set
	protected void print() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < option.size(); i++) {
			if (i == 0)
				stringBuilder.append(option.get(i).name).append(", ").append(option.get(i).price);
			else {
				stringBuilder.append("; ").append(option.get(i).name).append(", ").append(option.get(i).price);
			}
		}
		System.out.println(stringBuilder.toString());

	}

	protected String getChoiceName() {
		return choice.getName();
	}

	protected float getChoicePrice() {
		return choice.getPrice();
	}

	protected void setChoice(String name) {
		int optionIndex = findOption(name);
		choice = option.get(optionIndex);
	}

	protected ArrayList<String> getOptionList() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < option.size(); i++) {
			list.add(option.get(i).getName());
		}
		return list;
	}
}
