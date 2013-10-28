package com.shlick.data;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Role {
	@PrimaryKey
	@Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY )
	Key key;
	
	public static final String PLAYER = "PLAYER";
	public static final String ADMINISTRATOR = "ADMINISTRATOR";
	public static final String SUPER_USER = "SUPER_USER";
	
	public Role( String role )
	{
		this.role = role;
	}
	public Role()
	{
		this.role = PLAYER;
	}
	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	@Persistent
	String role;
}
