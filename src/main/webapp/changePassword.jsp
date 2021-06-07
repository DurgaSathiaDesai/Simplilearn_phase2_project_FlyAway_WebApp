<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
<style>

.bgimg {
	background-size: cover;
	background-image: url("images/bgicon.png");
	min-height: 100%;
}

table {
        border-spacing: 0 20px;
      }

#cpwd {
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
<script>
function validateForm()
{
	    var pwd1 = document.getElementById("newpassword").value;
	    var pwd2 = document.getElementById("cpassword").value;
	    if(pwd1 != pwd2) {
	        document.getElementById("error").innerHTML = "*Password mismatch in New Password and Confirm Password fields";
	        document.getElementById("newpassword").value = "";
	        document.getElementById("cpassword").value = "";
	        return false;
	      } 
}

 function setmsg()
{
	var errormsg ='${error}';
	document.getElementById("error").innerHTML = errormsg;
} 
/* function gotoPage() {
	window.location.href = "/adminDashboard.jsp";
	} */
</script>
</head>
<body class="bgimg" onload="setmsg()">
	<div id="back" align="right"><a href="adminDashboard.jsp"><span style="color:blue;font-size:20px;">Go Back</span></a></div>
	<div align=center id ="cpwd">
		<form action="changePwd" method=post onsubmit ="return validateForm()">
			<h3>Change Password</h3>
			<table>
				<tr>
					<td>UserName :</td>
					<td><input type="text" name="username" id="username" Maxlength=50 required></td>
				</tr>
				<tr>
					<td>Old Password :</td>
					<td><input type="password" name="oldpassword" id="oldpassword" Maxlength=50 required></td>
				</tr>
				<tr>
					<td>New Password :</td>
					<td><input type="password" name="newpassword" id="newpassword" Maxlength=50 pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required></td>
				</tr>
				<tr>
					<td>Confirm Password :</td>
					<td><input type="password" name="cpassword" id="cpassword" Maxlength=50 required></td>
				</tr>
				<!-- <tr>
				<td><input type="submit" id="loginbutton" value="  save "></td>
				<td> <input type="button" id="cancel" value="  cancel " onClick() = "gotoPage()"></td>
				</tr> -->
			</table>
			<input type="submit" id="loginbutton" value="  save ">
			<br>
			<span id="error" style="color:red"></span>
			<!-- <span id="msg" style="color:black"></span> -->
		</form>
	</div>
</body>
</html>