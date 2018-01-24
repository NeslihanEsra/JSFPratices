package com.nea.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreparedStatementExp {
	static final String jdbcDriver = "com.nea.jdbc.example.FirstApplication";
	static final String dbURL = "jdbc:postgresql://localhost:5432/emp";

	static final String usrname = "postgres";
	static final String pssword = "postgres";

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// jdbc driver a kay�t oldu. Yani class memory ye y�klendi
			Class.forName(jdbcDriver);

			// db ile contact a ge�ildi
			connection = DriverManager.getConnection(dbURL, usrname, pssword);
			System.out.println("db ye ba�lan�ld�");

			// sql db ye g�nderilir
			System.out.println("sql db ye g�nderildi");
			String sql = "UPDATE employees set age=? WHERE id=?"; //Postgresql de farkl� yaz�m var!
			statement = connection.prepareStatement(sql);

			// Bind values into the parameters.
			statement.setInt(1, 35); // This would set age
			statement.setInt(2, 102); // This would set ID

			// db deki datalar resulsette tutulur
			int rows = statement.executeUpdate();
			System.out.println("Rows impacted : " + rows);

			// Let us select all the records and display them.
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet resultSet = statement.executeQuery(sql);

			// resulset te tutulan datalar de�i�kene at�l�r
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int age = resultSet.getInt("age");
				String first = resultSet.getString("first");
				String last = resultSet.getString("last");

				// datalar consola yaz�l�r
				System.out.print("id: " + id);
				System.out.print(", age: " + age);
				System.out.print(", first: " + first);
				System.out.println(", last: " + last);
			}
			// clean-up environment
			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("i�lem tamamland�");
	}
}
