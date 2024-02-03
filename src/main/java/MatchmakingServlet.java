

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.client.MongoIterable;

import algorithm.util.MatchFinder;
import algorithm.util.PairingClass;
import application.service.Transactions;
import database.util.DBOperations;
import entity.util.User;

/**
 * Servlet implementation class MatchmakingServlet
 */
@WebServlet("/MatchmakingServlet")
public class MatchmakingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result[] = request.getParameterValues("result");
		String res = result[0];
		Cookie cookies[] = request.getCookies();
		HashMap<String,String> map = new HashMap<>();
		for(Cookie i : cookies) {
			map.put(i.getName(), i.getValue());
		}
		String username = map.get("username");
		if(res.equals("make-match")) {
			DBOperations login = new DBOperations();
			Transactions convert = new Transactions();
			try {
				User user = convert.getUser(login.getUser(username));
				MongoIterable<Document> docList = login.getUserMatches(user);
				List<PairingClass> list = convert.getUsersPairing(docList);
				MatchFinder match = new MatchFinder(username,list);
				String bestMatch = match.getBestMatch();
				Document document = login.userForMatch(bestMatch);
				User userTwo = convert.userForMatch(document);
				User userOne = convert.userForMatch(user);
				System.out.println("BEST MATCH : ");
				for(Cookie i : request.getCookies()) {
					response.addCookie(i);
				}
				
				response.addCookie(new Cookie("playerA",userOne.getUsername()));
				response.addCookie(new Cookie("playerA_rating",Integer.toString(userOne.getRating())));
				response.addCookie(new Cookie("playerB",userTwo.getUsername()));
				response.addCookie(new Cookie("playerB_rating",Integer.toString(userTwo.getRating())));
				response.sendRedirect("DisplayMatch.jsp");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			response.reset();
			response.sendRedirect("index.html");
		}
	}

}
