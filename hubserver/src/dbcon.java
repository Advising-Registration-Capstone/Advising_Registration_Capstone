
import java.sql.Connection;
// import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbcon {
	private static Connection con = null;
	private static ResultSet rs  = null;
	private static PreparedStatement pstmt = null;
	private static String DBUSER = "cap1"; //assigned username
    private static String DBPASS = "UApass91"; //assigned password
    private static String url 	 = "jdbc:db2://data.cis.uafs.edu:55000/CAP1";
	public boolean checkAuth() {
        boolean result = false;
        String val = null;
        String sql = "SELECT COUNT(*) FROM COURSE";

        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("The driver could not be loaded");
            e.printStackTrace();
        }
        System.out.println("Database driver is loaded successfully");

        // EXECUTION
        try {
            con = DriverManager.getConnection(url, DBUSER, DBPASS);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();            
            
            if (rs != null) {
                while (rs.next()) {
                    val = rs.getString(1);
                    System.out.println("Number of courses is " + val );
                }
            }
            rs.close();
            pstmt.close();
            con.close();

        } catch (final SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection failed");
		}

		if(val!=null){
            result = true;
        }else{
            result = false;
        }
            
		return result;
    }
}
