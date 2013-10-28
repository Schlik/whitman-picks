package com.shlick.data;

import javax.jdo.annotations.*;

@PersistenceCapable
public class UserRole {
	@PrimaryKey
	@Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY )
	Key key;
	
	@Persistent
	String email_address;
	
	@Persistent
	String role;

	public UserRole( String email_address, String role )
	{
		this.email_address = email_address;
		this.role = role;
	}
	public UserRole()
	{
	}
	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}
	/**
	 * @return the email_address
	 */
	public String getEmail_address() {
		return email_address;
	}
	/**
	 * @param email_address the email_address to set
	 */
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
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
	
}
