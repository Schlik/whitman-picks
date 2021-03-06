package com.shlick.data;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import com.shlick.util.NFLScores;


public enum SessionData {
	INSTANCE;
	
	public void session( HttpServletRequest request )
	{
		ses = request.getSession( false );
		if( ses != null && !ses.isNew() )
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
			
			iCurrentWeek = Integer.parseInt( param_currentweek );
			//if( iCurrentWeek == 0 ) iCurrentWeek = 1;
			
			if( param_valid != null && !param_valid.isEmpty() )
			{
				if( param_valid.equals( "true" ) ) b_logged_in = true;
			}
		}
		else
		{
			iCurrentWeek = NFLScores.getCurrentWeek();
			ses = request.getSession();
			ses.setAttribute( "current_week", Integer.toString( iCurrentWeek ) );
			ses.setAttribute( "total_games", Integer.toString( total_games ) );
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
