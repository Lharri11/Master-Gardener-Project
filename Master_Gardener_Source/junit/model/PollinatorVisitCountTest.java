package model;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PollinatorVisitCountTest {
    PollinatorVisitCount p1, p2;

    @Before
    public void setup()
    {
        p1 = new PollinatorVisitCount(5, 3, 1, 4, 6,2);
    }

    @Test
    public void getPollinatorVisitCountTest()
    {
        assertEquals(p1.getVisit_count(), 5);
    }

    @Test
    public void setPollinatorVisitCountTest()
    {
    	p1.setVisit_count(42);
    	assertEquals(p1.getVisit_count(), 42);
    }

    @Test
    public void getPollinatorVisitCountIDTest()
    {
        assertEquals(p1.getId(), 5);
    }

    @Test
    public void setPollinatorVisitCountIDTest()
    {
        p1.setId(40);
        assertEquals(p1.getId(), 40);
    }

    @Test
    public void getDataFormIDTest()
    {
        assertEquals(p1.getData_form_id(), 3);
    }

    @Test
    public void setDataFormIDTest()
    {
        p1.setData_form_id(5);
        assertEquals(p1.getData_form_id(), 5);
    }

    @Test
    public void getPollinatorIDTest()
    {
        assertEquals(p1.getPollinator_id(), 1);
    }

    @Test
    public void setPollinatorIDTest()
    {
        p1.setPollinator_id(42);
        assertEquals(p1.getPollinator_id(), 42);
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

}
