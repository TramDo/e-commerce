package model;

import java.io.File;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import DAO.EnrollmentDAO;
import DAO.StudentDAO;

import java.util.List;

import bean.EnrollmentBean;
import bean.ListWrapper;
import bean.StudentBean;

public class SIS {
	// define the static and private field, give it a name
	private static SIS instance;
	// these are the pointers to the DAO objects�DAO objects will support all
	// operations on database
	private StudentDAO stuDao;
	private EnrollmentDAO enDao;

	// getInstance will return that ONE instance of the pattern
	// with the the DAO objects initialized..
	public static SIS getInstance() throws ClassNotFoundException {

		if (instance == null) {
			instance = new SIS();
			instance.stuDao = new StudentDAO();
			instance.enDao = new EnrollmentDAO();
		}
		return instance;
	}

	private SIS() {

		/*try {
			stuDao = new StudentDAO();
			enDao = new EnrollmentDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	public Map<String, StudentBean> retrieveStudent(String namePrefix, int credit_taken) throws Exception {

		if (namePrefix.isEmpty()) {
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

	public String export(String namePrefix, int minGPA) throws Exception {
		Map<String, StudentBean> stuBean = new HashMap<String, StudentBean>();
		List<StudentBean> stuList = new ArrayList<StudentBean>();
		stuBean = retrieveStudent(namePrefix, minGPA);
		for (Map.Entry<String, StudentBean> entry : stuBean.entrySet()) {
			stuList.add(entry.getValue());
		}
		// 1.create a data object model
		ListWrapper lw = new ListWrapper(namePrefix, minGPA, stuList);

		// 2.create a context
		JAXBContext jc = JAXBContext.newInstance(lw.getClass());

		// 3. Create a marshaller
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

		// 4. instantiate the XML Schema..you might need to change the path, in this
		// case the
		// schema is in the project directory
		/*
		 * SchemaFactory sf =
		 * SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); Schema schema
		 * = sf.newSchema(new File("C:\\Users\\dothu\\e-commerse\\SIS-v2\\sis.xsd"));
		 * marshaller.setSchema(schema);
		 */

		// standard io
		StringWriter sw = new StringWriter();
		sw.write("<?xml version='1.0'?>\n");
		sw.write("<?xml-stylesheet type=\"text/xsl\" href=\"sis.xsl\"?>");
		sw.write("\n");

		// 5. marshall
		marshaller.marshal(lw, new StreamResult(sw)); // what and where to marshall
		System.out.println(sw.toString()); // for debugging
		String stri = (sw.toString()).replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		// return XML
		return stri;
		// return sw.toString();

	}

	public String getAsXML(String surname) throws Exception {
		Map<String, StudentBean> stuBean = new HashMap<String, StudentBean>();
		List<StudentBean> stuList = new ArrayList<StudentBean>();
		stuBean = stuDao.getStudent(surname);
		stuList.addAll(stuBean.values());

		// 1.create a data object model
		ListWrapper lw = new ListWrapper(surname, stuList);

		// 2.create a context
		JAXBContext jc = JAXBContext.newInstance(lw.getClass());

		// 3. Create a marshaller
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

		// standard io
		StringWriter sw = new StringWriter();
		sw.write("<?xml version='1.0'?>\n");
		sw.write("<?xml-stylesheet type=\"text/xsl\" href=\"sis.xsl\"?>");
		sw.write("\n");

		// 5. marshall
		marshaller.marshal(lw, new StreamResult(sw)); // what and where to marshall
		System.out.println(sw.toString()); // for debugging
		//String stri = (sw.toString()).replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		// return XML
		return sw.toString();

	}
	
	public int create(String sid, String givenName, String surname, int credit_taken, int credit_graduate) throws Exception {
		return stuDao.insert(sid, givenName, surname, credit_taken, credit_graduate);	
	}
	
	public int delete(String sid) throws Exception {
		return stuDao.delete(sid);
	}

}
