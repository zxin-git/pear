package com.zxin.apache.http.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path(value = "/customer")
//@Produces("*/*")
// @Produces("application/xml")
 @Produces("application/json")
public interface CustomerService {
	@GET
	@Path(value = "/{id}/info")
	Customer findCustomerById(@PathParam("id") String id);

	@GET
	@Path(value = "/search")
	Customer findCustomerByName(@QueryParam("name") String name);
}