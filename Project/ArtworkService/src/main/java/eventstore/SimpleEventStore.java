package eventstore;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import aggregates.ArtworkAggregate;
import events.Event;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public class SimpleEventStore {

	private static SimpleEventStore instance = new SimpleEventStore();
	private Map<UUID, List<Event>> events = new ConcurrentHashMap<>();

	public static SimpleEventStore getInstance() {
		return instance;
	}

	private SimpleEventStore() {
	}

	// Si salvano tutti gli eventi di un aggregato
	// Se un aggregato è allo stato precedente, si sovrascrivono.
	public void save(UUID uuid, List<Event> newEvents) throws Exception {
		if (newEvents == null) {
			throw new BadRequestException("Richiesta non valida");
		}
		events.put(uuid, newEvents);
	}

	// Si caricano gli eventi di un aggregato
	public List<Event> load(UUID aggregateId) throws ResourceNotFoundException {
		List<Event> aggregateEvents = events.get(aggregateId);
		if (aggregateEvents == null) {
			throw new ResourceNotFoundException("ID non valido, non corrisponde a nessun artwork");
		}
		return new ArrayList<>(aggregateEvents);
	}

	public Map<UUID, List<Event>> getAllEventsForEveryAggregate() {
		return events;
	}

	public void delete(UUID aggregateID) {
		events.remove(aggregateID);
	}

	public void update(ArtworkAggregate newArtwork, UUID id) {
		events.replace(id, newArtwork.getEvents());

	}

}
