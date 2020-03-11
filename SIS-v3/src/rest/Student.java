package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("student") //this is the path of the resource

public class Student {

	@GET
	@Path("/read/")
	@Produces("text/plain")
	
	public String getStudent(@QueryParam("studentName") String name) throws Exception {
		//http://localhost:8080/SIS-v3/rest/student/read?studentName=Rod
		System.out.print("I'm here");
		return name;
		
	}

}
