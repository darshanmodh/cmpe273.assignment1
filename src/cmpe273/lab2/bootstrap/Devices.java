package cmpe273.lab2.bootstrap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import cmpe273.lab2.db.Connection;

@Path("/devices")
public class Devices {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBooks() throws Exception {
		
		String returnString = null;
		Response rb = null;
		DBCollection table = null;
		
		try {
			JSONArray json = new JSONArray();
			
			table = Connection.getTable();
			DBCursor cursor = table.find();
			while(cursor.hasNext()) {
				JSONObject jsonObject = new JSONObject();
				BasicDBObject obj = (BasicDBObject) cursor.next();
				
				jsonObject.put("endpointName", obj.getString("_id"));
				jsonObject.put("manufacturer", obj.getString("manufacturer"));
				jsonObject.put("modelNumber", obj.getString("modelNumber"));
				jsonObject.put("serialNumber", obj.getString("serialNumber"));
				jsonObject.put("deviceType", obj.getString("deviceType"));
				jsonObject.put("hardwareVersion", obj.getString("hardwareVersion"));
				jsonObject.put("softwareVersion", obj.getString("softwareVersion"));
				jsonObject.put("IPaddress", obj.getString("IPaddress"));
				jsonObject.put("currentTime", obj.getString("currentTime"));
				jsonObject.put("currentTemperature", obj.getString("currentTemperature"));
				jsonObject.put("currentHumidity", obj.getString("currentHumidity"));
				json.put(jsonObject);
		    }
			
			
			returnString = json.toString();
			
			rb = Response.ok(returnString).build();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rb;
	}

}
