package cmpe273.lab2.clientBS;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import cmpe273.lab2.db.Operations;

@Path("/bootstrap")
public class BootstrapGET {
	
	
	@GET
	@Path("/{endpoint}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(
			@PathParam("endpoint") String endpoint) throws Exception {
		
		Operations operation = new Operations();
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			json = operation.queryReturnsBrandParts(endpoint);
			returnString = json.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertBook(String incomingData) throws Exception {

		Operations operation = new Operations();
		Response rb = null;

		try {
			JSONObject partsData = new JSONObject(incomingData);
			System.out.println("Search Category: " + partsData.optString("CATEGORY"));

			rb = operation.searchDevice(partsData.optString("CATEGORY"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your Request.")
					.build();
		}
		return rb;
	}

}
