package model;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PlantStrainTest {
    PlantStrain p1, p2;

    @Before
    public void setup()
    {
        p1 = new PlantStrain(1, 2, "Alex Fly Trap", "Egg");
    }

    @Test
    public void getStrainNameTest()
    {
        assertEquals(p1.getStrainName(), "Alex Fly Trap");
    }

    @Test
    public void getStrainIDTest()
    {
        assertEquals(p1.getStrainID(), 1);
    }

    @Test
    public void getPlantIDTest()
    {
        assertEquals(p1.getPlantID(), 2);
    }

    @Test
    public void getStrainTypeTest()
    {
        assertEquals(p1.getStrainType(), "Egg");
    }

    @Test
    public void setStrainIDTest()
    {
        p1.setStrainID(3);
        assertEquals(p1.getStrainID(), 3);
    }

    @Test
    public void setPlantIDTest()
    {
        p1.setPlantID(4);
        assertEquals(p1.getPlantID(), 4);
    }

    @Test
    public void setStrainTypeTest()
    {
        p1.setStrainType("Gorgle");
        assertEquals(p1.getStrainType(), "Gorgle");
    }

    @Test
    public void setStrainNameTest()
    {
        p1.setStrainName("Alex Tried Trap");
        assertEquals(p1.getStrainName(), "Alex Tried Trap");
    }

}
