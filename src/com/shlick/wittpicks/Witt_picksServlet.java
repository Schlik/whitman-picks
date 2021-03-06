package com.shlick.wittpicks;

import java.io.IOException;
import javax.servlet.http.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class Witt_picksServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      UserService userService = UserServiceFactory.getUserService();
      User user = userService.getCurrentUser();

      if (user != null) {
    	  //resp.setCharacterEncoding("utf-8");
          //resp.setContentType("text/plain");
          //resp.getWriter().println("Hello, " + user.getNickname());
    	  resp.sendRedirect( "/fetchTeams" );
      } else {
          resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
      }
  }
}