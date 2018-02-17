package controller;

//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

import database.core.DatabaseProvider;
import database.core.IDatabase;
import database.core.MySQLDatabase;
import model.Garden;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
//import model.Group;
//import model.Post;

public class UploadController {

	private IDatabase database = null;

	public UploadController() throws SQLException {

		// creating DB instance here
		DatabaseProvider.setInstance(new MySQLDatabase());
		database = DatabaseProvider.getInstance();		
	}

	public boolean insertDocumentByGardenID(String root, int root_id, String filePath) throws SQLException {

		boolean worked = database.insertGardenDocument(root, root_id, filePath);

		return worked;
	}
}
