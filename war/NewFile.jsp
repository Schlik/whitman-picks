<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>Wittman Picks</title>
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
<td>EAGLES</td>
<td>    5  </td>
<td> Chiefs </td>
<td><input type="radio" name="game1" value="2"></td>
</tr>


<tr>
<td><input type="radio" name="game2" value="3" checked></td>
<td>TITANS</td>
<td>    3  </td>
<td> Chargers </td>
<td><input type="radio" name="game2" value="4"></td>
</tr>

<tr>
<td><input type="radio" name="game3" value="5" checked></td>
<td> VIKINGS </td>
<td>    5  </td>
<td> Browns </td>
<td><input type="radio" name="game3" value="6"></td>
</tr>
<tr>
<td><input type="radio" name="game4" value="7" checked></td>
<td> PATRIOTS </td>
<td>    7  </td>
<td> Tambpa Bay </td>
<td><input type="radio" name="game4" value="8"></td>
</tr>

<tr>
<td><input type="radio" name="game5" value="9" checked ></td>
<td> Houston </td>
<td>    2  </td>
<td> RAVENS </td>
<td><input type="radio" name="game5" value="10"></td>
</tr>

<tr>
<td><input type="radio" name="game6" value="11" checked></td>
<td> COWBOYS</td>
<td>    4   </td>
<td> Rams   </td>
<td><input type="radio" name="game6" value="12"></td>
</tr>

<tr>
<td><input type="radio" name="game7" value="13" checked></td>
<td> SAINTS  </td>
<td>    7.5    </td>
<td> Arizona    </td>
<td><input type="radio" name="game7" value="14"></td>
</tr>

<tr>
<td><input type="radio" name="game8" value="15" checked></td>
<td> REDSKINS </td>
<td>    1.5    </td>
<td> Lions</td>
<td><input type="radio" name="game8" value="16"></td>
</tr>

<tr>
<td><input type="radio" name="game9" value="17" checked></td>
<td> Packers </td>
<td>    1    </td>
<td> BENGALS</td>
<td><input type="radio" name="game9" value="18"></td>
</tr>

<tr>
<td><input type="radio" name="game10" value="19" checked></td>
<td> CAROLINA</td>
<td>    2    </td>
<td> Giants</td>
<td><input type="radio" name="game10" value="20"></td>
</tr>

<tr>
<td><input type="radio" name="game11" value="21" checked></td>
<td> MIAMI</td>
<td>    1.5    </td>
<td> Falcons</td>
<td><input type="radio" name="game11" value="22"></td>
</tr>

<tr>
<td><input type="radio" name="game12" value="23" checked></td>
<td> 49ers</td>
<td>    10.5    </td>
<td> Colts</td>
<td><input type="radio" name="game12" value="24"></td>
</tr>


<tr>
<td><input type="radio" name="game13" value="25" checked></td>
<td> SEATTLE</td>
<td>    19.5    </td>
<td> Jacksonville</td>
<td><input type="radio" name="game13" value="26"></td>
</tr>

<tr>
<td><input type="radio" name="game14" value="27" checked></td>
<td> JETS </td>
<td>    2.5    </td>
<td> Bills </td>
<td><input type="radio" name="game14" value="28"></td>
</tr>

<tr>
<td><input type="radio" name="game15" value="29" checked></td>
<td> Bears </td>
<td>    1.5    </td>
<td> STEELERS</td>
<td><input type="radio" name="game15" value="30"></td>
</tr>

<tr>
<td><input type="radio" name="game16" value="31" checked></td>
<td> DENVER</td>
<td>    15    </td>
<td> Raiders </td>
<td><input type="radio" name="game16" value="32"></td>
</tr>


</table>

E-mail: <input type="email" name="picker_email">


<input type="submit" value="Submit Picks">
</form> 
  
</body>
<footer>
	<script>
		$(function(){
			$('#weeks').liquidSlider({autoSlide:true});
		});
	</script>
</footer>
</html>