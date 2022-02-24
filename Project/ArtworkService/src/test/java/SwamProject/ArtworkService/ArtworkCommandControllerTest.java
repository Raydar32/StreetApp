package SwamProject.ArtworkService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import DAOs.ArtworkDAO;
import DTOs.ArtworkChangeAuthorCommandDTO;
import DTOs.ArtworkChangeNameCommandDTO;
import DTOs.ArtworkChangeStyleCommandDTO;
import DTOs.ArtworkChangeTypeCommandDTO;
import DTOs.ArtworkReportNewCommandDTO;
import aggregates.ArtworkAggregate;
import clients.AuthorClient;
import clients.UserClient;
import controllers.ArtworkCommandController;
import entities.ArtStyle;
import entities.ArtType;
import entities.Author;
import entities.User;

public class ArtworkCommandControllerTest {
	@InjectMocks
	private ArtworkCommandController commandService;

	@Mock
	private ArtworkDAO dao;

	@Mock
	private AuthorClient authorClient;

	@Mock
	private UserClient userClient;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testArtworkDelete() throws Exception {
		//Si crea un artwork di test
		ArtworkAggregate artwork = getTestingArtwork();
		artwork.removeArtwork();
		when(dao.load(artwork.getId())).thenReturn(artwork);
		when(dao.update(any(ArtworkAggregate.class), any(UUID.class))).thenReturn(artwork);
		//Gestione della cancellazione
		ArtworkAggregate deleted = commandService.deleteArtwork(artwork.getId());
		assertEquals(artwork, deleted);
	}

	@Test
	public void testArtworkCreation() throws Exception {
		// Si crea un aggregato di prova.
		ArtworkAggregate artwork = getTestingArtwork();

		// Si genera una richiesta di creazione a partire dall'artwork
		ArtworkReportNewCommandDTO creationRequest = new ArtworkReportNewCommandDTO();
		creationRequest.setLatitude(artwork.getLat());
		creationRequest.setLongitude((artwork.getLongitude()));
		creationRequest.setName(artwork.getName());
		creationRequest.setReportingUserID(artwork.getReportingUser().getId());
		creationRequest.setStyle(artwork.getStyle().toString());
		creationRequest.setType(artwork.getType().toString());
		creationRequest.setArtworkCreatorID(artwork.getArtworkCreator().getId());

		// Mocking
		when(userClient.getUserFromRestApiById(any(Integer.class))).thenReturn(artwork.getReportingUser());
		when(authorClient.getAuthorFromRestApiById(any(Integer.class))).thenReturn(artwork.getArtworkCreator());
		when(dao.save(any(ArtworkAggregate.class))).thenReturn(artwork);

		// Si esegue la richiesta
		ArtworkAggregate created = commandService.createArtwork(creationRequest);
		assertEquals(artwork, created);

	}

	// Le richieste di update vengono testate in modo separato.
	@Test
	public void TestChangeAuthor() throws Exception {
		// Si crea un aggregato di prova.
		ArtworkAggregate artwork = getTestingArtwork();

		// Si crea una richiesta di update dell'autore
		Author newAuthor = new Author();
		artwork.changeAuthor(newAuthor);
		ArtworkChangeAuthorCommandDTO authorUpdateRequest = new ArtworkChangeAuthorCommandDTO();
		authorUpdateRequest.artworkCreator = 1;

		// Mocking
		when(dao.update(any(ArtworkAggregate.class), any(UUID.class))).thenReturn(artwork);
		when(dao.load(any(UUID.class))).thenReturn(artwork);

		// Si esegue la richiesta
		ArtworkAggregate updated = commandService.changeArtworkAuthor(authorUpdateRequest, artwork.getId());
		assertEquals(artwork.getArtworkCreator().getId(), updated.getArtworkCreator().getId());

	}

	@Test
	public void TestChangeName() throws Exception {
		// Si crea un aggregato di prova.
		ArtworkAggregate artwork = getTestingArtwork();

		// Si crea una richiesta di update del nome
		artwork.changeName("nuovo nome");
		ArtworkChangeNameCommandDTO artworkNameUpdateRequest = new ArtworkChangeNameCommandDTO();
		artworkNameUpdateRequest.name = "nuovo nome";

		// Mocking
		when(dao.update(any(ArtworkAggregate.class), any(UUID.class))).thenReturn(artwork);
		when(dao.load(any(UUID.class))).thenReturn(artwork);

		// Si esegue la richiesta
		ArtworkAggregate updated = commandService.changeArtworkName(artworkNameUpdateRequest, artwork.getId());
		assertEquals(artwork.getArtworkCreator().getId(), updated.getArtworkCreator().getId());

	}

	@Test
	public void TestChangeStyle() throws Exception {
		// Si crea un aggregato di prova.
		ArtworkAggregate artwork = getTestingArtwork();

		// Si crea una richiesta di update dello stile
		ArtworkChangeStyleCommandDTO artworkChangeStyleRequest = new ArtworkChangeStyleCommandDTO();
		artworkChangeStyleRequest.style = "HYPERREALISTIC";

		// Mocking
		when(dao.update(any(ArtworkAggregate.class), any(UUID.class))).thenReturn(artwork);
		when(dao.load(any(UUID.class))).thenReturn(artwork);

		// Si esegue la richiesta
		ArtworkAggregate updated = commandService.changeArtworkStyle(artworkChangeStyleRequest, artwork.getId());
		assertEquals(artwork.getArtworkCreator().getId(), updated.getArtworkCreator().getId());

	}

	@Test
	public void TestChangeType() throws Exception {
		// Si crea un aggregato di prova.
		ArtworkAggregate artwork = getTestingArtwork();

		// Si crea la richiesta
		ArtworkChangeTypeCommandDTO artworkChangeTypeRequest = new ArtworkChangeTypeCommandDTO();
		artworkChangeTypeRequest.type = "MURALES";

		// Mocking
		when(dao.update(any(ArtworkAggregate.class), any(UUID.class))).thenReturn(artwork);
		when(dao.load(any(UUID.class))).thenReturn(artwork);

		// Si esegue la richiesta
		ArtworkAggregate updated = commandService.changeArtworkType(artworkChangeTypeRequest, artwork.getId());
		assertEquals(artwork.getArtworkCreator().getId(), updated.getArtworkCreator().getId());

	}

	private ArtworkAggregate getTestingArtwork() {
		User reportingUser = new User();
		Author author = new Author();
		ArtworkAggregate artwork = new ArtworkAggregate(reportingUser, 10, 10, "ArtworkTest");
		artwork.changeArtStyle(ArtStyle.COMIC);
		artwork.changeArtType(ArtType.MURALES);
		artwork.changeAuthor(author);
		return artwork;
	}
}
