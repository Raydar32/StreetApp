package SwamProject.ArtworkService;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAOs.ArtworkDAO;
import DTOs.ArtworkDTO;
import DTOs.AuthorDTO;
import aggregates.ArtworkAggregate;
import controllers.ArtworkViewController;
import entities.ArtStyle;
import entities.ArtType;
import entities.Author;
import entities.User;
import entities.GroupType;
import mappers.ArtworkMapper;
import mappers.AuthorMapper;

import java.util.UUID;

public class ArtworkViewControllerTest {
	@InjectMocks
	private ArtworkViewController viewService;

	@Mock
	private ArtworkDAO artworkDAO;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindById() throws Exception {	
	
		ArtworkAggregate artwork = getTestingArtwork();
		
		
		UUID target_uuid = UUID.randomUUID();
		ArtworkMapper mapper = new ArtworkMapper();
		ArtworkDTO a_dto = mapper.ArtworkEntityToDTO(artwork);
		when(artworkDAO.load(Mockito.any(UUID.class))).thenReturn(artwork);
		ArtworkDTO service_result = viewService.getArtworkByUUID(target_uuid);
		Assert.assertEquals(a_dto,service_result);
		
		
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
