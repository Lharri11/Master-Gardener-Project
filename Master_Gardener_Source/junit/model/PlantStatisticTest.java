package model;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PlantStatisticTest {

    private PlantStatistic p1, p2, p3, p4, p5, p6, p7, p8;

    @Before
    public void setup()
    {
        p1 = new PlantStatistic(12, 13, 40, 20, 'e', 't');
        p2 = new PlantStatistic(140, 89, 20, 20, 'g', 'q');
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionTest()
    {
        //Alex - There are many different combinations for this, I tested some simple ones.
        p3 = new PlantStatistic(-20, -60, 101, 10, 'r', 'z');
        p4 = new PlantStatistic(1, -2, 100, 10, 'r', 'r');
        p5 = new PlantStatistic(-1, 2, 101, -1, 'r', 'q');
        p6 = new PlantStatistic(1, -1, 101, 1, 'r', 'r');
        p7 = new PlantStatistic(1, 2, 101, -1, 'r', 'r');
        p8 = new PlantStatistic(1, 2, 3, -1, 'r', 'r');
    }
    */

    @Test
    public void getPlantHeightTest()
    {
        assertEquals(p1.getPlantHeight(), 12);
        assertEquals(p2.getPlantHeight(), 140);
    }

    @Test
    public void setPlantHeightTest()
    {
    	p1.setPlantHeight(420);
    	assertEquals(p1.getPlantHeight(), 420);
    }
    
    @Test
    public void getPlantSizeTest()
    {
        assertEquals(p1.getPlantSize(), 13);
        assertEquals(p2.getPlantSize(), 89);
    }
    
    @Test
    public void setPlantSizeTest()
    {
    	p1.setPlantSize(420);
    	assertEquals(p1.getPlantSize(), 420);
    }

    @Test
    public void getPercentCoverageTest()
    {
        assertEquals(p1.getPercentCoverage(), 40);
        assertEquals(p2.getPercentCoverage(), 20);
    }
    
    @Test
    public void setPercentCoverageTest()
    {
    	p1.setPercentCoverage(99);
    	assertEquals(p1.getPercentCoverage(), 99);
    }

    @Test
    public void getPlantPlotSizeTest()
    {
        assertEquals(p1.getPlotSize(), 20);
        assertEquals(p2.getPlotSize(), 20);
    }
    
    @Test
    public void setPlantPlotSizeTest()
    {
    	p1.setPlotSize(23);
    	assertEquals(p1.getPlotSize(), 23);
    }

    @Test
    public void getPlantBloomsOpenTest()
    {
        assertEquals(p1.getBloomsOpen(), 'e');
        assertEquals(p2.getBloomsOpen(), 'g');
    }

    @Test
    public void setPlantBloomsOpenTest()
    {
    	p1.setBloomsOpen('b');
    	assertEquals(p1.getBloomsOpen(), 'b');
    }
    
    @Test
    public void getPlantVigorTest()
    {
        assertEquals(p1.getVigor(), 't');
        assertEquals(p2.getVigor(), 'q');
    }
    
    @Test
    public void setPlantVigorTest()
    {
    	p1.setVigor('v');
    	assertEquals(p1.getVigor(), 'v');
    }
    
}
