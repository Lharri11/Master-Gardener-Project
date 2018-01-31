//This test sucks and whoever wrote it should feel bad
//On a side node, I changed line 24 from new User(null, null, 0, ..) to actually be a (fake) account

package model;

//import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GardenTest {

	private Garden group;
	private User testAccount, moderator_account;
	private ArrayList<User> userlist;
	private ArrayList<User> mod_list;
	private ArrayList<Post> testposts;
	private Post testpost;

	@Before
	public void setUp()
	{
		//testing variables
		// FIXME
		testAccount = new User("chorgy", "12345678", 1, "dude", "look", "lady", null);

		userlist = new ArrayList<User>();
		userlist.add(testAccount);
		testpost = new Post("a post", 1, 1);
		testposts = new ArrayList<Post>();
		testposts.add(testpost);

		//moderator_account = new User("Mod", "password", 2, "Admin", "admin@mgp.org", "Admin");
		//moderator_account.setModeratorStatus(true);
		//mod_list.add(moderator_account);

		group = new Garden("a garden", "description" );
		group.setGarden_id(1);
		group.setMembers(userlist);

		group.setPosts(testposts);
	}


	@Test
	public void getGardenNameTest()
	{
		assertEquals(group.getGarden_name(), "a garden");
	}

	@Test
	public void setGardenNameTest()
	{
		group.setGarden_name("Eggly");
		assertEquals(group.getGarden_name(), "Eggly");
	}

	@Test
	public void getDescriptionTest()
	{
		assertEquals(group.getDescription(), "description");
	}

	@Test
	public void setDescriptionTest()
	{
		group.setDescription("Memes never die");
		assertEquals(group.getDescription(), "Memes never die");

	}

	@Test
	public void getGardenIDTest()
	{
		assertEquals(group.getGarden_id(), 1);
	}

	@Test
	public void setGardenIDTest()
	{
		group.setGarden_id(5);
		assertEquals(group.getGarden_id(), 5);
	}

	@Test
	public void getMembersOfGardenTest()
	{
		assertEquals(group.getMembers(), userlist);
	}

	@Test
	public void setMembersOfGardenTest()
	{
		// FIXME
		User a2 = new User("Eggguy", "Dungle", 1, "Man", "dude@gmail.com", "crud", null);
		// Add the account to userlist as well since userlist should = group.getMembers anyway.
		userlist.add(a2);
		group.setMembers(userlist);
		assertEquals(group.getMembers(), userlist);
	}


	/*@Test
	public void test() {
		//does NOT test for requests, requests not complete yet.
		
		//testing variables
		Garden group = new Garden("a group", "description");
		User testUser = new User("chorgy", "12345678", 1, "dude", "look", "lady");
		ArrayList<User> userlist = new ArrayList<User>();
		userlist.add(testUser);
		Post testpost = new Post("a post", 1, 1);
		ArrayList<Post> testposts = new ArrayList<Post>();
		testposts.add(testpost);
		
		//actual tests
		org.junit.Assert.assertEquals(group.getDescription(), "description");
		group.setDescription("a new group");
		org.junit.Assert.assertEquals(group.getDescription(), "a new group");
		group.addAccount(testUser);
		org.junit.Assert.assertEquals(group.getMembers(), userlist);
		org.junit.Assert.assertFalse(group.userisMod(testUser));
		group.addMod(testUser);
		org.junit.Assert.assertTrue(group.userisMod(testUser));
		org.junit.Assert.assertEquals(group.getModerators(), userlist);
		group.createPost("a post");
		org.junit.Assert.assertFalse(group.getPosts().isEmpty());

	}*/

}
