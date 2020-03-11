package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Analytics
 */
@WebServlet("/admin")
public class Analytics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String firstValue, maxValue;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Analytics() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("count") != null) {
			maxValue = request.getSession().getAttribute("maxValue").toString();
			request.getSession().setAttribute("maxPrincipal", maxValue);
		} else {

			firstValue = request.getSession().getAttribute("principal").toString();
			request.getSession().setAttribute("maxPrincipal", firstValue);

		}
		if (request.getQueryString() != null) {
			request.setAttribute("applicantName", request.getSession().getAttribute("username"));
		}
		request.getRequestDispatcher("/MaxPrincipal.jspx").forward(request, response);
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
