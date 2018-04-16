package controller;

import database.core.DatabaseProvider;
import database.core.IDatabase;
import database.core.MySQLDatabase;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

//import java.sql.Connection;
//import java.util.Random;

public class ChartGenControllerTest {

	private IDatabase db = null;
	private ChartController thiscontroller = new ChartController();
	
	
	@Before
	public void setUp() throws Exception {
		
		DatabaseProvider.setInstance(new MySQLDatabase());
		db = DatabaseProvider.getInstance();	
//		db.dropTables();
//		db.createTables();
//		db.loadInitialData();
	}
	
	@Test
	public void TestChartGen() throws SQLException{

		// FIXME
	//int VisitCount = thiscontroller.getVisitCountByPollinatorVisitFields(2,1,1,4);

	//assertEquals(10,VisitCount);


		// FIXME
	// test to fail strain id 100000 shoudl not exist in data base so it should return -1
	//int VisitFail =  thiscontroller.getVisitCountByPollinatorVisitFields(1,1,1,100000);

	//assertEquals(-1,VisitFail);


	}












	

	
	
	
	
	
	
}


