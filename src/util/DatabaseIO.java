package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//Basic interaction with mysql database
public class DatabaseIO {

	private static final String DBURL = "jdbc:mysql://localhost:3306";

	private static final String USER = "root";

	private static final String PASSWORD = "";

	private Properties properties;

	private Connection connection = null;

	// initialize connection with database and read in sql instruction
	public DatabaseIO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		properties = new Properties();
		try {
			FileInputStream in = new FileInputStream("sql.pro");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// close connection after operation
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// create automobile_db database and create automobile, optionset, options
	// tables
	public void createDB() {
		try {
			Statement statement = connection.createStatement();

			BufferedReader bufferedReader = null;
			bufferedReader = new BufferedReader(new FileReader("CreateDatabase.sql"));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				statement.executeUpdate(line);
			}

			bufferedReader.close();
			statement.close();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// select * from automobile and print out
	public void printAutomobile() {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(properties.getProperty("SelectAutomobile"));
			System.out.println("ID\tModel\tMake\tBasePrice");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id") + "\t" + resultSet.getString("model") + "\t"
						+ resultSet.getString("make") + "\t" + resultSet.getString("baseprice"));
			}
			statement.close();
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// select * from optionset and print out
	public void printOptionSet() {

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(properties.getProperty("SelectOptionSet"));
			System.out.println("ID\tName\tAutoID");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id") + "\t" + resultSet.getString("name") + "\t"
						+ resultSet.getString("autoid"));
			}
			statement.close();
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// select * from options and print out
	public void printOptions() {

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(properties.getProperty("SelectOptions"));
			System.out.println("ID\tName\tPrice\tOptionsetID");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id") + "\t" + resultSet.getString("name") + "\t"
						+ resultSet.getString("price") + "\t" + resultSet.getString("optionsetID"));
			}
			statement.close();
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		return connection;
	}

	public Properties getProperties() {
		return properties;
	}

}
