package com.shlick.wittpicks;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.tools.ant.taskdefs.LogOutputStream;







//import com.fgomes.util.RequestDispatcher;
//import com.google.appengine.api.users.User;
//import com.google.appengine.api.users.UserService;
//import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.shlick.util.RequestDispatcher;

public class submitPickServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public String pick_output_string;
	public static final int CURRENT_WEEK = 4;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String email_address = req.getParameter("picker_email");
		int current_week = Integer.parseInt( req.getParameter("current_week" ) );
		int total_games = Integer.parseInt( req.getParameter("total_games" ));

		if (email_address == null || email_address.isEmpty()) {
			resp.sendRedirect("/login.jsp");

//			UserService userService = UserServiceFactory.getUserService();
//			User user = userService.getCurrentUser();
//
//			if( user != null )
//			{
//				email_address = user.getEmail();
//			}
			
			//resp.sendRedirect(userService.createLoginURL(req
			//		.getRequestURI()));
			
		}
		
		Date date_picked = new Date();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key userPicksKey = KeyFactory.createKey( "user_email", email_address );

		Entity my_picks;
		try
		{
			my_picks = datastore.get( userPicksKey );
		}
		catch( EntityNotFoundException enfe )
		{
			my_picks = new Entity("picks", userPicksKey);
			my_picks.setProperty("user_email", email_address);
			my_picks.setProperty("week_number", current_week);
			my_picks.setProperty("date_picked", date_picked );
			
			for( int i = 1; i <= total_games; i++ ) {
				// Go through all of the games and store it in the Datastore
				String current_game = "game" + i;
				String content = req.getParameter(current_game);
				my_picks.setProperty(current_game, content);
			}

			// Now store off the data
			datastore.put(my_picks);
		}

		pick_output_string = email_address + ", ";

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:hh:ss" );
		String strDatePicked = sdf.format( my_picks.getProperty( "date_picked" ) );

//		try
//		{
			req.setAttribute( "date_picked", strDatePicked );
			RequestDispatcher.forward(req, resp, "/pickResults.jsp" );
//		}
//		catch( Exception fex )
//		{
//			//resp.sendRedirect( "/index.jsp?email=" + email_address );
//			fex.printStackTrace();
//		}
//		resp.sendRedirect("/pickResults.jsp?USER_EMAIL=" + email_address
//				+ "&CURRENT_WEEK=" + current_week + "&TOTAL_GAMES=" + total_games + "&DATE_PICKED=" + strDatePicked );
	}

}
