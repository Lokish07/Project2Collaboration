package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.BlogCommentDAO;
import com.coll.model.BlogComment;

public class BlogCommentDAOTestCase {
	static BlogCommentDAO blogCommentDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		blogCommentDAO = (BlogCommentDAO) context.getBean("blogCommentDAO");
	}
	
	@Ignore
	@Test
	public void addBlogCommentTest() {
		BlogComment blogComment = new BlogComment();
		blogComment.setBlogId(1);
		blogComment.setCommentDate(new java.util.Date());
		blogComment.setCommentMessage("My first comment");
		blogComment.setUsername("sss");
		assertTrue("Problem adding blog comment", blogCommentDAO.addBlogComment(blogComment));
	}
	
	@Ignore
	@Test
	public void deleteBlogCommentTest() {
		BlogComment blogComment = blogCommentDAO.getBlogComment(2);
		assertTrue("Problem deleting blog comment", blogCommentDAO.deleteBlogComment(blogComment));
	}
	
	@Ignore
	@Test
	public void editBlogCommentTest() {
		BlogComment blogComment = blogCommentDAO.getBlogComment(80);
		blogComment.setCommentMessage("Edited comment");
		assertTrue("Problem editing blog comment", blogCommentDAO.editBlogComment(blogComment));
	}
	
	@Ignore
	@Test
	public void listBlogCommentsTest() {
		List<BlogComment> blogCommentList = blogCommentDAO.getBlogCommentList(95);
		for(BlogComment bc : blogCommentList)
		{
			System.out.print("\n"+bc.getCommentId()+"\t");
			System.out.println(bc.getCommentMessage());
		}
	}
}