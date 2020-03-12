package ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.EnrollmentBean;
import bean.StudentBean;
import model.SIS;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start/*")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String target = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Start() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		ServletContext context = this.getServletContext();
		SIS mSIS = null;
		try {
			mSIS = SIS.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.setAttribute("mSIS", mSIS);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");

		String xmlResult = "";

		ServletContext context = this.getServletContext();
		SIS mSIS = (SIS) context.getAttribute("mSIS");
		Map<String, StudentBean> mapStu = new HashMap<String, StudentBean>();
		Map<String, EnrollmentBean> mapEnroll = new HashMap<String, EnrollmentBean>();
		String surname="";
		String minCredit="";
		int count=0;

		if (request.getParameter("report") == null) {
			target = "/Form.jspx";
			
		}

		else {
			surname = request.getParameter("surname");
			minCredit = request.getParameter("minCredit");

			try {
				mapStu = mSIS.retrieveStudent(surname, Integer.parseInt(minCredit));
				mapEnroll = mSIS.retrieveEnrollment();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			count = mapStu.size();
			
		}
		
		if (request.getParameter("xmlBtn") != null) {
			surname = request.getParameter("surname");
			minCredit = request.getParameter("minCredit");

			try {
				xmlResult = mSIS.export(surname, Integer.parseInt(minCredit));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("xmlResult", xmlResult);
			target = "/Result.jspx";
		}
		
		if (request.getPathInfo().equals("/Ajax")) {
			response.getWriter().append("<br/>");
			if (count <= 1) {
				response.getWriter().append("There is " + count + " entry.");
			} else {
				response.getWriter().append("There are " + count + " entries.");
			}
			response.getWriter().append("<br/>");
			response.getWriter().append("<br/>");
			response.getWriter().append("<table>");
			response.getWriter()
					.append("<tr><th>Id</th><th>Name</th><th>Credits taken</th><th>Credits to graduate</th></tr>");
			for (Map.Entry<String, StudentBean> entry : mapStu.entrySet()) {
				response.getWriter()
						.append("<tr><td>" + entry.getValue().getSid() + "</td>" + "<td>"
								+ entry.getValue().getName() + "</td>" + "<td>" + entry.getValue().getCredit_taken()
								+ "</td>" + "<td>" + entry.getValue().getCredit_graduate() + "</td></tr>");
			}
			response.getWriter().append("</table>");
			response.getWriter().flush();
			return;
			

		}
		
		request.getRequestDispatcher(target).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
