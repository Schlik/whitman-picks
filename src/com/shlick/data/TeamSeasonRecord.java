package com.shlick.data;

//import java.util.Date;

import javax.jdo.annotations.*;

@PersistenceCapable
public class TeamSeasonRecord {
	@PrimaryKey
	@Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY )
	Key key;
	
	@Persistent( mappedBy = "key")
	Team team;
	
	@Persistent
	Integer season;
	
	@Persistent
	Integer wins;
	
	@Persistent
	Integer loss;
	
	@Persistent
	Integer tied;

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param team_id the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @return the season
	 */
	public Integer getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(Integer season) {
		this.season = season;
	}

	/**
	 * @return the wins
	 */
	public Integer getWins() {
		return wins;
	}

	/**
	 * @param wins the wins to set
	 */
	public void setWins(Integer wins) {
		this.wins = wins;
	}

	/**
	 * @return the loss
	 */
	public Integer getLoss() {
		return loss;
	}

	/**
	 * @param loss the loss to set
	 */
	public void setLoss(Integer loss) {
		this.loss = loss;
	}

	/**
	 * @return the tied
	 */
	public Integer getTied() {
		return tied;
	}

	/**
	 * @param tied the tied to set
	 */
	public void setTied(Integer tied) {
		this.tied = tied;
	}
	
	
}
