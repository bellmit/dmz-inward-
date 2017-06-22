package dmz.Junit.Test;


import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @author dmz
 * @date 2017/1/4
 */
// Junit默認的Runner
@RunWith(BlockJUnit4ClassRunner.class)
public class JunitLifeCycleTest {

    public JunitLifeCycleTest()
    {
        super();
        System.out.println("<<Person Constructor>>");
    }

    @BeforeClass
    public static void beforeClassM()
    {
        System.out.println("<<Before Class>>");
    }


    @Before
    public void beforeM()
    {
        System.out.println("<<Before>>");
    }


    @AfterClass
    public static void afterClassM()
    {
        System.out.println("<<After Class>>");
    }


    @After
    public void after()
    {
        System.out.println("<<After>>");
    }

    @Test
    public void testMethod1()
    {
        System.out.println("Test Method 1.");
    }

    @Test
    public void testMethod2()
    {
        System.out.println("Test Method 2.");
    }
}
