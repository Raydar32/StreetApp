package endpoints;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DTOs.ArtworkDTO;
import DTOs.EventDTO;
import aggregates.ArtworkAggregate;
import controllers.ArtworkViewController;
import events.Event;
import exceptions.ResourceNotFoundException;
import mappers.ArtworkMapper;
import mappers.EventMapper;

@Path("/ArtworkMicroservice")
public class ArtworkViewEndpoint {
	@Inject
	ArtworkViewController artworkViewController;

	ArtworkMapper mapper = new ArtworkMapper();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllArtworks() throws Exception {
		// Per adesso la richiesta viene creata a mano.
		Response r;

		List<ArtworkDTO> dtos;
		try {
			dtos = artworkViewController.findAllArtworks().stream().map(mapper::ArtworkEntityToDTO)
					.collect(Collectors.toList());
			r = Response.ok(dtos).build();
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return r;

	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findArtworkByID(@PathParam("id") String id) throws Exception {
		Response response;
		UUID uuid = UUID.fromString(id);
		try {
			ArtworkDTO resultDTO = artworkViewController.getArtworkByUUID(uuid);
			response = Response.ok(resultDTO).build();
		} catch (ResourceNotFoundException e) {
			throw e;
		}
		return response;

	}

	@Path("/gethistory/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArtworkHistory(@PathParam("id") String id) throws Exception {
		Response response;
		UUID uuid = UUID.fromString(id);
		try {
			List<Event> eventi = artworkViewController.getArtworkEventsHistory(uuid);
			EventMapper eMapper = new EventMapper();
			List<EventDTO> eventi_dto = eventi.stream().map(eMapper::eventToDTO).collect(Collectors.toList());
			response = Response.ok(eventi_dto).build();
		} catch (ResourceNotFoundException e) {
			throw e;

		}
		return response;

	}
}
