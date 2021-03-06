/**
 * 
 */
package bean;

/**
 * @author dothu
 *
 */
import javax.xml.bind.annotation.*;
public class StudentBean  {

	/**
	 * 
	 */
	//four private attributes
	//@XmlElement
	private String sid;
	//@XmlElement
	private String name;
	//@XmlElement
	private int credit_taken;
	//@XmlElement
	private int credit_graduate;
	
	//private int credit_taking;
	
	/**
	 * @param sid
	 * @param name
	 * @param credit_taken
	 * @param credit_graduate
	 */
	public StudentBean(String sid, String name, int credit_taken, int credit_graduate) {
		super();
		this.sid = sid;
		this.name = name;
		this.credit_taken = credit_taken;
		this.credit_graduate = credit_graduate;
		//this.credit_taking = credit_taking;
	}

	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}

	/**
	 * @param sid the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the credit_taken
	 */
	public int getCredit_taken() {
		return credit_taken;
	}

	/**
	 * @param credit_taken the credit_taken to set
	 */
	public void setCredit_taken(int credit_taken) {
		this.credit_taken = credit_taken;
	}

	/**
	 * @return the credit_graduate
	 */
	public int getCredit_graduate() {
		return credit_graduate;
	}

	/**
	 * @param credit_graduate the credit_graduate to set
	 */
	public void setCredit_graduate(int credit_graduate) {
		this.credit_graduate = credit_graduate;
	}

}
