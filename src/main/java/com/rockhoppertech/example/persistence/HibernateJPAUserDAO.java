package com.rockhoppertech.example.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.rockhoppertech.example.domain.User;

/**
 * TODO: this is incomplete. Just a taste to get your started.
 * 
 * @author <a href="mailto:gene@rockhoppertech.com">Gene De Lisa</a>
 *
 */
public class HibernateJPAUserDAO implements UserDAO {
	
	// actually, in real life I put this nonsense in an AbstractDAO from which the
	// individual DAOs inherit.
	
	protected EntityManager entityManager;
	EntityManagerFactory emf;

	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected EntityManager getEntityManager() {
		if (this.entityManager == null) {
			emf = Persistence.createEntityManagerFactory("users-jpa");
			entityManager = emf.createEntityManager();
		}
		return this.entityManager;
	}
	
	@Override
	public User create(User user) {
		EntityManager em = this.getEntityManager();
		em.persist(user);
		return user;
	}

	@Override
	public void delete(String userName) {
		EntityManager em = this.getEntityManager();
	}

	@Override
	public List<User> findAll() {
		EntityManager em = this.getEntityManager();

		return null;
	}

	@Override
	public User findByPK(Long pk) {
		EntityManager em = this.getEntityManager();

		return null;
	}

	@Override
	public User findUserByUserName(String userName) {
		EntityManager em = this.getEntityManager();
		return null;
	}

	@Override
	public int getNumberOfUsers() {
		EntityManager em = this.getEntityManager();
		return 0;
	}

	@Override
	public List<User> getPage(int firstRow, int rowsPerPage, String sortField,
			boolean sortAscending) {
		EntityManager em = this.getEntityManager();
		return null;
	}

	@Override
	public User update(User user) {
		EntityManager em = this.getEntityManager();
		return null;
	}

	@Override
	public List<User> findByGivenName(String givenName) {
		EntityManager em = this.getEntityManager();
		return null;
	}

	@Override
	public List<User> findByFamilyName(String familyName) {
		EntityManager em = this.getEntityManager();
		return null;
	}

	@Override
	public List<User> findByEmail(String email) {
		EntityManager em = this.getEntityManager();
		return null;
	}

}
