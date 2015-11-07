package cmpe273.lab2.db;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Operations extends Connection {

	public JSONArray queryReturnsBrandParts(String endpoint) throws Exception {

		DBCollection table = null;
		JSONArray json = new JSONArray();

		try {
			table = getTable();
			BasicDBObject query = new BasicDBObject();
			query.put("_id", endpoint);
			DBCursor cursor = table.find(query);
			// DBCursor cursor = table.find();

			while (cursor.hasNext()) {

				JSONObject jsonObject = new JSONObject();
				BasicDBObject obj = (BasicDBObject) cursor.next();

				jsonObject.put("endpointClientName", obj.getString("_id"));
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

				// do others
				json.put(jsonObject);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return json;
	}

	public Response searchDevice(String CATEGORY) throws Exception {

		String returnString = null;
		Response rb = null;
		DBCollection table = null;
		JSONArray json = new JSONArray();
		try {
			table = getTable();
			BasicDBObject query = new BasicDBObject();
			query.put("_id", CATEGORY);
			DBCursor cursor = table.find(query);
			if (cursor.size() == 0) {
				returnString = "No Record Found";
				return Response.ok().entity("No record found").build();
			}
			while (cursor.hasNext()) {
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

	public int insertDevice(String endpoint_name, String manufacturer, String modelNumber, String serialNumber,
			String deviceType, String hardwareVersion, String softwareVersion, String IPaddress, String currentTime,
			String currentTemperature, String currentHumidity) throws Exception {

		DBCollection table = null;

		try {
			table = getTable();
			Map<String, Object> docMap = new HashMap<String, Object>();
			docMap.put("_id", endpoint_name);
			docMap.put("manufacturer", manufacturer);
			docMap.put("modelNumber", modelNumber);
			docMap.put("serialNumber", serialNumber);
			docMap.put("deviceType", deviceType);
			docMap.put("hardwareVersion", hardwareVersion);
			docMap.put("softwareVersion", softwareVersion);
			docMap.put("IPaddress", IPaddress);
			docMap.put("currentTime", currentTime);
			docMap.put("currentTemperature", currentTemperature);
			docMap.put("currentHumidity", currentHumidity);
			table.insert(new BasicDBObject(docMap));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		}
		return 200;
	}
	
	public int deleteDevice(String endpointName) throws Exception {
		// TODO Auto-generated method stub
		DBCollection table = null;
		try {
			table = getTable();
			BasicDBObject query = new BasicDBObject();
			query.put("_id", endpointName);
			table.remove(query);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		} 
		return 200;
	}

	public int updateDevice(String endpointName, String manufacturer, String modelNumber, String serialNumber,
			String deviceType, String hardwareVersion, String softwareVersion, String IPaddress, String currentTime,
			String currentTemperature, String currentHumidity) throws Exception {
		DBCollection table = null;
		try {
			table = getTable();
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("manufacturer", manufacturer);
			newDocument.put("modelNumber", modelNumber);
			newDocument.put("serialNumber", serialNumber);
			newDocument.put("deviceType", deviceType);
			newDocument.put("hardwareVersion", hardwareVersion);
			newDocument.put("softwareVersion", softwareVersion);
			newDocument.put("IPaddress", IPaddress);
			newDocument.put("currentTime", currentTime);
			newDocument.put("currentTemperature", currentTemperature);
			newDocument.put("currentHumidity", currentHumidity);
			
			BasicDBObject searchQuery = new BasicDBObject().append("_id", endpointName);
			
			table.update(searchQuery, newDocument);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 500;
		} 
		return 200;
	}
	
}
