<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
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
	<div id="back" align="right"><a href="index.jsp"><span style="color:blue;font-size:20px;">Home</span></a></div>
	<div align=center class = "container">
		<img border="0" src="images/loginicon.jpg"
    	 alt="LoginIcon" width="200" height="200">
		<form action="adminLogin" method=post>
			<table>
				<tr>
					<td>UserName </td>
					<td><input type="text" name="username" id="username" Maxlength=50 required></td>
				</tr>
				<tr>
					<td>Password </td>
					<td><input type="password" name="password" id="password" Maxlength=50 required></td>
				</tr>
			</table>
			<input type="submit" id="loginbutton" value="  Login  ">
		</form>
	</div>
</body>
</html>