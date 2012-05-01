/**
 * 
 */
package com.rockhoppertech.example.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.Email;
import org.springframework.util.StringUtils;

import com.rockhoppertech.example.domain.Roles.Role;

/**
 * 
 * @author <a href="mailto:gene@rockhoppertech.com">Gene De Lisa</a>
 * 
 */
@Entity
@XmlRootElement
// @XmlAccessorType(XmlAccessType.PROPERTY)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserType", propOrder = { "userID", "userName", "familyName",
		"givenName", "email", "password", "roles", "creationDate", "lastActivityDate",
		"active"})
public class User {
	public final static class UserComparator implements Comparator<User> {
		private boolean ascending = true;
		private String sortField;
		private Class<?> theClass = User.class;

		@SuppressWarnings("unchecked")
		@Override
		public int compare(User o1, User o2) {
			User c1 = o1;
			User c2 = o2;
			if (this.ascending == false) {
				c1 = o2;
				c2 = o1;
			}
			try {
				Object fo1 = this.getValue(c1);
				Object fo2 = this.getValue(c2);
				if (Comparable.class.isAssignableFrom(fo1.getClass())) {
					return ((Comparable) fo1).compareTo(fo2);
				} else {
					System.err.println("Not assignable to comparable");
					throw new IllegalArgumentException(
							"Not assignable to comparable: " + fo1);
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			return 0;
		}

		private Object getValue(Object a) {
			Object value = null;
			String methodName = "get" + this.sortField;
			try {
				Method method = this.theClass.getMethod(methodName,
						(Class<?>[]) null);
				value = method.invoke(a, (Object[]) null);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return value;
		}

		public void setAscending(boolean b) {
			this.ascending = b;
		}

		public void setSortField(String sortField) {
			// this is a Spring method. Jakarta Commons Lang has one also.
			this.sortField = StringUtils.capitalize(sortField);
		}

		void toggleAscending() {
			this.ascending = !this.ascending;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userID;

	// Spring's DefaultMessageCodesResolver uses this format
	@NotNull(message = "{NotNull.user.email}")
	@Email
	@Column(name = "EMAIL")
	@XmlElement(name = "email", required = true)
	private String email;

	@NotNull(message = "{NotNull.user.familyName}")
	@Size(min = 2, max = 32, message = "{Size.user.familyName}")
	@Column(name = "FAMILY_NAME")
	@XmlElement(name = "familyName", required = false)
	private String familyName;

	@NotNull(message = "{NotNull.user.givenName}")
	@Size(min = 2, max = 32, message = "{Size.user.givenName}")
	@Column(name = "GIVEN_NAME")
	@XmlElement(name = "givenName", required = false)
	private String givenName;

	@NotNull(message = "{NotNull.user.password}")
	@Size(min = 6, max = 32, message = "{Size.user.password}")
	@Column(name = "PASSWORD")
	//@Pattern(regexp="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message="")
	@XmlElement(name = "password", required = false)
	private String password;

	// @ManyToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity =
	// Role.class, fetch = FetchType.EAGER)
	// @XmlElementWrapper(name = "roles")
	// @XmlElement(name = "role", required = true)
	private EnumSet<Role> roles;

	@Column(name = "USER_NAME")
	@NotNull(message = "{NotNull.user.userName}")
	@Size(min = 2, max = 32, message = "{Size.user.userName}")
	@XmlElement(name = "userName", required = true)
	private String userName;

	@NotNull(message = "{NotNull.user.creationDate}")
	@Future(message = "{Future.user.creationDate}")
	@Column(name = "CREATION_DATE")
	@XmlElement(name = "creationDate", required = false)
	private Date creationDate;

	@NotNull(message = "{NotNull.user.lastActivityDate}")
	@Future(message = "{Future.user.lastActivityDate}")
	@Column(name = "LAST_ACTIVITY_DATE")
	@XmlElement(name = "lastActivity", required = false)
	private Date lastActivityDate;

	@Column(name = "ACTIVE")
	@XmlElement(name = "active", required = false)
	private boolean active = true;

	public User() {
		this.roles = EnumSet.noneOf(Role.class);
	}

	public User(final String userName, final String password,
			final String givenName, final String familyName, final String email) {

		this.roles = EnumSet.noneOf(Role.class);
		this.userName = userName;
		this.password = password;
		this.givenName = givenName;
		this.familyName = familyName;
		this.email = email;
	}

	@PrePersist
	public void prePersist() {
		this.setCreationDate(new Date());
		this.setLastActivityDate(new Date());
	}

	@PreUpdate
	public void preUpdate() {
		this.setLastActivityDate(new Date());
	}

	public User addRole(final Role role) {
		this.roles.add(role);
		return this;
	}

	/*
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (this.familyName == null) {
			if (other.familyName != null) {
				return false;
			}
		} else if (!this.familyName.equals(other.familyName)) {
			return false;
		}
		if (this.givenName == null) {
			if (other.givenName != null) {
				return false;
			}
		} else if (!this.givenName.equals(other.givenName)) {
			return false;
		}
		if (this.userID == null) {
			if (other.userID != null) {
				return false;
			}
		} else if (!this.userID.equals(other.userID)) {
			return false;
		}
		if (this.userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!this.userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return this.familyName;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return this.givenName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return Collections.unmodifiableSet(this.roles);
	}

	/**
	 * @return the userID
	 */
	public Long getUserID() {
		return this.userID;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return this.userName;
	}

	/*
	 * Read Effective Java.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.familyName == null) ? 0 : this.familyName.hashCode());
		result = prime * result
				+ ((this.givenName == null) ? 0 : this.givenName.hashCode());
		result = prime * result
				+ ((this.userID == null) ? 0 : this.userID.hashCode());
		result = prime * result
				+ ((this.userName == null) ? 0 : this.userName.hashCode());
		return result;
	}

	public boolean isInRole(final Role role) {
		return this.roles.contains(role);
	}

	public User removeRole(final Role role) {
		this.roles.add(role);
		return this;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param familyName
	 *            the familyName to set
	 */
	public void setFamilyName(final String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @param givenName
	 *            the givenName to set
	 */
	public void setGivenName(final String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(final EnumSet<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("usr ID:").append(this.userID).append(' ');
		sb.append("user name:").append(this.userName).append(' ');
		sb.append("password:").append(this.password).append(' ');
		sb.append("email:").append(this.email).append(' ');
		sb.append("given Name:").append(this.givenName).append(' ');
		sb.append("family Name:").append(this.familyName).append(' ');
		sb.append("active:").append(this.active).append(' ');
		sb.append("creation:").append(this.creationDate).append(' ');
		sb.append("last activity:").append(this.lastActivityDate).append(' ');
		for (Role role : this.roles) {
			sb.append("role:").append(role).append(' ');
		}
		return sb.toString();
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
