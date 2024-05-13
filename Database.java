package DACS1_UngDungNhanTin;

import java.sql.*;

public class Database {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/dacs1";
	private static final String DB_USER = "diem";
	private static final String DB_PASSWORD = "1234";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}

}
