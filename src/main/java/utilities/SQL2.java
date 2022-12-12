package utilities;

import java.sql.Connection;
import java.sql.Statement;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.DriverManager;

public class SQL2 {
	// Connection object
	static Connection con = null;
	// Statement object
	private static Statement stmt;
	// Constant for Database URL
	public static String DB_URL = "jdbc:mysql://192.168.0.105:3306/helpdesk";
	//Database Username
	public static String DB_USER = "vinni";
	// Database Password
	public static String DB_PASSWORD = "your_password";

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setUp() throws Exception {
	try{
	// Database connection
	String dbClass = "com.mysql.jdbc.Driver";
	Class.forName(dbClass).newInstance();
	// Get connection to DB
	Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

	// Create Statement Object
	Statement stmt = con.createStatement();
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	}

	@Test
	public void test() {
	try{
	String query = "SELECT * FROM Login;";
	// Get the contents of userinfo table from DB
	ResultSet res = stmt.executeQuery(query);
	// Print the result untill all the records are printed
	// res.next() returns true if there is any next record else returns false
	while (res.next())
	{
	System.out.print(res.getString(1));
	System.out.print(" " + res.getString(2));
	System.out.print(" " + res.getString(3));
	System.out.println(" " + res.getString(4));
	}
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	}

	@AfterTest
	public void tearDown() throws Exception {
	// Close DB connection
	if (con != null) {
	con.close();
	}
	}

}
