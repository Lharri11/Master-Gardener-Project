package controller;

import database.core.DatabaseProvider;
import database.core.IDatabase;
import database.core.MySQLDatabase;
import model.Garden;
import model.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

//import java.sql.Connection;
//import java.util.Random;

public class AdminControllerTest {

	private IDatabase db = null;
	private AdminController thiscontroller = new AdminController();
	
	
	@Before
	public void setUp() throws Exception {
		
		DatabaseProvider.setInstance(new MySQLDatabase());
		db = DatabaseProvider.getInstance();	
//		db.dropTables();
//		db.createTables();
//		db.loadInitialData();
	}
	
	@Test
	public void TestCreateAndDeleteAccount() throws SQLException{
		// FIXME
		User admin9 = new User("admin9", "password9", -1, "a_name9", "admin9@ycp.edu", "HEY GUYS I’M ADMIN n9ne LETS HAVE SOME FUN", null);
		Boolean test = thiscontroller.createUser(admin9);
		assertEquals(true, test);

		Boolean admin11 = thiscontroller.deleteUserByUsername(admin9);
		assertEquals(true, admin11);
	}










	

	
	
	
	
	
	
}


