package controller;

import database.core.DatabaseProvider;
import database.core.MySQLDatabase;
import database.core.IDatabase;
import model.User;

import java.sql.SQLException;

public class SignupController {
	
	private IDatabase database = null;

	public SignupController() throws SQLException {
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();
	}
	
	public boolean createUser(User user){
		return this.database.insertNewUserIntoDatabase(user);
	}
}
