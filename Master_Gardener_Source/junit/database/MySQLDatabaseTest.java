package database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import database.core.DBUtil;
import database.core.DatabaseProvider;
import database.core.IDatabase;
import database.core.MySQLDatabase;
import model.*;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MySQLDatabaseTest {
    public static final int MAX_ATTEMPTS = 10;
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/MasterGardener";
    private static final String MYSQL_USERNAME = "nsarzyns";
    private static final String MYSQL_PASSWORD = "0100555";

    private IDatabase db = null;

    public static DataSource getMySQLDataSource() {
        MysqlDataSource mysqlDS;
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL("jdbc:mysql://localhost:3306/MasterGardener");
        mysqlDS.setUser("root");
        mysqlDS.setPassword("root");

        return mysqlDS;
    }

    @Before
    public void setUp() throws Exception {
        DatabaseProvider.setInstance(new MySQLDatabase());
        db = DatabaseProvider.getInstance();
        // TODO: Consider dropping/creating tables here (not using the DB methods, but the datagrip method)
    }


    @Test
    public void createCustomQueryTest() {
        String[] select_array = {"not_a_password"};
        String[] from_array = {"not_accounts"};
        String[] where_array = {"pass_wurd", "memesxD"};

        String result_set = db.createCustomQuery(select_array, from_array, where_array, false);
        assertEquals(result_set, "SELECT not_a_password FROM not_accounts WHERE pass_wurd = memesxD");

        String[] slct_array = {"not_a_password"};
        String[] frm_array = {"not_accounts", "not_users.users"};
        String[] whr_array = {"pass_wurd", "memesxD", "def_not_password", "crud"};
        result_set = db.createCustomQuery(slct_array, frm_array, whr_array, false);
        assertEquals(result_set, "SELECT not_a_password FROM not_accounts, not_users.users WHERE pass_wurd = memesxD AND def_not_password = crud");
    }

    @Test
    public void updateXTupleByYTableTest() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;
        try {
            stmt = conn.prepareStatement("INSERT INTO mg_county (county_ID, countyName, stateName) VALUES (-420, 'CrubCounty', 'PAYEEHAW')");
            stmt.executeUpdate();

            String[] col = {"county_ID", "countyName", "stateName"};
            String[] val = {"-1000", "'ALEXCOUNTY'", "'Nay'"};
            String[] where = {"county_ID", "-420", "countyName", "'CrubCounty'", "stateName", "'PAYEEHAW'"};
            if (db.updateXTupleFromYTable("mg_county", col, val, where)) {
                success = true;
            }
            if (success) {
                stmt = conn.prepareStatement("DELETE FROM mg_county WHERE county_ID = -1000 AND countyName = 'ALEXCOUNTY' AND stateName = 'Nay'");
                stmt.executeUpdate();
            } else {
                stmt = conn.prepareStatement("DELETE FROM mg_county WHERE county_ID = -420 AND countyName = 'CrubCounty' AND stateName = 'PAYEEHAW'");
                stmt.executeUpdate();
            }
            assertEquals(success, true);
        } catch (SQLException e) {
            System.out.println("UPDATE X TUPLE BY Y TABLE TEST FAILED! exception:\n" + e);
            System.out.println(stmt);
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }

    }

    @Test
    public void deleteXTupleByYTableTest() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;
        try {
            stmt = conn.prepareStatement("INSERT INTO mg_county (county_ID, countyName, stateName) VALUES (-420, 'CrubCounty', 'PAYEEHAW')");
            stmt.executeUpdate();

            String[] where = {"county_ID", "-420", "countyName", "'CrubCounty'", "stateName", "'PAYEEHAW'"};
            if (db.deleteXTuplefromYTable("mg_county", where)) ;
            {
                success = true;
            }
            if (!success) {
                stmt = conn.prepareStatement("DELETE FROM mg_county WHERE county_ID = -420 AND countyName = 'CrubCounty' AND stateName = 'PAYEEHAW'");
                stmt.executeUpdate();
            }

            assertEquals(success, true);
        } catch (SQLException e) {
            System.out.println("DELETE X TUPLE BY Y TABLE TEST FAILED! exception:\n" + e);
            System.out.println(stmt);
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }

    }

    @Test
    public void getGardenIDByGardenNameTest() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_garden (garden_ID, garden_name, description) VALUES (-420, 'Memes', 'Dank')");
            stmt.executeUpdate();

            if (db.getGardenIDByGardenName("Memes") == -420) {
                success = true;
            }

            stmt = conn.prepareStatement("DELETE FROM mg_garden WHERE garden_ID = -420 AND garden_name = 'Memes' AND description = 'Dank'");
            stmt.executeUpdate();

            assertEquals(success, true);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(stmt);
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void getCountyIDByCountyNameTest() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_county (county_ID, countyName, stateName) VALUES (-430, 'Memes', 'Dank')");
            stmt.executeUpdate();

            if (db.getCountyIDByCountyName("Memes") == -430) {
                success = true;
            }

            stmt = conn.prepareStatement("DELETE FROM mg_county WHERE county_ID = -430 AND countyName = 'Memes' AND stateName = 'Dank'");
            stmt.executeUpdate();

            assertEquals(success, true);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(stmt);
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void getPlantIDByPlantNameTest() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_plant(plant_ID, plantName) VALUES (-942, 'Dank')");
            stmt.executeUpdate();

            if (db.getPlantIDByPlantName("Dank") == -942) {
                success = true;
            }

            stmt = conn.prepareStatement("DELETE FROM mg_plant WHERE plant_ID = -942 AND plantName = 'Dank'");
            stmt.executeUpdate();

            assertEquals(success, true);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(stmt);
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void getStrainIDByStrainNameTest() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_plant_strain(strand_id, strand_name, plant_id) VALUES (-920, 'Crunk', -5)");
            stmt.executeUpdate();

            if (db.getStrainIDByStrainName("Crunk") == -920) {
                success = true;
            }

            stmt = conn.prepareStatement("DELETE FROM mg_plant_strain WHERE strand_id = -920 AND strand_name = 'Crunk' AND plant_id = -5");
            stmt.executeUpdate();

            assertEquals(success, true);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(stmt);
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void getPollinatorIDByPollinatorNameTest() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_pollinator (pollinator_ID, pollinatorName, pollinatorType) VALUES (-200, 'Alex', 'Gross')");
            stmt.executeUpdate();

            if (db.getPollinatorIDByPollinatorName("Alex") == -200) {
                success = true;
            }

            stmt = conn.prepareStatement("DELETE FROM mg_pollinator WHERE pollinator_ID = -200");
            stmt.executeUpdate();

            assertEquals(success, true);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(stmt);
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void getCountyByCountyIDTest() throws SQLException {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_county (county_ID, countyName, stateName) VALUES (-232, 'Alexs', 'Za Warudo')");
            stmt.executeUpdate();

            if (db.getCountyByCountyID(-232).equals("Alexs")) {
                success = true;
            }

            stmt = conn.prepareStatement("DELETE FROM mg_county WHERE county_ID = -232");
            stmt.executeUpdate();

            assertEquals(success, true);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(stmt);
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }


    @Test
    public void getCountyByStateNameTest() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_county (county_ID, countyName, stateName) VALUES (-233, 'Alexs', 'Za Warudo')");
            stmt.executeUpdate();

            if (db.getCountyByStateName("Za Warudo").equals("Alexs")) {
                success = true;
            }

            stmt = conn.prepareStatement("DELETE FROM mg_county WHERE county_ID = -233");
            stmt.executeUpdate();

            assertEquals(success, true);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(stmt);
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    /*
    @Test
    public void getGardenAddressByGardenID() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_garden (garden_ID, garden_name, description) VALUES (-235, 'Skrip', 'Meme!')");
            stmt.executeUpdate();

            if (db.getGardenAddressByGardenID(-235).equals("Alexs")) {
                success = true;
            }

            stmt = conn.prepareStatement("DELETE FROM mg_garden WHERE garden_ID = -235");
            stmt.executeUpdate();

            assertEquals(success, true);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(stmt);
        } finally {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }
    */

    @Test
    public void deleteUserFromDatabaseTest() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        User user = new User( "Alex", "Alex", -1, "Alex@gmail", "name", "desc");
        try {
            stmt = conn.prepareStatement("insert into mg_user (user_ID, userName, passWord, login_id, email, name, description)" +
                    " VALUES (-3, 'Alex', 'Alex', -1, 'Alex@gmail', 'name', 'desc')");
            stmt.executeUpdate();

            if (db.deleteUserFromDatabase(user))
            {
                success = true;
            } else {
                stmt = conn.prepareStatement("DELETE FROM mg_user WHERE userName = 'Alex' AND passWord = 'Alex' AND email = 'Alex@gmail'");
                stmt.executeUpdate();
            }
            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }
    @Test
    public void deletePollinatorFromDatabaseTest () throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        Pollinator poll = new Pollinator(-410, "Pump", "Skrub");
        try {
            stmt = conn.prepareStatement("INSERT INTO mg_pollinator (pollinator_ID, pollinatorName, pollinatorType) VALUES (-410, 'Pump', 'Skrub')");
            stmt.executeUpdate();

            if (db.deletePollinatorFromDatabase(poll))
            {
                success = true;

            } else {
                stmt = conn.prepareStatement("DELETE FROM mg_pollinator WHERE pollinator_ID = -410");
                stmt.executeUpdate();
            }
            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void deleteStrainFromDatabaseTest() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        PlantStrain pl = new PlantStrain(-2, -410, "Pump", "Skrub");

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_plant_strain (strand_id, strand_name, plant_id) VALUES (-2, 'Pump', -410)");
            stmt.executeUpdate();

            if (db.deleteStrainFromDatabase(pl))
            {
                success = true;

            } else {
                stmt = conn.prepareStatement("DELETE FROM mg_plant_strain WHERE strand_id = -2");
                stmt.executeUpdate();
            }
            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void deletePlantFromDatabaseTest() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        Plant pl = new Plant(-4, "Crub");

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_plant (plant_ID, plantName) VALUES (-4, 'Crub')");
            stmt.executeUpdate();

            if (db.deletePlantFromDatabase(pl))
            {
                success = true;

            } else {
                stmt = conn.prepareStatement("DELETE FROM mg_plant WHERE plant_ID = -4");
                stmt.executeUpdate();
            }
            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void deleteGardenFromDatabaseTest() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        Garden grd = new Garden("Skrip", "Spurgle");
        grd.setGarden_id(-20);

        try {
            stmt = conn.prepareStatement("INSERT INTO mg_garden (garden_ID, garden_name, description) VALUES (-20, 'Skrip', 'Spurgle')");
            stmt.executeUpdate();

            if (db.deleteGardenFromDatabase(grd))
            {
                success = true;

            } else {
                stmt = conn.prepareStatement("DELETE FROM mg_garden WHERE garden_ID = -20");
                stmt.executeUpdate();
            }
            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void insertPollinatorIntoTable() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        Pollinator poll = new Pollinator(-35, "Name", "Type");

        try
        {
            if(db.insertPollinatorIntoTable(poll))
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_pollinator WHERE pollinator_ID = -35");
            set = stmt.executeQuery();

            if(set.next())
            {
                System.out.println("Unsuccessful insert during test.");
                success = false;
            }
            if(success)
            {
                stmt = conn.prepareStatement("DELETE FROM mg_pollinator WHERE pollinatorName = 'Name' AND pollinatorType = 'Type'");
                stmt.executeUpdate();
            }
            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void insertPlantStrainIntoTable() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        PlantStrain ps = new PlantStrain(-230, -20, "Strayn", "Type");

        try
        {
            if(db.insertPlantStrainIntoTable(ps))
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_plant_strain WHERE strand_name = 'Strayn' AND plant_id = -20");
            set = stmt.executeQuery();

            if(!set.next())
            {
                System.out.println("Unsuccessful insert during test.");
                success = false;
            }
            if(success)
            {
                stmt = conn.prepareStatement("DELETE FROM mg_plant_strain WHERE strand_name = 'Strayn' AND plant_id = -20");
                stmt.executeUpdate();
            }
            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void insertPlantIntoTable() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        Plant pl = new Plant(-42, "Skrip");

        try
        {
            if(db.insertPlantIntoTable(pl))
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_plant WHERE plantName = 'Skrip'");
            set = stmt.executeQuery();

            if(!set.next())
            {
                System.out.println("Unsuccessful insert during test.");
                success = false;
            }
            if(success)
            {
                stmt = conn.prepareStatement("DELETE FROM mg_plant WHERE plantName = 'Skrip'");
                stmt.executeUpdate();
            }
            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void updatePollinatorName() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_pollinator (pollinator_ID, pollinatorName, pollinatorType) VALUES (-24, 'Crub', 'Skrig')");
            stmt.executeUpdate();

            if(db.updatePollinatorName("Crub", "Yog"))
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_pollinator WHERE pollinatorName = 'Yog'");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_pollinator WHERE pollinator_ID = -24");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void updatePollinatorTypeByName() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_pollinator (pollinator_ID, pollinatorName, pollinatorType) VALUES (-25, 'Crub1', 'Skrig1')");
            stmt.executeUpdate();

            if(db.updatePollinatorTypeByName("Crub1", "Skorp"))
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_pollinator WHERE pollinatorType = 'Skorp'");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_pollinator WHERE pollinator_ID = -25");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void updatePlantName() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_plant (plant_ID, plantName) VALUES (-42, 'Sexy_Crub')");
            stmt.executeUpdate();

            if(db.updatePlantName("Sexy_Crub", "Less Sexy Crub"))
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_plant WHERE plantName = 'Less Sexy Crub'");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_plant WHERE plant_ID = -42");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void updateGardenName() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_garden (garden_ID, garden_name, description) VALUES (-24, 'eggs', 'dungle')");
            stmt.executeUpdate();

            if(db.updateGardenName("eggs", "corgy"));
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_garden WHERE garden_name = 'corgy'");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_garden WHERE garden_id = -24");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void updateGardenDescriptionByName() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_garden (garden_ID, garden_name, description) VALUES (-25, 'eggs', 'dungle')");
            stmt.executeUpdate();

            if(db.updateGardenDescriptionByName("eggs", "i like memes broh"));
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_garden WHERE description = 'i like memes broh'");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_garden WHERE garden_id = -25");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void updateStrainName() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_plant_strain (strand_id, strand_name, plant_id) VALUES (-32, 'egg', -24)");
            stmt.executeUpdate();

            if(db.updateStrainName("egg", "yogg"));
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_plant_strain WHERE strand_name = 'yogg'");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_plant_strain WHERE strand_id = -32");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void updatePlotHeight() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_plot (plot_id, plant_id, plot_height, plot_area_dbl, percent_coverage, blooms_open_status, plant_vigor_status)" +
                    " VALUES (-50, -340, -40.0, -40.0, -40.0, 'ye', 'no')");
            stmt.executeUpdate();

            if(db.updatePlotHeight(-42.0, -50));
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_plot WHERE plot_id = -50 AND plot_height = -42.0");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_plot WHERE plot_id = -50");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void updatePlotArea() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_plot (plot_id, plant_id, plot_height, plot_area_dbl, percent_coverage, blooms_open_status, plant_vigor_status)" +
                    " VALUES (-50, -340, -40.0, -40.0, -40.0, 'ye', 'no')");
            stmt.executeUpdate();

            if(db.updatePlotArea(-42.0, -50));
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_plot WHERE plot_id = -50 AND plot_area_dbl = -42.0");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_plot WHERE plot_id = -50");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }
    @Test
    public void updatePlotPercentCoverage( ) throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_plot (plot_id, plant_id, plot_height, plot_area_dbl, percent_coverage, blooms_open_status, plant_vigor_status)" +
                    " VALUES (-50, -340, -40.0, -40.0, -40.0, 'ye', 'no')");
            stmt.executeUpdate();

            if(db.updatePlotPercentCoverage(-42.0, -50));
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_plot WHERE plot_id = -50 AND percent_coverage = -42.0");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_plot WHERE plot_id = -50");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void updatePlotBloomsOpen() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_plot (plot_id, plant_id, plot_height, plot_area_dbl, percent_coverage, blooms_open_status, plant_vigor_status)" +
                    " VALUES (-50, -340, -40.0, -40.0, -40.0, 'ye', 'no')");
            stmt.executeUpdate();

            if(db.updatePlotBloomsOpen("nuh", -50));
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_plot WHERE plot_id = -50 AND blooms_open_status = 'nuh'");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_plot WHERE plot_id = -50");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }

    @Test
    public void updatePlotVigorStatus() throws SQLException
    {
        DataSource ds = getMySQLDataSource();
        Connection conn = ds.getConnection();
        PreparedStatement stmt = null;
        ResultSet set = null;
        boolean success = false;

        try
        {
            stmt = conn.prepareStatement("INSERT INTO mg_plot (plot_id, plant_id, plot_height, plot_area_dbl, percent_coverage, blooms_open_status, plant_vigor_status)" +
                    " VALUES (-50, -340, -40.0, -40.0, -40.0, 'ye', 'no')");
            stmt.executeUpdate();

            if(db.updatePlotVigorStatus("go",-50));
            {
                success = true;
            }

            stmt = conn.prepareStatement("SELECT * FROM mg_plot WHERE plot_id = -50 AND plant_vigor_status = 'go'");
            set = stmt.executeQuery();

            if(!set.next())
            {
                success = false;
                System.out.println("Something went wrong in tests");
            }

            stmt = conn.prepareStatement("DELETE FROM mg_plot WHERE plot_id = -50");
            stmt.executeUpdate();

            assertEquals(success, true);
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println(stmt);
        }
        finally
        {
            DBUtil.closeQuietly(stmt);
            DBUtil.closeQuietly(set);
        }
    }
}