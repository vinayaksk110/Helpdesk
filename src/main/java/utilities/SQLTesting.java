package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTesting {
	public static void  main(String[] args) throws  ClassNotFoundException, SQLException {													
		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"		
        String dbUrl = "jdbc:mysql://172.25.245.177:3306/Helpdesk";
//        String dbUrl = "jdbc:mysql://172.25.245.177:3306/mysql?characterEncoding=utf8";

		//Database Username		
		String username = "vinni";	
        
		//Database Password		
		String password = "D@vteam12";				

		//Query to Execute		
		String query = "select * from Login;";	
        
 	    //Load mysql jdbc driver		
   	    Class.forName("com.mysql.jdbc.Driver");			
   
   		//Create Connection to DB		
    	Connection con = DriverManager.getConnection(dbUrl,username,password);
    	System.out.println("connected");
  
  		//Create Statement Object		
	   Statement stmt = con.createStatement();					

			// Execute the SQL Query. Store results in ResultSet		
 		ResultSet rs= stmt.executeQuery(query);							
 
 		// While Loop to iterate through all data and print results		
		while (rs.next()){
	        		String value0 = rs.getString(1);								        
                    String value1 = rs.getString(2);
                    String value2 = rs.getString(3);
                    System. out.println(value0+"  "+value1+" "+value2);		
            }		
			 // closing DB Connection		
			con.close();			
}

}
