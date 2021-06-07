<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Airlines</title>
<style>

.bgimg {
	background-size: cover;
	background-image: url("images/bgicon.png");
	min-height: 100%;
}

.container {
        height: 300px;
        width : 500px;
        margin: 0 auto;
        margin-top: 100px;
        border: 2px solid;
        background-color: white;
        align-self: center;
        border-radius: 25px;
    }
    
    #back
 {
	 margin-top: 50px;
 	margin-right: 200px;
 }
</style>
</head>
<body class="bgimg">
	<div id="back" align="right"><a href="adminDashboard.jsp"><span style="color:blue;font-size:20px;">Go Back</span></a></div>
	<div align=center class = "container">
	<form action="addAirline" method=post>
			<h2>Add new Airline</h2>
			<table>
				<tr>
					<td>Airline Id</td>
					<td><input type="text" name="airlineId" id="airlineId" Maxlength=50 pattern="FA[0-9]{3}" title="AirlineId should start with string FA followed by 3 digits" required></td>
				</tr>
				<tr>
					<td>Airline name</td>
					<td><input type="text" name="airlinename" id="airlinename" Maxlength=50 required></td>
				</tr>
				<tr>
					<td>Airline Status</td>
					<td><select name="astatus" required>
						<option value="inservice">InService</option>
						<option value="outofservice">OutofService</option>
						</select>
					</td>
				</tr>
			</table>
			<input type="submit" id="addbutton" value="  Save  ">
		</form>
		</div>
</body>
</html>