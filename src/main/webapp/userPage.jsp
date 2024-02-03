<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page language="java"  import="database.util.DBOperations"%>
<%@page language="java"  import="application.service.Transactions"%>
<%@page language="java"  import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style-user.css">
    <style>
                *,
        *:before,
        *:after{
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
    <form action="MatchmakingServlet"  method="post">
        <p>Current Session : <%
	        Cookie cookies[] = request.getCookies();
        	Map<String,String> map = new HashMap<>();
	        for(Cookie i : cookies){
	        	map.put(i.getName(),i.getValue());
	        	response.addCookie(i);
	        }
	        String username = map.get("username");
	        String password = map.get("password");
	        DBOperations login = new DBOperations();
	        Transactions tns = new Transactions();
	        int rating = tns.getUser(login.getUser(username)).getRating();
        %><%= username + "," + rating%></p>
        &nbsp;
        <h3>Avialable Options</h3>
        <button type="submit" value="make-match" name="result">Make Match</button>
        <button type="submit" value="log-out" name="result">Log Out</button>
    </form>
</body>
</html>
