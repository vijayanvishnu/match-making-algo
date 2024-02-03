

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import algorithm.util.RandomWinner;
import database.util.DBOperations;
import entity.util.Game;
import entity.util.MyException;
import entity.util.User;

/**
 * Servlet implementation class RandomWinner
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookies[] = request.getCookies();
		DBOperations login = new DBOperations();
		HashMap<String,String> map = new HashMap<>();
		for(Cookie i : cookies) {
			map.put(i.getName(),i.getValue());
		}
		User userOne = new User(map.get("playerA"),Integer.parseInt(map.get("playerA_rating")));
		User userTwo = new User(map.get("playerB"),Integer.parseInt(map.get("playerB_rating")));
		Game game = new Game(userOne,userTwo);
		RandomWinner random = new RandomWinner();
		User winner = random.getRandomWinner(userTwo, userOne);
		game.setWinner(winner);
		try {
			if(winner == null ) {
				userOne.setRating(3 + userOne.getRating());
				userTwo.setRating(3 + userTwo.getRating());
			}
			else if(userOne != winner) {
				userOne.setRating(userOne.getRating()- 3);
				userTwo.setRating(userTwo.getRating()+ 5);
			}
			else {
				userTwo.setRating(userTwo.getRating()- 3);
				userOne.setRating(userOne.getRating()+ 5);
			}
			login.addGame(game);
			login.updateRating(userOne);
			login.updateRating(userTwo);
		} catch (MyException e) {
			e.printStackTrace();
		}
		
		if(winner == null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<body style='background-color: #080710; text-align : center; color : white;'>");
			out.println("<h1>"+"match draw"+"</h1>");
			out.print("<h2>Updated Rating : </h2><p></p>");
			out.print("<h3>User name : <i>"+userOne.getUsername()+"</i></h3>");
			out.print("<h3>Rating : <i>"+userOne.getRating()+"</i></h3>");
			out.println("<h2><i><a href= 'userPage.jsp'>Play Again !</a></i></h2>");
			out.println("</body>");
		}else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<body style='background-color: #080710; text-align : center; color : white;'>");
			out.println("<h1>"+"Winner : "+ winner.getUsername()+"</h1>");
			out.print("<h2>Updated Rating : </h2><p></p>");
			out.print("<h3>User name : <i>"+userOne.getUsername()+"</i></h3>");
			out.print("<h3>Rating : <i>"+userOne.getRating()+"</i></h3>");
			out.println("<h2><i><a href= 'userPage.jsp'>Play Again !</a></i></h2>");
			out.println("</body>");
		}
		// response.sendRedirect("userPage.jsp");
	}

}
