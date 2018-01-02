package controller;

import static org.junit.Assert.*;

//import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

import model.User;
import org.junit.Before;
import org.junit.Test;

import database.core.DatabaseProvider;
import database.core.MySQLDatabase;
import database.core.IDatabase;
import model.Garden;

public class UserControllerTest {

	private IDatabase db = null;
	private UserController thiscontroller = new UserController();
	
	
	@Before
	public void setUp() throws Exception {
		
		DatabaseProvider.setInstance(new MySQLDatabase());
		db = DatabaseProvider.getInstance();	
//		db.dropTables();
//		db.createTables();
//		db.loadInitialData();
	}
	
	@Test
	public void testreturnaccountfromUsername(){
		User admin1 = new User("admin1", "password1", -1, "a_name1", "admin1@ycp.edu", "HEY GUYS I’M ADMIN ONE LETS HAVE SOME FUN");
		User test = thiscontroller.returnUserForUsername("admin1");
		assertEquals(admin1.getName(), test.getName());
		assertEquals(admin1.getEmail(), test.getEmail());
		assertEquals(admin1.getLoginId(), test.getLoginId());
		assertEquals(admin1.getPassword(), test.getPassword());
		assertEquals(admin1.getDescription(), test.getDescription());
		assertEquals(admin1.getUsername(), test.getUsername());
	}
	
	
	@Test
	public void testgetusersgardens() throws SQLException{
		User admin1 = new User("admin1", "password1", -1, "a_name1", "admin1@ycp.edu", "HEY GUYS I’M ADMIN ONE LETS HAVE SOME FUN");
		List<Garden> gardens = new ArrayList<Garden>();
		gardens = thiscontroller.getUsersGardens("admin1");

		assertEquals(gardens.get(0).getGarden_name(), "Adams County Garden");
//		List<Garden> testgardens = new ArrayList<Garden>();
//		testgardens = thiscontroller.getUsersGardens("admin1");
//		assertEquals(testgardens.get(0).getGarden_name(), " ");
//		assertEquals(testgardens.get(1).getGarden_name(), "Group2");
	}
	
	
	@Test
	public void testgetgardenbygardenname() throws SQLException{
		Garden test = new Garden("Adams County Garden", "Description1");
		test.setGarden_id(1);
		int get = thiscontroller.getGardenIDbyGardenname("Adams County Garden");
		assertEquals(test.getGarden_id(), get);
	}
	
	
	
	
	
	
}


