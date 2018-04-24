package controller;

import database.core.DatabaseProvider;
import database.core.MySQLDatabase;
import database.core.IDatabase;
import model.PollinatorDataForm;
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

        System.out.println("Active Accountss:");
        if (activeAccounts.isEmpty()) {
            System.out.println("No active accounts. This shouldn't appear.....come'on you know why :P");
            return null;
        } else {
            return activeAccounts;
        }

    }

    public List<String> getAllUnconfirmedDataForm() throws SQLException {
        List<String> unconfirmed =  database.getUnconfirmedDataFormsByCounty("Adams");
                //database.getUnconfirmedDataformsByCounty("Adams");
        if (unconfirmed.isEmpty()) {
            System.out.println("Empty");
            return null;
        } else {
            System.out.println("Returned");
            return unconfirmed;
        }
    }

    public boolean updatePollinatorVisitCountPlotIDs()
    {
        boolean success = false;
        try{
            success = database.updatePollinatorVisitPlotIDs();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return success;
    }
}