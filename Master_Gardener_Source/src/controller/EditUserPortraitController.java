package controller;

//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

import database.core.DatabaseProvider;
import database.core.IDatabase;
import database.core.MySQLDatabase;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
//import model.Group;
//import model.Post;

public class EditUserPortraitController {

	private IDatabase database = null;

	public EditUserPortraitController() throws SQLException {

		// creating DB instance here
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();		
	}

	//public boolean updateUserbyUsername(String username, String newUserName, String description, InputStream inputStream) throws IOException {
	public boolean insertUserPortraitbyUsername(String username, String filePath) throws IOException {

		User user = database.queryForUserByUsername(username);

		//boolean worked = database.updateUser(username, user, inputStream);
		boolean worked = database.insertUserPortrait(username, user, filePath);

		return worked;
	}

	public boolean updateUserPortraitbyUsername(String username, String filePath) throws IOException {

		User user = database.queryForUserByUsername(username);

		boolean success = database.updateUserCredentials(username, user);

		return success;
	}

	public boolean verifyUserHasPortrait(String username) throws IOException, SQLException {

		boolean success = database.verifyUserHasPortrait(username);

		return success;
	}
}
