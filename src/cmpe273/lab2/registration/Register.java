package cmpe273.lab2.registration;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import cmpe273.lab2.db.Operations;

@Path("/registration")
public class Register {

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDevice(String incomingData) throws Exception {

		String returnString = null;
		JSONArray json = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Operations operation = new Operations();

		try {
			JSONObject partsData = new JSONObject(incomingData);
			System.out.println("jsonData: " + partsData.toString());

			int http_code = operation.insertDevice(partsData.optString("endpoint_name"),
					partsData.optString("manufacturer"), partsData.optString("modelNumber"),
					partsData.optString("serialNumber"), partsData.optString("deviceType"),
					partsData.optString("hardwareVersion"), partsData.optString("softwareVersion"),
					partsData.optString("IPaddress"), partsData.optString("currentTime"),
					partsData.optString("currentTemperature"), partsData.optString("currentHumidity"));

			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Device has been inserted successfully.");
				returnString = json.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to enter Item").build();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your Request.").build();
		}
		return Response.ok(returnString).build();
	}

}
