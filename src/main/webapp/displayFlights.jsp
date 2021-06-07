<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Flights</title>
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
	 margin-top: 50px;
 	margin-left: 200px;
 }
</style>
<script>
		/* function enableButton() 
		{
			document.getElementById("submit").disabled = false;
			
		} */
		
		function validateForm() 
		{
			var ele =document.getElementsByName("flightcode");
			var selected = false;
			for(var i=0;i<ele.length;i++)
			{
				if(ele[i].checked == true)
				{
					selected = true;
					break;
				}
			}
			if(selected == false)
			{
				document.getElementById("error").innerHTML = "*Please select any flight!";
				return false;
			}
		}
</script>
</head>
<body class="bgimg">
	<div id="home" align="right"><a href="index.jsp"><span style="color:blue;font-size:20px;">Home</span></a></div>
    <div id="back" align="left"><a href="searchFlights.jsp"><span style="color:blue;font-size:20px;">Back to Search</span></a></div>
	
	<div align="center">
		<form action="BookFlights.jsp" method="post" onsubmit ="return validateForm()">
        <table border="1" cellpadding="5">
            <caption><h2>List of Flights</h2></caption>
            <tr>
                <th>Flight</th>
                <th>TravelDate</th>
                <th>Depart-Arrive</th>
                <th>Fare</th>
            </tr>
            <c:forEach var="flight" items="${flightlist}">
                <tr>
                	<td>
                	<input type="radio" id="${flight.getFlightCode().getAirlineId()}" name="flightcode" value="${flight.getFinfoId()}">
                	<label>${flight.getFlightCode().getAirlineId()}</label><br>
                	<c:out value="${flight.getSrc().getSrcId()}"/><c:out value="--->"/><c:out value="${flight.getDestination().getDestnId()}"/>
                	</td>
                    <td><c:out value="${flight.getTravelDate()}" /></td>
                    <td><c:out value="${flight.getDeptTime()}" /><c:out value="-" /><c:out value="${flight.getArrTime()}" /></td>
                    <td><c:out value="${flight.getPrice()}" /></td>
                </tr>
            </c:forEach>
        </table>
        <input type="image" id = "submit" src="images/bookflightsicon.png"alt="Search" width="100" height="100"/>
        <br>
			<span id="error" style="color:red"></span>
        </form>
    </div>
</body>
</html>