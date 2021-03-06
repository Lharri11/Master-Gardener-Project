package controller;

import database.core.DatabaseProvider;
import database.core.MySQLDatabase;
import database.core.IDatabase;
import model.Garden;

public class CreateGardenController {
	
	private IDatabase database = null;

	public CreateGardenController(){
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();
	}
	
	public boolean createGarden(Garden garden){
		return this.database.insertNewGardenIntoDatabase(garden);
	}
}
