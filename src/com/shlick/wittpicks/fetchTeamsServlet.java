/**
 * 
 */
package com.shlick.wittpicks;

import java.io.IOException;
import java.io.PrintWriter;
//import java.util.*;
//import java.text.*;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.servlet.http.HttpSession;

import com.shlick.util.NFLScores;

/**
 * @author fgomes
 * 
 */
public class fetchTeamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		handleRequest( req, resp );
		
	}

	@SuppressWarnings("unused")
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	    boolean b_logged_in = false;
		String param_valid = "";
		String param_email = "";
		String param_name = "";
		boolean b_admin = false;
		
		HttpSession ses = req.getSession( false );
		if( ses != null && !ses.isNew() )
		{
			param_valid = (String)ses.getAttribute( "valid" );
			param_email = (String)ses.getAttribute( "email" );
			param_name = (String)ses.getAttribute( "name" );
			String param_admin = (String)ses.getAttribute( "isadmin" );
			
			if( param_admin.equals( "true" ) ) b_admin = true;
			
			b_logged_in = true;
		}

		String week = req.getParameter("week");
		int iWeek = 0;
		
		if ( week != null && !week.isEmpty() ) {
			iWeek = Integer.parseInt( week );
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println( "<!DOCTYPE html>");
		out.println( "<html><head>");
		out.println( "<link id=\"favicon\" href=\"/favicon.ico\" rel=\"shortcut icon\">");
		out.println( "<meta content=\"width=device-width, target-density=160dip, initial-scale=1, maxmimum-scale=1\" name=\"viewport\">");
		out.println( "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		out.println( "<title>Games</title>");
		out.println( "<link rel=\"stylesheet\" href=\"/stylesheets/ui-lightness/jquery-ui-1.10.3.custom.css\" />");
		out.println( "<script src=\"/js/jquery-1.10.2.js\"></script>" );
		out.println( "<script src=\"/js/jquery-ui-1.10.3.custom.min.js\"></script>" );
		out.println( "<link rel=\"stylesheet\" href=\"/stylesheets/style.css\">");
		out.println( "</head><body>" );
		
		pageHeader( out, b_logged_in, b_admin, param_email, iWeek );
		
		out.println( "<footer>");
		out.println( "<p class=\"fg-center\">&copy;2013, All rights reserved.</p>");
		out.println( "</footer>");

		out.println( "</div>");
		out.println( "</body></html>" );
		out.close();
		
	}
	
	private void pageHeader( PrintWriter out, boolean b_logged_in, boolean b_admin, String param_email, int week )
	{
		int iCurrentWeek = NFLScores.getCurrentWeek();
	
		out.println( "<div class=\"fg-wrapper\">" );
				out.println( "<div id=\"top-right-nav\">" );
	     			
					if( b_logged_in )
					{
						out.print( param_email + " | <a href=\"/home?a=logout\">Log Out</a>" );
						if( b_admin )
						{
							out.print( " | <a href=\"/home?a=users\">Users</a>" );
							out.print( " | <a href=\"/home?a=setup\">Setup</a>" );
						}
						out.print( "\n" );
					}
					else
					{
						out.print( "<a href=\"/login.jsp\">Log In</a> | " 
							+ "<button id=\"create-user\">Register</button>\n" );
					}
					

				   out.println( "<br />" );
				   out.println( "<span style=\"color:red\">Login to make your picks<br/> or register if you are<br/> visiting for the first time</span>" );
				   out.println( "</div>" );
	     	out.println( "<img src=\"/img/logo.png\">" );
	     	out.println( " <h1>Weekly Game Picks</h1></img>" );
			
		    	
		    out.println( "<header>" );
			out.println( "<div class=\"header-wrapper\">" );
				out.println( "<nav>" );
			    	out.println( "<ul id=\"main-menu\">" );
			    		out.println( "<li>Weeks:</li>" );
			    				for( int i = 1; i < 18; i++ )
			    				{
			    				   if( i == week )
			    				   {
			    					  out.print( "<li><a  class=\"active-week\" href=\"/week?week=" + i + "&email=" + param_email + "\">" + i + "</a></li>\n" );
			    				   }
			    				   else
			    				   {
			    				      out.print( "<li><a href=\"/week?week=" + i + "&email=" + param_email + "&name=" + "\">" + i + "</a></li>\n" );
			    				   }
			    				}
			    			out.println( "</ul>" );
			    	out.println( "</nav>" );
			    out.println( "</div>" );
		    out.println( "</header>" );
			out.println( "<div class=\"clearboth\"></div>" );
		    out.println( "<section>" );
		    out.println( "<form action=\"/submitPicks\" method=\"post\" id=\"pick-submit\">" );
	    	
		    
			out.print( NFLScores.getGame( week ) );

	        if( ( week == iCurrentWeek ) && ( b_logged_in ) )
	        {
	        
	        	out.println( "<fieldset>" );
	        	out.println( "<input type=\"hidden\" name=\"picker_email\" id=\"picker_email\" value=\"<%= param_email %>\" >" );
	        	out.println( "<input type=\"hidden\" name=\"current_week\" id=\"current_week\" value=\"<%= iCurrentWeek %>\">" );
	        	out.println( "<input type=\"hidden\" name=\"name\" id=\"name\" value=\"<%= param_name %>\">" );
	        	out.println( "<input type=\"hidden\" name=\"total_games\" id=\"total_games\" value=\"16\">" );
	        	out.println( "<input type=\"submit\" value=\"Submit Picks\" name=\"idsubmit\" id=\"idsubmit\">" );
	        	out.println( "</fieldset>" );
	        }
	        out.println( "</form>" );
	        out.println( "</section>" );

	}
}
