package events;

import java.util.UUID;

import entities.User;

public class ArtworkCreatedEvent extends Event {
	public User reportingUser;
	public long latitude;
	public long longitude;
	public String name;

	public ArtworkCreatedEvent(UUID uuid, User reportingUser, long latitude, long longitude, String name) {
		super();
		this.reportingUser = reportingUser;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
	}
}
