<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Source</title>
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
	<form action="addSource" method=post>
			<h2>Add a Source</h2>
			<table>
				<tr>
					<td>Source Id</td>
					<td><input type="text" name="srcId" id="srcId" Maxlength=6 pattern="[A-Z]{3}[0-9]{3}" title="Source Id should start with 3 uppercase letters followed by 3 digits" required></td>
				</tr>
				<tr>
					<td>Source name</td>
					<td><input type="text" name="srcname" id="srcname" Maxlength=50 required></td>
				</tr>
			</table>
			<input type="submit" id="loginbutton" value="  Save  ">
		</form>
		</div>
</body>
</html>