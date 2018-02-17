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

import static org.junit.Assert.assertEquals;

//import java.sql.Connection;
//import java.util.Random;

public class CreateGardenControllerTest {

	private IDatabase db = null;
	private CreateGardenController ThisGardenController = new CreateGardenController();

	public CreateGardenControllerTest() throws SQLException {
	}

	@Before
	public void setUp() throws Exception {
		
		DatabaseProvider.setInstance(new MySQLDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	/*@Test
	public void TestCreateNewGarden() throws SQLException{
		Garden Test = new Garden("The Best Garden Ever", "Hot Damn this is my favorite Garden");

		Boolean worked = ThisGardenController.createGarden(Test);

		assertEquals(true,worked);

	}*/
}


