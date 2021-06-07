<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ page import="java.util.List" %>
<%@ page import = "java.time.LocalDate" %>
<%@ page import="com.flyaway.dao.hibernateUtil" %>
<%@ page import="com.flyaway.model.flightInfo" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Scheduled Flights</title>
<style>
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
		}
		
</script>
</head>
<body onload="getminDate()">
	  <% 
	  List<flightInfo> fblist = null;
	 
	  String tDate = request.getParameter("traveldate");
	 
	  LocalDate date = LocalDate.now();
	  if(tDate != null)
	  {
		  date = LocalDate.parse(tDate);
		  fblist = hibernateUtil.getScheduledFlightsbyDate(date);
	  }
	  else
	  {
		  
		  fblist = hibernateUtil.getScheduledFlightsbyDate(date);
	  }
	  
	  pageContext.setAttribute("flist", fblist);
	  pageContext.setAttribute("trDate", date);
	  %>
	  <div align="center">
	  <br>
	  <br>
	  <div style="padding: 10px" id = "link" align="center">
		<a href="scheduleFlights.jsp" target="_parent"><span style="color: white">Schedule Flights</span></a> 
	</div>
	<br>
	<br>
	  <form action="listScheduledFlights.jsp" method="post">
	   <label>Travel Date: </label><input type="date" id="traveldate" name="traveldate" onchange="this.form.submit();" required>
	   
	    <c:set var="fbinfo" scope="page" value="${flist}"/> 
   	<c:if test="${fn:length(fbinfo) != 0}">
	<table border="1" cellpadding="5">
            <caption><h2>List of Scheduled Flights</h2></caption>
            <tr>
                <th>FlightCode</th>
                <th>From--->To</th>
                <th>TravelDate</th>
                <th>Depart-Arrive</th>
                <th>Total Seats</th>
                <th>Available Seats</th>
                <th>Fare</th>
                <th>Flight Status</th>
            </tr>
            <c:forEach var="flight" items="${pageScope.flist}">
                <tr>
                	<td>
                	<c:out value="${flight.getFlightCode().getAirlineId()}"/>
                	</td>
                	<td>
                	<c:out value="${flight.getSrc().getSrcId()}"/><c:out value="--> "/><c:out value="${flight.getDestination().getDestnId()}"/>
                	</td>
                    <td><c:out value="${flight.getTravelDate()}" /></td>
                    <td><c:out value="${flight.getDeptTime()}" /><c:out value="-" /><c:out value="${flight.getArrTime()}" /></td>
                    <td><c:out value="${flight.getTotalSeats()}" /></td>
                    <td><c:out value="${flight.getAvailSeats()}" /></td>
                    <td><c:out value="${flight.getPrice()}" /></td>
                    <td><c:out value="${flight.getFlightStatus()}" /></td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
         <c:if test="${fn:length(fbinfo) == 0}">
	     <!-- <h2>No Bookings!</h2> -->
	     <br>
	     <br>
	     <c:set var="fbdate" scope="page" value="${trDate}"/> 
	     <c:out value="No flights scheduled for date "/><c:out value="${fbdate}"/>
	  </c:if>
        </form>
        </div>
</body>
</html>