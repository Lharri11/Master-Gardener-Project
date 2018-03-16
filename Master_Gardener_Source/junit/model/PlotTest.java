package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlotTest {
    Plot p1;

    @Before
    public void setup()
    {
        p1 = new Plot(1, 4, 50.0, 35.0, 0.0, "b", "v", "t");
    }

    /*@Test
    public void getPlotHeightTest()
    {
        // Delta describes how close they should be. The fudge factor.
        assertEquals(p1.getPlot_height(), 5.0, .1);
    }*/

    @Test
    public void setPlotHeightTest()
    {
        p1.setPlot_height(3.67);
        assertEquals(p1.getPlot_height(), 3.67, 0.01);
    }

    @Test
    public void getPlotIDTest()
    {
        assertEquals(p1.getPlot_id(), 1);
    }

    @Test
    public void setPlotIDTest()
    {
        p1.setPlot_id(42);
        assertEquals(p1.getPlot_id(), 42);
    }

    @Test
    public void getPlantIDTest()
    {
        assertEquals(p1.getPlant_id(), 4);
    }

    @Test
    public void setPlantIDTest()
    {
        p1.setPlant_id(42);
        assertEquals(p1.getPlant_id(), 42);
    }

    /*@Test
    public void getPlotAreaTest()
    {
        assertEquals(p1.getPlot_area_dbl(), 35.0, 0.01);
    }*/

    @Test
    public void setPlotAreaTest()
    {
        p1.setPlot_area_dbl(37.5);
        assertEquals(p1.getPlot_area_dbl(), 37.5, 0.01);
    }

    /*@Test
    public void getPercentCoverageTest()
    {
        assertEquals(p1.getPercent_coverage(), 50.0, 0.01);
    }*/

    @Test
    public void setPercentCoverageTest()
    {
        p1.setPercent_coverage(69.5);
        assertEquals(p1.getPercent_coverage(), 69.5, 0.01);
    }

    /*@Test
    public void getPlotAreaUnitTest()
    {
        assertEquals(p1.getPlot_area_unit(), "This unit does something");
    }*/

    @Test
    public void setPlotAreaUnitTest()
    {
        p1.setPlot_area_unit("Egg");
        assertEquals(p1.getPlot_area_unit(), "Egg");
    }

    /*@Test
    public void getBloomsOpenStatusTest()
    {
        assertEquals(p1.getBlooms_open_status(), "b");
    }*/

    @Test
    public void setBloomsOpenStatusTest()
    {
        p1.setBlooms_open_status("o");
        assertEquals(p1.getBlooms_open_status(), "o");
    }

    /*@Test
    public void getPlantVigorStatusTest()
    {
        assertEquals(p1.getPlant_vigor_status(), "v");
    }*/

    @Test
    public void setPlantVigorStatusTest()
    {
        p1.setPlant_vigor_status("s");
        assertEquals(p1.getPlant_vigor_status(), "s");
    }
}
