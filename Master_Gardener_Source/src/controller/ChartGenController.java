package controller;


import database.core.DatabaseProvider;
import database.core.MySQLDatabase;
import database.core.IDatabase;
import model.User;

import java.sql.SQLException;

public class ChartGenController {
    private IDatabase database = null;

    public ChartGenController(){
        DatabaseProvider.setInstance(new MySQLDatabase());
        database = DatabaseProvider.getInstance();
    }

    public int getVisitCountByPollinatorVisitFields(int data_form_id, int pollinator_id, int plant_id, int strain_id) throws SQLException {
        int visitcont = database.queryForVisitCountByFieldsFromPollVisit(data_form_id,pollinator_id,plant_id,strain_id);

        return visitcont;
    }
}