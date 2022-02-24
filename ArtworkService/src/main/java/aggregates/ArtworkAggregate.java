package aggregates;

import java.util.List;
import java.util.UUID;

import entities.ArtStyle;
import entities.ArtType;
import entities.Author;
import entities.User;
import events.ArtworkAuthorChangedEvent;
import events.ArtworkCreatedEvent;
import events.ArtworkNameChangedEvent;
import events.ArtworkRemovedEvent;
import events.ArtworkStyleChangedEvent;
import events.ArtworkTypeChangedEvent;

public class ArtworkAggregate extends AggregateRoot {
	private String name;
	private ArtStyle style;
	private ArtType type;
	private long latitude;
	private long longitude;
	private User reportingUser;
	private Author artworkCreator;
	
	//Eventi
	public void changeArtStyle(ArtStyle style) {
		this.style = style;
		ArtworkStyleChangedEvent evt = new ArtworkStyleChangedEvent(super.getId(), style);
		generateEvent(evt);
	}

	public void changeArtType(ArtType type) {
		this.type = type;
		generateEvent(new ArtworkTypeChangedEvent(super.getId(), type));
	}

	public void changeName(String newName) {
		this.name = newName;
		generateEvent(new ArtworkNameChangedEvent(super.getId(), newName));
	}
	
	public void changeAuthor(Author newAuthor) {
		this.artworkCreator = newAuthor;
		generateEvent(new ArtworkAuthorChangedEvent(getId(), newAuthor));
		
	}
	
	public void removeArtwork() {
		generateEvent(new ArtworkRemovedEvent(this.getId()));
	}
	
	//Costruttori
	//Un artwork per essere creato deve avere almeno questi campi
	public ArtworkAggregate(User reportingUser, long latitude, long longitude, String name) {
		super(UUID.randomUUID());
		this.reportingUser = reportingUser;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		generateEvent(new ArtworkCreatedEvent(super.getId(), reportingUser, latitude, longitude, name));
		
	}
	
	public ArtworkAggregate(UUID id) {
		super(id);
	}

	public ArtworkAggregate() {
		super(UUID.randomUUID());
		
	}
	

	
	// Metodi per la gestione degli eventi, ereditati e sovrascritti
	// dalla superclasse.
	@Override
	public void apply(ArtworkNameChangedEvent event) {
		this.name = event.newname;
	}

	@Override
	public void apply(ArtworkStyleChangedEvent event) {
		this.style = event.newstyle;
	}
	
	@Override
	public void apply(ArtworkTypeChangedEvent event) {
		this.type = event.newType;
	}
	
	@Override
	public void apply(ArtworkCreatedEvent event) {
		this.latitude = event.latitude;
		this.longitude = event.longitude;
		this.name = event.name;
		this.reportingUser = event.reportingUser;
	}
	
	@Override
	public void apply(ArtworkAuthorChangedEvent event) {
		this.artworkCreator = event.newAuthor;
	}
	
	@Override
	public void apply(ArtworkRemovedEvent event) {
		
	}
	
	// Getters and setters
	public String getName() {
		return name;
	}
	
	public List<events.Event> getEvents() {
		return super.getEvents();
	}

	public ArtStyle getStyle() {
		return style;
	}

	
	public ArtType getType() {
		return type;
	}

	public void setType(ArtType type) {
		this.type = type;
	}

	public long getLat() {
		return latitude;
	}

	public void setLat(long lat) {
		this.latitude = lat;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public User getReportingUser() {
		return reportingUser;
	}

	public Author getArtworkCreator() {
		return artworkCreator;
	}

	

}
