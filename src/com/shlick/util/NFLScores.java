/**
 * 
 */
package com.shlick.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.io.PrintWriter;
import java.net.URL;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.Date;

import com.shlick.data.SpreadData;




/**
 * @author fgomes
 *
 */
public class NFLScores {
   private URL url;
   private ArrayList<String> data = new ArrayList<String>();
   private static final Logger log = Logger.getLogger( NFLScores.class.getName() );
   private String activeWeek = "";
   private int iActiveWeek = -1;
   
   public NFLScores( String url )
   {
	   try
	   {
		   this.url = new URL( url );
		   init();
	   }
	   catch( MalformedURLException uex )
	   {
		   uex.printStackTrace();
	   }
   }
   public NFLScores( int year, String season, int iWeek )
   {
	   this( "http://www.nfl.com/scores/" 
			   			+ Integer.toString( year )
			   			+ "/" + season + "/" 
			   			+ Integer.toString( iWeek ) );
   }
   public NFLScores()
   {
	   this( "http://www.nfl.com/scores" );
   }
   
   private void init()
   {
	   try
	   {
		   BufferedReader reader = new BufferedReader( new InputStreamReader( url.openStream() ));
		   
		   if( Debug.ON )
		   {
			   log.info( "==============================================================================================");
		   }
		   String line;
		   while( ( line = reader.readLine() ) != null )
		   {
			   if( ( line == null ) || ( "".compareTo( line ) == 0 ) || line.isEmpty() || line == "\n" ) continue;
			   data.add( line );
			   //log.info( line );
			   
				if( ( iActiveWeek == -1 ) && ( line.contains( "active-week\">" ) ) )
				{
					activeWeek = extractText( "week-item\">", line );
					iActiveWeek = Integer.parseInt( activeWeek );
					if( Debug.ON )
					{
						log.info( "Active week: " + activeWeek + " which is " + iActiveWeek );
					}
				}
			   line = "";
		   }
		   reader.close();
		   if( Debug.ON )
		   {
			   log.info( "==============================================================================================");
		   }
	   }
	   catch( Exception err )
	   {
		   err.printStackTrace();
	   }
   }
   
   public static int getCurrentWeek()
   {
	   NFLScores nfl = new NFLScores();
	   return nfl.iActiveWeek;
   }
   
   public List<String> getTeams()
   {
	  List<String> ret = new ArrayList<String>(); 
	  
	  for( int i = 0; i < data.size(); i++ )
	  {
		  String team = data.get( i );
		  if( team.contains( "team-name" ) )
		  {
			  int idx = team.indexOf("href" );
			  idx = team.indexOf('>', idx );
			  ++idx;
			  int toidx = team.indexOf('<', idx );
			  String teamName = team.substring(idx, toidx);
			  ret.add( teamName );
		  }
	  }
	  
	  return ret;
   }
   
   private String extractText( String pattern, String line )
   {
		int idx = line.indexOf( pattern );
		idx = line.indexOf('>', idx);
		++idx;
		int toidx = line.indexOf('<', idx);
		String text;
		if( (toidx - idx ) < 0 )
			text = "-";
		else
			text = line.substring(idx, toidx);
		
		//log.info( pattern + " Text between " + idx + " and " + toidx + " is " + text );
		return text;
   }
 
   private String extractText( String pattern, String line, String delim )
   {
		int idx = line.indexOf( pattern );
		idx = line.indexOf(delim, idx);
		++idx;
		int toidx = line.indexOf(delim, idx);
		String text;
		if( (toidx - idx ) < 0 )
			text = "-";
		else
			text = line.substring(idx, toidx);
		
		//log.info( pattern + " Text between " + idx + " and " + toidx + " is " + text );
		return text;
   }
 
   private String extractTeamName( String line )
   {
		int idx = 0;
		if( line.contains("href" ) )
			idx = line.indexOf("href");
		idx = line.indexOf('>', idx);
		++idx;
		int toidx = line.indexOf('<', idx);
		String teamName = line.substring(idx, toidx);
		return teamName;
   }
   
   private Date extractDate( String line )
   {
		int idx = line.indexOf("<div id=\"scorebox");
		idx = line.indexOf('-', idx);
		++idx;
		int toidx = line.indexOf('"', idx);
		String strDate = line.substring(idx, toidx);
		//log.info( "Date:: " + strDate + " from line:: " + line );
		
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd" );
		Date ret = null;
		try
		{
			ret = formatter.parse( strDate );
		}
		catch( Exception ex )
		{
			log.warning( ex.toString() );
		}
		return ret;
   }
   public List<Score> getScores()
   {
	  List<Score> ret = new ArrayList<Score>(); 
	  
	  Boolean newScore = false;
	  Boolean awayTeam = false;
	  Score tmp = new Score();
	  	  
	  int i = 0;
	  for( int lines = data.size(); i < lines; i++ )
	  {
		  String line = data.get( i );
		  if( line.contains( "score-boxes" ) )
		  {
			  break;
		  }
	  }
	  for( int lines = data.size(); i < lines; i++ )
	  {
		  String line = data.get( i );
		  if( line.contains( "scorebox-wrapper" ) )
		  {
			  tmp.setBigPlaysCount(0);
			  tmp.setDate(null);
			  tmp.setNetwork("");
			  newScore = true;
			  //log.info( "scorebox-wrapper" );
		  }
		  if( line.contains( "<div id=\"scorebox" ) && newScore )
		  {
			  //log.info( "scorebox- line: " + line );
			  tmp.setDate( extractDate( line ) );
		  }
		  if( line.contains( "network\">" ) && newScore )
		  {
			  String tok = extractText( "network\">", line );
			  //log.info("network => " + tok );
			  tmp.setNetwork( tok  );
		  }
		  if( line.contains( "away-team\">" ) && newScore )
		  {
			  //log.info( "away-team" );
			  awayTeam = true;
		  }
		  if( line.contains( "home-team\">" ) && newScore )
		  {
			  //log.info( "home-team" );
			  awayTeam = false;
		  }
		  if( line.contains( "team-record\">" ) && newScore )
		  {
			  String record = "--";
			  if( line.contains( "</p>" ) )
				  record = extractText( "team-record\">", line );
			  else
			  {
				  i++;
				  for( ; i < lines; i++ )
				  {
					  line = data.get( i );
					  if( line.contains( "</a>" ) ) break;
				  }
				  
				  //log.info( "team-record line => " + line );
				  if( i < lines && line.contains( "</a>" ) )
				  {
					  record = extractTeamName( line );
				  }
			  }
			  //log.info( "team-record => " + record );
			  if( awayTeam )
				  tmp.getAwayTeam().setTeamRecord( record );
			  else
				  tmp.getHomeTeam().setTeamRecord( record );
		  }
		  if( line.contains( "team-name\">" ) && newScore ) 
		  {
			  String name = extractTeamName( line );

			  //log.info( "team-name => " + name );
			  if( awayTeam )
				  tmp.getAwayTeam().setTeamName( name );
			  else
				  tmp.getHomeTeam().setTeamName( name );
		  }
		  if( line.contains( "total-score\">" ) && newScore ) 
		  {
			  String totalScore = extractText( "total-score\">", line );
			  //log.info( "total-score => " + totalScore );
			  int total = 0;
			  try
			  {
			  	total = Integer.parseInt( totalScore );
			  }
			  catch( NumberFormatException ne )
			  {
				  total = 0;
			  }
			  
			  if( awayTeam )
				  tmp.getAwayTeam().setTotalScore( total );
			  else
				  tmp.getHomeTeam().setTotalScore( total );
			  
		  }
		  if( line.contains( "<span class='big-plays-count'>" )  && newScore )
		  {
			  
			  String bpc = extractText( "<span class='big-plays-count'>", line );
			  //log.info( "big-plays-count => " + bpc );
			  int bigPlaysCount = 0;
			  try
			  {
				  bigPlaysCount = Integer.parseInt( bpc );
			  }
			  catch( NumberFormatException ne )
			  {
				  bigPlaysCount = 0;
			  }
			  tmp.setBigPlaysCount(bigPlaysCount); 
			  
		  }
		  if( line.contains( "team-logo" ) && newScore )
		  {
			  String logo = extractText( "img src=", line, "\"" );
			  //log.info( "Team Logo:=> " + logo );
			  if( awayTeam )
				  tmp.getAwayTeam().setTeamLogo( logo );
			  else
				  tmp.getHomeTeam().setTeamLogo( logo );
		  }
		  if( line.contains( "class=\"products-social\"" ) && newScore )
		  {
			  newScore = false;
			  awayTeam = false;
			  
			  
			  Score s = tmp.newCopy();
			  ret.add( s );			  
		  }
	  }
	  //log.info( "Total Games: " + ret.size() + " from total source lines " + data.size() );
	  return ret;
   }

   public static String getGame( int week )
   {
		StringBuilder ret = new StringBuilder();

		String url = "http://www.nfl.com/scores";
		String strWeek = "Current Week";
		
		if (week > 0 && week < 18) {
			Date dt = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy");

			url += "/" + fmt.format(dt) + "/REG" + week;
			strWeek = String.format("Week %d", week);
		}

		NFLScores nfl = new NFLScores(url);
		List<Score> scores = nfl.getScores();

		ret.append("<div class=\"game\">");
		ret.append("<p class=\"fg-center\">" + strWeek + "</p>");
		ret.append("<table id=\"games\"><thead>");
		ret.append("<tr><th>Select</th><th colspan=\"2\">Away Team</th><th>Score</th><th>Big Play Count</th><th colspan=\"2\">Home Team</th><th>Score</th><th>Select</th></tr></thead>\n");
		int i = 0;
		for (Score score : scores) {
			++i;
			boolean big_score = score.getBigPlaysCount() > 0;

			ret.append("<tr>"
					+ "<td class=\"fg-center\">"
					+ ( big_score==false?"<input type=\"radio\" name=\"game" + i + "\" value=\"" + (((i-1)*2) + 1) + "\" checked=\"checked\"></td>":"</td>" )
					+ "<td><img src=\""
					+ score.getAwayTeam().getTeamLogo() + "\"/></td><td>"
					+ score.getAwayTeam().getTeamName() + "<br/>" + score.getAwayTeam().getTeamRecord()
					+ "</td><td class=\"fg-center\">"
					+ score.getAwayTeam().getTotalScore()
					+ "</td><td class=\"fg-center\">"
					+ score.getBigPlaysCount() + "</td><td><img src=\""
					+ score.getHomeTeam().getTeamLogo() + "\"/></td><td>"
					+ score.getHomeTeam().getTeamName() + "<br/>" + score.getHomeTeam().getTeamRecord()
					+ "</td><td class=\"fg-center\">"
					+ score.getHomeTeam().getTotalScore() + "</td>"
					+ "<td class=\"fg-center\">"
					+ ( big_score==false?"<input type=\"radio\" name=\"game" + i + "\" value=\"" + ( i * 2 ) + "\" ></td>":"</td>" )
					+ "</tr>\n");
		}
		ret.append("</table>");
		ret.append("</div>\n");
		ret.append("<input type=\"hidden\" name=\"total_games\" id=\"total_games\" value=\"" + scores.size() + "\">" );

		return ret.toString();
   }

   public static String getGameWithSpreads( int week, int iCurrentWeek )
   {
	   List<SpreadData> sd = null;
	   
		StringBuilder ret = new StringBuilder();

		String url = "http://www.nfl.com/scores";
		String strWeek = "Current Week";
		
		if (week > 0 && week < 18) {
			Date dt = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy");

			url += "/" + fmt.format(dt) + "/REG" + week;
			strWeek = String.format("Week %d", week);
		}

		if( week == iCurrentWeek )
		{
			sd = Spreads.getSpreadsForCurrentWeek();
		}
		else
		{
			sd = Spreads.getSpreadsForWeek( week );
		}
		NFLScores nfl = new NFLScores(url);
		List<Score> scores = nfl.getScores();

		ret.append("<div class=\"game\">");
		ret.append("<p class=\"fg-center\">" + strWeek + "</p>");
		ret.append("<table id=\"games\"><thead>");
		ret.append("<tr><th>Select</th><th colspan=\"2\">Away Team</th><th>Score</th><th>Spread</th><th colspan=\"2\">Home Team</th><th>Score</th><th>Select</th></tr></thead>\n");
		int i = 0;
		for (Score score : scores) {
			++i;
			boolean big_score = score.getBigPlaysCount() > 0;
			double spread = Spreads.getSpreadForTeam( 
									score.getAwayTeam().getTeamName(), sd);
			if( spread == 0.0 ) 
			{
				spread = Spreads.getSpreadForTeam(
									score.getHomeTeam().getTeamName(), sd );
			}
					
			ret.append("<tr>"
					+ "<td class=\"fg-center\">"
					+ ( big_score==false?"<input type=\"radio\" name=\"game" + i + "\" value=\"" + (((i-1)*2) + 1) + "\" checked=\"checked\"></td>":"</td>" )
					+ "<td><img src=\""
					+ score.getAwayTeam().getTeamLogo() + "\"/></td><td>"
					+ score.getAwayTeam().getTeamName() + "<br/>" + score.getAwayTeam().getTeamRecord()
					+ "</td><td class=\"fg-center\">"
					+ score.getAwayTeam().getTotalScore()
					+ "</td><td class=\"fg-center\">"
					+ spread + "</td><td><img src=\""
					+ score.getHomeTeam().getTeamLogo() + "\"/></td><td>"
					+ score.getHomeTeam().getTeamName() + "<br/>" + score.getHomeTeam().getTeamRecord()
					+ "</td><td class=\"fg-center\">"
					+ score.getHomeTeam().getTotalScore() + "</td>"
					+ "<td class=\"fg-center\">"
					+ ( big_score==false?"<input type=\"radio\" name=\"game" + i + "\" value=\"" + ( i * 2 ) + "\" ></td>":"</td>" )
					+ "</tr>\n");
		}
		ret.append("</table>");
		ret.append("</div>\n");
		ret.append("<input type=\"hidden\" name=\"total_games\" id=\"total_games\" value=\"" + scores.size() + "\">" );

		return ret.toString();
   }


}
