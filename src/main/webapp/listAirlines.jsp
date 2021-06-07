<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.flyaway.dao.hibernateUtil" %>
<%@ page import="com.flyaway.model.airlines" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Airlines</title>
<style type="text/css">
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
</head>
<body>
	<% 
	  List<airlines> alist = hibernateUtil.getAirlines(); 
	  pageContext.setAttribute("alist", alist);
	  %>
	  <br>
	  <br>
	  <div style="padding: 10px" id = "link" align="center">
		<a href="addAirlines.jsp" target="_parent"><span style="color: white">Add Airline</span></a> 
		<a href="updateAirlineStatus.jsp" target="_parent"><span style="color: white">Update Airline Status</span></a>
	</div>
	  <div align="center">
	  <h2>List of Airlines</h2>
	  <table border="1" cellpadding="5">       
            <tr>
                <th>Airline Id</th>
                <th>Airline Name</th>
                <th>AirlineService Status</th>
            </tr>
            <c:forEach var="airlinelist" items="${pageScope.alist}">
                <tr>
                	<td>
                	<c:out value="${airlinelist.airlineId}"/>
                	</td>
                	<td>
                	<c:out value="${airlinelist.airlinename}"/>
                	</td>
                	<td>
                	<c:out value="${airlinelist.airlinestatus}"/>
                	</td>
                </tr>
            </c:forEach>
        </table>
        </div>
</body>
</html>