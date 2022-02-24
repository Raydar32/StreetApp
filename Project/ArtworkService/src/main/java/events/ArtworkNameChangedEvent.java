package events;

import java.util.UUID;

public class ArtworkNameChangedEvent extends Event {
	public String newname;

	public ArtworkNameChangedEvent(UUID aggregateId, String newname) {
		super();
		super.aggregateId = aggregateId;
		this.newname = newname;
	}

}
