package events;

import java.util.UUID;

public class ArtworkRemovedEvent extends Event {

	public ArtworkRemovedEvent(UUID uuid) {
		super();
		super.aggregateId = uuid;

	}
}
