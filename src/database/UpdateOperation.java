package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import util.DatabaseIO;

public class UpdateOperation {
	DatabaseIO databaseIO = null;

	public UpdateOperation() {
		databaseIO = new DatabaseIO();

	}

	// update option price in database
	public void updateOptionPrice(String modelName, String setName, String optionName, float price) {
		try {
			Connection connection = databaseIO.getConnection();
			Properties properties = databaseIO.getProperties();
			selectDB(connection, properties);

			// find the automobile id using select
			PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("FindModel"));
			preparedStatement.setString(1, modelName);
			ResultSet resultSet = preparedStatement.executeQuery();
			int modelID = 0;
			if (resultSet.next())
				modelID = Integer.parseInt(resultSet.getString("id"));

			// find optionset id using select
			preparedStatement = connection.prepareStatement(properties.getProperty("FindOptionSet"));
			preparedStatement.setString(1, setName);
			preparedStatement.setInt(2, modelID);
			resultSet = preparedStatement.executeQuery();
			int setID = 0;
			if (resultSet.next())
				setID = Integer.parseInt(resultSet.getString("id"));

			// update the option price
			preparedStatement = connection.prepareStatement(properties.getProperty("UpdateOptionPrice"));
			preparedStatement.setFloat(1, price);
			preparedStatement.setString(2, optionName);
			preparedStatement.setInt(3, setID);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// update optionset name in database
	public void updateOptionSetName(String modelName, String setName, String newName) {
		try {
			Connection connection = databaseIO.getConnection();
			Properties properties = databaseIO.getProperties();
			selectDB(connection, properties);

			// find the automobile id using select
			PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("FindModel"));
			preparedStatement.setString(1, modelName);
			ResultSet resultSet = preparedStatement.executeQuery();
			int modelID = 0;
			if (resultSet.next())
				modelID = Integer.parseInt(resultSet.getString("id"));
			resultSet.close();

			// update optionset name
			preparedStatement = connection.prepareStatement(properties.getProperty("UpdateOptionSetName"));
			preparedStatement.setString(1, newName);
			preparedStatement.setString(2, setName);
			preparedStatement.setInt(3, modelID);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
