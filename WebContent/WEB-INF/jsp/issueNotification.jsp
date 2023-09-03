<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="description" content="" />
<meta name="keywords" content="" />
<title>Integrated Drones Services</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/js/style.css" rel="stylesheet">
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
	<br/>
	<c:if test="${not empty status}">
   <center><font face=verdana color=red>${status}</center></font>
</c:if>
	<table align="center" border="1" >
	<tr><th><font size="" color="black">Ticket ID</th><th><font size="" color="black">Drone ID</th>
	<th><font size="" color="black">Drone No</th>
	<th><font size="" color="black">Drone Model</th>
	<th><font size="" color="black">Manager Phone No</th>
	<th><font size="" color="black">Manager Name</th>
	<th><font size="" color="black">Issue Summary</th>
	<th><font size="" color="black">Issue Date</th>
	<th><font size="" color="black">Issue Status</th>
	
	</tr>
    <c:forEach var="bfb" items="${list}"> 
    <tr>
    <td><font size="" color="black">${bfb.id}</td>
	<td><font size="" color="black">${bfb.droneid}</td>
    <td><font size="" color="black">${bfb.droneno}</td>
    <td><font size="" color="black">${bfb.dronemodel}</td>
	<td><font size="" color="black">${bfb.managerphone}</td>
	<td><font size="" color="black">${bfb.managername}</td>
	<td><font size="" color="black">${bfb.description}</td>
	<td><font size="" color="black">${bfb.issuedate}</td>
	<td><font size="" color="black">${bfb.status}</td>
	
	</tr>
    </c:forEach>
    </table>
	<br/><br/><br/><br/><br/><br/><br/>
</body>
</html>