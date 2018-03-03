// Alex - Use this to create forms later eventually. 
package controller;

import database.core.*;
import model.PollinatorDataForm;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataFormController
{
	private IDatabase database = null;

	public DataFormController() {
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();
	}

	public boolean createDataInput(PollinatorDataForm pdf) throws SQLException {
		return this.database.insertDataFromDataform(pdf);
	}

	public ArrayList<Integer> getGeneratorListByUsernames(String[] names) throws SQLException
	{
		return database.getGeneratorListByUsernames(names);
	}

}
