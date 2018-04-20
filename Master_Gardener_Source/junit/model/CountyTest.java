package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountyTest {
    County c1, c2;

    @Before
    public void setup()
    {
        c1 = new County("York", "Pennsylvania");
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionTest()
    {
        // Error checking the state is likely not needed if we simply use a drop down box. The only thing to
        // check here is county ID, which must be positive, non-zero.
        c2 = new County(-222, "York", "Massadingdong");
    }*/

    @Test
    public void getCountyIDTest()
    {
        assertEquals(c1.getCountyID(), 324);
    }

    @Test
    public void setCountyIDTest()
    {
    	c1.setCountyID(42);
    	assertEquals(c1.getCountyID(), 42);
    }
    
    @Test
    public void getCountyNameTest()
    {
        assertEquals(c1.getCountyName(), "York");
    }
    
    @Test
    public void setCountyNameTest()
    {
    	c1.setCountyName("Edwardo");
    	assertEquals(c1.getCountyName(), "Edwardo");
    }

    @Test
    public void getStateNameTest()
    {
        assertEquals(c1.getStateName(), "Pennsylvania");
    }
    
    @Test
    public void setStateNameTest()
    {
    	c1.setStateName("Not Pennsylvania");
    	assertEquals(c1.getStateName(), "Not Pennsylvania");
    }

}
