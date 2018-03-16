package controller;

import database.core.DatabaseProvider;
import database.core.MySQLDatabase;
import database.core.IDatabase;
import model.User;

import java.sql.SQLException;
import java.util.List;

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

    public List<User> getAllUsernames() throws SQLException {
        List<User> activeAccounts = database.getAllUsernames();

        System.out.println("Active Accounts:");
        /*
        Formatter formatter = new Formatter();
        System.out.println(formatter.format("%20s %30s %20s", "Username*", "Email*", "Name*"));
        for (int i = 0; i < activeAccounts.size(); i++) {
            System.out.format(activeAccounts.get(i) + " ");
        } */

        if (activeAccounts.isEmpty()) {
            System.out.println("No active accounts. This shouldn't appear.....come'on you know why :P");
            return null;
        } else {
            return activeAccounts;
        }

    }

}