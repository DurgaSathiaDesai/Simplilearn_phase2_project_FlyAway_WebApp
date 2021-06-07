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
<title>Update Airline Status</title>
<style>

.bgimg {
	background-size: cover;
	background-image: url("images/bgicon.png");
	min-height: 100%;
}

#back
 {
	 margin-top: 50px;
 	margin-right: 200px;
 }
</style>
<script>
function validateForm() 
{
	document.getElementById("error").innerHTML = "";
	document.getElementById("msg").innerHTML = "";
	var ele =document.getElementsByName("airlineId");
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
		document.getElementById("error").innerHTML = "*Please select any Airlines to update status!";
		return false;
	}
}

function handleChange() 
{
	var ele =document.getElementsByName("airlineId");
	for(var i=0;i<ele.length;i++)
	{
		if(ele[i].checked == true)
		{
			document.getElementById(ele[i].value).disabled=false;
		}
		else
		{
			document.getElementById(ele[i].value).disabled=true;
		}
	}
	
}

function setmsg()
{
	var errormsg ='${errormsg}';
	document.getElementById("error").innerHTML = errormsg;
	var successmsg ='${successmsg}';
	document.getElementById("msg").innerHTML = successmsg;
}

</script>

</head>
<body class="bgimg" onload="setmsg()">
	<% 
	  List<airlines> alist = hibernateUtil.getAirlines(); 
	  pageContext.setAttribute("alist", alist);
	  %>
	<div id="back" align="right"><a href="adminDashboard.jsp"><span style="color:blue;font-size:20px;">Go Back</span></a></div>
	<div align=center>
	<form action="updateAirlineStatus" method="post" onsubmit ="return validateForm()">
		
		<table border="1" cellpadding="5">  
			<caption><h2>Update Airline Status</h2></caption>     
            <tr>
                <th>Airline Id</th>
                <th>Airline Name</th>
                <th>AirlineService Status</th>
                <th>Select status</th>
            </tr>
            <c:forEach var="airlinelist" items="${pageScope.alist}">
                <tr>
                	<td>
                	<input type="radio" id="${airlinelist.airlinename}" name="airlineId" value="${airlinelist.airlineId}" onclick="handleChange()">
                	<c:out value="${airlinelist.airlineId}"/>
                	</td>
                	<td>
                	<c:out value="${airlinelist.airlinename}"/>
                	</td>
                	<td><c:out value="CurrentStatus : "/><c:out value="${airlinelist.airlinestatus}"/></td>
                	<td><select name="astatus" id="${airlinelist.airlineId}" disabled required>
						<option value="inservice">InService</option>
						<option value="outofservice">OutofService</option>
						</select>
					</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <br>
        <input type="submit" id="updatebutton" value="  Update Status  ">
        <br>
		<br>
		<span id="error" style="color:red"></span>
		<br>
		<span id="msg" style="color:black"></span>
     </form>
	</div>
</body>
</html>