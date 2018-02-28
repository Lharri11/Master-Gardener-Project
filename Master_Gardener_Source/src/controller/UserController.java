package controller;
import model.Garden;

import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;


import database.core.DatabaseProvider;
import database.core.MySQLDatabase;
import database.core.IDatabase;
import model.User;
//import model.ObjectHandler;
 
public class UserController {

	private IDatabase database = null;

	public UserController() {
		// creating DB instance here
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();		
	}

	public User returnUserForUsername(String username){
		return this.database.queryForUserByUsername(username);
	}
	
	public List<Garden> getUsersGardens(String user) throws SQLException {
		List<Garden> gardens = database.getGardensByUser(user);
		System.out.println("User's Gardens:");
		for(int x = 0; x < gardens.size(); x++)
		{
			if(x == gardens.size()){
				System.out.println(gardens.get(x).getGarden_name() + "\n");
			}
			else{
				System.out.println(gardens.get(x).getGarden_name());
			}
		}
		if (gardens.isEmpty()) {
			System.out.println("No gardens in database for that User");
			return null;
		}
		else {
			return gardens;
		}			
	}
	
	public int getGardenIDbyGardenname(String name) throws SQLException{
		List<Garden> garden = database.getGardenbyGardenName(name);
		int gardenID = 0;
		gardenID = garden.get(0).getGarden_id();
		System.out.println("Redirecting to Garden ID #"+gardenID);
		return gardenID;
	}

	public int getUserModeratorStatus(String username) throws SQLException
	{
		return database.getModeratorStatusByUsername(username);
	}
}
