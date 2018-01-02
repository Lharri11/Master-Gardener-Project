package model;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PlantTest {
    Plant p1, p2;

    @Before
    public void setup()
    {
        p1 = new Plant(9699, "Red Rover");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionTest()
    {
        p2 = new Plant(-1, "");
    }

    @Test
    public void getPlantIDTest()
    {
        assertEquals(p1.getPlantID(), 9699);
    }

    @Test
    public void setPlantIDTest()
    {
        p1.setPlantID(42);
        assertEquals(p1.getPlantID(), 42);
    }

}