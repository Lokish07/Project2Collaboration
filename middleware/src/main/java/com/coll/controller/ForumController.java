package com.coll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.coll.dao.ForumDAO;
import com.coll.dao.FriendDAO;
import com.coll.model.Blog;
import com.coll.model.Forum;
import com.coll.model.UserDetail;

@RestController
public class ForumController {

	@Autowired
	ForumDAO forumDAO;
	@Autowired
	FriendDAO friendDAO;

	@PostMapping(value = "/addForum")
	public String addForum(@RequestBody Forum forum) {
		forum.setCreatedDate(new java.util.Date());
		if (forumDAO.addForum(forum)) {
			Gson gson = new Gson();
			return gson.toJson(forum);
		} else
			return "Error adding forum";
	}

	@GetMapping(value = "/deleteForum/{forumId}")
	public String deleteForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);
		if (forumDAO.deleteForum(forum)) {
			Gson gson = new Gson();
			return gson.toJson(forum);
		} else
			return "Error deleting forum";
	}

	@PostMapping(value = "/updateForum")
	public String updateForum(@RequestBody Forum forum) {
		forum.setCreatedDate(forumDAO.getForum(forum.getForumId()).getCreatedDate());

		if (forumDAO.updateForumDetails(forum)) {
			Gson gson = new Gson();
			return gson.toJson(forum);
		} else
			return "Error updating forum";
	}

	@GetMapping(value = "/approveForum/{forumId}")
	public String approveForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);
		if (forumDAO.approveForum(forum)) {
			{
				Gson gson = new Gson();
				return gson.toJson(forum);
			}
		} else {
			return "Error approving forum";
		}
	}

	@GetMapping(value = "/rejectForum/{forumId}")
	public String rejectForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);
		if (forumDAO.rejectForum(forum)) {
			{
				Gson gson = new Gson();
				return gson.toJson(forum);
			}
		} else {
			return "Error rejecting forum";
		}
	}

	@GetMapping(value = "/getForumList")
	public String getForumList() {
		List<Forum> forumList = forumDAO.getForumList();
		if (forumList != null) {
			Gson gson = new Gson();
			return gson.toJson(forumList);
		} else {
			return "Error rejecting forum";
		}
	}

	@GetMapping(value = "/getForum/{forumId}")
	public String getForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);
		if (forum != null) {
			Gson gson = new Gson();
			return gson.toJson(forum);
		} else
			return "Error retrieving forum";
	}

	@GetMapping(value = "/getLimitedForumList/{username}/{startRowNum}/{endRowNum}")
	public String getLimitedForumList(@PathVariable("username") String username,
			@PathVariable("startRowNum") int startRowNum, @PathVariable("endRowNum") int endRowNum) {
		List<Forum> forumList = forumDAO.getLimitedForumList(username, startRowNum, endRowNum);
		if (forumList != null) {
			Gson gson = new Gson();
			return gson.toJson(forumList);
		} else {
			return "Error getting limited forum list.";
		}
	}
}