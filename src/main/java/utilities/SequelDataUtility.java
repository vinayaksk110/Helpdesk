package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.ResultSetMetaData;

public class SequelDataUtility {
	UserCredentials userData = new UserCredentials();
	HashMap<String, String> map = new HashMap<>();
	ResultSetMetaData rsMetaData;

//	public static void main(String[] args) {
//		SQLTesting sql = new SQLTesting();
//		sql.Getdata("Login", "LoginToAccount");
//
//	}
	
	public SequelDataUtility(){
		
	}

	public UserCredentials Getdata(String Table, String TestCase) {
		// String Table, String TestCase
		try {
			// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
			String dbUrl = "jdbc:mysql://172.19.241.15:3306/Helpdesk";
			String username = "vinni";
			String password = "D@vteam12";
			String query = "select * from "+Table+" where testCase='"+TestCase+"';";
			// Load mysql jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Create Connection to DB
			Connection con = DriverManager.getConnection(dbUrl, username, password);
			System.out.println("=======Connection Established To The Database=======");
			// Create Statement Object
			Statement stmt = con.createStatement();
			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery(query);

			rsMetaData = (ResultSetMetaData) rs.getMetaData();
//			System.out.println("List of column names in the current table: ");
			
			// While Loop to iterate through all data and print results
			int count = rsMetaData.getColumnCount();
			ArrayList<String> columnName = new ArrayList<String>();
			for (int i = 1; i <= count; i++) {
//				System.out.print(rsMetaData.getColumnName(i) + " ");
				columnName.add(rsMetaData.getColumnName(i));
			}
			
			// Creating arraylist of values.
			ArrayList<String> Values = new ArrayList<String>();
			while (rs.next()) {
				int k = 1;
				while (k <= count) {
//					System.out.print(rs.getString(k) + " ");
					Values.add(rs.getString(k++));
				}
			}

			// for every key, value
			for (int l = 0; l < columnName.size(); l++) {
				// put() method on the key-value pair
				map.put(columnName.get(l), Values.get(l));
			}
//			System.out.println(map);
			
			userData.emailID = map.get("userName");
			userData.password = map.get("password");
			userData.ccNo = map.get("CCNo");
			userData.firstName = map.get("firstName");
			
//			System.out.println(userData);

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
//		System.out.println("Datttttttttttttttaaaaaaaaa:"+userData.emailID);
		return userData;
	}

}
