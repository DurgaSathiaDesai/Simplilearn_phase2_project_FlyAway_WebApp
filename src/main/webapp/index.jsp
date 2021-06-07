<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
.bgimg-1 {
	background-size: cover;
	background-image: url("images/flightmainpageicon.jpg");
	min-height: 100%;
}

a:link, a:visited {
	background-color: #33D4FF;
	color: white;
	padding: 15px 25px;
	text-align: center;
	text-decoration: none;
	border-radius: 25px;
	
	/* border: 2px solid blue; */
	/* display: inline-block; */
}

a:hover, a:active {
	background-color: #33B2FF;
}
#link
{
 	  margin-top: 30px;
	  margin-left: 60px;
}
</style>
</head>
<body class="bgimg-1">
	<div style="padding: 60px" id = "link">
		<a href="searchFlights.jsp"><span style="color: white">Search a Flight</span></a> 
		<a href="adminLogin.jsp"><span style="color: white">Admin Login</span></a> 
	</div>
	<div class="w3-display-left w3-text-blue-grey" style="mardin-top: 110px; margin-left: 110px;">
		<br> <span class="w3-jumbo w3-hide-small">Let's Go!</span><br>
		<span class="w3-jumbo w3-hide-small">It's Time To FlyAway!</span><br>
		<!-- <span class="w3-large">Book flight tickets at best prices</span> -->
	</div>
</body>
</html>
