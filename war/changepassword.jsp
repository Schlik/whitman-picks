<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fgomes.data.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
  <head>
    <link id="favicon" href="/favicon.ico" rel="shortcut icon">
    <meta content="width=device-width, target-density=160dip, initial-scale=1, maxmimum-scale=1" name="viewport">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Jay's Picks - Change Password</title>
	
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
  	Session ses = new Session( request );
  	String email = ses.getEmail();
  %>
		
     <div class="fg-wrapper">
     	<div id="top-right-nav">
     	</div>
     	<img src="/img/logo.png">
	    <h1>Change Password</h1></img>
		
	    	
		<header>
			<div class="header-wrapper">
	    	</div>
		</header>
	    <div class="clearboth"></div>
	    <section>
		    <div id="login-form" title="Login to Pick">
				<%
					try
					{
						String err = request.getAttribute( "error" ).toString();
						out.println( err );
					}
					catch( Exception e )
					{
					}
				%>
				<p class="validateTips">
				</p>
	    		<form action="/home" method="post" id="loginForm">
					<fieldset>
						<label for="email">Email</label>
						<input type="text" name="email" id="email" value="<%= email %>" class="text ui-widget-content ui-corner-all" />
						<label for="oldpassword">Old Password</label>
						<input type="password" name="oldpassword" id="oldpassword" value="" class="text ui-widget-content ui-corner-all" />
						<label for="password">New Password</label>
						<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
						<label for="repeat_password">Repeat Password</label>
						<input type="password" name="repeatpassword" id="repeatpassword" value="" class="text ui-widget-content ui-corner-all" />
						<input type="submit" value="ChangePassword" id="submit" name="submit" />
					</fieldset>
				</form>
			</div>
			
			<div id="result">
			</div>
	    </section>
	    	    

	    <footer>
	    	<p class="fg-center">&copy;2013, All rights reserved.</p>
	    </footer>
    </div><!-- fg-wrapper -->
    
 </body>
</html>
