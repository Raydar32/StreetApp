package endpoints;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DTOs.AuthorDTO;
import controllers.AuthorViewController;
import exceptions.ResourceNotFoundException;

@Path("/AuthorMicroservice")
public class AuthorViewEndpoint {
	@Inject
	AuthorViewController authorViewController;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllUsers() {
		List<AuthorDTO> usersDtos = authorViewController.findAllUsers();
		return Response.ok(usersDtos).build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findUserById(@PathParam("id") int id) throws Exception {
		Response response;
		AuthorDTO dto;
		try {
			dto = authorViewController.findById(id);
			response = Response.ok(dto).build();
		} catch (ResourceNotFoundException e) {
			throw e;

		}
		return response;

	}

}
