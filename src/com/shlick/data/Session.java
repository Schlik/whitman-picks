package com.shlick.data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.shlick.util.NFLScores;

public class Session {
	public Session( HttpServletRequest request )
	{
		ses = request.getSession( false );
		if( ses != null ) //&& !ses.isNew() )
		{
			param_valid = (String)ses.getAttribute( "valid" );
			param_email = (String)ses.getAttribute( "email" );
			param_name = (String)ses.getAttribute( "name" );
			String param_total_games = (String)ses.getAttribute( "total_games" );
			String param_admin = (String)ses.getAttribute( "isadmin" );
			String param_currentweek = (String)ses.getAttribute( "current_week" );
			
			if( param_admin != null && !param_admin.isEmpty() )
			{
				if( param_admin.equals( "true" ) ) 
					b_admin = true;
				else
					b_admin = false;
			}
			else
				b_admin = false;
			
			if( param_total_games != null && !param_total_games.isEmpty() ) 
			{
				total_games = Integer.parseInt( param_total_games );
			}
			else
				total_games = 18;  //Default constant
			
			if( param_currentweek != null && !param_currentweek.isEmpty() )
			{
				iCurrentWeek = Integer.parseInt( param_currentweek );
			}
			else
			{
				//init( request );
				setCurrentWeek( NFLScores.getCurrentWeek() );
			}
			
			if( param_valid != null && !param_valid.isEmpty() )
			{
				if( param_valid.equals( "true" ) ) b_logged_in = true;
			}
		}
		else
		{
			init( request );
			setCurrentWeek( NFLScores.getCurrentWeek() );
		}
	}
	public boolean isValid()
	{
		return b_logged_in;
	}
	
	/**
	 * @return the b_logged_in
	 */
	public boolean isLoggedIn() {
		return b_logged_in;
	}
	/**
	 * @param b_logged_in the b_logged_in to set
	 */
	public void setLoggedIn(boolean b_logged_in) {
		this.b_logged_in = b_logged_in;
	}
	/**
	 * @return the param_email
	 */
	public String getEmail() {
		return param_email;
	}
	/**
	 * @param param_email the param_email to set
	 */
	public void setEmail(String param_email) {
		this.param_email = param_email;
	}
	/**
	 * @return the param_name
	 */
	public String getName() {
		return param_name;
	}
	/**
	 * @param param_name the param_name to set
	 */
	public void setName(String param_name) {
		this.param_name = param_name;
	}
	/**
	 * @return the b_admin
	 */
	public boolean isAdmin() {
		return b_admin;
	}
	/**
	 * @param b_admin the b_admin to set
	 */
	public void setAdmin(boolean b_admin) {
		this.b_admin = b_admin;
	}
	/**
	 * @return the total_games
	 */
	public int getTotalGames() {
		return total_games;
	}
	/**
	 * @param total_games the total_games to set
	 */
	public void setTotalGames(int total_games) {
		this.total_games = total_games;
	}
	/**
	 * @return the iCurrentWeek
	 */
	public int getCurrentWeek() {
		return iCurrentWeek;
	}
	/**
	 * @param iCurrentWeek the iCurrentWeek to set
	 */
	public void setCurrentWeek(int iCurrentWeek) {
		this.iCurrentWeek = iCurrentWeek;
		ses.setAttribute( "iCurrentWeek",  Integer.toString( iCurrentWeek ) );
	}
	
	public void init( HttpServletRequest req )
	{
		ses = req.getSession();
	    b_logged_in = false;
		param_valid = "";
		param_email = "";
		param_name = "";
		b_admin = false;
		total_games = 18;
		iCurrentWeek = 1;	
		
		ses.setAttribute( "logged_in", b_logged_in?"true":"false" );
		ses.setAttribute( "valid", param_valid );
		ses.setAttribute( "email",  param_email );
		ses.setAttribute( "name", 	param_name );
		ses.setAttribute( "password", "" );
		ses.setAttribute( "isadmin", b_admin?"true":"false" );
		ses.setAttribute( "total_games", Integer.toString( total_games ) );
		ses.setAttribute( "iCurrentWeek",  Integer.toString( iCurrentWeek ) );
	}
	
	public static void resetSession( HttpSession ses )
	{
		try
		{
			ses.removeAttribute( "logged_in" );
			ses.removeAttribute( "valid" );
			ses.removeAttribute( "email" );
			ses.removeAttribute( "name" );
			ses.removeAttribute( "password" );
			ses.removeAttribute( "isadmin" );
			ses.removeAttribute( "total_games" );
			ses.removeAttribute( "iCurrentWeek" );	
			ses.invalidate();
		}
		catch( Exception ex )
		{
			
		}
	}

	HttpSession ses;
    boolean b_logged_in = false;
	String param_valid = "";
	String param_email = "";
	String param_name = "";
	boolean b_admin = false;
	int total_games = 18;
	int iCurrentWeek = 1;

}
