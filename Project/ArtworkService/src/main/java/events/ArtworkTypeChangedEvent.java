package events;

import java.util.UUID;

import entities.ArtType;

public class ArtworkTypeChangedEvent extends Event{
	public ArtType newType;

	public ArtworkTypeChangedEvent(UUID uuid, ArtType newtype) {
		super();
		super.aggregateId = uuid;
		this.newType = newtype;
	}
}
