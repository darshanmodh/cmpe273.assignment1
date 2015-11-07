$(document).ready(function() {
	
	getInventory();
	
	$(document.body).on('click', ':button, .DELETE_BTN', function(e) {
		//console.log(this);
		var $this = $(this)
			, endpointName = $this.val()
			, obj = {endpointName : endpointName}
			, $tr = $this.closest('tr');
		
		deleteInventory(obj, endpointName);
	});
});

function deleteInventory(obj, endpointName) {

	ajaxObj = {
		type : "DELETE",
		url : "http://localhost:8089/LWM2M/lwm2m/delete/" + endpointName + "/",
		data : JSON.stringify(obj),
		contentType : "application/json",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.responseText);
		},
		success : function(data) {
			// console.log(data);
			$('#delete_response').text(data[0].MSG);
		},
		complete : function(XMLHttpRequest) {
			// console.log( XMLHttpRequest.getAllResponseHeaders() );
			getInventory();
		},
		dataType : "json" // request JSON
	};

	return $.ajax(ajaxObj);
}

function getInventory() {

	var d = new Date(), n = d.getTime();

	ajaxObj = {
		type : "GET",
		url : "http://localhost:8089/LWM2M/lwm2m/devices",
		data : "ts=" + n,
		contentType : "application/json",
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.responseText);
		},
		success : function(data) {
			var html_string = "<table border='1'>"
					+ "<tr><th>Endpoint Client Name</th>"
					+ "<th>Manufacturer</th>" + "<th>Model Number</th>"
					+ "<th>Serial Number</th>" + "<th>Device Type</th>"
					+ "<th>Hardware Version</th>" + "<th>Software Version</th>"
					+ "<th>IP Address</th>" + "<th>Current Time</th>"
					+ "<th>Current Temperature</th>"
					+ "<th>Current Humidity</th>" 
					+ "<th>Delete</th>"
					+ "</th></tr>";

			$.each(data, function(index1, val1) {
				// console.log(val1);
				html_string = html_string + templateGetInventory(val1);
			});

			$('#get_device').html(
					"<table border='1'>" + html_string + "</table>");
		},
		complete : function(XMLHttpRequest) {
			// console.log( XMLHttpRequest.getAllResponseHeaders() );
		},
		dataType : "json" // request JSON
	};

	return $.ajax(ajaxObj);
}

function templateGetInventory(param) {
	return '<tr>' + '<td class="endpointName">' + param.endpointName + '</td>'
			+ '<td class="manufacturer">' + param.manufacturer + '</td>'
			+ '<td class="modelNumber">' + param.modelNumber + '</td>'
			+ '<td class="serialNumber">' + param.serialNumber + '</td>'
			+ '<td class="deviceType">' + param.deviceType + '</td>'
			+ '<td class="hardwareVersion">' + param.hardwareVersion + '</td>'
			+ '<td class="softwareVersion">' + param.softwareVersion + '</td>'
			+ '<td class="IPaddress">' + param.IPaddress + '</td>'
			+ '<td class="currentTime">' + param.currentTime + '</td>'
			+ '<td class="currentTemperature">' + param.currentTemperature
			+ '</td>' + '<td class="currentHumidity">' + param.currentHumidity
			+ '<td class="CL_PC_PARTS_BTN"> <button class="DELETE_BTN" value="'+param.endpointName +'" type="button">Delete</button>'
			+ '</td>' + '</tr>';
}