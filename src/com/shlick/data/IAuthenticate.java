package com.shlick.data;

public interface IAuthenticate {
	boolean isValidUser( String email, String password );
	User getUser(String email);
	boolean createUser( String email, String password, String name, Role role );
	boolean createUser( User user );
	boolean isLoggedIn();
	boolean isAdmin();
	boolean createRole( String role );
	boolean addUserToRole(User user, Role role );
}
