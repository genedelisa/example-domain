package com.rockhoppertech.example.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rockhoppertech.example.domain.Roles.Role;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:gene@rockhoppertech.com">Gene De Lisa</a>
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class UserTest {
	private static Validator validator;

	@BeforeClass
	public static void setUpValidator() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void shouldCreate() {
		User user = new User("bozo", "password", "bozo", "clown", "foo@bar.com");
		user.addRole(Role.user);
		assertThat(user, is(notNullValue()));
		assertThat(user.isInRole(Role.user), equalTo(true));
		assertThat(user.isInRole(Role.saltsAdmin), equalTo(false));
	}

	@Test
	public void shouldSort() {

		List<User> userList = new ArrayList<User>();
		User user;
		userList.add(user = new User("rocky", "yo", "Rocky", "Balboa",
				"foo@bar.com"));
		userList.add(user = new User("bruno", "domeSweetDome", "Bruno",
				"Lessky", "foo@bar.com"));
		userList.add(user = new User("noah", "pa$$word", "Noah", "Vale",
				"foo@bar.com"));
		userList.add(user = new User("heidi", "pa$$word", "Heidi", "Clare",
				"foo@bar.com"));
		userList.add(user = new User("helen", "pa$$word", "Helen", "Back",
				"foo@bar.com"));
		userList.add(user = new User("jack", "pa$$word", "Jack", "Haas",
				"foo@bar.com"));
		userList.add(user = new User("justin", "pa$$word", "Justin", "Case",
				"foo@bar.com"));
		userList.add(user = new User("ophelia", "pa$$word", "Ophelia", "Payne",
				"foo@bar.com"));
		userList.add(user = new User("paige", "pa$$word", "Paige", "Turner",
				"foo@bar.com"));
		userList.add(user = new User("ricko", "pa$$word", "Rick", "O'Shea",
				"foo@bar.com"));
		userList.add(user = new User("ricks", "pa$$word", "Rick", "Shaw",
				"foo@bar.com"));
		userList.add(user = new User("sal", "pa$$word", "Sal", "Minella",
				"foo@bar.com"));
		userList.add(user = new User("seth", "pa$$word", "Seth", "Poole",
				"foo@bar.com"));
		userList.add(user = new User("russ", "pa$$word", "Russell", "Leeves",
				"foo@bar.com"));
		userList.add(user = new User("shanda", "pa$$word", "Shanda", "Lear",
				"foo@bar.com"));
		userList.add(user = new User("sonny", "pa$$word", "Sonny", "Day",
				"foo@bar.com"));
		userList.add(user = new User("stan", "pa$$word", "Stan", "Still",
				"foo@bar.com"));
		userList.add(user = new User("stanc", "pa$$word", "Stanley", "Cupp",
				"foo@bar.com"));
		userList.add(user = new User("sue", "pa$$word", "Sue", "Flay",
				"foo@bar.com"));
		userList.add(user = new User("tim", "pa$$word", "Tim", "Burr",
				"foo@bar.com"));
		userList.add(user = new User("tommy", "pa$$word", "Tommy", "Hawk",
				"foo@bar.com"));
		userList.add(user = new User("warren", "pa$$word", "Warren", "Peace",
				"foo@bar.com"));
		userList.add(user = new User("will", "pa$$word", "Will", "Power",
				"foo@bar.com"));
		userList.add(user = new User("woody", "pa$$word", "Woody", "Forrest",
				"foo@bar.com"));
		userList.add(user = new User("x", "pa$$word", "X.", "Benedict",
				"foo@bar.com"));
		userList.add(user = new User("bosnia", "pa$$word", "Sarah", "Yayvo",
				"foo@bar.com"));

		User.UserComparator comp = new User.UserComparator();
		comp.setAscending(true);
		comp.setSortField("givenName");
		Collections.sort(userList, comp);
		int rowsPerPage = 5;
		int firstRow = 1;
		int fromIndex = firstRow * rowsPerPage;
		int toIndex = firstRow * rowsPerPage + rowsPerPage;
		List<User> page = userList.subList(fromIndex, toIndex);
		assertThat(page, is(notNullValue()));
		for (User u : page) {
			System.err.println(u.getGivenName());
		}
		System.err.println();
		System.err.println("entire list");
		for (User u : userList) {
			System.err.println(u.getGivenName());
		}

	}

	@Test
	public void shouldFailValidation() {
		User user = new User();
		user.setFamilyName("a");
		user.setEmail("bad address");

		Set<ConstraintViolation<User>> violations = validator.validate(user);

		assertThat("there are violations", violations.size(), greaterThan(0));

		for (ConstraintViolation<User> violation : violations) {

			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			System.err.print("violation: " + violation);
			System.err.println("path: " + propertyPath);
			System.err.println("message:" + message);
			System.err.println();
		}
	}

}
