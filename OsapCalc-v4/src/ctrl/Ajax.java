package ctrl;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Loan;

/**
 * Servlet implementation class Ajax
 */
@WebServlet("/Osap/Ajax")
public class Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String gracePeriod;
	String fixedInterest;
	String appName;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ajax() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
		response.setContentType("text/plain");
		System.out.println(request.getPathInfo());
		Double result = 0.0;
		String err="";
		DecimalFormat df = new DecimalFormat("#.##");
		
		ServletContext context = this.getServletContext();
		
		Double  interest, A, period;
		
		Double graceInterest = 0.0;
		
		Loan mLoan = (Loan)context.getAttribute("mLoan");
		
		interest = Double.valueOf(request.getParameter("interest"));
		
		A = Double.valueOf(request.getParameter("principal"));
		
		period = Double.valueOf(request.getParameter("period"));
	
		
	
		try {
			graceInterest = mLoan.computeGraceInterest(A, Double.valueOf(gracePeriod), interest, Double.valueOf(fixedInterest));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//request.setAttribute("errMsg", e1.getMessage());
		}
	
		
		
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
				request.getSession().setAttribute("graceInterest", "0.00");
				
			}
			else
			{
				request.getSession().setAttribute("graceInterest", df.format(graceInterest));
				
			}
				
		request.getSession().setAttribute("payment", df.format(result));
			
		response.getWriter().append("Grace Period Interest: $");
		
		response.getWriter().append((String)request.getSession().getAttribute("graceInterest"));

		
		response.getWriter().append("<br/>");
	
		
		response.getWriter().append("Monthly paymnet: $");

		response.getWriter().append((String)request.getSession().getAttribute("payment"));
		response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
