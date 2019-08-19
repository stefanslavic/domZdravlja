package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	private static final String DATABASE = "localhost:3306/domzdravlja";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";	

	private static Connection connection;

	public static void open() throws Exception {
		// ucitavanje MySQL drajvera
		Class.forName("com.mysql.jdbc.Driver");
		// otvaranje konekcije
		connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?useSSL=false&serverTimezone=UTC", USER_NAME, PASSWORD);
	}

	public static Connection getConnection() throws Exception {
		if (connection == null || connection.isClosed()) {
			throw new Exception("Connection does not exist!");
		}

		return connection;
	}

	public static void close() throws Exception {
		connection.close();
	}

}
