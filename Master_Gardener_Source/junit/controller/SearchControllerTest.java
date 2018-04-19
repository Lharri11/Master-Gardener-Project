package controller;

import database.core.DatabaseProvider;
import database.core.IDatabase;
import database.core.MySQLDatabase;
import model.Garden;
import model.GardenMember;
import model.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//import java.sql.Connection;
//import java.util.Random;

public class SearchControllerTest {

	private IDatabase db = null;
	private SearchController thiscontroller = new SearchController();
	private EditUserController someController = new EditUserController();
	private UserController someController1 = new UserController();
	private GardenMemberController ThisGardenController = new GardenMemberController();



	@Before
	public void setUp() throws Exception {
		
		DatabaseProvider.setInstance(new MySQLDatabase());
		db = DatabaseProvider.getInstance();

	}
	
/*	@Test
	public void TestSearchFunctions() throws SQLException{

		List<Garden> gardens = thiscontroller.getGardensLike("Clinton County Garden");
		//Garden Test = new Garden("The Best Garden Ever", "Hot Damn this is my favorite Garden");

		assertNotNull(gardens);

		//test to fail
		List<Garden> gardensFail = thiscontroller.getGardensLike("rumpelstiltskin");
		assertNull(gardensFail);
	}*/
	

}


