// Alex - This class was previously known as "SearchController" but was taken over by me. It is now data controller
// and will serve my purposes.

package controller;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;


import database.core.*;
import model.Garden;
 
public class DataController {

	private IDatabase database = null;

	public DataController() throws SQLException {

		// creating DB instance here
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();		
	}

	/* Alex - Here are some example queries we'll likely need. We'll need more.
	getPlantByID()
	getPlantByStrand()
	getPollinatorByID()
	getPollinatorByVisitCount()
	getWeekByWeekNumber()
	getWeeksByDate()
	getAllDataByCounty()
	*/
	
	public List<Garden> getGardensLike(String keyword) throws SQLException {
		System.out.println("reached controller with keyword "+keyword);
		// get the list of (Author, Book) pairs from DB
		List<Garden> gardens = database.getGardensLikeKeyword(keyword);
		for(int x = 0; x < gardens.size(); x++){
			System.out.println(gardens.get(x).getGarden_name());
		}

		if (gardens.isEmpty()) {
			System.out.println("No gardens in database like that keyword");
			return null;
		}
		else {
			return gardens;
		}			

	}
}