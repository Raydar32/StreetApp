package SwamProject.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAOs.UserDAO;
import DTOs.UserCreationCommandDTO;

import DTOs.UserUsernameUpdateCommandDTO;
import controllers.UserCommandController;
import controllers.UserViewController;
import entities.User;

public class UserCommandControllerTest {
	@InjectMocks
	private UserCommandController commandService;

	@InjectMocks
	UserViewController viewService = new UserViewController();

	@Mock
	private UserDAO userDAO;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testDelete() throws Exception {
		User u = new User();
		u.setUsername("Test_Username");
		// Utente viene rimosso
		when(userDAO.delete(Mockito.any(Integer.class))).thenReturn(u);
		User deleted = commandService.delete(u.getId());
		assertEquals(deleted, u);

	}

	@Test
	public void testUpdate() throws Exception {
		/*
		 * Data una richiesta di update si effettua l'update dell'utente.
		 */
		User u = new User();
		u.setUsername("user");
		// Update
		u.setUsername("user2");
		UserUsernameUpdateCommandDTO request = new UserUsernameUpdateCommandDTO();
		request.setUsername("user2");

		when(userDAO.update(u.getId(), u)).thenReturn(u);
		User updated = commandService.update(u.getId(), request);

		assertEquals(updated, u);

	}

	@Test
	public void testCreation() throws Exception {
		/*
		 * Data una richiesta di creazione con un certo username, quell'username viene
		 * creato
		 */
		User user = new User();
		user.setUsername("test_username");

		UserCreationCommandDTO request = new UserCreationCommandDTO();
		request.setUsername(user.getUsername());
		when(userDAO.create(Mockito.any(User.class))).thenReturn(user);
		User created = commandService.create(request);
		assertEquals(user, created);

	}

}
