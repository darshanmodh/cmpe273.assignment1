$(document).ready(function() {
	
	var $put_example = $('#put_example')
		, $SET_manufacturer = $('#SET_manufacturer')
		, $SET_modelNumber = $('#SET_modelNumber');
	
	getInventory();
	
	$(document.body).on('click', ':button, .UPDATE_BTN', function(e) {
		var $this = $(this)
			, endpointName = $this.val()
			, $tr = $this.closest('tr')
			, manufacturer = $tr.find('.manufacturer').text()
			, modelNumber = $tr.find('.modelNumber').text()
			, serialNumber = $tr.find('.serialNumber').text()
			, deviceType = $tr.find('.deviceType').text()
			, hardwareVersion = $tr.find('.hardwareVersion').text()
			, softwareVersion = $tr.find('.softwareVersion').text()
			, IPaddress = $tr.find('.IPaddress').text()
			, currentTime = $tr.find('.currentTime').text()
			, currentTemperature = $tr.find('.currentTemperature').text()
			, currentHumidity = $tr.find('.currentHumidity').text();
		
		$('#SET_manufacturer').val(manufacturer);
		$('#SET_modelNumber').val(modelNumber);
		$('#SET_serialNumber').val(serialNumber);
		$('#SET_deviceType').val(deviceType);
		$('#SET_hardwareVersion').val(hardwareVersion);
		$('#SET_softwareVersion').val(softwareVersion);
		$('#SET_IPaddress').val(IPaddress);
		$('#SET_currentTime').val(currentTime);
		$('#SET_currentTemperature').val(currentTemperature);
		$('#SET_currentHumidity').val(currentHumidity);
		
		$('#update_response').text("");		
		
	});
	
	
	$put_example.submit(function(e) {
		e.preventDefault(); //cancel form submit
		
		var obj = $put_example.serializeObject()
			, manufacturer = $SET_manufacturer.text()
			, modelNumber = $SET_modelNumber.text();
		console.log(obj);
		console.log(endpointName);
		
		updateInventory(obj, endpointName);
	});
});

function updateInventory(obj, endpointName) {
	
	ajaxObj = {  
			type: "PUT",
			url: "http://localhost:8089/LWM2M/lwm2m/update/" + endpointName + "/",
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				//console.log(data);
				$('#update_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				getInventory();
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function getInventory() {
	
	var d = new Date(), n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:8089/LWM2M/lwm2m/devices", 
			data: "ts="+n, 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				//console.log(data);
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
					//console.log(val1);
					html_string = html_string + templateGetInventory(val1);
				});
				
				$('#get_device').html("<table border='1'>" + html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
			}, 
			dataType: "json" //request JSON
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
	+ '<td class="CL_PC_PARTS_BTN"> <button class="UPDATE_BTN" value=" ' + param.endpointName + ' " type="button">Update</button>'
	+ '</td>' + '</tr>';
}
