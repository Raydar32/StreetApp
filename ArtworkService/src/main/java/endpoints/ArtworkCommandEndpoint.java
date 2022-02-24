package endpoints;

import java.util.UUID;

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

import DTOs.ArtworkChangeAuthorCommandDTO;
import DTOs.ArtworkChangeNameCommandDTO;
import DTOs.ArtworkChangeStyleCommandDTO;
import DTOs.ArtworkChangeTypeCommandDTO;
import DTOs.ArtworkReportNewCommandDTO;
import aggregates.ArtworkAggregate;
import controllers.ArtworkCommandController;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import mappers.ArtworkMapper;

@Path("/ArtworkMicroservice")
public class ArtworkCommandEndpoint {

	@Inject
	ArtworkCommandController artworkCommandController;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(ArtworkReportNewCommandDTO request) throws BadRequestException {
		Response response;
		try {
			ArtworkAggregate created = artworkCommandController.createArtwork(request);
			response = Response.ok(new ArtworkMapper().ArtworkEntityToDTO(created)).build();
		} catch (Exception e) {
			throw new BadRequestException("Impossibile creare l'artwork con la richiesta effettuata");
		}
		return response;

	}

	/*
	 * L'update di un artwork viene gestito per richieste, qui gestiscono:
	 * 
	 */
	@Path("/updatestyle/{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeArtworkStyle(ArtworkChangeStyleCommandDTO request, @PathParam("id") String id)
			throws Exception {
		Response response;
		try {
			ArtworkAggregate updated = artworkCommandController.changeArtworkStyle(request, UUID.fromString(id));
			response = Response.ok(new ArtworkMapper().ArtworkEntityToDTO(updated)).build();
		} catch (BadRequestException e) {
			throw e;
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return response;

	}

	@Path("/updatetype/{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeArtworkStyle(ArtworkChangeTypeCommandDTO request, @PathParam("id") String id)
			throws Exception {
		Response response;
		try {
			ArtworkAggregate updated = artworkCommandController.changeArtworkType(request, UUID.fromString(id));
			response = Response.ok(new ArtworkMapper().ArtworkEntityToDTO(updated)).build();
		} catch (BadRequestException e) {
			throw e;
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return response;

	}

	@Path("/updatename/{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeArtworkStyle(ArtworkChangeNameCommandDTO request, @PathParam("id") String id)
			throws Exception {
		Response response;
		try {
			ArtworkAggregate updated = artworkCommandController.changeArtworkName(request, UUID.fromString(id));
			response = Response.ok(new ArtworkMapper().ArtworkEntityToDTO(updated)).build();
		} catch (BadRequestException e) {
			throw e;
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return response;

	}

	@Path("/updateauthor/{id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeArtworkStyle(ArtworkChangeAuthorCommandDTO request, @PathParam("id") String id)
			throws Exception {
		Response response;
		try {
			ArtworkAggregate updated = artworkCommandController.changeArtworkAuthor(request, UUID.fromString(id));
			response = Response.ok(new ArtworkMapper().ArtworkEntityToDTO(updated)).build();
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
	public Response deleteArtwork(@PathParam("id") String id) throws Exception {
		Response response;
		UUID artwork_uuid = UUID.fromString(id);
		ArtworkAggregate deleted = artworkCommandController.deleteArtwork(artwork_uuid);
		response = Response.ok(new ArtworkMapper().ArtworkEntityToDTO(deleted)).build();
		return response;

	}

}
