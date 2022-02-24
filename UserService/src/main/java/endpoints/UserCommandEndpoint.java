package endpoints;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DTOs.UserCreationCommandDTO;
import DTOs.UserUsernameUpdateCommandDTO;
import controllers.UserCommandController;
import entities.User;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import mappers.UserMapper;

@Path("/UserMicroservice")
public class UserCommandEndpoint {

	@Inject
	UserCommandController userCommandController;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUserFromJsonRequest(UserCreationCommandDTO request) throws Exception {
		Response response;
		try {
			User created = userCommandController.create(request);
			response = Response.ok(new UserMapper().UserToDto(created)).build();
		} catch (BadRequestException e) {
			throw e;
		}
		return response;

	}

	@Path("/updateusername/{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, UserUsernameUpdateCommandDTO request) throws Exception {
		Response response;
		try {
			User updated = userCommandController.update(id, request);
			response = Response.ok(new UserMapper().UserToDto(updated)).build();
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (BadRequestException e) {
			throw e;
		}

		return response;

	}

	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response deleteUser(@PathParam("id") int id) throws Exception {
		Response response;
		try {
			User deleted = userCommandController.delete(id);
			response = Response.ok(new UserMapper().UserToDto(deleted)).build();
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return response;

	}
}
