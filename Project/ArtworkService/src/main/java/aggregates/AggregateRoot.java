package aggregates;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import events.ArtworkAuthorChangedEvent;
import events.ArtworkCreatedEvent;
import events.ArtworkNameChangedEvent;
import events.ArtworkRemovedEvent;
import events.ArtworkStyleChangedEvent;
import events.ArtworkTypeChangedEvent;
import events.Event;
import events.EventComparator;

/*
 * 	Classe che implementa la root di un generico aggregato
 * 
 * 
 */

public abstract class AggregateRoot {
	private UUID id;
	private List<Event> events = new ArrayList<Event>();
	private int version;

	// Costruttore
	public AggregateRoot(UUID uuid) {
		this.id = uuid;
	}

	// Metodo per creare un evento
	public void generateEvent(Event e) {
		applyChange(e, true);
	}

	// Metodo per aggiungere un evento alla lista.
	private void applyChange(Event event, boolean isNew) {
		//invokeApplyIfEntitySupports(event);
		invokeApplyMethodNoReflection(event);
		if (isNew) {
			version++;
			event.eventNumber = version;
			events.add(event);
		}
	}

	// Metodo per ricostruire un aggregato
	public final void rebuildAggregateStatus(List<Event> history) {
		history.sort(new EventComparator()); // Ordino gli eventi per versione
		for (Event e : history) { // Li applico
			applyChange(e, false);
		}
		this.events = history;
		this.version = history.get(history.size() - 1).eventNumber;
	}


	
	
	
	/*
	 * 		Metodi di gestione degli eventi nella sottoclasse
	 * 		Un artworkAggregate, arrivato a questo punto, si "auto-applica"
	 *      un evento del tipo opportuno.
	 */
	
	
	// 	Si definiscono tanti eventi astratti quanti sono i possibili
	//  metodi apply per i possibili eventi.
	public abstract void apply(ArtworkNameChangedEvent event);
	public abstract void apply(ArtworkStyleChangedEvent event);
	public abstract void apply(ArtworkTypeChangedEvent event);
	public abstract void apply(ArtworkCreatedEvent event);
	public abstract void apply(ArtworkAuthorChangedEvent event);
	public abstract void apply(ArtworkRemovedEvent event);
	

	// Il seguente metodo applica l'evento 
	private void invokeApplyMethodNoReflection(Event e) {
		if (e instanceof ArtworkNameChangedEvent) {
			this.apply((ArtworkNameChangedEvent) e);
		}
		if (e instanceof ArtworkCreatedEvent) {
			this.apply((ArtworkCreatedEvent) e);
		}
		if (e instanceof ArtworkStyleChangedEvent) {
			this.apply((ArtworkStyleChangedEvent) e);
		}
		if (e instanceof ArtworkTypeChangedEvent) {
			this.apply((ArtworkTypeChangedEvent) e);
		}
		if (e instanceof ArtworkAuthorChangedEvent) {
			this.apply((ArtworkAuthorChangedEvent) e);
		}
		if (e instanceof ArtworkRemovedEvent) {
			this.apply((ArtworkRemovedEvent) e);
		}

	}


	// -----------------------------------------------------
	//			Metodi getters e setters 
	// -----------------------------------------------------	
	
	public List<Event> getEvents() {
		return events;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int v) {
		this.version = v;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AggregateRoot other = (AggregateRoot) obj;
		return Objects.equals(id, other.id) && version == other.version;
	}

}
