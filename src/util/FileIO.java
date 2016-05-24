package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Properties;

import org.omg.CORBA.OBJ_ADAPTER;

import exception.AutoException;
import exception.FixHelper;
import exception.AutoException.ExceptionEnum;
import model.Automobile;
import model.OptionSet;

public class FileIO {

	// build automobile object from plain text file
	public Automobile buildAutoObject(String fileName) throws AutoException {

		Automobile automobile = null;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));

			int lineIndex = 0;
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				// read car model, name and base price
				if (lineIndex == 0) {
					String[] parts = line.split(",");
					String model = parts[0].trim();
					String make = parts[1].trim();

					if (parts.length == 3 && !parts[2].trim().equals("")) {
						float basePrice = Float.parseFloat(parts[2].trim());
						automobile = new Automobile(5, model, make, basePrice);
					} else {
						throw new AutoException(ExceptionEnum.MissingPrice);
					}
					lineIndex++;
					continue;
				}
				// read option set
				String[] parts = line.split(":");
				if (parts.length == 2) {
					String[] parts2 = parts[1].split(";");
					automobile.setOptionSet(parts[0].trim(), parts2.length);

					for (int j = 0; j < parts2.length; j++) {
						String[] parts3 = parts2[j].split(",");

						if (parts3.length == 2 && !parts3[1].trim().equals("")) {
							int price = Integer.parseInt(parts3[1].trim());
							automobile.setOption(lineIndex - 1, parts3[0].trim(), price);

						} else {
							throw new AutoException(ExceptionEnum.MissingOptionPrice);
						}
					}

				} else {
					throw new AutoException(ExceptionEnum.MissingOption);
				}
				lineIndex++;
			}

			if (lineIndex == 1) {
				throw new AutoException(ExceptionEnum.MissingOptionSet);
			}

			bufferedReader.close();

		} catch (FileNotFoundException e) {
			throw new AutoException(ExceptionEnum.WrongFileName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return automobile;
	}

	// build automobile object from file, no use in server class
	public Automobile buildAutoObjectFromText(Object object) throws AutoException {
		String fileName = (String) object;
		Automobile automobile = null;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));

			int lineIndex = 0;
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				// read car model, name and base price
				if (lineIndex == 0) {
					String[] parts = line.split(",");
					String model = parts[0].trim();
					String make = parts[1].trim();

					if (parts.length == 3 && !parts[2].trim().equals("")) {
						float basePrice = Float.parseFloat(parts[2].trim());
						automobile = new Automobile(5, model, make, basePrice);
					} else {
						throw new AutoException(ExceptionEnum.MissingPrice);
					}
					lineIndex++;
					continue;
				}
				// read option set
				String[] parts = line.split(":");
				if (parts.length == 2) {
					String[] parts2 = parts[1].split(";");
					automobile.setOptionSet(parts[0].trim(), parts2.length);

					for (int j = 0; j < parts2.length; j++) {
						String[] parts3 = parts2[j].split(",");

						if (parts3.length == 2 && !parts3[1].trim().equals("")) {
							int price = Integer.parseInt(parts3[1].trim());
							automobile.setOption(lineIndex - 1, parts3[0].trim(), price);

						} else {
							throw new AutoException(ExceptionEnum.MissingOptionPrice);
						}
					}

				} else {
					throw new AutoException(ExceptionEnum.MissingOption);
				}
				lineIndex++;
			}

			if (lineIndex == 1) {
				throw new AutoException(ExceptionEnum.MissingOptionSet);
			}

			bufferedReader.close();

		} catch (FileNotFoundException e) {
			throw new AutoException(ExceptionEnum.WrongFileName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return automobile;
	}

	// serialize automotive object and save to file
	public void serializeAuto(Automobile automotive) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("auto.ser"));
			out.writeObject(automotive);
			out.close();
		} catch (Exception e) {
			System.out.print("Error:" + e);
			System.exit(1);
		}

	}

	// read file and deserialize automotive object
	public Automobile deserializeAuto(String fileName) {
		ObjectInputStream in;
		Automobile automotive = null;
		try {
			in = new ObjectInputStream(new FileInputStream("auto.ser"));
			automotive = (Automobile) in.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return automotive;
	}

	// build autos from properties object uploaded from client
	public Automobile buildAutoFromProperties(Object object) {
		Properties properties = (Properties) object;

		Automobile automobile = new Automobile();
		String carMake = properties.getProperty("CarMake");
		StringBuilder stringBuilder = new StringBuilder();
		String optionSetName;
		String optionName;
		int optionPrice;

		if (!carMake.equals(null)) {
			automobile.setMake(carMake);
			automobile.setModel(properties.getProperty("CarModel"));
			automobile.setBasePrice(Float.parseFloat(properties.getProperty("BasePrice")));
			// set option set
			for (int i = 1; i <= 5; i++) {
				optionSetName = properties.getProperty("Option" + i);
				automobile.setOptionSet(optionSetName, 0);
				int j = 1;
				// set option
				while (true) {
					stringBuilder.setLength(0);
					stringBuilder.append("Option").append(i).append("Name").append(j);
					optionName = properties.getProperty(stringBuilder.toString());
					if (optionName == null) {
						break;
					}
					stringBuilder.setLength(0);
					stringBuilder.append("Option").append(i).append("Price").append(j);
					optionPrice = Integer.parseInt(properties.getProperty(stringBuilder.toString()));
					automobile.setOption(i - 1, optionName, optionPrice);
					j++;

				}
			}
		}

		return automobile;
	}

}
