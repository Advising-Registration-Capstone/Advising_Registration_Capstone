
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
        String val2 = null;
        String val3 = null;
        String val4 = null;
        String val5 = null;
        String val6 = null;
        String val7 = null;
        String sql = "SELECT * FROM COURSE";

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
            System.out.println("Course #...Credits...Name..............................Description...................");
            if (rs != null) {
                while (rs.next()) {
                    val = rs.getString(1);
                    val2 = rs.getString(2);
                    val3 = rs.getString(3);
                    val4 = rs.getString(4);
                    // val5 = rs.getString(5);
                    // val6 = rs.getString(6);
                    // val7 = rs.getString(7);
                    System.out.println(val + "......."+ val2 + "........." + val3 + ".........."+ val4 );
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
