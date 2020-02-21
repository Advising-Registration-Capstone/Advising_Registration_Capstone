
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbcon {
	private static Connection con = null;
	private static ResultSet rs  = null;
	private static PreparedStatement pstmt = null;
	private static String DBUSER = "root"; //assigned username
	private static String DBPASS = "UAFSdata1"; //assigned password
	private static String url 	 = "jdbc:mysql://74.117.171.123:3306/ARStudentHub"; //driver :// host : port / database
	                                    //74.117.171.123
	public boolean checkAuth() {
        boolean result = false;
        String val = null;
        String sql = "SHOW TABLES;";

        try {
            // Class.forName("mariadb-java-client-2.5.4");
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("The driver could not be loaded");
            e.printStackTrace();
        }
        System.out.println("Database driver is loaded successfully");

        // EXECUTION
        try {
            con = DriverManager.getConnection(url, DBUSER, DBPASS);
            // con = DriverManager.getConnection("jdbc:mysql://74.117.171.123:3306/ARStudentHub?user=root&password=UAFSdata1");
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    val = rs.getString(1);
                }
            }
            con.close();
        } catch (final SQLException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
		
		if(val!=null)
			result = true;
		else
			result = false;
		return result;
    }
}