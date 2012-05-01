package com.rockhoppertech.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockhoppertech.example.domain.User;
import com.rockhoppertech.example.persistence.UserDAO;

/**
 * A very simple service annotated so Spring will pick it up.
 * 
 * @author <a href="mailto:gene@rockhoppertech.com">Gene De Lisa</a>
 * 
 */
@Service("UserService")
public class DefaultUserService implements UserService {

	private UserDAO userDAO;

	@Autowired(required = true)
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public DefaultUserService() {
	}

	/**
	 * @see com.rockhoppertech.example.service.UserService#create(com.rockhoppertech.example.domain.User)
	 */
	@Override
	public void create(final User user) {
		this.userDAO.create(user);
	}

	/**
	 * @see com.rockhoppertech.example.service.UserService#delete(com.rockhoppertech.example.domain.User)
	 */
	@Override
	public void delete(final String userName) {
		this.userDAO.delete(userName);
	}

	/**
	 * @see com.rockhoppertech.example.service.UserService#findAll()
	 */
	@Override
	public List<User> findAll() {
		return this.userDAO.findAll();
	}

	/**
	 * @see com.rockhoppertech.example.service.UserService#findByUserName(java.lang.String)
	 */
	public User findByUserName(final String userName) {
		return this.userDAO.findUserByUserName(userName);
	}

	/**
	 * @return
	 * @see com.rockhoppertech.example.service.UserService#update(com.rockhoppertech.example.domain.User)
	 */
	@Override
	public User update(final User user) {
		return this.userDAO.update(user);
	}

	@Override
	public List<User> getPage(int firstRow, int rowsPerPage, String sortField,
			boolean sortAscending) {
		return this.userDAO.getPage(firstRow, rowsPerPage, sortField,
				sortAscending);
	}

	@Override
	public int getNumberOfUsers() {
		return this.userDAO.getNumberOfUsers();
	}

	@Override
	public void delete(User user) {
		this.userDAO.delete(user.getUserName());
	}

	@Override
	public User findByPK(Long pk) {
		return this.userDAO.findByPK(pk);
	}

	@Override
	public List<User> findByGivenName(String givenName) {
		return this.userDAO.findByGivenName(givenName);
	}

	@Override
	public List<User> findByFamilyName(String familyName) {
		return this.userDAO.findByFamilyName(familyName);
	}

	@Override
	public List<User> findByEmail(String email) {
		return this.userDAO.findByEmail(email);
	}

}
