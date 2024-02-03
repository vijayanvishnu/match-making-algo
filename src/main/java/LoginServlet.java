

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.util.DBOperations;
import entity.util.MyException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String click[] = request.getParameterValues("result");
		if(click != null && click[0].equals("register")) {
			response.sendRedirect("register.html");
			return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DBOperations object = new DBOperations();
		PrintWriter out = response.getWriter();
		boolean result;
		try {
			result = object.userPresense(username, password);
			if(!result) {
				response.setContentType("text/html");
				out.println("<body style='background-color: #080710; text-align : center; color : white;'>");
				out.print("<h3>Rating : <i>"+"No user founded"+"</i></h3><p></p>");
				out.println("<h2><i><a href= 'register.html'>Register</a></i></h2>");
				out.println("</body>");
			}
			else {
				Cookie cookies = new Cookie("username", username);
				response.addCookie(cookies);
				cookies = new Cookie("password",password);
				response.addCookie(cookies);
				response.sendRedirect("userPage.jsp");
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
