<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ page import="java.util.List" %>
<%@ page import = "java.time.LocalDate" %>
<%@ page import="com.flyaway.dao.hibernateUtil" %>
<%@ page import="com.flyaway.model.airlines" %>
<%@ page import="com.flyaway.model.flightBooking" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Bookings</title>
<style>
	#search {
        height: 300px;
        width : 1300px;
        margin-left: 0 auto;
        margin-top: 10px;
        align-self: center;
       
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
	  List<flightBooking> fblist = null;
	
	  String tDate = request.getParameter("traveldate");
	  LocalDate date = LocalDate.now();
	  if(tDate != null)
	  {
		  date = LocalDate.parse(tDate);
		  fblist = hibernateUtil.getBookingsbyDate(date);
	  }
	  else
	  {
		 
		  fblist = hibernateUtil.getBookingsbyDate(date);
	  }
	
	  pageContext.setAttribute("fbinfolist", fblist);
	  pageContext.setAttribute("trDate", date);
	  %>
	  <div align ="center">
	  <form action="listBookings.jsp" method="post">
	 <%--  <select name="flist" required onchange="this.form.submit()">
		<option value="">--Select--</option>
			<c:forEach items="${pageScope.alist}" var="airlinelist">
				<option value="${airlinelist.airlineId}">${airlinelist.airlineId}</option>
		    </c:forEach>
	  </select> --%>
	  <label>Travel Date: </label><input type="date" id="traveldate" name="traveldate" onchange="this.form.submit();" required>
	   </form>
	   <c:set var="fbinfo" scope="page" value="${fbinfolist}"/> 
	   <c:if test="${fn:length(fbinfo) == 0}">
	     <!-- <h2>No Bookings!</h2> -->
	     <br>
	     <br>
	     <c:set var="fbdate" scope="page" value="${trDate}"/> 
	     <c:out value="No bookings for date "/><c:out value="${fbdate}"/>
	  </c:if>
	  </div>
	   <div align ="center" id="search">
	  
   	<c:if test="${fn:length(fbinfo) != 0}">
	  <table border="1" cellpadding="5">
	    <tr>
	    <th>Booking Id</th>
	    <th>Flight Id</th>
	    <th>From-->To</th>
	    <th>Travel Date</th>
	    <th>DepartTime-ArrivalTime</th>
	    <th>Passengers</th>
	    <th>Fare</th>
	    <th>First Name</th>
	    <th>Last Name</th>
	    <th>Email Id</th>
	    <th>Contact Number</th>
	    <th>Booking Date</th>
	    <th>Booking Status</th>
	    <th>Payment Id</th>
	    </tr>
	   <c:forEach var="blist" items="${pageScope.fbinfolist}">
	     <tr>
	     <td><c:out value="${blist.bookingId}"/></td>
	     <td><c:out value="${blist.flightCode}"/></td>
	     <td><c:out value="${blist.src}"/><c:out value="-->"/><c:out value="${blist.destination}"/></td>
	     <td><c:out value="${blist.travelDate}"/></td>
   		 <td><c:out value="${blist.deptTime}"/><c:out value="-"/><c:out value="${blist.arrTime}"/></td>
   		 <td><c:out value="${blist.noOfPassengers}"/></td>
   		 <td><c:out value="${blist.price}"/></td>
   		 <td><c:out value="${blist.fName}"/></td>
   		 <td><c:out value="${blist.lName}"/></td>
   		 <td><c:out value="${blist.emailId}"/></td>
   		 <td><c:out value="${blist.contactNo}"/></td>
   		 <td><c:out value="${blist.bookingDate}"/></td>
   		 <td><c:out value="${blist.bookingStatus}"/></td>
   		 <td><c:out value="${blist.pinfo}"/></td>
	     </tr>
               </c:forEach>
	  </table>
	  </c:if>
	 
	 
	  </div>
</body>
</html>