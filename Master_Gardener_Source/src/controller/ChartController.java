package controller;

import database.core.DatabaseProvider;
import database.core.IDatabase;
import database.core.MySQLDatabase;
import model.County;
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

    //
    /* -------------------- VISIT COUNT QUERIES --------------------*/
    //

    //Pollinators
    public List<Pollinator> getAllPollinators() throws SQLException {
        return this.database.getAllPollinators();
    }

    //Counties
    public List<County> getAllCounties() throws SQLException {
        return this.database.getAllCounties();
    }

    //Visit Counts for all Pollinators
    public List<Integer> getVisitCountsByPollinator() throws SQLException {
        return this.database.getVisitCountsByPollinator();
    }

    //Visit Counts by Strain ID
    public List<Integer> getVisitCountsByPollinatorAndStrainID(int strain_id) throws SQLException {
        return this.database.getVisitCountsByPollinatorAndStrainID(strain_id);
    }

    //Visit Counts by County ID
    public List<Integer> getVisitCountsByPollinatorAndCountyID(int county_id) throws SQLException {
        return this.database.getVisitCountsByPollinatorAndCountyID(county_id);
    }
}
