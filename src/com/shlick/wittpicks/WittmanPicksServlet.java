package com.shlick.wittpicks;
import com.shlick.data.DAL;
import com.shlick.data.Session;
import com.shlick.data.User;
import com.shlick.data.UserRole;
import com.shlick.data.setup;
import com.shlick.util.Attributes;
import com.shlick.util.RequestDispatcher;
import com.shlick.util.Spreads;
import com.shlick.util.Util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.util.logging.*;
//import java.util.Enumeration;
import java.util.List;
import java.util.Map;


@SuppressWarnings("serial")
public class WittmanPicksServlet extends HttpServlet {	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        
		String email = req.getParameter( "email" );
		String pwd = req.getParameter( "password" );
		
		String submit = req.getParameter( "submit" );
		String reset = req.getParameter( "reset" );
		
		if( reset != null && reset.equals( "Reset" ) )
		{
			if( email == null || email.isEmpty() )
			{
				req.setAttribute("error", "enter email" );
			}
			else
			{
				if( DAL.resetPassword( email, email ) )
				{
					req.setAttribute("error", "Password has been reset to your email id. Please login and change the password." );
				}
				else
				{
					req.setAttribute("error", "Password reset failed." );					
				}
			}
			RequestDispatcher.forward( req,  resp,  "/login.jsp" );
		}
		else if( submit.equals( "Login" ) )
		{
			boolean success = DAL.login( email, pwd );
			if( success )
			{
				User u = DAL.getUser( email );
				boolean admin = DAL.isAdmin(email);
				
				HttpSession ses = req.getSession( false );
				Session.resetSession( ses );
				ses = req.getSession();
				
				ses.setAttribute( "valid", "true" );
				ses.setAttribute( "email",  email );
				ses.setAttribute( "name", u.getName() );
				ses.setAttribute( "password", u.getPassword() );
				ses.setAttribute( "total_games", req.getParameter( "total_games" ) );
				
				ses.setAttribute( "isadmin", admin?"true":"false" );
				//ses.setAttribute( "myuser",  u );
				
				RequestDispatcher.forward(req, resp, "/index.jsp" );
				//resp.sendRedirect( "/index.jsp" );
			}
			else
			{
				req.setAttribute("error", "Login failed." );
				RequestDispatcher.forward( req, resp, "/login.jsp" );
				//resp.sendRedirect( "/login.jsp?loginfailed=true" );
			}
		}
		else if( submit.equals( "Logout" ) )
		{
			HttpSession ses = req.getSession();

			Session.resetSession( ses );
			
			RequestDispatcher.forward( req,  resp,  "/index.jsp" );
		}
		else if( submit.equals( "ChangePassword" ) )
		{
			boolean success = false;
			String oldpwd = req.getParameter( "oldpassword" );
			String rptpwd = req.getParameter( "repeatpassword" );
			if( email != null && oldpwd != null && pwd != null && rptpwd != null && pwd.equals(rptpwd) )
			{
				success = DAL.resetPassword( email, pwd );
			}
			if( !success )
			{
				req.setAttribute("error", "Must enter email, old password, new password and repeat password" );
				RequestDispatcher.forward( req,  resp, "/changepassword.jsp" );
			}
			else
			{
				resp.sendRedirect("/home?a=logout" );
			}
		}
		else if( submit.equals( "Register" ) )
		{
			String name = req.getParameter( "name" );
			User u = DAL.getUser( email );
			if( u == null )
			{
				u = new User( email, name, pwd );
				boolean success = DAL.addUser( u );
				
				if( success )
				{
					Logger.getGlobal().info( "Registered the new user. Forwarding to login page.");
					//RequestDispatcher.forward(req, resp, "/login.jsp" );
					resp.sendRedirect( "/login.jsp" );
				}
				else
				{
					Logger.getGlobal().info( "Could not register the user.  Please try again." );
					RequestDispatcher.forward( req,  resp,  "/RegisterDialogForm.jsp");
				}
				//resp.sendRedirect( "/index.jsp" );
			}
			else
			{
				req.setAttribute("error", "Email already registered" );
				RequestDispatcher.forward( req, resp, "/login.jsp" );
				//resp.sendRedirect( "/login.jsp" );
			}
		}
		else
		{
			resp.getWriter().println( "Do not recognize " + req.getRequestURI() + "<br/>" );
			showSessionInfo( resp.getWriter(), req.getSession() );
			//resp.sendRedirect( "/" );
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
        PrintWriter out = resp.getWriter();
        
		String action = req.getParameter( "a" );
		
		//resp.setContentType("text/html");
		if( action == null || action.isEmpty() )
		{
			resp.sendRedirect( "/" );
		}
		else if( action.equals( "logout" ) )
		{
			HttpSession ses = req.getSession();

			Session.resetSession( ses );
			
			RequestDispatcher.forward( req,  resp,  "/index.jsp" );
		}
		else if( action.equals( "users" ) )
		{
			
			HttpSession ses = req.getSession( false );
			if( ses != null )
			{
				String email = (String)ses.getAttribute( "email" );
				if( ( email !=null ) && DAL.isAdmin(email) )
				{
					String valid = ses.getAttribute("valid").toString();
					if( valid.equals("true") )
					{
						resp.setContentType("text/html");
						showUserRoles( out, resp );
					}
				}
				else
				{
					resp.sendRedirect( "/login.jsp?email=" + email );
				}
			}
			else
			{
				resp.sendRedirect( "/login.jsp?notinsession" );
			}
		}
		else if( action.equals( "setup" ) )
		{
			boolean needSetup = DAL.needSetup();
			
			if( needSetup )
			{
				setup.init();
				resp.setContentType("text/html");
				showUserRoles( out, resp );
			}
			else
			{
				resp.sendRedirect( "/" );
			}
		}
		else if( action.equals( "passwd" ) )
		{
			RequestDispatcher.forward( req,  resp,  "/changepassword.jsp" );
		}
		else if( action.equals( "spreads" ) )
		{
			String sweek = req.getParameter( "w" );
			resp.setContentType("text/html");
			out.println("<head><link rel=\"stylesheet\" href=\"/stylesheets/style.css\"></head>"
);
			if( sweek == null || sweek.isEmpty() )
			{
				Spreads s = new Spreads();
				s.showTables( out );
			}
			else
			{
				int iweek = Integer.parseInt( sweek );
				Spreads s = new Spreads( iweek );
				s.showTables( out );
			}
		}
	}


	private void showSessionInfo( PrintWriter out, HttpSession session )
	{
        out.println( "Session ID: " + session.getId() + "<br/>" );
        out.println( "Creation Time: " + session.getCreationTime() + "<br/>" );
        out.println( "Last Accessed Time: " + session.getLastAccessedTime() + "<br/>" );
        out.println( "Is New: " + session.isNew() + "<br />" );

        Attributes attributenames = Util.getAttributes( session );
        out.println( "<table><tr><th>Attribute</th><th>Value</th></tr>");

        for( Map.Entry<String, String> attr : attributenames.entrySet() )
        {
        	out.println( "<tr><td>" + attr.getKey() 
        			+ "</td><td>" + attr.getValue() + "</td></tr" );
        }
        out.println( "</table><p />" );
		
	}
	
/*	
	private void showRoles( PrintWriter out,  HttpServletResponse resp ) throws IOException
	{
		List<Role> roles = DAL.getRoles();
		out.println( "<table><tr><th>Role</th></tr>");
		for( Role role : roles )
		{
			out.println( "<tr><td>" + role.getRole() + "</td></tr>" );
		}
		out.println( "</table><br/>");
	}
	
	private void showUsers( PrintWriter out,  HttpServletResponse resp ) throws IOException
	{
		List<User> users = DAL.getUsers();
		out.println( "<table><tr><th>Email</th><th>Name</th><th>Password</th></tr>");
		for( User user : users )
		{
			out.println( "<tr>");
			out.print( "<td>" + user.getEmail() + "</td>" );
			out.print( "<td>" + user.getName() + "</td>" );
			out.println( "<td>" + user.getPassword() + "</td>");
		}
		out.println( "</table>");
	}
*/	
	private void showUserRoles( PrintWriter out, HttpServletResponse resp ) throws IOException
	{
		List<User> users = DAL.getUsers();
		
		out.println( "<table><tr><th>Email</th><th>Name</th><th>Password</th><th>Role</th></tr>");
		for( User user : users )
		{
			out.println( "<tr>");
			out.print( "<td>" + user.getEmail() + "</td>" );
			out.print( "<td>" + user.getName() + "</td>" );
			out.print( "<td>" + user.getPassword() + "</td>");
			
			List<UserRole> ur = DAL.getUserRoles( user.getEmail() );
			int i = 0;
			for( UserRole r : ur )
			{
				if( i == 0 )
				{
					out.println( "<td>" + r.getRole() + "</td>");
					if( ur.size() > 1 )
					{
						out.println( "</tr>" );
					}
				}
				else
				{
					out.println( "<tr>");
					out.println("<td></td><td></td><td></td><td>" + r.getRole() + "</td>");
					if( i < ur.size() )
					{
						out.println( "</tr>" );
					}
				}
				i++;
			}
			if( i == 0 )
			{
				out.println( "<td></td>" );
			}
			out.println( "</tr>");	
			
		}
		out.println( "</table>");
	}

}

