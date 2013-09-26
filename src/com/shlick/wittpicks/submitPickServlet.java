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

@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

	
	String email_address = req.getParameter("picker_email");
	pick_output_string = email_address + ", ";
	//verifyEmail();

	int i = 1;
	while(true)
	{
		String current_game = "game" + i;
		String content = req.getParameter(current_game);
		if (content == null) {
			break;
		}
		else
		{
			pick_output_string += content + ", ";
			i++;
		}
	}
	
    Key userPicksKey = KeyFactory.createKey("UserEmail", email_address);

    Entity picks_string = new Entity("userPicks", userPicksKey);
    picks_string.setProperty("selections", pick_output_string);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(picks_string);


    
    resp.sendRedirect("/pickResults.jsp?userEmail=" + email_address);
}


}
