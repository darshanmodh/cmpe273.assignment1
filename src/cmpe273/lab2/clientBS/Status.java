package cmpe273.lab2.clientBS;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/status")
public class Status {
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>LWM2M Web-Service is running</p>";
	}

}
