package logika;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BatohTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BatohTest
{
    

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
   

    @Test
    public void testBatoh()
    {
        logika.Batoh batoh1 = new logika.Batoh();
        assertEquals(920, batoh1.getKapacita());
        batoh1.pridejKapacitu(920);
        assertEquals(0, batoh1.getKapacita());
    }
}


