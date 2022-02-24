package controllers;

import java.util.UUID;
import java.util.stream.Stream;

import javax.inject.Inject;

import DAOs.ArtworkDAO;
import DTOs.ArtworkChangeAuthorCommandDTO;
import DTOs.ArtworkChangeNameCommandDTO;
import DTOs.ArtworkChangeStyleCommandDTO;
import DTOs.ArtworkChangeTypeCommandDTO;
import DTOs.ArtworkReportNewCommandDTO;
import aggregates.ArtworkAggregate;
import clients.AuthorClient;
import clients.UserClient;
import entities.ArtStyle;
import entities.ArtType;
import entities.Author;
import entities.User;

import eventstore.SimpleEventStore;
import exceptions.BadRequestException;

public class ArtworkCommandController {

	@Inject
	ArtworkDAO dao;

	SimpleEventStore eventStore = SimpleEventStore.getInstance();
	UserClient userClient = new UserClient();
	AuthorClient authorClient = new AuthorClient();

	public ArtworkAggregate createArtwork(ArtworkReportNewCommandDTO request) throws Exception {
		if (!checkRequestConsistency(request)) {
			throw new BadRequestException("Richiesta non valida, l'artwork non è consistente");
		}
		/* Ricreo un artwork dalla richiesta, comunicando con gli altri servizi */
		User reportingUser = userClient.getUserFromRestApiById(request.getReportingUserID());
		Author artworkCreator = authorClient.getAuthorFromRestApiById(request.getArtworkCreatorID());
		long latitude = request.getLatitude();
		long longitude = request.getLongitude();
		ArtStyle style = ArtStyle.valueOf(request.getStyle());
		ArtType type = ArtType.valueOf(request.getType());
		String name = request.getName();

		ArtworkAggregate nuovoArtwork = new ArtworkAggregate(reportingUser, latitude, longitude, name);
		nuovoArtwork.changeArtStyle(style);
		nuovoArtwork.changeArtType(type);
		nuovoArtwork.changeAuthor(artworkCreator);

		/* Artwork viene persistito nell'event store */
		ArtworkAggregate created = dao.save(nuovoArtwork);
		return created;

	}

	public ArtworkAggregate changeArtworkStyle(ArtworkChangeStyleCommandDTO request, UUID fromString) throws Exception {
		if (request.style == null || request.style.equals("")) {
			throw new BadRequestException("Valore stile nullo");
		}
		if (!Stream.of(ArtStyle.values()).anyMatch(v -> v.name().equals(request.getStyle()))) {
			throw new BadRequestException(
					"Valore dello stile non presente, valori presenti: " + ArtStyle.values().toString());
		}
		ArtworkAggregate a = new ArtworkAggregate(fromString);
		a.rebuildAggregateStatus(dao.load(fromString).getEvents());
		a.changeArtStyle(ArtStyle.valueOf(request.getStyle()));
		ArtworkAggregate updated = dao.update(a, fromString);
		return updated;
	}

	public ArtworkAggregate changeArtworkType(ArtworkChangeTypeCommandDTO request, UUID fromString) throws Exception {
		if (request.type == null || request.type.equals("")) {
			throw new BadRequestException("Valore nullo");
		}

		if (!Stream.of(ArtType.values()).anyMatch(v -> v.name().equals(request.getType()))) {
			throw new BadRequestException(
					"Valore dello stile non presente, valori presenti: " + ArtType.values().toString());
		}

		ArtworkAggregate a = new ArtworkAggregate(fromString);
		a.rebuildAggregateStatus(dao.load(fromString).getEvents());

		a.changeArtType(ArtType.valueOf(request.getType()));
		ArtworkAggregate updated = dao.update(a, fromString);
		return updated;

	}

	public ArtworkAggregate changeArtworkName(ArtworkChangeNameCommandDTO request, UUID fromString) throws Exception {
		if (request.name == null || request.name.equals("")) {
			throw new BadRequestException("Nome ha Valore nullo");
		}
		ArtworkAggregate a = new ArtworkAggregate(fromString);
		a.rebuildAggregateStatus(dao.load(fromString).getEvents());
		a.changeName(request.name);
		ArtworkAggregate updated = dao.update(a, fromString);
		return updated;

	}

	public ArtworkAggregate changeArtworkAuthor(ArtworkChangeAuthorCommandDTO request, UUID fromString)
			throws Exception {
		if (request.artworkCreator == 0) {
			throw new BadRequestException(" ID autore ha Valore nullo");
		}
		Author new_author = authorClient.getAuthorFromRestApiById(request.artworkCreator);
		ArtworkAggregate a = new ArtworkAggregate(fromString);
		a.rebuildAggregateStatus(dao.load(fromString).getEvents());
		a.changeAuthor(new_author);
		ArtworkAggregate updated = dao.update(a, fromString);
		return updated;

	}

	public ArtworkAggregate deleteArtwork(UUID id) throws Exception {

		// ArtworkAggregate toDelete = dao.delete(id);
		ArtworkAggregate toDelete = new ArtworkAggregate(id);
		toDelete.rebuildAggregateStatus(dao.load(id).getEvents());
		toDelete.removeArtwork();
		toDelete = dao.update(toDelete, id);
		return toDelete;

	}

	// Una richiesta senza i campi fondamentali non è valida
	private boolean checkRequestConsistency(ArtworkReportNewCommandDTO request) {
		if (request.getLatitude() == 0 || request.getLongitude() == 0 || request.getName() == null) {
			return false;
		}
		return true;
	}

}
