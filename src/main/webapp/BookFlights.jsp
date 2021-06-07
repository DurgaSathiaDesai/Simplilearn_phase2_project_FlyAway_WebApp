<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 
<%@ page import="com.flyaway.dao.hibernateUtil" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Flights</title>
<script src = "http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src = "http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<style>
.bgimg {
	background-size: cover;
	background-image: url("images/bgicon.png");
	min-height: 100%;
}

#home
 {
	 margin-top: 50px;
 	margin-right: 200px;
 }
 
 #back
 {
	 margin-top: 30px;
 	margin-left: 200px;
 }
</style>
</head>
<body class="bgimg">
	<%
		String finfoId = request.getParameter("flightcode");
		HttpSession hsession = request.getSession(false);
		hsession.setAttribute("finfoId", finfoId);
		double fare =  hibernateUtil.getPrice(finfoId);
		hsession.setAttribute("fare",fare);
	%>
	<div id="home" align="right"><a href="index.jsp"><span style="color:blue;font-size:20px;">Home</span></a></div>
    <div id="back" align="left"><a href="displayFlights.jsp"><span style="color:blue;font-size:20px;">Go Back</span></a></div>
	  <div align="center">
	  <form action="bookFlight" method="post">
	  		<table>
	  			<caption><h2>Passenger Contact details</h2></caption>
				<tr>
					<td><label for="Title">Title</label></td> 
	  				<td><select name="title" id ="title">
							<option value="Mr">Mr.</option>
							<option value="Mrs">Mrs.</option>
							<option value="Ms">Ms.</option>
						</select>
					</td>
					<td>First Name </td>
					<td><input type="text" name="fname" id="fname" Maxlength=50 pattern="[A-Za-z]+" title = "Name must contain only alphabets" required></td>
					<td>Last Name </td>
					<td><input type="text" name="lname" id="lname" Maxlength=50 pattern="[A-Za-z]+" title = "Name must contain only alphabets" required></td>
				</tr>
				<tr>
					<td>Email </td>
					<td><input type="text" name="email" id="email" Maxlength=50 pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Please enter a valid email address" required></td>
					<td>Contact Number</td>
					<td><input type="text" name="cno" id="cno" Maxlength=50 pattern="[6-9]{1}[0-9]{9}" title="Please enter a valid 10 digit mobile no!" required></td>
				</tr>
			</table>
			<c:set var = "pt" scope = "page" value ="title"/>
			<c:set var = "fn" scope = "page" value ="fname"/>
			<c:set var = "ln" scope = "page" value ="lname"/>
			<table cellpadding="2">
				<caption><h2>Passenger details</h2></caption>
				<c:forEach var="Passenger" items="${noOfPassengers}">
				<c:set var = "pass" scope = "page" value ="${Passenger}"/>
				<tr><th>${Passenger}</th></tr>
                <tr>
                	<c:set var = "ptitle" scope = "page" value = "${pass}${pt}"/>
	  				<td><label>Title </label><select name="${ptitle}" id ="${ptitle}">
							<option value="Mr">Mr.</option>
							<option value="Mrs">Mrs.</option>
							<option value="Ms">Ms.</option>
						</select>
					</td>
					<td><label>First Name</label></td>
					<c:set var = "pfname" scope = "page" value = "${pass}${fn}"/>
					<td><input type="text" name="${pfname}" id="${pfname}" pattern="[A-Za-z]+" title = "Name must contain only alphabets" Maxlength=50 required></td>
					<td>Last Name </td>
					<c:set var = "plname" scope = "page" value = "${pass}${ln}"/>
					<td><input type="text" name="${plname}" id="${plname}" pattern="[A-Za-z]+" title = "Name must contain only alphabets" Maxlength=50 required></td>
                </tr>
                 </c:forEach>
			</table>
	  	
	  		<table>
	  			<caption><h2>Payment details</h2></caption>
				<tr>
				<td><input type="text" name="cardnumber" id="cardnumber" Maxlength=19 placeholder="Card Number" pattern="[0-9]{16}" title="Please enter your 16 digit Card number!" required></td>
				<td><label>Expiry Month</label><select name="emonth" id ="emonth" placeholder="Expiry Month" required>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
						<label>Expiry Year</label>
						<select name="eyear" id ="eyear" placeholder="Expiry Year" required>
							<option value="2021">2021</option>
							<option value="2022">2022</option>
							<option value="2023">2023</option>
							<option value="2024">2024</option>
							<option value="2025">2025</option>
							<option value="2026">2026</option>
							<option value="2027">2027</option>
							<option value="2028">2028</option>
							<option value="2029">2029</option>
							<option value="2030">2030</option>
						</select>
					</td>
					<td><input type="text" name="fullname" id="fullname" Maxlength=50 placeholder="Full Name" required>
					<input type="text" name="cvv" id="cvv" Maxlength=3 placeholder="CVV" pattern="[0-9]{3}" title="Please enter your 3 digit CVV!"  required>
					</td>
				</tr>
				</table>
				<%-- <c:out value="Total fare : "/> --%>
				<%-- <c:set var = "price" scope = "page" value = "${${fare}*${noOfPass}}"/>
				<c:out value="${price}"/> --%>
				<%= "<h3> Total fare : "+(Double.parseDouble(session.getAttribute("fare").toString())*Integer.parseInt(session.getAttribute("noOfPass").toString()))+"</h3>"%>
				<input type="submit" id="paybutton" value="  Confirm Payment  ">
	  	</form>
	  </div> 
</body>
<script>
      $(document).ready(function() {
         function disablePrev() { window.history.forward() }
         window.onload = disablePrev();
         window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
      });
   </script>
</html>