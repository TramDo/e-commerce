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
@WebServlet("/Osap")
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
		
		System.out.println("Hello, Got a GET request from Osap!");
		response.setContentType("text/plain");
		Writer resOut = response.getWriter();
		
		String target="/UI.jspx";
		String resultPage = "/Results.jsp";
		
		if (request.getParameter("calculate")==null)
			
		{		
			request.getRequestDispatcher(target).forward(request, response);
			return;
			
		}	
			
				
		ServletContext context = this.getServletContext();
		
		
		String grace = request.getParameter("grace"); //value = on or off
		Double gracePeriod = Double.valueOf(context.getInitParameter("gracePeriod"));
		Double fixedInterest = Double.valueOf(context.getInitParameter("fixedInterest"));
	
		double totalInterest, interest, A, period;
		if (request.getParameter("interest") == null)
		{
			 interest = Double.valueOf(context.getInitParameter("interest"));
		}
		else
			 interest = Double.valueOf(request.getParameter("interest"));
		
		
		if (request.getParameter("principal") == null)
		{
			A = Double.valueOf(context.getInitParameter("principal"));
		}
		else
			A = Double.valueOf(request.getParameter("principal"));
		
		
		if (request.getParameter("period") == null)
		{
			period = Double.valueOf(context.getInitParameter("period"));
		}
		else
			period = Double.valueOf(request.getParameter("period"));
					
		double rate = interest/100; //user supply
		
		totalInterest = rate + (fixedInterest/100);
		
		double graceInterest = A * (totalInterest/12) * gracePeriod;
		
		double result = (totalInterest / 12) * A /(1- Math.pow((1+(totalInterest/12)),-period));
		
		double resultGrace = result + (graceInterest/gracePeriod);
		
		DecimalFormat df = new DecimalFormat("#.##");
		
			if (grace == null)
			{
				request.getSession().setAttribute("graceInterest", 0.00);
				request.getSession().setAttribute("payment", df.format(result));	
				
			}
	
		else
		{
			
			request.getSession().setAttribute("graceInterest", df.format(graceInterest));
			request.getSession().setAttribute("payment", df.format(resultGrace));
		}
		
		request.getRequestDispatcher(resultPage).forward(request, response);
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
