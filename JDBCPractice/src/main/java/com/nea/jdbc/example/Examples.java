package com.nea.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Examples {

	static final String jdbcDriver = "com.nea.jdbc.example.Examples";
	static final String dbUrl = "jdbc:postgresql://localhost:5432/emp";
	static final String user = "postgres";
	static final String pass = "postgres";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		String sql;

		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(dbUrl, user, pass);
			System.out.println("db ye baðlanýldý...");
			statement = connection.createStatement();

			// kayýt silme
			// sql = "DELETE FROM employees WHERE id = 111";
			// statement.executeUpdate(sql);
			// sql = "DELETE FROM employees WHERE first = 'Sumit'";
			// statement.executeUpdate(sql);
			// sql = "DELETE FROM employees WHERE id = 103";
			// statement.executeUpdate(sql);

			// // tablo kayýtlarýný update etme
			// String sql = "UPDATE employees SET age = 30 WHERE id in (100,
			// 101)";
			// statement.executeUpdate(sql);

			// tablo kayýtlarýný görüntüleme
			// sql = "SELECT id, age, first, last from employees";

			// kayýtlarý filtreleme
			// sql = "SELECT id, first, last, age FROM employees WHERE id >=
			// 112";

			// like kullanýmý
			// sql = "SELECT id, first, last, age FROM employees WHERE last LIKE
			// '%an%' ";

			// ASC & DESC
			sql = "SELECT id, first, last, age FROM employees ORDER BY id ASC";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", Name: " + first);
				System.out.println(", Surname: " + last);
			}
			rs.close();

			// tabloya kayýt ekleme
			// String sql = "INSERT INTO employees VALUES (110, 18, 'Eren',
			// 'Son')";
			// statement.executeUpdate(sql);
			// sql = "INSERT INTO employees VALUES (111, 65, 'John', 'Fah')";
			// statement.executeUpdate(sql);
			// sql = "INSERT INTO employees VALUES (112, 30, 'Suzan', 'Kan')";
			// statement.executeUpdate(sql);
			// sql = "INSERT INTO employees VALUES(113, 28, 'Tom', 'Mit')";
			// statement.executeUpdate(sql);
			// System.out.println("Yeni kayýtlar eklendi...");

			// tablo silme
			// String sql = "DROP TABLE registration ";
			// statement.executeUpdate(sql);
			// System.out.println("Tablo silindi...");

			// tablo oluþturma
			// String sql = "create table registration (id INT PRIMARY KEY NOT
			// NULL, name VARCHAR(50), surname VARCHAR(255), age INT)";
			// statement.executeUpdate(sql);
			// System.out.println("Tablo oluþturuldu...");

			// db silme
			// String sql = "drop database student";
			// statement.executeUpdate(sql);
			// System.out.println("Database silindi...");

			// db oluþturma
			// String sql = "create database student";
			// statement.executeUpdate(sql);
			// System.out.println("Database oluþturuldu...");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}
}
