package DAOs;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;

import entities.User;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;

public class UserDAO implements UserDAOInterface {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("psUnit");
	EntityManager em = factory.createEntityManager();

	public User create(User user) throws Exception {
		if (user.getUsername().equals("")) {
			throw new BadRequestException("Utente con nome non valido");
		}
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.flush();
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new BadRequestException("Un utente con l'username inserito è già presente nel sistema");
			}

		}
		User found = em.find(User.class, user.getId());
		if (found == null) {
			throw new BadRequestException("Impossibile creare l'utente, errore sconosciuto");
		}
		return user;

	}

	public List<User> findAll() {
		TypedQuery<User> results = em.createQuery("from User", User.class);
		List<User> queryList = results.getResultList();
		return queryList;

	}

	public User update(int id, User new_user) throws Exception {
		User old_user = em.find(User.class, id);
		if (old_user == null) {
			throw new ResourceNotFoundException("Utente non trovato, non si può aggiornare.");

		}
		old_user.setUsername(new_user.getUsername());
		em.getTransaction().begin();
		em.merge(old_user);
		em.getTransaction().commit();
		return old_user;
	}

	public User delete(int id) throws Exception {
		User user_to_remove = em.find(User.class, id);
		if (user_to_remove == null) {
			throw new ResourceNotFoundException("Utente non trovato, non si può cancellare.");
		}
		em.getTransaction().begin();
		em.remove(user_to_remove);
		em.getTransaction().commit();
		return user_to_remove;
	}

	public User findById(int id) throws Exception {

		em.getTransaction().begin();
		User u = em.find(User.class, id);
		em.getTransaction().commit();
		if (u == null) {
			throw new ResourceNotFoundException("Utente con id " + id + " non trovato");
		}

		return u;

	}

}
