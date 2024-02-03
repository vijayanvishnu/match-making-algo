

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
import entity.util.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		DBOperations object = new DBOperations();
		StringBuilder errors = new StringBuilder();
		try {
			boolean res = object.emailExists(email);
			if(res) {
				errors.append("email exists,");
			}
			res = false;
			res = object.usernameExists(username);
			if(res) {
				errors.append("username exists,");
			}
			res = false;
			res = password.matches("^([a-zA-Z0-9@*#]{8,15})$");
			if(!res) {
				errors.append("create a strong password,");
			}
			if(errors.length()>0) {
				errors = new StringBuilder(errors.substring(0, errors.length()-1));
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("<body style='background-color: #080710; text-align : center; color : white;'>");
				out.println("<h1>"+errors.toString()+"</h1>");
				out.println("<h2><i><a href= 'register.html'>Register</a></i></h2>");
				out.println("</body>");
			}
			else {
				Cookie cookies = new Cookie("username",username);
				response.addCookie(cookies);
				cookies = new Cookie("password",password);
				response.addCookie(cookies);
				User user = new User(name,username,password,email);
				object.addUser(user);
				response.sendRedirect("userPage.jsp");
			}
		} catch (MyException e) {
			e.printStackTrace();
		}
		
	}

}
