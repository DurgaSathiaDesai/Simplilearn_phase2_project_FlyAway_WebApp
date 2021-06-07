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
<title>Search Flights</title>
<style>

.bgimg {
	background-size: cover;
	background-image: url("images/bgicon.png");
	min-height: 100%;
}

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
			
			var errormsg ='${error}';
			document.getElementById("error").innerHTML = errormsg;
		}
		
		function validateForm()
		{
			var al = document.getElementById("Adult");
		    var adult = parseInt(al.options[al.selectedIndex].value);
		    var cl = document.getElementById("Child");
		    var child = parseInt(cl.options[cl.selectedIndex].value);
		    var noOfPassenger = adult+child;
			    if(noOfPassenger>5) {
			        document.getElementById("error").innerHTML = "*Sorry, you cannot book tickets for more than 5 passengers at a time!";
			        return false;
			      } 
			    
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
<body onload="getminDate()" class="bgimg">
	<% 
	  List<sourceLocations> slist = hibernateUtil.getSource(); 
	  pageContext.setAttribute("slist", slist);
	  
	  List<destnLocations> dlist = hibernateUtil.getDestination(); 
	  pageContext.setAttribute("dlist", dlist);
	  %>
	<div id="back" align="right"><a href="index.jsp"><span style="color:blue;font-size:20px;">Home</span></a></div>
	<div align=center id="search">
		<h2>Search Flights</h2>
		<form action="findFlights" method="post" onsubmit ="return validateForm()">
		<table>
			<tr>
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
			
			
				<td><label for="passengers">Passengers :    </label>
				
					<label for="Adultlbl">  Adult</label>
					<select name="Adult" id ="Adult">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
					<label for="Childlbl">Child</label>
					<select name="Child" id = "Child">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="3">4</option>
					</select>
				</td>
				</tr>	
		</table>
		<input type="image" src="images/searchicon1.png"alt="Search" width="100" height="100"/>
		<br>
		<br>
		 <%-- <c:set var="errormsg" scope="page" value="${error}"/>  --%>
		<%-- <c:if test="${not empty error}">  --%>
		<span id="error" style="color:red"></span>
		<%-- </c:if> --%>
		</form>
	</div>
</body>
</html>