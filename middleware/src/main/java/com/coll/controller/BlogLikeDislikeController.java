package com.coll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.coll.dao.BlogDAO;
import com.coll.dao.BlogLikeDislikeDAO;
import com.coll.model.BlogDislike;
import com.coll.model.BlogLike;

@RestController
public class BlogLikeDislikeController {

	@Autowired
	BlogLikeDislikeDAO blogLikeDislikeDAO;
	@Autowired
	BlogDAO blogDAO;

	@PostMapping("/addBlogLike")
	public String addBlogLike(@RequestBody BlogLike blogLike) {
		if (blogLikeDislikeDAO.addBlogLike(blogLike)) {
			Gson gson = new Gson();
			return gson.toJson(blogLike);
		} else
			return "Error adding blog like";
	}

	@GetMapping("/deleteBlogLike/{blogLikeId}")
	public String deleteBlogLike(@PathVariable("blogLikeId") int blogLikeId) {
		BlogLike blogLike = blogLikeDislikeDAO.getBlogLikeById(blogLikeId);
		if (blogLikeDislikeDAO.removeBlogLike(blogLike)) {
			Gson gson = new Gson();
			return gson.toJson(blogLike);
		} else
			return "Error removing blog like";
	}

	@PostMapping("/addBlogDislike")
	public String addBlogDislike(@RequestBody BlogDislike blogDislike) {
		if (blogLikeDislikeDAO.addBlogDislike(blogDislike)) {
			Gson gson = new Gson();
			return gson.toJson(blogDislike);
		} else
			return "Error adding blog dislike";
	}

	@GetMapping("/deleteBlogDislike/{blogDislikeId}")
	public String deleteBlogDislike(@PathVariable("blogDislikeId") int blogDislikeId) {
		BlogDislike blogDislike = blogLikeDislikeDAO.getBlogDislikeById(blogDislikeId);
		if (blogLikeDislikeDAO.removeBlogDislike(blogDislike)) {
			Gson gson = new Gson();
			return gson.toJson(blogDislike);
		} else
			return "Error removing blog dislike";
	}

	public String getBlogLikeById(int likeId) {
		BlogLike blogLike = blogLikeDislikeDAO.getBlogLikeById(likeId);
		if (blogLike != null) {
			Gson gson = new Gson();
			return gson.toJson(blogLike);
		} else
			return "Error getting blog like";
	}

	public String getBlogDislikeById(int dislikeId) {
		BlogDislike blogDislike = blogLikeDislikeDAO.getBlogDislikeById(dislikeId);
		if (blogDislike != null) {
			Gson gson = new Gson();
			return gson.toJson(blogDislike);
		} else
			return "Error getting blog dislike";
	}

	@GetMapping(value = "/getBlogLike/{blogId}/{username}")
	public String getBlogLikeByUser(@PathVariable("blogId") int blogId, @PathVariable("username") String username) {
		BlogLike blogLike = blogLikeDislikeDAO.getBlogLikeByUser(username, blogId);
		Gson gson = new Gson();
		return gson.toJson(blogLike);
	}

	@GetMapping(value = "/getBlogDislike/{blogId}/{username}")
	public String getBlogDislikeByUser(@PathVariable("blogId") int blogId, @PathVariable("username") String username) {
		BlogDislike blogDislike = blogLikeDislikeDAO.getBlogDislikeByUser(username, blogId);
		Gson gson = new Gson();
		return gson.toJson(blogDislike);
	}

	public String getBlogLikesList(String username) {
		List<BlogLike> blogLikeList = blogLikeDislikeDAO.getBlogLikesList(username);
		if (blogLikeList != null) {
			Gson gson = new Gson();
			return gson.toJson(blogLikeList);
		} else
			return "Error getting blog likes list of user " + username;
	}

	public String getBlogDislikesList(String username) {
		List<BlogDislike> blogDislikeList = blogLikeDislikeDAO.getBlogDislikesList(username);
		if (blogDislikeList != null) {
			Gson gson = new Gson();
			return gson.toJson(blogDislikeList);
		} else
			return "Error getting blog dislikes list of user " + username;
	}
}