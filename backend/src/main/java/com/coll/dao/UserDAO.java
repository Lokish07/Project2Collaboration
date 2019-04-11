package com.coll.dao;

import java.util.List;

import com.coll.model.UserDetail;

public interface UserDAO {
	public boolean addUser(UserDetail user);

	public boolean deleteUser(UserDetail user);

	public boolean updateUser(UserDetail user);

	public UserDetail getUser(String username);

	public boolean approveUser(UserDetail user);

	public boolean rejectUser(UserDetail user);
	
	public List<UserDetail> getUserList();
}