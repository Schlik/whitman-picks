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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<body>

<%
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Key userPicksKey = KeyFactory.createKey( "UserEmail", userEmail);
    // Run an ancestor query to ensure we see the most up-to-date
    // view of the Greetings belonging to the selected Guestbook.
    Query query = new Query("userPicks",userPicksKey);
    List<Entity> all_picks = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));

        for (Entity pick : all_picks ) {
        	
           pageContext.setAttribute("user_picks", pick.getProperty("selections"));
    
        %>
        <p>Your picks are : ${fn:escapeXml(user_picks)}</p>
        <%
    }
%>
  
</body>
</html>