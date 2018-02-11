package controller;

import database.core.DatabaseProvider;
import database.core.IDatabase;
import database.core.MySQLDatabase;
import model.User;
//import org.eclipse.jetty.server.Authentication;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

//import java.sql.Connection;
//import java.util.Random;

public class EditUserControlerTest {

	private IDatabase db = null;
	private AdminController thiscontroller = new AdminController();
	private EditUserController someController = new EditUserController();
	private UserController someController1 = new UserController();


	@Before
	public void setUp() throws Exception {

		DatabaseProvider.setInstance(new MySQLDatabase());
		db = DatabaseProvider.getInstance();

	}

	@Test
	public void TestUpdateUsername() throws SQLException, IOException{
		// FIXME
		User admin9 = new User("admin9", "password9", -1, "a_name9", "admin9@ycp.edu", "HEY GUYS I’M ADMIN n9ne LETS HAVE SOME FUN", null);
		Boolean test = thiscontroller.createUser(admin9);
		assertEquals(true, test);

		// db statement
		Boolean worked = null;

		worked = someController.updateUserCredentialsbyUsername("admin9","Chase","HEY GUYS I’M ADMIN n9ne LETS HAVE SOME FUN" );

		assertEquals(true,worked);


//cleanup delete the account

		// get the new accoutn information from the database and delete it
		admin9 = someController1.returnUserForUsername("Chase");
		Boolean admin11 = thiscontroller.deleteUserByUsername(admin9);
		assertEquals(true, admin11);

	}


}
