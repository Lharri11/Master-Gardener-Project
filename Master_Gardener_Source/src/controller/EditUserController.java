package controller;

//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

import database.core.*;
import model.User;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
//import model.Group;
//import model.Post;

public class EditUserController {

	private IDatabase database = null;

	public EditUserController() {

		// creating DB instance here
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();		
	}

	//public boolean updateUserbyUsername(String username, String newUserName, String description, InputStream inputStream) throws IOException {
	public boolean updateUserCredentialsbyUsername(String username, String newUserName, String description) throws IOException {

		User user = database.queryForUserByUsername(username);
		user.setName(newUserName);
		user.setDescription(description);

		//boolean worked = database.updateUser(username, user, inputStream);
		System.out.println("The username is: " + user.getUsername());
		boolean success = database.updateUserCredentials(username, user);

		return success;
	}

	public boolean updatePassword(String username, String old_password, String new_password) throws SQLException
	{
		return database.updatePassword(username, old_password, new_password);
	}
}
