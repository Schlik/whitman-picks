<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fgomes.data.*" %>
<!doctype html>
<html lang="en">
<head>
    <link id="favicon" href="/favicon.ico" rel="shortcut icon">
    <meta content="width=device-width, target-density=160dip, initial-scale=1, maxmimum-scale=1" name="viewport">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Jay's Picks - Register</title>
    
	<link rel="stylesheet" href="/stylesheets/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
	<script src="/js/jquery-1.10.2.js"></script>
	<script src="/js/jquery-ui-1.10.3.custom.min.js"></script>
	<link rel="stylesheet" href="/stylesheets/style.css">	
	<script>
	$(function() {
		var name = $( "#name" ),
			email = $( "#email" ),
			password = $( "#password" ),
			repeatpassword = $( "#repeatpassword" ),
			allFields = $( [] ).add( name ).add( email ).add( password ).add( repeatpassword ),
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
			if ( o.val().length > max || o.val().length < min ) {
				o.addClass( "ui-state-error" );
				updateTips( "Length of " + n + " must be between " +
					min + " and " + max + "." );
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
		
		function checkSame( l, r ) {
			if( l.val() == r.val() )
				return true;
			else
			{
				l.addClass( "ui-state-error" );
				updateTips( "Password mismatch" );
				return false;
			}
		}

		$( "#register-form" ).dialog({
			autoOpen: true,
			height: 500,
			width: 400,
			modal: true,
			buttons: {
				"Register": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );

					bValid = bValid && checkLength( name, "username", 3, 16 );
					bValid = bValid && checkLength( email, "email", 6, 80 );
					bValid = bValid && checkLength( password, "password", 5, 80 );
					bValid = bValid && checkLength( repeatpassword, "repeatpassword", 5, 80 );

					bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
					// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
					bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
					bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z@.])+$/, "Password field only allow : a-z 0-9" );
					bValid = bValid && checkRegexp( repeatpassword, /^([0-9a-zA-Z@.])+$/, "Password field only allow : a-z 0-9" );

					bValid = bValid && checkSame( password, repeatpassword );
					
					if ( bValid ) {
						
						ajaxHandler( { email: email.val(), name: name.val(), password: password.val(), repeatpassword: repeatpassword.val(), submit: "Register" } );
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
		
		function ajaxHandler( d )
		{
			$.ajax({
				type: "POST",
				url: "/home",
				data: d
			});
		}
	});
	</script>
</head>
  <body>
  <%
  	Session ses = new Session( request );
  	String email = ses.getEmail();
  	String name = ses.getName();
  	if( email == null ) email = "";
  	if( name == null ) name = "";
  %>
		
     <div class="fg-wrapper">
     	<div id="top-right-nav">
			<a href="/login.jsp">Log In</a> | 
				<a href="/RegisterDialogForm.jsp">Register</a> 
			 | <a href="/registerjsp.jsp">RegisterAct</a>
			 | <a href="/home?a=spreads">Spreads</a>
     	</div>
     	<img src="/img/logo.png">
	    <h1>Register</h1></img>
		
	    	
		<header>
			<div class="header-wrapper">
	    	</div>
		</header>
	    <div class="clearboth"></div>
	    <section>

			<div id="register-form" title="Register for Pick">
				<p class="validateTips">All form fields are required.</p>
			
				<form>
					<fieldset>
						<label for="email">Email</label>
						<input type="text" name="email" id="email" value="<%= email %>" class="text ui-widget-content ui-corner-all" />
						<label for="name">Name</label>
						<input type="text" name="name" id="name" value="<%= name %>" class="text ui-widget-content ui-corner-all" />
						<label for="password">New Password</label>
						<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
						<label for="repeat_password">Repeat Password</label>
						<input type="password" name="repeatpassword" id="repeatpassword" value="" class="text ui-widget-content ui-corner-all" />
					</fieldset>
				</form>
			</div>
			
	    </section>
	    	    

	    <footer>
	    	<p class="fg-center">&copy;2013, All rights reserved.</p>
	    </footer>
    </div><!-- fg-wrapper -->
</body>
</html>
