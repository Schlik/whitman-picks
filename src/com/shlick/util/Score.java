/**
 * 
 */
package com.shlick.util;

import java.beans.Beans;
import java.util.Date;

/**
 * @author fgomes
 *
 */
public class Score extends Beans {

	public class Team extends Beans
	{
		public Team( Boolean awayTeam )
		{
			setAwayTeam( awayTeam );
		}
		public Team()
		{
		}
		/**
		 * @return the awayTeam
		 */
		public boolean isAwayTeam() {
			return awayTeam;
		}
		/**
		 * @param awayTeam the awayTeam to set
		 */
		public void setAwayTeam(boolean awayTeam) {
			this.awayTeam = awayTeam;
		}
		/**
		 * @return the teamRecord
		 */
		public String getTeamRecord() {
			return teamRecord;
		}
		/**
		 * @param teamRecord the teamRecord to set
		 */
		public void setTeamRecord(String teamRecord) {
			this.teamRecord = teamRecord;
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
		public void setTeamName(String teamName) {
			this.teamName = teamName;
		}
		/**
		 * @return the totalScore
		 */
		public int getTotalScore() {
			return totalScore;
		}
		/**
		 * @param totalScore the totalScore to set
		 */
		public void setTotalScore(int totalScore) {
			this.totalScore = totalScore;
		}
		/**
		 * @return the firstQt
		 */
		public int getFirstQt() {
			return firstQt;
		}
		/**
		 * @param firstQt the firstQt to set
		 */
		public void setFirstQt(int firstQt) {
			this.firstQt = firstQt;
		}
		/**
		 * @return the secondQt
		 */
		public int getSecondQt() {
			return secondQt;
		}
		/**
		 * @param secondQt the secondQt to set
		 */
		public void setSecondQt(int secondQt) {
			this.secondQt = secondQt;
		}
		/**
		 * @return the thirdQt
		 */
		public int getThirdQt() {
			return thirdQt;
		}
		/**
		 * @param thirdQt the thirdQt to set
		 */
		public void setThirdQt(int thirdQt) {
			this.thirdQt = thirdQt;
		}
		/**
		 * @return the fourthQt
		 */
		public int getFourthQt() {
			return fourthQt;
		}
		/**
		 * @param fourthQt the fourthQt to set
		 */
		public void setFourthQt(int fourthQt) {
			this.fourthQt = fourthQt;
		}
		/**
		 * @return the otQt
		 */
		public int getOtQt() {
			return otQt;
		}
		/**
		 * @param otQt the otQt to set
		 */
		public void setOtQt(int otQt) {
			this.otQt = otQt;
		}
		public String getTeamLogo() {
			return teamLogo;
		}
		public void setTeamLogo( String logo ) {
			teamLogo = logo;
		}
		private boolean awayTeam = false;
		private String teamRecord;
		private String teamName;
		private int totalScore;
		private int firstQt;
		private int secondQt;
		private int thirdQt;
		private int fourthQt;
		private int otQt;
		private String teamLogo;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the network
	 */
	public String getNetwork() {
		return network;
	}
	/**
	 * @param network the network to set
	 */
	public void setNetwork(String network) {
		this.network = network;
	}
	/**
	 * @return the awayTeam
	 */
	public Team getAwayTeam() {
		return awayTeam;
	}
	/**
	 * @param awayTeam the awayTeam to set
	 */
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	/**
	 * @return the homeTeam
	 */
	public Team getHomeTeam() {
		return homeTeam;
	}
	/**
	 * @param homeTeam the homeTeam to set
	 */
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	/**
	 * @return the bigPlaysCount
	 */
	public int getBigPlaysCount() {
		return bigPlaysCount;
	}
	/**
	 * @param bigPlaysCount the bigPlaysCount to set
	 */
	public void setBigPlaysCount(int bigPlaysCount) {
		this.bigPlaysCount = bigPlaysCount;
	}
	
	public Score newCopy() {
		Score s = new Score();
		s.date = this.date;
		s.network = this.network;
		s.bigPlaysCount = this.bigPlaysCount;
		
		Team awayTeam = new Team( true );
		awayTeam.teamName = this.awayTeam.teamName;
		awayTeam.teamRecord = this.awayTeam.teamRecord;
		awayTeam.totalScore = this.awayTeam.totalScore;
		awayTeam.teamLogo = this.awayTeam.teamLogo;
		
		s.awayTeam = awayTeam;
		
		Team homeTeam = new Team();
		homeTeam.teamName = this.homeTeam.teamName;
		homeTeam.teamRecord = this.homeTeam.teamRecord;
		homeTeam.totalScore = this.homeTeam.totalScore;
		homeTeam.teamLogo = this.homeTeam.teamLogo;
		
		s.homeTeam = homeTeam;
		
		return s;
	}

	/**
	 * @return the spread
	 */
	public int getSpread() {
		return spread;
	}
	/**
	 * @param spread the spread to set
	 */
	public void setSpread(int spread) {
		this.spread = spread;
	}
	public void setSpread(String spread) {
		this.spread = Integer.parseInt( spread );
	}

	private Date date;
	private String network;
	private Team awayTeam = new Team( true );
	private Team homeTeam = new Team();
	private int bigPlaysCount;
	private int spread;
}
