package com.coll.dao;

import com.coll.model.ProfilePic;

public interface ProfilePicDAO {
	public boolean addProfilePic(ProfilePic profilePic);

	public boolean deleteProfilePic(String username);
	
	public boolean updateProfilePic(ProfilePic profilepic);

	public ProfilePic getProfilePic(String username);
}
