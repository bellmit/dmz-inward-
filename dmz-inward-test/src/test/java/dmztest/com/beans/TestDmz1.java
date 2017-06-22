package dmztest.com.beans;

/**
 * Created by dmz on 2016/2/19.
 */
//@Component(value = "testDmz1")
public class TestDmz1 implements ITestDmz {
    public TestDmz1(){
        System.out.println("TestDmz1 Constructor");
    }
    public void say() {
        System.out.println("TestDmz1");
    }
}
