package com.rockhoppertech.example.domain;

import java.util.EnumSet;
import java.util.Set;

import javax.persistence.Entity;

/**
 * @author <a href="mailto:gene@rockhoppertech.com">Gene De Lisa</a>
 * 
 */
@Entity
public class Roles {
	

	public enum Role {
		user(0), siteAdmin(1), navsupRep(2), saltsAdmin(3);

		int db;

		Role(int db) {
			this.db = db;
		}
	}
	private Set<User> users;
	
	static final EnumSet<Role> admins = EnumSet.of(Role.siteAdmin,
			Role.saltsAdmin);
	static final EnumSet<Role> nonAdminSchmucks = EnumSet.complementOf(admins);
	public static final EnumSet<Role> allRoles = EnumSet.allOf(Role.class);

	static boolean isAdmin(Role role) {
		return admins.contains(role);
	}

	static boolean isSchmuck(Role role) {
		return nonAdminSchmucks.contains(role);
	}

	public static boolean areAdmins(EnumSet<Role> test) {
		return admins.containsAll(test);
	}

	public static void main(String[] args) {

		for (Role s : allRoles) {
			System.out.println(s);
		}
		System.out.println("is user admin? " + isAdmin(Role.user));
		System.out.println("is saltsAdmin admin? " + isAdmin(Role.saltsAdmin));
		System.out.println("Are Admins? "
				+ areAdmins(EnumSet.of(Role.user, Role.saltsAdmin)));
		System.out.println("Are Admins? "
				+ areAdmins(EnumSet.of(Role.siteAdmin, Role.saltsAdmin)));

		// Uses the order of defined enum to determine range inclusion
		EnumSet<Role> firstGuys = EnumSet.range(Role.user, Role.navsupRep);
		System.out.println(firstGuys);
	}

}