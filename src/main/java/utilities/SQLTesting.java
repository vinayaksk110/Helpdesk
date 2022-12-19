package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLTesting {
	UserCredentials userData = new UserCredentials();
	

	public static void main(String[] args) {
		SQLTesting sql= new SQLTesting();
		sql.Getdata("Login", "LoginToFreeTrialAccount");

	}

	public UserCredentials Getdata(String Table, String TestCase) {
		try {
			// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
			String dbUrl = "jdbc:mysql://172.24.113.59:3306/Helpdesk";
			// Database Username
			String username = "vinni";
			// Database Password
			String password = "D@vteam12";
			// Query to Execute
//			String query = "select * from "+Table+" where testCase='"+TestCase+"';";
//			String query = "SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('Login') ";
			String query = "SELECT * FROM sys.tables WHERE object_id = OBJECT_ID('Login'); ";
			// Load mysql jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);
			System.out.println("Connection established");
			// Create Statement Object
			Statement stmt = con.createStatement();
			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery(query);
			// While Loop to iterate through all data and print results
//			$row = array();
//			$row[] = mysql_fetch_array($result, MYSQL_ASSOC);
			
			while (rs.next()) {
				String value = rs.getString(1);
				System.out.println(value);
			}
			// closing DB Connection
			con.close();
		} catch (SQLException sqle) {
			System.out.println("there was an issue updating");
	        System.out.println("SQLException: " + sqle.getMessage());
	        System.out.println("SQLState: " + sqle.getSQLState());
	        System.out.println("VendorError: " + sqle.getErrorCode());
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			System.out.println("Exception occured while connecting to DB");
		}
		return userData;
	}

}
