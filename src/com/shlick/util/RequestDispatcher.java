package com.shlick.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class RequestDispatcher {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	static public void forward( HttpServletRequest req, HttpServletResponse resp, String path )
		throws ServletException, IOException
	{
		req.getRequestDispatcher( path ).forward( req,  resp );
	}
	
	static public void include( HttpServletRequest req, HttpServletResponse resp, String path )
			throws ServletException, IOException
	{
		req.getRequestDispatcher( path ).include( req,  resp );
	}
	
}
