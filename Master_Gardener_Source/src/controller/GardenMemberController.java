package controller;

import database.core.*;
import model.GardenMember;

import java.sql.SQLException;

public class GardenMemberController {
	
	private IDatabase database = null;

	public GardenMemberController() throws SQLException {
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();
	}
	
	public boolean addGardenMember(GardenMember groupMember){
		return this.database.insertNewGardenMemberIntoDatabase(groupMember);
	}
}
