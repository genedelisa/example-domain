package com.rockhoppertech.example.service;

import java.util.List;

import com.rockhoppertech.example.domain.User;

/**
 * @author Gene De Lisa
 * 
 */
public interface UserService {
	public void create(User user);

	public User findByUserName(String userName);

	public List<User> findAll();

	public User update(User user);

	public void delete(String userName);

	public void delete(User user);

	public int getNumberOfUsers();

	public User findByPK(Long pk);

	public List<User> findByGivenName(String givenName);

	public List<User> findByFamilyName(String familyName);

	public List<User> findByEmail(String email);

	public List<User> getPage(int firstRow, int rowsPerPage, String sortField,
			boolean sortAscending);

}