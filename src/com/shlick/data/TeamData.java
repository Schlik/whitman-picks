package com.shlick.data;


import javax.jdo.annotations.*;

@PersistenceCapable
public class TeamData {
	@PrimaryKey
	@Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY )
	Key key;
	
	@Persistent( mappedBy = "key" )
	Team team;
	
	@Persistent
	TeamRecord team_record;
	
	@Persistent
	TeamSeasonRecord team_season_record;

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
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @return the team_record
	 */
	public TeamRecord getTeam_record() {
		return team_record;
	}

	/**
	 * @param team_record the team_record to set
	 */
	public void setTeam_record(TeamRecord team_record) {
		this.team_record = team_record;
	}

	/**
	 * @return the team_season_record
	 */
	public TeamSeasonRecord getTeam_season_record() {
		return team_season_record;
	}

	/**
	 * @param team_season_record the team_season_record to set
	 */
	public void setTeam_season_record(TeamSeasonRecord team_season_record) {
		this.team_season_record = team_season_record;
	}
	
	
}
