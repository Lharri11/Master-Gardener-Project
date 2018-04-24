// Alex - Use this to create forms later eventually. 
package controller;

import database.core.*;
import model.*;

import java.sql.SQLException;
import java.util.List;

public class DataFormController
{
	private IDatabase database = null;

	public DataFormController() {
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();
	}

    public List<Integer> getAllDataFormIDs() throws SQLException {
        return this.database.getAllDataFormIDs();
    }

    public List<Integer> getAllVisitCountIDs() throws SQLException {
        return this.database.getAllVisitCountIDs();
    }

    public int getGardenIDByGardenName(final String garden_name) throws SQLException {
        return this.database.getGardenIDByGardenName(garden_name);
    }

    public int getPlantIDByPlantName(final String plant_name) throws SQLException {
        return this.database.getPlantIDByPlantName(plant_name);
    }

    public Plant getPlantByPlantID(final int plant_id) throws SQLException {
        return this.database.getPlantByPlantID(plant_id);
    }

	public int getStrainIDByStrainName(final String strain_name) throws SQLException {
	    return this.database.getStrainIDByStrainName(strain_name);
    }

    public PlantStrain getStrainByStrainID(final int strain_id) throws SQLException {
        return this.database.getStrainByStrainID(strain_id);
    }

    public Plot getPlotByGardenIDAndStrainID(final int garden_id, final int strain_id) throws SQLException {
        return this.database.getPlotByGardenIDAndStrainID(garden_id, strain_id);
    }


    public int getPollinatorIDByPollinatorName(final String pollinator_name) throws SQLException {
        return this.database.getPollinatorIDByPollinatorName(pollinator_name);
    }

    public Pollinator getPollinatorByPollinatorID(final int pollinator_id) throws SQLException {
        return this.database.getPollinatorByPollinatorID(pollinator_id);
    }

    public int getCountyIDByGardenName(final String garden_name) throws SQLException {
        return this.database.getCountyIDByGardenName(garden_name);
    }

    public User getUserFromUserID(final int user_id) throws SQLException {
        return this.database.getUserFromUserID(user_id);
    }

    public User getUserFromUserName(final String user_name) throws SQLException {
        return this.database.getUserFromUserName(user_name);
    }

    public int getUserIDFromFirstNameAndLastName(final String first_name, final String last_name) throws SQLException {
        return this.database.getUserIDFromFirstNameAndLastName(first_name, last_name);
    }

	public boolean createDataInput(PollinatorDataForm pdf) throws SQLException {
		return this.database.insertDataFromDataform(pdf);
	}
}
