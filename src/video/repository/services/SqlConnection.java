package video.repository.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
	
	public static Connection DbConnector() {
		try {
			java.sql.Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://130.100.100.2/hmssantamariadb", "netuser", "netuser");
			return conn;
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
