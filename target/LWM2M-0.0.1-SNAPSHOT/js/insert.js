$(document).ready(function() {
	
	var $post_example = $('#form_insert_device');
	
	$('#submit').click(function(e) {
		e.preventDefault();
		
		var jsObj = $post_example.serializeObject()
			, ajaxObj = {};
		
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:8089/LWM2M/lwm2m/registration/", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				if(data[0].HTTP_CODE == 200) {
					$('#div_ajaxResponse').text( data[0].MSG );
				}
			},
			complete: function(XMLHttpRequest) {
			}, 
			dataType: "json"
		};
		
		$.ajax(ajaxObj);
	});
});
