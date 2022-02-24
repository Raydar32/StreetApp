package events;

import java.util.UUID;

public abstract class Event {
	public UUID aggregateId;
	public int eventNumber;
}
