package com.rockhoppertech.example.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rockhoppertech.example.domain.Roles.Role;
import com.rockhoppertech.example.domain.User;

/**
 * A very simple dao annotated so Spring will pick it up.
 * 
 * @author <a href="mailto:gene@rockhoppertech.com">Gene De Lisa</a>
 * 
 */
@Repository("UserDAO")
public class DefaultUserDAO implements UserDAO {
	private Map<String, User> users;
	private List<User> userList;

	public DefaultUserDAO() {
		this.users = new HashMap<String, User>();
		this.userList = new LinkedList<User>();
		User user = null;
		this.create(user = new User("rocky", "yo", "Rocky", "Balboa",
				"foo@bar.com"));
		user.addRole(Role.user).addRole(Role.saltsAdmin);
		this.create(user = new User("bruno", "domeSweetDome", "Bruno",
				"Lessky", "foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("noah", "pa$$word", "Noah", "Vale",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("heidi", "pa$$word", "Heidi", "Clare",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("helen", "pa$$word", "Helen", "Back",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("jack", "pa$$word", "Jack", "Haas",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("justin", "pa$$word", "Justin", "Case",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("ophelia", "pa$$word", "Ophelia", "Payne",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("paige", "pa$$word", "Paige", "Turner",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("ricko", "pa$$word", "Rick", "O'Shea",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("ricks", "pa$$word", "Rick", "Shaw",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("sal", "pa$$word", "Sal", "Minella",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("seth", "pa$$word", "Seth", "Poole",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("russ", "pa$$word", "Russell", "Leeves",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("shanda", "pa$$word", "Shanda", "Lear",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("sonny", "pa$$word", "Sonny", "Day",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("stan", "pa$$word", "Stan", "Still",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("stanc", "pa$$word", "Stanley", "Cupp",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("sue", "pa$$word", "Sue", "Flay",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("tim", "pa$$word", "Tim", "Burr",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("tommy", "pa$$word", "Tommy", "Hawk",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("warren", "pa$$word", "Warren", "Peace",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("will", "pa$$word", "Will", "Power",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("woody", "pa$$word", "Woody", "Forrest",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("x", "pa$$word", "X.", "Benedict",
				"foo@bar.com"));
		user.addRole(Role.user);
		this.create(user = new User("bosnia", "pa$$word", "Sarah", "Yayvo",
				"foo@bar.com"));

		this.userList.addAll(this.users.values());
	}

	/**
	 * @see com.rockhoppertech.example.service.UserService#create(com.rockhoppertech.example.domain.User)
	 */
	@Override
	public User create(final User user) {
		this.users.put(user.getUserName(), user);
		return user;
	}

	/*
	 * @see
	 * com.rockhoppertech.example.persistence.UserDAO#delete(java.lang.String)
	 */
	@Override
	public void delete(String userName) {
		if (this.users.containsKey(userName)) {
			this.users.remove(userName);
		} else {
			throw new IllegalArgumentException(
					"User is unknown. How can I delete that? " + userName);
		}
	}

	/*
	 * @see com.rockhoppertech.example.persistence.UserDAO#getAllUsers()
	 */
	@Override
	public List<User> findAll() {
		final List<User> all = new ArrayList<User>();
		for (final User user : this.users.values()) {
			all.add(user);
		}
		return Collections.unmodifiableList(all);
	}

	/**
	 * @see com.rockhoppertech.example.service.UserService#findByUserName(java.lang.String)
	 */
	@Override
	public User findUserByUserName(final String userName) {
		if (this.users.containsKey(userName) == false) {
			throw new IllegalArgumentException("Whoozat? " + userName);
		}
		final User user = this.users.get(userName);
		// make a defensive copy?
		return user;
	}

	/**
	 * @see com.rockhoppertech.example.service.UserService#update(com.rockhoppertech.example.domain.User)
	 */
	@Override
	public User update(final User user) {
		if (this.users.containsKey(user.getUserName())) {
			this.users.put(user.getUserName(), user);
		} else {
			throw new IllegalArgumentException(
					"User is unknown. How can I update that? " + user);
		}
		return user;
	}

	@Override
	public int getNumberOfUsers() {
		return this.users.size();
	}

	@Override
	public List<User> getPage(int firstRow, int rowsPerPage, String sortField,
			boolean sortAscending) {
		User.UserComparator comp = new User.UserComparator();
		comp.setAscending(sortAscending);
		comp.setSortField(sortField);
		Collections.sort(this.userList, comp);

		// int fromIndex = firstRow * rowsPerPage;
		// int toIndex = firstRow * rowsPerPage + rowsPerPage;

		int fromIndex = firstRow;
		int toIndex = firstRow + rowsPerPage;
		if (toIndex >= this.userList.size() - 1) {
			toIndex = this.userList.size() - 1;
		}
		List<User> page = this.userList.subList(fromIndex, toIndex);
		return page;
	}

	@Override
	public User findByPK(Long pk) {
		// boy is this clunky
		List<User> all = findAll();
		for (User user : all) {
			if (user.getUserID().equals(pk))
				return user;
		}
		return null;
	}

	@Override
	public List<User> findByGivenName(String givenName) {
		List<User> all = findAll();
		List<User> results = new ArrayList<User>();
		for (User user : all) {
			if (user.getGivenName().equals(givenName))
				results.add(user);
		}
		return results;
	}

	@Override
	public List<User> findByFamilyName(String familyName) {
		List<User> all = findAll();
		List<User> results = new ArrayList<User>();
		for (User user : all) {
			if (user.getFamilyName().equals(familyName))
				results.add(user);
		}
		return results;
	}

	@Override
	public List<User> findByEmail(String email) {
		List<User> all = findAll();
		List<User> results = new ArrayList<User>();
		for (User user : all) {
			if (user.getEmail().equals(email))
				results.add(user);
		}
		return results;
	}
}
