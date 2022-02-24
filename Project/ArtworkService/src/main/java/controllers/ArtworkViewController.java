package controllers;

import java.util.List;
import DAOs.ArtworkDAO;
import DTOs.ArtworkDTO;

import java.util.UUID;

import javax.inject.Inject;

import aggregates.ArtworkAggregate;
import events.Event;
import eventstore.SimpleEventStore;
import mappers.ArtworkMapper;

public class ArtworkViewController {
	@Inject
	ArtworkDAO dao;
	
	// Acquisisco un istanza di eventstore (Singleton)
	SimpleEventStore eventStore = SimpleEventStore.getInstance();

	public ArtworkDTO getArtworkByUUID(UUID id) throws Exception {
		ArtworkMapper mapper = new ArtworkMapper();
		ArtworkAggregate a = dao.load(id);
		return mapper.ArtworkEntityToDTO(a);

	}

	public List<ArtworkAggregate> findAllArtworks() throws Exception {
		return dao.getEveryArtwork();
	}

	public List<Event> getArtworkEventsHistory(UUID uuid) throws Exception {
		List<Event> eventi = dao.load(uuid).getEvents();
		return eventi;
	}

}
