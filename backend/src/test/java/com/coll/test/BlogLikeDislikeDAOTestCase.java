package com.coll.test;

import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.BlogLikeDislikeDAO;

public class BlogLikeDislikeDAOTestCase {

	static BlogLikeDislikeDAO blogLikeDislikeDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		blogLikeDislikeDAO = (BlogLikeDislikeDAO) context.getBean("blogLikeDislikeDAO");
	}
}