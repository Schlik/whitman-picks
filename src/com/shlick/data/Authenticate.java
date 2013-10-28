package com.shlick.data;

import java.util.logging.*;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Authenticate implements IAuthenticate {

	private static final Logger log = Logger.getGlobal();
	
	@Override
	public boolean isValidUser(String email, String password) {
		boolean ret;
		
		User user = getUser( email );
		
		ret = user.getPassword().equals( password );

		return ret;
	}

	@Override
	public boolean createUser(String email, String password, String name, Role role ) {
		boolean ret = false;

		List<Role> user_role = new ArrayList<Role>(1);
		user_role.add( role );
		
		User user = new User( email, email, password );
		user.setRoles( user_role );
		
		ret = createUser( user ); 
		
		return ret;
	}

	@Override
	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

	public User getUser(String email) {
		User ret = new User();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter ue = new FilterPredicate( "user_email", FilterOperator.EQUAL, email );
		Query q = new Query( "Users" ).setFilter( ue );
		PreparedQuery pq = datastore.prepare( q );
		
		Iterable<Entity> results = pq.asIterable();
		
		boolean found = false;
		for( Entity myuser : results )
		{
			ret.setEmail( email );
			ret.setName( myuser.getProperty( "name" ).toString() );
			ret.setPassword( myuser.getProperty( "password" ).toString() );
			log.info( "Found " + email + " in Users entity");
			found = true;
		}
		
		if( !found ) log.info( "Did not find email " + email + " in Users entity");
		
		return ret;
	}
	public List<User> getUsers() {
		List<User> ret = new ArrayList<User>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query( "Users" );
		PreparedQuery pq = datastore.prepare( q );
		
		Iterable<Entity> results = pq.asIterable();
		
		for( Entity myuser : results )
		{
			User user = new User();
			user.setEmail( myuser.getProperty( "user_email" ).toString() );
			user.setName( myuser.getProperty( "name" ).toString() );
			user.setPassword( myuser.getProperty( "password" ).toString() );
			ret.add( user );
		}
		
		return ret;
	}

	public List<UserRole> getUserRoles( String email ) {
		List<UserRole> ret = new ArrayList<UserRole>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter ue = new FilterPredicate( "user_email", FilterOperator.EQUAL, email );
		Query q = new Query( "UserRoles" ).setFilter( ue );
		PreparedQuery pq = datastore.prepare( q );
		
		Iterable<Entity> results = pq.asIterable();
		
		for( Entity myrole : results )
		{
			UserRole userRole = new UserRole();
			userRole.setEmail_address( email );;
			userRole.setRole( myrole.getProperty( "role" ).toString() );
			ret.add( userRole );
		}
		
		return ret;
	}
	
	@Override
	public boolean addUserToRole(User user, Role role ) {
		boolean ret = false;
		boolean found = false;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query("UserRoles");
        q.setFilter(FilterOperator.EQUAL.of("email_address",user.email));
        PreparedQuery pq = datastore.prepare(q);

        for (Entity result : pq.asIterable())
        {
        	if( result.equals( role.role ) )
        	{
        		found = true;
        		break;
        	}
        }
        if( !found )
        {
			Key userRolesKey = KeyFactory.createKey("UserRoles", user.email );
	
			try
			{
				Entity userroles = new Entity( "UserRoles", userRolesKey );
				userroles.setProperty( "email_address", user.email );
				userroles.setProperty( "role", role.getRole() );
				datastore.put( userroles );
				ret = true;
			}
			catch( Exception userroleexp )
			{
				//userroleexp.printStackTrace();
			}
        }
		
		return ret;
	}
	
	public boolean addUserToRole(String email, Role role ) {
		boolean ret = false;
		boolean found = false;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query("UserRoles");
		FilterPredicate fpe = FilterOperator.EQUAL.of("email_address", email);
		FilterPredicate fpr = FilterOperator.EQUAL.of( "role", role.getRole() );
		
		Filter cf = CompositeFilterOperator.and( fpe, fpr );
		
        q.setFilter( cf );
        
        
        PreparedQuery pq = datastore.prepare(q);

        
        for (Entity result : pq.asIterable())
        {
        	if( result.equals( role.role ) )
        	{
        		found = true;
        		break;
        	}
        }
        if( !found )
        {
			Key userRolesKey = KeyFactory.createKey( role.getRole(), email );
	
			try
			{
				Entity userroles = new Entity( "UserRoles", userRolesKey );
				userroles.setProperty( "email_address", email );
				userroles.setProperty( "role", role.getRole() );
				datastore.put( userroles );
				ret = true;
			}
			catch( Exception userroleexp )
			{
				//userroleexp.printStackTrace();
			}
        }
		
		return ret;
	}
	
	@Override
	public boolean createUser(User user) {
		boolean ret = false;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key userPicksKey = KeyFactory.createKey(user.email, "Users");

		Entity entity = new Entity( "Users", userPicksKey );

		try
		{
			entity = datastore.get( userPicksKey );
		}
		catch( EntityNotFoundException enfe )
		{
			entity.setProperty( "user_email", user.email);
			entity.setProperty( "password",  user.getPassword() );
			entity.setProperty( "name", user.getName() );
			
			
			try
			{
				datastore.put( entity );
				ret = true;
			}
			catch( Exception dsperr )
			{
				//dsperr.printStackTrace();
			}
		}
		
/*		
 * 		finally
		{
			Entity roles;
			List<Role> uroles = user.getRoles();
			for( int i = 0; i < uroles.size(); i++ )
			{
				try
				{
					Entity userroles = new Entity( "UserRoles", userPicksKey );
					userroles.setProperty( "user", entity.getKey() );
					userroles.setProperty( "role", uroles.get(i).role );
					datastore.put( userroles );
				}
				catch( Exception userroleexp )
				{
					userroleexp.printStackTrace();
				}
			}
		}
		*/
		
		return ret;
	}

	@Override
	public boolean createRole(String role) {
		boolean ret = false;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key roleKey = KeyFactory.createKey( role, "Roles");

		Entity entity;
		try
		{
			entity = datastore.get( roleKey );
		}
		catch( EntityNotFoundException enfe )
		{
			entity = new Entity( "Roles", roleKey );
			entity.setProperty( "role", role );
			
			try
			{
				datastore.put( entity );
				ret = true;
			}
			catch( Exception dsperr )
			{
				enfe.printStackTrace();
			}
		}
		
		return ret;
	}

}
