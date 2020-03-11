package model;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.StudentBean;

public class StudentDAO {
	
	DataSource ds;
	
	public StudentDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext().lookup("java:/comp/env/jdbc/EECS"));
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, StudentBean>retrieve(String namePrefix, int credit_taken) throws SQLException{
		String query = "select * from students where surname like '%" + namePrefix + "%' and credit_taken >= " + credit_taken;
		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
			String sid = r.getString("SID");
			int cred_taken= r.getInt("CREDIT_TAKEN");
			int cred_graduate = r.getInt("CREDIT_GRADUATE");
			
			rv.put(sid, new StudentBean(sid, name, cred_taken, cred_graduate));
		}
				
		r.close();
		p.close();
		con.close();
				
		return rv;
		
	}
	
	public Map<String, StudentBean>retrieveAll() throws SQLException{
		String query = "select * from students";
		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
			String sid = r.getString("SID");
			int cred_taken= r.getInt("CREDIT_TAKEN");
			int cred_graduate = r.getInt("CREDIT_GRADUATE");
			
			rv.put(sid, new StudentBean(sid, name, cred_taken, cred_graduate));
		}
				
		r.close();
		p.close();
		con.close();
				
		return rv;
		
	}
	
	public Map<String, StudentBean>add(String stuID, String givenName, String surName) throws SQLException{
		String query = String.format("insert into students values ('%s', '%s', '%s', 0, 0)", stuID, givenName, surName);
		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		Connection con = this.ds.getConnection();
		//PreparedStatement p = con.prepareStatement(query);
		//ResultSet r = p.executeQuery();
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
		rv.put(stuID, new StudentBean(stuID, givenName + surName, 0, 0));			
		con.close();
				
		return rv;
		
	}

}
