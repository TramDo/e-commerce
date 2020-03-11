package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Osap
 */
@WebServlet("/Osap/aSamplePath")
public class Osap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Osap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Hello, Got a GET request from Osap!");
		response.setContentType("text/plain");
		Writer resOut = response.getWriter();
		/*resOut.write("Hello, World!\n");
		
		String clientIP = request.getRemoteAddr();
		resOut.write("Client IP: " + clientIP + "\n");
		
		int clientPort = request.getRemotePort();
		resOut.write("Client port: " + clientPort + "\n");
		
		if (clientIP.equals("0:0:0:0:0:0:0:1"))
			resOut.write("This IP has been flagged!\n");
		
		String clientProtocol = request.getProtocol();
		resOut.write("Client protocol: " + clientProtocol + "\n");
		
		resOut.write("Client method:" + request.getMethod() + "\n");
		
		String clientQueryString = request.getQueryString();
		resOut.write("Query String: " + clientQueryString + "\n");
		
		String foo = request.getParameter("foo");
		resOut.write("Query Param: foo=" + foo + "\n");
		
		String uri = request.getRequestURI();
		resOut.write("Request URI : " + uri + "\n");
		
		String serPath = request.getServletPath();
		resOut.write("Request Servlet Path: " + serPath + "\n");*/
		
		//resOut.write("---- Info from context object ----\n");
		//resOut.write("---- Application info ----\n");
		//resOut.write("---- Sesion info ---\n");
		
		ServletContext context = this.getServletContext();
		
		String applicantName = context.getInitParameter("applicantName");
		String applicationName = context.getInitParameter("applicationName");
		String principal = context.getInitParameter("principal");
		
		/*String contextPath=context.getContextPath();
		String realPath = context.getRealPath("Osap");
		*/
		HttpSession session = request.getSession();
		double interest, A, period;
		if (request.getParameter("interest") == null)
		{
			 interest = Double.valueOf(context.getInitParameter("interest"));
		}
		else
			 interest = Double.valueOf(request.getParameter("interest"));
		session.setAttribute("pastInterest",Double.toString(interest) );
		
		if (request.getParameter("principal") == null)
		{
			A = Double.valueOf(context.getInitParameter("principal"));
		}
		else
			A = Double.valueOf(request.getParameter("principal"));
		session.setAttribute("pastPrincipal", Double.toString(A) );
		
		if (request.getParameter("period") == null)
		{
			period = Double.valueOf(context.getInitParameter("period"));
		}
		else
			period = Double.valueOf(request.getParameter("period"));
		session.setAttribute("pastPeriod", Double.toString(period));		
		
		
				
		/*resOut.write("Application Name = " + applicationName + "\n");
		resOut.write("Context Path=" + contextPath + "\n");
		resOut.write("Real path of Osap\n" + realPath + "\n");
		resOut.write("Applicant Name = " + applicantName + "\n");
		resOut.write("principal=" + session.getAttribute("pastPrincipal") + "\n");
		resOut.write("period=" + session.getAttribute("pastPeriod") + "\n");
		resOut.write("interest=" + session.getAttribute("pastInterest") + "\n");*/
		
		
		
		double rate = interest/100;
		
		double result = ((rate / 12) * A)/(1- Math.pow((1+(rate/12)),-period));
		
		DecimalFormat df = new DecimalFormat("#.#");
		
		
		resOut.write("---- Monthly payments ----\n");
		resOut.write("Based on Principal=" + A);
		resOut.write(" Period=" + period);
		resOut.write(" Interest=" + interest + "\n");
		resOut.write("Monthly payments: " + df.format(result));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
