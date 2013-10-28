package com.shlick.data;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Team {
	@PrimaryKey
	@Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY )
	Key key;
	
	@Persistent
	String team_id;
	
	@Persistent
	String team_name;
	
	@Persistent
	String team_logo;
	
	@Persistent
	String team_site;
	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @return the team_id
	 */
	public String getTeam_id() {
		return team_id;
	}
	/**
	 * @param team_id the team_id to set
	 */
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	/**
	 * @return the team_name
	 */
	public String getTeam_name() {
		return team_name;
	}
	/**
	 * @param team_name the team_name to set
	 */
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	/**
	 * @return the team_logo
	 */
	public String getTeam_logo() {
		return team_logo;
	}
	/**
	 * @param team_logo the team_logo to set
	 */
	public void setTeam_logo(String team_logo) {
		this.team_logo = team_logo;
	}
	/**
	 * @return the team_site
	 */
	public String getTeam_site() {
		return team_site;
	}
	/**
	 * @param team_site the team_site to set
	 */
	public void setTeam_site(String team_site) {
		this.team_site = team_site;
	}
	
}
