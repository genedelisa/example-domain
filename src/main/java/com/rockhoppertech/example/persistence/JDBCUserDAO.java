package com.rockhoppertech.example.persistence;

import java.util.List;

import com.rockhoppertech.example.domain.User;

public class JDBCUserDAO implements UserDAO {

    @Override
    public User create(User user) {
        // TODO Auto-generated method stub
    	return null;
    }

    @Override
    public void delete(String userName) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNumberOfUsers() {
        String SQL_COUNT = "SELECT count(*) FROM Users";
        return 0;
    }

    @Override
    public List<User> getPage(int firstRow, int rowsPerPage, String sortField,
            boolean sortAscending) {
        String select = "SELECT id, name, value FROM Users ORDER BY %s %s LIMIT ?, ?";
        String sortDirection = sortAscending ? "ASC" : "DESC";
        String sql = String.format(select, sortField, sortDirection);

        return null;
    }

    @Override
    public User findUserByUserName(String userName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User update(User user) {
        // TODO Auto-generated method stub
    	return null;
    }

	@Override
	public User findByPK(Long pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByGivenName(String givenName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByFamilyName(String familyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

  

}
