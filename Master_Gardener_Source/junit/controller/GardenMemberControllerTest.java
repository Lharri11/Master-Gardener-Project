package controller;

import database.core.DatabaseProvider;
import database.core.IDatabase;
import database.core.MySQLDatabase;
import model.GardenMember;
import model.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

//import java.sql.Connection;
//import java.util.Random;

public class GardenMemberControllerTest {

	private IDatabase db = null;
	private AdminController thiscontroller = new AdminController();
	private EditUserController someController = new EditUserController();
	private UserController someController1 = new UserController();
	private GardenMemberController ThisGardenController = new GardenMemberController();



	@Before
	public void setUp() throws Exception {
		
		DatabaseProvider.setInstance(new MySQLDatabase());
		db = DatabaseProvider.getInstance();

	}
	
	@Test
	public void TestInsertNewGardenMemberIntoDataBase() throws SQLException{
		User admin9 = new User("admin9", "password9", -1, "a_name9", "admin9@ycp.edu", "HEY GUYS I’M ADMIN n9ne LETS HAVE SOME FUN", null);
		Boolean test = thiscontroller.createUser(admin9);
		assertEquals(true, test);
		GardenMember garden = new GardenMember(1,someController1.returnUserForUsername("admin9").getUserId());

		Boolean worked = ThisGardenController.addGardenMember(garden);

		assertEquals(true, worked);


		// get the new account information from the database and delete it
		Boolean admin11 = thiscontroller.deleteUserByUsername(admin9);
		assertEquals(true, admin11);

	}
}


