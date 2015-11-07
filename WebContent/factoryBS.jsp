<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Factory Bootstrap</title>
</head>
<body>
	Device List
	<table border="1">
		<tr>
			<th>endPointName</th>
			<th>manufacturer</th>
			<th>modelNumber</th>
			<th>serialNumber</th>
			<th>deviceType</th>
			<th>hardwareVersion</th>
			<th>softwareVersion</th>
			<th>IPaddress</th>
			<th>currentTime</th>
			<th>currentTemperature</th>
			<th>currentHumidity</th>
		</tr>

		
			
				<c:forEach items="${list}" var="record">
					<tr>
						<td>${record.getEndpointName }</td>
						<td>${record.getManufacturer }</td>
						<td>${record.getModelNumber }</td>
						<td>${record.getSerialNumber }</td>
						<td>${record.getDeviceType }</td>
						<td>${record.getHardwareVersion }</td>
						<td>${record.getSoftwareVersion }</td>
						<td>${record.getIPaddress }</td>
						<td>${record.getCurrentTime }</td>
						<td>${record.getCurrentTemperature }</td>
						<td>${record.getCurrentHumidity }</td>
					</tr>
				</c:forEach>
			
		


	</table>


</body>
</html>