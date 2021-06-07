<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.flyaway.dao.hibernateUtil" %>
<%@ page import="com.flyaway.model.sourceLocations" %>
<%@ page import="com.flyaway.model.destnLocations" %>
<%@ page import="com.flyaway.model.airlines" %>
<%@ page import="com.flyaway.dao.airlinesOps" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Schedule Flights</title>
<style>

.bgimg {
	background-size: cover;
	background-image: url("images/bgicon.png");
	min-height: 100%;
}

#search {
        height: 300px;
        width : 1000px;
        margin: 0 auto;
        margin-top: 100px;
        border: 2px solid;
        background-color: white;
        align-self: center;
        border-radius: 25px;
    }
    
  table {
        border-spacing: 0 15px;
      }
      
 #back
 {
	 margin-top: 50px;
 	margin-right: 200px;
 }


</style>
<script>
		function getminDate() {
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth() + 1; //January is 0 so need to add 1 to make it 1!
			var yyyy = today.getFullYear();
			if (dd < 10) {
				dd = '0' + dd
			}
			if (mm < 10) {
				mm = '0' + mm
			}

			today = yyyy + '-' + mm + '-' + dd;
			document.getElementById("traveldate").setAttribute("min", today);

			var newDate = new Date();
			var day = newDate.getDate();
			var month = newDate.getMonth() + 1; //January is 0 so need to add 1 to make it 1!
			var year = newDate.getFullYear() + 1;
			if (day < 10) {
				day = '0' + day
			}
			if (month < 10) {
				month = '0' + month
			}

			newDate = year + '-' + month + '-' + day;
			document.getElementById("traveldate").setAttribute("max", newDate);
			
			var errormsg ='${errormsg}';
			document.getElementById("error").innerHTML = errormsg;
			var successmsg ='${successmsg}';
			document.getElementById("msg").innerHTML = successmsg;
		}
		
		function validateForm()
		{
			document.getElementById("error").innerHTML = "";
			document.getElementById("msg").innerHTML = "";
			
			var from = document.getElementById("srclocation");
		    var src = from.options[from.selectedIndex].value;
		    var to = document.getElementById("destnlocation");
		    var destn =to.options[to.selectedIndex].value;
		    var result = src.localeCompare(destn);
			    if(result == 0) {
			        document.getElementById("error").innerHTML = "From location and To location must not be same!";
			        return false;
			      } 
		}
		
</script>
</head>
<body onload="getminDate()" class="bgimg" onsubmit ="return validateForm()">
	<% 
	  List<sourceLocations> slist = hibernateUtil.getSource(); 
	  pageContext.setAttribute("slist", slist);
	  
	  List<destnLocations> dlist = hibernateUtil.getDestination(); 
	  pageContext.setAttribute("dlist", dlist);
	  
	  List<airlines> alist = airlinesOps.getAirlinesInService();
	  pageContext.setAttribute("alist", alist); 
	  %>
	  <div id="back" align="right"><a href="adminDashboard.jsp"><span style="color:blue;font-size:20px;">Go Back</span></a></div>
	  <div align=center id="search">
		<h2>Schedule Flights</h2>
		<form action="scheduleFlights" method="post">
		<table>
			<tr>
				<td>
					<label for="fcode">Flight Id  </label>
					<select name="flightcode" required>
						<option value="">--Select--</option>
						<c:forEach items="${pageScope.alist}" var="flist">
							<option value="${flist.airlineId}">${flist.airlineId}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<label for="from">From</label>
					<select name="srclocation" id = "srclocation" required>
						<option value="">--Select--</option>
						<c:forEach items="${pageScope.slist}" var="sourcelist">
							<option value="${sourcelist.srcId}">${sourcelist.srcname}</option>
						</c:forEach>
					</select>
				</td> 
				<td>
					<label for="to">To</label> 
					<select name="destnlocation" id = "destnlocation" required>
						<option value="">--Select--</option>
						<c:forEach items="${pageScope.dlist}" var="destnslist">
							<option value="${destnslist.destnId}">${destnslist.destnname}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label for="date">Date of Travel</label> 
				<input type="date" id="traveldate" name="traveldate" required></td>
				<td><label for="dtime">Departure Time</label><input type="time" id="deptime" name="deptime" required></td>
				<td><label for="atime">Arrival Time</label><input type="time" id="arrtime" name="arrtime" required></td>
			</tr>
				<td><label for="tSeats">Total Seats</label> 
				<input type="number" id="totalSeats" name="totalSeats" maxlength=3 min="80" max="300" required></td>
				<td><label for="aSeats">Available Seats</label> 
				<input type="number" id="availSeats" name="availSeats" maxlength=3 min="80" max="300" required></td>
				<td><label for="price">Price</label> 
				<input type="text" id="fprice" name="fprice" Maxlength=20 pattern="^\d+\.\d{2}$" Title = "Price must be in xxxx.xx format" required></td>
			<tr>
			</tr>
			</table>
			<input type="submit" id="addbutton" value="  Save  ">
			<br>
			<br>
			<span id="error" style="color:red"></span>
			<br>
			<span id="msg" style="color:black"></span>
			</form>
			</div>
</body>
</html>