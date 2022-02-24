package endpoints;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import DTOs.UserDTO;
import controllers.UserViewController;
import exceptions.ResourceNotFoundException;

import javax.ws.rs.core.MediaType;

@Path("/UserMicroservice")
public class UserViewEndpoint {
	@Inject
	UserViewController userViewController;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllUsers() {
		List<UserDTO> found = userViewController.findAllUsers();
		return Response.ok(found).build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findUserById(@PathParam("id") int id) throws Exception {
		Response response;
		try {
			UserDTO found = userViewController.findById(id);
			response = Response.ok(found).build();
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return response;

	}

}
