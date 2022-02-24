package DAOs;

import java.util.List;

import entities.User;

public interface UserDAOInterface {
	public User create(User user) throws Exception;

	public List<User> findAll();

	public User update(int id, User new_user) throws Exception;

	public User delete(int id) throws Exception;

	public User findById(int id) throws Exception;
}
