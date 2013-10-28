package com.shlick.util;

import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.shlick.data.SpreadData;
import com.shlick.data.TeamCity;


public class Spreads {
	enum TFLDS { DT, TIM, ZON, FAV, SCO, UND };
	private URL url;
	private static final Logger log = Logger.getLogger(Spreads.class.getName());
	private static Document dom;
	private static List<SpreadData> data = new ArrayList<SpreadData>();
	private static final String urlDefSource = "http://www.footballlocks.com/nfl_point_spreads.shtml";
	private static final String urlSource = "http://www.footballlocks.com/nfl_point_spreads_week_";
	private int week = 0;
	
	public Spreads(String src) {
		init(src);
	}

	public Spreads( int iWeek )
	{
		week = iWeek;
		init( iWeek );
	}
	
	public Spreads() {
		this( urlDefSource );
	}

	private void init(String src) {
		try {
			url = new URL(src);

			dom = Jsoup.parse(url, 30000 ); 
			if( week == 0 )
				extractSpreadData();
			else
				extractMainSpreadData();
			extractMondaySpreadData();
			updateWithTeamNames();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void init( int iWeek ) {
		String src = urlSource + Integer.toString( iWeek ) + ".shtml" ;	
		init( src );
	}
	
	private String newYorkTeam( String team )
	{
		String ret = team.replace("At", " ").trim();
		ret = ret.replace( "NY",  " " ).trim();
		return ret;
	}
	
	private void extractMondaySpreadData()
	{
		try
		{
			Element trs = dom.select( "table" ).get( 36 );
			
			Elements fst	 = trs.select("table").get( 2 )
									.select( "table" ).select( "table" );

			
			Elements trs2 = fst.last().select("tr");
			Element e = trs2.last();

			Elements tds = e.select( "td" );
			
			String dttime = "";
			String favteam = "";
			String spread = "";
			String undteam = "";
			
			TFLDS cmd = TFLDS.DT;
			for( Element d : tds )
			{
				String v = d.text().trim();
				switch( cmd )
				{
					case DT:
						dttime = v;
						cmd = TFLDS.FAV;
						break;
					case FAV:
						favteam = v;
						
						cmd = TFLDS.SCO;
						break;
					case SCO:
						spread = v;
						if( spread.equals( "PK" ) ) spread = "0";
						cmd = TFLDS.UND;
						break;
					case UND:
						undteam = v;
						
						cmd = TFLDS.DT;
						data.add( new SpreadData( dttime, favteam, spread, undteam ) );
						dttime = "";
						favteam = "";
						spread = "";
						undteam = "";
						break;
					default:
						break;
				}
			}

				
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}

	private void extractMainSpreadData()
	{
		data.clear();
		try
		{
//			Element trs = dom.select( "table" ).get( 36 );
//			
//			Elements fst	 = trs.select("table").get( 2 )
//									.select( "table" ).select( "table" );
//
//			
//			Elements trs2 = fst.first().select("tr");
			
			Elements alltrs = dom.select( "tr" );
			List<Element> trs2 = alltrs.subList(51, 69);
			
			if( Debug.ON )
			{
				int i = 0;
				for( Element e : trs2 )
				{
					log.info( "FRANCIS <TR> (" + i++ + ") ::" );
					log.info( e.html() + "\n\n" );
					if( i == 17 ) break;
				}
			}			

			for( Element e : trs2 )
			{
				Elements tds = e.select( "td" );
				
				String dttime = "";
				String favteam = "";
				String spread = "";
				String undteam = "";
				
				TFLDS cmd = TFLDS.DT;
				for( Element d : tds )
				{
					String v = d.text().trim();
					if( v.equals("Date & Time" ) ) break; //skip header
					switch( cmd )
					{
						case DT:
							dttime = d.text();
							cmd = TFLDS.FAV;
							break;
						case FAV:
							favteam = d.text();
							
							cmd = TFLDS.SCO;
							break;
						case SCO:
							spread = v;
							if( spread.equals( "PK" ) ) spread = "0";
							if( v.contains( "PK" ) ) spread = "0";
							cmd = TFLDS.UND;
							break;
						case UND:
							undteam = d.text();
							
							cmd = TFLDS.DT;
							if( !dttime.isEmpty() )
							{
								data.add( new SpreadData( dttime, favteam, spread, undteam ) );
							}
							dttime = "";
							favteam = "";
							spread = "";
							undteam = "";
							break;
						default:
							break;
					} //switch
				} //for d
			} //for e

				
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	private void extractSpreadData()
	{
		data.clear();
		try
		{
			Elements alltrs = dom.select( "tr" );
			List<Element> trs2 = alltrs.subList(53, 69);
			
			if( Debug.ON )
			{
				int i = 0;
				for( Element e : trs2 )
				{
					log.info( "FRANCIS <TR> (" + i++ + ") ::" );
					log.info( e.html() + "\n\n" );
					if( i == 17 ) break;
				}
			}

			for( Element e : trs2 )
			{
				Elements tds = e.select( "td" );
				
				String dttime = "";
				String favteam = "";
				String spread = "";
				String undteam = "";
				
				TFLDS cmd = TFLDS.DT;
				for( Element d : tds )
				{
					String v = d.text().trim();
					if( v.equals("Date & Time" ) ) break; //skip header
					switch( cmd )
					{
						case DT:
							dttime = d.text();
							cmd = TFLDS.FAV;
							break;
						case FAV:
							favteam = d.text();
							
							cmd = TFLDS.SCO;
							break;
						case SCO:
							spread = v;
							if( spread.equals( "PK" ) ) spread = "0";
							if( v.contains( "PK" ) ) spread = "0";
							cmd = TFLDS.UND;
							break;
						case UND:
							undteam = d.text();
							
							cmd = TFLDS.DT;
							data.add( new SpreadData( dttime, favteam, spread, undteam ) );
							dttime = "";
							favteam = "";
							spread = "";
							undteam = "";
							break;
						default:
							break;
					} //switch
				} //for d
			} //for e

				
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	
	private void updateWithTeamNames()
	{
		TeamCity[] cities = TeamCity.getTeamCities();
		
		for( SpreadData sd : data )
		{
			String ft = sd.getFavTeam();
			String ut = sd.getUnderdogTeam();
			
			/*ft = ft.replace( "At NY ", "" );
			ft = ft.replace( "At ", "" );
			ut = ut.replace( "At NY ", "" );
			ut = ut.replace( "At ", "" );*/
			
			ft = this.newYorkTeam( ft );
			ut = this.newYorkTeam( ut );
			
			for( TeamCity tc : cities )
			{
				String city = tc.getCity();
				String name = tc.getTeamName();
				
				if( city.equals( ft ) )
				{
					ft = name;
					//break;
				}
				if( city.equals( ut ) )
				{
					ut = name;
					//break;
				}
			}
			sd.setFavTeam( ft );
			sd.setUnderdogTeam( ut );
		}
	}
	
	public void showTables( PrintWriter out )
	{
		out.println( "<table id=\"spreadtable\"><tr><th>Date</th><th>Favorite Team</th><th>Spread</th><th>Underdog Team</th></tr>" );
		for( SpreadData sd : data )
		{
			out.println( "<tr><td>" + sd.getDatetime() + "</td>" 
					+"<td>" + sd.getFavTeam() + "</td>" 
					+ "<td>" + sd.getSpread() + "</td>" 
					+ "<td>" + sd.getUnderdogTeam() + "</td></tr>" );			
		}
		out.println( "</table>");
		
	}
	
	public List<SpreadData> getSpreads()
	{
		return data;
	}

	public static List<SpreadData> getSpreadsForCurrentWeek()
	{
		Spreads s = new Spreads();
		return s.getSpreads();
	}
	
	public static List<SpreadData> getSpreadsForWeek( int iWeek )
	{
		Spreads s = new Spreads( iWeek );
		
		return s.getSpreads();
	}

	public static double getSpreadForTeam( String teamName, List<SpreadData> sd )
	{
		double ret = 0;
		
		for( SpreadData d : sd )
		{
			if( d.getFavTeam().equals( teamName ) 
					|| d.getUnderdogTeam().equals( teamName ) )
			{
				String v = d.getSpread();
				ret = Double.parseDouble( v );
				break;
			}
		}
		return ret;
	}
}
