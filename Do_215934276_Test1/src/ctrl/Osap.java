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
@WebServlet({ "/Osap/*" })
public class Osap extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private Loan mLoan= new Loan();
	String gracePeriod;
	String fixedInterest;
	String appName;
	String copyRight;

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
		copyRight = context.getInitParameter("copyRight");
		context.setAttribute("appName", appName);

		context.setAttribute("mLoan", new Loan());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Hello, Got a GET request from Osap!");
		System.out.println(request.getRequestURI());
		System.out.println(request.getPathInfo());

		response.setContentType("text/plain");

		String target = "/UI.jspx";
		String resultPage = "/Results.jsp";
		Double result = 0.0;
		String err = "";
		DecimalFormat df = new DecimalFormat("#.##");
		String customGracePeriod = "";
		String username = "";
		Double graceInterest = 0.0;

		ServletContext context = this.getServletContext();
		Loan mLoan = (Loan) context.getAttribute("mLoan");

		if (("/Flexible".equals(request.getPathInfo()))) {

			request.setAttribute("flexible", "flexible");
			request.setAttribute("copyRight", copyRight);

		}

		if (request.getParameter("calculate") == null)

		{
			request.getRequestDispatcher(target).forward(request, response);
//			return;

		}

		Double interest, A, period;

		interest = Double.valueOf(request.getParameter("interest"));

		A = Double.valueOf(request.getParameter("principal"));

		period = Double.valueOf(request.getParameter("period"));

		username = request.getParameter("username");
		customGracePeriod = request.getParameter("gracePeriod");

		if (request.getContextPath().contains("/Flexible")) {

			try {
				mLoan.checkUsername(username);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				err = e.getMessage();
				request.setAttribute("errMsg", err);
			}

		}

		try {
			if (customGracePeriod != null) {
				graceInterest = mLoan.computeGraceInterest(A, Double.valueOf(customGracePeriod), interest,
						Double.valueOf(fixedInterest));
			} else
				graceInterest = mLoan.computeGraceInterest(A, Double.valueOf(gracePeriod), interest,
						Double.valueOf(fixedInterest));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		String grace = request.getParameter("grace");

		try {
			if (customGracePeriod != null) {
				result = mLoan.computePayment(period, A, interest, customGracePeriod, Double.valueOf(customGracePeriod),
						Double.valueOf(fixedInterest));
			} else
				result = mLoan.computePayment(period, A, interest, grace, Double.valueOf(gracePeriod),
						Double.valueOf(fixedInterest));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			err = e.getMessage();
			request.setAttribute("errMsg", err);
		}

		request.getSession().setAttribute("interest", interest);
		request.getSession().setAttribute("principal", A);
		request.getSession().setAttribute("period", period);
		request.getSession().setAttribute("gracePeriod", customGracePeriod);
		request.getSession().setAttribute("username", username);

		if (grace == null && customGracePeriod == null) {
			request.getSession().setAttribute("graceInterest", 0.00);
			request.getSession().setAttribute("checked", "uncheck");
		} else if (grace != null || customGracePeriod != null) {
			request.getSession().setAttribute("graceInterest", df.format(graceInterest));
			request.getSession().setAttribute("checked", "check");
		}

		request.getSession().setAttribute("payment", df.format(result));

		if ("".equals(err)) {
			request.getRequestDispatcher(resultPage).forward(request, response);
		} else {
			request.getRequestDispatcher(target).forward(request, response);

		}

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
