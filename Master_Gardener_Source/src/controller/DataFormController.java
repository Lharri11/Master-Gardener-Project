// Alex - Use this to create forms later eventually. 
package controller;

import database.core.*;
import model.PollinatorDataForm;

import java.sql.SQLException;

public class DataFormController
{
	private IDatabase database = null;

	public DataFormController() {
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();
	}

	public int getStrainIDByStrainName(final String strain_name) throws SQLException {
	    return this.database.getStrainIDByStrainName(strain_name);
    }

	public boolean createDataInput(PollinatorDataForm pdf) throws SQLException {
		return this.database.insertDataFromDataform(pdf);
	}
}
