package SwamProject.AuthorService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAOs.AuthorDAO;
import DTOs.AuthorCreationCommandDTO;
import DTOs.AuthorUpdateGroupNameCommandDTO;
import DTOs.AuthorUpdateGroupTypeCommandDTO;
import DTOs.AuthorUpdateNameCommandDTO;
import controllers.AuthorCommandController;
import entities.Author;
import entities.GroupType;

public class AuthorCommandControllerTest {

	
	@InjectMocks
	private AuthorCommandController commandService;
	
	@Mock
	private AuthorDAO dao;
	

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testDelete() throws Exception {
		Author testAuthor = new Author();
		testAuthor.setAuthorName("Autore di test");
		testAuthor.setGroupName("Gruppo di test");
		testAuthor.setGroupType(GroupType.ARTISTIC);
		//Utente viene rimosso
		when(dao.delete(Mockito.any(Integer.class))).thenReturn(testAuthor);
		Author deleted = commandService.delete(testAuthor.getId());
		assertEquals(deleted, testAuthor);	
		
	}
	
	@Test
	public void testCreation() throws Exception {
		Author testAuthor = new Author();
		testAuthor.setAuthorName("Autore di test");
		testAuthor.setGroupName("Gruppo di test");
		testAuthor.setGroupType(GroupType.ARTISTIC);
		when(dao.create(Mockito.any(Author.class))).thenReturn(testAuthor);
		AuthorCreationCommandDTO creationRequest = new AuthorCreationCommandDTO();
		creationRequest.setAuthorName(testAuthor.getAuthorName());
		creationRequest.setGroupName(testAuthor.getGroupName());
		creationRequest.setGroupType(testAuthor.getGroupType().toString());
		Author created = commandService.create(creationRequest);
		assertEquals(created, testAuthor);
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		//Creazione di un autore
		Author testAuthor = new Author();
		testAuthor.setAuthorName("Autore di test");
		testAuthor.setGroupName("Gruppo di test");
		testAuthor.setGroupType(GroupType.ARTISTIC);
		//Update
		testAuthor.setAuthorName("nuovo nome");
		testAuthor.setGroupName("nuovo gruppo");
		testAuthor.setGroupType(GroupType.CREW);
		//Si creano le richieste come se fossero fatte da un utente
		AuthorUpdateNameCommandDTO nameChangeRequest = new AuthorUpdateNameCommandDTO();
		nameChangeRequest.authorName = "nuovo nome";
		
		AuthorUpdateGroupNameCommandDTO groupNameChangeRequest = new AuthorUpdateGroupNameCommandDTO();
		groupNameChangeRequest.groupName = "nuovo gruppo";
		
		AuthorUpdateGroupTypeCommandDTO groupTypeChangeRequest = new AuthorUpdateGroupTypeCommandDTO();
		groupTypeChangeRequest.groupType = "CREW";
		
		//Mocking
		when(dao.update(testAuthor.getId(),testAuthor)).thenReturn(testAuthor);
		when(dao.findByID(testAuthor.getId())).thenReturn(testAuthor);
		
		//Il servizio esegue le richieste
		commandService.updateGroupName(testAuthor.getId(), groupNameChangeRequest);
		commandService.updateGroupType(testAuthor.getId(), groupTypeChangeRequest);
		Author finalUpdate = commandService.updateName(testAuthor.getId(), nameChangeRequest);
		
		assertEquals(finalUpdate, testAuthor);
		
		
		
	}
	
	
}
