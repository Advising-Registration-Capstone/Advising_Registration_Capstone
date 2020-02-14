package mariadb;

import java.sql.*;

public class dbcon{

    private static Connection con = null;
	private static ResultSet rs  = null;
	private static PreparedStatement pstmt = null;
	private static String DBUSER = "ua720"; //assigned username
	private static String DBPASS = "UAFS8314"; //assigned password
	private static String url 	 = "jdbc:db2://data.cis.uafs.edu:55000/CS2043"; //driver :// host : port : database

    public boolean checkAuth(String user, String pass) {
		boolean result = false;
		int val = 0;
		String sql = "SELECT count(*) FROM Customer WHERE Cust_UserName = ? AND Cust_Password = ?";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		
		}catch(ClassNotFoundException e) {
			System.out.println("The driver could not be loaded");
			e.printStackTrace();
		}
		System.out.println("Database driver is loaded successfully");
	
	
		//EXECUTION 
		try {
			con = DriverManager.getConnection(url, DBUSER, DBPASS);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					val = rs.getInt(1);
				}
			}			
		} catch(SQLException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
		
		if(val==1)
			result = true;
		else
			result = false;
		return result;
	}
	

}
