package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import util.DatabaseIO;

public class DeleteOperation {
	DatabaseIO databaseIO = null;

	public DeleteOperation() {
		databaseIO = new DatabaseIO();
	}

	// delete automobile from database
	public void deleteOption(String modelName) {
		try {
			Connection connection = databaseIO.getConnection();
			Properties properties = databaseIO.getProperties();
			selectDB(connection, properties);

			PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("DeleteModel"));
			preparedStatement.setString(1, modelName);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
