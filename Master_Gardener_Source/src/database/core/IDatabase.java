package database.core;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.*;
import model.User;

import javax.servlet.http.Part;

public interface IDatabase {
	public boolean createTables();
	public boolean dropTables();
	public Connection connect() throws SQLException;
	public void loadInitialData();
	public int queryForLoginIdByUsername(String username);
	public User queryForUserByUsername(String username);
	public String queryForPasswordByUsername(String username);
	public List<Garden> getGardensByUser(String user);
	public boolean insertNewUserIntoDatabase(User newb);
	public boolean insertNewGardenIntoDatabase(Garden garden);
	public List<Garden> getGardensLikeKeyword(String keyword);
	public List<Garden> getGardenbyGardenName(String name);
	public Garden getGardenbyGardenID(int iD);
	public List<Pair<User, Post>> getPostsbyGardenID(int iD);
	public boolean insertNewPostIntoDatabase(Post post);
	public boolean insertNewGardenMemberIntoDatabase(GardenMember gardenMember);

	// Master Gardener Program methods
	public int getGardenIDByUserID(final int account_ID);
	public String getCountyByCountyID(final int county_id);
	public String getCountyByStateName(final String state_name);
	public String getUsernameByCounty(final String county_name);
	public String getGardenAddressByGardenID(final int garden_id);
	public String getUsernameByCountyID(final int county_id);
	public List<String> getStringByWildcardQuery(final String[] select_array, final String[] from_array, final String[] where_array);
	public boolean deleteUserFromDatabase(User user) throws SQLException;
	public boolean verifyGardenExistsbyGardenName(final String garden_name) throws SQLException;
	public boolean verifyUserHasPortrait(final String username) throws IOException, SQLException;
	public boolean updateUserCredentials(String username, User user);
	public boolean updateUserPortrait(String username, User user, String filePath);
	public boolean insertUserPortrait(String username, User user, String filePath);
	public boolean insertGardenDocument(String garden, int garden_id, String filePath) throws SQLException;
}
