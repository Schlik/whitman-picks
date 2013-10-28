package com.shlick.data;

import org.mortbay.log.Log;

public class setup {
	
	public static void init()
	{
		populateRoles();
		populateEmails();
		populateUserRoles();
	}
 	private static void populateEmails()
	{
		
		String[] all_emails = {
				"twittman@wittmanconstruction.com",
				"roberthwittmann@aol.com",
				"eddie.mctague@gmail.com",
				"dawn0571@verizon.net", 
				"dwittesq@aol.com",
				"jcolfer@uslogisticsnj.com",
				"mikeytee@verizon.net",
				"ejp72689@gmail.com",
				"jpboll@aol.com",
				"bwhaleycb@yahoo.com",
				"rmchale@nopests.com",
				"jachirico@championrestorationexperts.com",
				"z.michaux@yahoo.com",
				"bcolfer@uslogisticsnj.com",
				"duffieldt@westinghouselighting.com",
				"billhanigan@yahoo.com",
				"kenzmm@aol.com",
				"john.walls@fmglobal.com",
				"dffoster@comcast.net",
				"coles0913@gmail.com",
				"jcarusi66@gmail.com",
				"bmayer@uslogisticsnj.com",
				"duketigers@aol.com",
				"chadder15@comcast.net",
				"epowell1957@gmail.com",
				"wjrobertsoninc@aol.com",
				"chotto@uslogisticsnj.com",
				"joek8008@aol.com",
				"lslclrk@yahoo.com",
				"wcolfer@specialty-freight.com",
				"al-snyder@comcast.net",
				"pitri14@hotmail.com",
				"mikefattfsu@msn.com",
				"robert.luebkemann@sungard.com",
				"bmcquaide200@aol.com",
				"Scott.Connolly@dbr.com",
				"memig19@gmail.com",
				"bc.redbrick@gmail.com",
				"bcoylee23@gmail.com",
				"maribeldelgado45@gmail.com",
				"kris2328@aol.com",
				"Egan86@comcast.net",
				"robertpbauer@gmail.com",
				"tjbats14@gmail.com",
				"joe@consertech.com",
				"barb144@comcast.net",
				"jmcginty@wittmanconstruction.com",
				"ccuster@nthdegree.com",
				"vange619@comcast.net",
				"mkdandrea24@yahoo.com",
				"lcolferjr@uslogisticsnj.com",
				"jchirico@championrestorationexperts.com",
				"andrew69@comcast.net",
				"edm146@comcast.net",
				"tbran045@yahoo.com",
				"ratkevic111@gmail.com",
				"rvasdias@gmail.com",
				"collin.crotty@gmail.com",
				"jlennon980@aol.com",
				"jay.defazio@mcsservices.com",
				"k.zimmer989@gmail.com",
				"dhettinger124@aol.com",
				"twhite56@verizon.net",
				"tony.white@mcsservices.com", 
				"reuwer@hotmail.com",
				"kmgarber@hotmail.com",
				"jimb_usl@hotmail.com",
				"jimmattb@msn.com",
				"bernjoan0512@bellsouth.net",
				"pezhead8569051452@yahoo.com",
				"davewalker254@gmail.com",
				"nascar1rpb@comcast.net",
				"Bustles@comcast.net",
				"Gmanboy@aol.com",
				"kmock698@aol.com",
				"todd.wojtylak@missionse.com",
				"trk8@live.com",
				"sonnynix@comcast.net",
				"pfavila@comcast.net",
				"skline57@gmail.com",
				"cook3657@aol.com",
				"gwaters22@comcast.net",
				"wjcolfer4@specialty-freight.com",
				"pierrelanglois63@me.com",
				"jaycoghlan73@yahoo.com",
				"suchamark@msn.com",
				"eagles240pat@hotmail.com",
				"susan@hotfoilehs.com",
				"KMitch0310@Verizon.net",
				"billymoe123@yahoo.com",
				"dgfeldman@comcast.net",
				"ron.schreyer@hotmail.com",
				"steveosteveo100@gmail.com",
				"garyswhite@comcast.net",
				"cfmanzo7@gmail.com",
				"jim8511@yahoo.com",
				"douglasleber81@gmail.com",
				"william.hunter1@yahoo.com",
				"mted26@yahoo.com",
				"tjaccardo@yahoo.com",
				"monkerjm@gmail.com",
				"neil.spence3@gmail.com",
				"karhcra@aol.com",
				"kshorter@csc.com",
				"brian.defazio@mcsservices.com",
				"westcliffruby@verizon.net",
				"pskbowler@gmail.com",
				"pcynewski@gmail.com",
				"gatorgirlnj95@yahoo.com",
				"gregrebs@verizon.net",
				"brandonchirico@yahoo.com",
				"wreckage07@rocketmail.com",
				"lynchline@aol.com",
				"MikeyMarchlik@gmail.com",
				"francis.gomes@fgomes.com"				
		};
				
		for( int i = 0; i < all_emails.length; i++ )
		{
			if( !DAL.addUser( new User( all_emails[i], all_emails[i], all_emails[i] ) ) )
			{
				Log.info( "Failed to add email: " + all_emails[i] );
			}		
		}
	}
 	private static void populateUserRoles()
	{
 		String[] admin_emails = {
				"twittman@wittmanconstruction.com",
				"roberthwittmann@aol.com",
				"MikeyMarchlik@gmail.com",
				"francis.gomes@fgomes.com"
 		};
		String[] all_emails = {
				"eddie.mctague@gmail.com",
				"dawn0571@verizon.net", 
				"dwittesq@aol.com",
				"jcolfer@uslogisticsnj.com",
				"mikeytee@verizon.net",
				"ejp72689@gmail.com",
				"jpboll@aol.com",
				"bwhaleycb@yahoo.com",
				"rmchale@nopests.com",
				"jachirico@championrestorationexperts.com",
				"z.michaux@yahoo.com",
				"bcolfer@uslogisticsnj.com",
				"duffieldt@westinghouselighting.com",
				"billhanigan@yahoo.com",
				"kenzmm@aol.com",
				"john.walls@fmglobal.com",
				"dffoster@comcast.net",
				"coles0913@gmail.com",
				"jcarusi66@gmail.com",
				"bmayer@uslogisticsnj.com",
				"duketigers@aol.com",
				"chadder15@comcast.net",
				"epowell1957@gmail.com",
				"wjrobertsoninc@aol.com",
				"chotto@uslogisticsnj.com",
				"joek8008@aol.com",
				"lslclrk@yahoo.com",
				"wcolfer@specialty-freight.com",
				"al-snyder@comcast.net",
				"pitri14@hotmail.com",
				"mikefattfsu@msn.com",
				"robert.luebkemann@sungard.com",
				"bmcquaide200@aol.com",
				"Scott.Connolly@dbr.com",
				"memig19@gmail.com",
				"bc.redbrick@gmail.com",
				"bcoylee23@gmail.com",
				"maribeldelgado45@gmail.com",
				"kris2328@aol.com",
				"Egan86@comcast.net",
				"robertpbauer@gmail.com",
				"tjbats14@gmail.com",
				"joe@consertech.com",
				"barb144@comcast.net",
				"jmcginty@wittmanconstruction.com",
				"ccuster@nthdegree.com",
				"vange619@comcast.net",
				"mkdandrea24@yahoo.com",
				"lcolferjr@uslogisticsnj.com",
				"jchirico@championrestorationexperts.com",
				"andrew69@comcast.net",
				"edm146@comcast.net",
				"tbran045@yahoo.com",
				"ratkevic111@gmail.com",
				"rvasdias@gmail.com",
				"collin.crotty@gmail.com",
				"jlennon980@aol.com",
				"jay.defazio@mcsservices.com",
				"k.zimmer989@gmail.com",
				"dhettinger124@aol.com",
				"twhite56@verizon.net",
				"tony.white@mcsservices.com", 
				"reuwer@hotmail.com",
				"kmgarber@hotmail.com",
				"jimb_usl@hotmail.com",
				"jimmattb@msn.com",
				"bernjoan0512@bellsouth.net",
				"pezhead8569051452@yahoo.com",
				"davewalker254@gmail.com",
				"nascar1rpb@comcast.net",
				"Bustles@comcast.net",
				"Gmanboy@aol.com",
				"kmock698@aol.com",
				"todd.wojtylak@missionse.com",
				"trk8@live.com",
				"sonnynix@comcast.net",
				"pfavila@comcast.net",
				"skline57@gmail.com",
				"cook3657@aol.com",
				"gwaters22@comcast.net",
				"wjcolfer4@specialty-freight.com",
				"pierrelanglois63@me.com",
				"jaycoghlan73@yahoo.com",
				"suchamark@msn.com",
				"eagles240pat@hotmail.com",
				"susan@hotfoilehs.com",
				"KMitch0310@Verizon.net",
				"billymoe123@yahoo.com",
				"dgfeldman@comcast.net",
				"ron.schreyer@hotmail.com",
				"steveosteveo100@gmail.com",
				"garyswhite@comcast.net",
				"cfmanzo7@gmail.com",
				"jim8511@yahoo.com",
				"douglasleber81@gmail.com",
				"william.hunter1@yahoo.com",
				"mted26@yahoo.com",
				"tjaccardo@yahoo.com",
				"monkerjm@gmail.com",
				"neil.spence3@gmail.com",
				"karhcra@aol.com",
				"kshorter@csc.com",
				"brian.defazio@mcsservices.com",
				"westcliffruby@verizon.net",
				"pskbowler@gmail.com",
				"pcynewski@gmail.com",
				"gatorgirlnj95@yahoo.com",
				"gregrebs@verizon.net",
				"brandonchirico@yahoo.com",
				"wreckage07@rocketmail.com",
				"lynchline@aol.com"
		};
		
		for( int i = 0; i < admin_emails.length; i++ )
		{
			if( !DAL.addUserRole( new UserRole( admin_emails[ i ], Role.ADMINISTRATOR ) ) )
			{
				Log.info( "Failed to add email: " + admin_emails[i] );
			}
		}
				
		for( int i = 0; i < all_emails.length; i++ )
		{
			if( !DAL.addUserRole( new UserRole( all_emails[ i ], Role.PLAYER ) ) )
			{
				Log.info( "Failed to add email: " + all_emails[i] );
			}
		}
	}

	private static void populateRoles()
	{
		
		String[] all_roles = {
				"PLAYER",
				"ADMINISTRATOR",
				"SUPER_USER"			
		};
		
		for( int i = 0; i < all_roles.length; i++ )
		{
			if( !DAL.addRole( all_roles[i] ) )
			{
				Log.info( "Failed to add role: " + all_roles[i] );
			}
		}
	}
	
/*	private static void populateTeamCities()
	{
		TeamCity[] teamcities = {
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
*/	
}
