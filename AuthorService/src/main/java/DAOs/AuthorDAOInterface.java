package DAOs;

import java.util.List;
import entities.Author;

public interface AuthorDAOInterface {
	public Author create(Author author) throws Exception;

	public List<Author> findAll();

	public Author findByID(int id) throws Exception;

	public Author update(int id, Author new_author) throws Exception;

	public Author delete(int id) throws Exception;
}
