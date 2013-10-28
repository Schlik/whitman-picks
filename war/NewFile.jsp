<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.apphosting.api.ApiProxy" %>
<%@ page import="com.google.apphosting.api.ApiProxy.Environment" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta charset="UTF-8">
<title><Jays Picks></title>
<link rel="stylesheet" href="/stylesheets/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" href="/stylesheets/liquid-slider.css">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/webfont/1.4.10/webfont.js"></script>
<script src="/js/jquery.easing.1.3.js"></script>
<script src="/js/jquery.touchSwipe.min.js"></script>
<script src="/js/jquery.liquid-slider.min.js"></script>
</head>
<body>
<%
	UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    if (user == null) {
          response.sendRedirect( "/login" );
    }
    out.print( user.getNickname() );
%>
<div class="liquid-slider" id="weeks">
	<div>
		<h2>Week 1</h2>
	</div>
	<div>
		<h2>Week 2</h2>
	</div>
	<div>
		<h2>Week 3</h2>
	</div>
	<div>
		<h2>Week 4</h2>
	</div>
</div>

<h1>NFL Week 3</h1>



<table border="1">

<form action="/submitPicks" method="post">

<tr>
<td><input type="radio" name="game1" value="1" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/167/full/960.gif">Eagles</img></td>
<td>    5  </td>
<td><img src="http://content.sportslogos.net/logos/7/162/full/857.gif">Chiefs</img> </td>
<td><input type="radio" name="game1" value="2"></td>
</tr>


<tr>
<td><input type="radio" name="game2" value="3" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/160/full/1053.gif">Titans</img></td>
<td>    3  </td>
<td><img src="http://content.sportslogos.net/logos/7/164/full/8e1jhgblydtow4m3okwzxh67k.gif">Chargers</img></td>
<td><input type="radio" name="game2" value="4"></td>
</tr>

<tr>
<td><input type="radio" name="game3" value="5" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/172/full/2704_minnesota_vikings-primary-2013.png">Vikings</img></td>
<td>5</td>
<td><img src="http://content.sportslogos.net/logos/7/155/full/2ioheczrkmc2ibc42c9r.gif">Browns</img></td>
<td><input type="radio" name="game3" value="6"></td>
</tr>
<tr>
<td><input type="radio" name="game4" value="7" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/151/full/y71myf8mlwlk8lbgagh3fd5e0.gif">Patriots</img></td>
<td>    7  </td>
<td><img src="http://content.sportslogos.net/logos/7/176/full/1046.gif">Buccaneers</img></td>
<td><input type="radio" name="game4" value="8"></td>
</tr>

<tr>
<td><input type="radio" name="game5" value="9" checked ></td>
<td><img src="http://content.sportslogos.net/logos/7/157/full/570.gif">Texans</img></td>
<td>    2  </td>
<td><img src="http://content.sportslogos.net/logos/7/153/full/318.gif">Ravens </img></td>
<td><input type="radio" name="game5" value="10"></td>
</tr>

<tr>
<td><input type="radio" name="game6" value="11" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/165/full/406.gif">Cowboys</img></td>
<td>    4   </td>
<td><img src="http://content.sportslogos.net/logos/7/178/full/1029.gif">Rams</img> </td>
<td><input type="radio" name="game6" value="12"></td>
</tr>

<tr>
<td><input type="radio" name="game7" value="13" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/175/full/907.gif">Saints</img>  </td>
<td>    7.5    </td>
<td><img src="http://content.sportslogos.net/logos/7/177/full/kwth8f1cfa2sch5xhjjfaof90.gif">Cardinals</img>   </td>
<td><input type="radio" name="game7" value="14"></td>
</tr>

<tr>
<td><input type="radio" name="game8" value="15" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/168/full/im5xz2q9bjbg44xep08bf5czq.gif">Redskins</img></td>
<td>    1.5    </td>
<td><img src="http://content.sportslogos.net/logos/7/170/full/cwuyv0w15ruuk34j9qnfuoif9.gif">Lions</img></td>
<td><input type="radio" name="game8" value="16"></td>
</tr>

<tr>
<td><input type="radio" name="game9" value="17" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/171/full/dcy03myfhffbki5d7il3.gif">Packers</img> </td>
<td>    1    </td>
<td><img src="http://content.sportslogos.net/logos/7/154/full/403.gif">Bengals</img></td>
<td><input type="radio" name="game9" value="18"></td>
</tr>

<tr>
<td><input type="radio" name="game10" value="19" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/174/full/f1wggq2k8ql88fe33jzhw641u.gif">Panthers</img></td>
<td>    2    </td>
<td><img src="http://content.sportslogos.net/logos/7/166/full/919.gif">Giants</img></td>
<td><input type="radio" name="game10" value="20"></td>
</tr>

<tr>
<td><input type="radio" name="game11" value="21" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/150/full/4105_miami_dolphins-primary-2013.png">Dolphins</img></td>
<td>    1.5    </td>
<td><img src="http://content.sportslogos.net/logos/7/173/full/299.gif">Falcons</img></td>
<td><input type="radio" name="game11" value="22"></td>
</tr>

<tr>
<td><input type="radio" name="game12" value="23" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/179/full/9455_san_francisco_49ers-primary-2009.gif">49ers</img></td>
<td>    10.5    </td>
<td><img src="http://content.sportslogos.net/logos/7/158/full/593.gif">Colts</img></td>
<td><input type="radio" name="game12" value="24"></td>
</tr>


<tr>
<td><input type="radio" name="game13" value="25" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/180/full/pfiobtreaq7j0pzvadktsc6jv.gif">Seahawks</img></td>
<td>    19.5    </td>
<td><img src="http://content.sportslogos.net/logos/7/159/full/8856_jacksonville_jaguars-alternate-2013.png">Jaguars</img></td>
<td><input type="radio" name="game13" value="26"></td>
</tr>

<tr>
<td><input type="radio" name="game14" value="27" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/152/full/v7tehkwthrwefgounvi7znf5k.gif">Jets</img></td>
<td>    2.5    </td>
<td><img src="http://content.sportslogos.net/logos/7/149/full/n0fd1z6xmhigb0eej3323ebwq.gif">Bills</img> </td>
<td><input type="radio" name="game14" value="28"></td>
</tr>

<tr>
<td><input type="radio" name="game15" value="29" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/169/full/364.gif">Bears </img></td>
<td>    1.5    </td>
<td><img src="http://content.sportslogos.net/logos/7/156/full/970.gif">Steelers</img></td>
<td><input type="radio" name="game15" value="30"></td>
</tr>

<tr>
<td><input type="radio" name="game16" value="31" checked></td>
<td><img src="http://content.sportslogos.net/logos/7/161/full/9ebzja2zfeigaziee8y605aqp.gif">Broncos</img></td>
<td>    15    </td>
<td><img src="http://content.sportslogos.net/logos/7/163/full/g9mgk6x3ge26t44cccm9oq1vl.gif">Raiders</img> </td>
<td><input type="radio" name="game16" value="32"></td>
</tr>


</table>

E-mail: <input type="email" name="picker_email">
Date: <input type="text" name="date" id="date" />

<input type="submit" value="Submit Picks">
</form> 

  <footer>
	<script>
		$(function(){
			$('#weeks').liquidSlider({autoSlide:true});
			
			$('#date' ).datepicker();
		});
	</script>
  </footer>
</body>

</html>