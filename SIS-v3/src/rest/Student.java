package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.SIS;

@Path("student") //this is the path of the resource

public class Student {

	@GET
	@Path("/read/")
	@Produces("text/plain")
	
	public String getStudent(@QueryParam("studentName") String surname) throws Exception {
		//http://localhost:8080/SIS-v3/rest/student/read?studentName=Rod
		//curl -X GET http://localhost:8080/SIS-v3/rest/student/read?studentName=Rod
		String result = SIS.getInstance().getAsXML(surname);
		return result;
		
	}
	
	@POST
	@Path("/create")
	@Consumes("text/plain")
	@Produces("text/plain")
	//curl -X POST "http://localhost:8080/SIS-v3/rest/student/create?sid=003&givenName=John&surName=Wayne&creditTaken=3&creditGraduate=1"
	public String createStudent(@QueryParam("sid")String sid, @QueryParam("givenName") String givenName, @QueryParam("surName")String surname, @QueryParam("creditTaken")int credit_taken, @QueryParam("creditGraduate")int credit_graduate ) {
		System.out.print("This is create");
		String rs="";
		int result = 0;
		try {
			result = SIS.getInstance().create(sid, givenName, surname, credit_taken, credit_graduate);
			rs="insertedRows: " + result;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("This is create");
		return rs;
		
	}
	
	@DELETE
	@Path("/delete/")
	@Consumes("text/plain")
	@Produces("text/plain")
	//curl -X DELETE http://localhost:8080/SIS-v3/rest/student/delete?sid=003

	public String delete(@QueryParam("sid")String sid) {
	
		int result = 0;
		String rs="";
		try {
			result = SIS.getInstance().delete(sid);
			rs="deletedRows: " + result;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
		
	}

}
