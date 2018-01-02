package model;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PollinatorRecordTest {
    // This test is not finished
    PollinatorRecord p1, p2;

    @Before
    public void setup()
    {
        p1 = new PollinatorRecord(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionTest()
    {
        p2 = new PollinatorRecord(-1);
    }

    @Test
    public void getWeekNumber()
    {
        assertEquals(p1.getWeekNumber(), 10);
    }
    
    @Test
    public void setWeekNumber()
    {
    	p1.setWeekNumber(5);
    	assertEquals(p1.getWeekNumber(), 5);
    }
}
