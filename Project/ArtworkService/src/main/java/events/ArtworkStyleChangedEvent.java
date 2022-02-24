package events;

import java.util.UUID;

import entities.ArtStyle;

public class ArtworkStyleChangedEvent extends Event {
	public ArtStyle newstyle;

	public ArtworkStyleChangedEvent(UUID uuid, ArtStyle newname) {
		super();
		super.aggregateId = uuid;
		this.newstyle = newname;
	}
}
