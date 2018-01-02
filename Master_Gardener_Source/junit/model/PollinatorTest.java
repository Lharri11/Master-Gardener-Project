package model;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PollinatorTest {
    Pollinator p1, p2, p3, p4, p5, p6, p7;

    @Before
    public void setup()
    {
        p1 = new Pollinator(2444, "Bungle Tree", "Grossopotamus");
    }

    @Test
    public void getPollinatorIDTest()
    {
        assertEquals(p1.getPollinatorID(), 2444);
    }

    @Test
    public void setPollinatorIDTest()
    {
    	p1.setPollinatorID(42);
    	assertEquals(p1.getPollinatorID(), 42);
    }
    
    @Test
    public void getPollinatorNameTest()
    {
        assertEquals(p1.getPollinatorName(), "Bungle Tree");
    }

    @Test
    public void setPollinatorNameTest()
    {
    	p1.setPollinatorName("crub");
    	assertEquals(p1.getPollinatorName(), "crub");
    }
    
    @Test
    public void getPollinatorTypeTest()
    {
        assertEquals(p1.getPollinatorType(), "Grossopotamus");
    }
    
    @Test
    public void setPollinatorTypeTest()
    {
    	p1.setPollinatorType("trub");
    	assertEquals(p1.getPollinatorType(), "trub");
    }
}
