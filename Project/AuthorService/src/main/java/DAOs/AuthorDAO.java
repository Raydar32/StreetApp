package DAOs;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;

import entities.Author;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public class AuthorDAO implements AuthorDAOInterface {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("psUnit");
	EntityManager em = factory.createEntityManager();

	public Author create(Author author) throws Exception {
		if (!checkAuthorConsistency(author)) {
			throw new BadRequestException("Richiesta di creazione autore non valida");
		}
		// Verifico se l'username non è già presente.
		try {
			em.getTransaction().begin();
			em.persist(author);
			em.flush();
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new BadRequestException("Un utente con l'username inserito è già presente nel sistema");
			}
		}
		Author found = em.find(Author.class, author.getId());
		if (found == null) {
			throw new BadRequestException("Impossibile creare l'autore");
		}
		return found;
	}

	public List<Author> findAll() {
		TypedQuery<Author> results = em.createQuery("from Author", Author.class);
		List<Author> queryList = results.getResultList();
		return queryList;
	}

	public Author findByID(int id) throws Exception {
		em.getTransaction().begin();
		Author u = em.find(Author.class, id);
		em.getTransaction().commit();
		if (u == null) {
			throw new ResourceNotFoundException("Autore con id " + id + " non trovato");
		}
		return u;
	}

	public Author update(int id, Author new_author) throws Exception {

		Author oldAuthor = em.find(Author.class, id);
		if (oldAuthor == null) {
			throw new ResourceNotFoundException("Autore con id " + id + " non trovato, impossibile aggiornare");
		}
		oldAuthor.setAuthorName(new_author.getAuthorName());
		oldAuthor.setGroupName(new_author.getGroupName());
		oldAuthor.setGroupType(new_author.getGroupType());
		em.getTransaction().begin();
		em.merge(oldAuthor);
		em.getTransaction().commit();
		return oldAuthor;
	}

	public Author delete(int id) throws Exception {
		Author author_to_remove = em.find(Author.class, id);
		if (author_to_remove == null) {
			throw new ResourceNotFoundException("Autore con id " + id + " non trovato, impossibile cancellare");
		}
		em.getTransaction().begin();
		em.remove(author_to_remove);
		em.getTransaction().commit();
		return author_to_remove;

	}

	private boolean checkAuthorConsistency(Author author) {
		if (author.getAuthorName().equals("") || author.getGroupName().equals("")) {
			return false;
		}
		return true;
	}

}
