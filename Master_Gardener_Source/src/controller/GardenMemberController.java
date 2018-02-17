package controller;

import database.core.*;
import model.GardenMember;

public class GardenMemberController {
	
	private IDatabase database = null;

	public GardenMemberController(){
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();
	}
	
	public boolean addGardenMember(GardenMember groupMember){
		return this.database.insertNewGardenMemberIntoDatabase(groupMember);
	}
}
