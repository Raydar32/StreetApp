package controllers;

import javax.inject.Inject;

import DAOs.UserDAO;
import DTOs.UserCreationCommandDTO;
import DTOs.UserUsernameUpdateCommandDTO;
import entities.User;
import exceptions.BadRequestException;

public class UserCommandController {
	@Inject
	UserDAO userDAO;

	public User create(UserCreationCommandDTO request) throws Exception {
		User u = new User();
		u.setUsername(request.getUsername());
		User created = userDAO.create(u);
		return created;
	}

	public User update(int id, UserUsernameUpdateCommandDTO request) throws Exception {
		if (request.getUsername() == null || request.getUsername().equals("")) {
			throw new BadRequestException("Valore nullo");
		}
		User u = new User();
		u.setUsername(request.getUsername());
		User updated = userDAO.update(id, u);
		return updated;

	}

	public User delete(int id) throws Exception {

		return userDAO.delete(id);

	}
}