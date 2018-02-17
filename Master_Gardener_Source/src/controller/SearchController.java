package controller;
import model.Garden;

import java.sql.SQLException;
import java.util.List;
import database.core.*;
 
public class SearchController {

	private IDatabase database = null;

	public SearchController() throws SQLException {

		// creating DB instance here
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();		
	}

	public List<Garden> getGardensLike(String keyword) throws SQLException {
		
		List<Garden> gardens = database.getGardensLikeKeyword(keyword);
		if (gardens.isEmpty()) {
			System.out.println("No gardens found that match the keyphrase provided");
			return null;
		}
		else {
			for(int x = 0; x < gardens.size(); x++){
				System.out.println("Matching gardens:");
				System.out.println(gardens.get(x).getGarden_name());
			}
			return gardens;
		}			
	}

	public int getGardenIDbyGardenname(String name) {
		List<Garden> garden = database.getGardenbyGardenName(name);
		int gardenID = 0;
		String gardenName;
		gardenID = garden.get(0).getGarden_id();
		gardenName = garden.get(0).getGarden_name();
		System.out.println("Redirecting to "+ gardenName);
		return gardenID;
	}
}