// Alex - Use this to create forms later eventually. 
package controller;

import database.core.*;
import model.Plant;
import model.PlantStrain;
import model.PollinatorDataForm;
import model.User;

import java.sql.SQLException;

public class DataFormController
{
	private IDatabase database = null;

	public DataFormController() {
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();
	}

    public int getGardenIDByGardenName(final String garden_name) throws SQLException {
        return this.database.getGardenIDByGardenName(garden_name);
    }

    public int getPlantIDByPlantName(final String plant_name) throws SQLException {
        return this.database.getPlantIDByPlantName(plant_name);
    }

	public int getStrainIDByStrainName(final String strain_name) throws SQLException {
	    return this.database.getStrainIDByStrainName(strain_name);
    }

    public Plant getPlantByPlantID(final int plant_id) throws SQLException {
        return this.database.getPlantByPlantID(plant_id);
    }

    public PlantStrain getStrainByStrainID(final int strain_id) throws SQLException {
        return this.database.getStrainByStrainID(strain_id);
    }

    public int getCountyIDByGardenName(final String garden_name) throws SQLException {
        return this.database.getCountyIDByGardenName(garden_name);
    }

    public User getUserFromUserName(final String user_name) throws SQLException {
        return this.database.getUserFromUserName(user_name);
    }

    public User getUserFromUserID(final int user_id) throws SQLException {
        return this.database.getUserFromUserID(user_id);
    }

	public boolean createDataInput(PollinatorDataForm pdf) throws SQLException {
		return this.database.insertDataFromDataform(pdf);
	}
}
