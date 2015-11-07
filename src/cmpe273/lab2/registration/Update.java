package cmpe273.lab2.registration;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import cmpe273.lab2.db.Operations;

@Path("/update")
public class Update {
	
	@PUT
	@Path("/{endpointName}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDevice(@PathParam("endpointName") String endpointName, String incomingData)
			throws Exception {

		String device = null;
		int http_code;
		String returnString;
		JSONArray json = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Operations operation = new Operations();

		try {

			JSONObject partsData = new JSONObject(incomingData);
			device = partsData.optString("endpointName");
			
			System.out.println("MOD:"+partsData.optString("modelNumber"));

			http_code = operation.updateDevice(device,
					partsData.optString("manufacturer"), partsData.optString("modelNumber"),
					partsData.optString("serialNumber"), partsData.optString("deviceType"),
					partsData.optString("hardwareVersion"), partsData.optString("softwareVersion"),
					partsData.optString("IPaddress"), partsData.optString("currentTime"),
					partsData.optString("currentTemperature"), partsData.optString("currentHumidity"));

			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Device has been updated successfully.");
			} else {
				return Response.status(500)
						.entity("Server was not able to process your request.")
						.build();
			}
			returnString = json.put(jsonObject).toString();
			//System.out.println("RETURNSTRING:"+returnString);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request.")
					.build();
		}
		return Response.ok(returnString).build();

	}

}
