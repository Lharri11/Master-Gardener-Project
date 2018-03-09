package model;

import org.junit.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PollinatorDataFormTest {

    private PollinatorDataForm p1;
    // There isn't really a use to having 4 different instances of LocalDateTime but it helps keep track of things.
    private LocalDate date_created, date_generated;
    private LocalTime monitor_start, monitor_stop;
    private ArrayList<User> gardeners = new ArrayList<User>();
    private ArrayList<Pollinator> pollinators = new ArrayList<Pollinator>();
    private ArrayList<Plant> plants = new ArrayList<Plant>();
    private ArrayList<PlantStrain> plant_strains = new ArrayList<PlantStrain>();

    @Before
    public void setup()
    {
    	// FIXME
        User a1 = new User("Grundy", "boodle", 3, "eggs","dungle", "guy@yahoo.com", "im a guy man", null);
    	// FIXME
        User a2 = new User("Skroble", "boodle", 5, "cheese", "whiz", "man@yahoo.com", "im a guy man", null);
    	// FIXME
        User a3 = new User("Chicken Dinner", "boodle",  7, "green","pantspoop", "dude@yahoo.com", "im a guy man", null);

        Pollinator pl1 = new Pollinator(1, "Brumble Bree", "Bee");
        Plant pp1 = new Plant(1, "Egg Plant");
        PlantStrain ps1 = new PlantStrain(2, 1, "Gross", "Idunno");
        ArrayList<Plot> plot_list = new ArrayList<Plot>();
        plot_list.add(new Plot(3, 4, 2, 3.0, 30.0, "b", "v", "D"));

        gardeners.add(a1);
        gardeners.add(a2);
        gardeners.add(a3);
        pollinators.add(pl1);
        plants.add(pp1);
        plant_strains.add(ps1);


        //Some arguments are exaggerated and only exist to test.
       // p1 = new PollinatorDataForm(1, 2, 3, 50, 3, 40, date_created, date_generated,
           //     monitor_start, monitor_stop, "m", "Memes", "c", "w", plot_list, new Plant(1, "yes"));
    }

    @Test
    public void getGardenIDTest()
    {
        assertEquals(p1.getGarden_id(), 2);
    }

    @Test
    public void setGardenerIDTest()
    {
        p1.setGarden_id(3);
        assertEquals(p1.getGarden_id(), 3);
    }

    @Test
    public void getCountyIDTest()
    {
        assertEquals(p1.getCounty_id(), 3);
    }

    @Test
    public void setCountyIDTest()
    {
        p1.setCounty_id(5);
        assertEquals(p1.getCounty_id(), 5);
    }

    @Test
    public void getWeekNumberTest()
    {
        assertEquals(p1.getWeek_number(), 3);
    }

    @Test
    public void setWeekNumberTest()
    {
        p1.setWeek_number(4);
        assertEquals(p1.getWeek_number(), 4);
    }

    @Test
    public void getTemperatureTest()
    {
        assertEquals(p1.getTemperature(), 50);
    }

    @Test
    public void setTemperatureTest()
    {
        p1.setTemperature(60);
        assertEquals(p1.getTemperature(), 60);
    }

    // TODO: TEST LOCALDATETIMES HERE
    @Test
    public void AllLocalDateTimeTests()
    {
        System.out.println("LOCALDATETIME GETTER/SETTER TESTS NOT IMPLEMENTED.");
    }

    @Test
    public void getCloudTest()
    {
        assertEquals(p1.getCloud_status(), "c");
    }

    @Test
    public void setCloudTest()
    {
        p1.setCloud_status("r");
        assertEquals(p1.getCloud_status(), "r");
    }

    @Test
    public void getWindTest()
    {
        assertEquals(p1.getWind_status(), "w");
    }

    @Test
    public void setWindTest()
    {
        p1.setWind_status("c");
        assertEquals(p1.getWind_status(), "c");
    }

    @Test
    // TODO: Theses tests suck
    public void getGardenersTest()
    {
        p1.setGardeners(gardeners);
        assertEquals(p1.getGardeners(), gardeners);
    }

    @Test
    public void setGardenersTest()
    {
    	// FIXME
        User a4 = new User("Memer", "Crub", 1, "a", "s",  "b", "c", null );
        gardeners.add(a4);
        p1.setGardeners(gardeners);
        assertEquals(p1.getGardeners(), gardeners);
    }

    @Test
    public void getPollinatorsTest()
    {
        assertEquals(p1.getPollinators(), pollinators);
    }

    @Test
    public void setPollinatorsTest()
    {
        Pollinator pl2 = new Pollinator(1,"Greg", "Yes");
        pollinators.add(pl2);
        p1.setPollinators(pollinators);
        assertEquals(p1.getPollinators(), pollinators);
    }

    @Test
    public void getPlantStrainTest()
    {
        assertEquals(p1.getPlantStrains(), plant_strains);
    }

    @Test
    public void setPlantStrainTest()
    {
        PlantStrain ps2 = new PlantStrain(2, 1, "Asb", "Bab");
        plant_strains.add(ps2);
        p1.setPlantStrains(plant_strains);
        assertEquals(p1.getPlantStrains(), plant_strains);
    }

}
