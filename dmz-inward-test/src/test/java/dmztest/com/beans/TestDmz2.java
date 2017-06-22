package dmztest.com.beans;

/**
 * Created by dmz on 2016/2/19.
 */
//@Component(value = "testDmz2")
public class TestDmz2 implements ITestDmz {
    public TestDmz2(){
        System.out.println("TestDmz2 Constructor.");
    }
    public void say() {
        System.out.println("TestDmz2");
    }
}
