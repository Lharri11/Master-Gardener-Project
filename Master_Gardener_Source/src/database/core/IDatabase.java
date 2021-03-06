package database.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.*;
import model.*;

public interface IDatabase {
	public boolean createTables();
	public boolean dropTables();
	public Connection connect() throws SQLException;
	public void loadInitialData();

	// Master Gardener Program methods
	//========================================================================================================
	// Implemented Query Methods
	// TODO: ↓↓↓ CREATE CUSTOM QUERY EXPORT ONLY EXPORTS TO ONE SPECIFIC FOLDER (MAYBE), CHANGE THIS SO USER SELECTS LOCATION ↓↓↓
	public String createCustomQuery(final String[] select_array, final String[] from_array, final String[] where_array, final boolean export);
	public int getGardenIDByGardenName(final String garden_name) throws SQLException;
	public int getCountyIDByGardenName(final String garden_name) throws SQLException;
	public int getCountyIDByCountyName(final String county_name) throws SQLException;
	public int getPlantIDByPlantName(final String plant_name) throws SQLException;
	public int getStrainIDByStrainName(final String strain_name) throws SQLException;
	public int getPollinatorIDByPollinatorName(final String poll_name) throws SQLException;
	public Pollinator getPollinatorByPollinatorID(final int pollinator_id) throws SQLException;
	public String getPollinatorNameByPollinatorID(final int pollinator_id) throws SQLException;
	public String getCountyByCountyID(final int county_id);
	public String getCountyByStateName(final String state_name);
	public User getUserFromUserID(final int user_id) throws SQLException;
	public User getUserFromUserName(final String user_name) throws SQLException;
	public int getUserIDFromFirstNameAndLastName(final String first_name, final String last_name) throws SQLException;
	public ArrayList<String> getUsernamesFromFirstNameAndLastName(String first_name, String last_name) throws SQLException;
	public String getGardenAddressByGardenID(final int garden_id);
	public List<User> getAllUsernames() throws SQLException;
	public List<Garden> getAllGardens() throws SQLException;
	public List<Pollinator> getAllPollinators() throws SQLException;
	public List<String> getAllPlants() throws SQLException;
	public List<String> getAllStrains() throws SQLException;
	public List<Integer> getVisitCountsByPollinator() throws SQLException;
	public List<Integer> getVisitCountsByPollinatorAndStrainID(final int strain_id) throws SQLException;
	public List<Integer> getVisitCountsByPollinatorAndCountyID(final int county_id) throws SQLException;
	public Pair<Pair<List<Integer>, List<String>>, List<Date>> getVisitCountAndPollinatorNameAndDateByCounty(final County county) throws SQLException;
	public List<Integer> getVisitCountsByCounty(final County county) throws SQLException;
	public List<String> getPollinatorsNameByCounty(final County county) throws SQLException;
	public List<Date> getDatesByCounty(final County county) throws SQLException;
	// ↓↓↓ This is not implemented ↓↓↓
	public List<String> getAllPollinatorsPlantsGardenVisitCount() throws SQLException;
	public ResultSet adHocQuery(final String[] select_array, final String[] from_array, final String[] where_array) throws SQLException;
	public List<Garden> getGardensByUser(String user);
	public List<County> getAllCounties() throws SQLException;
	public List<Garden> getGardensLikeKeyword(String keyword);
	public List<Garden> getGardenbyGardenName(String name);
	public Garden getGardenbyGardenID(int iD);
	public  List<Pair<User, Post>> getPostsbyGardenID(int iD);
	public int queryForLoginIdByUsername(String username);
	//public User queryForUserAccountByUsername(String username); // TODO:: ADD this method
	public String queryForPasswordByUsername(String username);
	public List<Integer> getAllDataFormIDs() throws SQLException;
	public List<Integer> getAllVisitCountIDs() throws SQLException;
	public List<Integer> getUnconfirmedDataformIDsByCounty(String county) throws SQLException;
	public List<PollinatorDataForm> getUnconfirmedDataformsByCounty(String county) throws SQLException;
	public String hashString(String password) throws SQLException;
	public int getModeratorStatusByUsername(String username) throws SQLException;
	public ArrayList<Integer> getGeneratorListByUsernames(String[] names) throws SQLException;
	public boolean checkPasswordByUsername(String username, String password)throws SQLException;
	public ArrayList<String> getPlantsFromGarden(Garden garden) throws SQLException;
	public int getNumberOfPlantsFromGarden(Garden garden) throws SQLException;
	public ArrayList<String> getPollinatorsFromGarden(Garden garden) throws SQLException;
	public List<String> getCountiesWithUnconfirmedDataforms () throws SQLException;
	//========================================================================================================


	//========================================================================================================
	// Implemented Update methods
	public boolean updatePollinatorName(final String pollinator_name, final String updated_name) throws SQLException;
	public boolean updatePollinatorTypeByName(final String pollinator_name, final String updated_type) throws SQLException;
	public boolean updatePlantName(final String plant_name, final String updated_name) throws SQLException;
	public boolean updateGardenName(final String current_name, final String updated_name) throws SQLException;
	public boolean updateGardenDescriptionByName(final String garden_name, final String updated_desc) throws SQLException;
	public boolean updateStrainName(final String current_name, final String updated_name) throws SQLException;
	public boolean updatePlotHeight(final double updated_height, final int plot_id) throws SQLException;
	public boolean updatePlotArea( final double updated_area, final int plot_id ) throws SQLException;
	public boolean updatePlotPercentCoverage( final double updated_percent, final int plot_id ) throws SQLException;
	public boolean updatePlotBloomsOpen( final String updated_bo, final int plot_id) throws SQLException;
	public boolean updatePlotVigorStatus( final String updated_vigor, final int plot_id) throws SQLException;
	// ↓↓↓ Writing test for this required me to not be lazy ↓↓↓
	public boolean updatePollinatorVisitCount( final int dataform_id, final int pollinator_id, final int plant_id, final int updated_count) throws SQLException;
	// TODO: ↓↓↓ !!! NEED TO CHECK THE VALUES TO ENSURE NO MALICIOUS USERS UPDATE THINGS !!! ↓↓↓
	public boolean updateXTupleFromYTable(final String table, final String[] column, final String[] col_value, final String[] where) throws SQLException;
	// TODO: Create More Update Tables
	// Maybe something like updatePlantStrainByPlant?
	public boolean updatePassword(String user_name, String old_password, String new_password) throws SQLException;
	//========================================================================================================

	//========================================================================================================
	// Implemented Insert methods
	public boolean insertDataFromDataform(final PollinatorDataForm pdf) throws SQLException;
	public boolean insertNewGardenIntoDatabase(Garden garden);
	public boolean insertNewPostIntoDatabase(Post post);
	public boolean insertNewGardenMemberIntoDatabase(GardenMember gardenMember);
	public boolean insertPollinatorIntoTable(final Pollinator poll) throws SQLException;
	public boolean insertPlantStrainIntoTable(final PlantStrain ps) throws SQLException;
	public boolean insertPlantIntoTable(final Plant plant) throws SQLException;
	// TODO: Create More Insert-Into Methods
	//========================================================================================================

	//========================================================================================================
	// Implemented Delete Methods
	public boolean deleteUserFromDatabase(final User user) throws SQLException;
	public boolean deletePollinatorFromDatabase(final Pollinator poll) throws SQLException;
	public boolean deleteStrainFromDatabase(final PlantStrain strain) throws SQLException;
	public boolean deletePlantFromDatabase(final Plant plant) throws SQLException;
	public boolean deleteGardenFromDatabase(final Garden garden) throws SQLException;
	// ↓↓↓ Writing test for this required me to not be lazy ↓↓↓
	public boolean deleteDataformFromDatabase(final PollinatorDataForm pdf) throws SQLException;
	// TODO: ↓↓↓ !!! NEED TO CHECK THE VALUES TO ENSURE NO MALICIOUS USERS UPDATE THINGS !!! ↓↓↓
	public boolean deleteXTuplefromYTable(final String table, final String[] where)throws SQLException;
	// TODO: Create More Delete-From Methods
	public boolean deleteUserFromGarden(User user, Garden garden) throws SQLException;
	//========================================================================================================


	public User queryForUserByUsername(String username);

	public boolean insertNewUserIntoDatabase(User newb);


	// Master Gardener Program methods
	public int getGardenIDByUserID(final int account_ID);
	public boolean verifyGardenExistsbyGardenName(final String garden_name) throws SQLException;
	public boolean verifyUserHasPortrait(final String username) throws IOException, SQLException;
	//public boolean verifyUserHasPortrait(final String username) throws IOException, SQLException;
	public boolean updateUserCredentials(String username, User user);
	public boolean updateUserPortrait(String username, User user, String filePath);
	public boolean insertUserPortrait(String username, User user, String filePath);
	public boolean insertGardenDocument(String garden, int garden_id, String filePath) throws SQLException;


	// get plant by garden
	// get strain by plant
	//
    public boolean updatePollinatorVisitPlotIDs() throws SQLException;
	public List<String> getStrainByPlant(final Plant plant) throws SQLException;
	public String getPlantNameByGarden(final Garden garden) throws SQLException;
	public Plant getPlantByPlantID(final int plant_id) throws SQLException;
	public PlantStrain getStrainByStrainID(final int strain_id) throws SQLException;
	public Plot getPlotByGardenIDAndStrainID(final int garden_id, final int strain_id) throws SQLException;
	public int queryForVisitCountByFieldsFromPollVisit(final int data_form_id, int pollinator_id, int plant_id, int strain_id);
	public String saltPassword(String username, String password) throws SQLException;
}
