package com.shlick.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import org.datanucleus.util.Base64;

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

public class DAL {
	private static final Logger log = Logger.getGlobal();

	private DAL()
	{
	}
	
	public static User getUser( String email )
	{
		User ret = null;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter ue = new FilterPredicate( "user_email", FilterOperator.EQUAL, email );
		Query q = new Query( "Users" ).setFilter( ue );
		PreparedQuery pq = datastore.prepare( q );
		
		Iterable<Entity> results = pq.asIterable();
		
		boolean found = false;
		for( Entity myuser : results )
		{
			ret = new User();
			ret.setEmail( email );
			ret.setName( myuser.getProperty( "name" ).toString() );
			ret.setPassword( myuser.getProperty( "password" ).toString() );
			log.info( "Found " + email + " in Users entity");
			found = true;
			break;
		}
		
		if( !found ) log.info( "Did not find email " + email + " in Users entity");
		
		return ret;
	}
	public static List<UserRole> getUserRole( String email )
	{
		List<UserRole> ret = new ArrayList<UserRole>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter ue = new FilterPredicate( "email_address", FilterOperator.EQUAL, email );
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
	public static boolean addUser( User user )
	{
		boolean ret = false;
		
		User u = getUser( user.email );
		if( u!= null && user.email.equals( u.email ) )
		{
			log.info( "User " + user.email + " exists!" );
			return ret;
		}
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key userPicksKey = KeyFactory.createKey(user.email, "Users");

		Entity entity = new Entity( "Users", userPicksKey );

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
		return ret;
	}
	public static boolean addUserRole( UserRole ur )
	{
		boolean ret = false;
		boolean found = false;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query("UserRoles");
		FilterPredicate fpe = FilterOperator.EQUAL.of("email_address", ur.email_address);
		FilterPredicate fpr = FilterOperator.EQUAL.of( "role", ur.role );
		
		Filter cf = CompositeFilterOperator.and( fpe, fpr );
		
        q.setFilter( cf );
        
        
        PreparedQuery pq = datastore.prepare(q);

        
        for (Entity result : pq.asIterable())
        {
        	if( result.equals( ur.role ) )
        	{
        		found = true;
        		break;
        	}
        }
        if( !found )
        {
			Key userRolesKey = KeyFactory.createKey( ur.role, ur.email_address );
	
			try
			{
				Entity userroles = new Entity( "UserRoles", userRolesKey );
				userroles.setProperty( "email_address", ur.email_address );
				userroles.setProperty( "role", ur.role );
				datastore.put( userroles );
				ret = true;
			}
			catch( Exception userroleexp )
			{
				userroleexp.printStackTrace();
			}
        }
        return ret;
	}
	public static void updateUser( User u )
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter ue = new FilterPredicate( "user_email", FilterOperator.EQUAL, u.email );
		Query q = new Query( "Users" ).setFilter( ue );
		PreparedQuery pq = datastore.prepare( q );
		
		Iterable<Entity> results = pq.asIterable();
		
		//boolean found = false;
		for( Entity myuser : results )
		{
			myuser.setProperty( "password", u.password );
			datastore.put( myuser );
			break;
		}
		
	}
	public static void updateUserRole( UserRole ur )
	{
		
	}
	public static void deleteUser( User u )
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter ue = new FilterPredicate( "user_email", FilterOperator.EQUAL, u.email );
		Query q = new Query( "Users" ).setFilter( ue );
		PreparedQuery pq = datastore.prepare( q );
		
		Iterable<Entity> results = pq.asIterable();
		
		//boolean found = false;
		for( Entity myuser : results )
		{
			myuser.setProperty( "password", u.password );
			datastore.delete( myuser.getKey() );
			break;
		}
		
	}
	public static void deleteUserRole( UserRole ur )
	{
		
	}
	public static List<User> getUsers()
	{
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
	
	
	public static boolean addRole(String role) 
	{
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

	public static List<UserRole> getUserRoles( String email )
	{
		List<UserRole> ret = new ArrayList<UserRole>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter ue = new FilterPredicate( "email_address", FilterOperator.EQUAL, email );
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

	public static boolean isAdmin( String email )
	{
		boolean ret = false;
		List<UserRole> roles = getUserRoles( email );
		
		for( int i = 0; i < roles.size(); i++ )
		{
			UserRole ur = roles.get( i );
			if( ur.getRole().equals( Role.ADMINISTRATOR ) )
			{
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	public static List<Role> getRoles()
	{
		List<Role> ret = new ArrayList<Role>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	Query q = new Query("Roles");
        
        PreparedQuery pq = datastore.prepare(q);

        for (Entity result : pq.asIterable())
        {
        	Role r = new Role();
        	r.role = result.getProperty( "role" ).toString();
        	ret.add( r );
        }
		
		return ret;
	}
	public static List<Team> getTeams()
	{
		List<Team> teams = new ArrayList<Team>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	Query q = new Query("Teams");
        
        PreparedQuery pq = datastore.prepare(q);

        for (Entity result : pq.asIterable())
        {
        	Team team = new Team();
        	team.team_id = result.getProperty( "team_id" ).toString();
        	team.team_logo = result.getProperty("team_log").toString();
        	team.team_name = result.getProperty("team_name").toString();
        	team.team_site = result.getProperty( "team_site" ).toString();
        	teams.add( team );
        }
		
		return teams;
	}
	public static Team getTeam( String team_id )
	{
		Team team = new Team();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter ue = new FilterPredicate( "team_id", FilterOperator.EQUAL, team_id );
		Query q = new Query( "Teams" ).setFilter( ue );
		PreparedQuery pq = datastore.prepare( q );
		
		Iterable<Entity> results = pq.asIterable();
		
		boolean found = false;
		for( Entity myteam : results )
		{
			team.team_id = team_id;
			team.team_logo = myteam.getProperty( "team_logo" ).toString();
        	team.team_name = myteam.getProperty("team_name").toString();
        	team.team_site = myteam.getProperty( "team_site" ).toString();
			log.info( "Found " + team_id + " in Teams entity");
			found = true;
		}
		
		if( !found ) log.info( "Did not find team " + team_id + " in Teams entity");
		
		return team;
		
	}
	public static boolean addTeam( Team team )
	{
		boolean ret = false;
		
		Team existingteam = getTeam( team.team_id );
		if( existingteam == null || team.team_id.equals( existingteam.team_id ) )
		{
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Key teamKey = KeyFactory.createKey( team.team_id, "Teams");

			
			Entity entity = new Entity( "Teams", teamKey );
			entity.setProperty( "team_id", team.team_id );
			entity.setProperty( "team_logo",  team.team_logo );
			entity.setProperty( "team_name",  team.team_name );
			entity.setProperty( "team_site",  team.team_site );
			
			try
			{
				datastore.put( entity );
				ret = true;
			}
			catch( Exception dsperr )
			{
				dsperr.printStackTrace();
			}
		}
		return ret;
	}
	public static List<TeamData> getTeamData( String team_id, String season )
	{
		List<TeamData> teamData = new ArrayList<TeamData>();
		
		return teamData;
	}
	public static List<TeamRecord> getTeamRecord( String team_id, String season )
	{
		List<TeamRecord> teamRecord = new ArrayList<TeamRecord>();
		
		return teamRecord;
	}
	public static List<TeamSeasonRecord> getTeamSeasonRecord( String team_id, String season )
	{
		List<TeamSeasonRecord> teamSeasonRecord = new ArrayList<TeamSeasonRecord>();
		
		return teamSeasonRecord;
	}

	public static SpreadData getSpread( String game_id )
	{
		SpreadData ret = new SpreadData();
		
		return ret;
	}
	
	public static boolean addSpread( SpreadData sd )
	{
		boolean ret = false;
		
		return ret;
	}
	
	
	public static int getNumberOfRoles()
	{
		return getRoles().size();
	}
	public static int getNumberOfUsers()
	{
		return getUsers().size();
	}

	public static boolean needSetup()
	{
		boolean ret = false;
		
		int noOfRoles = getNumberOfRoles();
		int noOfUsers = getNumberOfUsers();
		
		log.info( "Number of roles in the system: " + noOfRoles );
		log.info( "Number of users in the system: " + noOfUsers );
		ret = !( ( noOfRoles > 0 ) && ( noOfUsers > 0 ) );
		log.info( "needSetup = " + ret );
		
		return ret;
	}
	public static boolean login( String email, String password )
	{
		boolean ret = false;
		
		User u = getUser( email );
		String pwd = Base64.encodeString( password );
		if( u != null && pwd.equals( u.password ) )
		{
			ret = true;
		}
		return ret;
	}
	public static boolean resetPassword( String email, String password )
	{
		boolean ret = false;
		
		User u = getUser( email );
		if( u != null )
		{
			String pwd = Base64.encodeString( password );
			u.setPassword( pwd );
			updateUser( u );
			return ret = true;
		}
		
		return ret;
	}
}
