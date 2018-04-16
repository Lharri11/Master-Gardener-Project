package controller;

import database.core.DatabaseProvider;
import database.core.IDatabase;
import database.core.MySQLDatabase;
import model.Garden;
import model.Pollinator;

import java.sql.SQLException;
import java.util.List;

public class ChartController {

    private IDatabase database = null;

    public ChartController(){
        DatabaseProvider.setInstance(new MySQLDatabase());
        database = DatabaseProvider.getInstance();
    }

    public List<Pollinator> getAllPollinators() throws SQLException {
        return this.database.getAllPollinators();
    }
}
