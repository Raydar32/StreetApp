package endpoints;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DAOs.AuthorDAO;
import DTOs.AuthorCreationCommandDTO;
import DTOs.AuthorUpdateGroupNameCommandDTO;
import DTOs.AuthorUpdateGroupTypeCommandDTO;
import DTOs.AuthorUpdateNameCommandDTO;
import controllers.AuthorCommandController;
import entities.Author;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import mappers.AuthorMapper;

@Path("/AuthorMicroservice")
public class AuthorCommandEndpoint {

	@Inject
	AuthorCommandController authorCommandController;
	AuthorDAO authorDAO = new AuthorDAO();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUserFromJsonRequest(AuthorCreationCommandDTO request) throws Exception {
		Response response;
		try {
			Author created = authorCommandController.create(request);
			response = Response.ok(new AuthorMapper().AuthorEntityToDTO(created)).build();
		} catch (BadRequestException e) {
			throw e;
		}
		return response;

	}

	@Path("/updatename/{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, AuthorUpdateNameCommandDTO request) throws Exception {
		Response response;
		try {
			Author updated = authorCommandController.updateName(id, request);
			response = Response.ok(new AuthorMapper().AuthorEntityToDTO(updated)).build();
		} catch (BadRequestException e) {
			throw e;
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return response;

	}

	@Path("/updategroupname/{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, AuthorUpdateGroupNameCommandDTO request) throws Exception {
		Response response;
		try {
			Author updated = authorCommandController.updateGroupName(id, request);
			response = Response.ok(new AuthorMapper().AuthorEntityToDTO(updated)).build();
		} catch (BadRequestException e) {
			throw e;
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return response;

	}

	@Path("/updategrouptype/{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, AuthorUpdateGroupTypeCommandDTO request) throws Exception {
		Response response;
		try {
			Author updated = authorCommandController.updateGroupType(id, request);
			response = Response.ok(new AuthorMapper().AuthorEntityToDTO(updated)).build();
		} catch (BadRequestException e) {
			throw e;
		} catch (ResourceNotFoundException e) {
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
			Author deleted = authorCommandController.delete(id);
			response = Response.ok(new AuthorMapper().AuthorEntityToDTO(deleted)).build();
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return response;
	}
}
