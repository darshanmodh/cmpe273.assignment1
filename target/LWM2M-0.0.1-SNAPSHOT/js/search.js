$(document).ready(function() {
	
	var $post_example = $('#form_search_device');
	
	$('#submit').click(function(e) {
		e.preventDefault();
		
		var jsObj = $post_example.serializeObject()
			, ajaxObj = {};
		
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:8089/LWM2M/lwm2m/bootstrap/", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(data) {
				var no_record = "<p>No Records found.</p>";
				$('#get_devices').html(no_record);
			},
			success: function(data) { 
				var html_string = "<table border='1'>" +
				"<tr><th>Endpoint Client Name</th>" +
				"<th>Manufacturer</th>" +
				"<th>Model Number</th>" +
				"<th>Serial Number</th>" +
				"<th>Device Type</th>" +
				"<th>Hardware Version</th>" +
				"<th>Software Version</th>" +
				"<th>IP Address</th>" +
				"<th>Current Time</th>" +				
				"<th>Current Temperature</th>" +
				"<th>Current Humidity</th>" +
				"</th></tr>";
		
		$.each(data, function(index1, val1) {
			html_string = html_string + templateGetInventory(val1);
		});
		
		$('#get_devices').html(html_string + "</table><br><br><p>Data Receved from Server's database.</p>");
	},
			complete: function(XMLHttpRequest) {
			}, 
			dataType: "json"
		};
		
		$.ajax(ajaxObj);
	});
});


function templateGetInventory(param) {
	return '<tr>' +
				'<td class="endpointName">' + param.endpointName + '</td>' +
				'<td class="manufacturer">' + param.manufacturer + '</td>' +
				'<td class="modelNumber">' + param.modelNumber + '</td>' +
				'<td class="serialNumber">' + param.serialNumber + '</td>' +
				'<td class="deviceType">' + param.deviceType + '</td>' +
				'<td class="hardwareVersion">' + param.hardwareVersion + '</td>' +
				'<td class="softwareVersion">' + param.softwareVersion + '</td>' +
				'<td class="IPaddress">' + param.IPaddress + '</td>' +
				'<td class="currentTime">' + param.currentTime + '</td>' +
				'<td class="currentTemperature">' + param.currentTemperature + '</td>' +
				'<td class="currentHumidity">' + param.currentHumidity + '</td>' +
			'</tr>';
}

