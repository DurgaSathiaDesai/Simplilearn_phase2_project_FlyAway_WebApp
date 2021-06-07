<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
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
 
 #logoutloc
 {
	 margin-top: 20px;
 	margin-right: 100px;
 }

</style>
<script type="text/javascript">
     function newSrc() {
      var e = document.getElementById("operations");
      var newSrc = e.options[e.selectedIndex].value;
      //document.getElementById("MyFrame").src=newSrc;
      document.getElementById("MyFrame").src=newSrc;
      //alert(newSrc);
     }
    </script>
</head>
<body class="bgimg">
	<div align="right" id ="logoutloc">
<a href='logout'>Logout</a>
</div>
<div id="back" align="right"><a href="changePassword.jsp"><span style="color:blue;font-size:20px;">Change Password</span></a></div>
<div align=center>
	<form>
  		<label for="operation">Choose a operation:</label>
  		<select name="operations" id="operations" onChange="newSrc();">
      		<option value="listScheduledFlights.jsp">List Scheduled flights</option>
      		<option value="listSrcDestn.jsp">List Source and Destination</option>
	        <option value="listAirlines.jsp">List Airlines</option>
	        <option value="listBookings.jsp">List Bookings</option>    
  </select>
  
</form>
</div>
<div align="center">
<iframe  src = listScheduledFlights.jsp style="width:1350px;height:500px;overflow:scroll;border: none;" id="MyFrame"></iframe>
</div>
</body>
</html>