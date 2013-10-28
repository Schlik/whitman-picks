package com.shlick.data;

//import java.io.IOException;
//import java.io.ObjectStreamException;
//import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.*;

import org.datanucleus.util.Base64;

@PersistenceCapable
public class User { //implements Serializable {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY )
	Key key;
	
	@Persistent
	String email;
	
	@Persistent
	String name;
	
	@Persistent
	String password;
	
	@Persistent
	List<Role> roles;
	
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles( List<Role> roles) {
		this.roles = roles;
	}

	public User( String emailId, String name, String password )
	{
		this.email = emailId;
		this.name = name;
		this.password = Base64.encodeString( password );
		this.roles = new ArrayList<Role>();
	}
	public User()
	{
	}
	
	public Key getKey() 
	{
		return key;
	}
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail( String email )
	{
		this.email = email;
	}
	public String getName()
	{
		return name;
	}
	
	public void setName( String name )
	{
		this.name = name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword( String password )
	{
		this.password = password;
	}
	
/*	private void writeObject( java.io.ObjectOutputStream out ) 
		throws IOException
	{
		out.defaultWriteObject();
	}
	private void readObject( java.io.ObjectInputStream in )
		throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
	}
	
	@SuppressWarnings("unused")
	private void readObjectNoData()
		throws ObjectStreamException
	{
		
	}*/
}
