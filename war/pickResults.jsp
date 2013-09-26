<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.google.appengine.api.datastore.PreparedQuery" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<body>

<%
    String user_email = request.getParameter("USER_EMAIL");
	int current_week = Integer.parseInt(request.getParameter("CURRENT_WEEK" ));
	int total_games = Integer.parseInt(request.getParameter("TOTAL_GAMES" ));

     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
     Key userPicksKey = KeyFactory.createKey( "UserEmail", user_email);
//     // Run an ancestor query to ensure we see the most up-to-date
//     // view of the Greetings belonging to the selected Guestbook.
   

//   Query query = new Query("picks",userPicksKey).addFilter("week_number", Query.FilterOperator.EQUAL, current_week );
//Wont run :     Query query = new Query("picks").addFilter("week_number", Query.FilterOperator.EQUAL, current_week );
     Query gaeQuery = new Query("picks");
     PreparedQuery pq = datastore.prepare(gaeQuery);

     List<Entity> all_picks = pq.asList(FetchOptions.Builder.withDefaults()); //datastore.prepare(query).asList(/*FetchOptions.Builder.withLimit(5)*/);

     int counter = 1;
     for (Entity pick : all_picks ) {
    	user_email = (String)pick.getProperty("email_address");
    	
    	String output = "<p>" + user_email;
    	
        for( int i = 1; i < total_games; i++){
        	String game_name = "game" + i;
        	output += ", " + pick.getProperty(game_name);
        }
        output += "<p>";
        out.print( output );
        output = "";
     }
        	
%>
  
</body>
</html>