package model;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class AccountTest {
	User a1, a2, a3, a4, a5;
	
    @Before
	public void setUp(){
    	//---------Same setup as BooksForSaleTest (all components needed)---------------------------------------------------------------
       
	    this.a1 = new User("LoganH24", "abc12314", 1, "LogeyBear", "Logan.Harris1995@gmail.com", "555-555-5555", new byte[1024]);
	    this.a2 = new User("Amcdevitt97", "def456123", 2, "Ally", "amcdevitt97@ycp.edu", "555-555-5555", new byte[1024]);
	    this.a3 = new User("GRuths", "ghi789122", 3, "Garry", "gruths@ycp.edu", "555-555-5555", new byte[1024]);
	    
	
		//---------Same setup as BooksForSaleTest (all components needed)----------------------------------------------------------------
	}

	/*@Test(expected = IllegalArgumentException.class)
	public void illegalArgumentExceptionTest()
	{
		// Test password length
		a4 = new User("ChugCroble", "1234567", 20, "Chug Croble", "chugcroble@gmail.com", "420-666-6969", new byte[1024]);

		// Test literally anything else
		a5 = new User("Memes&Cheese", "123456789", -1, "", "", "", new byte[1024]);
	}*/

	@Test
	public void getUsernameTest(){
		assertEquals(a1.getUsername(),"LoganH24");
		assertEquals(a2.getUsername(),"Amcdevitt97");
		assertEquals(a3.getUsername(),"GRuths");
	}

	@Test
	//Only works if getUsername works, which is also tested.
	public void setUsernameTest()
	{
		a1.setUsername("mememan69");
		assertEquals(a1.getUsername(), "mememan69");
		
		a2.setUsername("crub");
		assertEquals(a2.getUsername(), "crub");
	}
	
	@Test
	public void getPasswordTest(){
		assertEquals(a1.getPassword(),"abc12314");
		assertEquals(a2.getPassword(),"def456123");
		assertEquals(a3.getPassword(),"ghi789122");
	}
	
	@Test
	//Bear in mind that this tests when password lengths meet requirements
	//(kind of like every other test)
	public void setPasswordTest()
	{
		a1.setPassword("crub_skriggle");
		assertEquals(a1.getPassword(), "crub_skriggle");
	}
	
	@Test
	public void getLoginIdTest(){
		assertEquals(a1.getLoginId(), 1);
		assertEquals(a2.getLoginId(), 2);
		assertEquals(a3.getLoginId(), 3);
	}
	
	@Test
	//This test won't be seen by the User
	public void setLoginIDTest()
	{
		a1.setLoginId(42);
		assertEquals(a1.getLoginId(), 42);
	}
	
	@Test
	public void getNameTest(){
		assertEquals(a1.getName(),"LogeyBear");
		assertEquals(a2.getName(),"Ally");
		assertEquals(a3.getName(),"Garry");
	}
	
	@Test
	public void setNameTest()
	{
		a1.setName("Edwardo");
		assertEquals(a1.getName(), "Edwardo");
	}
	
	@Test
	public void getEmailTest(){
		assertEquals(a1.getEmail(),"Logan.Harris1995@gmail.com");
		assertEquals(a2.getEmail(),"amcdevitt97@ycp.edu");
		assertEquals(a3.getEmail(),"gruths@ycp.edu");
	}
	
	@Test
	public void setEmailTest()
	{
		a1.setEmail("nicksucks@gmail.com");
		assertEquals(a1.getEmail(), "nicksucks@gmail.com");
	}
	
	@Test
	public void getDescriptionTest()
	{
		assertEquals(a1.getDescription(), "555-555-5555");
	}
	
	@Test
	public void setDescriptionTest()
	{
		a1.setDescription("555-555-5555 is a terrible description.");
		assertEquals(a1.getDescription(), "555-555-5555 is a terrible description.");
	}
	//TODO: Uncomment
	/*@Test
	public void getModeratorStatus()
	{
		assertEquals(a1.getModeratorStatus(), false);
	}

	@Test
	public void setModeratorStatus()
	{
		a1.setModeratorStatus('0');
		assertEquals(a1.getModeratorStatus(), true);
	} */
	
}