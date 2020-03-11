package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.EnrollmentBean;
import bean.StudentBean;

public class EnrollmentDAO {
	
	DataSource ds;
	
	public EnrollmentDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext().lookup("java:/comp/env/jdbc/EECS"));
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, EnrollmentBean>retrieve() throws SQLException{
		ArrayList<String> students = new ArrayList<String>();
		String query1= "Select distinct cid, credit from ENROLLMENT";
		Map<String, EnrollmentBean> rv = new HashMap<String, EnrollmentBean>();
		Connection con = this.ds.getConnection();
		Statement stmt = con.createStatement();
		PreparedStatement p1 = con.prepareStatement(query1);
		ResultSet r1 = p1.executeQuery();
		ResultSet r2 = null;
		while (r1.next()) {
			String cid = r1.getString("CID");
			int credit = r1.getInt("CREDIT");
			r2 = stmt.executeQuery("select sid from ENROLLMENT where cid = '"+cid+"'");
			while (r2.next()) {
				String sid = r2.getString("SID");
				students.add(sid);
			}
			rv.put(cid, new EnrollmentBean(cid, students, credit));
					
		}
		r2.close();
		r1.close();
		p1.close();
		con.close();
	return rv;
	}
	

}
