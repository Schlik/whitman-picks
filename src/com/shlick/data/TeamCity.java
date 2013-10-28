package com.shlick.data;


public class TeamCity implements Comparable<TeamCity> {

	private String city;
	private String teamName;
	
	public TeamCity( String city, String teamName )
	{
		this.city = city;
		this.teamName = teamName;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public TeamCity setCity(String city) {
		this.city = city;
		return this;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public TeamCity setTeamName(String teamName) {
		this.teamName = teamName;
		return this;
	}
	
	public boolean isSameCity( String city )
	{
		return this.city.equals( city );
	}
	public boolean isSameTeam( String teamName )
	{
		return this.teamName.equals( teamName );
	}

	@Override
	public int compareTo(TeamCity o) {
		int ret = o.city.compareTo(this.city) | o.teamName.compareTo(this.teamName); 
		return ret;
	}
	
	public static TeamCity[] getTeamCities()
	{
		return teamcities.clone();
	}
	
	private static final TeamCity[] teamcities = {
			new TeamCity( "Buffalo", "Bills" ),
			new TeamCity( "Miami", "Dolphins" ),
			new TeamCity( "New England", "Patriots" ),
			new TeamCity( "New York", "Jets" ),
			new TeamCity( "Baltimore", "Ravens" ),
			new TeamCity( "Cincinnati", "Bengals" ),
			new TeamCity( "Cleveland", "Browns" ),
			new TeamCity( "Pittsburgh", "Steelers" ),
			new TeamCity( "Houston", "Texans" ),
			new TeamCity( "Indianapolis", "Colts" ),
			new TeamCity( "Jacksonville", "Jaguars" ),
			new TeamCity( "Tennessee", "Titans" ),
			new TeamCity( "Denver", "Broncos" ),
			new TeamCity( "Kansas City", "Chiefs" ),
			new TeamCity( "Oakland", "Raiders" ),
			new TeamCity( "San Diego", "Chargers" ),
			
			new TeamCity( "Dallas", "Cowboys" ),
			new TeamCity( "New York", "Giants" ),
			new TeamCity( "Philadelphia", "Eagles" ),
			new TeamCity( "Washington", "Redskins" ),
			new TeamCity( "Chicago", "Bears" ),
			new TeamCity( "Detroit", "Lions" ),
			new TeamCity( "Green Bay", "Packers" ),
			new TeamCity( "Minnesota", "Vikings" ),
			new TeamCity( "Atlanta", "Falcons" ),
			new TeamCity( "Carolina", "Panthers" ),
			new TeamCity( "New Orleans", "Saints" ),
			new TeamCity( "Tampa Bay", "Buccaneers" ),
			new TeamCity( "Arizona", "Cardinals" ),
			new TeamCity( "St. Louis", "Rams" ),
			new TeamCity( "San Francisco", "49ers" ),
			new TeamCity( "Seattle", "Seahawks" )
	};

}
