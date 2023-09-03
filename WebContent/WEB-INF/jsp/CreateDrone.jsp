<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="description" content="" />
<meta name="keywords" content="" />
<title>Integrated Drones Services</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/js/style.css" rel="stylesheet"><style>  
.error{color:red}  
</style>  
</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="logo">
			<h3><font size="" color="white">Integrated Drones Services</font></h3>
		</div>
		<div id="slogan">
			
		</div>
	</div>
	<div id="menu">
		<ul>
			<li class="first current_page_item"><a href="CreateDrone">Create Drone</a></li>
			<li><a href="DeleteDrone">Edit/Delete Drones</a></li>
			<li><a href="RaiseIssue">Raise Issue</a></li>
			<li><a href="issueNotification">View Notifications</a></li>
			<li><a href="Logout">Logout</a></li>
		</ul>
				<br class="clearfix" />
				</div>
			
	<div id="splash">
		<img class="pic" src="${pageContext.request.contextPath}/js/images/investor.jpg" width="870" height="230" alt="" />
	</div>	
			<center>
<form:form method="post" action="StoreDroneDetails" modelAttribute="CreateDrone">
<br/>
   <h2><b>Add New Drone Details Screen</b></h2>

   <c:if test="${not empty status}">
   <center><font face=verdana color=red>${status}</center></font>
</c:if>
   
							
						<table align="center" width="40" >
			 <tr><td><font size="" color="black"><b>Drone&nbsp;No</b></td>
			 <td><form:input path="droneno" size="40"/></td>
			 <td><form:errors path="droneno" cssClass="error"/></td>
			 </tr>
         
		  <tr><td><font size="" color="black"><b>Drone&nbsp;Model</b></td>
		  <td><form:input path="dronemodel"  size="40"/></td>
		  <td><form:errors path="dronemodel" cssClass="error"/></td>
		  </tr>

		  <tr><td><font size="" color="black"><b>Purchased&nbsp;Date</b></td>
		  <td><form:input path="purchasedate"  type="date"/></td>
		  <td><form:errors path="purchasedate" cssClass="error"/></td>
		  </tr>

         
			<tr><td></td><td><input type="submit" value="Add Drone Details">
			</td>
			</table>
			</form:form>
				</div>	
					
				</div>
				<br/><br/><br/><br/><br/>
	</body>
</html>