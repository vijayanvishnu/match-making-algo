<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page language="java"  import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Best Match</title>
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
    <form action="ResultServlet"  method="post">
         <%
	        Cookie cookies[] = request.getCookies();
         	Map<String,String> map = new HashMap<>();
         	for(Cookie x : cookies){
         		map.put(x.getName(),x.getValue());
         		response.addCookie(x);
         	}
         	String playerB_username = map.get("playerB");
         	int playerB_rating = Integer.parseInt(map.get("playerB_rating"));
         	String playerA_username = map.get("playerA");
         	int playerA_rating = Integer.parseInt(map.get("playerA_rating"));
        %>
        &nbsp;
        <h3>Best Match</h3>
        &nbsp;
        <p>User Name : <%=playerB_username %></p>
        &nbsp;
        <p>Rating : <%=playerB_rating %></p>
        &nbsp;
        <button type="submit" value="next">Random Winner -></button>
    </form>
</body>
</html>
