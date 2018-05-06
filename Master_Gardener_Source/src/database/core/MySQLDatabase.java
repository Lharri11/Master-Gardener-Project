package database.core;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import model.*;
import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("Duplicates")
public class MySQLDatabase implements IDatabase {

    public static final int MAX_ATTEMPTS = 10;
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/mastergardener";
    private static final String MYSQL_USERNAME = "gardener";
    private static final String MYSQL_PASSWORD = "gardener";

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
        if ("mysql".equals(dbType)) {
            ds = getMySQLDataSource();
        } else {
            System.out.println("invalid db type");
            return;
        }

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select user_ID, first_name, last_name from mg_user");
            while (rs.next()) {
                System.out.println("\nUser ID = " + rs.getInt("user_ID") + ", Name = " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("\nAcquiring MySQL DataSource...");
        System.out.println("----Connecting to Database---- ");
        System.out.println("Testing Initial Data:");
        testDataSource("mysql");
    }

    public ResultSet adHocQuery(final String[] select_array, final String[] from_array, final String[] where_array) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            stmt = conn.prepareStatement(createCustomQuery(select_array, from_array, where_array, false));
            set = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went horribly wrong in the adHocQuery method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return set;
    }

    /* ----------------------------------------------Query Functions---------------------------------------------- */

    public String createCustomQuery(final String[] select_array, final String[] from_array, final String[] where_array, final boolean export) {
        for (int i = 0; i < select_array.length; i++) {
            if (select_array[i].equals("password") || select_array[i].equals("username")
                    || select_array[i].equals("login_id") || select_array[i].equals("email") || select_array[i].equals("description")) {
                System.out.println("User is trying to access data that they shouldn't be. Closing...");
                return "";
            }
        }

        for (int i = 0; i < from_array.length; i++) {
            if (from_array[i].equals("password") || from_array[i].equals("username")
                    || from_array[i].equals("login_id") || from_array[i].equals("email") || from_array[i].equals("description")) {
                System.out.println("User is trying to access data that they shouldn't be. Closing...");
                return "";
            }
        }

        for (int i = 0; i < where_array.length; i++) {
            if (where_array[i].equals("password") || where_array[i].equals("username")
                    || where_array[i].equals("login_id") || where_array[i].equals("email") || where_array[i].equals("description")) {
                System.out.println("User is trying to access data that they shouldn't be. Closing...");
                return "";
            }
        }

        String prepared_statement = "SELECT " + select_array[0];

        if(select_array.length > 1) {
            for (int i = 1; i < select_array.length; i++) {
                prepared_statement += ", " + select_array[i];
            }
        }
        prepared_statement += " FROM " + from_array[0];
        if(from_array.length > 1) {
            for (int i = 1; i < from_array.length; i++) {
                prepared_statement += ", " + from_array[i];
            }
        }
        if (where_array.length > 0) {
            prepared_statement += " WHERE " + where_array[0];
            prepared_statement += " = " + where_array[1];

            for (int i = 2; i < where_array.length - 1; i++) {
                prepared_statement += " AND " + where_array[i];
                prepared_statement += " = " + where_array[i + 1];
            }
        }


        // TODO: Append to text file to save queries
        if (export) {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            System.out.println("exportCustomQuery method: This is debug code. Remember to remove this.\n Current relative path is: " + s);

            // This should look at the filepath "Master_gardener_Source/war/data/..."
            File file = new File("war/data/exported_query.txt");
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                // If file does not exist, create it
                if (!file.exists()) {
                    System.out.println("File does not exist, creating...");
                    file.createNewFile();
                } else {
                    System.out.println("File already exists.");
                }
                // Create new FileWriter and BufferedWriter for writing to files.
                fw = new FileWriter(file, true);
                bw = new BufferedWriter(fw);

                bw.write(prepared_statement);
            } catch (IOException e) {
                System.out.println("exportCustomQuery method: Caught new IOException: " + e);
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                    if (fw != null) {
                        fw.close();
                    }

                } catch (IOException f) {
                    System.out.println("exportCustomQuery method: Caught new IOException(1): " + f);
                }
            }
        }
        return prepared_statement;
    }

    public String saltPassword(String username, String password) throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        String salt = null;
        try
        {
            stmt = conn.prepareStatement("SELECT salt FROM mg_user WHERE user_name = ?");
            stmt.setString(1, username);
            set = stmt.executeQuery();

            if(set.next())
            {
                salt = set.getString(1);
            }
        }
        finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }

        return salt+password;
    }

    public boolean updatePollinatorVisitPlotIDs() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        int math = 0;
        ArrayList<Integer> garden_ids = new ArrayList<>();
        ArrayList<Integer> pvc_ids = new ArrayList<>();
        ArrayList<Integer> df_ids = new ArrayList<>();
        ArrayList<Integer> pvc_strain_ids = new ArrayList<>();
        ArrayList<Integer> plot_list = new ArrayList<>();

        System.out.println("Working. This may take a while...");

        try
        {
            // Get all individual PVC ids in order
            //stmt = conn.prepareStatement("SELECT id, data_form_id, strain_id FROM mg_pollinator_visit");
            stmt = conn.prepareStatement("SELECT id FROM mg_pollinator_visit");
            set = stmt.executeQuery();

            while(set.next())
            {
                // Add individual PVC ids and dataform into respective arraylists
                pvc_ids.add(set.getInt(1));
            }

            // Get all the dataform IDs in order
            stmt = conn.prepareStatement("SELECT data_form_id FROM mg_pollinator_visit");
            set = stmt.executeQuery();

            while(set.next())
            {
                df_ids.add(set.getInt(1));
            }

            // Get all the strain IDs in order
            stmt = conn.prepareStatement("SELECT strain_id FROM mg_pollinator_visit");
            set = stmt.executeQuery();

            while(set.next())
            {
                pvc_strain_ids.add(set.getInt(1));
            }

            System.out.println("PERFORMING FIRST DATA INTEGRITY CHECK(FIRST 20 TUPLES):");
            for(int i = 0; i < 20; i++) {
                System.out.println("pvc_id: " + pvc_ids.get(i) + "    df_id: " + df_ids.get(i) + "    pvc_strain_id: " + pvc_strain_ids.get(i));
            }

            // (THE ABOVE TEST HELPS BUT IT DEFINITELY WORKS AS WELL, BROKEN STUFF IS BELOW THIS)

            for(int i = 0; i < df_ids.size()-1; i++)
            {
                // Get all garden IDs per dataform ID matching the plot. There's lots of repetition here.
                stmt = conn.prepareStatement("SELECT garden_id FROM mg_data_form WHERE id = ?");
                stmt.setInt(1, df_ids.get(i));
                set = stmt.executeQuery();

                if(set.next())
                {
                    garden_ids.add(set.getInt(1));
                }


            }

            System.out.println("\nPERFORMING SECOND DATA INTEGRITY CHECK:");
            for(int j = 0; j < 100; j++)
            {
                System.out.println("pvc_id: "+pvc_ids.get(j) + "   df_id:" + df_ids.get(j) + "   garden_id: " + garden_ids.get(j));
            }

            // (APPEARS AS IF THE ABOVE TEST WORKS)

            for(int i = 0; i < df_ids.size()-1; i++)
            // You can use any of the above arraylists for this, df_ids was chosen arbitrarily.
            {
                //Perform the math. The correlation looks like (strain_id) + (9 * (garden_id - 1))
                // This holds true for the examples given by Logan in his texts, eg:
                // Strain #5 for Garden #1: Plot ID = 5 + (9 * 0) = 5
                // Strain #5 for Garden #2: Plot ID = 5 + (9 * 1) = 14
                // etc

                math = pvc_strain_ids.get(i) + (9 * (garden_ids.get(i) - 1));
                plot_list.add(math);
                // This gets the supposed plot IDs
            }

            // Next, update the plot IDs in PVC table:
            for(int i = 0; i < pvc_ids.size()-1; i++ )
            // Another arbitrarily chosen arraylist, they should all be the same size so it doesn't matter
            {
                stmt = conn.prepareStatement("UPDATE mg_pollinator_visit SET plot_id = ? WHERE id = ? AND data_form_id = ?");
                stmt.setInt(1, plot_list.get(i));
                stmt.setInt(2, pvc_ids.get(i));
                stmt.setInt(3, df_ids.get(i));
                stmt.executeUpdate();

            }

        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return true;
    }

    public boolean checkPasswordByUsername(String username, String password)throws SQLException
    {
        // This method checks a password passed into the method VS. the user's actual password.
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;

        String curr_pass = null;


        try{
            stmt = conn.prepareStatement("SELECT salt from mg_user WHERE user_name = ?");
            stmt.setString(1, username);
            set = stmt.executeQuery();

            String salt = null;
            if(set.next())
            {
                salt = set.getString(1);
            }

            String check_pass = hashString(salt+password);

            stmt = conn.prepareStatement("SELECT passWord from mg_user WHERE user_name = ?");
            stmt.setString(1, username);
            set = stmt.executeQuery();

            if(set.next())
            {
                curr_pass = set.getString(1);
            }
            if(curr_pass.equals(check_pass))
            {
                return true;
            }
            return false;
        }
        finally{
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
    }

    public ArrayList<String> getPlantsFromGarden(Garden garden) throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        ArrayList<String> plant_list = new ArrayList<>();
        ArrayList<Integer> plant_strain_id_list = new ArrayList<>();

        try
        {
            stmt = conn.prepareStatement("SELECT plant_strain_id FROM mg_plot WHERE garden_id = ?");
            stmt.setInt(1, garden.getGarden_id());
            set = stmt.executeQuery();

            while(set.next())
            {
                plant_strain_id_list.add(set.getInt(1));
            }

            for(int i = 0; i < plant_strain_id_list.size(); i++) {
                stmt = conn.prepareStatement("SELECT name FROM mg_plant_strain WHERE plant_id = ?");
                stmt.setInt(1, plant_strain_id_list.get(i));
                set = stmt.executeQuery();
                while(set.next())
                {
                    plant_list.add(set.getString(1));
                }
            }
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);

        }

        return plant_list;
    }
    public int getNumberOfPlantsFromGarden(Garden garden) throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        ArrayList<Integer> plant_strain_id_list = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT plant_strain_id FROM mg_plot WHERE garden_id = ?");
            stmt.setInt(1, garden.getGarden_id());
            set = stmt.executeQuery();
            while (set.next()) {
                plant_strain_id_list.add(set.getInt(1));
            }

        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return plant_strain_id_list.size();
    }

    public ArrayList<String> getPollinatorsFromGarden(Garden garden) throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        //TODO

        return null;
    }


    public List<String> getAllPollinatorsPlantsGardenVisitCount() throws SQLException {


        try {
            return doQueryLoop(new Query<List<String>>() {
                @Override
                public List<String> query() throws SQLException {
                    List<String> results = new ArrayList<String>();
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();

                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    try {
                        // TODO: SQL Code

                        // This clustertruck is basically saying:
                        // GET     Name of Garden, Plant Name, Pollinator Name, and Respective Pollinator Visit Count

                        stmt = conn.prepareStatement("SELECT mg_dataform_insert.garden_name, mg_dataform_insert.plant_name, mg_dataform_insert.pollinator_name," +
                                " mg_pollinator_visit.visit_count " +
                                "FROM mg_dataform_insert, mg_pollinator_visit" +
                                " WHERE mg_garden.garden_ID = mg_dataform_insert.garden_id AND mg_pollinator.pollinator_ID = mg_dataform_insert.pollinator_id" +
                                " AND mg_plant.plant_ID = mg_dataform_insert.plant_id AND mg_pollinator_visit.id = mg_dataform_insert.pvc_id ");

                        boolean found = false;
                        set = stmt.executeQuery();
                        while (set.next()) {
                            found = true;

                            // Since the third column is an int, we need to get that pre-emptively to convert it to a String
                            Integer visit_count = set.getInt(3);

                            results.add(set.getString(1));
                            results.add(set.getString(2));
                            results.add(visit_count.toString());
                            results.add(set.getString(4));
                        }
                        if (!found) {
                            System.out.println("No data found. Something is probably wrong.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                        DBUtil.closeQuietly(conn);
                    }
                    return results;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<String>();

        }
    }

    public List<String> getAllPollinators() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();


        try {
            return doQueryLoop(new Query<List<String>>() {
                @Override
                public List<String> query() throws SQLException {
                    List<String> results = new ArrayList<String>();

                    PreparedStatement stmt = null;
                    ResultSet resultSet = null;

                    try

                    {
                        stmt = conn.prepareStatement("SELECT pollinatorName FROM mg_pollinator");
                        resultSet = stmt.executeQuery();
                        boolean found = false;
                        while (resultSet.next()) {
                            found = true;
                            results.add(resultSet.getString(1));
                        }
                        if (!found) {
                            System.out.println("No data was found in the pollinator table.");
                        }
                    } finally

                    {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(resultSet);
                        DBUtil.closeQuietly(conn);
                    }

                    return results;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
            // Return new empty string
            return new ArrayList<String>();
        }
    }

    public List<String> getAllPlants() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        try {
            return doQueryLoop(new Query<List<String>>() {
                @Override
                public List<String> query() throws SQLException {
                    List<String> results = new ArrayList<String>();

                    PreparedStatement stmt = null;
                    ResultSet resultSet = null;
                    try

                    {
                        stmt = conn.prepareStatement("SELECT plant_name FROM mg_plant");
                        resultSet = stmt.executeQuery();
                        boolean found = false;
                        while (resultSet.next()) {
                            found = true;
                            results.add(resultSet.getString(1));
                        }
                        if (!found) {
                            System.out.println("No data was found in the plant table.");
                        }
                    } finally

                    {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(resultSet);
                        DBUtil.closeQuietly(conn);
                    }
                    return results;

                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<String>();

        }
    }

    public List<String> getAllStrains() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        try {
            return doQueryLoop(new Query<List<String>>() {
                @Override

                public List<String> query() throws SQLException {
                    List<String> results = new ArrayList<String>();
                    PreparedStatement stmt = null;
                    ResultSet resultSet = null;

                    try {
                        stmt = conn.prepareStatement("SELECT name FROM mg_plant_strain");
                        resultSet = stmt.executeQuery();
                        boolean found = false;
                        while (resultSet.next()) {
                            found = true;
                            results.add(resultSet.getString(1));
                        }
                        if (!found) {
                            System.out.println("No data was found in the strain table.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(resultSet);
                        DBUtil.closeQuietly(conn);
                    }
                    return results;
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
            // Empty list
            return new ArrayList<String>();
        }

    }

    public Pair<Pair<List<Integer>, List<String>>, List<Date>> getVisitCountAndPollinatorNameAndDateByCounty(final County county) throws SQLException {

        List<Integer> vc_list = getVisitCountsByCounty(county);
        List<String> poll_list = getPollinatorsNameByCounty(county);
        List<Date> date_list = getDatesByCounty(county);

        Pair<List<Integer>, List<String>> pair = new Pair<> (vc_list, poll_list);
        Pair<Pair<List<Integer>, List<String>>, List<Date>> data = new Pair<>(pair, date_list);

        // TODO: DO NOT DELETE THIS BECAUSE NICK IS A RUHSTARd
        /*data.getLeft().getLeft();
        data.getLeft().getRight();
        data.getRight();*/

        return data;
    }

    public List<Integer> getVisitCountsByCounty(final County county) throws SQLException
    {
        ArrayList<Integer> df_id_list = new ArrayList<>();
        ArrayList<Integer> vc_id_list = new ArrayList<>();
        ArrayList<Integer> return_list = new ArrayList<>();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet set = null;
        ResultSet set2 = null;
        ResultSet set3 = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        try
        {
            // Get all dataform IDs per county
            stmt = conn.prepareStatement("SELECT id FROM mg_data_form WHERE county_id = ? AND confirmed = ?");
            stmt.setInt(1, county.getCountyID());
            // Use '1' as the argument so there's no chance of error when getting a confirmed or unconfirmed dataform
            stmt.setInt(2, 1);
            set = stmt.executeQuery();

            while(set.next())
            {
                df_id_list.add(set.getInt(1));
            }

            // Get all visitcount ids per dataform
            for(int i = 0; i < df_id_list.size(); i++) {
                stmt2 = conn.prepareStatement("SELECT visitcount_id FROM mg_dataform_insert WHERE dataform_id = ?");
                stmt2.setInt(1, df_id_list.get(i));
                set2 = stmt2.executeQuery();
                vc_id_list.add(set2.getInt(1));
            }

            // Get all visit counts by county (can shorten this to two statements if data_form_id value is correct)
            for(int i = 0; i < vc_id_list.size(); i++)
            {
                stmt3 = conn.prepareStatement("SELECT visit_count FROM mg_pollinator_visit WHERE id = ?");
                stmt3.setInt(1, vc_id_list.get(i));
                set3 = stmt3.executeQuery();
                return_list.add(set3.getInt(1));
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("There was an error in getVisitCountsByCounty");
            return null;
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(stmt3);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(set2);
            DBUtil.closeQuietly(set3);
            DBUtil.closeQuietly(conn);
        }

        return return_list;
    }

    public List<String> getPollinatorsNameByCounty(final County county) throws SQLException
    {
        ArrayList<Integer> df_id_list = new ArrayList<>();
        ArrayList<Integer> poll_id_list = new ArrayList<>();
        ArrayList<String> return_list = new ArrayList<>();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet set = null;
        ResultSet set2 = null;
        ResultSet set3 = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        try
        {
            // Get all dataform IDs per county
            stmt = conn.prepareStatement("SELECT id FROM mg_data_form WHERE county_id = ? AND confirmed = ?");
            stmt.setInt(1, county.getCountyID());
            // Use '1' as the argument so there's no chance of error when getting a confirmed or unconfirmed dataform
            stmt.setInt(2, 1);
            set = stmt.executeQuery();

            while(set.next())
            {
                df_id_list.add(set.getInt(1));
            }

            // Get all poll ids per dataform
            for(int i = 0; i < df_id_list.size(); i++) {
                stmt2 = conn.prepareStatement("SELECT pollinator_id FROM mg_dataform_insert WHERE dataform_id = ?");
                stmt2.setInt(1, df_id_list.get(i));
                set2 = stmt2.executeQuery();
                poll_id_list.add(set2.getInt(1));
            }

            // Get all pollinator names by county
            for(int i = 0; i < poll_id_list.size(); i++)
            {
                stmt3 = conn.prepareStatement("SELECT pollinatorName FROM mg_pollinator WHERE pollinator_ID = ?");
                stmt3.setInt(1, poll_id_list.get(i));
                set3 = stmt3.executeQuery();
                return_list.add(set3.getString(1));
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("There was an error in getPollinatorsByCounty");
            return null;
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(stmt3);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(set2);
            DBUtil.closeQuietly(set3);
            DBUtil.closeQuietly(conn);
        }

        return return_list;
    }
    public List<Date> getDatesByCounty(final County county) throws SQLException
    {
        ArrayList<Integer> df_id_list = new ArrayList<>();
        ArrayList<Date> return_list = new ArrayList<>();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        ResultSet set2 = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        try
        {
            // Get all dataform IDs per county
            stmt = conn.prepareStatement("SELECT id FROM mg_data_form WHERE county_id = ? AND confirmed = ?");
            stmt.setInt(1, county.getCountyID());
            // Use '1' as the argument so there's no chance of error when getting a confirmed or unconfirmed dataform
            stmt.setInt(2, 1);
            set = stmt.executeQuery();

            while(set.next())
            {
                df_id_list.add(set.getInt(1));
            }

            // Get all dates matching the county id
            for(int i = 0; i < df_id_list.size(); i++) {
                stmt2 = conn.prepareStatement("SELECT date_generated FROM mg_data_form WHERE id = ?");
                stmt2.setInt(1, df_id_list.get(i));
                set2 = stmt2.executeQuery();
                return_list.add(set2.getDate(1));
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("There was an error in getDateByCounty");
            return null;
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(set2);
            DBUtil.closeQuietly(conn);
        }

        return return_list;
    }

    public String removeUsernameFromUsersTable(final String username) {
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

                try {
                    // Get account to be deleted
                    stmt1 = conn.prepareStatement(
                            "select user_id " +
                                    " from mg_user " +
                                    "where mg_user.user_name = ? "

                    );

                    stmt1.setString(1, username);
                    rslt1 = stmt1.executeQuery();

                    if (rslt1.next()) {
                        user_id = rslt1.getInt(1);
                        System.out.println("User " + username + " found with ID " + user_id + ".");
                    } else {
                        System.out.println("Attempting to remove non-existant User...\n It failed?!");
                        failed = true;
                    }

                    if (!failed) {
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
                    } else {
                        System.out.println("Returning empty string due to not finding the intended User.");
                        return "";
                    }
                }
                // Close all statements/resultSets
                finally {
                    DBUtil.closeQuietly(rslt1);
                    DBUtil.closeQuietly(stmt1);
                    DBUtil.closeQuietly(stmt2);
                    DBUtil.closeQuietly(stmt3);
                    DBUtil.closeQuietly(conn);
                }

            }
        });
    }

    public boolean deleteUserFromGarden(User user, Garden garden) throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        PreparedStatement stmt = null;

        try
        {
            stmt = conn.prepareStatement("DELETE FROM mg_garden_member WHERE user_id = ? AND garden_id = ?");
            stmt.setInt(1, user.getUserId());
            stmt.setInt(2, garden.getGarden_id());
            stmt.executeUpdate();
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(conn);
        }
        return true;
    }

    public boolean deleteUserByUsername(Connection conn, User user) throws SQLException {
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet set = null;
        boolean success = false;
        try {
            stmt1 = conn.prepareStatement(
                    "DELETE from mg_garden_member "
                            + "WHERE user_id = ? ");
            stmt1.setInt(1, user.getUserId());
            stmt1.executeUpdate();

            /*stmt2 = conn.prepareStatement(
                    "DELETE from mg_post "
                            + " WHERE user_id = ? ");
            stmt2.setInt(1, user.getUserId());
            stmt2.executeUpdate();*/

            stmt3 = conn.prepareStatement(
                    "DELETE from mg_user "
                            + " WHERE user_name = ? ");
            stmt3.setString(1, user.getUsername());
            stmt3.executeUpdate();

            success = true;
        } catch (SQLException e) {
            System.out.println("DeleteUserByusernameDatabase: " + e.getMessage());
            return false;
        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(stmt3);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    private boolean verifyGardenMemberexistsinGarden(Connection conn, GardenMember TheMember) throws SQLException {
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
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    private boolean updateUserByUsername(String username, User user) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        boolean success = false;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;

        try {
            String password = null;
            String salt = null;
            stmt = conn.prepareStatement("SELECT passWord, salt FROM mg_user WHERE user_name = ? ");
            stmt.setString(1, user.getUsername());
            //stmt.setString(2, user.getDescription());
            set = stmt.executeQuery();
            if(set.next()) {
                password = set.getString(1);
                salt = set.getString(2);
            }
            stmt = conn.prepareStatement(
                    "UPDATE mg_user "
                            + " SET first_name = ?, last_name = ?, email = ?, description = ? "
                            + " WHERE user_name = ? AND password = ? AND salt = ?");
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getDescription());
            stmt.setString(5, user.getUsername());
            //stmt.setBlob(3, inputStream);
            stmt.setString(6, password);
            stmt.setString(7, salt);
            stmt.executeUpdate();

            //get user_id
            stmt2 = conn.prepareStatement(
                    "SELECT user_id FROM mg_user "
                            + " WHERE first_name = ? AND user_name = ?");
            stmt2.setString(1, user.getUsername());
            stmt2.setString(2, username);
            set = stmt2.executeQuery();

            success = true;

        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    private boolean insertUserPortraitByUsername(Connection conn, String username, User user, String filePath) throws SQLException {
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        boolean success = false;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            stmt = conn.prepareStatement(
                    "INSERT into mg_user_portrait (user_name, filePath) "
                            + " VALUES(?,?)");
            stmt.setString(1, username);
            stmt.setString(2, filePath);
            stmt.executeUpdate();

            success = true;

        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    private boolean updateUserPortraitByUsername(Connection conn, String username, User user, String filepath) throws SQLException {
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        boolean success = false;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;

        try {
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
                            + " SET user_name = ?, filepath = ? "
                            + " WHERE user_name = ? ");
            stmt2.setString(1, username);
            stmt2.setString(2, filepath);

            stmt2.executeUpdate();

            success = true;

        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    public User getUserFromUserName(String username) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        User user;
        PreparedStatement stmt = null;
        ResultSet set = null;
        try {
            stmt = conn.prepareStatement(
                    " SELECT user_id, user_name, passWord, login_id, email, first_name, last_name, description FROM mg_user "
                            + " WHERE user_name=?");
            stmt.setString(1, username);

            set = stmt.executeQuery();
            set.next();

            user = new User(username, username, 0, username, username, username, username);
            loadUser(user, set, 1);


        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return user;
    }

    public User getUserFromUserID(int user_id) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        User user = new User(null, null, 0, null, null, null, null);;

        try {
            stmt = conn.prepareStatement(
                    " SELECT user_id, user_name, passWord, login_id, email, first_name, last_name, description FROM mg_user "
                            + " WHERE user_id = ?");
            stmt.setInt(1, user_id);
            set = stmt.executeQuery();
            //set.next();

            if(set.next()){
                loadUser(user, set, 1);
                /*System.out.println("USERFROMUSERID TEST:");
                System.out.println("user_id: "+ user.getUserId());
                System.out.println("username: " + user.getUsername());
                System.out.println("pasword: "+ user.getPassword());
                System.out.println("login_id: "+ user.getLoginId());
                System.out.println("email: " + user.getEmail());
                System.out.println("first_name: "+ user.getFirstName());
                System.out.println("last_name: " + user.getLastName());
                System.out.println("description: "+ user.getDescription());*/
            }
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return user;
    }

    public int getUserIDFromFirstNameAndLastName(final String first_name, final String last_name) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        int user_id = 0;

        try {
            stmt = conn.prepareStatement(
                    " SELECT user_id FROM mg_user "
                            + " WHERE first_name = ? AND last_name = ?");
            stmt.setString(1, first_name);
            stmt.setString(2, last_name);

            set = stmt.executeQuery();
            if(set.next()) {
                user_id = set.getInt(1);
            }

        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return user_id;
    }


    public String hashString(String str) throws SQLException
    {
        // This is a workaround method for making sure that two hashed strings are the same
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;

        try
        {
            stmt = conn.prepareStatement("SELECT SHA2(SHA2(SHA2(?, 512), 512), 512)");
            stmt.setString(1, str);
            set = stmt.executeQuery();
            if(set.next())
            {
                return set.getString(1);
            }
            return null;
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }

    }

    private String getPasswordByUsername(Connection conn, String username) throws SQLException {
        DataSource ds = getMySQLDataSource();
        String password = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(" SELECT password FROM mg_user WHERE user_name=? ");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                password = rs.getString(1);
            }
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(conn);
        }
        return password;
    }

    public int getGardenIDByGardenName(final String garden_name) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        int id = -1;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn.prepareStatement(" SELECT garden_ID FROM mg_garden WHERE garden_name = ? ");
            stmt.setString(1, garden_name);
            rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(conn);
        }
        return id;
    }

    public int getCountyIDByCountyName(final String county_name) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        int id = -1;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn.prepareStatement(" SELECT county_ID FROM mg_county WHERE countyName = ? ");
            stmt.setString(1, county_name);
            rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(conn);
        }
        return id;
    }

    public int getPlantIDByPlantName(final String plant_name) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        int id = -1;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn.prepareStatement(" SELECT plant_ID FROM mg_plant WHERE plant_name = ? ");
            stmt.setString(1, plant_name);
            rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(conn);
        }
        return id;
    }

    public int getStrainIDByStrainName(final String strain_name) throws SQLException {
        {
            DataSource ds = getMySQLDataSource();
            Connection conn = ds.getConnection();
            int id = -1;

            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {

                stmt = conn.prepareStatement(" SELECT strain_id FROM mg_plant_strain WHERE name = ? ");
                stmt.setString(1, strain_name);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    id = rs.getInt(1);
                }
            } finally {
                DBUtil.closeQuietly(stmt);
                DBUtil.closeQuietly(rs);
                DBUtil.closeQuietly(conn);
            }
            return id;
        }
    }

    public int getPollinatorIDByPollinatorName(final String poll_name) throws SQLException {
        {
            DataSource ds = getMySQLDataSource();
            Connection conn = ds.getConnection();
            int id = -1;

            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {

                stmt = conn.prepareStatement(" SELECT pollinator_ID FROM mg_pollinator WHERE pollinatorName = ? ");
                stmt.setString(1, poll_name);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    id = rs.getInt(1);
                }
            } finally {
                DBUtil.closeQuietly(stmt);
                DBUtil.closeQuietly(rs);
                DBUtil.closeQuietly(conn);
            }
            return id;
        }
    }

    public Pollinator getPollinatorByPollinatorID(final int pollinator_id) throws SQLException {
        {
            DataSource ds = getMySQLDataSource();
            Connection conn = ds.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Pollinator pollinator = new Pollinator(0, null, null);

            try {

                stmt = conn.prepareStatement(" SELECT * FROM mg_pollinator WHERE pollinator_id = ? ");
                stmt.setInt(1, pollinator_id);
                rs = stmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("No Pollinator matching ID <" + pollinator_id + "> found");
                }
                else{
                    loadPollinator(pollinator, rs, 1);
                }
            } finally {
                DBUtil.closeQuietly(stmt);
                DBUtil.closeQuietly(rs);
                DBUtil.closeQuietly(conn);
            }
            return pollinator;
        }
    }

    public String getPollinatorNameByPollinatorID(final int pollinator_id) throws SQLException {
        {
            DataSource ds = getMySQLDataSource();
            Connection conn = ds.getConnection();
            PreparedStatement stmt = null;
            ResultSet set = null;
            String pollinator_name = null;

            try {
                stmt = conn.prepareStatement(" SELECT pollinator_name FROM mg_pollinator WHERE pollinator_id = ? ");
                stmt.setInt(1, pollinator_id);
                set = stmt.executeQuery();

                if (set.next()) {
                    pollinator_name = set.getString(1);
                }
            } finally {
                DBUtil.closeQuietly(stmt);
                DBUtil.closeQuietly(set);
                DBUtil.closeQuietly(conn);
            }
            return pollinator_name;
        }
    }

    /** This is a backend helper method for the primary method getUnconfirmedDataformsByCounty**/
    public List<Integer> getUnconfirmedDataformIDsByCounty(String county) throws SQLException
    {
        {
            DataSource ds = getMySQLDataSource();
            Connection conn = ds.getConnection();
            int id = -1;
            ArrayList<Integer> df_list = new ArrayList<Integer>();
            PreparedStatement stmt1 = null;
            PreparedStatement stmt2 = null;
            ResultSet rs = null;
            try {

                stmt1 = conn.prepareStatement("SELECT county_ID FROM mg_county WHERE county_name = ?");
                stmt1.setString(1, county);
                rs = stmt1.executeQuery();

                if(!rs.next())
                {
                    System.out.println("COULD NOT FIND COUNTY WITH NAME "+county+", STOPPING METHOD");
                    return new ArrayList<Integer>();
                }
                else {
                    // There can only be one county_id per name
                    int county_id = rs.getInt(1);
                    stmt2 = conn.prepareStatement(" SELECT id FROM mg_data_form WHERE county_id = ? AND confirmed = ?");
                    stmt2.setInt(1, county_id);
                    stmt2.setInt(2, 0);
                    rs = stmt2.executeQuery();

                    while (rs.next()) {
                        df_list.add(rs.getInt(1));
                    }
                }
            } finally {
                DBUtil.closeQuietly(stmt1);
                DBUtil.closeQuietly(stmt2);
                DBUtil.closeQuietly(rs);
                DBUtil.closeQuietly(conn);
            }
            return df_list;
        }
    }

    public List<String> getCountiesWithUnconfirmedDataforms () throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        ArrayList<Integer> county_ids = new ArrayList<>();
        ArrayList<String> county_names = new ArrayList<>();

        PreparedStatement stmt1 = null;
        ResultSet set1 = null;

        try
        {
            stmt1 = conn.prepareStatement("SELECT county_id FROM mg_data_form WHERE confirmed = ?");
            stmt1.setInt(1, 0);

            set1 = stmt1.executeQuery();

            int county_id = 0;
            boolean cont;
            if(!set1.next())
            {
                county_names.add("NO COUNTIES WITH UNCONFIRMED DFs.");
                return county_names;
            }
            while(set1.next())
            {
                cont = true;
                county_id = set1.getInt(1);

                for(int i = 0; i < county_ids.size(); i++)
                {
                    if(county_id == county_ids.get(i))
                    {
                        cont = false;
                    }
                }
                if(cont)
                {
                    county_ids.add(county_id);
                }
            }
            for(int i = 0; i < county_ids.size(); i++) {
                stmt1 = conn.prepareStatement("SELECT countyName FROM mg_county WHERE county_ID = ?");
                stmt1.setInt(1, county_ids.get(i));
                set1 = stmt1.executeQuery();

                if(set1.next())
                {
                    county_names.add(set1.getString(1));
                }
            }
        }
        finally
        {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(set1);
            DBUtil.closeQuietly(conn);
        }
        return county_names;
    }

    /** Use this for frontend work, this is the primary method for getting unconfirmed dataforms **/
    public List<PollinatorDataForm> getUnconfirmedDataformsByCounty(String county) throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        // Get list of dataform IDs for the county
        List<Integer> cids = getUnconfirmedDataformIDsByCounty(county);
        List<PollinatorDataForm> return_list = new ArrayList<>();
        //ArrayList<String> return_list = new ArrayList<>();
        ArrayList<String> plant_names = new ArrayList<>();
        ArrayList<String> poll_names = new ArrayList<>();
        ArrayList<String> strain_names = new ArrayList<>();
        ArrayList<Integer> visit_counts = new ArrayList<>();

        PreparedStatement stmt1 = null;
        ResultSet set1 = null;
        ResultSet set2 = null;

        try {
            if(cids.size() > 0) {
                for (int i = 0; i < cids.size(); i++) {
                    // First, add the current ID to the result list
                    //return_list.add(cids.get(i).toString());

                    // Create PDF object. This will not hold all information present in the constructor.
                    // Side note: Must always re-initialize the variables plant_names, ... , visit_counts (shown below)
                    // since the constructor does not handle them. (Design choice; the only place we use these
                    // names is in this method)
                    PollinatorDataForm pdf = new PollinatorDataForm(-1, -1, null, -1, null, null, null, null,
                            null, null, null, -1, null, null, 0, null, null, null, null, null);
                    plant_names.clear();
                    poll_names.clear();
                    strain_names.clear();
                    visit_counts.clear();


                    // Then, get miscellaneous (easy) fields
                    stmt1 = conn.prepareStatement("SELECT id, temperature, date_collected, date_generated, date_confirmed, monitor_start, monitor_stop,"
                            + " wind_status, cloud_status, comments, butterfly_moth_comments FROM mg_data_form WHERE id = ?");
                    stmt1.setInt(1, cids.get(i));
                    // Hopefully, this loads 8 fields into the ResultSet from columns 1 to 8
                    set1 = stmt1.executeQuery();

                    // Get all of these fields as a string so we can add them to the return set
                    // TODO: FIX STRAIN
                    while(set1.next()) {
                        for (int j = 1; j < 12; j++) {
                            //return_list.add(set1.getString(j));
                            // As Logan would say, jenky way of getting the correct types from the above statement

                            pdf.setData_form_id(set1.getInt((j++)));

                            pdf.setTemperature(set1.getInt(j++));

                            pdf.setDate_collected(set1.getDate(j++).toLocalDate());

                            pdf.setDate_generated(set1.getDate(j++).toLocalDate());

                            pdf.setDate_confirmed(set1.getDate(j++).toLocalDate());

                            pdf.setMonitor_start(set1.getTime(j++).toLocalTime());

                            pdf.setMonitor_stop(set1.getTime(j++).toLocalTime());

                            pdf.setWind_status(set1.getString(j++));

                            pdf.setCloud_status(set1.getString(j++));

                            pdf.setComments(set1.getString(j++));

                            pdf.setButterflyMothComments(set1.getString(j++));

                        }
                    }

                    // Now, do the hard stuff
                    stmt1 = conn.prepareStatement("SELECT garden_id FROM" +
                            " mg_data_form WHERE id = ?");
                    stmt1.setInt(1, cids.get(i));

                    // Load all of the IDs into the ResultSet so we can begin parsing them
                    set1 = stmt1.executeQuery();

                    // Start off by getting garden name
                    stmt1 = conn.prepareStatement("SELECT garden_name FROM mg_garden WHERE garden_ID = ?");
                    if (set1.next()) {
                        stmt1.setInt(1, set1.getInt(1));
                    }
                    set2 = stmt1.executeQuery();

                    if (set2.next()) {
                        pdf.setGarden_name(set2.getString(1));
                    }
                    // Get PVC stats for relevant DFs
                    stmt1 = conn.prepareStatement("SELECT id, plant_id, strain_id, visit_count FROM mg_pollinator_visit WHERE data_form_id = ?");
                    stmt1.setInt(1, cids.get(i));
                    set1 = stmt1.executeQuery();

                    // TODO: Everything prior might work. Test it all.

                    while (set1.next()) {

                        stmt1 = conn.prepareStatement("SELECT plant_name FROM mg_plant WHERE plant_ID = ?");
                        // Use column 2 because that's the plant ID
                        stmt1.setInt(1, set1.getInt(2));

                        set2 = stmt1.executeQuery();
                        if (set2.next()) {
                            plant_names.add(set2.getString(1));
                            //pdf.setPlant_name(set2.getString(1));
                            //return_list.add(set2.getString(1));
                        }

                        // Get Pollinator_name for column 1 of the set
                        stmt1 = conn.prepareStatement("SELECT mg_pollinator.pollinatorName FROM mg_pollinator, mg_pollinator_visit" +
                                " WHERE mg_pollinator_visit.id = ? AND mg_pollinator.pollinator_ID = mg_pollinator_visit.pollinator_id");
                        // Get first column which contains dataform IDs (which is just repeated. A lot.)
                        stmt1.setInt(1, set1.getInt(1));
                        set2 = stmt1.executeQuery();
                        if (set2.next()) {
                            poll_names.add(set2.getString(1));
                            // pdf.setPollinator_name(set2.getString(1));
                            //return_list.add(set2.getString(1));
                        }


                        // get plant strain name
                        stmt1 = conn.prepareStatement("SELECT mg_plant_strain.name FROM mg_plant_strain, mg_pollinator_visit " +
                                "WHERE mg_pollinator_visit.id = ? AND mg_pollinator_visit.strain_id = mg_plant_strain.strain_id");
                        stmt1.setInt(1, set1.getInt(1));
                        set2 = stmt1.executeQuery();

                        // Add plant strain name
                        if (set2.next()) {
                            strain_names.add(set2.getString(1));
                            //pdf.setStrain_name(set2.getString(1));
                            //return_list.add(set2.getString(1));
                        }

                        visit_counts.add(set1.getInt(4));
                        //pdf.setVisit_counts(set1.getInt(4));
                        //return_list.add(set1.getString(4));
                        //set1.next();

                    }
                    pdf.setPlant_names(plant_names);
                    pdf.setPollinator_names(poll_names);
                    pdf.setStrain_names(strain_names);
                    pdf.setVisit_counts(visit_counts);
                    return_list.add(pdf);

                    //return_list.add("END");
                }
            }
            else{
                System.out.println("DATA IS NOT BEING GOT CORRECTLY");
                //return_list.add("NO DATA TO DISPLAY");
            }
        }
        finally
        {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(set1);
            DBUtil.closeQuietly(set2);
            DBUtil.closeQuietly(conn);
        }


        return return_list;
    }

    public List<Integer> getAllDataFormIDs() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        ResultSet rs = null;
        ArrayList<Integer> data_form_ids = new ArrayList<Integer>();

        try {

            stmt1 = conn.prepareStatement("SELECT id FROM mg_data_form ORDER BY id DESC");
            rs = stmt1.executeQuery();
            if(!rs.next()) {
                System.out.println("Error Acquiring DataForm IDs");
                return new ArrayList<Integer>();
            }
            else {
                while(rs.next()){
                    data_form_ids.add(rs.getInt(1));
                }
            }
        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(conn);
        }
        return data_form_ids;
    }

    public List<Integer> getAllVisitCountIDs() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        ResultSet rs = null;
        ArrayList<Integer> visit_count_ids = new ArrayList<Integer>();

        try {

            stmt1 = conn.prepareStatement("SELECT id FROM mg_pollinator_visit ORDER BY id DESC");
            rs = stmt1.executeQuery();
            if(!rs.next()) {
                System.out.println("Error Acquiring VisitCount IDs");
                return new ArrayList<Integer>();
            }
            else {
                while(rs.next()){
                    visit_count_ids.add(rs.getInt(1));
                }
            }
        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(conn);
        }
        return visit_count_ids;
    }

    public int getModeratorStatusByUsername(String username) throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        ResultSet set = null;
        int mod_status = -1;
        try
        {
            stmt1 = conn.prepareStatement("SELECT moderator FROM mg_user WHERE user_name = ?");
            stmt1.setString(1, username);
            set = stmt1.executeQuery();
            if(set.next())
            {
                mod_status = set.getInt(1);
            }
        }
        finally
        {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return mod_status;
    }

    public ArrayList<Integer> getGeneratorListByUsernames(String[] names) throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        ResultSet set1 = null;
        ArrayList<Integer> generator_ids = null;

        try
        {
            for(int i = 0; i < names.length; i++) {
                stmt1 = conn.prepareStatement("SELECT user_ID FROM mg_user WHERE user_name = ?");
                stmt1.setString(1, names[i]);
                set1 = stmt1.executeQuery();
                if(set1.next())
                {
                    generator_ids.add(set1.getInt(1));
                }
            }
        }
        finally
        {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(set1);
            DBUtil.closeQuietly(conn);
        }

        return generator_ids;
    }

    private boolean insertUserIntoUsers(Connection conn, User user) throws SQLException {
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;

        SecureRandom random = new SecureRandom();
        // Create array for salt
        byte[] salt = new byte[64];
        // Get a random salt (this is cryptographically secure)
        random.nextBytes(salt);
        // use salt.toString(); to print out a salt.
        // pre-pend salt to password for when we hash it
        String password = salt.toString() + user.getPassword();

        boolean success = false;

        try {
            stmt1 = conn.prepareStatement(
                    "INSERT INTO mg_user (user_name, passWord, salt, login_id, first_name, last_name, email, description) "
                            + " VALUES(?, SHA2(SHA2(SHA2(?, 512), 512), 512), ?,?,?,?,?,?)");
            stmt1.setString(1, user.getUsername());
            stmt1.setString(2, password);
            stmt1.setString(3, salt.toString());
            stmt1.setInt(4, user.getLoginId());
            stmt1.setString(5, user.getFirstName());
            stmt1.setString(6, user.getLastName());
            stmt1.setString(7, user.getEmail());
            stmt1.setString(8, user.getDescription());


            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement(
                    "SELECT user_ID FROM mg_user "
                            + " WHERE user_name = ?");
            stmt2.setString(1, user.getUsername());
            set = stmt2.executeQuery();

        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    private boolean insertGardenintoGardens(Connection conn, Garden garden) throws SQLException {
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        boolean success = false;

        try {
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

        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    private boolean insertPostintoPosts(Connection conn, Post post) throws SQLException {
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt1 = conn.prepareStatement(
                    "INSERT INTO mg_post ( user_ID, garden_id, comments) "
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

        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    private boolean insertGardenMemberintoGardenMembers(Connection conn, GardenMember groupMember) throws SQLException {
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        boolean success = false;

        try {
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
            if (set.next()) {
                success = true;
            }

        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    public boolean updatePollinatorName(final String pollinator_name, final String updated_name) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_pollinator " +
                    "SET pollinatorName = ? " +
                    "WHERE pollinatorName = ?");
            stmt.setString(1, updated_name);
            stmt.setString(2, pollinator_name);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT pollinatorName FROM mg_pollinator WHERE pollinatorName = ?");
            stmt2.setString(1, updated_name);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF POLLINATOR NAME");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updatePollinatorName method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updatePassword(String user_name, String old_password, String new_password) throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        boolean success = false;

        try
        {
            String salt = null;

            stmt1 = conn.prepareStatement("SELECT user_id, salt FROM mg_user WHERE user_name = ? AND passWord = SHA2(SHA2(SHA2(?, 512), 512), 512)");
            stmt1.setString(1, user_name);
            stmt1.setString(2, old_password);

            rs = stmt1.executeQuery();

            if(rs.next())
            {
                salt = rs.getString(2);
            }
            else
            {
                System.out.println("Incorrect username or password.");
                return false;
            }

            new_password = salt+new_password;

            stmt1 = conn.prepareStatement("UPDATE mg_user" +
                    " SET passWord = SHA2(SHA2(SHA2(?, 512), 512), 512) " +
                    " WHERE user_name = ? ");

            stmt1.setString(1, new_password);
            stmt1.setString(2, user_name);

            stmt1.executeUpdate();

            // Check to see if password updated
            stmt2 = conn.prepareStatement("SELECT user_ID FROM mg_user WHERE passWord = SHA2(SHA2(SHA2(?, 512), 512), 512) AND user_name = ? AND salt = ?");
            stmt2.setString(1, new_password);
            stmt2.setString(2, user_name);
            stmt2.setString(3, salt);
            rs = stmt2.executeQuery();

            if(rs.next())
            {
                success = true;
            }

            return success;
        }
        finally
        {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(conn);
        }
    }

    public boolean updatePollinatorTypeByName(final String pollinator_name, final String updated_type) throws SQLException {
        {
            PreparedStatement stmt = null;
            PreparedStatement stmt2 = null;
            ResultSet set = null;
            DataSource ds = getMySQLDataSource();
            Connection conn = ds.getConnection();
            boolean completed = false;
            try {
                stmt = conn.prepareStatement("UPDATE mg_pollinator " +
                        "SET pollinatorType = ? " +
                        "WHERE pollinatorName = ?");
                stmt.setString(1, updated_type);
                stmt.setString(2, pollinator_name);

                stmt.executeUpdate();

                stmt2 = conn.prepareStatement("SELECT pollinatorName FROM mg_pollinator WHERE pollinatorName = ? and pollinatorType = ?");
                stmt2.setString(1, pollinator_name);
                stmt2.setString(2, updated_type);

                set = stmt2.executeQuery();

                if (set.next()) {
                    completed = true;
                } else {
                    System.out.println("UNSUCCESSFUL UPDATE OF POLLINATOR TYPE");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Something when wrong in the updatePollinatorType method.");
            } finally {
                DBUtil.closeQuietly(stmt);
                DBUtil.closeQuietly(stmt2);
                DBUtil.closeQuietly(set);
                DBUtil.closeQuietly(conn);
            }
            return completed;
        }
    }

    public boolean updatePlantName(final String plant_name, final String updated_name) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_plant " +
                    "SET plant_name = ? " +
                    "WHERE plant_name = ?");
            stmt.setString(1, updated_name);
            stmt.setString(2, plant_name);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT plant_name FROM mg_plant WHERE plant_name = ?");
            stmt2.setString(1, updated_name);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF PLANT TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updatePlantName method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updateGardenName(final String current_name, final String updated_name) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_garden " +
                    "SET garden_name = ? " +
                    "WHERE garden_name = ?");
            stmt.setString(1, updated_name);
            stmt.setString(2, current_name);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT garden_name FROM mg_garden WHERE garden_name = ?");
            stmt2.setString(1, updated_name);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF GARDEN TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updateGardenName method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updateGardenDescriptionByName(final String garden_name, final String updated_desc) throws SQLException {

        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_garden " +
                    "SET description = ? " +
                    "WHERE garden_name = ?");
            stmt.setString(1, updated_desc);
            stmt.setString(2, garden_name);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT garden_name FROM mg_garden WHERE description = ?");
            stmt2.setString(1, updated_desc);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF GARDEN TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updateGardenByDescription method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updateStrainName(final String current_name, final String updated_name) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_plant_strain " +
                    "SET name = ? " +
                    "WHERE name = ?");
            stmt.setString(1, updated_name);
            stmt.setString(2, current_name);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT name FROM mg_plant_strain WHERE name = ?");
            stmt2.setString(1, updated_name);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF STRAIN TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updateStrainName method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updatePlotHeight(final double updated_height, final int plot_id) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_plot " +
                    "SET plot_height = ? " +
                    "WHERE plot_id = ?");
            stmt.setDouble(1, updated_height);
            stmt.setInt(2, plot_id);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT plot_id FROM mg_plot WHERE plot_id = ? AND plot_height = ?");
            stmt2.setInt(1, plot_id);
            stmt2.setDouble(2, updated_height);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF PLOT TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updatePlotHeight method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public Plot getPlotByGardenIDAndStrainID(final int garden_id, final int strain_id) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        Plot plot = new Plot(-1, -1, -1, -1, -1, -1, null);

        try {
            stmt = conn.prepareStatement("SELECT * FROM mg_plot " +
                    "WHERE garden_id = ? AND plant_strain_id = ?");
            stmt.setInt(1, garden_id);
            stmt.setInt(2, strain_id);

            set = stmt.executeQuery();

            if(!set.next()){
                System.out.println("No Plots found matching Garden ID <" + garden_id + "> and Strain ID<" + strain_id + ">");
            }
            else{
                loadPlot(plot, set, 1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updatePlotArea method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return plot;
    }

    public boolean getPlotByStrainAndGardenID(final double updated_area, final int plot_id) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_plot " +
                    "SET plot_area_dbl = ? " +
                    "WHERE plot_id = ?");
            stmt.setDouble(1, updated_area);
            stmt.setInt(2, plot_id);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT plot_id FROM mg_plot WHERE plot_id = ? AND plot_area_dbl = ?");
            stmt2.setInt(1, plot_id);
            stmt2.setDouble(2, updated_area);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF PLOT TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updatePlotArea method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updatePlotArea(final double updated_area, final int plot_id) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_plot " +
                    "SET plot_area_dbl = ? " +
                    "WHERE plot_id = ?");
            stmt.setDouble(1, updated_area);
            stmt.setInt(2, plot_id);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT plot_id FROM mg_plot WHERE plot_id = ? AND plot_area_dbl = ?");
            stmt2.setInt(1, plot_id);
            stmt2.setDouble(2, updated_area);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF PLOT TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updatePlotArea method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updatePlotPercentCoverage(final double updated_percent, final int plot_id) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_plot " +
                    "SET percent_coverage = ? " +
                    "WHERE plot_id = ?");
            stmt.setDouble(1, updated_percent);
            stmt.setInt(2, plot_id);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT plot_id FROM mg_plot WHERE plot_id = ? AND percent_coverage = ?");
            stmt2.setInt(1, plot_id);
            stmt2.setDouble(2, updated_percent);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF PLOT TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updatePlotPercentCoverage method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updatePlotBloomsOpen(final String updated_bo, final int plot_id) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_plot " +
                    "SET blooms_open_status = ? " +
                    "WHERE plot_id = ?");
            stmt.setString(1, updated_bo);
            stmt.setInt(2, plot_id);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT plot_id FROM mg_plot WHERE plot_id = ? AND blooms_open_status = ?");
            stmt2.setInt(1, plot_id);
            stmt2.setString(2, updated_bo);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF PLOT TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updatePlotBloomsOpen method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updatePlotVigorStatus(final String updated_vigor, final int plot_id) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_plot " +
                    "SET plant_vigor_status = ? " +
                    "WHERE plot_id = ?");
            stmt.setString(1, updated_vigor);
            stmt.setInt(2, plot_id);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT plot_id FROM mg_plot WHERE plot_id = ? AND plant_vigor_status = ?");
            stmt2.setInt(1, plot_id);
            stmt2.setString(2, updated_vigor);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF PLOT TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updatePlotVigorStatusTable method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updatePollinatorVisitCount(final int dataform_id, final int pollinator_id, final int plant_id, final int updated_count) throws SQLException {
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        boolean completed = false;
        try {
            stmt = conn.prepareStatement("UPDATE mg_pollinator_visit " +
                    "SET visit_count = ? " +
                    "WHERE data_form_id = ? AND pollinator_id = ? AND plant_id = ? ");
            stmt.setInt(1, updated_count);
            stmt.setInt(2, dataform_id);
            stmt.setInt(3, pollinator_id);
            stmt.setInt(4, plant_id);

            stmt.executeUpdate();

            stmt2 = conn.prepareStatement("SELECT id FROM mg_pollinator_visit " +
                    "WHERE data_form_id = ? AND pollinator_id = ? AND plant_id = ? AND visit_count = ?");
            stmt2.setInt(1, dataform_id);
            stmt2.setInt(2, pollinator_id);
            stmt2.setInt(3, plant_id);
            stmt2.setInt(4, updated_count);

            set = stmt2.executeQuery();

            if (set.next()) {
                completed = true;
            } else {
                System.out.println("UNSUCCESSFUL UPDATE OF VISIT COUNT TABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something when wrong in the updateVisitCount method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return completed;
    }

    public boolean updateXTupleFromYTable(final String table, final String[] column, final String[] col_value, final String[] where) throws SQLException
    {
        boolean success = false;

        if(column.length != col_value.length)
        {
            System.out.println("NUM COLUMNS != NUM COLUMN VALUES");
        }
        else if(where.length %2 != 0){
            System.out.println("Wrong number of WHERE parameters passed.");
        }
        else {
            PreparedStatement stmt = null;
            PreparedStatement stmt2 = null;
            ResultSet set = null;
            DataSource ds = getMySQLDataSource();
            Connection conn = ds.getConnection();

            String prepared_statement = "UPDATE " + table + " SET ";
            String where_append = "";
            for (int i = 0; i < column.length; i++)
            {
                prepared_statement += column[i];
                prepared_statement += " = ";
                prepared_statement += col_value[i];
                if(i != column.length - 1)
                {
                    prepared_statement += ", ";
                }
            }

            prepared_statement += " WHERE ";

            for(int i = 0; i < where.length - 1; i += 2)
            {
                where_append += where[i];
                where_append += " = ";
                where_append += where[i+1];
                if(i != where.length - 2)
                {
                    where_append += " AND ";
                }
            }

            prepared_statement += where_append;

            try
            {

                stmt = conn.prepareStatement(prepared_statement);
                stmt.executeUpdate();

                stmt2 = conn.prepareStatement("SELECT * FROM " + table + " WHERE " + where_append);
                set = stmt2.executeQuery();

                if(!set.next())
                {
                    success = true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("FAILED to updateXTupleFromYTable");
            } finally {
                DBUtil.closeQuietly(stmt);
                DBUtil.closeQuietly(stmt2);
                DBUtil.closeQuietly(set);
                DBUtil.closeQuietly(conn);
            }
        }
        return success;
    }

    public boolean insertPollinatorIntoTable(final Pollinator poll) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt1 = conn.prepareStatement(
                    "INSERT INTO mg_pollinator (pollinatorName, pollinatorType) "
                            + " VALUES(?,?)");
            stmt1.setString(1, poll.getPollinatorName());
            stmt1.setString(2, poll.getPollinatorType());

            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement(
                    "SELECT pollinator_ID FROM mg_pollinator "
                            + " WHERE pollinatorName= ? AND pollinatorType = ?");
            stmt2.setString(1, poll.getPollinatorName());
            stmt2.setString(2, poll.getPollinatorType());
            set = stmt2.executeQuery();
            if (set.next()) {
                success = true;
            }

        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    public boolean insertPlantStrainIntoTable(final PlantStrain ps) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        boolean success = false;

        try {

            stmt1 = conn.prepareStatement(
                    "INSERT INTO mg_plant_strain (name, plant_id) "
                            + " VALUES(?, ?)");
            stmt1.setString(1, ps.getStrainName());
            stmt1.setInt(2, ps.getPlantID());

            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement(
                    "SELECT strain_id FROM mg_plant_strain "
                            + " WHERE name = ? AND plant_id = ?");
            stmt2.setString(1, ps.getStrainName());
            stmt2.setInt(2, ps.getPlantID());

            set = stmt2.executeQuery();
            if (set.next()) {
                success = true;
            }

        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    public boolean insertPlantIntoTable(final Plant plant) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt1 = conn.prepareStatement(
                    "INSERT INTO mg_plant (plant_name) "
                            + " VALUES(?)");
            stmt1.setString(1, plant.getPlantName());

            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement(
                    "SELECT plant_ID FROM mg_plant "
                            + " WHERE plant_name = ? ");
            // TODO: A plant name does not have to be unique. This update does not necessarily work as intended.
            stmt2.setString(1, plant.getPlantName());
            //stmt2.setInt(2, plant.getPlantID()); might solve the above issue. Problem is, where the hell do we get the ID without a query?

            set = stmt2.executeQuery();
            if (set.next()) {
                success = true;
            }

        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    private boolean verifyUserExistsByUsername(Connection conn, String username) throws SQLException {
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean registered = false;

        try {
            stmt = conn.prepareStatement("SELECT * from mg_user WHERE user_name=? ");
            stmt.setString(1, username);
            set = stmt.executeQuery();
            if (set.next()) {
                registered = true;
            }
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return registered;
    }

    public boolean verifyUserHasPortrait(String username) throws SQLException {
        Connection conn;
        DataSource ds = getMySQLDataSource();
        conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean registered = false;

        try {
            stmt = conn.prepareStatement("SELECT * from mg_user_portrait WHERE user_name=? ");
            stmt.setString(1, username);
            set = stmt.executeQuery();
            if (set.next()) {
                registered = true;
            }
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return registered;
    }

    public boolean deletePollinatorFromDatabase(final Pollinator poll) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        boolean success;

        // DELETE DEPENDENCIES FROM THE FOLLOWING:
        // mg_plant_strain, mg_dataform_insert, mg_pollinator
        try {

            stmt2 = conn.prepareStatement("DELETE FROM mg_dataform_insert WHERE pollinator_id = ?");
            stmt2.setInt(1, poll.getPollinatorID());
            stmt2.executeUpdate();

            stmt3 = conn.prepareStatement("DELETE FROM mg_pollinator WHERE pollinator_id = ?");
            stmt3.setInt(1, poll.getPollinatorID());
            stmt3.executeUpdate();

            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was an error deleting a Pollinator from the database.");
            return false;
        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(stmt3);
            DBUtil.closeQuietly(conn);
        }

        return success;
    }

    public boolean deleteStrainFromDatabase(final PlantStrain strain) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        boolean success;

        // DELETE DEPENDENCIES FROM THE FOLLOWING:
        // mg_plant_strain, mg_dataform_insert
        try {

            stmt2 = conn.prepareStatement("DELETE FROM mg_dataform_insert WHERE strain_id = ?");
            stmt2.setInt(1, strain.getStrainID());
            stmt2.executeUpdate();

            stmt1 = conn.prepareStatement("DELETE FROM mg_plant_strain WHERE strain_id = ?");
            stmt1.setInt(1, strain.getStrainID());
            stmt1.executeUpdate();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was an error deleting a PlantStrain from the database.");
            return false;
        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(conn);
        }

        return success;
    }

    public boolean deletePlantFromDatabase(final Plant plant) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        boolean success;

        // DELETE DEPENDENCIES FROM THE FOLLOWING:
        // mg_pollinator_visit, mg_dataform_insert, mg_plant
        try {

            stmt1 = conn.prepareStatement("DELETE FROM mg_pollinator_visit WHERE plant_id = ?");
            stmt1.setInt(1, plant.getPlantID());
            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement("DELETE FROM mg_dataform_insert WHERE plant_id = ?");
            stmt2.setInt(1, plant.getPlantID());
            stmt2.executeUpdate();

            stmt3 = conn.prepareStatement("DELETE FROM mg_plant WHERE plant_ID = ?");
            stmt3.setInt(1, plant.getPlantID());
            stmt3.executeUpdate();

            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was an error deleting a Plant from the database.");
            return false;
        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(stmt3);
            DBUtil.closeQuietly(conn);
        }

        return success;
    }

    public boolean deleteGardenFromDatabase(final Garden garden) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        PreparedStatement stmt4 = null;
        boolean success;

        // DELETE DEPENDENCIES FROM THE FOLLOWING:
        // mg_data_form, mg_dataform_insert, mg_garden_member, and mg_garden
        try {

            stmt1 = conn.prepareStatement("DELETE FROM mg_data_form WHERE garden_id = ?");
            stmt1.setInt(1, garden.getGarden_id());
            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement("DELETE FROM mg_dataform_insert WHERE garden_id = ?");
            stmt2.setInt(1, garden.getGarden_id());
            stmt2.executeUpdate();

            stmt3 = conn.prepareStatement("DELETE FROM mg_garden_member WHERE garden_id = ?");
            stmt3.setInt(1, garden.getGarden_id());
            stmt3.executeUpdate();

            stmt4 = conn.prepareStatement("DELETE FROM mg_garden WHERE garden_id = ?");
            stmt4.setInt(1, garden.getGarden_id());
            stmt4.executeUpdate();

            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was an error deleting a Garden from the database.");
            return false;
        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(stmt3);
            DBUtil.closeQuietly(stmt4);
            DBUtil.closeQuietly(conn);
        }

        return success;
    }

    public boolean deleteDataformFromDatabase(final PollinatorDataForm pdf) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        boolean success;

        // DELETE DEPENDENCIES FROM THE FOLLOWING:
        // mg_dataform_insert, mg_pollinator_visit, mg_data_form
        try {

            stmt1 = conn.prepareStatement("DELETE FROM mg_dataform_insert WHERE dataform_id = ?");
            stmt1.setInt(1, pdf.getData_form_id());
            stmt1.executeUpdate();

            stmt2 = conn.prepareStatement("DELETE FROM mg_pollinator_visit WHERE data_form_id = ?");
            stmt2.setInt(1, pdf.getData_form_id());
            stmt2.executeUpdate();

            stmt3 = conn.prepareStatement("DELETE FROM mg_data_form WHERE id = ?");
            stmt3.setInt(1, pdf.getData_form_id());
            stmt3.executeUpdate();

            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was an error deleting a Dataform from the database.");
            return false;
        } finally {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(stmt3);
            DBUtil.closeQuietly(conn);
        }

        return success;
    }

    public boolean deleteXTuplefromYTable(final String table, final String[] where)throws SQLException
    {
        boolean success = false;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;

        String prepared_statement = "DELETE FROM " + table + " WHERE ";
        String where_append = "";

        for(int i = 0; i < where.length - 1; i+= 2)
        {
            where_append += " "+ where[i];
            where_append += " = ";
            where_append += where[i+1] + " ";

            if(i != where.length - 2)
            {
                where_append += " AND ";
            }
        }

        prepared_statement += where_append;

        try
        {
            stmt = conn.prepareStatement(prepared_statement);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("SELECT * FROM " + table + " WHERE " + where_append);
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = true;
            }
            else
            {
                System.out.println("FAILED to properly delete using deleteXTupleFromYTable");
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error in deleteXTupleFromYTable method");
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    @Override
    public boolean createTables() {
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

    public int queryForLoginIdByUsername(final String username) {
        try {
            return doQueryLoop(new Query<Integer>() {
                @Override
                public Integer query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    int loginId = -1;
                    try {
                        stmt = conn.prepareStatement(
                                " SELECT login_id FROM mg_user "
                                        + " WHERE user_name = ?");
                        stmt.setString(1, username);
                        set = stmt.executeQuery();

                        if (set.next()) {
                            loginId = set.getInt(1);
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                        DBUtil.closeQuietly(conn);
                    }
                    return loginId;
                }
            });
        } catch (SQLException e) {
            System.out.println("queryForLoginIdByUsername: " + e.getMessage());
            return -1;
        }
    }

    public User queryForUserByUsername(final String username) {
        try {
            return doQueryLoop(new Query<User>() {
                @Override
                public User query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    User user = null;
                    if (verifyUserExistsByUsername(conn, username)) {
                        user = getUserFromUserName(username);
                    }
                    return user;
                }
            });
        } catch (SQLException e) {
            System.out.println("queryForUserUserByUsername: " + e.getMessage());
            return null;
        }
    }

    public String queryForPasswordByUsername(final String username) {
        try {
            return doQueryLoop(new Query<String>() {
                @Override
                public String query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    String password;
                    password = getPasswordByUsername(conn, username);
                    return password;
                }
            });
        } catch (SQLException e) {
            System.out.println("queryForPasswordByUsername: " + e.getMessage());
            return null;
        }
    }

    public List<Garden> getGardensByUser(final String user) {
        return executeTransaction(new Transaction<List<Garden>>() {
            @Override
            public List<Garden> execute(Connection conn) throws SQLException {
                DataSource ds = getMySQLDataSource();
                conn = ds.getConnection();

                PreparedStatement stmt1 = null;
                ResultSet set = null;

                try {

                    stmt1 = conn.prepareStatement(
                            "select mg_garden.garden_id, mg_garden.garden_name, mg_garden.description from mg_garden, mg_user, mg_garden_member where mg_user.user_name = ? and mg_user.user_id = mg_garden_member.user_id and mg_garden_member.garden_id = mg_garden.garden_id"
                    );
                    stmt1.setString(1, user);

                    set = stmt1.executeQuery();

                    List<Garden> returnGardens = new ArrayList<Garden>();
                    //Boolean found = false;

                    while (set.next()) {
                        //found = true;
                        Garden garden = new Garden(null, null);

                        loadGarden(garden, set, 1);
                        returnGardens.add(garden);
                    }

                    //if (!found) {
                    //System.out.println("<" + User + "> is not in the database");
                    //}
                    return returnGardens;
                } finally {
                    DBUtil.closeQuietly(set);
                    DBUtil.closeQuietly(stmt1);
                    DBUtil.closeQuietly(conn);
                }
            }
        });
    }

    public boolean insertNewUserIntoDatabase(final User user) {
        try {
            return doQueryLoop(new Query<Boolean>() {
                @Override
                public Boolean query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    boolean success = false;
                    if (!verifyUserExistsByUsername(conn, user.getUsername())) {
                        if (insertUserIntoUsers(conn, user)) ;
                        success = true;
                    }
                    return success;
                }

            });
        } catch (SQLException e) {
            System.out.println("insertNewUserIntoDatabase: " + e.getMessage());
            return false;
        }
    }

    public boolean insertNewGardenIntoDatabase(final Garden garden) {
        try {
            return doQueryLoop(new Query<Boolean>() {
                @Override
                public Boolean query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    boolean success = false;
                    if (!verifyGardenExistsbyGardenName(garden.getGarden_name())) {
                        if (insertGardenintoGardens(conn, garden)) ;
                        success = true;
                    }
                    return success;
                }
            });
        } catch (SQLException e) {
            System.out.println("insertNewGardenIntoDatabase: " + e.getMessage());
            return false;
        }
    }

    public List<Garden> getGardensLikeKeyword(final String keyword) {
        return executeTransaction(new Transaction<List<Garden>>() {
            @Override
            public List<Garden> execute(Connection conn) throws SQLException {
                DataSource ds = getMySQLDataSource();
                conn = ds.getConnection();

                PreparedStatement stmt1 = null;

                ResultSet set = null;
                System.out.println("reached DB with keyword " + keyword);
                try {

                    stmt1 = conn.prepareStatement(
                            "select mg_garden.garden_id, mg_garden.garden_name, mg_garden.description from mg_garden where mg_garden.garden_name like ? "
                    );
                    stmt1.setString(1, "%" + keyword + "%");

                    set = stmt1.executeQuery();

                    List<Garden> returnGardens = new ArrayList<Garden>();
                    //Boolean found = false;

                    while (set.next()) {
                        //found = true;
                        Garden garden = new Garden(null, null);

                        loadGarden(garden, set, 1);
                        returnGardens.add(garden);
                    }

                    //if (!found) {
                    //System.out.println("<" + keyword + "> not found as a valid group");
                    //}
                    return returnGardens;
                } finally {
                    DBUtil.closeQuietly(set);
                    DBUtil.closeQuietly(stmt1);
                    DBUtil.closeQuietly(conn);
                }
            }
        });
    }

    public List<Garden> getGardenbyGardenName(final String name) {
        return executeTransaction(new Transaction<List<Garden>>() {
            public List<Garden> execute(Connection conn) throws SQLException {
                DataSource ds = getMySQLDataSource();
                conn = ds.getConnection();

                PreparedStatement stmt1 = null;

                ResultSet set = null;

                try {
                    stmt1 = conn.prepareStatement("select mg_garden.garden_id, mg_garden.garden_name, mg_garden.description from mg_garden where mg_garden.garden_name = ?");
                    stmt1.setString(1, name);
                    set = stmt1.executeQuery();


                    List<Garden> returnGarden = new ArrayList<Garden>();
                    Boolean found = false;

                    while (set.next()) {
                        found = true;
                        Garden group = new Garden(null, null);

                        loadGarden(group, set, 1);
                        returnGarden.add(group);
                    }
                    if (!found) {
                        System.out.println("<" + name + "> is not a garden");
                    }

                    return returnGarden;
                } finally {
                    DBUtil.closeQuietly(set);
                    DBUtil.closeQuietly(stmt1);
                    DBUtil.closeQuietly(conn);
                }
            }
        });
    }

    /*
     * ----------------------- Query Utility Functions----------------------------------------------------
     */

    public Garden getGardenbyGardenID(final int ID) {
        return executeTransaction(new Transaction<Garden>() {
            public Garden execute(Connection conn) throws SQLException {
                DataSource ds = getMySQLDataSource();
                conn = ds.getConnection();
                PreparedStatement stmt1 = null;
                ResultSet set = null;
                boolean found = false;

                try {
                    stmt1 = conn.prepareStatement(
                            " select mg_garden.garden_id, mg_garden.garden_name, mg_garden.description from mg_garden where mg_garden.garden_id = ? "
                    );
                    stmt1.setInt(1, ID);
                    set = stmt1.executeQuery();

                    Garden garden = new Garden(null, null);
                    while (set.next()) {
                        found = true;

                        loadGarden(garden, set, 1);
                    }
                    if (!found) {
                        System.out.println("Garden ID Provided is invalid");
                    }

                    return garden;

                } finally {
                    DBUtil.closeQuietly(set);
                    DBUtil.closeQuietly(stmt1);
                    DBUtil.closeQuietly(conn);
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

                try {
                    stmt1 = conn.prepareStatement(
                            " select mg_user.*, mg_post.* from " +
                                    " mg_user, mg_post, mg_garden_member " +
                                    " where mg_post.garden_id = ?  and mg_user.user_id = mg_garden_member.user_id " +
                                    " and mg_garden_member.garden_id = mg_post.garden_id " +
                                    " and mg_user.user_id = mg_post.user_id" +
                                    " order by mg_post.post_id DESC"
                    );

                    stmt1.setInt(1, ID);

                    set = stmt1.executeQuery();

                    List<Pair<User, Post>> returnPosts = new ArrayList<Pair<User, Post>>();
                    //Boolean found = false;

                    while (set.next()) {
                        //found = true;
                        Post post = new Post(null, 0, 0);
                        User user = new User(null, null, 0, null, null, null, null);
                        loadUser(user, set, 1);
                        loadPost(post, set, 8);
                        returnPosts.add(new Pair<User, Post>(user, post));
                    }

                    //if (!found) {
                    //System.out.println("<" + ID + "> is not in the database");
                    //}
                    return returnPosts;
                } finally {
                    DBUtil.closeQuietly(set);
                    DBUtil.closeQuietly(stmt1);
                    DBUtil.closeQuietly(conn);
                }
            }
        });
    }

    public boolean insertNewPostIntoDatabase(final Post post) {
        try {
            return doQueryLoop(new Query<Boolean>() {
                @Override
                public Boolean query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    boolean success = false;
                    if (insertPostintoPosts(conn, post)) {
                        success = true;
                    }
                    return success;
                }

            });
        } catch (SQLException e) {
            System.out.println("insertNewPostIntoDatabase: " + e.getMessage());
            return false;
        }
    }

    public boolean insertNewGardenMemberIntoDatabase(final GardenMember gardenMember) {
        try {
            return doQueryLoop(new Query<Boolean>() {
                @Override
                public Boolean query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    boolean success = false;
                    if (!verifyGardenMemberexistsinGarden(conn, gardenMember)) {
                        if (insertGardenMemberintoGardenMembers(conn, gardenMember))
                            success = true;
                    }
                    return success;
                }

            });
        } catch (SQLException e) {
            System.out.println("insertNewGardenMemberIntoDatabase: " + e.getMessage());
            return false;
        }
    }

    public int getGardenIDByUserID(final int user_id) {
        try {
            return doQueryLoop(new Query<Integer>() {
                @Override
                public Integer query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
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
                        if (!found) {
                            System.out.println("User ID <" + user_id + "> was not found in the accounts table.");
                            System.out.println("Check the SQL code in the query, user_id may need to change.");
                            System.out.println("May also need a many-to-many table to connect gardens <-> accounts.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                        DBUtil.closeQuietly(conn);
                    }
                    return garden_id;
                }
            });
        } catch (SQLException e) {
            System.out.println("Error in getGardenIDByUserID: " + e.getMessage());
            return -1;
        }
    }

    public String getCountyByCountyID(final int county_id) {
        try {
            return doQueryLoop(new Query<String>() {
                @Override
                public String query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    String county_name = "";
                    try {
                        stmt = conn.prepareStatement(
                                " select countyName " +
                                        "	from mg_county"
                                        + " where county_ID = ?"
                        );
                        stmt.setInt(1, county_id);
                        set = stmt.executeQuery();

                        // testing that a set was returned
                        Boolean found = false;

                        if (set.next()) {
                            found = true;
                            county_name = set.getString(1);
                        }
                        if (!found) {
                            System.out.println("County name <" + county_name + "> was not found in the counties table.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                        DBUtil.closeQuietly(conn);
                    }
                    return county_name;
                }
            });
        } catch (SQLException e) {
            return "Error in getCountyByCountyID: " + e.getMessage() + ".";
            //return -1;
        }
    }

    public String getCountyByStateName(final String state_name) {
        try {
            return doQueryLoop(new Query<String>() {
                @Override
                public String query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    String county_name = "";
                    try {
                        stmt = conn.prepareStatement(
                                " select countyName " +
                                        "	from mg_county"
                                        + " where stateName = ?"
                        );
                        stmt.setString(1, state_name);
                        set = stmt.executeQuery();

                        // testing that a set was returned
                        Boolean found = false;

                        if (set.next()) {
                            found = true;
                            county_name = set.getString(1);
                        }
                        if (!found) {
                            System.out.println("County name <" + county_name + "> was not found in the counties table.");

                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                        DBUtil.closeQuietly(conn);
                    }
                    return county_name;
                }
            });
        } catch (SQLException e) {
            return "Error in getCountyByStateName: " + e.getMessage() + ".";
            //return -1;
        }
    }

    public int getCountyIDByGardenName(final String garden_name) {
        try {
            return doQueryLoop(new Query<Integer>() {
                @Override
                public Integer query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    int county_id = 0;

                    try {
                        stmt = conn.prepareStatement(
                                " SELECT county_id " +
                                        "	FROM mg_county"
                                        + " WHERE county_id IN (SELECT county_id FROM mg_garden WHERE garden_name = ?)"
                        );
                        stmt.setString(1, garden_name);
                        set = stmt.executeQuery();

                        // testing that a set was returned
                        Boolean found = false;

                        if (set.next()) {
                            found = true;
                            county_id = set.getInt(1);
                        }
                        if (!found) {
                            System.out.println("No counties matching the name <" + garden_name + "> in the counties table.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                        DBUtil.closeQuietly(conn);
                    }
                    return county_id;
                }
            });
        } catch (SQLException e) {
            System.out.println("Error in getCountyIDByStateName: " + e.getMessage() + ".");
            return -1;
        }
    }

    public String getGardenAddressByGardenID(final int garden_id) {
        try {
            return doQueryLoop(new Query<String>() {
                @Override
                public String query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
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
                        if (!found) {
                            System.out.println("Garden Address <" + garden_address + "> was not found in the gardens table.");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                        DBUtil.closeQuietly(conn);
                    }
                    return garden_address;
                }
            });
        } catch (SQLException e) {
            return "Error in getGardenIDByUserID: " + e.getMessage() + ".";
            //return -1;
        }
    }

    public boolean deleteUserFromDatabase(final User user) {
        try {
            return doQueryLoop(new Query<Boolean>() {
                @Override
                public Boolean query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    boolean success;
                    if (deleteUserByUsername(conn, user)) ;
                    success = true;
                    return success;
                }
            });
        } catch (SQLException e) {
            System.out.println("deleteUserFromDatabase: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyGardenExistsbyGardenName(String garden_name) throws SQLException {
        boolean registered = false;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            stmt = conn.prepareStatement("select * from mg_garden where mg_garden.garden_name = ?");
            stmt.setString(1, garden_name);
            set = stmt.executeQuery();
            if (set.next()) {
                registered = true;
            }
        } catch (SQLException se) {
            // Handles DB errors
            se.printStackTrace();
            return false;
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return registered;
    }

    public boolean updateUserCredentials(final String username, final User user) {
        try {
            return doQueryLoop(new Query<Boolean>() {
                @Override
                public Boolean query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    if (verifyUserExistsByUsername(conn, username))
                        //return updateUserByUsername(conn, username, user, inputStream);
                        return updateUserByUsername(username, user);
                    else
                        return false;
                }
            });
        } catch (SQLException e) {
            System.out.println("updateUserByUsername: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUserPortrait(final String username, final User user, final String filePath) {
        try {
            return doQueryLoop(new Query<Boolean>() {
                @Override
                public Boolean query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    if (verifyUserExistsByUsername(conn, username))
                        return updateUserPortraitByUsername(conn, username, user, filePath);
                    else
                        return false;
                }
            });
        } catch (SQLException e) {
            System.out.println("updateUserByUsername: " + e.getMessage());
            return false;
        }
    }

    public boolean insertUserPortrait(final String username, final User user, final String filePath) {
        try {
            return doQueryLoop(new Query<Boolean>() {
                @Override
                public Boolean query() throws SQLException {
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    if (verifyUserExistsByUsername(conn, username))
                        //return updateUserByUsername(conn, username, user, inputStream);
                        return insertUserPortraitByUsername(conn, username, user, filePath);
                    else
                        return false;
                }
            });
        } catch (SQLException e) {
            System.out.println("updateUserByUsername: " + e.getMessage());
            return false;
        }
    }

    public int queryForVisitCountByFieldsFromPollVisit(final int data_form_id, int pollinator_id, int plant_id, int strain_id){
        try{
            return doQueryLoop(new Query<Integer>(){
                @Override
                public Integer query() throws SQLException{
                    DataSource ds = getMySQLDataSource();
                    Connection conn = ds.getConnection();
                    PreparedStatement stmt = null;
                    ResultSet set = null;
                    int Visitcounts = -1;
                    try{
                        stmt = conn.prepareStatement(
                                " SELECT visit_count FROM mg_pollinator_visit "
                                        + " WHERE data_form_id = ? and  pollinator_id = ? and plant_id = ? and strain_id = ? ");
                        stmt.setInt(1, data_form_id);
                        stmt.setInt(2, pollinator_id);
                        stmt.setInt(3, plant_id);
                        stmt.setInt(4, strain_id);
                        set = stmt.executeQuery();

                        if(set.next()){
                            Visitcounts = set.getInt(1);
                        }
                    }finally{
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(set);
                        DBUtil.closeQuietly(conn);
                    }
                    return Visitcounts;
                }
            });
        }catch(SQLException e){
            System.out.println("queryForVisitCountByFieldsFromPollVisit: "+e.getMessage());
            return -1;
        }
    }

    public boolean insertGardenDocument(String root, int root_id, String filepath) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        boolean success = false;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            stmt = conn.prepareStatement(
                    "INSERT into mg_document (root, root_id, filepath) "
                            + " VALUES(?,?,?)");
            stmt.setString(1, root);
            stmt.setInt(2, root_id);
            stmt.setString(3, filepath);
            stmt.executeUpdate();

            success = true;

        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }
        return success;
    }

    public boolean insertDataFromDataform(final PollinatorDataForm pdf) throws SQLException
    {

        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        PreparedStatement stmt4 = null;
        ResultSet set1 = null;
        ResultSet set2 = null;
        ResultSet set3 = null;
        ResultSet set4 = null;
        boolean result = true;
        int dataform_id = 0;
        int pollinator_visit_count_id = 0;

        // Total number of Pollinators
        int pollinators_total = 9;

        System.out.println("Inserting Dataform data...");
        try {
            int index = 1;
            int generators_total = 4;
            // This is required to make dateCreated and Generated work
            Date date_collected = java.sql.Date.valueOf(pdf.getDate_collected());
            //Date date_generated = java.sql.Date.valueOf(pdf.getDate_generated());
            //Date date_confirmed = java.sql.Date.valueOf(pdf.getDate_confirmed());

            // Insert into the main dataform table
            stmt1 = conn.prepareStatement("INSERT INTO mg_data_form (week_number, garden_id, county_id, generator_id1, generator_id2, generator_id3, generator_id4, " +
                    "date_collected, wind_status, cloud_status, comments, butterfly_moth_comments, confirmed, temperature, monitor_start, monitor_stop)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt1.setInt(index++, pdf.getWeek_number());
            stmt1.setInt(index++, pdf.getGarden_id());
            stmt1.setInt(index++, pdf.getCounty_id());
            //Generator ID Solution
            for(int i = 0; i <= (pdf.getGenerators().size()-1); i++){
                stmt1.setInt(index++, pdf.getGenerators().get(i).getUserId());
            }
            for(int i = 1; i <= (generators_total - pdf.getGenerators().size()); i++){
                stmt1.setInt(index++, -1);
            }
            stmt1.setDate(index++, (java.sql.Date) date_collected);
            stmt1.setString(index++, pdf.getWind_status());
            stmt1.setString(index++, pdf.getCloud_status());
            stmt1.setString(index++, pdf.getComments());
            stmt1.setString(index++, pdf.getButterflyMothComments());
            stmt1.setInt(index++, 0);
            stmt1.setInt(index++, pdf.getTemperature());
            stmt1.setTime(index++, Time.valueOf(pdf.getMonitor_start()));
            stmt1.setTime(index++, Time.valueOf(pdf.getMonitor_stop()));
            stmt1.executeUpdate();

            // Get DataForm ID
            index = 1;
            generators_total = 4;
            stmt4 = conn.prepareStatement("SELECT id FROM mg_data_form WHERE week_number = ? AND garden_id = ? AND county_id = ? AND generator_id1 = ? AND date_collected = ?" +
                    " AND comments = ? AND butterfly_moth_comments = ? AND wind_status = ? AND cloud_status = ? AND generator_id1 = ?");

            stmt4.setInt(1, pdf.getWeek_number());
            stmt4.setInt(2, pdf.getGarden_id());
            stmt4.setInt(3, pdf.getCounty_id());
            stmt4.setInt(4 , pdf.getGenerators().get(0).getUserId());
            stmt4.setDate(5, (java.sql.Date) date_collected);
            stmt4.setString(6, pdf.getComments());
            stmt4.setString(7, pdf.getButterflyMothComments());
            stmt4.setString(8, pdf.getWind_status());
            stmt4.setString(9, pdf.getCloud_status());
            stmt4.setInt(10, pdf.getGenerators().get(0).getUserId());

            set4 = stmt4.executeQuery();
            //System.out.println(set4.getInt(1));
            if(set4.next())
            {
                // It's very unlikely for two dataforms to have exactly the same amount of data,
                // but it's far more likely for errors to occur when getAllDataFormIDs tries to get
                // the last DF id + 1
                dataform_id = set4.getInt(1);
            }

            if (dataform_id == 0) {
                System.out.println("Error Acquiring DataForm ID");
            }

            // TODO: FINISH TESTING THIS

            else {
                System.out.println("Updating Plot data...");

                // Update Plots
                for (int j = 0; j <= (pdf.getPlots().size()-1); j++) {
                    Plot plot = pdf.getPlots().get(j);

                    stmt2 = conn.prepareStatement("UPDATE mg_plot " +
                            "SET percent_coverage = ?, plot_area_dbl = ?" +
                            " WHERE plant_id = ? AND plant_strain_id = ?");
                    stmt2.setDouble(1, plot.getPlot_percent_coverage());
                    stmt2.setDouble(2, plot.getPlot_area_dbl());
                    stmt2.setInt(3, plot.getPlant_id());
                    stmt2.setInt(4, plot.getStrain_id());
                    stmt2.executeUpdate();
                }
                System.out.println("Inserting Pollinator Visit Count data...");

                // Insert Pollinator Visit Counts
                for (int k = 0; k <= (pdf.getPollinatorVisitCounts().size()-1); k++) {
                    PollinatorVisitCount pvc = pdf.getPollinatorVisitCounts().get(k);

                    stmt3 = conn.prepareStatement("INSERT INTO mg_pollinator_visit (data_form_id, pollinator_id, plot_id, plant_id, strain_id, visit_count)" +
                            "VALUES (?, ?, ?, ?, ?, ?)");
                    stmt3.setInt(1, dataform_id);
                    stmt3.setInt(2, pvc.getPollinator_id());
                    stmt3.setInt(3, pvc.getPlot_id());
                    stmt3.setInt(4, pvc.getPlant_id());
                    stmt3.setInt(5, pvc.getStrain_id());
                    stmt3.setInt(6, pvc.getVisit_count());
                    stmt3.executeUpdate();

                    // Set VisitCount ID
                    pollinator_visit_count_id = getAllVisitCountIDs().get(0);
                    if (pollinator_visit_count_id == 0) {
                        System.out.println("Error Acquiring VisitCount ID");
                        result = false;
                        break;
                    }
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.closeQuietly(stmt1);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(stmt3);
            DBUtil.closeQuietly(stmt4);
            DBUtil.closeQuietly(set1);
            DBUtil.closeQuietly(set2);
            DBUtil.closeQuietly(set3);
            DBUtil.closeQuietly(set4);
            DBUtil.closeQuietly(conn);
        }

        if(result) {
            System.out.println("All Dataform fields successfully inserted/updated...");
        }
        else {
            System.out.println("DataForm unsuccessfully generated...Time to debug :(");
        }
        return result;
    }


    /* ---------------------------- Create/Drop Tables & Load Initial Data ------------------------------*/

    public List<User> getAllUsernames() throws SQLException {

        List<User> result_set = new ArrayList<>();
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        try {
            return doQueryLoop(new Query<List<User>>() {
                @Override
                //          (column probably does not exist in any tables
                public List<User> query() throws SQLException {

                    PreparedStatement stmt = null;
                    ResultSet resultSet = null;
                    try {
                        stmt = conn.prepareStatement(
                                " SELECT user_name, email, first_name, last_name FROM mg_user ");

                        resultSet = stmt.executeQuery();

                        boolean found = false;
                        while (resultSet.next())
                        {
                            found = true;
                            User user = new User(null,null, -1,null,null,null,null);
                            user.setUsername(resultSet.getString(1));
                            user.setEmail(resultSet.getString(2));
                            user.setFirstName(resultSet.getString(3));
                            user.setLastName(resultSet.getString(4));

                            result_set.add(user);
                        }
                        if (!found) {
                            System.out.println("getAllUsername query: No users were found in the database (NANI?!)");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(resultSet);
                        DBUtil.closeQuietly(conn);
                    }
                    return result_set;
                }
            });
        } catch (SQLException e) {
            e.getStackTrace();
            // return empty list
            return new ArrayList<User>();
        }
    }

    public List<Garden> getAllGardens() throws SQLException {

        List<Garden> result_set = new ArrayList<>();
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();

        try {
            return doQueryLoop(new Query<List<Garden>>() {
                @Override
                //          (column probably does not exist in any tables
                public List<Garden> query() throws SQLException {

                    PreparedStatement stmt = null;
                    ResultSet resultSet = null;
                    try {
                        stmt = conn.prepareStatement(
                                "SELECT garden_name FROM mg_garden");

                        resultSet = stmt.executeQuery();

                        boolean found = false;
                        while (resultSet.next())
                        {
                            found = true;
                            Garden garden = new Garden(null,null);
                            garden.setGarden_name(resultSet.getString(1));

                            result_set.add(garden);
                        }
                        if (!found) {
                            System.out.println("getAllGardens query: No gardens were found in the database (NANI?!)");
                        }
                    } finally {
                        DBUtil.closeQuietly(stmt);
                        DBUtil.closeQuietly(resultSet);
                        //conn.close();
                    }
                    return result_set;
                }
            });
        } catch (SQLException e) {
            e.getStackTrace();
            // return empty list
            return new ArrayList<Garden>();
        }
    }

    public List<String> getStrainByPlant(final Plant plant) throws SQLException {
        List<String> result_set = new ArrayList<String>();
        List<Integer> id_set = new ArrayList<Integer>();
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        ResultSet set2 = null;
        boolean found = false;

        try {
            stmt = conn.prepareStatement("SELECT strain_id FROM mg_dataform_insert WHERE plant_id = ?");
            stmt.setInt(1, plant.getPlantID());
            set = stmt.executeQuery();

            while (set.next()) {
                found = true;
                id_set.add(set.getInt(1));
            }

            for (int i = 0; i < id_set.size(); i++) {
                stmt2 = conn.prepareStatement("SELECT name FROM mg_plant_strain WHERE strain_id = ?");
                stmt2.setInt(1, id_set.get(i));
                set2 = stmt2.executeQuery();
                result_set.add(set2.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was an error in the getStrainByPlant method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(set2);
            DBUtil.closeQuietly(conn);
        }

        if (!found) {
            System.out.println("There were no Strains found for the designated Plant");
        }

        return result_set;
    }


    public PlantStrain getStrainByStrainID(final int strain_id) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean found = false;
        PlantStrain strain = new PlantStrain(0, 0,null, null);

        try {
            stmt = conn.prepareStatement("SELECT * FROM mg_plant_strain WHERE strain_id = ?");
            stmt.setInt(1, strain_id);
            set = stmt.executeQuery();

            while (set.next()) {
                found = true;
                loadStrain(strain, set, 1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was an error in the getStrainByStrainID method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }

        if (!found) {
            System.out.println("There was no Strain found for the designated Strain ID");
        }

        return strain;
    }


    public Plant getPlantByPlantID(final int plant_id) throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean found = false;
        Plant plant = new Plant(0,null);

        try {
            stmt = conn.prepareStatement("SELECT * FROM mg_plant WHERE plant_id = ?");
            stmt.setInt(1, plant_id);
            set = stmt.executeQuery();

            while (set.next()) {
                found = true;
                loadPlant(plant, set, 1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was an error in the getPlantByPlantID method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(conn);
        }

        if (!found) {
            System.out.println("There was no Plant found for Plant ID <" + plant_id + ">");
        }

        return plant;
    }


    public String getPlantNameByGarden(final Garden garden) throws SQLException {
        String result_set = "!!!ERROR!!!";
        int plant_id = -1;
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet set = null;
        ResultSet set2 = null;
        boolean found = false;

        try {
            stmt = conn.prepareStatement("SELECT plant_id FROM mg_dataform_insert WHERE garden_id = ?");
            stmt.setInt(1, garden.getGarden_id());
            set = stmt.executeQuery();

            if (set.next()) {
                found = true;
                plant_id = set.getInt(1);
            } else {
                System.out.println("Garden ID not found in dataform-insert table");

            }
            if (found) {
                stmt2 = conn.prepareStatement("SELECT plant_name FROM mg_plant WHERE plant_ID = ?");
                stmt2.setInt(1, plant_id);
                set2 = stmt2.executeQuery();
                result_set = set2.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was an error in the getStrainByPlant method.");
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(stmt2);
            DBUtil.closeQuietly(set);
            DBUtil.closeQuietly(set2);
            DBUtil.closeQuietly(conn);
        }

        if (!found) {
            System.out.println("There were no Strains found for the designated Plant");
        }

        return result_set;
    }

    private <ReturnType> ReturnType doQueryLoop(Query<ReturnType> query) throws SQLException {

        Connection conn = connect();

        ReturnType ret = null;
        int times = 0;
        boolean done = false;
        try {
            while (!done && times < MAX_ATTEMPTS) {
                try {
                    //DataSource ds = getMySQLDataSource();
                    //Connection conn = ds.getConnection();
                    ret = query.query();
                    conn.commit();
                    done = true;
                } catch (SQLException e) {
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
        } finally {
            DBUtil.closeQuietly(conn);
        }
    }

    public <ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
        try {
            return doExecuteTransaction(txn);
        } catch (SQLException e) {
            throw new PersistenceException("Transaction failed", e);
        }
    }

    public <ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
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
        user.setEmail(resultSet.getString(index++));
        user.setFirstName(resultSet.getString(index++));
        user.setLastName(resultSet.getString(index++));
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

    private void loadPlant(Plant plant, ResultSet resultSet, int index) throws SQLException {
        plant.setPlantID(resultSet.getInt(index++));
        plant.setPlantName(resultSet.getString(index++));
    }

    private void loadStrain(PlantStrain strain, ResultSet resultSet, int index) throws SQLException {
        strain.setStrainID(resultSet.getInt(index++));
        strain.setStrainName(resultSet.getString(index++));
        strain.setStrainType(resultSet.getString(index++));
    }

    private void loadPlot(Plot plot, ResultSet resultSet, int index) throws SQLException {
        plot.setPlot_id(resultSet.getInt(index++));
        plot.setGarden_id(resultSet.getInt(index++));
        plot.setPlant_id(resultSet.getInt(index++));
        plot.setStrain_id(resultSet.getInt(index++));
        plot.setPlot_height(resultSet.getDouble(index++));
        plot.setPlot_area_dbl(resultSet.getDouble(index++));
        plot.setPlot_percent_coverage(resultSet.getDouble(index++));
        plot.setPlot_blooms_open_status(resultSet.getString(index++));
    }

    private void loadPollinator(Pollinator pollinator, ResultSet resultSet, int index) throws SQLException {
        pollinator.setPollinatorID(resultSet.getInt(index++));
        pollinator.setPollinatorName(resultSet.getString(index++));
        pollinator.setPollinatorType(resultSet.getString(index++));
    }



    public interface Transaction<ResultType> {
        ResultType execute(Connection conn) throws SQLException;
    }

    public interface Query<ReturnType> {
        ReturnType query() throws SQLException;
    }
}
