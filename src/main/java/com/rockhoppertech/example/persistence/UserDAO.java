package com.rockhoppertech.example.persistence;

import java.util.List;

import com.rockhoppertech.example.domain.User;

/**
 * @author <a href="mailto:gene@rockhoppertech.com">Gene De Lisa</a>
 * 
 */
public interface UserDAO {
	public User create(User user);

	public void delete(String userName);

	public List<User> findAll();

	public User findByPK(Long pk);

	public User findUserByUserName(String userName);

	public int getNumberOfUsers();

	public List<User> getPage(int firstRow, int rowsPerPage, String sortField,
			boolean sortAscending);

	public User update(User user);

	public List<User> findByGivenName(String givenName);

	public List<User> findByFamilyName(String familyName);

	public List<User> findByEmail(String email);
}