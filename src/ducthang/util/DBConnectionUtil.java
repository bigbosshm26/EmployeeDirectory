package ducthang.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

	// define the DB properties
	private static final String USER = "student";
	
	private static final String PASS = "student";
	
	private static final String URL = "jdbc:mysql://localhost:3306/employeedirectory";
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private static Connection connection = null;
	
	// define the static method
	public static Connection openConnection(){
		// check the connection
		if(connection != null) {
			return connection;
		}
		else {
			try {
			// load the driver
			Class.forName(DRIVER);
			//get the connection
			connection = DriverManager.getConnection(URL,USER,PASS);
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
                e.printStackTrace();
            } 
			//return the connection
			return connection;
			
		}
	}
	
}
