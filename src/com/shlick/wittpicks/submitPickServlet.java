package com.shlick.wittpicks;


import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


public class submitPickServlet extends HttpServlet {    
	

	private static final long serialVersionUID = 1L;
	public   String pick_output_string;
	public static final int CURRENT_WEEK = 4;

@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

	
	String email_address = req.getParameter("picker_email");
	pick_output_string = email_address + ", ";
	//TODO verifyEmail() - the email entered, is it listed in a file?  ie. grep for filename in file if exists true else false
	
	
	//Setup a Datastore.  It seems synonymous with database...
	Key userPicksKey = KeyFactory.createKey("UserEmail", email_address);
	Entity my_picks = new Entity("picks", userPicksKey);
	//Since we only have an email as a key, need current week to filter picks
	my_picks.setProperty("week_number", CURRENT_WEEK);
	
	int i = 1;
	while(true)
	{
		//Go through all of the games and store it in the Datastore
		String current_game = "game" + i;
		String content = req.getParameter(current_game);
		if (content == null) {
			break;
		}
		else
		{
			my_picks.setProperty( current_game, content);
			i++;
		}
	}
	
    //Now store off the data
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(my_picks);


    resp.sendRedirect("/pickResults.jsp?userEmail=" + email_address);
}


}
