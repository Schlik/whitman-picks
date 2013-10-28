package com.shlick.data;

import java.util.Date;

import javax.jdo.annotations.*;

@PersistenceCapable
public class TeamRecord {
	@PrimaryKey
	@Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY )
	Key key;
	
	@Persistent( mappedBy = "key")
	Team team;
	
	@Persistent
	Date   date_played;
	
	@Persistent
	Integer q1;
	
	@Persistent
	Integer q2;
	
	@Persistent
	Integer q3;
	
	@Persistent
	Integer q4;
	
	@Persistent
	Integer to;

	@Persistent
	Boolean won;

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @return the team
	 */
	public Team getTeam_id() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @return the date_played
	 */
	public Date getDate_played() {
		return date_played;
	}

	/**
	 * @param date_played the date_played to set
	 */
	public void setDate_played(Date date_played) {
		this.date_played = date_played;
	}

	/**
	 * @return the q1
	 */
	public Integer getQ1() {
		return q1;
	}

	/**
	 * @param q1 the q1 to set
	 */
	public void setQ1(Integer q1) {
		this.q1 = q1;
	}

	/**
	 * @return the q2
	 */
	public Integer getQ2() {
		return q2;
	}

	/**
	 * @param q2 the q2 to set
	 */
	public void setQ2(Integer q2) {
		this.q2 = q2;
	}

	/**
	 * @return the q3
	 */
	public Integer getQ3() {
		return q3;
	}

	/**
	 * @param q3 the q3 to set
	 */
	public void setQ3(Integer q3) {
		this.q3 = q3;
	}

	/**
	 * @return the q4
	 */
	public Integer getQ4() {
		return q4;
	}

	/**
	 * @param q4 the q4 to set
	 */
	public void setQ4(Integer q4) {
		this.q4 = q4;
	}

	/**
	 * @return the to
	 */
	public Integer getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(Integer to) {
		this.to = to;
	}

	/**
	 * @return the won
	 */
	public Boolean getWon() {
		return won;
	}

	/**
	 * @param won the won to set
	 */
	public void setWon(Boolean won) {
		this.won = won;
	}
	
	
}
