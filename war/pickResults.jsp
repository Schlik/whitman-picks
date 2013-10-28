<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query.Filter" %>
<%@ page import="com.google.appengine.api.datastore.Query.FilterPredicate" %>
<%@ page import="com.google.appengine.api.datastore.Query.FilterOperator" %>
<%@ page import="com.google.appengine.api.datastore.Query.CompositeFilter" %>
<%@ page import="com.google.appengine.api.datastore.Query.CompositeFilterOperator" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.google.appengine.api.datastore.PreparedQuery" %>
<%@ page import="com.google.appengine.api.datastore.Query.SortDirection" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>

<html>
  <head>
    <link id="favicon" href="/favicon.ico" rel="shortcut icon">
    <meta content="width=device-width, target-density=160dip, initial-scale=1, maxmimum-scale=1" name="viewport">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Jay's Picks</title>
	
	<link rel="stylesheet" href="/stylesheets/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
	<script src="/js/jquery-1.10.2.js"></script>
	<script src="/js/jquery-ui-1.10.3.custom.min.js"></script>
	
	<link rel="stylesheet" href="/stylesheets/animate.css">
	<script src="/js/jquery.easing.1.3.js"></script>
	<script src="/js/jquery.touchSwipe.min.js"></script>
	<script src="/js/jquery.liquid-slider.js"></script>
	<link rel="stylesheet" href="/stylesheets/liquid-slider.css">
	<link rel="stylesheet" href="/stylesheets/style.css">	
  </head>

<body>
	<%
    String user_email = request.getParameter("picker_email");
	int current_week = Integer.parseInt(request.getParameter("current_week" ));
	int total_games = Integer.parseInt(request.getParameter("total_games" ));
	String date_picked = request.getParameter( "date_picked" );
	
	%>
	
     <div class="fg-wrapper">
     	<div id="top-right-nav">
 			<% 
 			
			if( user_email != null )
			{
				out.print( user_email + " | <a href=\"/index.jsp?email=" + user_email + "\">Back to Home</a>\n" );
			}
			else
			{
				out.print( "<a href=\"/\">Back to Home</a>\n" );
			}
			
		   %>
			   
     	</div>
     	<a href="/"><img src="/img/logo.png"></a>
	    <h1>Your pick</h1></img>
		
	    	
<%

     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
     Key userPicksKey = KeyFactory.createKey( "user_email", user_email);
     				
   
	Filter weekFilter = new FilterPredicate( "week_number", FilterOperator.EQUAL, current_week );
	Filter dateFilter = new FilterPredicate( "date_picked", FilterOperator.GREATER_THAN_OR_EQUAL, date_picked );
	//Filter comboFilter = CompositeFilterOperator.and( weekFilter, dateFilter );
	
	//Query gaeQuery = new Query("picks", userPicksKey).addFilter("week_number", Query.FilterOperator.EQUAL, current_week );
	Query gaeQuery = new Query("picks", userPicksKey).setFilter( weekFilter )
						.addSort( "date_picked", SortDirection.DESCENDING );
	PreparedQuery pq = datastore.prepare(gaeQuery);

     List<Entity> all_picks = pq.asList(FetchOptions.Builder.withDefaults()); 

     int counter = 1;
     for (Entity pick : all_picks ) {
    	
    	String output = "<p>Date of Pick: " + pick.getProperty( "date_picked" ) + " " + pick.getProperty("user_email");
    	output += " Week: " + current_week + " Picks: ";
    	
        for( int i = 1; i <= total_games; i++){
        	String game_name = "game" + i;
        	output += pick.getProperty(game_name) + (( i == total_games ) ? "" : ", " );
        }
        output += "<p>";
        out.print( output );
        output = "";
        break;
     }
    
        	
%>
	</div>
</body>
</html>