package model;

//import static org.junit.Assert.*;

//import model.User;
import org.junit.Test;

import model.Post;

public class PostTest {

	@Test
	public void test() {
		//testing variables
		String text = "this is a post";
		Post post = new Post(text, 1, 1);
		org.junit.Assert.assertEquals(post.getComments(), "this is a post");
		//post.getComments("new post");
		//org.junit.Assert.assertEquals(post.getComments(), "new post");
	}
}
