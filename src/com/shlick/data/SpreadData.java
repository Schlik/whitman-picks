package com.shlick.data;

public class SpreadData {
	String datetime;
	String favTeam;
	String spread;
	String underdogTeam;
	
	public SpreadData( String datetime, String favTeam, String spread, String underdogTeam )
	{
		this.datetime = datetime;
		this.favTeam = favTeam;
		this.spread = spread;
		this.underdogTeam = underdogTeam;
	}
	public SpreadData()
	{
		this( "", "", "", "" );
	}
	/**
	 * @return the datetime
	 */
	public String getDatetime() {
		return datetime;
	}
	/**
	 * @param datetime the datetime to set
	 */
	public SpreadData setDatetime(String datetime) {
		this.datetime = datetime;
		return this;
	}
	/**
	 * @return the favTeam
	 */
	public String getFavTeam() {
		return favTeam;
	}
	/**
	 * @param favTeam the favTeam to set
	 */
	public SpreadData setFavTeam(String favTeam) {
		this.favTeam = favTeam;
		return this;
	}
	/**
	 * @return the spread
	 */
	public String getSpread() {
		return spread;
	}
	/**
	 * @param spread the spread to set
	 */
	public SpreadData setSpread(String spread) {
		this.spread = spread;
		return this;
	}
	/**
	 * @return the underdogTeam
	 */
	public String getUnderdogTeam() {
		return underdogTeam;
	}
	/**
	 * @param underdogTeam the underdogTeam to set
	 */
	public SpreadData setUnderdogTeam(String underdogTeam) {
		this.underdogTeam = underdogTeam;
		return this;
	}
	
	
}
