package controller;

import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

import database.core.*;
import model.*;
import model.User;

public class GardenController {

	private IDatabase database = null;

	public GardenController() throws SQLException {
		
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();		
	}

	public User returnUserForUsername(String username){
		return this.database.queryForUserByUsername(username);
	}

	public List<Garden> getUsersGardens(String user) throws SQLException {
		List<Garden> gardens = database.getGardensByUser(user);

		if (gardens.isEmpty()) {
			System.out.println("No gardens in database for that User");
			return null;
		}
		else {
			return gardens;
		}
	}

	public Garden getGardenbyID(final int ID) throws SQLException {
		Garden garden = database.getGardenbyGardenID(ID);
		String this_garden_name = garden.getGarden_name();
		Boolean exists = database.verifyGardenExistsbyGardenName(this_garden_name);
		if(!exists){
			System.out.println("No gardens in database for that ID");
			return null;
		}
		else{
			return garden;
		}
	}
	
	public List<Pair<User, Post>> getPostsbyGardenID(int ID) {
		List<Pair<User, Post>> posts = database.getPostsbyGardenID(ID);
		if(posts.isEmpty()){
			System.out.println("No Posts for Garden");
			return null;
		}
		else{
			return posts;
		}
	}

	public List<Garden>getGardensbyUserID(String username) {
		List<Garden> gardens = database.getGardensByUser(username);
		if(gardens.isEmpty()){
			System.out.println("No gardens for User");
			return null;
		}
		else{
			return gardens;
		}
	}

	public boolean addPost(String comments, int garden, String username){
		boolean worked = false;
		Garden thisgarden = database.getGardenbyGardenID(garden);
		User thisuser = database.queryForUserByUsername(username);
		Post post = new Post(comments, thisuser.getUserId(), thisgarden.getGarden_id());
		worked = database.insertNewPostIntoDatabase(post);
		return worked;
	}
	
	public boolean addGardenMember(int garden, String username){
		boolean worked = false;
		Garden thisgarden = database.getGardenbyGardenID(garden);
		User thisuser = database.queryForUserByUsername(username);
		GardenMember gardenMember = new GardenMember(thisgarden.getGarden_id(), thisuser.getUserId());
		worked = database.insertNewGardenMemberIntoDatabase(gardenMember);
		return worked;
	}
}
