package controller;

import static org.junit.Assert.*;

import database.core.MySQLDatabase;
import model.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.core.DatabaseProvider;
import database.core.IDatabase;
import model.Garden;

public class GroupControllerTest {

	private IDatabase db = null;
	GardenController thiscontroller = new GardenController();
	
	@Before
	public void setUp() throws Exception {
		
		DatabaseProvider.setInstance(new MySQLDatabase());
		db = DatabaseProvider.getInstance();	
//		db.dropTables();
//		db.createTables();
		//db.loadInitialData();
	}
	
	@Test
	public void testreturnAccountForUsername(){
		// FIXME
		User admin = new User("admin1", "password1", -1, "a_name1", "admin1@ycp.edu", "HEY GUYS I’M ADMIN ONE LETS HAVE SOME FUN", null);
		User test = thiscontroller.returnUserForUsername("admin1");
		//User test = new User("admin1", "password1", -1, "a_name1", "admin1@ycp.edu", "HEY GUYS I’M ADMIN ONE LETS HAVE SOME FUN");

		//System.out.println(test.getName());
		assertEquals(admin.getName(), test.getName());
		assertEquals(admin.getLoginId(), test.getLoginId());
		assertEquals(admin.getEmail(), test.getEmail());
		assertEquals(admin.getPassword(), test.getPassword());
		assertEquals(admin.getDescription(), test.getDescription());
		assertEquals(admin.getUsername(), test.getUsername());
	}
	
	@Test
	public void testgetUsersGardens() throws SQLException{

		List<Garden> groups = new ArrayList<Garden>();
		groups = thiscontroller.getUsersGardens("admin1");
		assertEquals(groups.get(0).getGarden_name(), "Adams County Garden");

	}
	
	@Test
	public void TestgetGardenID() throws SQLException{
		List<Garden> groups = new ArrayList<Garden>();
		groups = thiscontroller.getGardensbyUserID("admin1");
		assertEquals(groups.get(0).getGarden_name(), "Adams County Garden");
		assertEquals(groups.get(0).getDescription(), "We are the Master Gardeners of this county!");
	}
}


