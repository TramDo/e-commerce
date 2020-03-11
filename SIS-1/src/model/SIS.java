package model;

import java.util.HashMap;
import java.util.Map;

import bean.EnrollmentBean;
import bean.StudentBean;

public class SIS {

	private StudentDAO stuDao;
	private EnrollmentDAO enDao;

	public SIS() {

		try {
			stuDao = new StudentDAO();
			enDao = new EnrollmentDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public Map<String, StudentBean> retrieveStudent(String namePrefix, int credit_taken) throws Exception {

		if(namePrefix.isEmpty()) {
			throw new Exception("Name is empty");
		}
		
		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		rv = stuDao.retrieve(namePrefix, credit_taken);

		return rv;

	}

	public Map<String, EnrollmentBean> retrieveEnrollment() throws Exception {
		Map<String, EnrollmentBean> rv = new HashMap<String, EnrollmentBean>();
		rv = enDao.retrieve();
		return rv;

	}

}
