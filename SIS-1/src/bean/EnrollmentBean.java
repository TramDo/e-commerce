/**
 * 
 */
package bean;

import java.util.ArrayList;

/**
 * @author dothu
 *
 */
public class EnrollmentBean {
	
	private String cid;
	private ArrayList<String> students = new ArrayList<String>();
	private int credit;
	/**
	 * @param cid
	 * @param students
	 * @param credit
	 */
	public EnrollmentBean(String cid, ArrayList<String> students, int credit) {
		super();
		this.cid = cid;
		this.students = students;
		this.credit = credit;
	}
	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
	/**
	 * @return the students
	 */
	public ArrayList<String> getStudents() {
		return students;
	}
	/**
	 * @param students the students to set
	 */
	public void setStudents(ArrayList<String> students) {
		this.students = students;
	}
	/**
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	

}
