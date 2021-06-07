<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ page import="com.flyaway.dao.hibernateUtil" %> 
<%@ page import="com.flyaway.model.flightBooking" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Booking</title>
<style>
.bgimg {
	background-size: cover;
	background-image: url("images/bgicon.png");
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
/* #link
{
 	  margin-top: 30px;
	  margin-left: 60px;
} */

#search {
        height: 300px;
        width : 700px;
        margin: 0 auto;
        margin-top: 100px;
        border: 2px solid;
        background-color: white;
        align-self: center;
        border-radius: 25px;
    }
</style>
</head>
<body class="bgimg">
	<%-- <c:set var="bId" scope="page" value="${bookingId}"/>  --%>
	<div align="center" id="search">
   <c:if test="${bookingId == 0}"> 
   	<h3>Sorry for inconvenience!Error in Booking!Please try again!</h3>
   </c:if>
   <c:if test="${bookingId != 0}"> 
   	<h2>Thank you for booking with FlyAway</h2>
   	<% 
   		int bookingId = Integer.parseInt(request.getAttribute("bookingId").toString());
   		flightBooking fbInfo = hibernateUtil.getBookingInfo(bookingId);
   		pageContext.setAttribute("finfo", fbInfo);
   	%>
   	<c:set var="flinfo" scope="page" value="${finfo}"/> 
   	<c:if test="${flinfo != null}">
   		<table>
   			<caption><h4>Hi,Your flight booking is confirmed.We will email your ticket shortly.</h4></caption>
   			<tr>
   				<td><c:out value="Booking Id :  "/>
   				<c:out value="${flinfo.bookingId}"/></td>
   				<td><c:out value="Booking Status : Confirmed"/></td>
   			</tr>
   			<tr>
   				<td><c:out value="Flight Id :  "/>
   				<c:out value="${flinfo.flightCode}"/>
   				</td>
   				<td><c:out value="("/><c:out value="${flinfo.src}"/><c:out value="-->"/><c:out value="${flinfo.destination}"/><c:out value=")"/></td>
   			</tr>
   			<tr>
   				<td><c:out value="TravelDate :"/><c:out value="${flinfo.travelDate}"/></td>
   				<td><c:out value="Timings :"/><c:out value="${flinfo.deptTime}"/><c:out value="-"/><c:out value="${flinfo.arrTime}"/></td>
   			</tr>
   			<tr>
   				<td><c:out value="Number of passengers :"/><c:out value="${flinfo.noOfPassengers}"/></td>
   			</tr>
   		</table>
   	</c:if>
   </c:if>
   <div style="padding: 60px" id = "link">
		<a href="index.jsp"><span style="color: white">Close</span></a> 
   </div>
   </div>
</body>

</html>