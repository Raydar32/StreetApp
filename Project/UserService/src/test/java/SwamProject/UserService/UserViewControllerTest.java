package SwamProject.UserService;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import DAOs.UserDAO;
import DTOs.UserDTO;
import controllers.UserViewController;
import entities.User;
import mappers.UserMapper;


public class UserViewControllerTest {

	@InjectMocks
	private UserViewController viewService;

	@Mock
	private UserDAO userDAO;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindById() throws Exception {
		User a = new User();
		a.setUsername("Test");	
		UserMapper mapper = new UserMapper();
		UserDTO a_dto = mapper.UserToDto(a);
		when(userDAO.findById(Mockito.any(Integer.class))).thenReturn(a);
		UserDTO service_result = viewService.findById(123);
		Assert.assertEquals(a_dto,service_result);
		
	}

}