package bean;

import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="sisReport")
public class ListWrapper {
	
	@XmlAttribute
	private String namePrefix;
	@XmlAttribute(name="creditTaken")
	private int credit_taken;
	@XmlElement(name="studentList")
	private List<StudentBean> list;
	/**
	 * @param namePrefix
	 * @param credit_taken
	 * @param list
	 */
	
	public ListWrapper() {
		
	}
	
	public ListWrapper(String namePrefix, int credit_taken, List<StudentBean> list) {
		super();
		this.namePrefix = namePrefix;
		this.credit_taken = credit_taken;
		this.list = list;
	}
	
	public ListWrapper(String namePrefix, List<StudentBean> list) {
		super();
		this.namePrefix = namePrefix;
		this.list = list;
	}


}
