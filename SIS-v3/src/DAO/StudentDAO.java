package DAO;

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
			//ds = (DataSource) (new InitialContext().lookup("java:/comp/env/jdbc/EECS"));
			ds = (DataSource) (new InitialContext().lookup("jdbc/Db2-5n"));
			
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
	
	public Map<String, StudentBean>getStudent(String surname) throws SQLException{
		String query = "select * from students where surname like '%" + surname + "%'";
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
	
	public int insert(String sid, String givenName, String surname, int credit_taken, int credit_graduate) throws SQLException, NamingException{
		String preparedStatement ="insert into students values(?,?,?,?,?)";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		//here we set individual parameters through method calls
		//first parameter is the place holder position in the ? //pattern above
		stmt.setString(1, sid);
		stmt.setString(2, givenName);
		stmt.setString(3, surname);
		stmt.setInt(4, credit_taken);
		stmt.setInt(5, credit_graduate);
		
		return stmt.executeUpdate();
		
	}
	
	public int delete(String sid) throws SQLException, NamingException{
		String p = "delete from students where sid=?";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(p);
		stmt.setString(1, sid);
		return stmt.executeUpdate();
	}

}
