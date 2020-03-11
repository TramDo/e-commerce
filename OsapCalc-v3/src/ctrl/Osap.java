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



import model.Loan;

/**
 * Servlet implementation class Osap
 */
@WebServlet("/Osap")
public class Osap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private Loan mLoan= new Loan();
	String gracePeriod;
	String fixedInterest;
	String appName;
	       
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
    @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		ServletContext context = this.getServletContext();
		
		
		gracePeriod = context.getInitParameter("gracePeriod");
		fixedInterest = context.getInitParameter("fixedInterest");
		appName = context.getInitParameter("applicationName");
		context.setAttribute("appName", appName);
		
		
		context.setAttribute("mLoan", new Loan());	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Hello, Got a GET request from Osap!");
		response.setContentType("text/plain");
		
		String target="/UI.jspx";
		String resultPage = "/Results.jsp";
				
		if (request.getParameter("calculate")==null)
			
		{		
			request.getRequestDispatcher(target).forward(request, response);
			return;
			
		}	
			
				
		ServletContext context = this.getServletContext();
		
		Double  interest, A, period;
		Double result = 0.0;
		Double graceInterest = 0.0;
		
		Loan mLoan = (Loan)context.getAttribute("mLoan");
		
		interest = Double.valueOf(request.getParameter("interest"));
		
		A = Double.valueOf(request.getParameter("principal"));
		
		period = Double.valueOf(request.getParameter("period"));
	
		String err="";
	
		try {
			graceInterest = mLoan.computeGraceInterest(A, Double.valueOf(gracePeriod), interest, Double.valueOf(fixedInterest));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//request.setAttribute("errMsg", e1.getMessage());
		}
	
		DecimalFormat df = new DecimalFormat("#.##");
		
		request.getSession().setAttribute("interest", interest);
		request.getSession().setAttribute("principal", A);
		request.getSession().setAttribute("period", period);
		
		String grace = request.getParameter("grace"); 
		
		try {
			result = mLoan.computePayment(period, A, interest, grace, Double.valueOf(gracePeriod), Double.valueOf(fixedInterest));
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			err = e.getMessage();
			request.setAttribute("errMsg", err);
				
		}
	
			if (grace == null)
			{
				request.getSession().setAttribute("graceInterest", 0.00);
				request.getSession().setAttribute("checked", "uncheck");
			}
			else
			{
				request.getSession().setAttribute("graceInterest", df.format(graceInterest));
				request.getSession().setAttribute("checked", "check");
			}
						
		request.getSession().setAttribute("payment", df.format(result));	
		
		if (err.equals("")) {
		
			request.getRequestDispatcher(resultPage).forward(request, response);
		}
		else
			request.getRequestDispatcher(target).forward(request, response);
			
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
