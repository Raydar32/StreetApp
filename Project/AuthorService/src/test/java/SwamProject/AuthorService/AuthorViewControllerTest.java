package SwamProject.AuthorService;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAOs.AuthorDAO;
import DTOs.AuthorDTO;
import controllers.AuthorViewController;
import entities.Author;
import entities.GroupType;
import mappers.AuthorMapper;



public class AuthorViewControllerTest {
	@InjectMocks
	private AuthorViewController viewService;

	@Mock
	private AuthorDAO authorDAO;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindById() throws Exception {
		Author testAuthor = new Author();
		testAuthor.setAuthorName("Autore di test");
		testAuthor.setGroupName("Gruppo di test");
		testAuthor.setGroupType(GroupType.ARTISTIC);
		AuthorMapper mapper = new AuthorMapper();
		AuthorDTO a_dto = mapper.AuthorEntityToDTO(testAuthor);
		when(authorDAO.findByID(Mockito.any(Integer.class))).thenReturn(testAuthor);
		AuthorDTO service_result = viewService.findById(123);
		Assert.assertEquals(a_dto,service_result);
		
	}
}
