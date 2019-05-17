package com.coll.dao;

import java.util.List;

import com.coll.model.ForumComment;

public interface ForumCommentDAO {
	public ForumComment getForumComment(int commentId);

	public boolean addForumComment(ForumComment forumComment);

	public boolean editForumComment(ForumComment forumComment);

	public boolean deleteForumComment(ForumComment forumComment);

	public List<ForumComment> getForumCommentList(int forumId);
}
