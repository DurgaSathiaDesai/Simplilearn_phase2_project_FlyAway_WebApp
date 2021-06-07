<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.flyaway.dao.hibernateUtil" %>
<%@ page import="com.flyaway.model.sourceLocations" %>
<%@ page import="com.flyaway.model.destnLocations" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List source and Destination</title>
<style>
.column {
  float: left;
  width: 33.33%;
  padding: 10px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
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
</style>
</head>
<body>
	<% 
	  List<sourceLocations> slist = hibernateUtil.getSource(); 
	  pageContext.setAttribute("slist", slist);
	  
	  List<destnLocations> dlist = hibernateUtil.getDestination(); 
	  pageContext.setAttribute("dlist", dlist);
	  %>
	  <br>
	  <br>
	  <div style="padding: 10px" id = "link" align="center">
		<a href="addSource.jsp" target="_parent"><span style="color: white">Add Source</span></a> 
		<a href="addDestination.jsp" target="_parent"><span style="color: white">Add Destination</span></a> 
	</div>
	  <div class="row" align="center">
	  <h2>List of Source and Destination</h2>
	  <div class="column">
	  <table border="1" cellpadding="5">       
            <tr>
                <th>SourceId</th>
                <th>SourceName</th>
            </tr>
            <c:forEach var="srclist" items="${pageScope.slist}">
                <tr>
                	<td>
                	<c:out value="${srclist.srcId}"/>
                	</td>
                	<td>
                	<c:out value="${srclist.srcname}"/>
                	</td>
                </tr>
            </c:forEach>
        </table>
        </div>
       <div class="column">
	  <table border="1" cellpadding="5">
            <tr>
                <th>DestinationId</th>
                <th>DestinationName</th>
            </tr>
            <c:forEach var="destnlist" items="${pageScope.dlist}">
                <tr>
                	<td>
                	<c:out value="${destnlist.destnId}"/>
                	</td>
                	<td>
                	<c:out value="${destnlist.destnname}"/>
                	</td>
                </tr>
            </c:forEach>
        </table>
        </div>
        </div>
</body>
</html>