package controller;

import database.core.DatabaseProvider;
import database.core.MySQLDatabase;
import database.core.IDatabase;
import model.User;

import java.sql.SQLException;

public class AdminController {

    private IDatabase database = null;

    public AdminController() {
        DatabaseProvider.setInstance(new MySQLDatabase());
        database = DatabaseProvider.getInstance();
    }

    public boolean createUser(User user){
        return this.database.insertNewUserIntoDatabase(user);
    }
    public boolean deleteUserByUsername(User user) throws SQLException { return this.database.deleteUserFromDatabase(user); }
    public User findUserByUsername(String username) {return this.database.queryForUserByUsername(username);}
}
