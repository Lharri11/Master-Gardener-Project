package database.core;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import model.*;

@SuppressWarnings("Duplicates")
public class MySQLDatabase implements IDatabase{

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/MasterGardener";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "root";


    public static DataSource getMySQLDataSource() {
        MysqlDataSource mysqlDS;
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(MySQLDatabase.MYSQL_URL);
        mysqlDS.setUser(MySQLDatabase.MYSQL_USERNAME);
        mysqlDS.setPassword(MySQLDatabase.MYSQL_PASSWORD);

        return mysqlDS;
    }

    private static void testDataSource(String dbType) {
        DataSource ds;
        if("mysql".equals(dbType)){
            ds = getMySQLDataSource();
        }else{
            System.out.println("invalid db type");
            return;
        }

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select user_ID, name from mg_user");
            while(rs.next()){
                System.out.println("\nUser ID = "+rs.getInt("user_ID")+", Name = "+rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public interface Transaction<ResultType> {
        ResultType execute(Connection conn) throws SQLException;
    }

    public static final int MAX_ATTEMPTS = 10;

    public interface Query<ReturnType>{
        ReturnType query(Connection conn) throws SQLException;
    }

	/* ----------------------------------------------Query Functions---------------------------------------------- */

    public List<String> getStringByWildcardQuery(final String[] select_array, final String[] from_array, final String[] where_array) {
        for(int i = 0; i < select_array.length; i++)
        {
            if( select_array[i].equals("password")
                    || select_array[i].equals("login_id")  || select_array[i].equals("email") || select_array[i].equals("description"))
            {
                List<String> emptyList = new ArrayList<String>();
                emptyList.add("This list should be empty.");
                System.out.println("User is trying to access data that they shouldn't be. Closing...");
                return emptyList;
            }
        }

        for(int i = 0; i < from_array.length; i++) {
            if (from_array[i].equals("password")
                    || from_array[i].equals("login_id") || from_array[i].equals("email") || from_array[i].equals("description")) {
                List<String> emptyList = new ArrayList<String>();
                emptyList.add("This list should be empty.");
                System.out.println("User is trying to access data that they shouldn't be. Closing...");
                return emptyList;
            }
        }

        for(int i = 0; i < where_array.length; i++)
        {
            if( where_array[i].equals("password")
                    || where_array[i].equals("login_id")  || where_array[i].equals("email") || where_array[i].equals("description"))
            {
                List<String> emptyList = new ArrayList<String>();
                emptyList.add("This list should be empty.");
                System.out.println("User is trying to access data that they shouldn't be. Closing...");
                return emptyList;
            }
        }

        List<String> return_value = new ArrayList<String>();
        try {
            return doQueryLoop(new Query<List<String>>() {
                @Override
                public List<String> query(Connection conn) throws SQLException {
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    String result_string = null;

                    String prepared_statement = "SELECT " + select_array[0];

                    for(int i = 1; i < select_array.length; i++)
                    {
                        prepared_statement += ", " + select_array[i];
                    }

                    prepared_statement += " FROM " + from_array[0];

                    for(int i = 1; i < from_array.length; i++)
                    {
                        prepared_statement += ", " + from_array[i];
                    }

                    if(where_array.length > 0)
                    {
                        prepared_statement += " WHERE " + where_array[0];
                        prepared_statement += " = " + where_array[1];

                        for(int i = 2; i < where_array.length - 1 ; i++)
                        {
                            prepared_statement += " AND " + where_array[i];
                            prepared_statement += " = " + where_array[i+1];
                        }
                    }
                    try
                    {
                        // Setup SQL statement
                        stmt = conn.prepareStatement(prepared_statement);

                        // Send prepared statement results into a set
                        set = stmt.executeQuery();

                        boolean found = false;
                        while(set.next())
                        {
                            found = true;
                            result_string = set.getString(1);
                            return_value.add(result_string);
                        }
                        if(!found)
                        {
                            System.out.println("There was no output for the following query: " +prepared_statement);
                        }
                    }
                    catch(SQLException e)
                    {
                        System.out.println("SQL Exception in Wildcard query: " + e);
                    }
                    finally
                    {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                    }
                    return return_value;
                }
            });
        } catch (SQLException e)
        {
            System.out.println("Error found in getStringByWildcardQuery: " +e);
            return return_value;
        }
    }

    public int queryForLoginIdByUsername(final String username){
        try{
            return doQueryLoop(new Query<Integer>(){
                @Override
                public Integer query(Connection conn) throws SQLException{
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    int loginId = -1;
                    try{
                        stmt = conn.prepareStatement(
                                " SELECT login_id FROM mg_user "
                                        + " WHERE username = ?");
                        stmt.setString(1, username);
                        set = stmt.executeQuery();

                        if(set.next()){
                            loginId = set.getInt(1);
                        }
                    }finally{
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                    }
                    return loginId;
                }
            });
        }catch(SQLException e){
            System.out.println("queryForLoginIdByUsername: "+e.getMessage());
            return -1;
        }
    }

    public int getGardenIDByUserID(final int user_id)
    {
        try {
            return doQueryLoop(new Query<Integer>() {
                @Override
                public Integer query(Connection conn) throws SQLException {
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    int garden_id = -1;
                    try {
                        stmt = conn.prepareStatement(
                                " select garden_id " +
                                        "	from mg_garden, mg_user"
                                        + " where mg_user.user_id = ?" +
                                        "	and mg_garden.user_id = mg_user.user_id");
                        stmt.setInt(1, user_id);
                        set = stmt.executeQuery();

                        // testing that a set was returned
                        Boolean found = false;

                        if (set.next()) {
                            found = true;
                            garden_id = set.getInt(1);
                        }
                        if(!found)
                        {
                            System.out.println("User ID <" + user_id + "> was not found in the accounts table.");
                            System.out.println("Check the SQL code in the query, user_id may need to change.");
                            System.out.println("May also need a many-to-many table to connect gardens <-> accounts.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                    }
                    return garden_id;
                }
            });
        }
        catch (SQLException e) {
            System.out.println("Error in getGardenIDByUserID: " + e.getMessage());
            return -1;
        }
    }

    public String getCountyByCountyID(final int county_id)
    {
        try {
            return doQueryLoop(new Query<String>() {
                @Override
                public String query(Connection conn) throws SQLException {
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    String county_name = "";
                    try {
                        stmt = conn.prepareStatement(
                                " select county_name " +
                                        "	from mg_county"
                                        + " where county_id = ?"
                        );
                        stmt.setInt(1, county_id);
                        set = stmt.executeQuery();

                        // testing that a set was returned
                        Boolean found = false;

                        if (set.next()) {
                            found = true;
                            county_name = set.getString(1);
                        }
                        if(!found)
                        {
                            System.out.println("County name <" + county_name + "> was not found in the counties table.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                    }
                    return county_name;
                }
            });
        }
        catch (SQLException e) {
            return "Error in getCountyByCountyID: " + e.getMessage() + ".";
            //return -1;
        }
    }

    public String getCountyByStateName(final String state_name)
    {
        try {
            return doQueryLoop(new Query<String>() {
                @Override
                public String query(Connection conn) throws SQLException {
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    String county_name = "";
                    try {
                        stmt = conn.prepareStatement(
                                " select county_name " +
                                        "	from mg_county"
                                        + " where state_name = ?"
                        );
                        stmt.setString(1, state_name);
                        set = stmt.executeQuery();

                        // testing that a set was returned
                        Boolean found = false;

                        if (set.next()) {
                            found = true;
                            county_name = set.getString(1);
                        }
                        if(!found)
                        {
                            System.out.println("County name <" + county_name + "> was not found in the counties table.");

                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                    }
                    return county_name;
                }
            });
        }
        catch (SQLException e) {
            return "Error in getCountyByStateName: " + e.getMessage() + ".";
            //return -1;
        }
    }

    public String getUsernameByCounty(final String county_name)
    {
        try {
            return doQueryLoop(new Query<String>() {
                @Override
                public String query(Connection conn) throws SQLException {
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    String username = "";
                    try {
                        stmt = conn.prepareStatement(
                                " select username " +
                                        "	from mg_user, mg_county"
                                        + " where county_name = ? "
                                        + " and mg_user.user_id = mg_county.user_id "
                        );
                        stmt.setString(1, county_name);
                        set = stmt.executeQuery();

                        // testing that a set was returned
                        Boolean found = false;

                        if (set.next()) {
                            found = true;
                            username = set.getString(1);
                        }
                        if(!found)
                        {
                            System.out.println("User <" + username + "> was not found attached to the counties table.");
                            System.out.println("Check SQL code for counties.accounts_id, it may need to be counties.county_user_id");
                            System.out.println("May also need a many-to-many table to connect accounts <-> counties.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                    }
                    return county_name;
                }
            });
        }
        catch (SQLException e) {
            return "Error in getGardenIDByUserID: " + e.getMessage() + ".";
            //return -1;
        }
    }

    public String getGardenAddressByGardenID(final int garden_id)
    {
        try {
            return doQueryLoop(new Query<String>() {
                @Override
                public String query(Connection conn) throws SQLException {
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    String garden_address = "";
                    try {
                        stmt = conn.prepareStatement(
                                " select garden_address " +
                                        "	from mg_garden"
                                        + " where garden_id = ?"
                        );
                        stmt.setInt(1, garden_id);
                        set = stmt.executeQuery();

                        // testing that a set was returned
                        Boolean found = false;

                        if (set.next()) {
                            found = true;
                            garden_address = set.getString(1);
                        }
                        if(!found)
                        {
                            System.out.println("Garden Address <" + garden_address + "> was not found in the gardens table.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                    }
                    return garden_address;
                }
            });
        }
        catch (SQLException e) {
            return "Error in getGardenIDByUserID: " + e.getMessage() + ".";
            //return -1;
        }
    }

    public String getUsernameByCountyID(final int county_id)
    {
        try {
            return doQueryLoop(new Query<String>() {
                @Override
                public String query(Connection conn) throws SQLException {
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    String username = "";
                    try {
                        stmt = conn.prepareStatement(
                                " select username " +
                                        "	from mg_user, mg_county"
                                        + " where mg_county.county_id = ?" +
                                        " and mg_county.user_id = mg_user.user_id"
                        );
                        stmt.setInt(1, county_id);
                        set = stmt.executeQuery();

                        // testing that a set was returned
                        Boolean found = false;

                        if (set.next()) {
                            found = true;
                            username = set.getString(1);
                        }
                        if(!found)
                        {
                            System.out.println("User <" + username + "> was not found related to the counties table.");
                            System.out.println("Check the SQL code in the query, user_id may need to be county_user_id or something.");
                            System.out.println("May also need a many-to-many table to connect accounts <-> counties.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                    }
                    return username;
                }
            });
        }
        catch (SQLException e) {
            return "Error in getGardenIDByUserID: " + e.getMessage() + ".";
            //return -1;
        }
    }

    public String removeUsernameFromUsersTable(final String username)
    {
        return executeTransaction(new Transaction<String>() {
            @Override
            public String execute(Connection conn) throws SQLException {
                DataSource ds = getMySQLDataSource();
                conn = ds.getConnection();

                PreparedStatement stmt1 = null;

                PreparedStatement stmt2 = null;
                PreparedStatement stmt3 = null;
                // PreparedStatement stmt4 = null;
                ResultSet rslt1 = null;

                boolean failed = false;
                Integer user_id = -1;

                try
                {
                    // Get account to be deleted
                    stmt1 = conn.prepareStatement(
                            "select user_id "+
                                    " from mg_account "+
                                    "where mg_account.username = ? "

                    ) ;

                    stmt1.setString(1, username);
                    rslt1 = stmt1.executeQuery();

                    if(rslt1.next())
                    {
                        user_id = rslt1.getInt(1);
                        System.out.println("User "+ username +" found with ID " + user_id +".");
                    }
                    else
                    {
                        System.out.println("Attempting to remove non-existant User...\n It failed?!");
                        failed = true;
                    }

                    if(!failed) {
                        // DELETE DEPENDENCIES FIRST
                        // TODO: CHECK TO SEE IF DEPENDENCY EXISTS FIRST (ie using SQLView)
                        stmt2 = conn.prepareStatement(
                                "delete from mg_garden_member" +
                                        " where mg_garden_member.user_id = ?"
                        );

                        stmt2.setInt(1, user_id);
                        stmt2.executeUpdate();

                        stmt3 = conn.prepareStatement(
                                "delete from mg_account " +
                                        "where user_id = ?"
                        );
                        stmt3.setInt(1, user_id);
                        stmt3.executeUpdate();

                        // Just because we need a return statement, we return the username
                        return username;
                    }else
                    {
                        System.out.println("Returning empty string due to not finding the intended User.");
                        return "";
                    }
                }
                // Close all statements/resultSets
                finally
                {
                    DBUtil.closeQuietly(rslt1);
                    DBUtil.closeQuietly(stmt1);
                    DBUtil.closeQuietly(stmt2);
                    DBUtil.closeQuietly(stmt3);
                }

            }
        });
    }

    public boolean updateUserCredentials(final String username, final User user){
        try{
            return doQueryLoop(new Query<Boolean>(){
                @Override
                public Boolean query(Connection conn)throws SQLException{
                    if(verifyUserExistsByUsername(conn,username))
                        //return updateUserByUsername(conn, username, user, inputStream);
                        return updateUserByUsername(conn, username, user);
                    else
                        return false;
                }
            });
        }catch(SQLException e){
            System.out.println("updateUserByUsername: "+e.getMessage());
            return false;
        }
    }

    public boolean updateUserPortrait(final String username, final User user, final String filePath){
        try{
            return doQueryLoop(new Query<Boolean>(){
                @Override
                public Boolean query(Connection conn)throws SQLException{
                    if(verifyUserExistsByUsername(conn,username))
                        return updateUserPortraitByUsername(conn, username, user, filePath);
                    else
                        return false;
                }
            });
        }catch(SQLException e){
            System.out.println("updateUserByUsername: "+e.getMessage());
            return false;
        }
    }

    public boolean insertUserPortrait(final String username, final User user, final String filePath){
        try{
            return doQueryLoop(new Query<Boolean>(){
                @Override
                public Boolean query(Connection conn)throws SQLException{
                    if(verifyUserExistsByUsername(conn,username))
                        //return updateUserByUsername(conn, username, user, inputStream);
                        return insertUserPortraitByUsername(conn, username, user, filePath);
                    else
                        return false;
                }
            });
        }catch(SQLException e){
            System.out.println("updateUserByUsername: "+e.getMessage());
            return false;
        }
    }

    public List<Garden> getGardenbyGardenName(final String name){
        return executeTransaction(new Transaction<List<Garden>>() {
            public List<Garden> execute(Connection conn) throws SQLException {
                DataSource ds = getMySQLDataSource();
                conn = ds.getConnection();

                PreparedStatement stmt1 = null;

                ResultSet set = null;

                try{
                    stmt1 = conn.prepareStatement("select mg_garden.garden_id, mg_garden.garden_name, mg_garden.description from mg_garden where mg_garden.garden_name = ?");
                    stmt1.setString(1, name);
                    set = stmt1.executeQuery();


                    List<Garden> returnGarden = new ArrayList<Garden>();
                    Boolean found = false;

                    while(set.next()) {
                        found = true;
                        Garden group = new Garden(null, null);

                        loadGarden(group, set, 1);
                        returnGarden.add(group);
                    }
                    if (!found) {
                        System.out.println("<" + name + "> is not a garden");
                    }

                    return returnGarden;
                }
                finally{
                    DBUtil.closeQuietly(set);
                    DBUtil.closeQuietly(stmt1);
                }
            }
        });
    }

    public Garden getGardenbyGardenID(final int ID){
        return executeTransaction(new Transaction<Garden>() {
            public Garden execute(Connection conn) throws SQLException {
                DataSource ds = getMySQLDataSource();
                conn = ds.getConnection();
                PreparedStatement stmt1 = null;
                ResultSet set = null;
                boolean found = false;

                try{
                    stmt1 = conn.prepareStatement(
                            " select mg_garden.garden_id, mg_garden.garden_name, mg_garden.description from mg_garden where mg_garden.garden_id = ? "
                    );
                    stmt1.setInt(1, ID);
                    set = stmt1.executeQuery();

                    Garden garden = new Garden(null, null);
                    while(set.next()) {
                        found = true;

                        loadGarden(garden, set, 1);
                    }
                    if (!found) {
                        System.out.println("Garden ID Provided is invalid");
                    }

                    return garden;

                }	finally{
                    DBUtil.closeQuietly(set);
                    DBUtil.closeQuietly(stmt1);
                }
            }
        });
    }

    public List<Pair<User, Post>> getPostsbyGardenID(final int ID) {
        return executeTransaction(new Transaction<List<Pair<User, Post>>>() {
            public List<Pair<User, Post>> execute(Connection conn) throws SQLException {
                DataSource ds = getMySQLDataSource();
                conn = ds.getConnection();

                PreparedStatement stmt1 = null;

                ResultSet set = null;

                try{
                    stmt1 = conn.prepareStatement(
                            " select mg_user.*, mg_post.* from "+
                                    " mg_user, mg_post, mg_garden_member "+
                                    " where mg_post.garden_id = ?  and mg_user.user_id = mg_garden_member.user_id "+
                                    " and mg_garden_member.garden_id = mg_post.garden_id " +
                                    " and mg_user.user_id = mg_post.user_id" +
                                    " order by mg_post.post_id DESC"
                    );

                    stmt1.setInt(1, ID);

                    set = stmt1.executeQuery();

                    List<Pair<User, Post>> returnPosts = new ArrayList<Pair<User, Post>>();
                    //Boolean found = false;

                    while(set.next()) {
                        //found = true;
                        Post post = new Post(null, 0, 0);
                        User user = new User();
                        loadUser(user, set, 1);
                        loadPost(post, set, 9);
                        returnPosts.add(new Pair<User, Post>(user, post));
                    }

                    //if (!found) {
                    //System.out.println("<" + ID + "> is not in the database");
                    //}
                    return returnPosts;
                }	finally{
                    DBUtil.closeQuietly(set);
                    DBUtil.closeQuietly(stmt1);
                }
            }
        });
    }

    public List<Garden> getGardensLikeKeyword(final String keyword){
        return executeTransaction(new Transaction<List<Garden>>() {
            @Override
            public List<Garden> execute(Connection conn) throws SQLException {
                DataSource ds = getMySQLDataSource();
                conn = ds.getConnection();

                PreparedStatement stmt1 = null;

                ResultSet set = null;
                System.out.println("reached DB with keyword "+ keyword);
                try{

                    stmt1 = conn.prepareStatement(
                            "select mg_garden.garden_id, mg_garden.garden_name, mg_garden.description from mg_garden where mg_garden.garden_name like ? "
                    );
                    stmt1.setString(1, "%"+keyword+"%");

                    set = stmt1.executeQuery();

                    List<Garden> returnGardens = new ArrayList<Garden>();
                    //Boolean found = false;

                    while(set.next()) {
                        //found = true;
                        Garden garden = new Garden(null, null);

                        loadGarden(garden, set, 1);
                        returnGardens.add(garden);
                    }

                    //if (!found) {
                    //System.out.println("<" + keyword + "> not found as a valid group");
                    //}
                    return returnGardens;
                }	finally{
                    DBUtil.closeQuietly(set);
                    DBUtil.closeQuietly(stmt1);
                }
            }
        });
    }

    public List<Garden> getGardensByUser(final String user){
        return executeTransaction(new Transaction<List<Garden>>() {
            @Override
            public List<Garden> execute(Connection conn) throws SQLException {
                DataSource ds = getMySQLDataSource();
                conn = ds.getConnection();

                PreparedStatement stmt1 = null;
                ResultSet set = null;

                try{

                    stmt1 = conn.prepareStatement(
                            "select mg_garden.garden_id, mg_garden.garden_name, mg_garden.description from mg_garden, mg_user, mg_garden_member where mg_user.username = ? and mg_user.user_id = mg_garden_member.user_id and mg_garden_member.garden_id = mg_garden.garden_id"
                    );
                    stmt1.setString(1, user);

                    set = stmt1.executeQuery();

                    List<Garden> returnGardens = new ArrayList<Garden>();
                    //Boolean found = false;

                    while(set.next()) {
                        //found = true;
                        Garden garden = new Garden(null, null);

                        loadGarden(garden, set, 1);
                        returnGardens.add(garden);
                    }

                    //if (!found) {
                    //System.out.println("<" + User + "> is not in the database");
                    //}
                    return returnGardens;
                }	finally{
                    DBUtil.closeQuietly(set);
                    DBUtil.closeQuietly(stmt1);
                }
            }
        });
    }

    public String queryForPasswordByUsername(final String username){
        try{
            return doQueryLoop(new Query<String>(){
                @Override
                public String query(Connection conn) throws SQLException{
                    String password;
                    password = getPasswordByUsername(conn,username);
                    return password;
                }
            });
        } catch(SQLException e){
            System.out.println("queryForPasswordByUsername: "+e.getMessage());
            return null;
        }
    }

    public User queryForUserByUsername(final String username){
        try{
            return doQueryLoop(new Query<User>(){
                @Override
                public User query(Connection conn) throws SQLException{
                    User user = null;
                    if(verifyUserExistsByUsername(conn, username)){
                        user = getUserFromUsername(conn,username);
                    }
                    return user;
                }
            });
        }catch(SQLException e){
            System.out.println("queryForUserUserByUsername: "+e.getMessage());
            return null;
        }
    }

    public boolean deleteUserFromDatabase(User user){
        try{
            return doQueryLoop(new Query<Boolean>(){
                @Override
                public Boolean query(Connection conn) throws SQLException{
                    boolean success;
                    if(deleteUserByUsername(conn, user));
                    success = true;
                    return success;
                }
            });
        }catch(SQLException e){
            System.out.println("deleteUserFromDatabase: "+e.getMessage());
            return false;
        }
    }

    public boolean deleteUserByUsername(Connection conn, User user) throws SQLException {
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet set = null;
        boolean success = false;
        try{
            stmt1 = conn.prepareStatement(
                    "DELETE from mg_garden_member "
                            + " WHERE user_id = ? ");
            stmt1.setInt(1, user.getUserId());
            stmt1.executeUpdate();

            /*stmt2 = conn.prepareStatement(
                    "DELETE from mg_post "
                            + " WHERE user_id = ? ");
            stmt2.setInt(1, user.getUserId());
            stmt2.executeUpdate();*/

            stmt3 = conn.prepareStatement(
                    "DELETE from mg_user "
                            + " WHERE username = ? ");
            stmt3.setString(1, user.getUsername());
            stmt3.executeUpdate();

            success = true;
        }catch(SQLException e){
            System.out.println("insertNewUserIntoDatabase: "+e.getMessage());
            return false;
        }finally{
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(stmt3);
            DBUtil.closeQuietly(set);
        }
        return success;
    }

    public boolean insertNewUserIntoDatabase(final User user){
        try{
            return doQueryLoop(new Query<Boolean>(){
                @Override
                public Boolean query(Connection conn) throws SQLException{
                    boolean success = false;
                    if(!verifyUserExistsByUsername(conn, user.getUsername())){
                        if(insertUserIntoUsers(conn, user));
                        success = true;
                    }
                    return success;
                }

            });
        }catch(SQLException e){
            System.out.println("insertNewUserIntoDatabase: "+e.getMessage());
            return false;
        }
    }

    public boolean insertNewGardenIntoDatabase(final Garden garden){
        try{
            return doQueryLoop(new Query<Boolean>(){
                @Override
                public Boolean query(Connection conn) throws SQLException{
                    boolean success = false;
                    if(!verifyGardenExistsbyGardenName(garden.getGarden_name())){
                        if(insertGardenintoGardens(conn,garden));
                        success = true;
                    }
                    return success;
                }
            });
        }catch(SQLException e){
            System.out.println("insertNewGardenIntoDatabase: "+e.getMessage());
            return false;
        }
    }

    public boolean insertNewPostIntoDatabase(final Post post){
        try{
            return doQueryLoop(new Query<Boolean>(){
                @Override
                public Boolean query(Connection conn) throws SQLException{
                    boolean success = false;
                    if(insertPostintoPosts(conn,post)){
                        success = true;
                    }
                    return success;
                }

            });
        }catch(SQLException e){
            System.out.println("insertNewPostIntoDatabase: "+e.getMessage());
            return false;
        }
    }

    public boolean insertNewGardenMemberIntoDatabase(final GardenMember gardenMember){
        try{
            return doQueryLoop(new Query<Boolean>(){
                @Override
                public Boolean query(Connection conn) throws SQLException{
                    boolean success = false;
                    if(!verifyGardenMemberexistsinGarden(conn, gardenMember)){
                        if(insertGardenMemberintoGardenMembers(conn, gardenMember))
                            success = true;
                    }
                    return success;
                }

            });
        }catch(SQLException e){
            System.out.println("insertNewGardenMemberIntoDatabase: "+e.getMessage());
            return false;
        }
    }

    private boolean verifyGardenMemberexistsinGarden(Connection conn, GardenMember TheMember ) throws SQLException {
        boolean success = false;
        PreparedStatement stmt2 = null;
        ResultSet set = null;

        try {
            stmt2 = conn.prepareStatement(
                    "SELECT member_id FROM mg_garden_member "
                            + " WHERE mg_garden_member.garden_id = ? and mg_garden_member.user_id = ?");
            stmt2.setInt(1, TheMember.getGarden_id());
            stmt2.setInt(2, TheMember.getUser_id());
            set = stmt2.executeQuery();
            if (set.next()) {
                success = true;
            }

        } finally {
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
        }
        return success;
    }

    /*
	 * ----------------------- Query Utility Functions----------------------------------------------------
	 */

    private boolean updateUserByUsername(Connection conn, String username, User user) throws SQLException{
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        boolean success = false;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;

        try{
            stmt = conn.prepareStatement(
                    "UPDATE mg_user "
                            + " SET username = ?, description = ? "
                            + " WHERE username = ? ");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getDescription());
            //stmt.setBlob(3, inputStream);
            stmt.setString(3, username);
            stmt.executeUpdate();

            //get user_id
            stmt2 = conn.prepareStatement(
                    "SELECT user_id FROM mg_user "
                            + " WHERE username = ?");
            stmt2.setString(1, user.getUsername());
            set = stmt2.executeQuery();


            success = true;

        }finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
        }
        return success;
    }

    private boolean insertUserPortraitByUsername(Connection conn, String username, User user, String filePath) throws SQLException{
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        boolean success = false;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try{
            stmt = conn.prepareStatement(
                    "INSERT into mg_user_portrait (username, filePath) "
                            + " VALUES(?,?)");
            stmt.setString(1, username);
            stmt.setString(2, filePath);
            stmt.executeUpdate();

            success = true;

        }finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
        return success;
    }

    private boolean updateUserPortraitByUsername(Connection conn, String username, User user, String filepath) throws SQLException{
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        boolean success = false;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;

        try{
            /*stmt = conn.prepareStatement(
                    "DELETE FROM mg_user_portrait" +
                            " where username = ?"
            );

            stmt.setString(1, username);
            stmt.executeUpdate();

            stmt2 = conn.prepareStatement(
                    "INSERT into mg_user_portrait (username, filePath) "
                            + " VALUES(?,?)");
            stmt2.setString(1, filePath);
            stmt2.setString(2, username);
            stmt2.executeUpdate();*/

            stmt2 = conn.prepareStatement(
                    "UPDATE mg_user_portrait "
                            + " SET username = ?, filepath = ? "
                            + " WHERE username = ? ");
            stmt2.setString(1, username);
            stmt2.setString(2, filepath);

            stmt2.executeUpdate();

            success = true;

        }finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
        }
        return success;
    }

    public boolean insertGardenDocument(String root, int root_id, String filepath) throws SQLException{
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        boolean success = false;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try{
            stmt = conn.prepareStatement(
                    "INSERT into mg_document (root, root_id, filepath) "
                            + " VALUES(?,?,?)");
            stmt.setString(1, root);
            stmt.setInt(2, root_id);
            stmt.setString(3, filepath);
            stmt.executeUpdate();

            success = true;

        }finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
        return success;
    }

    private User getUserFromUsername(Connection conn, String username) throws SQLException{
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();

        User user;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try{
            stmt = conn.prepareStatement(
                    " SELECT * FROM mg_user "
                            +" WHERE username=?");
            stmt.setString(1, username);

            set = stmt.executeQuery();
            set.next();

            user = new User(username, username, 0, username, username, username, new byte[1024]);
            loadUser(user, set, 1);

        }finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
        return user;
    }

    private String getPasswordByUsername(Connection conn,String username) throws SQLException{
        DataSource ds = getMySQLDataSource();
        String password = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(" SELECT password FROM mg_user WHERE username=? ");
            stmt.setString(1,username);
            rs = stmt.executeQuery();

            if(rs.next()){
                password = rs.getString(1);
            }
        }finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(rs);
        }
        return password;
    }

    private boolean insertUserIntoUsers(Connection conn, User user) throws SQLException{
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;

        boolean success = false;

        try{
            stmt1 = conn.prepareStatement(
                    "INSERT INTO mg_user (username, password, login_id, name, email, description, portrait) "
                            + " VALUES(?,?,?,?,?,?,?)");
            stmt1.setString(1, user.getUsername());
            stmt1.setString(2, user.getPassword());
            stmt1.setInt(3, user.getLoginId());
            stmt1.setString(4, user.getName());
            stmt1.setString(5, user.getEmail());
            stmt1.setString(6, user.getDescription());

            Blob portrait = new SerialBlob(user.getPortrait());
            stmt1.setBlob(7, portrait);

            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement(
                    "SELECT user_id FROM mg_user "
                            + " WHERE username = ?");
            stmt2.setString(1, user.getUsername());
            set = stmt2.executeQuery();

        }finally{
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
        }
        return success;
    }

    private boolean insertGardenintoGardens(Connection conn, Garden garden) throws SQLException{
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        boolean success = false;

        try{
            stmt1 = conn.prepareStatement(
                    "INSERT INTO mg_garden (garden_name, description) "
                            + " VALUES(?,?)");
            stmt1.setString(1, garden.getGarden_name());
            stmt1.setString(2, garden.getDescription());


            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement(
                    "SELECT garden_id FROM mg_garden "
                            + " WHERE garden_name = ?");
            stmt2.setString(1, garden.getGarden_name());
            set = stmt2.executeQuery();

        }finally{
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
        }
        return success;
    }


    private boolean insertPostintoPosts(Connection conn, Post post) throws SQLException{
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        boolean success = false;

        try{
            stmt1 = conn.prepareStatement(
                    "INSERT INTO mg_post ( user_id, garden_id, comments) "
                            + " VALUES(?,?,?)");
            stmt1.setInt(1, post.getUser_id());
            stmt1.setInt(2, post.getGroupId());
            stmt1.setString(3, post.getComments());



            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement(
                    "SELECT post_id FROM mg_post "
                            + " WHERE comments = ?");
            stmt2.setString(1, post.getComments());
            set = stmt2.executeQuery();

        }finally{
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
        }
        return success;
    }

    private boolean insertGardenMemberintoGardenMembers(Connection conn, GardenMember groupMember) throws SQLException{
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        boolean success = false;

        try{
            stmt1 = conn.prepareStatement(
                    "INSERT INTO mg_garden_member (garden_id, user_id) "
                            + " VALUES(?,?)");
            stmt1.setInt(1, groupMember.getGarden_id());
            stmt1.setInt(2, groupMember.getUser_id());

            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement(
                    "SELECT member_id FROM mg_garden_member "
                            + " WHERE mg_garden_member.garden_id = ? and mg_garden_member.user_id = ?");
            stmt2.setInt(1, groupMember.getGarden_id());
            stmt2.setInt(2, groupMember.getUser_id());
            set = stmt2.executeQuery();
            if(set.next()){
                success = true;
            }

        }finally{
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
        }
        return success;
    }

    private boolean verifyUserExistsByUsername(Connection conn, String username) throws SQLException{
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean registered = false;

        try{
            stmt = conn.prepareStatement("SELECT * from mg_user WHERE username=? ");
            stmt.setString(1, username);
            set = stmt.executeQuery();
            if(set.next()){
                registered = true;
            }
        }finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
        return registered;
    }

    public boolean verifyUserHasPortrait(String username) throws SQLException{
        Connection conn;
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean registered = false;

        try{
            stmt = conn.prepareStatement("SELECT * from mg_user_portrait WHERE username=? ");
            stmt.setString(1, username);
            set = stmt.executeQuery();
            if(set.next()){
                registered = true;
            }
        }finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
        return registered;
    }

    public boolean verifyGardenExistsbyGardenName(String garden_name) throws SQLException{
        boolean registered = false;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;

        try{
            stmt = conn.prepareStatement("select * from mg_garden where mg_garden.garden_name = ?");
            stmt.setString(1, garden_name);
            set = stmt.executeQuery();
            if(set.next()){
                registered = true;
            }
        }catch(SQLException se) {
            // Handles DB errors
            se.printStackTrace();
            return false;
        }finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
        return registered;
    }

	/* ---------------------------- Create/Drop Tables & Load Initial Data ------------------------------*/

    @Override
    public boolean createTables()
    {
        Connection conn = null;
        Statement stmt1 = null;
        /*Statement stmt2 = null;
        Statement stmt3 = null;
        Statement stmt4 = null;
        Statement stmt5 = null;
        Statement stmt6 = null;
        //etc */

        try
        {
            Class.forName(DATABASE_DRIVER);
            System.out.println("Connecting to MySQL Database...");
            conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            System.out.println("Success!");

            System.out.println("Creating necessary tables.");
            stmt1 = conn.createStatement();

            stmt1.executeUpdate("CREATE TABLE IF NOT EXISTS mg_account" +
                    "	user_id integer primary key " +
                    "		generated always as identity (start with 1, increment by 1), " +
                    "	username varchar(40)," +
                    "	password varchar(40)," +
                    "   login_id integer," +
                    "	name varchar(20)," +
                    "	email varchar(50)," +
                    "	description varchar(180)"
            );

            System.out.println("Created mg_account table in DB.");

        }
        catch(SQLException se)
        {
            // Handles DB errors
            se.printStackTrace();
            return false;
        }
        catch(Exception e)
        {
            // Handles Class.forName
            e.printStackTrace();
            return false;
        }
        finally {
            DBUtil.closeQuietly(stmt1);
            /*try {
                // TODO: This try block may get bloated with stmt's
                if (stmt1 != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                //TODO: NOTE: Having a return statement within FINALLY may massively complicated debugging.
                return false;
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException se)
            {
                se.printStackTrace();
                //TODO: NOTE: Having a return statement within FINALLY may massively complicated debugging.
                return false;
            }*/
        }
        System.out.println("Finished work in createTables.");
        return true;
    }

    @Override
    public boolean dropTables() {
        return false;
    }

    public Connection connect() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        // Set autocommit to false to allow execution of
        // multiple queries/statements as part of the same transaction.
        conn.setAutoCommit(false);

        return conn;
    }

    @Override
    public void loadInitialData() {

    }

    private <ReturnType> ReturnType doQueryLoop(Query<ReturnType> query) throws SQLException{

        Connection conn = connect();

        ReturnType ret = null;
        int times = 0;
        boolean done = false;
        try{
            while(!done && times < MAX_ATTEMPTS){
                try{
                    ret = query.query(conn);
                    conn.commit();
                    done = true;
                }catch(SQLException e){
                    if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
                        times++;
                    } else {
                        throw e;
                    }
                }
            }

            if (!done) {
                throw new SQLException("Query Failed, TIMEOUT. ");
            }
            return ret;
        }finally{
            DBUtil.closeQuietly(conn);
        }
    }

    public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
        try {
            return doExecuteTransaction(txn);
        } catch (SQLException e) {
            throw new PersistenceException("Transaction failed", e);
        }
    }

    public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
        Connection conn = connect();

        try {
            int numAttempts = 0;
            boolean success = false;
            ResultType result = null;

            while (!success && numAttempts < MAX_ATTEMPTS) {
                try {
                    result = txn.execute(conn);
                    conn.commit();
                    success = true;
                } catch (SQLException e) {
                    if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
                        // Deadlock: retry (unless max retry count has been reached)
                        numAttempts++;
                    } else {
                        // Some other kind of SQLException
                        throw e;
                    }
                }
            }

            if (!success) {
                throw new SQLException("Transaction failed (too many retries)");
            }

            // Success!
            return result;
        } finally {
            DBUtil.closeQuietly(conn);
        }
    }

    private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
        user.setUserId(resultSet.getInt(index++));
        user.setUsername(resultSet.getString(index++));
        user.setPassword(resultSet.getString(index++));
        user.setLoginId(resultSet.getInt(index++));
        user.setName(resultSet.getString(index++));
        user.setEmail(resultSet.getString(index++));
        user.setDescription(resultSet.getString(index++));
    }

    private void loadGarden(Garden garden, ResultSet resultSet, int index) throws SQLException {
        garden.setGarden_id(resultSet.getInt(index++));
        garden.setGarden_name(resultSet.getString(index++));
        garden.setDescription(resultSet.getString(index++));
    }

    private void loadGardenMember(GardenMember groupMember, ResultSet resultSet, int index) throws SQLException {
        groupMember.setMember_id(resultSet.getInt(index++));
        groupMember.setGarden_id(resultSet.getInt(index++));
        groupMember.setUser_id(resultSet.getInt(index++));
    }

    private void loadPost(Post post, ResultSet resultSet, int index) throws SQLException {
        post.setPostId(resultSet.getInt(index++));
        post.setUser_id(resultSet.getInt(index++));
        post.setGroupId(resultSet.getInt(index++));
        post.setComments(resultSet.getString(index++));
    }

    public static void main(String[] args) {
        System.out.println("\nAcquiring MySQL DataSource...");
        System.out.println("----Connecting to Database---- ");
        System.out.println("Testing Initial Data:");
        testDataSource("mysql");
    }
}