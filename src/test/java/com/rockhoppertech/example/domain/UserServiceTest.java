package com.rockhoppertech.example.domain;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rockhoppertech.example.service.UserService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;

import static org.hamcrest.collection.IsCollectionContaining.hasItem;

import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:gene@rockhoppertech.com">Gene De Lisa</a>
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class UserServiceTest {

	@Autowired(required = true)
	private UserService userService;

	@Test
	public void shouldCreate() {
		assertThat(userService, is(notNullValue()));
		assertThat(userService, is(instanceOf(UserService.class)));

		User user = new User("bozo", "password", "bozo", "clown", "foo@bar.com");
		this.userService.create(user);
		User user2 = this.userService.findByUserName(user.getUserName());
		assertThat(user2, is(notNullValue()));
		assertThat(user, equalTo(user2));
		assertThat(this.userService.findAll(), hasItem(user));
	}

	@Test
	public void shouldFindAll() {
		List<User> all = this.userService.findAll();
		assertThat(all, is(notNullValue()));
		assertThat(all.size(), not(equalTo(0)));
	}

	@Test
	public void shouldFindUserByUserName() {
		User user = new User("bozo", "password", "bozo", "clown", "foo@bar.com");
		this.userService.create(user);
		User user2 = this.userService.findByUserName(user.getUserName());
		assertThat(user2, is(notNullValue()));
	}

	@Test
	public void shouldUpdate() {
		User user = new User("bozo", "password", "bozo", "clown", "foo@bar.com");
		this.userService.create(user);
		User user2 = this.userService.findByUserName(user.getUserName());
		assertThat(user2, is(notNullValue()));
		String newGiven = "bozo T.";
		user.setGivenName(newGiven);
		this.userService.update(user);
		user2 = this.userService.findByUserName(user.getUserName());
		assertThat(user2.getGivenName(), equalTo(newGiven));
	}

	@Test
	public void shouldDelete() {
		User user = new User("bozo", "password", "bozo", "clown", "foo@bar.com");
		this.userService.create(user);
		// now you see him
		assertThat(this.userService.findAll(), hasItem(user));
		this.userService.delete(user.getUserName());
		// now you don't
		assertThat(this.userService.findAll(), not(hasItem(user)));

	}
}
