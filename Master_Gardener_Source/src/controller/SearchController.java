package controller;
import model.Garden;

import java.sql.SQLException;
import java.util.List;
import database.core.*;
import model.GardenMember;
import model.User;

public class SearchController {

	private IDatabase database = null;

	public SearchController() {

		// creating DB instance here
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();
	}

	public User returnUserForUsername(String username){
		return this.database.queryForUserByUsername(username);
	}


	public List<Garden> getAllGardens() throws SQLException {


		List<Garden> gardens = database.getAllGardens();

		if (gardens.isEmpty()) {
			System.out.println("No gardens found");
			return null;
		} else {
			return gardens;
		}
	}

	public List<Garden> removeUserGardensFromList(String user) throws SQLException {
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
			System.out.println("No gardens in database to remove for user");
			return null;
		}
		else {
			return gardens;
		}
	}




	public boolean addNewUsertoGarden(String gardenName, String username) throws SQLException {
		boolean worked = false;
		int thisGardenID = database.getGardenIDByGardenName(gardenName);
		Garden thisGarden = database.getGardenbyGardenID(thisGardenID);
		User thisUser = database.queryForUserByUsername(username);
		GardenMember gardenMember = new GardenMember(thisGarden.getGarden_id(), thisUser.getUserId());
		worked = database.insertNewGardenMemberIntoDatabase(gardenMember);
		return worked;
	}



/*	public int getGardenIDbyGardenname(String name) {
		List<Garden> garden = database.getGardenbyGardenName(name);
		int gardenID = 0;
		String gardenName;
		gardenID = garden.get(0).getGarden_id();
		gardenName = garden.get(0).getGarden_name();
		System.out.println("Redirecting to "+ gardenName);
		return gardenID;
	}*/

}