package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;
import util.DatabaseIO;

//Add a new automobile to database
public class CreateOperation {

	DatabaseIO databaseIO = null;

	public CreateOperation() {
		databaseIO = new DatabaseIO();
	}

	// persist automobile to database
	public void createOperation(Automobile automobile) {

		try {
			Connection connection = databaseIO.getConnection();
			Properties properties = databaseIO.getProperties();
			selectDB(connection, properties);

			// persist to automobile table
			PreparedStatement preparedStatement = connection
					.prepareStatement(properties.getProperty("CreateAutomobile"), Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, automobile.getMake());
			preparedStatement.setString(2, automobile.getModel());
			preparedStatement.setFloat(3, automobile.getBasePrice());
			preparedStatement.executeUpdate();
			int autoID = getID(preparedStatement);

			// persist to optionset table
			ArrayList<String> optionSetList = automobile.getOptionSetList();
			for (int i = 0; i < optionSetList.size(); i++) {
				String setName = optionSetList.get(i);
				preparedStatement = connection.prepareStatement(properties.getProperty("CreateOptionSet"),
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, setName);
				preparedStatement.setInt(2, autoID);
				preparedStatement.executeUpdate();
				int optionsetID = getID(preparedStatement);

				// persist to options table
				ArrayList<String> optionlist = automobile.getOptionList(setName);
				for (int j = 0; j < optionlist.size(); j++) {
					String optionName = optionlist.get(j);
					preparedStatement = connection.prepareStatement(properties.getProperty("CreateOption"),
							Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, optionName);
					preparedStatement.setFloat(2, automobile.getOptionPrice(setName, optionName));
					preparedStatement.setInt(3, optionsetID);
					preparedStatement.executeUpdate();
				}
			}

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// get auto_generated id from statement
	public int getID(PreparedStatement statement) {
		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}

	// select database after connection established, use automoible_db
	public void selectDB(Connection connection, Properties properties) {
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeQuery(properties.getProperty("UseDB"));
			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
