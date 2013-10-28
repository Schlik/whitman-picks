<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.apphosting.api.ApiProxy" %>
<%@ page import="com.google.apphosting.api.ApiProxy.Environment" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.fgomes.util.*" %>
<%@ page import="com.fgomes.data.*" %>
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
	    boolean b_logged_in = false;
		String param_email = "";
		String param_name = "";
		boolean b_admin = false;
		int total_games = 18;
		int iCurrentWeek = 1;
		boolean needSetup = DAL.needSetup();
		
		Session sesdata = new Session( request );
		
		b_logged_in = sesdata.isLoggedIn();
		param_name = sesdata.getName();
		param_email = sesdata.getEmail();
		b_admin = sesdata.isAdmin();
		total_games = sesdata.getTotalGames();
		iCurrentWeek = sesdata.getCurrentWeek();
	    
	    String strWeek = request.getParameter( "week" );
	    int week = 0;
	  	
   
   		if( strWeek != null && !strWeek.isEmpty() ) week = Integer.parseInt( strWeek );
   		 
	  	if( week == 0 ) week = iCurrentWeek;
	  	
	  %>
     <div class="fg-wrapper">
     	<div id="top-right-nav">
     			<% 
     			/* */
				if( b_logged_in )
				{
					out.print( param_email + " | <a href=\"/home?a=logout\">Log Out</a>" );
					out.print( " | <a href=\"/home?a=passwd\">Change Pwd</a>" );
					if( b_admin )
					{
						out.print( " | <a href=\"/home?a=users\">Users</a>" );
						if( needSetup )
						{
							out.print( " | <a href=\"/home?a=setup\">Setup</a>" );
						}
					}
				}
				else
				{
					out.print( "<a href=\"/login.jsp\">Log In</a> | " 
						+ "<a href=\"/RegisterDialogForm.jsp\">Register</a>" ); // + "<button id=\"create-user\">Register</button>" );
					if( needSetup )
					{
						out.print( " | <a href=\"/home?a=setup\">Setup</a>" );
					}
					out.print( " | <a href=\"/registerjsp.jsp\">RegisterAct</a>" );
				}
				
				out.print( " | <a href=\"/home?a=spreads\">Spreads</a>" );
				out.print( "\n" );
				
				/* */
			   %>
			   <br />
			   <span style="color:red">Login to make your picks<br/> or register if you are<br/> visiting for the first time</span>
     	</div>
     	<img src="/img/logo.png">
	    <h1>Weekly Game Picks</h1></img>
		
	    	
		<header>
			<div class="header-wrapper">
		    	<nav>
		    		<ul id="main-menu">
						<li>Weeks:</li>
		    			<%
		    				for( int i = 1; i < 18; i++ )
		    				{
		    				   if( i == week )
		    				   {
		    					  out.print( "<li><a  class=\"active-week\" href=\"/?week=" + i + "\">" + i + "</a></li>\n" );
		    				   }
		    				   else
		    				   {
		    				      out.print( "<li><a href=\"/?week=" + i + "\">" + i + "</a></li>\n" );
		    				   }
		    				}
		    			%>
		    		</ul>
		    	</nav>
	    	</div>
		</header>
	    <div class="clearboth"></div>
	    <section>
	    	<form action="/submitPicks" method="post" id="pick-submit">
	    	<%
				out.print( NFLScores.getGameWithSpreads( week, iCurrentWeek ) );

		        if( ( week == iCurrentWeek ) && ( b_logged_in ) )
		        {
		        %>
		        	<fieldset>
		    		<input type="hidden" name="picker_email" id="picker_email" value="<%= param_email %>" >
		    		<input type="hidden" name="current_week" id="current_week" value="<%= iCurrentWeek %>">
		    		<input type="hidden" name="name" id="name" value="<%= param_name %>">
		    		<input type="hidden" name="total_games" id="total_games" value="<%= total_games %>">
		    		<input type="submit" value="Submit Picks" name="idsubmit" id="idsubmit">
		    		</fieldset>
		    	<%
		    	}
		    	%>
	    	</form>
	    </section>
	    	    
	    <div id="register-form" title="Register for Pick">
			<p class="validateTips">All form fields are required.</p>
		
			<form>
			<fieldset>
				<label for="name">Name</label>
				<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
				<label for="email">Email</label>
				<input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
				<label for="password">Password</label>
				<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
			</fieldset>
			</form>
		</div>


	    <div id="login-form" title="Login to Pick">
			<p class="validateTips"></p>
			<form>
			<fieldset>
				<label for="email">Email</label>
				<input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
				<label for="password">Password</label>
				<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
			</fieldset>
			</form>
		</div>

	    
	    <footer>
	    	<p class="fg-center">&copy;2013, All rights reserved.</p>
	    </footer>
    </div><!-- fg-wrapper -->
    
    <script>
	$(function() {
		var name = $( "#name" ),
			email = $( "#email" ),
			password = $( "#password" ),
			allFields = $( [] ).add( name ).add( email ).add( password ),
			loginFields = $( [] ).add( email ).add( password ),
			tips = $( ".validateTips" );

		function updateTips( t ) {
			tips
				.text( t )
				.addClass( "ui-state-highlight" );
			setTimeout(function() {
				tips.removeClass( "ui-state-highlight", 1500 );
			}, 500 );
		}

		function checkLength( o, n, min, max ) {
			var l = o.val().length;
			if ( l > max || l < min ) {
				o.addClass( "ui-state-error" );
				updateTips( "Length of " + n + " must be between " +
					min + " and " + max + ". But is " + l );
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp( o, regexp, n ) {
			if ( !( regexp.test( o.val() ) ) ) {
				o.addClass( "ui-state-error" );
				updateTips( n );
				return false;
			} else {
				return true;
			}
		}

		$( "#register-form" ).dialog({
			autoOpen: false,
			height: 350,
			width: 350,
			modal: true,
			buttons: {
				"Register": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );

					bValid = bValid && checkLength( name, "username", 3, 16 );
					bValid = bValid && checkLength( email, "email", 6, 80 );
					bValid = bValid && checkLength( password, "password", 5, 16 );

					bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
					// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
					bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
					bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z A-Z 0-9" );

					if ( bValid ) {
						$.post( "/", { picker_email: email.val(), name: name.val(), password: password.val() } );
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});

		$( "#create-user" )
			.button()
			.click(function() {
				$( "#register-form" ).dialog( "open" );
			});
		$( "#login" )
			.button()
			.click( function() {
				$( "#login-form" ).dialog( "open" );
			});
		
		$( "#login-form" ).dialog({
			autoOpen: false,
			height: 350,
			width: 350,
			modal: true,
			buttons: {
				"Login": function() {
					var bValid = true;
					loginFields.removeClass( "ui-state-error" );

					bValid = bValid && checkLength( email, "email", 6, 80 );
					bValid = bValid && checkLength( password, "password", 5, 16 );

					// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
					bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
					bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z A-Z 0-9" );

					if ( bValid ) {
					    alert( "email: " + email.val() + " name: " + password );
						$.post( "/", { picker_email: email.val(), password: password.val() } );
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				loginFields.val( "" ).removeClass( "ui-state-error" );
			}
		});
		
	});
    </script>
  </body>
</html>
