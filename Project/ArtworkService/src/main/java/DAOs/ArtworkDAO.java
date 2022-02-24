package DAOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import aggregates.ArtworkAggregate;
import events.Event;
import eventstore.SimpleEventStore;

/*
 * 	Classe che persiste Artwork attraverso un event store
 *  questo DAO funziona ad eventi.
 * 
 */
public class ArtworkDAO implements ArtworkDaoInterface {
	SimpleEventStore eventStore = SimpleEventStore.getInstance();

	public ArtworkAggregate save(ArtworkAggregate a) throws Exception {
		eventStore.save(a.getId(), a.getEvents());
		ArtworkAggregate created = new ArtworkAggregate(a.getId());
		created.rebuildAggregateStatus(eventStore.load(a.getId()));
		return created;
	}

	public ArtworkAggregate load(UUID id) throws Exception {
		List<Event> eventiArtwork = eventStore.load(id);
		ArtworkAggregate a = new ArtworkAggregate(id);
		a.rebuildAggregateStatus(eventiArtwork);
		return a;
	}

	public ArtworkAggregate delete(UUID id) throws Exception {
		ArtworkAggregate artworkToDelete = new ArtworkAggregate(id);
		artworkToDelete.rebuildAggregateStatus(eventStore.load(id));
		artworkToDelete.removeArtwork(); // Genero evento di cancellazione.
		return artworkToDelete;
	}

	public ArtworkAggregate update(ArtworkAggregate newArtwork, UUID id) throws Exception {
		eventStore.update(newArtwork, id);
		ArtworkAggregate updatedArtwork = new ArtworkAggregate(id);
		updatedArtwork.rebuildAggregateStatus(eventStore.load(id));
		return updatedArtwork;

	}

	public List<ArtworkAggregate> getEveryArtwork() throws Exception {
		ArrayList<ArtworkAggregate> aggregates = new ArrayList<>();
		Map<UUID, List<Event>> eventMap = eventStore.getAllEventsForEveryAggregate();
		for (Entry<UUID, List<Event>> entry : eventMap.entrySet()) {
			ArtworkAggregate a = new ArtworkAggregate(entry.getKey());
			a.rebuildAggregateStatus(eventStore.load(entry.getKey()));
			aggregates.add(a);
		}
		return aggregates;

	}

}
