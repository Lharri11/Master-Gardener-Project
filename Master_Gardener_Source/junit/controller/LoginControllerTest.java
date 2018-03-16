package controller;

import static org.junit.Assert.*;

//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;


import model.User;
import org.junit.Before;
import org.junit.Test;

import database.core.DatabaseProvider;
import database.core.MySQLDatabase;
import database.core.IDatabase;

import java.sql.SQLException;
//import model.User;
//import model.Group;

public class LoginControllerTest {

	private IDatabase db = null;
	LoginController thiscontroller = new LoginController();
	
	@Before
	public void setUp() throws Exception {
		
		DatabaseProvider.setInstance(new MySQLDatabase());
		db = DatabaseProvider.getInstance();	
//		db.dropTables();
//		db.createTables();
//		db.loadInitialData();
	}
	
	@Test
	public void testController(){
		assertEquals(1,1);
	}


	@Test
	public void testLogin()throws SQLException {
		// FIXME
		User admin = new User("admin1", "password1", -1, "a_name1", "lastname", "admin1@ycp.edu", "HEY GUYS I’M ADMIN ONE LETS HAVE SOME FUN", null);
		int test = thiscontroller.validateLogin(admin.getUsername(),admin.getLoginId());
		assertEquals(test,admin.getLoginId());


		// FIXME
		User admin2 = new User("admin1", "password1", 5, "a_name1", "lastname", "admin1@ycp.edu", "HEY GUYS I’M ADMIN ONE LETS HAVE SOME FUN", null);
		int test2 = thiscontroller.validateLogin(admin.getUsername(),admin.getLoginId());
		assertFalse(test2 != admin.getLoginId());

	}

	@Test
	public void testFailedLogin()throws SQLException {
		// FIXME
		User admin = new User("admin1", "Butts", -1, "a_name1", "lastname", "admin1@ycp.edu", "HEY GUYS I’M ADMIN ONE LETS HAVE SOME FUN", null);
		int test = thiscontroller.loginUser(admin.getUsername(),admin.getPassword());
		assertEquals(test,-1);


	}



}


