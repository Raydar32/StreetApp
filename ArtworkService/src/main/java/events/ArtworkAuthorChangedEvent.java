package events;

import java.util.UUID;

import entities.Author;

public class ArtworkAuthorChangedEvent extends Event {
	public Author newAuthor;

	public ArtworkAuthorChangedEvent(UUID aggregateId, Author newAuthor) {
		super();
		super.aggregateId = aggregateId;
		this.newAuthor = newAuthor;
	}

}
