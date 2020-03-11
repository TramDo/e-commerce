package restService;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

//this class is a simple implementation of a REST service
//it is a Plant catalog, with initial plants

@Path("pfs")  //this is the path of the service
public class PlantsForSale {
     HashMap<String, String> catalog;
     
	public PlantsForSale() {
		catalog=new HashMap<String, String>();
		catalog.put("rose", "$7.0");
		catalog.put("tulip", "$5.0");
		
	}
	
	/* GET fps/plant?plantname=x     */
	//this is a READ method on the service
	//the resource name is plant, the operation is get, the parameters are passed as 
	//query parameters in the url
	//once you deploy this, you can access this method with
	//http://localhost:8080/RESTSample/rest/pfs/plant?plantName=rose
	//note the annotations
	
	
	@GET
    @Path("/plant/")
	@Produces("text/plain")
	public String getPrice(@DefaultValue("rose") @QueryParam("plantName") String name) {	
		System.out.println("received:" +name);
		return (String) catalog.get(name);
	}
	
	/* GET fps/plant/add?plantname=x&price=y */
	//this is a CREATE method on the service
	//the resource name is plant, the operation is get, the parameters are passed as 
	//query parameters in the url
	//once you deploy this, you can access this method with
	//http://localhost:8080/RESTSample/rest/pfs/plant/add?plantName=y&price=$56
	//note the annotations
	
	@GET
    @Path("/plant/add/")
	@Consumes("text/plain")
	public String addPlant(@QueryParam("plantName")String name, @QueryParam("price")String price)
	{
		System.out.println("received:" +name +" "+ price);
		catalog.put(name, price);
		return getCatalogAsString();
	}
	
	/* POST fps/plant/create       */
	//this is a CREATE method on the service
	//the resource name is plant, the operation is POST, the parameters are passed as 
	//parameters in a form
	//once you deploy this, you can access this method with
	//http://localhost:8080/RESTSample/rest/pfs/plant/create
	//you can invoke it at the above address but need to include the parameters
	//in the body..check the client samples of how to do it from Java
	

	@POST
    @Path("/plant/create/")
	@Consumes("text/plain")
	public String createPlant(@FormParam("plantName")String name, @FormParam("price")String price)
	{
		System.out.println("received:" +name +" "+ price);
		catalog.put(name, price);
		return getCatalogAsString();
	}

	
	
	public String getCatalogAsString() {
		String result="Catalog</br>";
		
        Set ms = (Set) catalog.entrySet();
        //Create iterator on Set 
        Iterator mapIterator = ms.iterator();
        while (mapIterator.hasNext()) {
        	    Map.Entry me = (Map.Entry) mapIterator.next();
            // getKey 
            String key = (String) me.getKey();
            //getValue method returns corresponding key's value
            String value = (String) me.getValue();
            result+= key + "  " + value+"</br>";
        }
        return result;
	}
	
	/* to be completed*/
	public void deletePlant(String name) {
    	catalog.remove(name);
		
	}
	
	/* to be completed*/
    public void updatePlant(String name, String price) {
    	catalog.replace (name, price);
}




}
