package com.rockhoppertech.example.marshal.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rockhoppertech.example.domain.User;

/**
 * A JAXB helper since JAXB doesn't know what a Java list is.
 * @author <a href="mailto:gene@rockhoppertech.com">Gene De Lisa</a>
 * 
 */
@XmlRootElement(name = "Users")
// bind all non-static, non-transient fields
// to XML unless annotated with @XmlTransient
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UserList {

	private List<User> users = new ArrayList<User>();

	// JAXB needs this default constructor
	public UserList() {
	}

	public UserList(List<User> users) {
		this.setUsers(users);
	}

	@XmlElement(name = "User")
	// @XmlJavaTypeAdapter(value = UserAdapter.class)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = new ArrayList<User>(users);
	}

	/**
	 * Returns "this" for a fluent interface.
	 * <code>list.add(u1).add(u2);</code>
	 * @param user
	 * @return
	 */
	public UserList add(User user) {
		this.users.add(user);
		return this;
	}

	public User findUserByFamilyName(String name) {
		for (User c : users) {
			if (c.getFamilyName().equals(name))
				return c;
		}
		return null;
	}
}
