package utilities;

import java.sql.Connection;
import java.sql.Statement;

import org.checkerframework.common.reflection.qual.ForName;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLCOnnector {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"

		String dbUrl = "jdbc:mysql://192.168.0.105:3306/helpdesk";
		String username = "vinni";
		String password = "D@vteam12";
		String query = "SELECT * FROM Login;";
//
//		// Load mysql jdbc driver
//		Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.cj.jdbc.Driver");

//		Connection con = DriverManager
//				.getConnection("jdbc:mysql://192.168.0.105:3306/helpdesk?user=vinni&password=D@vteam12");

		Connection con = DriverManager.getConnection(dbUrl, username, password);

		// Create Statement Object
		Statement stmt = con.createStatement();

		// Execute the SQL Query. Store results in ResultSet
		ResultSet rs = stmt.executeQuery(query);

		// While Loop to iterate through all data and print results
		while (rs.next()) {
			String slno = rs.getString(1);
			String email = rs.getString(2);
			String passwd = rs.getString(3);
			System.out.println(slno + "  " + email+" "+passwd);
		}
		// closing DB Connection
		con.close();
	}

}
